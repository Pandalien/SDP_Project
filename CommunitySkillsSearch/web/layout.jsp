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
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="shortcut icon" href="res/images/favicon.ico" type="image/x-icon"/>
        <link rel="icon" href="res/images/favicon.ico" type="image/x-icon"/>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons"/>
        <!-- Bootstrap Core CSS -->
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"/>
        <link rel="stylesheet" href="css/bootstrap-tagsinput.css" type="text/css"/>
        <link rel="stylesheet" href="css/bootstrap-toggle.min.css" type="text/css"/>
        <!-- Custom CSS -->
        <link rel="stylesheet" href="css/sb-admin-2.css" type="text/css"/>
        <link rel="stylesheet" href="css/site.css" type="text/css"/>
        <link rel="stylesheet" href="css/login.css" type="text/css"/>
        <!-- jQuery -->
        <script type="text/javascript" src="js/jquery-3.1.0.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.8/angular.min.js"></script>
        <script type="text/javascript" src="js/login.js"></script>
        <!-- Bootstrap Core JavaScript -->
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <script type="text/javascript" src="js/bootstrap-toggle.min.js"></script> 
        <script type="text/javascript" src="js/bootstrap-tagsinput.js"></script>
        <script type="text/javascript" src="js/bootstrap-tagsinput-angular.js"></script>
        <title>Community Skills Search</title>
    </head>
    <body>
        <!-- Navigation -->
        <jsp:include page="/shared/_nav.jsp"/>

        <div class="container" id="main_container">
            <!-- Page Heading/Breadcrumbs -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Community Skills Search
                        <small></small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="home">Home</a>
                        </li>
                        <li class="active">${current_path}</li>
                    </ol>
                </div>
            </div>
            <!-- /.row -->

            <!-- Content Row -->
            <div class="row">
                <div class="col-lg-12">
                    <jsp:include page="/shared/message.jsp"/>
                    <jsp:include page="${content}"/>
                </div>
            </div>
            <!-- /.row -->
            
            <hr>
            <!-- Footer -->
            <footer>
                <div class="row">
                    <div class="col-lg-12">
                        <p>Copyright &copy; Community Skills Search 2016</p>
                    </div>
                </div>
            </footer>
    </div>

    </body>
</html>
