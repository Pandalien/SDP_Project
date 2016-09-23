<%-- 
    Document   : edit
    Created on : Apr 17, 2016, 1:53:46 PM
    Author     : Andy Chen
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@page import="entities.UserSkills"%>
<%@page import="entities.Suburb"%>
<%@page import="entities.Skills"%>
<%@page import="java.util.List"%>
<%@page import="utils.Contract"%>
<%@page import="entities.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Suburb> suburbs = (List<Suburb>) request.getAttribute(Contract.SUBURBS);
    List<Skills> skills = (List<Skills>) request.getAttribute(Contract.SKILLS);
    User user = (User) session.getAttribute(Contract.CURRENT_USER);
%>

<h1>Edit Account</h1>
<hr>
<div class="row">
    <!-- left column -->
    <div class="col-md-3">
        <div class="text-center">
            <img src="//placehold.it/100" class="avatar img-circle" alt="avatar">
            <h6>Upload a photo...</h6>
            <input type="file" class="form-control">
        </div>
    </div>
    <!-- edit form column -->
    <div class="col-md-9 personal-info">
        <h3>Personal info</h3>
        <br>
        <form class="form-horizontal" role="form" action="user?action=edit" method="post">
            <div class="form-group">
                <label class="col-lg-3 control-label">Name:</label>
                <div class="col-lg-8">
                    <input name="name" class="form-control" type="text" placeholder="" value='<%=user.getName()%>'>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">Email:</label>
                <div class="col-lg-8">
                    <input name="email" class="form-control" type="email" placeholder="" value='<%=user.getEmail()%>'>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">Suburb:</label>
                <div class="col-lg-8">
                    <div class="ui-select">
                        <select name="suburb_id" id="user_time_zone" class="form-control" value=''>
                            <%for (Suburb s : suburbs) {%>
                            <option value='<%=s.getId()%>' <%=s.getId()==user.getSuburbId().getId() ? "selected" : ""%> ><%=s.getSuburb()%></option>
                            <%}%>
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Skills:</label>
                <div class="col-md-8">
                    <div class="input-group">
                        <%
                            Collection<UserSkills> userSkills = user.getUserSkillsCollection();
                            Iterator<UserSkills> skillsIterator = userSkills.iterator();
                            String skillsList = "";
                            while (skillsIterator.hasNext()) {
                                Skills skill = skillsIterator.next().getSkills();
                                skillsList += skill.getName();
                                if (skillsIterator.hasNext())
                                    skillsList += ",";
                            }
                        %>
                        <input name="current_skill" type="text" data-role="tagsinput" value='<%=skillsList%>'/>
                        <span class="input-group-btn">
                          <button class="btn btn-danger" type="button">-</button>
                        </span>
                        <select name="skills" class="form-control" value=''>
                            <%for (Skills s : skills) {%>
                            <option value='<%=s.getId()%>'><%=s.getName()%></option>
                            <%}%>
                        </select>
                        <span class="input-group-btn">
                          <button class="btn btn-success" type="button">+</button>
                        </span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Join Date:</label>
                <div class="col-md-8">
                    <input name="joined_date" class="form-control" type="date" value='<%=user.getJoinedDate()%>' readonly>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Visible in search?</label>
                <div class="col-md-8">
                    <input name="visible" class="form-control" type="checkbox" <%=user.getVisible() ? "checked" : ""%>> 
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label"></label>
                <div class="col-md-8">
                    <input type="submit" class="btn btn-primary" value="Save Changes">
                    <span></span>
                    <input type="reset" class="btn btn-default" value="Cancel">
                </div>
            </div>
        </form>
    </div>
</div>
                            
<%
    if (user != null) {
%>

<a href="listing?action=browse&roll=seller&member=<%= user.getId()%>">Listings</a>
<%}%>