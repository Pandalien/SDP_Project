<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Skills Detail</title>
            <link rel="stylesheet" type="text/css" href="/CommunitySkillsSearch/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Skills Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Id:"/>
                    <h:outputText value="#{skills.skills.id}" title="Id" />
                    <h:outputText value="Name:"/>
                    <h:outputText value="#{skills.skills.name}" title="Name" />
                    <h:outputText value="Description:"/>
                    <h:outputText value="#{skills.skills.description}" title="Description" />
                    <h:outputText value="ClassificationId:"/>
                    <h:panelGroup>
                        <h:outputText value="#{skills.skills.classificationId}"/>
                        <h:panelGroup rendered="#{skills.skills.classificationId != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{classification.detailSetup}">
                                <f:param name="jsfcrud.currentSkills" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][skills.skills][skills.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentClassification" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][skills.skills.classificationId][classification.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="skills"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.SkillsController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{classification.editSetup}">
                                <f:param name="jsfcrud.currentSkills" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][skills.skills][skills.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentClassification" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][skills.skills.classificationId][classification.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="skills"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.SkillsController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{classification.destroy}">
                                <f:param name="jsfcrud.currentSkills" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][skills.skills][skills.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentClassification" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][skills.skills.classificationId][classification.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="skills"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.SkillsController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>

                    <h:outputText value="AdvertsCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty skills.skills.advertsCollection}" value="(No Items)"/>
                        <h:dataTable value="#{skills.skills.advertsCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty skills.skills.advertsCollection}">
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Id"/>
                                </f:facet>
                                <h:outputText value="#{item.id}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Title"/>
                                </f:facet>
                                <h:outputText value="#{item.title}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Content"/>
                                </f:facet>
                                <h:outputText value="#{item.content}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Advertscol"/>
                                </f:facet>
                                <h:outputText value="#{item.advertscol}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Closed"/>
                                </f:facet>
                                <h:outputText value="#{item.closed}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="UserId"/>
                                </f:facet>
                                <h:outputText value="#{item.userId}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText escape="false" value="&nbsp;"/>
                                </f:facet>
                                <h:commandLink value="Show" action="#{adverts.detailSetup}">
                                    <f:param name="jsfcrud.currentSkills" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][skills.skills][skills.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentAdverts" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][adverts.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="skills" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.SkillsController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{adverts.editSetup}">
                                    <f:param name="jsfcrud.currentSkills" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][skills.skills][skills.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentAdverts" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][adverts.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="skills" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.SkillsController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{adverts.destroy}">
                                    <f:param name="jsfcrud.currentSkills" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][skills.skills][skills.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentAdverts" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][adverts.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="skills" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.SkillsController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>
                    <h:outputText value="UserSkillsCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty skills.skills.userSkillsCollection}" value="(No Items)"/>
                        <h:dataTable value="#{skills.skills.userSkillsCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty skills.skills.userSkillsCollection}">
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Level"/>
                                </f:facet>
                                <h:outputText value="#{item.level}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Skills"/>
                                </f:facet>
                                <h:outputText value="#{item.skills}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="User"/>
                                </f:facet>
                                <h:outputText value="#{item.user}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText escape="false" value="&nbsp;"/>
                                </f:facet>
                                <h:commandLink value="Show" action="#{userSkills.detailSetup}">
                                    <f:param name="jsfcrud.currentSkills" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][skills.skills][skills.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentUserSkills" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][userSkills.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="skills" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.SkillsController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{userSkills.editSetup}">
                                    <f:param name="jsfcrud.currentSkills" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][skills.skills][skills.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentUserSkills" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][userSkills.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="skills" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.SkillsController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{userSkills.destroy}">
                                    <f:param name="jsfcrud.currentSkills" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][skills.skills][skills.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentUserSkills" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][userSkills.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="skills" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.SkillsController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{skills.remove}" value="Destroy">
                    <f:param name="jsfcrud.currentSkills" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][skills.skills][skills.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{skills.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentSkills" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][skills.skills][skills.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{skills.createSetup}" value="New Skills" />
                <br />
                <h:commandLink action="#{skills.listSetup}" value="Show All Skills Items"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
