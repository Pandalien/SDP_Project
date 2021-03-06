<%-- 
    Document   : rate
    Created on : Oct 11, 2016, 11:52:45 PM
    Author     : Matt
--%>

<%@page import="entities.Adverts"%>
<%@page import="entities.Responders"%>
<%@page import="utils.Contract"%>
<%@page import="entities.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    User user = (User) session.getAttribute(Contract.CURRENT_USER);     //current user
    User worker = (User) request.getAttribute(Contract.OTHER_USER);     // the user who worked for the job
    Adverts ad = (Adverts) request.getAttribute(Contract.ADVERTS);
    boolean isAdOwner = ad != null && ad.getId() == user.getId();
%>

<div class="container fuelux">
    <div class="row">
        <div class="col-md-8 personal-info">
        <%
            if (user != null && worker != null && ad != null) {
        %>
            <h3>Rate for <%=isAdOwner? user.getName() : worker.getName()%>:</h3>
            <br>
            <form action="jobs?action=rate" method="post">
                <input type="hidden" name="workerId" value='<%= worker==null? "" : worker.getId()%>'/>
                <input type="hidden" name="id" value='<%= ad==null? "" : ad.getId()%>'/>
                <div class="form-group row">
                    <div class="col-md-12">
                        <div class="well">
                            <h4>Rating</h4>
                            <div class="radio radio-inset">
                                <label class="radio-custom" data-initialize="radio">
                                    <input name="rating" value="-1" title="Sample" class="sr-only" type="radio">Bad</label>
                                <label class="radio-custom" data-initialize="radio">
                                    <input name="rating" value="0" title="Sample" class="sr-only" type="radio">Neutral</label>
                                <label class="radio-custom" data-initialize="radio">
                                    <input name="rating" value="1" title="Sample" class="sr-only" type="radio" required>Good</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group row">   
                    <div class="col-md-12">
                        <div class="well">
                            <h4>Feedback</h4>
                            <div class="form-group">
                                <textarea name="feedback" class="form-control" rows="4" required="true"></textarea>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group row">  
                    <div class="col-md-12">
                        <input type="submit" value="Submit" class="btn btn-primary">
                        <span></span>
                        <a href="jobs?action=<%=isAdOwner ? "applicants" : "applications"%>" class="btn btn-default">Later</a>
                    </div>
                </div>
            </form>
        <%  }%>
        </div>
    </div>
</div>
        