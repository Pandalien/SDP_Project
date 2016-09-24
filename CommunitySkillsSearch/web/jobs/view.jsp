<%-- 
    Document   : view
    Created on : Sep 24, 2016, 2:25:09 PM
    Author     : andyc
--%>

<%@page import="utils.Contract"%>
<%@page import="entities.Adverts"%>
<%
    Adverts ad = (Adverts) request.getAttribute(Contract.VIEW_ADVERT);
%>

<div class="container-fluid">
  <!-- A fluid container that uses the full witdh -->
  <div class="row">
      <h2><%=ad.getTitle()%><span class="tag tag-pill tag-defaultl"><%=ad.getClosed()==null||ad.getClosed()? "Open" : "Closed"%></span></h2>
  </div>
  <div class="container">
      <div class="row">
          <div class="col-md-9">
              <%=ad.getContent()%>
          </div>
          <div class="col-md-3">
              <%=ad.getExpiryDate()%>
          </div>
      </div>
  </div>

</div>

