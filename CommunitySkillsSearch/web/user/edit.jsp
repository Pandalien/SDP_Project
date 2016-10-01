<%-- 
    Document   : edit
    Created on : Apr 17, 2016, 1:53:46 PM
    Author     : Andy Chen, Matt Chung
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
    Collection<UserSkills> userSkills = (Collection<UserSkills>) request.getAttribute("skillsList");
    User user = (User) session.getAttribute(Contract.CURRENT_USER);
%>

<h2>EDIT ACCOUNT</h2>
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
        <form class="form-horizontal" name="user_form_edit" role="form" action="user?action=edit" method="post">
            <div class="form-group">
                <label class="col-md-3 control-label">User Name</label>
                <div class="col-md-8">
                    <input name="name" class="form-control" type="text" value='<%=user.getName()%>' readonly>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Email</label>
                <div class="col-md-8">
                    <input name="email" class="form-control" type="email" value='<%=user.getEmail()%>' readonly>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Contact</label>
                <div class="col-md-8">
                    <input name="phone" class="form-control" type="tel" value='<%=user.getPhone()%>'>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Suburb</label>
                <div class="col-md-8">
                    <div class="ui-select">
                        <select name="suburb_id" class="form-control" value="">
                            <%for (Suburb s : suburbs) {%>
                            <option value="<%=s.getId()%>"<%=s.getId()==user.getSuburbId().getId() ? " selected" : ""%>><%=s.getSuburb()%></option>
                            <%}%>
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Skills</label>
                <div class="col-md-8">
                    
                    <div class="input-group">
                        <select id="skillTags" multiple name="current_skill" data-role="tagsinput">
                        <%
                            Iterator<UserSkills> skillsIterator = userSkills.iterator();
                            while (skillsIterator.hasNext()) {
                                Skills skill = skillsIterator.next().getSkills();
                                %><option value="<%=skill.getName()%>"></option>
                          <%}
                        %>
                        </select>
                    </div>
                    <br>
                    <div class="input-group">
                        <select id="skillsList" name="skills" class="form-control" value="">
                            <%for (Skills s : skills) {%>
                            <option value="<%=s.getId()%>"><%=s.getName()%></option>
                            <%}%>
                        </select>
                        <span class="input-group-btn">
                            <button id="add_btn" name="addSkill" class="btn btn-success" type="button" onclick="addSkillTag();">+</button>
                            <script>
                                function addSkillTag() {
                                    var selectedSkill = $("#skillsList option:selected").text();
                                    $('#skillTags').tagsinput('add', selectedSkill);
                                }
                            </script>
                        </span>
                    </div>
                        
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Introduction</label>
                <div class="col-md-8">
                    <textarea name="intro" class="form-control" value=''><%=user.getIntroduction()%></textarea>
                </div>
            </div>      
            <div class="form-group">
                <label class="col-md-3 control-label">Join Date</label>
                <div class="col-md-8">
                    <input name="joined_date" class="form-control" type="text" value='<%=user.getJoinedDate()%>' readonly='true'/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Visible in Search?</label>
                <div class="col-md-8">
                    <input name="visible" id="edit_visible" type="checkbox"
                           data-toggle="toggle" data-onstyle="success" data-offstyle="danger"
                           <%=user.getVisible() != null && user.getVisible().booleanValue() ? " checked" : ""%>
                           />
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