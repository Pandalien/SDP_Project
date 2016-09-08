<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Messages Items</title>
            <link rel="stylesheet" type="text/css" href="/CommunitySkillsSearch/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing Messages Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No Messages Items Found)<br />" rendered="#{messages.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{messages.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{messages.pagingInfo.firstItem + 1}..#{messages.pagingInfo.lastItem} of #{messages.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{messages.prev}" value="Previous #{messages.pagingInfo.batchSize}" rendered="#{messages.pagingInfo.firstItem >= messages.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{messages.next}" value="Next #{messages.pagingInfo.batchSize}" rendered="#{messages.pagingInfo.lastItem + messages.pagingInfo.batchSize <= messages.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{messages.next}" value="Remaining #{messages.pagingInfo.itemCount - messages.pagingInfo.lastItem}"
                                   rendered="#{messages.pagingInfo.lastItem < messages.pagingInfo.itemCount && messages.pagingInfo.lastItem + messages.pagingInfo.batchSize > messages.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{messages.messagesItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Id"/>
                            </f:facet>
                            <h:outputText value="#{item.messagesPK.id}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Content"/>
                            </f:facet>
                            <h:outputText value="#{item.content}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Read"/>
                            </f:facet>
                            <h:outputText value="#{item.read}"/>
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
                                <h:outputText value="User"/>
                            </f:facet>
                            <h:outputText value="#{item.user}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="User1"/>
                            </f:facet>
                            <h:outputText value="#{item.user1}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{messages.detailSetup}">
                                <f:param name="jsfcrud.currentMessages" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][messages.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{messages.editSetup}">
                                <f:param name="jsfcrud.currentMessages" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][messages.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{messages.remove}">
                                <f:param name="jsfcrud.currentMessages" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][messages.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>

                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{messages.createSetup}" value="New Messages"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />


            </h:form>
        </body>
    </html>
</f:view>
