<%-- 
    Document   : login
    Created on : Apr 17, 2016, 4:31:18 PM
    Author     : Andy Chen
--%>

<%@page import="entities.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    User user = (User) session.getAttribute("current_user");
    if (user == null) {
%>
<h1>Log in</h1>
<form action="user?action=login" method="post">
    <table>
        <tr><td>User Name:</td><td><input type="text" name="username"/></td></tr>
        <tr><td>Password:</td><td><input type="password" name="password"/></tr>
    </table>
    <input type="submit" value="Log in"/>
</form>
<a href="user?action=create">Sign up</a>
<%
} else {
%>
<p>Welcome back, <a href="user?action=details&member=<%= user.getId()%>"><%= user.getName()%></a></p>
<a href="home">Go to home page</a>
<%
    }
%>

