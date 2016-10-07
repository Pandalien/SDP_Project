<%-- 
    Document   : login
    Created on : Apr 17, 2016, 4:31:18 PM
    Author     : Andy Chen, Matt Chung
--%>
<%@page import="utils.Contract"%>
<%@page import="java.util.List"%>
<%@page import="sb.SuburbFacade"%>
<%@page import="entities.Suburb"%>
<%@page import="entities.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Suburb> suburbs = (List<Suburb>) request.getAttribute(Contract.SUBURBS);
%>
<%
    User user = (User) session.getAttribute(Contract.CURRENT_USER);
    if (user == null) {
%>
<h1 class="text-center">COMMUNITY SKILLS SEARCH</h1>
<div class="main">
    <div class="login_form">
        <form id="login_login" class="login" action="user?action=login" method="post">
            <input type="text" name="username" placeholder="username"/>
            <input type="password" name="password" placeholder="password"/>
            <p id="forgotpw"><a href="reset?action=resetpwd">Forgot password?</a></p>
            <button>login</button>
            <p class="text">Not registered? <a href="#" class="togglelink">Create an account</a></p>
        </form>
        <form id="login_create" class="register" action="user?action=create" onsubmit="return isPasswordsMatch()" method="post">
            <input type="text" name="username" pattern=".{4,}" title="Four or more characters" placeholder="user name" required/>
            <input type="text" placeholder="email address" name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" required/>
            <input type="password" id="pass1" placeholder="password" name="password"  pattern=".{4,}" title="Four or more characters" required/>
            <input type="password" id="pass2" placeholder="confirm password" name="password_match"/>
            <select name='suburb' class="ui-select">
                <%for (Suburb s : suburbs) {%>
                <option value='<%=s.getId()%>'><%=s.getSuburb()%></option>
                <%}%>
            </select>
            <button>Register</button>
            <p class="text">Already registered? <a href="#" class="togglelink">Sign In</a></p>
        </form>
    </div>
</div>
                    
<%
} else {
%>
<p>Welcome back, <a href="user?action=details&member=<%= user.getId()%>"><%= user.getName()%></a></p>
<a href="home">Go to home page</a>
<%
    }
%>
<script type="text/javascript" src="js/login.js"></script>
<%--testing--%>