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
    List<UserSkills> userSkills = (List<UserSkills>) request.getAttribute("skillsList");
    User user = (User) session.getAttribute(Contract.CURRENT_USER);
%>

<div class="row">
    <!-- left column -->
    <jsp:include page="_navigation.jsp"/>

    <!-- edit form column -->
    <div class="col-md-8 personal-info">
        <h3>Personal info</h3>
        <p>* required field</p>
        <form class="form-horizontal" name="user_form_edit" role="form" action="user?action=edit" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label class="col-md-3 control-label">User Name</label>
                <div class="col-md-8">
                    <input class="form-control" type="text" name="name" value='<%=user.getName()%>' readonly>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">* Email</label>
                <div class="col-md-8">
                    <input class="form-control" type="email" name="email" value='<%=user.getEmail()%>'>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">* Contact</label>
                <div class="col-md-8">
                    <input class="form-control" type="tel" name="phone" value='<%=user.getPhone()%>'>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Suburb</label>
                <div class="col-md-8">
                    <div class="ui-select">
                        <select class="form-control" name="suburb_id" value="">
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
                        <input id="skillTags" type="text" name="current_skill"/>
                        <script type="text/javascript">
                            $(document).ready(function(){
                                $('#skillTags').tagsinput({
                                    allowDuplicates: false,
                                    itemValue: 'id',  // this will be used to set id of tag
                                    itemText: 'label' // this will be used to set text of tag
                                });
                            });
                            <%
                                for (UserSkills skill : userSkills) {
                                    if (skill == null) {
                                            continue;
                                        }
                            %>
                                    $(document).ready(function(){
                                        $('#skillTags').tagsinput('add', {"id": <%=skill.getUserSkillsPK().getSkillsId()%>, "label": "<%=skill.getSkills().getName()%>"});
                                    });
                                <%}
                            %>
                        </script>
                    </div>
                    <br>
                    <div class="input-group">
                        <select id="skillsList" class="form-control" name="skills" value="">
                            <%for (Skills s : skills) {%>
                            <option value="<%=s.getId()%>"><%=s.getName()%></option>
                            <%}%>
                        </select>
                        <span class="input-group-btn">
                            <button id="add_btn" class="btn btn-success" type="button" onclick="addSkillTag();">+</button>
                            <script>
                                function addSkillTag() {
                                    var selectedSkill = $("#skillsList option:selected");
                                    $('#skillTags').tagsinput('add', {"id": selectedSkill.val(), "label": selectedSkill.text()});
                                }
                            </script>
                        </span>
                    </div>  
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Introduction</label>
                <div class="col-md-8">
                    <textarea class="form-control" name="intro" value=''><%=user.getIntroduction()%></textarea>
                </div>
            </div>      
            <div class="form-group">
                <label class="col-md-3 control-label">Join Date</label>
                <div class="col-md-8">
                    <input class="form-control" type="text" name="joined_date" value='<%=user.getJoinedDate()%>' readonly='true'/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Visible in Search?</label>
                <div class="col-md-8">
                    <input id="edit_visible" type="checkbox" name="visible" 
                           data-toggle="toggle" data-onstyle="success" data-offstyle="danger"
                           <%=user.getVisible() != null && user.getVisible().booleanValue() ? " checked" : ""%>
                           />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label"></label>
                <div class="col-md-8">
                    <input type="submit" class="btn btn-primary" value="Save Changes">
                </div>
            </div>
        </form>
    </div>
</div>
                           
