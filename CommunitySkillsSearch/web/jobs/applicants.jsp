<%-- 
    Document   : applicants
    Created on : Oct 3, 2016, 4:45:00 PM
    Author     : andyc
--%>
<%@page import="entities.Responders"%>
<%@page import="utils.Contract"%>
<%@page import="entities.Adverts"%>
<%@page import="java.util.List"%>
<%
     List<Responders> responders = (List<Responders>) request.getAttribute(Contract.ADVERT_RESPONDERS);
%>
<div class="row">
    <!-- left column -->
    <jsp:include page="../user/_navigation.jsp"/>

    <!-- my current adverts column -->
    <div class="col-md-8 personal-info">
        <h2>My current applicants</h2>
        <table class="table table-hover">
            <tr>
                <th>User</th><th>Rating</th><th>Job</th><th>Status</th><th>Action</th>
            </tr>

        <%
            if(responders !=null){
                for(Responders r : responders){
                    Integer status = r.getStatus();
                    if (status == null) {
                            r.setStatus(0);
                        }
        %>
            <tr>
                <%-- User column --%>
                <td><a href='user?action=view&id=<%=r.getRespondersPK().getUserId()%>'><%=r.getUser()!=null? r.getUser().getName() : "View"%></a></td>
                <%-- Rating column --%>
                <td><%=r.getUser()!=null? r.getUser().getRating() : ""%></td>
                <%-- Job column --%>
                <td><a href='jobs?action=view&id=<%=r.getRespondersPK().getAdvertsId()%>'><%=r.getAdverts()!=null? r.getAdverts().getTitle() : "View Ad."%></a></td>
                <%-- Status column --%>
                <%
                    switch (Contract.ResponderStatus.values()[r.getStatus()]) {
                    case SELECTED:
                        %>
                        <td>Selected</td>
                        <td><a href='jobs?action=done&id=<%=r.getRespondersPK().getAdvertsId()%>&userid=<%=r.getRespondersPK().getUserId()%>'>Mark as done</a></td>
                        <%
                        break;
                    case DECLINED:
                        %>
                        <td>Declined</td>
                        <td></td>
                        <%
                        break;
                    case JOB_DONE:
                        %>
                        <td>Job done</td>
                        <td><a href='jobs?action=rate&id=<%=r.getRespondersPK().getAdvertsId()%>&userid=<%=r.getRespondersPK().getUserId()%>'>Rate <%=r.getUser()!=null? r.getUser().getName() : "this worker"%></a></td>
                        <%
                        break;
                    case FEEDBACK:
                        %>
                        <td>Feedback placed</td>
                        <td></td>
                        <%
                        break;
                    case FEEDBACK_WORKER:
                        %>
                        <td>Feedback received</td>
                        <td></td>
                        <%
                        break;
                    default:
                        %>
                        <td>Unassigned</td>
                        <td><a href='jobs?action=assign&id=<%=r.getRespondersPK().getAdvertsId()%>&userid=<%=r.getRespondersPK().getUserId()%>'>Assign</a></td>
                        <%
                        break;
                    }%>
            </tr>
                <%}
            }
            %>
        </table>
    </div>