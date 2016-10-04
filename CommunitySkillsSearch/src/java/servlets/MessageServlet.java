/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.MessageType;
import beans.RequestData;
import entities.Messages;
import entities.MessagesPK;
import entities.User;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sb.MessagesFacade;
import utils.Contract;
import utils.StringUtils;


/**
 *
 * @author andyc
 */
public class MessageServlet extends AbstractServlet {

    @EJB
    private MessagesFacade messagesFacade;
    
    public void index(HttpServletRequest request, HttpServletResponse response) {
        User user = getCurrentUser(request);
        List<Messages> messages = messagesFacade.findByReceiverIdAndRead(user, false);
        request.setAttribute(Contract.MESSAGES_RECEIVED, messages);
    }
    
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
        
        request.setAttribute(Contract.OTHER_USER, receiver);
        
        getView(request, response, "message/compose.jsp");
    }
    
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
        
        String message = request.getParameter("message");
        if (StringUtils.isBlank(message)) {
            alertWarning(request, "You message cannot be empty.");
            showGoBackPage(request, response);
            return;
        }
        
        Messages msg = new Messages();
        MessagesPK msgPk = new MessagesPK();
        msgPk.setReceiverId(receiver.getId());
        msgPk.setSenderId(data.user.getId());
        msg.setMessagesPK(msgPk);
        
        msg.setUser(data.user);//sender
        msg.setUser1(receiver);//receiver
        msg.setContent(message);
        
        messagesFacade.create(msg);
        
        alertSuccess(request, "Message sent.");
        getView(request, response, "message/index.jsp");
    }
}
