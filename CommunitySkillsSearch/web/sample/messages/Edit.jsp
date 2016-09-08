<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing Messages</title>
            <link rel="stylesheet" type="text/css" href="/CommunitySkillsSearch/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Editing Messages</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Id:"/>
                    <h:outputText value="#{messages.messages.messagesPK.id}" title="Id" />
                    <h:outputText value="Content:"/>
                    <h:inputText id="content" value="#{messages.messages.content}" title="Content" required="true" requiredMessage="The content field is required." />
                    <h:outputText value="Read:"/>
                    <h:inputText id="read" value="#{messages.messages.read}" title="Read" />
                    <h:outputText value="Time (MM/dd/yyyy HH:mm:ss):"/>
                    <h:inputText id="time" value="#{messages.messages.time}" title="Time" required="true" requiredMessage="The time field is required." >
                        <f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
                    </h:inputText>
                    <h:outputText value="User:"/>
                    <h:outputText value=" #{messages.messages.user}" title="User" />
                    <h:outputText value="User1:"/>
                    <h:outputText value=" #{messages.messages.user1}" title="User1" />

                </h:panelGrid>
                <br />
                <h:commandLink action="#{messages.edit}" value="Save">
                    <f:param name="jsfcrud.currentMessages" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][messages.messages][messages.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{messages.detailSetup}" value="Show" immediate="true">
                    <f:param name="jsfcrud.currentMessages" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][messages.messages][messages.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <h:commandLink action="#{messages.listSetup}" value="Show All Messages Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
