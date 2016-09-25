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
            Keywords <input type="text" name="Keywords"/>
            <input type="submit" name="Search"/>
            <input type="reset" name="Reset"/>
            <input type="radio" name="type" value="workers"/> For Worker
            <input type="radio" name="type" value="jobs"/> For Jobs
            <br/>
        </form>   
    <div/>
<div/>
<!-- search results -->
<%
    List<Adverts> list = (List<Adverts>) request.getAttribute(Contract.ADVERTS);
%>
<%
    if (list != null) {
%>

<table>
    <tr><th>Results</th>
        <%if(list!=null){%>
            <%for (Adverts advert : list) {%>
        <tr>
            <td><%= advert.getTitle() %></td>
            <td><%= advert.getContent() %></td>
            <td></td>
            <td></td>
        </tr>
            <%}%>
        <%} else {%>
            <%=""%>
        <%}%>
</table>

<%}%>
    
    