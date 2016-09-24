<%-- 
    Document   : create
    Created on : Sep 17, 2016, 12:02:45 PM
    Author     : andy chen
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="entities.Adverts"%>
<%@page import="entities.Classification"%>
<%@page import="entities.Suburb"%>
<%@page import="utils.Contract"%>
<%@page import="entities.Skills"%>
<%@page import="java.util.List"%>

<%
    List<Skills> skills = (List<Skills>) request.getAttribute(Contract.SKILLS);
    List<Suburb> suburbs = (List<Suburb>) request.getAttribute(Contract.SUBURBS);
    List<Classification> classifications = (List<Classification>) request.getAttribute(Contract.CLASSIFICATIONS);
    Adverts ad = (Adverts) request.getAttribute(Contract.VIEW_ADVERT);
    Integer suburbId = 1;
    Integer cataId = 1;
    String xDate = "";
    List<Integer> reSkIds=null;
    
    if (ad != null) {
        suburbId = ad.getSuburbId().getId();
        cataId = ad.getClassificationId().getId();
        
        reSkIds = (List<Integer>) request.getAttribute(Contract.ADVERT_SKILL_IDS);
        
        if (ad.getExpiryDate() != null) {
            xDate = new SimpleDateFormat("yyyy-MM-dd").format(ad.getExpiryDate());
        }
    }
%>
<form action="jobs?action=create" method="post">
    <input type="hidden" name="id" value='<%= ad==null? "" : ad.getId()%>'/>
    <div class="form-group">
        <label class="col-md-3 control-label">Title</label>
        <div class="col-md-8">
            <input name="title" value='<%= ad==null? "" : ad.getTitle()%>' class="form-control" type="text" value="" placeholer="title" required="true"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-3 control-label">Classification</label>
        <div class="col-md-8">
            <div class="ui-select">
                <select name='classification' class="form-control" >
                    <%for (Classification cl : classifications) {%>
                    <option
                        value='<%=cl.getId()%>' 
                        <%= cl.getId()==cataId? "selected='true'" : ""%>>
                        <%=cl.getName()%>
                    </option>
                    <%}%>
                </select>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-3 control-label">Content</label>
        <div class="col-md-8">
            <textarea name="content" class="form-control" rows="10" required="true"><%= ad==null? "" : ad.getContent()%></textarea>
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-3 control-label">Select skills required: (Hold down the Ctrl (windows) / Command (Mac) button to select multiple options.)</label>
        <div class="col-md-8">
            <div class="ui-select">
                <select name='skills' multiple="true" class="form-control" >
                    <%for (Skills s : skills) {%>
                    <option value='<%=s.getId()%>' <%=(reSkIds !=null && reSkIds.contains(s.getId()))? "selected" : ""%>><%=s.getName()%></option>
                    <%}%>
                </select>
            </div>
        </div>
    </div>            
    <div class="form-group">
        <label class="col-md-3 control-label">Location</label>
        <div class="col-md-8">
            <div class="ui-select">
                <select name='suburb' class="form-control" >
                    <%for (Suburb sub : suburbs) {%>
                    <option value='<%=sub.getId()%>' <%= suburbId == sub.getId() ? "selected='true'" : ""%>>
                        <%=sub.getSuburb()%>
                    </option>
                    <%}%>
                </select>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-3 control-label">Expiry Date</label>
        <div class="col-md-8">
            <input name="expiry_date" type="date" class="form-control" 
                   value='<%=xDate%>'
            />
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-3 control-label"></label>
        <div class="col-md-8">
            <input type="submit" class="btn btn-primary" value='<%= ad==null? "Create" : "Save"%>'>
            <input type="reset" class="btn btn-default" value="Reset">
        </div>
    </div>
</form>
