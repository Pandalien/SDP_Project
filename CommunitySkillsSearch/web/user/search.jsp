<%-- 
    Document   : search
    Created on : Sep 21, 2016, 6:10:20 PM
    Author     : Dan, AD
--%>
<%@page import="java.util.List"%>
<%@page import="entities.*"%>
<%@page import="utils.*"%>
<%
    List<Skills> skills = (List<Skills>) request.getAttribute(Contract.SKILLS);
    List<Suburb> suburbs = (List<Suburb>) request.getAttribute(Contract.SUBURBS);
    List<Classification> classifications = (List<Classification>) request.getAttribute(Contract.CLASSIFICATIONS);
    SearchParams search = (SearchParams) request.getAttribute("search");    
%>    
<%
    List<Adverts> jobsList = (List<Adverts>) request.getAttribute(Contract.ADVERTS);
    boolean isClosed;
%>
<%
    List<User> workersList = (List<User>) request.getAttribute(Contract.USERS);
%>

<h1>COMMUNITY SKILLS SEARCH</h1>
<form action="search">
    <input type="hidden" name="action" value="searchFor">

    <div class="form-group">
        <label class="col-md-3 control-label">Location</label>
        <div class="col-md-12">
            <div class="ui-select">
                <select name="suburb"> 
                    <%for (Suburb sub : suburbs) {%>
                    <option value="<%=sub.getId()%>"<%=search.getSuburbId() == sub.getId() ? " selected" : ""%>><%=sub.getSuburb()%></option>
                    <%}%>
                </select>
            </div>
        </div>
    </div>
    <%--Category <select name="classification"> 
        <%for (Classification cls : classifications) {%>
        <option value="<%=cls.getId()%>"<%=search.getClassificationId() == cls.getId() ? " selected" : ""%>><%=cls.getName()%></option>
        <%}%>
    </select>--%>
    <div class="form-group">
        <label class="col-md-3 control-label">Skills</label>
        <div class="col-md-12">
            <div class="ui-select">
                <select name="skills" multiple="true"> 
                    <%for (Skills s : skills) {%>
                    <option value="<%=s.getId()%>"<%=search.getSkillsId() == s.getId() ? " selected" : ""%>><%=s.getName()%></option>
                    <%}%>
                </select>
            </div>
        </div>
    </div> 
    <div class="form-group">
        <label class="col-md-3 control-label">Keywords</label>
        <div class="col-md-12">
            <input type="text" name="keywords" placeholder="keywords" pattern="^[A-Za-z0-9_]{1,15}$"
                   value="<%=search.getKeywords()%>"/>
        </div>
    </div>


    <div class="form-group">
        <label class="col-md-3 control-label"></label>
        <div class="col-md-12">
            <input type="submit" value="Search" class="btn btn-primary"/>
            <input type="reset" value="Reset" class="btn btn-default"/>
        </div>
    </div>

    <div class="form-group">
        <label class="col-md-3 control-label"></label>
        <div class="col-md-12">
            <input type="radio" name="type" value="workers"<%=search.getType() == SearchParams.WORKER ? " checked" : ""%>/> For Workers
            <input type="radio" name="type" value="jobs"<%=search.getType() == SearchParams.JOB ? " checked" : ""%>/> For Jobs
        </div>
    </div>

    
    
    <br/>
</form>    
 
<!-- search results for jobs -->
<table class="table table-hover">
    <%
        if (jobsList != null) {%>
    <tr><th>#</th><th>Title</th><th>Status</th><th>Action</th></tr>
    <%
            for (Adverts a : jobsList) {
                isClosed = a.getClosed() == null || !a.getClosed();
    %>
    <tr><td><%=a.getId()%></td>
        <td><a href='jobs?action=view&id=<%=a.getId()%>'><%=a.getTitle()%></a></td>
        <td><%=isClosed ? "Open" : "Closed"%></td>
        <td>
            <a href='jobs?action=apply&id=<%=a.getId()%>' class="btn btn-outline-primary">Apply</a>
        </td>
    </tr>
    <%}
        }
    %>
</table>

<!-- search results for workers -->
<table class="table table-hover">
    <%
        if (workersList != null) {%>
    <tr><th>#</th><th>Name</th><th>Phone#</th><th>Action</th></tr>
    <%
            for (User user : workersList) {
    %>
    <tr><td><%=user.getId()%></td>
        <td><a href='user?action=details&id=<%=user.getId()%>'><%=user.getName()%></a></td>
        <td><%= user.getPhone() %></td>
        <td>
            <a href='user?action=send&id=<%=user.getId()%>' class="btn btn-outline-primary">Send Messages</a>
        </td>
    </tr>
    <%}
        }
    %>
</table>

    