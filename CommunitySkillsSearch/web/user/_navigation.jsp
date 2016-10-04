<%-- 
    Document   : _navigation
    Created on : Oct 4, 2016, 10:21:39 PM
    Author     : andyc
--%>
<%@page import="utils.Contract"%>
<%@page import="entities.User"%>
<%
    User user = (User) session.getAttribute(Contract.CURRENT_USER);
%>

<!-- left column -->
<div class="col-md-3">
    <div class="text-center">
        <img src="UserPhotos/<%=user.getId()%>.jpg" class="avatar img-responsive img-circle" alt="avatar" onerror="this.onerror=null;this.src='//placehold.it/100';this.className='avatar img-circle';">
        <form action="user?action=upload" method="post" enctype="multipart/form-data">
            <h6>Upload a photo...</h6>
            <input type="file" name="file" class="form-control"/>
            <input type="submit" value="Upload"/>
        </form>
    </div>
    <div>
        <ul class="dropdown-menu">
            <li><a href="jobs?action=applications">My Applications</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="jobs?action=openings">My Advertisements</a></li>
            <li><a href="jobs?action=applicants">Received Applications</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="user?action=edit">Edit Account</a></li>
            <li><a href="#">Change Password</a></li>
            <li><a href="#">Delete Account</a></li>
        </ul>
    </div>
</div>