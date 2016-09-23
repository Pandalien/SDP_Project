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
    List<Adverts> adverts = (List<Adverts>) request.getAttribute(Contract.ADVERTS);
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<h1>COMMUNITY SKILLS SEARCH</h1>
<div class="main">
    <div class="form">
        <form action="user?action=search" method="post">
            <select name='suburb'> Location
                <%for (Suburb sub : suburbs) {%>
                <option value='<%=sub.getId()%>'><%=sub.getSuburb()%></option>
                <%}%>
            </select>
            <select name='classification'> Category
                <%for (Classification cls : classifications) {%>
                <option value='<%=cls.getId()%>'><%=cls.getName()%></option>
                <%}%>
            </select>
            <br/>
            <select name='skills' multiple="true"> Skills
                <%for (Skills s : skills) {%>
                <option value='<%=s.getId()%>'><%=s.getName()%></option>
                <%}%>
            </select>
            <br/>
            <input type="text" name="Keywords"/>
            <button type="button" name="Search"/>
            <button type="button" name="Reset"/>
            <radio type="radio" name="For Worker"/> For Worker
            <radio type="radio" name="For Jobs"/> For Jobs
            <br/>
            <!-- search results -->
            <%
                if (adverts != null) {
            %>
            <table>
                <tr><th>Title</th><th>Content</th><th>?</th><th>?</th>
                    <%for (Adverts advert : adverts) {%>
                <tr>
                    <td><%= advert.getTitle() %></td>
                    <td><%= advert.getContent() %></td>
                    <td></td>
                    <td></td>
                </tr>
                    <%}%>
            </table>

            <%}%>
        <form/>    
    <div/>
<div/>

    
    