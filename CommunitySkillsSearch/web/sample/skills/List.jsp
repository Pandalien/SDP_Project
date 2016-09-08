<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Skills Items</title>
            <link rel="stylesheet" type="text/css" href="/CommunitySkillsSearch/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing Skills Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No Skills Items Found)<br />" rendered="#{skills.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{skills.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{skills.pagingInfo.firstItem + 1}..#{skills.pagingInfo.lastItem} of #{skills.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{skills.prev}" value="Previous #{skills.pagingInfo.batchSize}" rendered="#{skills.pagingInfo.firstItem >= skills.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{skills.next}" value="Next #{skills.pagingInfo.batchSize}" rendered="#{skills.pagingInfo.lastItem + skills.pagingInfo.batchSize <= skills.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{skills.next}" value="Remaining #{skills.pagingInfo.itemCount - skills.pagingInfo.lastItem}"
                                   rendered="#{skills.pagingInfo.lastItem < skills.pagingInfo.itemCount && skills.pagingInfo.lastItem + skills.pagingInfo.batchSize > skills.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{skills.skillsItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
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
                                <f:param name="jsfcrud.currentSkills" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][skills.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{skills.editSetup}">
                                <f:param name="jsfcrud.currentSkills" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][skills.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{skills.remove}">
                                <f:param name="jsfcrud.currentSkills" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][skills.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>

                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{skills.createSetup}" value="New Skills"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />


            </h:form>
        </body>
    </html>
</f:view>
