package servlets;

import beans.MessageType;
import beans.RequestData;
import beans.ServerMessage;
import entities.Classification;
import entities.Messages;
import entities.Skills;
import entities.Suburb;
import entities.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sb.AdvertsFacade;
import sb.ClassificationFacade;
import sb.MessagesFacade;
import sb.SkillsFacade;
import sb.SuburbFacade;
import sb.UserFacade;
import utils.Contract;
import utils.StringUtils;


public class AbstractServlet extends HttpServlet {

    @EJB
    protected AdvertsFacade advertsFacade;

    @EJB
    protected MessagesFacade messagesFacade;

    @EJB
    protected ClassificationFacade classificationFacade;
    @EJB
    protected UserFacade userFacade;  
    
    @EJB
    protected SuburbFacade suburbFacade;
    
    @EJB
    protected SkillsFacade skillsFacade;
    
    protected String parametersGet(HttpServletRequest req){
        StringBuilder sb = new StringBuilder();
        boolean first;
        Enumeration<String> parameterNames = req.getParameterNames();

        while (parameterNames.hasMoreElements()) {

            String paramName = parameterNames.nextElement();
            sb.append(paramName).append("=");

            String[] paramValues = req.getParameterValues(paramName);
            first = true;
            for (String paramValue : paramValues) {
                sb.append(paramValue);
                if (first) {
                    first = false;
                } else {
                    sb.append(",");
                }
            }
            sb.append("&");
        }
        
        return sb.toString();
    }
    
    protected void parametersLog(HttpServletRequest req, HttpServletResponse res){
        Logger.getLogger(AbstractServlet.class.getName()).log(Level.INFO, parametersGet(req));
    }

    protected void parameterPrint(HttpServletRequest req, HttpServletResponse res) {
        try (PrintWriter out = res.getWriter()) {
            res.setContentType("text/plain");
            out.write(parametersGet(req));
        } catch (IOException ex) {
            Logger.getLogger(AbstractServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void responsePrint(HttpServletResponse response, String text) {
        try {
            try (PrintWriter out = response.getWriter()) {
                response.setContentType("text/plain");
                out.write(text + "\n");
            }
        } catch (Exception ex) {
            Logger.getLogger(AbstractServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //get user object from seesion, if not found redirct to log in page, 
    //the caller should stop the process if this method return null 
    protected User getCurrentUserOrLogin(HttpServletRequest request, HttpServletResponse response) {
        //get user object from seesion
        User user = getCurrentUser(request);

        //if not found redirct to log in page
        if (user == null) {
            alertInfo(request, "Please log on to continue.");
            login(request, response);
        }

        return user;
    }
    
    //get user object from seesion
    protected User getCurrentUser(HttpServletRequest request){
        return (User)request.getSession().getAttribute(Contract.CURRENT_USER);
    }
        
    protected void getView(HttpServletRequest request, HttpServletResponse response, String serverPage){
        String template = "/layout.jsp";
        request.setAttribute("content", serverPage);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(template);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(AbstractServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void invokeMethod(HttpServletRequest req, HttpServletResponse resp, boolean doPost){
        //call a public method according to the action name, if empty, index() is invoked
        String action = req.getParameter("action");
        if (action == null) {
            action = "index";
        }
        
        //check for new messages
        User user = getCurrentUser(req);
        if (user != null) {
            List<Messages> messages = messagesFacade.findByReceiverIdAndRead(user, false);
            if (messages != null && !messages.isEmpty()) {
                req.setAttribute(Contract.MESSAGES_RECEIVED, messages);
            }
        }   
        
        try {
            if (doPost) {
                action += "Post";
            }
            Method m = this.getClass().getMethod(action, new Class[]{HttpServletRequest.class, HttpServletResponse.class});
            Object ret = m.invoke(this, new Object[]{req, resp});
        } catch (NoSuchMethodException ex) {
            alertWarning(req, "Page not found.");
            showGoBackPage(req, resp);
        } catch(SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex){
            alertDanger(req, ex.toString());
            showGoBackPage(req, resp);
        }
    }

    protected void error404(HttpServletRequest req, HttpServletResponse resp){
        alertWarning(req, "The page does not exist.");
        showGoBackPage(req, resp);
    }
    
    public void alert(HttpServletRequest req, String msg, MessageType type, String link){
        //get old object
        List<ServerMessage> msgs = (List<ServerMessage>) req.getAttribute(Contract.MESSAGES_FROM_SERVER);
        if (msgs == null) {
            msgs = new ArrayList<>();
        }
        
        //update to the new one
        ServerMessage m = new ServerMessage(msg, type);
        m.setLink(link);
        
        msgs.add(m);
        req.setAttribute(Contract.MESSAGES_FROM_SERVER, msgs);
    }
    
    public void alert(HttpServletRequest req, String msg, MessageType type){
        alert(req, msg, type, "");
    }
    
    public void alertSuccess(HttpServletRequest req, String msg){
        alert(req, msg, MessageType.SUCCESS);
    }
    
    public void alertInfo(HttpServletRequest req, String msg){
        alert(req, msg, MessageType.INFO);
    }
    
    public void alertWarning(HttpServletRequest req, String msg){
        alert(req, msg, MessageType.WARNING);
    }
    
    public void alertDanger(HttpServletRequest req, String msg){
        alert(req, msg, MessageType.DANGER);
    }
    
    public void showGoBackPage(HttpServletRequest req, HttpServletResponse resp){
        getView(req, resp, "shared/goback.jsp");
    }
    
    public void showConfirmPage(HttpServletRequest req, HttpServletResponse resp, String confirmMsg, String action, int id){
        //show confirm message
        alertInfo(req, confirmMsg);
        
        //set action and id to form tag
        req.setAttribute("request_action", action);
        req.setAttribute("request_id", id);
        
        getView(req, resp, "shared/confirm.jsp");
    }
    
    protected void sendMessage(HttpServletRequest req, HttpServletResponse resp, String msg){
        req.setAttribute("server_message", msg);
        getView(req, resp, "shared/message.jsp");
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        invokeMethod(req, resp, true);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        invokeMethod(req, resp, false);
    }
    
    public void login(HttpServletRequest request, HttpServletResponse response) {
        //save original query url to hidden text input (see login.jsp)
        String redirect = request.getParameter("redirect");
        if (StringUtils.isEmpty(redirect)) {
            String action = request.getParameter("action");
            if (!StringUtils.isEmpty(action) && !action.equals("login")) {
                //create a new one if not exist
                String url = URLEncoder.encode(request.getRequestURL() + "?" + request.getQueryString());
                request.setAttribute("redirect", url);
            }
            
        } else {
            //use the old one
            request.setAttribute("redirect", redirect);
        }
        
        
        
        setCollectionSuburbs(request);
        getView(request, response, "user/login.jsp");
    }
    
    public int getRequestId(HttpServletRequest request){
        String strId = request.getParameter("id");
        
        if (StringUtils.isNumeric(strId)) {
            return Integer.parseInt(strId);
        }
        
        return -1;
    }
    
    public RequestData getAuthenticatedData(HttpServletRequest request, HttpServletResponse response){
        User user = getCurrentUserOrLogin(request, response);
        if (user == null) {
            return null;
        }

        int id = getRequestId(request);
        if (id == -1) {
            alertWarning(request, "The request page was not found.");
            showGoBackPage(request, response);
            return null;
        }
        
        return new RequestData(user, id);
    }
    
    protected void setCollectionSkills(HttpServletRequest request){
        List<Skills> skills = skillsFacade.findAll();
        request.setAttribute(Contract.SKILLS, skills);
    }
    
    protected void setCollectionSuburbs(HttpServletRequest request){
        List<Suburb> suburbs = suburbFacade.findAll();
        request.setAttribute(Contract.SUBURBS, suburbs);
    }
    
    protected void setCollectionClassifications(HttpServletRequest request){
        List<Classification> clz = classificationFacade.findAll();
        request.setAttribute(Contract.CLASSIFICATIONS, clz);
    }
}
