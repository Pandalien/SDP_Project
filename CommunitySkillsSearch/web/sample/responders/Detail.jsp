<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Responders Detail</title>
            <link rel="stylesheet" type="text/css" href="/CommunitySkillsSearch/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Responders Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Message:"/>
                    <h:outputText value="#{responders.responders.message}" title="Message" />
                    <h:outputText value="Time:"/>
                    <h:outputText value="#{responders.responders.time}" title="Time" >
                        <f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
                    </h:outputText>
                    <h:outputText value="Feedback:"/>
                    <h:outputText value="#{responders.responders.feedback}" title="Feedback" />
                    <h:outputText value="Rating:"/>
                    <h:outputText value="#{responders.responders.rating}" title="Rating" />
                    <h:outputText value="Status:"/>
                    <h:outputText value="#{responders.responders.status}" title="Status" />
                    <h:outputText value="Adverts:"/>
                    <h:panelGroup>
                        <h:outputText value="#{responders.responders.adverts}"/>
                        <h:panelGroup rendered="#{responders.responders.adverts != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{adverts.detailSetup}">
                                <f:param name="jsfcrud.currentResponders" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][responders.responders][responders.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentAdverts" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][responders.responders.adverts][adverts.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="responders"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.RespondersController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{adverts.editSetup}">
                                <f:param name="jsfcrud.currentResponders" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][responders.responders][responders.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentAdverts" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][responders.responders.adverts][adverts.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="responders"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.RespondersController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{adverts.destroy}">
                                <f:param name="jsfcrud.currentResponders" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][responders.responders][responders.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentAdverts" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][responders.responders.adverts][adverts.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="responders"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.RespondersController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="User:"/>
                    <h:panelGroup>
                        <h:outputText value="#{responders.responders.user}"/>
                        <h:panelGroup rendered="#{responders.responders.user != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{user.detailSetup}">
                                <f:param name="jsfcrud.currentResponders" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][responders.responders][responders.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][responders.responders.user][user.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="responders"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.RespondersController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{user.editSetup}">
                                <f:param name="jsfcrud.currentResponders" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][responders.responders][responders.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][responders.responders.user][user.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="responders"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.RespondersController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{user.destroy}">
                                <f:param name="jsfcrud.currentResponders" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][responders.responders][responders.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][responders.responders.user][user.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="responders"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.RespondersController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>


                </h:panelGrid>
                <br />
                <h:commandLink action="#{responders.remove}" value="Destroy">
                    <f:param name="jsfcrud.currentResponders" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][responders.responders][responders.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{responders.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentResponders" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][responders.responders][responders.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{responders.createSetup}" value="New Responders" />
                <br />
                <h:commandLink action="#{responders.listSetup}" value="Show All Responders Items"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
