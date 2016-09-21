/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entities.User;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.Contract;
import utils.Login;
import utils.StringUtils;


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
        String email = request.getParameter("email");
        String name = request.getParameter("username");
        String pass1 = request.getParameter("password");
        String pass2 = request.getParameter("password_match");
        Login checker = new Login();
        
        try{
            //check user name
            if (StringUtils.isBlank(name)) {
                sendMessage(request, response, "User name cannot be empty.");
                return;
            } else if (userFacade.isExist("name", name)) {
                sendMessage(request, response, "User name " + name + " already exist.");
                return;
            }

            //check email
            if (!checker.validateEmail(email)) {
                sendMessage(request, response, "Please input an valid email address.");
                return;
            } else if (userFacade.isExist("email", email)) {
                sendMessage(request, response, "Email " +email+ " already exist.");
                return;
            }
        }catch(Exception ex){
            
        }
        
        //check password
        if (!checker.validatePassword(pass1)) {
            sendMessage(request, response, "Please input an valid password. "
                    + "Four characters minimum, must include an uppercase, lowercase, and numeric character. No spaces.");
            return;
        }else if (pass1 == null ? pass2 != null : !pass1.equals(pass2)) {
            sendMessage(request, response, "The passwords you input do not match.");
            return;
        }
        
        //create user
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
        setCollectionSuburbs(request);
        getView(request, response, "user/edit.jsp");
    }
    
    public void editPost(HttpServletRequest request, HttpServletResponse response) {
        User user = getCurrentUser(request);
        
        user.setSuburbId(suburbFacade.findById(Integer.parseInt(request.getParameter("suburb_id"))));
        user.setEmail(request.getParameter("email"));
        
        userFacade.edit(user);
        
        edit(request, response);
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
