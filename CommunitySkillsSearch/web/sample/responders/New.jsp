<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New Responders</title>
            <link rel="stylesheet" type="text/css" href="/CommunitySkillsSearch/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New Responders</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{responders.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="Message:"/>
                    <h:inputText id="message" value="#{responders.responders.message}" title="Message" />
                    <h:outputText value="Time (MM/dd/yyyy HH:mm:ss):"/>
                    <h:inputText id="time" value="#{responders.responders.time}" title="Time" >
                        <f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
                    </h:inputText>
                    <h:outputText value="Feedback:"/>
                    <h:inputText id="feedback" value="#{responders.responders.feedback}" title="Feedback" />
                    <h:outputText value="Rating:"/>
                    <h:inputText id="rating" value="#{responders.responders.rating}" title="Rating" />
                    <h:outputText value="Status:"/>
                    <h:inputText id="status" value="#{responders.responders.status}" title="Status" />
                    <h:outputText value="Adverts:"/>
                    <h:selectOneMenu id="adverts" value="#{responders.responders.adverts}" title="Adverts" required="true" requiredMessage="The adverts field is required." >
                        <f:selectItems value="#{adverts.advertsItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                    <h:outputText value="User:"/>
                    <h:selectOneMenu id="user" value="#{responders.responders.user}" title="User" required="true" requiredMessage="The user field is required." >
                        <f:selectItems value="#{user.userItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{responders.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{responders.listSetup}" value="Show All Responders Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
