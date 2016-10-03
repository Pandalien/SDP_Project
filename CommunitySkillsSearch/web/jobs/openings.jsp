<%-- 
    Document   : openings
    Created on : Sep 24, 2016, 1:33:43 PM
    Author     : andyc
--%>

<%@page import="java.util.Collection"%>
<%@page import="entities.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entities.Responders"%>
<%@page import="utils.Contract"%>
<%@page import="entities.Adverts"%>
<%@page import="java.util.List"%>
<%
     List<Adverts> ads = (List<Adverts>) request.getAttribute(Contract.USER_ADVERTS);
     boolean isOpen;
%>


<h1>My current advertisement</h1>
<table class="table table-hover">
    <tr><th>#</th><th>Title</th><th>Status</th><th>Change</th></tr>
    
<%
    if(ads !=null){
        for(Adverts a : ads){
            isOpen = a.getClosed()==null||!a.getClosed();
%>
    <tr><td><%=a.getId()%></td>
        <td><a href='jobs?action=view&id=<%=a.getId()%>'><%=a.getTitle()%></a></td>
        <td><%=isOpen? "Open" : "Closed"%></td>
        <td>
            <a href='jobs?action=<%=isOpen? "close" : "open"%>&id=<%=a.getId()%>' class="btn btn-outline-primary"><%=isOpen? "Close" : "Open"%></a>
            <a href='jobs?action=edit&id=<%=a.getId()%>' class="btn btn-outline-primary">Edit</a>
            <a href='jobs?action=delete&id=<%=a.getId()%>' class="btn btn-outline-danger">Delete</a>
            <%if(a.getRespondersCollection()!=null && !a.getRespondersCollection().isEmpty()){%>
            <a href='jobs?action=applicants'>View Applicants</a>
            <%}%>
        </td>
    </tr>
        <%}
    }
    %>
</table>
