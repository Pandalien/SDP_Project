<%-- 
    Document   : applications
    Created on : Sep 30, 2016, 4:56:55 PM
    Author     : andyc
--%>

<%@page import="utils.Contract"%>
<%@page import="entities.Adverts"%>
<%@page import="java.util.List"%>
<%
    List<Adverts> ads = (List<Adverts>) request.getAttribute(Contract.ADVERTS);
    boolean isClosed;
%>


<h1>My current applications</h1>
<table class="table table-hover">
    <tr><th>#</th><th>Title</th><th>Status</th><th>Change</th></tr>

    <%
        if (ads != null) {
            for (Adverts a : ads) {
                isClosed = a.getClosed() == null || !a.getClosed();
    %>
    <tr><td><%=a.getId()%></td>
        <td><a href='jobs?action=view&id=<%=a.getId()%>'><%=a.getTitle()%></a></td>
        <td><%=isClosed ? "Open" : "Closed"%></td>
        <td>
            <a href='jobs?action=cancel&id=<%=a.getId()%>' class="btn btn-outline-primary">Cancel</a>
        </td>
    </tr>
    <%}
        }
    %>
</table>
