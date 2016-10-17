<%-- 
    Document   : applications
    Created on : Sep 30, 2016, 4:56:55 PM
    Author     : andyc
--%>

<%@page import="entities.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entities.Responders"%>
<%@page import="utils.Contract"%>
<%@page import="entities.Adverts"%>
<%@page import="java.util.List"%>
<%
    List<Adverts> ads = (List<Adverts>) request.getAttribute(Contract.ADVERTS);
    User user = (User) request.getAttribute(Contract.CURRENT_USER);
    boolean isClosed;
%>
<div class="row">
    <!-- left column -->
    <jsp:include page="../user/_navigation.jsp"/>

    <!-- edit photo column -->
    <div class="col-md-8 personal-info">
        <h2>My current applications</h2>
        <table class="table table-hover">
            <tr><th>#</th><th>Title</th><th>Vacancy</th><th>Status</th><th>Change</th><th>Pending</th></tr>

            <%
                if (ads != null) {
                    for (Adverts a : ads) {
                        isClosed = a.getClosed() == null || !a.getClosed();
            %>
            <tr>
                <td><%=a.getId()%></td>
                <td><a href='jobs?action=view&id=<%=a.getId()%>&userid=<%=user.getId()%>'><%=a.getTitle()%></a></td>
                <td><%=isClosed ? "Closed" : "Open"%></td>
                <%
                    List<Responders> res = new ArrayList<Responders>(a.getRespondersCollection());
                    Integer status = res.get(0).getStatus();
                    if (status == null) {
                        res.get(0).setStatus(0);
                    }
                    switch (Contract.ResponderStatus.values()[res.get(0).getStatus()]) {
                    case SELECTED:
                        %>
                        <td>Offered</td>
                        <td><a href='jobs?action=cancel&id=<%=a.getId()%>'>Withdraw</a></td>
                        <td><a href='jobs?action=accept&id=<%=a.getId()%>'>Accept</a> / <a href='jobs?action=reject&id=<%=a.getId()%>'>Reject</a></td>
                        <%
                        break;
                    case ACCEPTED:
                        %>
                        <td>Declined</td>
                        <td></td>
                        <td></td>
                        <%
                        break;
                    case DECLINED:
                        %>
                        <td>Declined</td>
                        <td></td>
                        <td></td>
                        <%
                        break;
                    case JOB_DONE:
                        %>
                        <td>Job done</td>
                        <td></td>
                        <td></td>
                        <%
                        break;
                    case FEEDBACK:
                        %>
                        <td>Feedback received</td>
                        <td></td>
                        <td><a href='jobs?action=rate&id=<%=res.get(0).getRespondersPK().getAdvertsId()%>&userid=<%=res.get(0).getRespondersPK().getUserId()%>'>Rate <%=a.getUserId()!=null? a.getUserId().getName() : "this advertiser"%></a></td>
                        <%
                        break;
                    case FEEDBACK_WORKER:
                        %>
                        <td>Feedback placed</td>
                        <td></td>
                        <td></td>
                        <%
                        break;
                    default:
                        %>
                        <td>Unassigned</td>
                        <td><a href='jobs?action=cancel&id=<%=a.getId()%>'>Withdraw</a></td>
                        <td></td>
                        <%
                        break;
                    }%>
            </tr>
                  <%}
                }%>
        </table>
    </div>
</div>
    

