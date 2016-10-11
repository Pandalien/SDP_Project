<%-- 
    Document   : rate
    Created on : Oct 11, 2016, 11:52:45 PM
    Author     : Matt
--%>

<%@page import="utils.Contract"%>
<%@page import="entities.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    User worker = (User) request.getAttribute(Contract.OTHER_USER);
%>
<div class="container fuelux">
    <div class="row">
        <div class="col-md-8 personal-info">
            <%
                if (worker != null) {
            %>
            <h3>Rate for <%=worker.getName()%>:</h3>
            <br>
            <form action="jobs?action=rate" method="post">
                <div class="form-group row">
                    <div class="col-md-12">
                        <div class="well">
                            <h4>Rating</h4>
                            <div class="radio radio-inset">
                                <label class="radio-custom" data-initialize="radio">
                                    <input name="radio1" value="-1" title="Sample" class="sr-only" type="radio">Bad</label>
                                <label class="radio-custom" data-initialize="radio">
                                    <input name="radio1" value="0" title="Sample" class="sr-only" type="radio">Neutral</label>
                                <label class="radio-custom" data-initialize="radio">
                                    <input name="radio1" value="1" title="Sample" class="sr-only" type="radio">Good</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group row">   
                    <div class="col-md-12">
                        <div class="well">
                            <h4>Feedback</h4>
                            <div class="form-group">
                                <textarea name="feedback" class="form-control" rows="4"></textarea>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group row">  
                    <div class="col-md-12">
                        <input type="submit" value="Submit" class="btn btn-primary">
                        <input type="button" value="Cancel" class="btn btn-secondary">
                    </div>
                </div>
            </form>
            <% } %>
        </div>
    </div>
</div>