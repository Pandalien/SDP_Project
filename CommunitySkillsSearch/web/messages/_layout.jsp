<%-- 
    Document   : _layout
    Created on : Oct 5, 2016, 1:50:29 PM
    Author     : andyc
--%>

<%@page import="entities.Messages"%>
<%@page import="utils.Contract"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Messages> messages = (List<Messages>) request.getAttribute(Contract.MESSAGES_RECEIVED);
    Messages msg = (Messages) request.getAttribute(Contract.MESSAGES_SELECTED);
    String act = (String) request.getAttribute("message_option");
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
            <li <%=act.equalsIgnoreCase("viewNew")? "class='active'" : ""%>><a href="messages?action=viewNew">Inbox</a></li> 
            <li <%=act.equalsIgnoreCase("viewSent")? "class='active'" : ""%>><a href="messages?action=viewSent">Sent</a></li>
            <li <%=act.equalsIgnoreCase("viewRead")? "class='active'" : ""%>><a href="messages?action=viewRead">Archive</a></li>
        </ul>
    </div>
</div>
<!-- /.row -->
<!-- Content Row -->
<div class="row">
    <!-- Sidebar Column -->
    <div class="col-md-3">
        <div class="list-group">
            <%if (messages != null) {
                    for (Messages m : messages) {
            %>
            <a href="messages?action=<%=act%>&id=<%=m.getId()%>" class="list-group-item"><%=m.getContent()%></a>
            <%}
            }%>
        </div>
    </div>
    <!-- Content Column -->
    <div class="col-md-9">
        <h2><%=msg != null ? msg.getSenderId().getName() : "Message"%></h2>
        <p><%=msg != null ? msg.getContent() : "No message selected."%></p>
    </div>
</div>
<!-- /.row -->

