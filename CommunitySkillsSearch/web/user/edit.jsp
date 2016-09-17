<%-- 
    Document   : edit
    Created on : Apr 17, 2016, 1:53:46 PM
    Author     : Andy Chen
--%>

<%@page import="util.Contract"%>
<%@page import="entities.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

    <h1>Update user profile</h1>

    <%
        User user = (User) session.getAttribute(Contract.CURRENT_USER);
        if (user != null) {
    %>
    <table>
        <tr><th>Name:</th><td><%= user.getName()%></td></tr>
        <tr><th>Rating:</th><td><%= user.getRating()%></td></tr>
        <tr><th>Introduction:</th><td><%= user.getIntroduction()%></td></tr>
        <tr><th>Member Since:</th><td><%= user.getJoinedDate()%></td></tr>
    </table>
    <a href="listing?action=browse&roll=seller&member=<%= user.getId()%>">Listings</a>
    <%}%>