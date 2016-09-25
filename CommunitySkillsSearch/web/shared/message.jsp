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
                    <div class="alert alert-success" role="alert">
                        <strong>Well done!</strong><%=msg.message%>
                    </div>
                        <%break;
                    case INFO:%>
                    <div class="alert alert-info" role="alert">
                        <strong>Heads up!</strong><%=msg.message%>
                    </div>
                        <%break;
                    case WARNING:%>
                    <div class="alert alert-warning" role="alert">
                        <strong>Warning!</strong><%=msg.message%>
                    </div>
                        <%break;
                    case DANGER:%>
                    <div class="alert alert-danger" role="alert">
                        <strong>Oh snap!</strong><%=msg.message%>
                    </div>
                        <%break;
                    case LINK:%>
                    <div class="alert alert-danger" role="alert">
                        <strong>Oh snap!</strong>
                        <a href="#" class="alert-link"><%=msg.message%></a>
                    </div>
                        <%break;
                    case DISMISSIBLE:%>
                    <div class="alert alert-warning alert-dismissible fade in" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                        <strong>Holy guacamole!</strong><%=msg.message%>
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