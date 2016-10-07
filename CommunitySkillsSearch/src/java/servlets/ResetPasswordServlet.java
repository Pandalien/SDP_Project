/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entities.User;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.*;
import javax.mail.internet.MimeMessage;
import utils.EditAccount;
import utils.Login;
import utils.RandomPassword;
import utils.StringUtils;

/**
 *
 * @author AD
 */
public class ResetPasswordServlet extends AbstractServlet {


    public void resetpwd(HttpServletRequest request, HttpServletResponse response) {
      getView(request, response, "resetpwd.jsp");
    }

    public void resetpwdPost(HttpServletRequest request, HttpServletResponse response) {

      String name = request.getParameter("username");
      String email = request.getParameter("email");
        
      Login passwordChecker = new Login();
      EditAccount emailChecker = new EditAccount();
      
      int errorCount = 0;
        
      try {
        //check user name
        if (StringUtils.isBlank(name)) {
          alertDanger(request, "User name cannot be empty.");
          errorCount++;
        } 
/*        
        else if (!userFacade.isExist("name", name)) {
          alertDanger(request, "User name " + name + " does not exist.");
          errorCount++;
        }
*/

        //check email
        if (!emailChecker.validateEmail(email)) {
          alertDanger(request, "Please input an valid email address.");
          errorCount++;
        } 
/*        
        else if (!userFacade.isExist("email", email)) {
          alertDanger(request, "Email " +email+ " does not exist.");
          errorCount++;
        }
*/
      }
      catch(Exception ex) {
        alertDanger(request, "System error.");
        errorCount++;
      }
        
      // steps:
      // 1. retrieve user
      // 2. set password to randomly generated string
      // 3. send same string to email address
      // 4. user may log-in & change password whenever.
      if (errorCount == 0) {
        
        User user = userFacade.findByUsernameAndEmail(name, email);       
        if (user == null) {
          alertDanger(request, "That is not a registered username or email address.");
        }
        else {
          
          boolean email_sent = false;
          
          String resetpass = RandomPassword.makeRandomPassword();
          
          // send email using JavaMail API
          String transport = "smtp";          
          String smtp_host = "localhost";
          Integer smtp_port = 2525;
          
          String smtp_user = "";
          String smtp_password = "";
          
          // message
          String subject = "Password Reset";          
          String from = "Community Skills Search";
          String from_email = "noreply@localhost";
          String to = user.getEmail();
          String body = "Hello " + user.getName() + ",\n\nyour new password is\n\n" + resetpass + "\n\n" +
                  "Please log in and change it now.";

          Properties props = new Properties();        
          props.put("mail.smtp.host", smtp_host);
          props.put("mail.smtp.port", smtp_port);
          props.put("mail.smtp.user", smtp_user);
          
          Session session = Session.getInstance(props);
                   
          try {
            Transport tr = session.getTransport("smtp");
            tr.connect(smtp_host, smtp_user, smtp_password);
            
            MimeMessage msg = new MimeMessage(session);          
            msg.setFrom(from_email);
            msg.setRecipients(Message.RecipientType.TO, user.getEmail());
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setText(body);            
            msg.saveChanges();
            
            tr.sendMessage(msg, msg.getAllRecipients());
            tr.close();  
            
            email_sent = true;
          }                   
          catch (MessagingException mex) {
            System.out.println("send failed, exception: " + mex);
          }      
            
          // store it    
          if (email_sent) {
            user.setPassword(resetpass);
            userFacade.edit(user);   
            alertSuccess(request, "Password reset. Please check your email.");               
          }          
          else {
            alertDanger(request, "Could not reset password.");            
          }
          
        }
        
      }       
     
      // default action is to show the same page again      
      resetpwd(request, response);
    }
  
}
