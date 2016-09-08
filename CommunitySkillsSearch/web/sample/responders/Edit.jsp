<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing Responders</title>
            <link rel="stylesheet" type="text/css" href="/CommunitySkillsSearch/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Editing Responders</h1>
            <h:form>
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
                    <h:outputText value=" #{responders.responders.adverts}" title="Adverts" />
                    <h:outputText value="User:"/>
                    <h:outputText value=" #{responders.responders.user}" title="User" />

                </h:panelGrid>
                <br />
                <h:commandLink action="#{responders.edit}" value="Save">
                    <f:param name="jsfcrud.currentResponders" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][responders.responders][responders.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{responders.detailSetup}" value="Show" immediate="true">
                    <f:param name="jsfcrud.currentResponders" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][responders.responders][responders.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <h:commandLink action="#{responders.listSetup}" value="Show All Responders Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
