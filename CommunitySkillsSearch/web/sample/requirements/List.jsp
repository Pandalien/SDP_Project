<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Requirements Items</title>
            <link rel="stylesheet" type="text/css" href="/WebApplication3/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing Requirements Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No Requirements Items Found)<br />" rendered="#{requirements.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{requirements.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{requirements.pagingInfo.firstItem + 1}..#{requirements.pagingInfo.lastItem} of #{requirements.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{requirements.prev}" value="Previous #{requirements.pagingInfo.batchSize}" rendered="#{requirements.pagingInfo.firstItem >= requirements.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{requirements.next}" value="Next #{requirements.pagingInfo.batchSize}" rendered="#{requirements.pagingInfo.lastItem + requirements.pagingInfo.batchSize <= requirements.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{requirements.next}" value="Remaining #{requirements.pagingInfo.itemCount - requirements.pagingInfo.lastItem}"
                                   rendered="#{requirements.pagingInfo.lastItem < requirements.pagingInfo.itemCount && requirements.pagingInfo.lastItem + requirements.pagingInfo.batchSize > requirements.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{requirements.requirementsItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Level"/>
                            </f:facet>
                            <h:outputText value="#{item.level}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Adverts"/>
                            </f:facet>
                            <h:outputText value="#{item.adverts}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Skills"/>
                            </f:facet>
                            <h:outputText value="#{item.skills}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{requirements.detailSetup}">
                                <f:param name="jsfcrud.currentRequirements" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][requirements.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{requirements.editSetup}">
                                <f:param name="jsfcrud.currentRequirements" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][requirements.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{requirements.remove}">
                                <f:param name="jsfcrud.currentRequirements" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][requirements.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>

                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{requirements.createSetup}" value="New Requirements"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />


            </h:form>
        </body>
    </html>
</f:view>
