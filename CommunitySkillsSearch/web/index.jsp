<%-- 
    Document   : index
    Created on : Apr 17, 2016, 1:48:35 PM
    Author     : Andy Chen
--%>

<%@page import="entities.Adverts"%>
<%@page import="utils.Contract"%>
<%@page import="entities.User"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%
    User user = (User) session.getAttribute(Contract.CURRENT_USER);
    List<Adverts> ads = (List<Adverts>) request.getAttribute(Contract.ADVERTS);
%>

<!-- Header Carousel -->
<div id="myCarousel" class="carousel slide">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner">
        <div class="item active">
            <div class="fill" style="background-image:url('res/images/jobsearch.jpg');"></div>
            <div class="carousel-caption">
                <h2>Work in the community</h2>
            </div>
        </div>
        <div class="item">
            <div class="fill" style="background-image:url('res/images/reflection-of-rocks-in-a-lake.jpg');"></div>
            <div class="carousel-caption">
                <h2>Enjoy life</h2>
            </div>
        </div>
        <div class="item">
            <div class="fill" style="background-image:url('res/images/sapa-rice-field.jpg');"></div>
            <div class="carousel-caption">
                <h2>Harvest awaits</h2>
            </div>
        </div>
    </div>

    <!-- Controls -->
    <a class="left carousel-control" href="#myCarousel" data-slide="prev">
        <span class="icon-prev"></span>
    </a>
    <a class="right carousel-control" href="#myCarousel" data-slide="next">
        <span class="icon-next"></span>
    </a>
</div>

<!-- Marketing Icons Section -->
<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">
            Welcome to Community Skills Search
        </h1>
    </div>
    <div class="col-md-4">
        <div class="panel panel-info">
            <div class="panel-heading">
                <h4><i class="fa fa-fw fa-check"></i> Get involved in the community</h4>
            </div>
            <div class="panel-body">
                <p>You can make a difference</p>
                <ul>
                    <li>Support families (daycare and eldercare)</li>
                    <li>Improve schools (tutoring, literacy)</li>
                    <li>Support youth (mentoring and after-school programs)</li>
                    <li>Beautify the community (beach and park cleanups)</li>
                </ul>
                <a href="search?action=searchFor&keywords=&type=workers" class="btn btn-primary">Find Workers</a>
                <a href="search?action=searchFor&keywords=&type=jobs" class="btn btn-primary">Find Jobs</a>                
            </div>
        </div>
    </div>
    <div class="col-md-4">
        <div class="panel panel-info">
            <div class="panel-heading">
                <h4><i class="fa fa-fw fa-gift"></i> Enhance your abilities</h4>
            </div>
            <div class="panel-body">
                <p><strong>Reduces stress:</strong> Experts report that when you focus on someone other than yourself, it interrupts usual tension-producing patterns.</p>
                <p><strong>Makes you healthier:</Strong> Moods and emotions, like optimism, joy, and control over one's fate, strengthen the immune system.</p>
                <a href="home?action=help" class="btn btn-default">Learn More</a>
            </div>
        </div>
    </div>
    <div class="col-md-4">
        <div class="panel panel-info">
            <div class="panel-heading">
                <h4><i class="fa fa-fw fa-compass"></i> Easy to Use</h4>
            </div>
            <div class="panel-body">
                <p>Community Skills Search&trade; provides an desirable spot for people interested in working in the community to search for jobs in the field. You can search by type of job, keywords or areas of interest, and location.</p>
                <a href="home?action=help" class="btn btn-default">Learn More</a>
            </div>
        </div>
    </div>
</div>
<!-- /.row -->

<br/>
<%if(ads != null && !ads.isEmpty()){%>
<!-- Jobs Section -->
<div class="row">
    <div class="col-lg-12">
        <h2 class="page-header">Jobs that match your skills</h2>
    </div>
    <jsp:include page="/jobs/_listings.jsp"/>
</div>
<!-- /.row -->
<%}%>

<!-- Script to Activate the Carousel -->
<script>
$('.carousel').carousel({
    interval: 5000 //changes the speed
});
</script>