<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Suburb Items</title>
            <link rel="stylesheet" type="text/css" href="/CommunitySkillsSearch/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing Suburb Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No Suburb Items Found)<br />" rendered="#{suburb.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{suburb.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{suburb.pagingInfo.firstItem + 1}..#{suburb.pagingInfo.lastItem} of #{suburb.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{suburb.prev}" value="Previous #{suburb.pagingInfo.batchSize}" rendered="#{suburb.pagingInfo.firstItem >= suburb.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{suburb.next}" value="Next #{suburb.pagingInfo.batchSize}" rendered="#{suburb.pagingInfo.lastItem + suburb.pagingInfo.batchSize <= suburb.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{suburb.next}" value="Remaining #{suburb.pagingInfo.itemCount - suburb.pagingInfo.lastItem}"
                                   rendered="#{suburb.pagingInfo.lastItem < suburb.pagingInfo.itemCount && suburb.pagingInfo.lastItem + suburb.pagingInfo.batchSize > suburb.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{suburb.suburbItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Id"/>
                            </f:facet>
                            <h:outputText value="#{item.id}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Suburb"/>
                            </f:facet>
                            <h:outputText value="#{item.suburb}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{suburb.detailSetup}">
                                <f:param name="jsfcrud.currentSuburb" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][suburb.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{suburb.editSetup}">
                                <f:param name="jsfcrud.currentSuburb" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][suburb.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{suburb.remove}">
                                <f:param name="jsfcrud.currentSuburb" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][suburb.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>

                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{suburb.createSetup}" value="New Suburb"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />


            </h:form>
        </body>
    </html>
</f:view>
