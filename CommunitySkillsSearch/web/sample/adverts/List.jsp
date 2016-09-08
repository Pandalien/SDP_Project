<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Adverts Items</title>
            <link rel="stylesheet" type="text/css" href="/CommunitySkillsSearch/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing Adverts Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No Adverts Items Found)<br />" rendered="#{adverts.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{adverts.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{adverts.pagingInfo.firstItem + 1}..#{adverts.pagingInfo.lastItem} of #{adverts.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{adverts.prev}" value="Previous #{adverts.pagingInfo.batchSize}" rendered="#{adverts.pagingInfo.firstItem >= adverts.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{adverts.next}" value="Next #{adverts.pagingInfo.batchSize}" rendered="#{adverts.pagingInfo.lastItem + adverts.pagingInfo.batchSize <= adverts.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{adverts.next}" value="Remaining #{adverts.pagingInfo.itemCount - adverts.pagingInfo.lastItem}"
                                   rendered="#{adverts.pagingInfo.lastItem < adverts.pagingInfo.itemCount && adverts.pagingInfo.lastItem + adverts.pagingInfo.batchSize > adverts.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{adverts.advertsItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
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
                                <f:param name="jsfcrud.currentAdverts" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][adverts.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{adverts.editSetup}">
                                <f:param name="jsfcrud.currentAdverts" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][adverts.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{adverts.remove}">
                                <f:param name="jsfcrud.currentAdverts" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][adverts.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>

                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{adverts.createSetup}" value="New Adverts"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />


            </h:form>
        </body>
    </html>
</f:view>
