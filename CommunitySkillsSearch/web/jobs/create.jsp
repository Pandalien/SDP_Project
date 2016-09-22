<%-- 
    Document   : create
    Created on : Sep 17, 2016, 12:02:45 PM
    Author     : andy chen
--%>
<%@page import="entities.Classification"%>
<%@page import="entities.Suburb"%>
<%@page import="utils.Contract"%>
<%@page import="entities.Skills"%>
<%@page import="java.util.List"%>
<%
    List<Skills> skills = (List<Skills>) request.getAttribute(Contract.SKILLS);
    List<Suburb> suburbs = (List<Suburb>) request.getAttribute(Contract.SUBURBS);
    List<Classification> classifications = (List<Classification>) request.getAttribute(Contract.CLASSIFICATIONS);

%>

<form action="jobs?action=create" method="post">
    <input name="title" type="text" value="0" placeholer="title" required="true"/>
    <br/>
    Classification
    <select name='classification'>
        <%for (Classification cl : classifications) {%>
        <option value='<%=cl.getId()%>'><%=cl.getName()%></option>
        <%}%>
    </select>
    <br/>
    <textarea name="content" cols="40" rows="40" required="true">Content</textarea>
    <br/>
    Select skills required: (Hold down the Ctrl (windows) / Command (Mac) button to select multiple options.)
    <br/>
    <select name='skills' multiple="true">
        <%for (Skills s : skills) {%>
        <option value='<%=s.getId()%>'><%=s.getName()%></option>
        <%}%>
    </select>
    <br/>
    Location
    <select name='suburb'>
        <%for (Suburb sub : suburbs) {%>
        <option value='<%=sub.getId()%>'><%=sub.getSuburb()%></option>
        <%}%>
    </select>
    <br/>
    Expiry<input name="expiry_date" type="date"/>
    <input type="reset"/>
    <input type="submit"/>
</form>