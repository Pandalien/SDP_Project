<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Requirements Detail</title>
            <link rel="stylesheet" type="text/css" href="/WebApplication3/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Requirements Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Level:"/>
                    <h:outputText value="#{requirements.requirements.level}" title="Level" />
                    <h:outputText value="Adverts:"/>
                    <h:panelGroup>
                        <h:outputText value="#{requirements.requirements.adverts}"/>
                        <h:panelGroup rendered="#{requirements.requirements.adverts != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{adverts.detailSetup}">
                                <f:param name="jsfcrud.currentRequirements" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][requirements.requirements][requirements.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentAdverts" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][requirements.requirements.adverts][adverts.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="requirements"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.RequirementsController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{adverts.editSetup}">
                                <f:param name="jsfcrud.currentRequirements" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][requirements.requirements][requirements.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentAdverts" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][requirements.requirements.adverts][adverts.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="requirements"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.RequirementsController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{adverts.destroy}">
                                <f:param name="jsfcrud.currentRequirements" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][requirements.requirements][requirements.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentAdverts" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][requirements.requirements.adverts][adverts.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="requirements"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.RequirementsController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="Skills:"/>
                    <h:panelGroup>
                        <h:outputText value="#{requirements.requirements.skills}"/>
                        <h:panelGroup rendered="#{requirements.requirements.skills != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{skills.detailSetup}">
                                <f:param name="jsfcrud.currentRequirements" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][requirements.requirements][requirements.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentSkills" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][requirements.requirements.skills][skills.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="requirements"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.RequirementsController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{skills.editSetup}">
                                <f:param name="jsfcrud.currentRequirements" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][requirements.requirements][requirements.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentSkills" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][requirements.requirements.skills][skills.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="requirements"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.RequirementsController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{skills.destroy}">
                                <f:param name="jsfcrud.currentRequirements" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][requirements.requirements][requirements.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentSkills" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][requirements.requirements.skills][skills.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="requirements"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.RequirementsController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>


                </h:panelGrid>
                <br />
                <h:commandLink action="#{requirements.remove}" value="Destroy">
                    <f:param name="jsfcrud.currentRequirements" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][requirements.requirements][requirements.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{requirements.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentRequirements" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][requirements.requirements][requirements.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{requirements.createSetup}" value="New Requirements" />
                <br />
                <h:commandLink action="#{requirements.listSetup}" value="Show All Requirements Items"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
