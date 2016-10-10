<%-- 
    Document   : _listing
    Created on : Oct 9, 2016, 8:48:16 PM
    Author     : andyc
--%>

<%@page import="utils.ServletUtils"%>
<%@page import="utils.StringUtils"%>
<%@page import="entities.User"%>
<%@page import="entities.Adverts"%>
<%@page import="utils.Contract"%>
<%@page import="java.util.List"%>
<%
     List<Adverts> ads = (List<Adverts>) request.getAttribute(Contract.ADVERTS);
     User currentUser = (User) session.getAttribute(Contract.CURRENT_USER);
     boolean isOwner;
     boolean isOpen;
     String photo;
%>
<%
    if (ads != null) {
        for (Adverts a : ads) {
            isOpen = a.getClosed() == null || !a.getClosed();
            isOwner = currentUser != null && a.getUserId().getId() == currentUser.getId();
            photo = ServletUtils.getUserPhoto(this, request, a.getImg());
%>

<div class="row">
    <div class="col-md-4">
        <a href='jobs?action=view&id=<%=a.getId()%>'>>
            <%if (!StringUtils.isEmpty(photo)) {%>
            <img class="img-responsive" src="<%=photo%>" width="400" alt="">
            <%}else{%>
            <img class="img-responsive" src="res/images/jobsearch.jpg" alt="">
            <%}%>
        </a>
    </div>
    <div class="col-md-8">
        <h3><span class="label label-default"><%=isOpen ? "Open" : "Closed"%></span> <%=a.getTitle()%></h3>
        <small>by <a href="user?action=view&id=<%=a.getUserId().getId()%>"><%=a.getUserId().getName()%></a>
        </small>
        <p><%=StringUtils.shortenString(a.getContent(), 40)%></p>
        <a class="btn btn-primary" href='jobs?action=view&id=<%=a.getId()%>'>View Job</a>

        <%if (isOwner) {%>
        <a href='jobs?action=<%=isOpen ? "close" : "open"%>&id=<%=a.getId()%>' class="btn btn-default"><%=isOpen ? "Close" : "Open"%></a>
        <a href='jobs?action=edit&id=<%=a.getId()%>' class="btn btn-default">Edit</a>
        <a href='jobs?action=delete&id=<%=a.getId()%>' class="btn btn-danger">Delete</a>
        <%if (a.getRespondersCollection() != null && !a.getRespondersCollection().isEmpty()) {%>
        <a href='jobs?action=applicants' class="btn btn-default">View Applicants</a>
        <%}%>
        
        <%}%>
    </div>
</div>
<hr>
<%}
    }
%>