<%-- 
    Document   : _layout
    Created on : Oct 5, 2016, 1:50:29 PM
    Author     : andyc
--%>

<%@page import="entities.User"%>
<%@page import="entities.Messages"%>
<%@page import="utils.Contract"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Messages> messages = (List<Messages>) request.getAttribute(Contract.MESSAGES_RECEIVED);
    Messages msg = (Messages) request.getAttribute(Contract.MESSAGES_SELECTED);
    User otherUser = (User) request.getAttribute(Contract.OTHER_USER);
    String act = (String) request.getAttribute("message_option");
    boolean vViewSent = act.equalsIgnoreCase("viewSent");
    String displayName;
    String sentTime;
%>

<!-- Content Row -->
<div class="row">
    <!-- Sidebar Column -->
    <div class="col-md-4">
        <div class="chat-panel panel panel-default">
            <div class="panel-heading">
                <i class="fa fa-comments fa-fw"></i> Messages
                <div class="btn-group pull-right">
                    <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-chevron-down"></i>
                    </button>
                    <ul class="dropdown-menu slidedown">
                        <li>
                            <a href="messages?action=viewNew">
                                <i class="fa fa-refresh fa-fw"></i> Refresh
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <ul class="nav nav-pills">
                    <li <%=act.equalsIgnoreCase("viewNew") ? "class='active'" : ""%>><a href="messages?action=viewNew">Inbox</a></li> 
                    <li <%=act.equalsIgnoreCase("viewSent") ? "class='active'" : ""%>><a href="messages?action=viewSent">Sent</a></li>
                    <li <%=act.equalsIgnoreCase("viewRead") ? "class='active'" : ""%>><a href="messages?action=viewRead">Archive</a></li>
                </ul>
                <br>
                <ul class="chat">
                    <%if (messages != null) {
                            for (Messages m : messages) {
                                displayName = vViewSent? m.getReceiverId().getName() : m.getSenderId().getName();
                                sentTime = m.getSentTime().toString();
                    %>
                    <li class="left clearfix">
                        <span class="chat-img pull-left">
                            <img src="http://placehold.it/50/55C1E7/fff" alt="User Avatar" class="img-circle">
                        </span>
                        <div class="chat-body clearfix">
                            <div class="header">
                                <a href="messages?action=<%=act%>&id=<%=m.getId()%>">
                                    <strong class="primary-font"><%=displayName%></strong>
                                </a>
                                <small class="pull-right text-muted">
                                    <i class="fa fa-clock-o fa-fw"></i> <%=sentTime%>
                                </small>
                            </div>
                            <p>
                                <small></small>
                            </p>
                        </div>
                    </li>
                    <%}
                        }%>
                </ul>
            </div>
            <!-- /.panel-body -->
            <div class="panel-footer">
                <div class="input-group">
                    <span class="input-group-btn">
                        <a href="messages?action=viewNew" class="btn btn-default">Refresh</a>
                    </span>
                </div>
            </div>
            <!-- /.panel-footer -->
        </div>
    </div>
    <!-- Content Column -->
    <div class="col-md-8">
        <h2><%=msg != null ? msg.getSenderId().getName() : "Message"%>
            <small><%=msg != null ? "Received: " +msg.getSentTime() : ""%></small>
        </h2>
        <p><%=msg != null ? msg.getContent() : "No message selected."%></p>
        
        <%if(otherUser != null && !act.equalsIgnoreCase("viewSent")){%>
        <!-- Comments Form -->
        <div class="well">
            <h4>Leave a Message to <%=otherUser.getName()%></h4>
            <form role="form" action="messages?action=compose" method="post">
                <input type="hidden" name="id" value="<%=otherUser.getId()%>"/>
                <div class="form-group">
                    <textarea name="message" class="form-control" rows="3"></textarea>
                </div>
                <button type="submit" class="btn btn-primary">Send</button>
            </form>
        </div>
        <%}%>
    </div>
</div>
<!-- /.row -->

