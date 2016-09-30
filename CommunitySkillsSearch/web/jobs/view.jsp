<%-- 
    Document   : view
    Created on : Sep 24, 2016, 2:25:09 PM
    Author     : andyc
--%>

<%@page import="entities.Responders"%>
<%@page import="utils.Contract"%>
<%@page import="entities.Adverts"%>
<%
    Adverts ad = (Adverts) request.getAttribute(Contract.VIEW_ADVERT);
    Responders responder = (Responders) request.getAttribute(Contract.ADVERT_RESPONDERS);
    boolean isClosed = ad.getClosed()==null||!ad.getClosed();
    boolean hasApplied = responder!=null;

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
      <dd class="col-sm-9"><%=ad.getUserId().getName()%></dd>

      <dt class="col-sm-3">Description</dt>
      <dd class="col-sm-9"><%=ad.getContent()%></dd>
      
      <dt class="col-sm-3">Expire</dt>
      <dd class="col-sm-9"><%=ad.getExpiryDate()%></dd>
      
      <dt class="col-sm-3">Availability</dt>
      <dd class="col-sm-9"><%=isClosed ? "Closed" : "Open"%></dd>
      
      <dt class="col-sm-3">Application</dt>  
      <dd class="col-sm-9"><a href='jobs?action=<%=hasApplied? "cancel" : "apply"%>&id=<%=ad.getId()%>' class="btn btn-outline-primary"><%=hasApplied? "Cancel" : "Apply"%></a></dd>
  </dl>
</div>

