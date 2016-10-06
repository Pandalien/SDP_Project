<%-- 
    Document   : layout
    Created on : Aug 19, 2016, 6:42:13 PM
    Author     : AndyChen
--%>
<%@page import="utils.Contract"%>
<%@page import="entities.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%User user = (User) session.getAttribute(Contract.CURRENT_USER);%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="shortcut icon" href="res/images/favicon.ico" type="image/x-icon"/>
        <link rel="icon" href="res/images/favicon.ico" type="image/x-icon"/>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons"/>
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"/>
        <link rel="stylesheet" href="css/navbar-fixed-top.css" type="text/css"/>
        <link rel="stylesheet" href="css/login.css" type="text/css"/>
        <link rel="stylesheet" href="css/bootstrap-tagsinput.css" type="text/css"/>
        <link rel="stylesheet" href="css/bootstrap-toggle.min.css" type="text/css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script type="text/javascript" src="js/jquery-3.1.0.js"></script>
        <title>Community Skills Search</title>
    </head>
    <body>
        <nav class="navbar navbar-default navbar-fixed-top">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <%
                        if (user != null) {
                    %>
                    <div class="navbar-brand">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" id="menu"> MENU <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="jobs?action=applications">My Applications</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="jobs?action=openings">My Advertisements</a></li>
                            <li><a href="jobs?action=applicants">Received Applications</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="user?action=edit">Edit Account</a></li>
                            <li><a href="user?action=changepwd">Change Password</a></li>
                            <li><a href="#">Delete Account</a></li>
                        </ul>
                    </div>
                <%}%>
                
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <%
                        if (user != null) {
                    %>
                    <div class="navbar-brand">
                        <ul class="nav navbar-nav">
                            <li><a href="home"><i class="material-icons">home</i></a></li>
                            <li><a href="jobs?action=create"><i class="material-icons">note_add</i></a></li>
                        </ul>
                    </div>
                    <%}%>
                    
                    <form action="search" class="navbar-form navbar-left">
                        <input type="hidden" name="action" value="searchFor">
                        <div class="form-group">
                            <input name="keywords" type="text" class="form-control" placeholder="Search" id="searchbar">
                        </div>
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                    
                    <ul class="nav navbar-nav navbar-right">
                        <%
                            if (user == null) {
                        %>
                        <li class="nb-right"><a href="user?action=login"><img src="res/images/ic_perm_identity_white_24px.svg" alt="Log in"/>Log in</a></li>
                            <%} else {%>
                        <li><a href="user?action=edit"><i class="material-icons">account_box</i><%= user.getName()%></a></li>
                        <li><a href="#"><i class="material-icons">assignment</i></a></li>
                        <li><a href="messages?action=viewNew"><i class="material-icons">email</i></a></li>
                        <li><a href="user?action=logout"><i class="material-icons">exit_to_app</i></a></li>
                        <%}%>
                    </ul>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>

        <div class="container-fluid" id="main_conainer">
            <div class="container">
                <jsp:include page="shared\message.jsp"/>
                <jsp:include page="${content}"/>
            </div>
            <hr>
        </div>
            <script type="text/javascript" src="js/bootstrap.min.js"></script>
            <script type="text/javascript" src="js/bootstrap-tagsinput.js"></script>
            <script type="text/javascript" src="js/bootstrap-tagsinput-angular.js"></script>
            <script type="text/javascript" src="js/bootstrap-toggle.min.js"></script>
    </body>
</html>
