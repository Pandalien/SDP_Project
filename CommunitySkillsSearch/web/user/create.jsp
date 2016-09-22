<%-- 
    Document   : create
    Created on : Apr 17, 2016, 1:36:29 PM
    Author     : Andy Chen
--%>

<%@page import="utils.Contract"%>
<%@page import="java.util.List"%>
<%@page import="sb.SuburbFacade"%>
<%@page import="entities.Suburb"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Suburb> suburbs = (List<Suburb>) request.getAttribute(Contract.SUBURBS);
%>

<h1>Join us!</h1>
<form action="user?action=create" method="post">

    <table>
        <tr><td>User Name</td><td><input type="text" name="username" pattern=".{4,}" title="Four or more characters" required/></td></tr>
        <tr><td>Password</td><td><input type="password" name="password"  pattern=".{4,}" title="Four or more characters" required/></td></tr>
        <tr><td>Email</td><td><input type="text" name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" required/></td></tr>
        <tr><td>Gender</td><td><input type="text" name="gender" pattern="[01]{1}" title="0 for female, 1 for male" required/></td></tr>
        <tr><td>Suburb</td><td>
                <select name='suburb'>
                    <%for(Suburb s : suburbs){%>
                    <option value='<%=s.getId()%>'><%=s.getSuburb()%></option>
                    <%}%>
                </select>
        </td></tr>
    </table>
    <input type="submit" value="Create User"/>
</form>
