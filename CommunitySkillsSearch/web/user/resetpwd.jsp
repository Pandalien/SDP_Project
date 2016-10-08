<%-- 
    Document   : resetpwd
    Created on : Oct 6, 2016, 9:41:00 PM
    Author     : AD
--%>

<%@page import="utils.Contract"%>
<%@page import="entities.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="main">
    <div class="login_form">
  
        <h2 style="margin-top: -10px;">Reset Password</h2> 
        <p>You may use this form to receive a new password to the email address you signed up with.</p>

        <form id="resetpwd" action="reset?action=resetpwd" onsubmit="" method="post">
            <input type="text" name="username" pattern=".{4,}" title="Four or more characters" placeholder="user name" required/>
            <input type="text" placeholder="email address" name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" required/>
            <button>Send Password Reset E-mail</button>
            <p class="text">Received your new password? <a href="user?action=login">Sign In</a></p>
        </form>
            
    </div>
</div>
 