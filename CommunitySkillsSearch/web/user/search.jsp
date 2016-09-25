<%-- 
    Document   : search
    Created on : Sep 21, 2016, 6:10:20 PM
    Author     : Dan
--%>
<%@page import="entities.Adverts"%>
<%@page import="entities.Skills"%>
<%@page import="java.util.List"%>
<%@page import="entities.Classification"%>
<%@page import="entities.Suburb"%>
<%@page import="utils.Contract"%>
<%@page import="entities.User"%>
<%
    List<Skills> skills = (List<Skills>) request.getAttribute(Contract.SKILLS);
    List<Suburb> suburbs = (List<Suburb>) request.getAttribute(Contract.SUBURBS);
    List<Classification> classifications = (List<Classification>) request.getAttribute(Contract.CLASSIFICATIONS);
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<h1>COMMUNITY SKILLS SEARCH</h1>
<div class="main">
    <div class="form">
        <form action="search">
            <input type="hidden" name="action" value="searchFor">
            Location <select name="suburb"> 
                <%for (Suburb sub : suburbs) {%>
                <option value="<%=sub.getId()%>"><%=sub.getSuburb()%></option>
                <%}%>
            </select>
            Category <select name="classification"> 
                <%for (Classification cls : classifications) {%>
                <option value="<%=cls.getId()%>"><%=cls.getName()%></option>
                <%}%>
            </select>
            <br/>
            Skills <select name="skills" multiple="true"> 
                <%for (Skills s : skills) {%>
                <option value="<%=s.getId()%>"><%=s.getName()%></option>
                <%}%>
            </select>
            <br/>
            Keywords <input type="text" name="keywords" placeholder="keywords" pattern="^[A-Za-z0-9_]{1,15}$"/>
            <input type="submit" value="Search"/>
            <input type="reset" value="Reset"/>
            <input type="radio" name="type" value="workers"/> For Worker
            <input type="radio" name="type" value="jobs"/> For Jobs
            <br/>
        </form>   
    <div/>
<div/>
<!-- search results for jobs -->
<%
    List<Adverts> jobsList = (List<Adverts>) request.getAttribute(Contract.ADVERTS);
%>
<%
    if (jobsList != null) {
%>

<table>
  <tr><th>Results</th></tr>
    <%for (Adverts advert : jobsList) {%>
    <tr>
      <td><%= advert.getTitle() %></td>
      <td><%= advert.getContent() %></td>
      <td></td>
      <td></td>
    </tr>
    <%}%>
</table>

<%  }%>
    

<!-- search results for workers -->
<%
    List<User> workersList = (List<User>) request.getAttribute(Contract.USERS);
%>
<%
    if (workersList != null) {
%>

<table>
  <tr><th>Results</th></tr>
    <%for (User u : workersList) {%>
    <tr>
        <td><%= u.getName() %></td>
        <td><%= u.getPhone() %></td>
        <td></td>
        <td></td>
    </tr>
    <%}%>
</table>

<%  }%>
    