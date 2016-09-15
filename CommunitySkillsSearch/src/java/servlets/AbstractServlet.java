package servlets;

import entities.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sb.UserFacade;


public class AbstractServlet extends HttpServlet {

    @EJB
    protected UserFacade userFacade;  
    final String CurrentUserAttributeName = "current_user";
   
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
        return (User)request.getSession().getAttribute(CurrentUserAttributeName);
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
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            String msg = "Page not found.";
            if (ex.getCause()!=null) {
                if (ex.getCause().getLocalizedMessage() != null) {
                    msg = ex.getCause().getLocalizedMessage();
                }
            }
            
            sendMessage(req, resp, msg);
        }
    }

    protected void error404(HttpServletRequest req, HttpServletResponse resp){
        sendMessage(req, resp, "The page does not exist.");
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
}