<%-- 
    Document   : search
    Created on : Sep 21, 2016, 6:10:20 PM
    Author     : Dan, AD
--%>
<%@page import="java.util.*"%>
<%@page import="entities.*"%>
<%@page import="utils.*"%>
<%
    List<Skills> skills = (List<Skills>) request.getAttribute(Contract.SKILLS);
    List<Suburb> suburbs = (List<Suburb>) request.getAttribute(Contract.SUBURBS);
    List<Classification> classifications = (List<Classification>) request.getAttribute(Contract.CLASSIFICATIONS);
    SearchParams search = (SearchParams) request.getAttribute("search");    

    List<Adverts> jobsList = (List<Adverts>) request.getAttribute(Contract.ADVERTS);
    boolean isClosed;

    List<User> workersList = (List<User>) request.getAttribute(Contract.USERS);
%>


<script type="text/javascript">
  function clearSearchForm() {
    var f = document.forms["search"];
    f.elements["keywords"].value = "";
    f.elements["suburb"].value = -1;
    f.elements["skills"].value = "";
  }
</script>

<!--<h1>COMMUNITY SKILLS SEARCH</h1>-->
<form action="search" role="form" name="search">
    <input type="hidden" name="action" value="searchFor">
    <div class="row">
        <div class="col-md-6">
            <div class="form-group">
                <label>Keywords</label>
                <input type="text" name="keywords" placeholder="keywords" pattern="^[A-Za-z0-9_]{1,15}$"
                       value="<%=search.getKeywords()%>" class="form-control"/>
            </div>
            <div class="form-group">
                <label>Location</label>
                <select name="suburb" class="form-control"> 
                    <option value="-1">Anywhere</option>
                    <%for (Suburb sub : suburbs) {%>
                    <option value="<%=sub.getId()%>"<%=search.getSuburbId() == sub.getId() ? " selected" : ""%>><%=sub.getSuburb()%></option>
                    <%}%>
                </select>
            </div>
            <div class="form-group">
                <label>Search for</label>
                <label class="radio-inline">
                    <input type="radio" name="type" value="workers"<%=search.getType() == SearchParams.WORKER ? " checked" : ""%>/> Workers
                </label>
                <label class="radio-inline">
                    <input type="radio" name="type" value="jobs"<%=search.getType() == SearchParams.JOB ? " checked" : ""%>/> Jobs
                </label>
            </div>
            <input type="submit" value="Search" class="btn btn-primary"/>
            <input type="button" value="Clear Form" class="btn btn-secondary" onClick="clearSearchForm();"/>
        </div>
        <div class="col-md-6">
            <div class="form-group">
                <label>Skills</label>
                <select name="skills" multiple="true" class="form-control" size="6"> 
                    <%for (Skills s : skills) {%>
                    <option value="<%=s.getId()%>"<%=search.getSkillsId() == s.getId() ? " selected" : ""%>><%=s.getName()%></option>
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
</form>    

<!-- search results for jobs -->
<jsp:include page="/jobs/_listings.jsp"/>


<!-- search results for workers -->
<div class="container">
  <%

    HashMap<String, List<UserSkills>> userSkillsMap = (HashMap)request.getAttribute("userSkillsMap");  

    if (workersList != null) {
      for (User worker : workersList) {
        String img = worker.getImg();

  %>  
  <div class="row">
    <hr/>
    <div class="col-md-2">
      <a href="">
        <img src="<%=ServletUtils.getUserAvatar(this, request, worker.getImg())%>" 
             class="avatar img-circle" 
             style="padding: 1em;"
             alt="avatar" 
             width="128" 
             onerror="this.onerror=null;this.src='//placehold.it/128';this.className='avatar img-circle';">
      </a>
    </div>
    <div class="col-md-2">
      <h3><a href="user?action=view&id=<%=worker.getId()%>"><%=worker.getName()%></a></h3>        
      <h4><%=worker.getSuburbId().getSuburb()%></h4>
      <p><a class="btn btn-primary" href="user?action=view&id=<%=worker.getId()%>">View Worker</a></p>
      <p><%=StringUtils.shortenString(worker.getIntroduction(), 40)%></p> 
    </div>
    <div class="col-md-8">
      <div class="skillsCell">
    <%
      List<UserSkills> usList = userSkillsMap.get(worker.getName());
      for (UserSkills us: usList) {
        Skills s = us.getSkills();
        %>
        <span class="skillsTag"><%=s.getName()%></span>&nbsp;
        <%
      }
    %>
      </div>

    </div>
  </div>
  <% 
    }
  } 
  %>  
</div>


<%--
<table class="table table-hover">
    <%
        if (workersList != null) {%>
    <tr><th>#</th><th>Name</th><th>Phone#</th><th>Action</th></tr>
    <%
            for (User user : workersList) {
    %>
    <tr><td><%=user.getId()%></td>
        <td><a href='user?action=view&id=<%=user.getId()%>'><%=user.getName()%></a></td>
        <td><%= user.getPhone() %></td>
        <td>
            <a href='messages?action=compose&id=<%=user.getId()%>' class="btn btn-outline-primary">Send Messages</a>
        </td>
    </tr>
    <%}
        }
    %>
</table>
--%>

    