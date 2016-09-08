<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Messages Detail</title>
            <link rel="stylesheet" type="text/css" href="/CommunitySkillsSearch/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Messages Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Id:"/>
                    <h:outputText value="#{messages.messages.messagesPK.id}" title="Id" />
                    <h:outputText value="Content:"/>
                    <h:outputText value="#{messages.messages.content}" title="Content" />
                    <h:outputText value="Read:"/>
                    <h:outputText value="#{messages.messages.read}" title="Read" />
                    <h:outputText value="Time:"/>
                    <h:outputText value="#{messages.messages.time}" title="Time" >
                        <f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
                    </h:outputText>
                    <h:outputText value="User:"/>
                    <h:panelGroup>
                        <h:outputText value="#{messages.messages.user}"/>
                        <h:panelGroup rendered="#{messages.messages.user != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{user.detailSetup}">
                                <f:param name="jsfcrud.currentMessages" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][messages.messages][messages.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][messages.messages.user][user.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="messages"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.MessagesController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{user.editSetup}">
                                <f:param name="jsfcrud.currentMessages" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][messages.messages][messages.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][messages.messages.user][user.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="messages"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.MessagesController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{user.destroy}">
                                <f:param name="jsfcrud.currentMessages" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][messages.messages][messages.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][messages.messages.user][user.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="messages"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.MessagesController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="User1:"/>
                    <h:panelGroup>
                        <h:outputText value="#{messages.messages.user1}"/>
                        <h:panelGroup rendered="#{messages.messages.user1 != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{user.detailSetup}">
                                <f:param name="jsfcrud.currentMessages" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][messages.messages][messages.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][messages.messages.user1][user.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="messages"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.MessagesController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{user.editSetup}">
                                <f:param name="jsfcrud.currentMessages" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][messages.messages][messages.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][messages.messages.user1][user.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="messages"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.MessagesController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{user.destroy}">
                                <f:param name="jsfcrud.currentMessages" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][messages.messages][messages.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][messages.messages.user1][user.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="messages"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.MessagesController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>


                </h:panelGrid>
                <br />
                <h:commandLink action="#{messages.remove}" value="Destroy">
                    <f:param name="jsfcrud.currentMessages" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][messages.messages][messages.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{messages.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentMessages" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][messages.messages][messages.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{messages.createSetup}" value="New Messages" />
                <br />
                <h:commandLink action="#{messages.listSetup}" value="Show All Messages Items"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
