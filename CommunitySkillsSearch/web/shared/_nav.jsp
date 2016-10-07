<%-- 
    Document   : _nav
    Created on : Oct 5, 2016, 8:23:44 PM
    Author     : andyc
--%>

<%@page import="entities.Messages"%>
<%@page import="java.util.List"%>
<%@page import="utils.Contract"%>
<%@page import="entities.User"%>
<%
    User user = (User) session.getAttribute(Contract.CURRENT_USER);
    List<Messages> messages = (List<Messages>) request.getAttribute(Contract.MESSAGES_RECEIVED);
%>

<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <span class="navbar-brand"><a href="home"><i class="material-icons">home</i></a></span>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-left">
                <%if (user != null) {
                %>
                <li><a href="jobs?action=create"><i class="material-icons">note_add</i></a></li>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                        <i class="material-icons">assignment</i>
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="active"><a href="jobs?action=applications">My Applications</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="jobs?action=openings">My Advertisements</a></li>
                        <li><a href="jobs?action=applicants">Received Applications</a></li>
                    </ul>
                </li>

                <%}%>
            </ul>
            <form action="search" class="navbar-form form-inline navbar-left">
                <div class="input-group input-group-sm">
                    <input type="hidden" name="action" value="searchFor">
                    <input name="keywords" id="searchbar" type="text" class="form-control" placeholder="Search for..." >
                    <span class="input-group-btn">
                        <button type="submit" class="btn btn-default" type="button">Go!</button>
                    </span>
                </div><!-- /input-group -->
            </form>
            
            <ul class="nav navbar-nav navbar-right">
                
                <%
                    if (user == null) {
                %>
                <li><a href="user?action=login"><i class="material-icons">perm_identity</i>Log in</a></li>
                        <%} else {%>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                        <i class="material-icons valign">account_box</i><b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="user?action=edit">Edit Account</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="user?action=changepwd">Change Password</a></li>
                        <li><a href="user?action=delete">Delete Account</a></li>
                    </ul>
                </li>
                <li><a href="messages?action=viewNew"><i class="material-icons">email</i><span class="label"><%=messages!=null? messages.size() : ""%></span></a></li>
                <li><a href="user?action=logout"><i class="material-icons">exit_to_app</i></a></li>
                <%}%>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>