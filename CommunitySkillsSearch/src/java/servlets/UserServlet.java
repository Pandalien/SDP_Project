/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.RequestData;
import entities.User;
import entities.UserSkills;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sb.UserSkillsFacade;
import utils.Contract;
import utils.EditAccount;
import utils.Login;
import utils.StringUtils;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB 
        maxFileSize = 1024 * 1024 * 2, // 2 MB
        maxRequestSize = 1024 * 1024 * 2, // 2 MB
        location = "/")
public class UserServlet extends AbstractServlet {

    @EJB
    private UserSkillsFacade userSkillsFacade;
    
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

        user.setIntroduction("");
        user.setJoinedDate(new Date());
        user.setPhone("");
        user.setVisible(Boolean.TRUE);
        
        try {
            userFacade.create(user);
            sessionStart(request, user);
            alertSuccess(request, "Hello " + user.getName() + ", your account is created successfully!");
            edit(request, response);
        } catch (Exception e) {
            alertDanger(request, "Failed to create account:" + e);
        }
    }
    
    public void edit(HttpServletRequest request, HttpServletResponse response) {
        User user = getCurrentUser(request);
        List<UserSkills> userSkills = userSkillsFacade.findByUserId(user.getId());
        for(UserSkills us : userSkills){
            us.setSkills(skillsFacade.find(us.getUserSkillsPK().getSkillsId()));
        }
        request.setAttribute("skillsList", userSkills);
        setCollectionSuburbs(request);
        setCollectionSkills(request);
        getView(request, response, "user/edit.jsp");
    }
    
    public void editPost(HttpServletRequest request, HttpServletResponse response) {
        // get the current user
        User user = getCurrentUser(request);
        // get the user input in email & phone field
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        // get the user choice in suburb field and set it to the user
        user.setSuburbId(suburbFacade.findById(Integer.parseInt(request.getParameter("suburb_id"))));
        // get the user choice in the visible checkbox and set it to the user
        user.setVisible((request.getParameter("visible") == null ? false : true));
        // get the user input in introduction textarea and set it to the user
        user.setIntroduction(request.getParameter("intro"));
        // deletes all existing skills in database for this user
        List<UserSkills> existingSkills = userSkillsFacade.findByUserId(user.getId());
        for (UserSkills us : existingSkills)
            userSkillsFacade.remove(us);
        // get the user input in skills field and save it into database UserSkill entity
        String skills = request.getParameter("current_skill");
        String[] skillsID = skills.split(",");
        if (skillsID !=null && skillsID.length != 0) {
            for (String id : skillsID) {
                if (StringUtils.isBlank(id)) {
                    continue;
                }
                UserSkills userSkill = new UserSkills(user.getId(), Integer.parseInt(id));
                userSkill.setLevel(1);
                userSkillsFacade.create(userSkill);
            }  
        }
        // validate email & phone before saving into database
        EditAccount editAccount = new EditAccount();
        if (editAccount.validateEmail(email) && editAccount.validatePhone(phone)) {
            user.setEmail(email);
            user.setPhone(phone);
            // save the changes of the user into datase User entity
            userFacade.edit(user);
            // alert the user the profile has been updated
            alertSuccess(request, "Profile saved.");
        }  
        else {
            if (!editAccount.validateEmail(email))
              alertDanger(request, "Please input a valid email address.");
            if (!editAccount.validatePhone(phone))
              alertDanger(request, "Please input a valid contact number. (eg. 1234567890,  (123)-456-7890, 123-456-7890 x0000)");
        }
        
        edit(request, response);
    }
    
    public void changepwd(HttpServletRequest request, HttpServletResponse response) {
      getView(request, response, "user/changepwd.jsp");
    }
    
    public void changepwdPost(HttpServletRequest request, HttpServletResponse response) {
      User user = getCurrentUser(request);
      
      if (user == null) {
        alertDanger(request, "Not logged in");
      }
      else {
        String pass0 = request.getParameter("pass0"); // old
        String pass1 = request.getParameter("pass1"); // new
        String pass2 = request.getParameter("pass2"); // verify new   
        
        // check current password to verify the user
        if (!user.getPassword().equals(pass0)) {
          alertWarning(request, "Old password mismatch.");
        }
        else {
          // check new password          
          Login passwordChecker = new Login();
          if (!passwordChecker.validatePassword(pass1)) {
            alertDanger(request, "Please input a valid password. "
                    + "Four characters minimum, must include an uppercase, lowercase, and numeric character. No spaces.");
          }
          else if (pass1 == null ? pass2 != null : !pass1.equals(pass2)) {
            alertDanger(request, "The new passwords you input do not match.");
          }
          else {
            // all checked out so change the password:
            user.setPassword(pass2);
            userFacade.edit(user);
            alertSuccess(request, "Password changed. Please log in again to continue.");
            
            // require the user to log in again with the new password to continue:
            logout(request, response);
            return;
          }
        }
      }

      // default action is to show the same page again
      changepwd(request, response);               
    }   
    
    public void loginPost(HttpServletRequest request, HttpServletResponse response) {
/*      
        List<User> users = userFacade.findByName(request.getParameter("username"));
        
        if (users == null || users.isEmpty()) {
            alertDanger(request, "The username you entered does not exist.");
        }else{
*/
            User user = userFacade.findByUsernameAndPassword(request.getParameter("username"), request.getParameter("password"));

            if (user == null) {
                alertDanger(request, "Your username or password is wrong, please try again.");
            }else{
                request.getSession().setAttribute(Contract.CURRENT_USER, user);
            }
//        }
        
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
            alertDanger(request, "Your username or password is wrong, please try again.");
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
        User user = userFacade.findByUsernameAndPassword("Andy", "Andy");

        devLoginWrapper(request, response, user);
    }
    
    @Override
    protected void invokeMethod(HttpServletRequest req, HttpServletResponse resp, boolean doPost) {
        req.setAttribute("current_path", "User");
        
        //features in this servlet require users to login
        User user = getCurrentUser(req);
        if (false && user == null) {
            login(req, resp);
            return;
        }
        
        super.invokeMethod(req, resp, doPost);
    }
    
    //view a user's details, or themselve's profile
    public void view(HttpServletRequest request, HttpServletResponse response) {
        RequestData data = getAuthenticatedData(request, response);
        if (data != null) {
            User worker = userFacade.find(data.id);
            
            if (worker != null) {
                if (worker.getId() == data.user.getId()) {
                    //view profile
                    edit(request, response);
                }else{
                    //view other's profile
                    request.setAttribute(Contract.OTHER_USER, worker);
                    getView(request, response, "user/view.jsp");
                }
                return;
            }
        }
        
        alertWarning(request, "The worker is not found.");
        showGoBackPage(request, response);
    }
    
    public void delete(HttpServletRequest request, HttpServletResponse response) {
        int id = getRequestId(request);
        showConfirmPage(request, response, "Are you sure you want to delete your account?", "user?action=delete", id);
    }
    
    public void deletePost(HttpServletRequest request, HttpServletResponse response) {
        User user = getCurrentUser(request);
        userFacade.remove(user);
        
        sessionEnd(request);
        alertSuccess(request, "Account Deleted :(");
        getView(request, response, "index.jsp");
    }
}
