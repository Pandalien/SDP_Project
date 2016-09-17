<%-- 
    Document   : details user, ratings etc.
    Created on : Apr 17, 2016, 1:51:07 PM
    Author     : Andy Chen
--%>

<%@page import="util.Contract"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Objects"%>
<%@page import="entities.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    User user = (User) request.getAttribute(Contract.OTHER_USER);
    User loggedInUser = (User) session.getAttribute(Contract.CURRENT_USER);
    if (user != null) {
%>
<h1><a href="#"><%= user.getName()%></a></h1>
    <%if (loggedInUser != null && Objects.equals(loggedInUser.getId(), user.getId())) {%>
<a href="listing?action=browse&roll=winner&member=<%= user.getId()%>">Items I won</a><br/>
<a href="listing?action=browse&roll=supplier&member=<%= user.getId()%>">Sold items</a><br/>
<a href="listing?action=browse&roll=seller&member=<%= user.getId()%>">Items I'm selling</a><br/>
<%}%>
<table>
    <tr><th>Name:</th><td><%= user.getName()%></td></tr>
    <tr><th>Rating:</th><td><%= user.getRating()%></td></tr>
    <tr><th>Introduction:</th><td><%= user.getIntroduction()%></td></tr>
    <tr><th>Member Since:</th><td><%= user.getJoinedDate()%></td></tr>
</table>
<a href="listing?action=browse&roll=seller&member=<%= user.getId()%>">Listings</a>
<%}%>
