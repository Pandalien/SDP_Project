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
import util.Contract;
import util.Login;


public class UserServlet extends AbstractServlet {  
    @Override
    public void init() {
    }
    
    public void browse(HttpServletRequest request, HttpServletResponse response) {
        List<User> users = userFacade.findAll();
        request.setAttribute(Contract.USERS, users);
        getView(request, response, "user/browse.jsp");
    }
    
    public void create(HttpServletRequest request, HttpServletResponse response) {
        //set a list of suburbs to request attributes
        setCollectionSuburbs(request);     
        getView(request, response, "user/create.jsp");
    }
    
    public void createPost(HttpServletRequest request, HttpServletResponse response) {
        String pass1 = request.getParameter("password");
        String pass2 = request.getParameter("password_match");
        if (pass1 == null ? pass2 != null : !pass1.equals(pass2)) {
            sendMessage(request, response, "The passwords you input do not match.");
            return;
        }
        
        String email = request.getParameter("email");
        String name = request.getParameter("pass1");

        Login checker = new Login();
        if (checker.validatePassword(pass1)) {
            sendMessage(request, response, "Please input an valid password.");
            return;
        }
        
        if (!checker.validateEmail(email)) {
            sendMessage(request, response, "Please input an valid email address.");
            return;
        }
        
        User user = new User(
                name,
                pass1,
                email,
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
    
    public void loginPost(HttpServletRequest request, HttpServletResponse response) {
        User user = userFacade.findByUsernameAndPassword(request.getParameter("username"), request.getParameter("password"));
        request.getSession().setAttribute(Contract.CURRENT_USER, user);
        login(request, response);
    }
    
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        sessionEnd(request);
        getView(request, response, "index.jsp");
    }
    
    protected void sessionEnd(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(Contract.CURRENT_USER, null);
    }

    protected void sessionStart(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        session.setAttribute(Contract.CURRENT_USER, user);
    }
}
