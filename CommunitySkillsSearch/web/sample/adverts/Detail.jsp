<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Adverts Detail</title>
            <link rel="stylesheet" type="text/css" href="/CommunitySkillsSearch/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Adverts Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Id:"/>
                    <h:outputText value="#{adverts.adverts.id}" title="Id" />
                    <h:outputText value="Title:"/>
                    <h:outputText value="#{adverts.adverts.title}" title="Title" />
                    <h:outputText value="Content:"/>
                    <h:outputText value="#{adverts.adverts.content}" title="Content" />
                    <h:outputText value="Advertscol:"/>
                    <h:outputText value="#{adverts.adverts.advertscol}" title="Advertscol" />
                    <h:outputText value="Closed:"/>
                    <h:outputText value="#{adverts.adverts.closed}" title="Closed" />
                    <h:outputText value="UserId:"/>
                    <h:panelGroup>
                        <h:outputText value="#{adverts.adverts.userId}"/>
                        <h:panelGroup rendered="#{adverts.adverts.userId != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{user.detailSetup}">
                                <f:param name="jsfcrud.currentAdverts" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][adverts.adverts][adverts.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][adverts.adverts.userId][user.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="adverts"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.AdvertsController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{user.editSetup}">
                                <f:param name="jsfcrud.currentAdverts" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][adverts.adverts][adverts.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][adverts.adverts.userId][user.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="adverts"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.AdvertsController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{user.destroy}">
                                <f:param name="jsfcrud.currentAdverts" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][adverts.adverts][adverts.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][adverts.adverts.userId][user.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="adverts"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.AdvertsController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>

                    <h:outputText value="SkillsCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty adverts.adverts.skillsCollection}" value="(No Items)"/>
                        <h:dataTable value="#{adverts.adverts.skillsCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty adverts.adverts.skillsCollection}">
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
                                    <f:param name="jsfcrud.currentAdverts" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][adverts.adverts][adverts.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentSkills" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][skills.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="adverts" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.AdvertsController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{skills.editSetup}">
                                    <f:param name="jsfcrud.currentAdverts" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][adverts.adverts][adverts.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentSkills" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][skills.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="adverts" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.AdvertsController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{skills.destroy}">
                                    <f:param name="jsfcrud.currentAdverts" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][adverts.adverts][adverts.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentSkills" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][skills.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="adverts" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.AdvertsController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>
                    <h:outputText value="RespondersCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty adverts.adverts.respondersCollection}" value="(No Items)"/>
                        <h:dataTable value="#{adverts.adverts.respondersCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty adverts.adverts.respondersCollection}">
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Message"/>
                                </f:facet>
                                <h:outputText value="#{item.message}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Time"/>
                                </f:facet>
                                <h:outputText value="#{item.time}">
                                    <f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
                                </h:outputText>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Feedback"/>
                                </f:facet>
                                <h:outputText value="#{item.feedback}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Rating"/>
                                </f:facet>
                                <h:outputText value="#{item.rating}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Status"/>
                                </f:facet>
                                <h:outputText value="#{item.status}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Adverts"/>
                                </f:facet>
                                <h:outputText value="#{item.adverts}"/>
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
                                <h:commandLink value="Show" action="#{responders.detailSetup}">
                                    <f:param name="jsfcrud.currentAdverts" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][adverts.adverts][adverts.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentResponders" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][responders.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="adverts" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.AdvertsController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{responders.editSetup}">
                                    <f:param name="jsfcrud.currentAdverts" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][adverts.adverts][adverts.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentResponders" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][responders.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="adverts" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.AdvertsController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{responders.destroy}">
                                    <f:param name="jsfcrud.currentAdverts" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][adverts.adverts][adverts.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentResponders" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][responders.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="adverts" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.AdvertsController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{adverts.remove}" value="Destroy">
                    <f:param name="jsfcrud.currentAdverts" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][adverts.adverts][adverts.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{adverts.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentAdverts" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][adverts.adverts][adverts.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{adverts.createSetup}" value="New Adverts" />
                <br />
                <h:commandLink action="#{adverts.listSetup}" value="Show All Adverts Items"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
