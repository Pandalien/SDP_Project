<%-- 
    Document   : index
    Created on : Oct 4, 2016, 10:00:14 PM
    Author     : andyc
--%>

<%@page import="entities.Messages"%>
<%@page import="utils.Contract"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Messages msg = (Messages) request.getAttribute(Contract.MESSAGES_SELECTED);
%>


<!-- Page Heading/Breadcrumbs -->
<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Messages
            <small>Inbox</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="home">Home</a>
            </li>
            <li class="active">Messages</li>
        </ol>
        
        <ul class="nav nav-pills">
            <li><a href="messages">Inbox</a></li> 
            <li class="active"><a href="messages?action=sent">Sent</a></li>
            <li><a href="messages?action=read">Archive</a></li>
        </ul>
    </div>
</div>
<!-- /.row -->
<jsp:include page="_sidebar.jsp"/>
<!-- Content Row -->
<div class="row">
    <!-- Sidebar Column -->
    
    <!-- Content Column -->
    <div class="col-md-9">
        <h2><%=msg!=null? msg.getSenderId().getName() : "Message"%></h2>
        <p><%=msg!=null? msg.getContent() : "No message selected."%></p>
    </div>
</div>
<!-- /.row -->

