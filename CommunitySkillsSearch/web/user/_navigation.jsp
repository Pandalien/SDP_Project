<%-- 
    Document   : _navigation
    Created on : Oct 4, 2016, 10:21:39 PM
    Author     : andyc
--%>
<%@page import="utils.ServletUtils"%>
<%@page import="utils.Contract"%>
<%@page import="entities.User"%>
<%
    User user = (User) session.getAttribute(Contract.CURRENT_USER);
    String content = (String) request.getAttribute("content");
%>

<div class="col-md-4">
    <div class="panel panel-default">
        <div class="panel-heading">
            <%=user.getName()%>
        </div>
        <div class="panel-body">
            <div class="text-center">
                <img src="<%=ServletUtils.getUserAvatar(this, request, user.getImg())%>" class="avatar img-circle" alt="avatar" width="100" onerror="this.onerror=null;this.src='//placehold.it/100';this.className='avatar img-circle';">
            </div>
            <br/>
            <a href="jobs?action=applications" class="btn btn-default btn-block">
                <span class="glyphicon glyphicon-list-alt"></span> My Applications
            </a>
            <br/>
            <a href="jobs?action=openings" class="btn btn-default btn-block">
                <span class="glyphicon glyphicon-briefcase"></span> My Advertisements
            </a>
            <br/>
            <a href="jobs?action=applicants" class="btn btn-default btn-block">
                <span class="glyphicon glyphicon-check"></span> Received Applications
            </a>
            <br/>
            <a href="user?action=edit" class="btn btn-default btn-block <%=content=="user/edit.jsp"? "active" : ""%>">
                <span class="glyphicon glyphicon-cog"></span> Edit Account
            </a>
            <br/>
            <a href="photo?action=upload" class="btn btn-default btn-block <%=content=="user/photo.jsp"? "active" : ""%>">
                <span class="glyphicon glyphicon-picture"></span> My Photo
            </a>
            <br/>
            <a href="user?action=changepwd" class="btn btn-default btn-block <%=content=="user/changepwd.jsp"? "active" : ""%>">
                <span class="glyphicon glyphicon-lock"></span> Change Password
            </a>
            <br/>
            <a href="user?action=delete" class="btn btn-danger btn-block">
                <span class="glyphicon glyphicon-trash"></span> Delete Account
            </a>
            <br/>
        </div>
        <div class="panel-footer">
            <a href="user?action=logout" class="btn btn-default btn-block"><span class="glyphicon glyphicon-log-out"></span> Log out</a>
        </div>
    </div>
</div>