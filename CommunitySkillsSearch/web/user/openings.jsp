<%-- 
    Document   : openings
    Created on : Sep 24, 2016, 1:33:43 PM
    Author     : andyc
--%>

<%@page import="utils.Contract"%>
<%@page import="entities.Adverts"%>
<%@page import="java.util.List"%>
<%
     List<Adverts> ads = (List<Adverts>) request.getAttribute(Contract.USER_ADVERTS);
%>


<h1>My current advertisement</h1>
<%
    for(Adverts a : ads){%>
    <a href='jobs?action=details&id=<%=a.getId()%>'><%=a.getTitle()%></a><br/>
    <%}
%>
