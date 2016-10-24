<%-- 
    Document   : _workStatus
    Created on : Oct 19, 2016, 1:07:56 PM
    Author     : andyc
    Used to display status for work
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@page import="entities.Adverts"%>
<%@page import="utils.Contract"%>
<%@page import="entities.Responders"%>
<%
    Adverts ad = (Adverts) request.getAttribute(Contract.VIEW_ADVERT);
    //Responders responder = (Responders) request.getAttribute(Contract.ADVERT_RESPONDERS);
    Collection<Responders> respondersList = ad.getRespondersCollection();
        Responders responder = null;
        if (respondersList != null && !respondersList.isEmpty()) {
            Iterator<Responders> it = respondersList.iterator();
            responder = it.next();
        }
%>
<%if(responder != null){%>
<%
    Integer status = responder.getStatus();
    if (status == null) {
        responder.setStatus(0);
    }
    switch (Contract.ResponderStatus.values()[responder.getStatus()]) {
        case SELECTED:
%>
Offered
<a href='jobs?action=accept&id=<%=ad.getId()%>' class="btn btn-primary">Accept</a> <a href='jobs?action=reject&id=<%=ad.getId()%>' class="btn btn-danger">Reject</a>
<%
        break;
    case ACCEPTED:
%>
Accepted
<%
        break;
    case DECLINED:
%>
Declined
<%
        break;
    case JOB_DONE:
%>
Job done
<%
        break;
    case FEEDBACK:
%>
Feedback received
<a href='jobs?action=rate&id=<%=ad.getId()%>&userid=<%=ad.getUserId().getId()%>'>Rate <%=ad.getUserId() != null ? ad.getUserId().getName() : "this advertiser"%></a>
<%
        break;
    case FEEDBACK_WORKER:
%>
Feedback placed
<%
        break;
    default:
%>
Submitted 
<a href='jobs?action=cancel&id=<%=ad.getId()%>'>Withdraw</a>
<%
            break;
    }%>




<%}%>
