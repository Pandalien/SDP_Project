<%-- 
    Document   : view
    Created on : Sep 24, 2016, 2:25:09 PM
    Author     : andyc
--%>

<%@page import="java.util.Iterator"%>
<%@page import="entities.Skills"%>
<%@page import="entities.Requirements"%>
<%@page import="utils.Contract.ResponderStatus"%>
<%@page import="utils.StringUtils"%>
<%@page import="javax.servlet.Servlet"%>
<%@page import="utils.ServletUtils"%>
<%@page import="entities.User"%>
<%@page import="java.util.Collection"%>
<%@page import="entities.Responders"%>
<%@page import="utils.Contract"%>
<%@page import="entities.Adverts"%>
<%
    Adverts ad = (Adverts) request.getAttribute(Contract.VIEW_ADVERT);
    Collection<Responders> respondersList = ad.getRespondersCollection();
    Responders responder = null;
    if (respondersList != null && !respondersList.isEmpty()) {
        Iterator<Responders> it = respondersList.iterator();
        responder = it.next();
    }
       
    User currentUser = (User) session.getAttribute(Contract.CURRENT_USER);
    
    boolean isOpen = ad.getClosed()==null||!ad.getClosed();
    boolean hasApplied = responder!=null;
    
    boolean isOwner = currentUser != null && (ad.getUserId().getId() == currentUser.getId());
    
    String photo = ServletUtils.getUserPhoto(this, request, ad.getImg());
%>
<!-- Content Row -->
<div class="row">
    <div class="col-md-12">
        <h2 class="page-header">
            <span class="label label-default"><%=isOpen? "Open" : "Closed"%></span>
            <%=ad.getTitle()%>
            <small>by <a href="user?action=view&id=<%=ad.getUserId().getId()%>"><%=ad.getUserId().getName()%></a>
            </small>
        </h2>
    </div>
    <!-- Job Post Content Column -->
    <div class="col-md-8">

        <!-- Job Post -->

        <!-- Preview Image -->
        <%if (!StringUtils.isEmpty(photo)) {%>
        <img class="img-responsive" src="<%=photo%>" height="200" alt="">
        <%}else{%>
        <img class="img-responsive" src="res/images/jobsearch.jpg" alt="">
        <%}%>

        <hr>

        <!-- Post Content -->
        <p class="lead">Looking for you ideal work?</p>
        <p>
            <%Collection<Skills> reqs = ad.getSkillsCollection();
            if (reqs != null) {
            %>
            Key Skills 
            <%
                for(Skills r : reqs){
                    String skill = r.getName();
                    if (skill != null) {
            %>
                        <span class="label label-info"><%=skill%></span>
            <%
                    }
                }
            }
            %>
            <%%>
        </p>
        <P><%=ad.getContent()%></P>

        <hr>

        <!-- Job Comments -->

        <%if(!isOwner){%>
        <!-- Comments Form -->
        <div class="well">
            <h4>Leave a Message to <%=ad.getUserId().getName()%></h4>
            <form role="form" action="messages?action=compose" method="post">
                <input type="hidden" name="id" value="<%=ad.getUserId().getId()%>"/>
                <div class="form-group">
                    <textarea name="message" class="form-control" rows="3"></textarea>
                </div>
                <button type="submit" class="btn btn-primary">Send</button>
            </form>
        </div>
        <%}%>
                
        <!-- Posted Comments -->
        <%
        if (responder != null && responder.getStatus() != null) {
            if (responder.getStatus() > Contract.ResponderStatus.JOB_DONE.ordinal()) {     // display advertiser's feedback 
        %>
                <hr>
                <div class="media">
                    <a class="pull-left" href="#">
                        <img src="<%=ServletUtils.getUserAvatar(this, request, ad.getUserId().getImg())%>" class="avatar img-circle" alt="avatar" width="64" onerror="this.onerror=null;this.src='//placehold.it/64';this.className='avatar img-circle';">
                    </a>
                    <div class="media-body">
                        <h4 class="media-heading">Feedback from <a href="user?action=view&id=<%=ad.getUserId().getId()%>"><%=ad.getUserId().getName()%></a></h4>
                        Rating: <%
                            switch (responder.getRating()){
                                case 1: %><strong>GOOD</strong>
                                <%break;
                                case 0: %><strong>NEUTRAL</strong>
                                <%break;
                                case -1: %><strong>BAD</strong>
                                <%break;
                            }
                        %>
                        <br><%=responder.getFeedback()%>
                    </div>
                </div>
        <%  }
            if (responder.getStatus() > Contract.ResponderStatus.FEEDBACK.ordinal()) {      // display worker's feedback 
        %>
                <hr>
                <div class="media">
                    <a class="pull-left" href="#">
                        <img src="<%=ServletUtils.getUserAvatar(this, request, responder.getUser().getImg())%>" class="avatar img-circle" alt="avatar" width="64" onerror="this.onerror=null;this.src='//placehold.it/64';this.className='avatar img-circle';">
                    </a>
                    <div class="media-body">
                        <h4 class="media-heading">Feedback from <a href="user?action=view&id=<%=responder.getUser().getId()%>"><%=responder.getUser().getName()%></a></h4>
                        Rating: <%
                            switch (responder.getRatingWorker()){
                                case 1: %><strong>GOOD</strong>
                                <%break;
                                case 0: %><strong>NEUTRAL</strong>
                                <%break;
                                case -1: %><strong>BAD</strong>
                                <%break;
                            }
                        %>
                        <br><%=responder.getFeedbackWorker()%>
                    </div>
                </div>
        <%  }
        }%>
    </div>

    <!-- Job Sidebar Widgets Column -->
    <div class="col-md-4">
        <!-- Date/Time -->
        <p><i class="fa fa-clock-o"></i> Expiry Date: <%=ad.getExpiryDate()%></p>
        <hr>

        <!-- Job Categories Well -->
        <div class="well">
            <h4>Job Categories</h4>
            <div class="row">
                <div class="col-md-6">
                    <ul class="list-unstyled">
                        <li><a href="search?action=searchFor&keywords=<%=ad.getTitle()%>"><%=ad.getClassificationId().getName()%></a>
                        </li>
                        
                    </ul>
                </div>
                <div class="col-md-6">
                    <ul class="list-unstyled">
                        <li><a href="search?action=searchFor">More...</a>
                        </li>
                    </ul>
                </div>
            </div>
            <!-- /.row -->
        </div>

        <%if (!isOwner) {%>
        
        <div class="well">
            <h4>Application</h4>
            <div class="row">
                <div class="col-md-12">
                    <ul class="list-unstyled">
                        <li>
                            <%if (isOpen && !hasApplied) {%>
                            <a href='jobs?action=apply&id=<%=ad.getId()%>' class="btn btn-outline-primary">Apply</a>
                            <%}else{%>
                                <jsp:include page="/jobs/_workStatus.jsp"/>
                            <%}%>
                        </li>
                    </ul>
                </div>
            </div>
        </div>

        <%} else {%>
        
        <div class="well">
            <h4>Edit Post</h4>
            <div class="row">
                <div class="col-md-12">
                    <p>
                        <a href='jobs?action=<%=isOpen ? "close" : "open"%>&id=<%=ad.getId()%>' class="btn btn-default"><%=isOpen ? "Close" : "Open"%></a>
                        <a href='jobs?action=edit&id=<%=ad.getId()%>' class="btn btn-default">Edit</a>
                        <a href='jobs?action=delete&id=<%=ad.getId()%>' class="btn btn-danger">Delete</a>
                    </p>
                </div>
            </div>
        </div>
        <div class="well">
            <h4>Edit Photo</h4>
            <div class="row">
                
                <div class="col-md-12">
                    <form class="form-horizontal" role="form" action="photo?action=uploadAdPhoto" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="id" value='<%= ad==null? "" : ad.getId()%>'/>
                        <div class="form-group">
                            <label class="col-md-3 control-label">Photo</label>
                            <div class="col-md-9">
                                <input class="form-control" type="file" name="file"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label"></label>
                            <div class="col-md-9">
                                <input type="submit" class="btn btn-primary" value="Upload">
                                <span></span>
                                <a class="btn btn-danger" href="photo?action=deleteAdPhoto&id=<%=ad.getId()%>">Delete</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div                
        <!-- Side Widget Well for Responders-->
        <%Collection<Responders> responders = ad.getRespondersCollection();
          if (responders != null && !responders.isEmpty()) {%>
          <div class="well">
              <h4>Candidates</h4>
        <%
              for (Responders r : responders) {
        %>
        
            <div class="card">
                <div class="card-block">
                    <a href="user?action=view&id=<%=r.getUser().getId()%>" class="card-link"><%=r.getUser().getName()%></a>
                </div>
            </div>
        
        <%}%>
        </div>
        <%}%>
                        
        <%}%>
    </div>

</div>
<!-- /.row -->
