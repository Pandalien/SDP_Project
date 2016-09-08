<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Responders Items</title>
            <link rel="stylesheet" type="text/css" href="/CommunitySkillsSearch/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing Responders Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No Responders Items Found)<br />" rendered="#{responders.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{responders.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{responders.pagingInfo.firstItem + 1}..#{responders.pagingInfo.lastItem} of #{responders.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{responders.prev}" value="Previous #{responders.pagingInfo.batchSize}" rendered="#{responders.pagingInfo.firstItem >= responders.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{responders.next}" value="Next #{responders.pagingInfo.batchSize}" rendered="#{responders.pagingInfo.lastItem + responders.pagingInfo.batchSize <= responders.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{responders.next}" value="Remaining #{responders.pagingInfo.itemCount - responders.pagingInfo.lastItem}"
                                   rendered="#{responders.pagingInfo.lastItem < responders.pagingInfo.itemCount && responders.pagingInfo.lastItem + responders.pagingInfo.batchSize > responders.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{responders.respondersItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
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
                                <f:param name="jsfcrud.currentResponders" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][responders.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{responders.editSetup}">
                                <f:param name="jsfcrud.currentResponders" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][responders.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{responders.remove}">
                                <f:param name="jsfcrud.currentResponders" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][responders.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>

                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{responders.createSetup}" value="New Responders"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />


            </h:form>
        </body>
    </html>
</f:view>
