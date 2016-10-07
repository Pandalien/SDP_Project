<%-- 
    Document   : compose
    Created on : Oct 4, 2016, 10:13:45 PM
    Author     : andyc
--%>
<%@page import="utils.Contract"%>
<%@page import="entities.User"%>
<%
    User otherUser = (User) request.getAttribute(Contract.OTHER_USER);
%>
<hr>
<div class="row">
    <!-- edit form column -->
    <div class="well">
        <h4>Send a Message to <%=otherUser.getName()%></h4>
        <form role="form" action="messages?action=compose" method="post">
            <input type="hidden" name="id" value="<%=otherUser.getId()%>"/>
            <div class="form-group">
                <textarea name="message" class="form-control" rows="3"></textarea>
            </div>
            <button type="submit" class="btn btn-primary">Send</button>
        </form>
    </div>
</div>