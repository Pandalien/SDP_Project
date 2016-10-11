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

<div class="row">
    <!-- left column -->
    <jsp:include page="_navigation.jsp"/>

    <!-- rating column -->
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
                                <input type="radio" name="rating" value="-1"  class="sr-only">Bad</label>
                            <label class="radio-custom" data-initialize="radio">
                                <input type="radio" name="rating" value="0" class="sr-only">Neutral</label>
                            <label class="radio-custom" data-initialize="radio">
                                <input type="radio" name="rating" value="1" class="sr-only">Good</label>
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