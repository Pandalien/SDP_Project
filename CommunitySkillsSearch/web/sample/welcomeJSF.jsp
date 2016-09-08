<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%--
    This file is an entry point for JavaServer Faces application.
--%>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>JSP Page</title>
<link rel="stylesheet" type="text/css" href="/CommunitySkillsSearch/faces/jsfcrud.css" />
        </head>
        <body>
            <h1><h:outputText value="JavaServer Faces"/></h1>
                <h:form>
                    <h:commandLink action="#{userSkills.listSetup}" value="Show All UserSkills Items"/>
                </h:form>

                <h:form>
                    <h:commandLink action="#{user.listSetup}" value="Show All User Items"/>
                </h:form>

                <h:form>
                    <h:commandLink action="#{suburb.listSetup}" value="Show All Suburb Items"/>
                </h:form>

                <h:form>
                    <h:commandLink action="#{skills.listSetup}" value="Show All Skills Items"/>
                </h:form>

                <h:form>
                    <h:commandLink action="#{responders.listSetup}" value="Show All Responders Items"/>
                </h:form>

                <h:form>
                    <h:commandLink action="#{messages.listSetup}" value="Show All Messages Items"/>
                </h:form>

                <h:form>
                    <h:commandLink action="#{classification.listSetup}" value="Show All Classification Items"/>
                </h:form>

                <h:form>
                    <h:commandLink action="#{adverts.listSetup}" value="Show All Adverts Items"/>
                </h:form>

        </body>
    </html>
</f:view>
