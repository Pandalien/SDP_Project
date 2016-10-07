<%-- 
    Document   : _navigation
    Created on : Oct 4, 2016, 10:21:39 PM
    Author     : andyc
--%>
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
                <img src="UserPhotos/<%=user.getId()%>.jpg" class="avatar img-responsive img-circle" alt="avatar" onerror="this.onerror=null;this.src='//placehold.it/100';this.className='avatar img-circle';">
            </div>
            <br/>
            <a href="jobs?action=applications" class="btn btn-default btn-block">
                <i class="fa fa-comment fa-fw"></i> My Applications
            </a>
            <br/>
            <a href="jobs?action=openings" class="btn btn-default btn-block">
                <i class="fa fa-comment fa-fw"></i> My Advertisements
            </a>
            <br/>
            <a href="jobs?action=applicants" class="btn btn-default btn-block">
                <i class="fa fa-comment fa-fw"></i> Received Applications
            </a>
            <br/>
            <a href="user?action=edit" class="btn btn-default btn-block <%=content=="user/edit.jsp"? "active" : ""%>">
                <i class="fa fa-comment fa-fw"></i> Edit Account
            </a>
            <br/>
            <a href="user?action=upload" class="btn btn-default btn-block <%=content=="user/photo.jsp"? "active" : ""%>">
                <i class="fa fa-comment fa-fw"></i> My Photo
            </a>
            <br/>
            <a href="user?action=changepwd" class="btn btn-default btn-block <%=content=="user/changepwd.jsp"? "active" : ""%>">
                <i class="fa fa-comment fa-fw"></i> Change Password
            </a>
            <br/>
            <a href="user?action=delete" class="btn btn-danger btn-block">
                <i class="fa fa-comment fa-fw"></i> Delete Account
            </a>
            <br/>
        </div>
        <div class="panel-footer">
            <a href="user?action=logout" class="btn btn-default btn-block">Log out</a>
        </div>
    </div>
</div>