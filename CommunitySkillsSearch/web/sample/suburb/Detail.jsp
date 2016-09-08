<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Suburb Detail</title>
            <link rel="stylesheet" type="text/css" href="/CommunitySkillsSearch/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Suburb Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Id:"/>
                    <h:outputText value="#{suburb.suburb.id}" title="Id" />
                    <h:outputText value="Suburb:"/>
                    <h:outputText value="#{suburb.suburb.suburb}" title="Suburb" />

                    <h:outputText value="UserCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty suburb.suburb.userCollection}" value="(No Items)"/>
                        <h:dataTable value="#{suburb.suburb.userCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty suburb.suburb.userCollection}">
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
                                    <h:outputText value="Password"/>
                                </f:facet>
                                <h:outputText value="#{item.password}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="JoinedDate"/>
                                </f:facet>
                                <h:outputText value="#{item.joinedDate}">
                                    <f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
                                </h:outputText>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Gender"/>
                                </f:facet>
                                <h:outputText value="#{item.gender}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Visible"/>
                                </f:facet>
                                <h:outputText value="#{item.visible}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Introduction"/>
                                </f:facet>
                                <h:outputText value="#{item.introduction}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Email"/>
                                </f:facet>
                                <h:outputText value="#{item.email}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Dob"/>
                                </f:facet>
                                <h:outputText value="#{item.dob}">
                                    <f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
                                </h:outputText>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Rating"/>
                                </f:facet>
                                <h:outputText value="#{item.rating}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Phone"/>
                                </f:facet>
                                <h:outputText value="#{item.phone}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="SuburbId"/>
                                </f:facet>
                                <h:outputText value="#{item.suburbId}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText escape="false" value="&nbsp;"/>
                                </f:facet>
                                <h:commandLink value="Show" action="#{user.detailSetup}">
                                    <f:param name="jsfcrud.currentSuburb" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][suburb.suburb][suburb.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][user.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="suburb" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.SuburbController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{user.editSetup}">
                                    <f:param name="jsfcrud.currentSuburb" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][suburb.suburb][suburb.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][user.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="suburb" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.SuburbController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{user.destroy}">
                                    <f:param name="jsfcrud.currentSuburb" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][suburb.suburb][suburb.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][user.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="suburb" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.SuburbController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{suburb.remove}" value="Destroy">
                    <f:param name="jsfcrud.currentSuburb" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][suburb.suburb][suburb.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{suburb.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentSuburb" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][suburb.suburb][suburb.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{suburb.createSetup}" value="New Suburb" />
                <br />
                <h:commandLink action="#{suburb.listSetup}" value="Show All Suburb Items"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
