/*
 * Software Development Practice, Stream 50 Team 2
 * Community Skills Search
 */
package servlets;

import beans.RequestData;
import entities.Messages;
import entities.User;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.Contract;
import utils.StringUtils;


/**
 *
 * @author andyc
 */
public class MessageServlet extends AbstractServlet {

    public void index(HttpServletRequest request, HttpServletResponse response) {
        viewNew(request, response);
    }

    //View: get messages that have been read
    public void viewRead(HttpServletRequest request, HttpServletResponse response) {
        getMessage(request, response);
        
        request.setAttribute("message_option", "viewRead");
        
        User user = getCurrentUserOrLogin(request, response);
        if (user == null) {
            return;
        }
        
        List<Messages> messages = messagesFacade.findByReceiverIdAndRead(user, true);
        request.setAttribute(Contract.MESSAGES_RECEIVED, messages);
        
        getView(request, response, "messages/_layout.jsp");
    }

    //View: view messages that have been sent
    public void viewSent(HttpServletRequest request, HttpServletResponse response) {
        getMessage(request, response);
        
        User user = getCurrentUserOrLogin(request, response);
        if (user == null) {
            return;
        }
        
        List<Messages> messages = messagesFacade.findBySenderId(user);

        request.setAttribute(Contract.MESSAGES_RECEIVED, messages);

        request.setAttribute("message_option", "viewSent");
        getView(request, response, "messages/_layout.jsp");
    }
    
    //View: view new messages
    public void viewNew(HttpServletRequest request, HttpServletResponse response) {
        Messages msg = getMessage(request, response);
        if (msg != null) {
            //mark message as read
            msg.setIsRead(Boolean.TRUE);
            messagesFacade.edit(msg);
        }

        //return list of messages (new message handled by AbstractSevlet.invokeMethod() on every request)
        request.setAttribute("message_option", "viewNew");
        getView(request, response, "messages/_layout.jsp");
    }
 
    //query messages from db
    private Messages getMessage(HttpServletRequest request, HttpServletResponse response) {
        User user = getCurrentUserOrLogin(request, response);
        if (user == null) {
            return null;
        }

        int id = getRequestId(request);
        if (id == -1) {
            return null;
        }
        
        List<Messages> msg = messagesFacade.findByUserAndId(user, id);
        if (msg != null && !msg.isEmpty()) {
            //set selected massage
            request.setAttribute(Contract.MESSAGES_SELECTED, msg.get(0));
            
            //get the sender object
            request.setAttribute(Contract.OTHER_USER, msg.get(0).getSenderId());
            
            return msg.get(0);
        }
        
        return null;
    }
    
    //View: create a message to send
    public void compose(HttpServletRequest request, HttpServletResponse response) {
        RequestData data = getAuthenticatedData(request, response);
        if (data == null) {
            return;
        }
        
        User receiver = userFacade.find(data.id);
        if (null == receiver) {
            alertDanger(request, "The user record was not found.");
            showGoBackPage(request, response);
            return;
        }
        
        if (Objects.equals(receiver.getId(), data.user.getId())) {
            alertWarning(request, "You cannot send messages to yourself.");
            showGoBackPage(request, response);
            return;
        }
        
        request.setAttribute(Contract.OTHER_USER, receiver);
        
        getView(request, response, "messages/compose.jsp");
    }
    
    //View: send message
    public void composePost(HttpServletRequest request, HttpServletResponse response) {
        RequestData data = getAuthenticatedData(request, response);
        if (data == null) {
            return;
        }
        
        User receiver = userFacade.find(data.id);
        if (null == receiver) {
            alertDanger(request, "The user record was not found.");
            showGoBackPage(request, response);
            return;
        }
        
        if (Objects.equals(receiver.getId(), data.user.getId())) {
            alertWarning(request, "You cannot send messages to yourself.");
            showGoBackPage(request, response);
            return;
        }
        
        String message = request.getParameter("message");
        if (StringUtils.isBlank(message)) {
            alertWarning(request, "You message cannot be empty.");
            showGoBackPage(request, response);
            return;
        }
        
        Messages msg = new Messages();
        msg.setSenderId(data.user);//sender
        msg.setReceiverId(receiver);//receiver
        msg.setContent(message);
        msg.setIsRead(Boolean.FALSE);
        msg.setSentTime(new Date());
        
        messagesFacade.create(msg);
        
        request.setAttribute("message_option", "viewNew");
        alertSuccess(request, "Message sent.");
        redirect(request, response, "/messages?action=viewNew");
    }
    
    @Override
    protected void invokeMethod(HttpServletRequest req, HttpServletResponse resp, boolean doPost) {
        req.setAttribute("current_path", "Messages");
        super.invokeMethod(req, resp, doPost);
    }
}
