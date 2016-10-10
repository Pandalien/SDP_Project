<%@page import="beans.ServerMessage"%>
<%@page import="java.util.List"%>
<%@page import="utils.Contract"%>
<%
    List<ServerMessage> msgs = (List<ServerMessage>) request.getAttribute(Contract.MESSAGES_FROM_SERVER);
%>

<span class="tag tag-info"></span>${server_message}

<%
    if (msgs != null) {
            for (ServerMessage msg : msgs) {
                switch (msg.level) {
                    case SUCCESS:%>
                    <div class="alert alert-success alert-dismissable">
                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                        <%=msg.message%>&nbsp;<%if(msg.hasLink()){%><a href="<%=msg.link%>" class="alert-link">View Details</a><%}%>
                    </div>
                        <%break;
                    case INFO:%>
                    <div class="alert alert-info alert-dismissable">
                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                        <%=msg.message%>&nbsp;<%if (msg.hasLink()) {%><a href="<%=msg.link%>" class="alert-link">View Details</a><%}%>
                    </div>
                        <%break;
                    case WARNING:%>
                    <div class="alert alert-warning alert-dismissable">
                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                        <%=msg.message%>&nbsp;<%if (msg.hasLink()) {%><a href="<%=msg.link%>" class="alert-link">View Details</a><%}%>
                    </div>
                        <%break;
                    case DANGER:%>
                    <div class="alert alert-danger alert-dismissable">
                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                        <%=msg.message%>&nbsp;<%if (msg.hasLink()) {%><a href="<%=msg.link%>" class="alert-link">View Details</a><%}%>
                    </div>
                        <%break;
                    case LINK:%>
                    <div class="alert alert-danger" role="alert">
                        <strong>Oh snap!</strong>&nbsp;
                        <a href="#" class="alert-link"><%=msg.message%></a>
                    </div>
                        <%break;
                    case DISMISSIBLE:%>
                    <div class="alert alert-warning alert-dismissible fade in" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                        <strong>Holy guacamole!</strong>&nbsp;<%=msg.message%>
                    </div>
                        <%break;
                    case HEADING:%>
                    <div class="alert alert-success" role="alert">
                        <h4 class="alert-heading">Attention!</h4>
                        <%=msg.message%>
                    </div>
                        <%break;
                }
            }
        }
%>