package servlets;

import beans.MessageType;
import beans.ServerMessage;
import entities.Classification;
import entities.Skills;
import entities.Suburb;
import entities.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
import sb.ClassificationFacade;
import sb.SkillsFacade;
import sb.SuburbFacade;
import sb.UserFacade;
import utils.Contract;
import utils.StringUtils;


public class AbstractServlet extends HttpServlet {

    @EJB
    private ClassificationFacade classificationFacade;
    @EJB
    protected UserFacade userFacade;  
    
    @EJB
    protected SuburbFacade suburbFacade;
    
    @EJB
    private SkillsFacade skillsFacade;
    
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
        String action = req.getParameter("action");
        if (action == null) {
            action = "index";
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
    
    public void alert(HttpServletRequest req, String msg, MessageType type){
        //get old object
        List<ServerMessage> msgs = (List<ServerMessage>) req.getAttribute(Contract.MESSAGES_FROM_SERVER);
        if (msgs == null) {
            msgs = new ArrayList<>();
        }
        
        //update to the new one
        msgs.add(new ServerMessage(msg, type));
        req.setAttribute(Contract.MESSAGES_FROM_SERVER, msgs);
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
