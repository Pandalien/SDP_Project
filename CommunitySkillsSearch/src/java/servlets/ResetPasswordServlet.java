/*
 * ResetPasswordServlet
 * If an input username and email address matches an existing account in the 
 * database, a new random password is generated and sent to the email address
 * on file. SMTP server properties are loaded from WEB-INF/smtp.properties. 
 */
package servlets;

import entities.User;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Properties;
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
      getView(request, response, "/user/resetpwd.jsp");
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
        //check email
        if (!emailChecker.validateEmail(email)) {
          alertDanger(request, "Please input an valid email address.");
          errorCount++;
        } 
      }
      catch(Exception ex) {
        alertDanger(request, "System error.");
        errorCount++;
      }
        
      // steps:
      // 1. retrieve user
      // 2. set password to randomly generated string
      // 3. send same string to email address
      // 4. if email sent without errors, store reset password in database
      if (errorCount == 0) {
        
        User user = userFacade.findByUsernameAndEmail(name, email);       
        if (user == null) {
          alertDanger(request, "That is not a registered username or email address.");
        }
        else {
          boolean email_sent = false;

          // make random password
          String resetpass = RandomPassword.makeRandomPassword();
         
          // --- smtp defaults which can be overridden by smtp.properties file ---
          String smtp_host = "localhost";
          Integer smtp_port = 2525;
          String smtp_user = "";
          // ---
          
          String smtp_password = "";
          
          // reset email fields and body content
          String subject = "Password Reset";          
          String from = "Community Skills Search";
          String from_email = "Community Skills Search <noreply@localhost>";
          String to = user.getEmail();
          String body = "Hello " + user.getName() 
                  + ",\n\nYour password has been reset to:\n\n" 
                  + resetpass 
                  + "\n\n"
                  + "Regards, \n"
                  + from;
          
          Properties props = new Properties();
          try {
            props.load(getServletContext().getResourceAsStream("/WEB-INF/smtp.properties"));
          }
          catch (Exception e) {
            System.err.println(e);
            props.put("mail.smtp.host", smtp_host);
            props.put("mail.smtp.port", smtp_port);
            props.put("mail.smtp.user", smtp_user);            
          }
          
          // send email using JavaMail API
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
//            getView(request, response, "user/login.jsp");            
//            return;
          }          
          else {
            alertDanger(request, "Could not reset password.");            
          }
          
        }
        
      }       
     
      // default action is to show the same page again      
      redirect(request, response, "/reset?action=resetpwd");
    }
    
    
  
}
