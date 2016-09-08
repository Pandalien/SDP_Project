<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Classification Detail</title>
            <link rel="stylesheet" type="text/css" href="/CommunitySkillsSearch/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Classification Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Id:"/>
                    <h:outputText value="#{classification.classification.id}" title="Id" />
                    <h:outputText value="Name:"/>
                    <h:outputText value="#{classification.classification.name}" title="Name" />

                    <h:outputText value="SkillsCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty classification.classification.skillsCollection}" value="(No Items)"/>
                        <h:dataTable value="#{classification.classification.skillsCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty classification.classification.skillsCollection}">
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Id"/>
                                </f:facet>
                                <h:outputText value="#{item.id}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Name"/>
                                </f:facet>
                                <h:outputText value="#{item.name}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Description"/>
                                </f:facet>
                                <h:outputText value="#{item.description}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="ClassificationId"/>
                                </f:facet>
                                <h:outputText value="#{item.classificationId}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText escape="false" value="&nbsp;"/>
                                </f:facet>
                                <h:commandLink value="Show" action="#{skills.detailSetup}">
                                    <f:param name="jsfcrud.currentClassification" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][classification.classification][classification.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentSkills" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][skills.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="classification" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.ClassificationController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{skills.editSetup}">
                                    <f:param name="jsfcrud.currentClassification" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][classification.classification][classification.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentSkills" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][skills.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="classification" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.ClassificationController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{skills.destroy}">
                                    <f:param name="jsfcrud.currentClassification" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][classification.classification][classification.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentSkills" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][skills.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="classification" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.ClassificationController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{classification.remove}" value="Destroy">
                    <f:param name="jsfcrud.currentClassification" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][classification.classification][classification.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{classification.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentClassification" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][classification.classification][classification.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{classification.createSetup}" value="New Classification" />
                <br />
                <h:commandLink action="#{classification.listSetup}" value="Show All Classification Items"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
