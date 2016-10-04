<%-- 
    Document   : compose
    Created on : Oct 4, 2016, 10:13:45 PM
    Author     : andyc
--%>
<%@page import="utils.Contract"%>
<%@page import="entities.User"%>
<%
    User receiver = (User) request.getAttribute(Contract.OTHER_USER);
%>
<h2>Send Messages</h2>
<hr>
<div class="row">
    <!-- edit form column -->
    <div class="col-md-9">
        <form class="form-horizontal" role="form" action="message?action=compose" method="post">
            <input type="hidden" name="id" value="<%=receiver.getId()%>"/>
            <div class="form-group">
                <label class="col-md-3 control-label">Receiver</label>
                <div class="col-md-8">
                    <input name="name" class="form-control" type="text" value='<%=receiver.getName()%>' readonly>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Message</label>
                <div class="col-md-8">
                    <textarea name="message" class="form-control" rows="10" required="true"></textarea>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label"></label>
                <div class="col-md-8">
                    <input type="submit" class="btn btn-primary" value="Send">
                    <span></span>
                    <input type="reset" class="btn btn-default" value="Reset">
                </div>
            </div>
        </form>
    </div>
</div>