<%-- 
    Document   : changepwd
    Created on : Sep 30, 2016, 3:28:51 PM
    Author     : AD
--%>

<%@page import="utils.Contract"%>
<%@page import="entities.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%    
  User user = (User) session.getAttribute(Contract.CURRENT_USER);
  
  if (user != null) {
%>
<h2>Change Password for <%=user.getName()%>:</h2>
<br>
  
<form action="user?action=changepwd" method="post">
  <div class="form-group row">
    <label class="col-md-2 col-form-label">Current Password:</label> 
    <div class="col-md-8">
      <input name="pass0" type="password" size="20" class="form-control">
    </div>
  </div>
  <div class="form-group row">  
    <label class="col-md-2 col-form-label">New Password:</label> 
    <div class="col-md-8">
      <input name="pass1" type="password" class="form-control">
    </div>
  </div>
  <div class="form-group row">    
    <label class="col-md-2 col-form-label">Verify New Password:</label> 
    <div class="col-md-8">
      <input name="pass2" type="password" class="form-control">
    </div>
  </div>
  <div class="form-group row">  
    <div class="col-md-8">
      <input type="submit" value="Change" class="btn btn-primary">
      <input type="button" value="Cancel" class="btn btn-secondary">
    </div>
  </div>
</form>
 

<% } %>