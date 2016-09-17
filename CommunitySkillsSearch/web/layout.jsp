<%-- 
    Document   : layout
    Created on : Apr 19, 2016, 6:42:13 PM
    Author     : Administrator
--%>
<%@page import="util.Contract"%>
<%@page import="entities.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>${title}</title>
        <link rel="stylesheet" href="res/css/site.css"/>
        <link rel="stylesheet" href="css/login.css" type="text/css"/>
        <script type="text/javascript" src="js/jquery-3.1.0.js"></script>
        <script src="js/login.js"></script>
    </head>
    <body>
        <header>
            <ul class="navbar">
                <li><a href="home"><img src="res/images/ic_home_white_24px.svg" alt="Home"/>Home</a></li>
                <li><a href="listing?action=browse"><img src="res/images/ic_list_white_24px.svg" alt="Browse"/>Browse</a></li>
                <li><a href="home?action=help"><img src="res/images/ic_help_outline_white_24px.svg" alt="Help"/>Help</a></li>
                <%
                    User user = (User) session.getAttribute(Contract.CURRENT_USER);
                    if (user == null) {%>
                <li class="nb-right"><a href="user?action=login"><img src="res/images/ic_perm_identity_white_24px.svg" alt="Log in"/>Log in</a></li>
                <%} else {%>
                <li class="nb-right"><a href="user?action=logout"><img src="res/images/ic_perm_identity_white_24px.svg" alt="Log out"/>Log out</a></li>
                <li class="nb-right"><a href="user?action=details&member=<%= user.getId()%>"><img src="res/images/ic_face_white_24px.svg" alt="Welcome"/>Welcome, <%= user.getName()%></a></li>
                <%}%>
            </ul>
        </header>
        <div id="body">
            <jsp:include page="${content}"/>
        </div>
        <footer>
            <small>Copy right 2016.</small>
        </footer>
    </body>
</html>
