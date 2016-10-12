<%-- 
    Author     : Andy Chen
--%>

<%@page import="utils.ServletUtils"%>
<%@page import="utils.Contract"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Objects"%>
<%@page import="entities.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    User worker = (User) request.getAttribute(Contract.OTHER_USER);
    
    User loggedInUser = (User) session.getAttribute(Contract.CURRENT_USER);
%>

<!-- Content Row -->
<div class="row">

    <!-- Contact Details Column -->
    <div class="col-md-12">
        <div class="text-center">
            <img src="<%=ServletUtils.getUserAvatar(this, request, worker.getImg())%>" class="avatar img-circle" alt="avatar" width="128" onerror="this.onerror=null;this.src='//placehold.it/128';this.className='avatar img-circle';">
            <h3><%=worker.getName()%></h3>
            <p>
                <%=worker.getSuburbId().getSuburb()%><br>
            </p>
            <p><i class="fa fa-clock-o"></i> 
                <abbr title="This is the rating about <%=worker.getName()%> form previous records.">Rating</abbr>: <%=worker.getRating()%></p>

            <p><i class="fa fa-phone"></i> 
                <abbr title="Phone">Phone</abbr>: <%=worker.getPhone()%></p>
            <p><i class="fa fa-envelope-o"></i> 
                <abbr title="Email">Email</abbr>: <a href="mailto:<%=worker.getEmail()%>"><%=worker.getEmail()%></a>
            </p>
            <p><i class="fa fa-clock-o"></i> 
                <abbr title="Member since">Member since</abbr>: <%=worker.getJoinedDate()%></p>
        </div>
        
        <div class="well">
            <h4>About <%=worker.getName()%></h4>
            <p>
                <%=worker.getIntroduction()%>
            </p>
        </div>
    </div>
            
    <!-- Message Column -->
    <div class="col-md-12">
        <div class="well">
            <h4>Leave a Message</h4>
            <form role="form" action="messages?action=compose" method="post">
                <input type="hidden" name="id" value="<%=worker.getId()%>"/>
                <div class="form-group">
                    <textarea name="message" class="form-control" rows="3"></textarea>
                </div>
                <button type="submit" class="btn btn-primary">Send</button>
            </form>
        </div>
    </div>
</div>
<!-- /.row -->