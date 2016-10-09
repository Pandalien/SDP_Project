<%-- 
    Document   : index
    Created on : Apr 17, 2016, 1:48:35 PM
    Author     : Andy Chen
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<h1>Welcome.</h1>

<h2><a href="user?action=developerLogin">Quick login as admin</a></h2>
<h2><a href="user?action=mattLogin">Quick login as Matt</a></h2>
<h2><a href="user?action=andyLogin">Quick login as andy</a></h2>
Who's here? <a href="user?action=browse">Browse Users</a>
<br/>
<a href="search?action=searchFor&suburb=-1&keywords=&type=workers">Search Users</a>
<br/>
<a href="jobs?action=create">Create a new job</a>

<p>Jobs that match your skills</p>
<jsp:include page="/jobs/_listings.jsp"/>