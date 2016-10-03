/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entities.User;
import entities.UserSkills;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sb.AdvertsFacade;
import utils.Contract;
import utils.EditAccount;
import utils.Login;
import utils.StringUtils;


public class UserServlet extends AbstractServlet {

    @EJB
    private AdvertsFacade advertsFacade;
    
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
        int errorCount = 0;
        String email = request.getParameter("email");
        String name = request.getParameter("username");
        String pass1 = request.getParameter("password");
        String pass2 = request.getParameter("password_match");
        Login passwordChecker = new Login();
        EditAccount emailChecker = new EditAccount();
        
        try{
            //check user name
            if (StringUtils.isBlank(name)) {
                errorCount++;
                alertDanger(request, "User name cannot be empty.");
            } else if (userFacade.isExist("name", name)) {
                errorCount++;
                alertDanger(request, "User name " + name + " already exist.");
            }

            //check email
            if (!emailChecker.validateEmail(email)) {
                errorCount++;
                alertDanger(request, "Please input an valid email address.");
            } else if (userFacade.isExist("email", email)) {
                errorCount++;
                alertDanger(request, "Email " +email+ " already exist.");
            }
        }catch(Exception ex){
            
        }
        
        //check password
        if (!passwordChecker.validatePassword(pass1)) {
            errorCount++;
            alertDanger(request, "Please input an valid password. "
                    + "Four characters minimum, must include an uppercase, lowercase, and numeric character. No spaces.");
            
        }else if (pass1 == null ? pass2 != null : !pass1.equals(pass2)) {
            errorCount++;
            alertDanger(request, "The passwords you input do not match.");
        }
        
        if (errorCount > 0) {
            showGoBackPage(request, response);
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
            alertSuccess(request, "Hello " + user.getName() + ", your account was created successfully!");
            edit(request, response);
        } catch (Exception e) {
            alertDanger(request, "Failed to created account:" + e);
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
        User user = getCurrentUser(request);
        Collection<UserSkills> userSkills = user.getUserSkillsCollection();
        request.setAttribute("skillsList", userSkills);
        setCollectionSuburbs(request);
        setCollectionSkills(request);
        getView(request, response, "user/edit.jsp");
    }
    
    public void editPost(HttpServletRequest request, HttpServletResponse response) {
        User user = getCurrentUser(request);
        user.setPhone(request.getParameter("phone"));
        user.setSuburbId(suburbFacade.findById(Integer.parseInt(request.getParameter("suburb_id"))));
        user.setVisible((request.getParameter("visible") == null ? false : true));
        user.setIntroduction(request.getParameter("intro"));
        //user.setEmail(request.getParameter("email"));
        
        userFacade.edit(user);
        edit(request, response);
    }
    
    public void loginPost(HttpServletRequest request, HttpServletResponse response) {
        List<User> users = userFacade.findByName(request.getParameter("username"));
        
        if (users == null || users.isEmpty()) {
            alertDanger(request, "The user you entered does not exist.");
        }else{
            User user = userFacade.findByUsernameAndPassword(request.getParameter("username"), request.getParameter("password"));

            if (user == null) {
                alertDanger(request, "Your user and pass did not match, please try again.");
            }else{
                request.getSession().setAttribute(Contract.CURRENT_USER, user);
            }
        }
        
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
    
    protected void devLoginWrapper(HttpServletRequest request, HttpServletResponse response, User user) {
        if (user == null) {
            alertDanger(request, "Your user and pass did not match, please try again.");
            login(request, response);
        } else {
            alertWarning(request, "You've logged in as the developer");
            request.getSession().setAttribute(Contract.CURRENT_USER, user);
        }

        getView(request, response, "index.jsp");
    }
    
    public void developerLogin(HttpServletRequest request, HttpServletResponse response) {
        User user = userFacade.findByUsernameAndPassword("admin", "admin");

        devLoginWrapper(request, response, user);
    }
    
    public void mattLogin(HttpServletRequest request, HttpServletResponse response) {
        User user = userFacade.findByUsernameAndPassword("Matt", "Matt");

        devLoginWrapper(request, response, user);
    }
    
    public void andyLogin(HttpServletRequest request, HttpServletResponse response) {
        User user = userFacade.findByUsernameAndPassword("andy", "andy");

        devLoginWrapper(request, response, user);
    }
}
