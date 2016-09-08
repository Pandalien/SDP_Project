<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Classification Items</title>
            <link rel="stylesheet" type="text/css" href="/CommunitySkillsSearch/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing Classification Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No Classification Items Found)<br />" rendered="#{classification.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{classification.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{classification.pagingInfo.firstItem + 1}..#{classification.pagingInfo.lastItem} of #{classification.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{classification.prev}" value="Previous #{classification.pagingInfo.batchSize}" rendered="#{classification.pagingInfo.firstItem >= classification.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{classification.next}" value="Next #{classification.pagingInfo.batchSize}" rendered="#{classification.pagingInfo.lastItem + classification.pagingInfo.batchSize <= classification.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{classification.next}" value="Remaining #{classification.pagingInfo.itemCount - classification.pagingInfo.lastItem}"
                                   rendered="#{classification.pagingInfo.lastItem < classification.pagingInfo.itemCount && classification.pagingInfo.lastItem + classification.pagingInfo.batchSize > classification.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{classification.classificationItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
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
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{classification.detailSetup}">
                                <f:param name="jsfcrud.currentClassification" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][classification.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{classification.editSetup}">
                                <f:param name="jsfcrud.currentClassification" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][classification.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{classification.remove}">
                                <f:param name="jsfcrud.currentClassification" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][classification.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>

                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{classification.createSetup}" value="New Classification"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />


            </h:form>
        </body>
    </html>
</f:view>
