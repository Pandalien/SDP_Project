<%-- 
    Document   : browse
    Created on : Apr 22, 2016, 8:40:48 AM
    Author     : Andy Chen
--%>

<%@page import="util.Contract"%>
<%@page import="java.util.List"%>
<%@page import="entities.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<User> list = (List<User>) request.getAttribute(Contract.USERS);
%>
<%
    if (list != null) {
%>

<table>
    <tr><th>Name</th><th>Rating</th><th>Location</th><th>Member Since</th>
        <%for (User user : list) {%>
    <tr>
        <td><a href="listing?action=browse&roll=seller&member=<%= user.getId()%>"><%= user.getName()%></a></td>
        <td><a href="user?action=details&member=<%= user.getId()%>"><%= user.getRating()%></a></td>
        <td><%= user.getIntroduction()%></td>
        <td><%= user.getJoinedDate()%></td>
    </tr>
        <%}%>
</table>

<%}%>


