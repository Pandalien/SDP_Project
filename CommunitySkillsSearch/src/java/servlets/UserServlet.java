/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entities.Suburb;
import entities.User;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sb.SuburbFacade;


public class UserServlet extends AbstractServlet {  

    @EJB
    private SuburbFacade suburbFacade;
    @Override
    public void init() {
    }
    
    public void browse(HttpServletRequest request, HttpServletResponse response) {
        List<User> users = userFacade.findAll();
        request.setAttribute("users", users);
        getView(request, response, "user/browse.jsp");
    }
    
    public void create(HttpServletRequest request, HttpServletResponse response) {
        List<Suburb> suburbs = suburbFacade.findAll();
        request.setAttribute("suburbs", suburbs);
        
        getView(request, response, "user/create.jsp");
    }
    
    public void createPost(HttpServletRequest request, HttpServletResponse response) {
        User user = new User(
                request.getParameter("username"),
                request.getParameter("password"),
                request.getParameter("email"),
                new Integer(request.getParameter("gender")),
                suburbFacade.findById(Integer.parseInt(request.getParameter("suburb")))
        );

        try {
            userFacade.create(user);
            sessionStart(request, user);
            sendMessage(request, response, "Hello " + user.getName() + ", your account was created successfully!");
        } catch (Exception e) {
            sendMessage(request, response, "Failed to created account:" + e);
        }
    }
    
    public void details(HttpServletRequest request, HttpServletResponse response) {
//        User user = userFacade.findById(request.getParameter("member"));
//        
//        if (user == null) {
//            error404(request, response);
//            return;
//        }
//        
//        request.setAttribute("user", user);
        getView(request, response, "user/details.jsp");
    }
    
    public void edit(HttpServletRequest request, HttpServletResponse response) {
        getView(request, response, "user/edit.jsp");
    }
    
    public void login(HttpServletRequest request, HttpServletResponse response) {
        getView(request, response, "user/login.jsp");
    }
    
    public void loginPost(HttpServletRequest request, HttpServletResponse response) {
        User user = userFacade.findByUsernameAndPassword(request.getParameter("username"), request.getParameter("password"));
        request.getSession().setAttribute(CurrentUserAttributeName, user);
        login(request, response);
    }
    
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        sessionEnd(request);
        getView(request, response, "index.jsp");
    }
    
    protected void sessionEnd(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(CurrentUserAttributeName, null);
    }

    protected void sessionStart(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        session.setAttribute(CurrentUserAttributeName, user);
    }
}