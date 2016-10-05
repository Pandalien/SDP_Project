<%-- 
    Document   : view
    Created on : Sep 24, 2016, 2:25:09 PM
    Author     : andyc
--%>

<%@page import="entities.User"%>
<%@page import="java.util.Collection"%>
<%@page import="entities.Responders"%>
<%@page import="utils.Contract"%>
<%@page import="entities.Adverts"%>
<%
    Adverts ad = (Adverts) request.getAttribute(Contract.VIEW_ADVERT);
    Responders responder = (Responders) request.getAttribute(Contract.ADVERT_RESPONDERS);
    User currentUser = (User) request.getAttribute(Contract.CURRENT_USER);
    
    boolean isOpen = ad.getClosed()==null||!ad.getClosed();
    boolean hasApplied = responder!=null;
    
    boolean isOwner = currentUser != null && ad.getUserId().getId() == currentUser.getId();
%>

<div class="container-fluid">
  <!-- A fluid container that uses the full witdh -->
  <div class="row">
      <h2><%=ad.getTitle()%><span class="tag tag-pill tag-defaultl"><%=ad.getClosed()==null||ad.getClosed()? "Open" : "Closed"%></span></h2>
  </div>
  <dl class="dl-horizontal">
      <dt class="col-sm-3">Classification</dt>
      <dd class="col-sm-9"><%=ad.getClassificationId().getName()%></dd>
      
      <dt class="col-sm-3">Advertiser</dt>
      <dd class="col-sm-9">
          <%=ad.getUserId().getName()%>
          <%if (!isOwner) {%>
          <a href="messages?action=compose&id=<%=ad.getUserId().getId()%>"><i class="material-icons">email</i></a>
            <%}%>
      </dd>
      <dt class="col-sm-3">Description</dt>
      <dd class="col-sm-9"><%=ad.getContent()%></dd>
      
      <dt class="col-sm-3">Expire</dt>
      <dd class="col-sm-9"><%=ad.getExpiryDate()%></dd>
      
      <dt class="col-sm-3">Availability</dt>
      <dd class="col-sm-9"><%=isOpen ? "Open" : "Closed"%></dd>
      
      <%if(!isOwner){%>
      <dt class="col-sm-3">Application</dt>  
      <dd class="col-sm-9">
          <%if(isOpen){%>
          <a href='jobs?action=<%=hasApplied ? "cancel" : "apply"%>&id=<%=ad.getId()%>' class="btn btn-outline-primary"><%=hasApplied ? "Cancel" : "Apply"%></a>
          <%}%>
      </dd>
      <%}else{%>
      <dt class="col-sm-3">Change</dt>
      <dd class="col-sm-9">
          <a href='jobs?action=<%=isOpen ? "Open" : "Closed"%>&id=<%=ad.getId()%>' class="btn btn-outline-primary"><%=isOpen ? "Close" : "Open"%></a>
          <a href='jobs?action=edit&id=<%=ad.getId()%>' class="btn btn-outline-primary">Edit</a>
          <a href='jobs?action=delete&id=<%=ad.getId()%>' class="btn btn-outline-danger">Delete</a>
      </dd>
      <%}%>
      <dt class="col-sm-3">Candidates</dt>
      <dd class="col-sm-9">
          <%
              Collection<Responders> responders = ad.getRespondersCollection();
              if (responders != null && !responders.isEmpty()) {
          %>
      <div>
          <%for (Responders r : responders) {%>
          <div class="card">
              <div class="card-block">
                  <a href="user?action=view&id=<%=r.getUser().getId()%>" class="card-link"><%=r.getUser().getName()%></a>
              </div>
          </div>
          <%}%>
      </div>
  <%}%>

      </dd>
  </dl>
</div>

