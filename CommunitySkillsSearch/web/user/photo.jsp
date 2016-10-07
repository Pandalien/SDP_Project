<%-- 
    Document   : photo
    Created on : Oct 8, 2016, 8:39:54 AM
    Author     : andyc
--%>
<%@page import="utils.Contract"%>
<%@page import="entities.User"%>
<%
    User user = (User) session.getAttribute(Contract.CURRENT_USER);
%>

<div class="row">
    <!-- left column -->
    <jsp:include page="_navigation.jsp"/>

    <!-- edit photo column -->
    <div class="col-md-8 personal-info">
        <h3>My Photo</h3>
        <br>
        <form class="form-horizontal" role="form" action="user?action=upload" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label class="col-md-3 control-label">Photo</label>
                <div class="col-md-8">
                    <input class="form-control" type="file" name="file"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label"></label>
                <div class="col-md-8">
                    <input type="submit" class="btn btn-primary" value="Upload">
                    <span></span>
                    <a class="btn btn-danger" href="user?action=deleteAvatar&id=<%=user.getId()%>">Delete</a>
                </div>
            </div>
        </form>
    </div>
</div>



