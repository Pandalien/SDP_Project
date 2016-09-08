<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New Adverts</title>
            <link rel="stylesheet" type="text/css" href="/CommunitySkillsSearch/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New Adverts</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{adverts.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="Title:"/>
                    <h:inputText id="title" value="#{adverts.adverts.title}" title="Title" required="true" requiredMessage="The title field is required." />
                    <h:outputText value="Content:"/>
                    <h:inputText id="content" value="#{adverts.adverts.content}" title="Content" required="true" requiredMessage="The content field is required." />
                    <h:outputText value="Advertscol:"/>
                    <h:inputText id="advertscol" value="#{adverts.adverts.advertscol}" title="Advertscol" />
                    <h:outputText value="Closed:"/>
                    <h:inputText id="closed" value="#{adverts.adverts.closed}" title="Closed" />
                    <h:outputText value="SkillsCollection:"/>
                    <h:selectManyListbox id="skillsCollection" value="#{adverts.adverts.jsfcrud_transform[jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method.collectionToArray][jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method.arrayToList].skillsCollection}" title="SkillsCollection" size="6" converter="#{skills.converter}" >
                        <f:selectItems value="#{skills.skillsItemsAvailableSelectMany}"/>
                    </h:selectManyListbox>
                    <h:outputText value="UserId:"/>
                    <h:selectOneMenu id="userId" value="#{adverts.adverts.userId}" title="UserId" required="true" requiredMessage="The userId field is required." >
                        <f:selectItems value="#{user.userItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                    <h:outputText value="RespondersCollection:"/>
                    <h:outputText escape="false" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getCollectionAsString'][adverts.adverts.respondersCollection == null ? jsfcrud_null : adverts.adverts.respondersCollection].jsfcrud_invoke}" title="RespondersCollection" />

                </h:panelGrid>
                <br />
                <h:commandLink action="#{adverts.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{adverts.listSetup}" value="Show All Adverts Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
