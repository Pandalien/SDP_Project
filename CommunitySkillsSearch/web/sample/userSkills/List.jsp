<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing UserSkills Items</title>
            <link rel="stylesheet" type="text/css" href="/CommunitySkillsSearch/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing UserSkills Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No UserSkills Items Found)<br />" rendered="#{userSkills.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{userSkills.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{userSkills.pagingInfo.firstItem + 1}..#{userSkills.pagingInfo.lastItem} of #{userSkills.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{userSkills.prev}" value="Previous #{userSkills.pagingInfo.batchSize}" rendered="#{userSkills.pagingInfo.firstItem >= userSkills.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{userSkills.next}" value="Next #{userSkills.pagingInfo.batchSize}" rendered="#{userSkills.pagingInfo.lastItem + userSkills.pagingInfo.batchSize <= userSkills.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{userSkills.next}" value="Remaining #{userSkills.pagingInfo.itemCount - userSkills.pagingInfo.lastItem}"
                                   rendered="#{userSkills.pagingInfo.lastItem < userSkills.pagingInfo.itemCount && userSkills.pagingInfo.lastItem + userSkills.pagingInfo.batchSize > userSkills.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{userSkills.userSkillsItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
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
                                <f:param name="jsfcrud.currentUserSkills" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][userSkills.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{userSkills.editSetup}">
                                <f:param name="jsfcrud.currentUserSkills" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][userSkills.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{userSkills.remove}">
                                <f:param name="jsfcrud.currentUserSkills" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][userSkills.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>

                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{userSkills.createSetup}" value="New UserSkills"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />


            </h:form>
        </body>
    </html>
</f:view>
