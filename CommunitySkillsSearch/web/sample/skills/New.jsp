<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New Skills</title>
            <link rel="stylesheet" type="text/css" href="/CommunitySkillsSearch/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New Skills</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{skills.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="Name:"/>
                    <h:inputText id="name" value="#{skills.skills.name}" title="Name" />
                    <h:outputText value="Description:"/>
                    <h:inputText id="description" value="#{skills.skills.description}" title="Description" />
                    <h:outputText value="AdvertsCollection:"/>
                    <h:selectManyListbox id="advertsCollection" value="#{skills.skills.jsfcrud_transform[jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method.collectionToArray][jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method.arrayToList].advertsCollection}" title="AdvertsCollection" size="6" converter="#{adverts.converter}" >
                        <f:selectItems value="#{adverts.advertsItemsAvailableSelectMany}"/>
                    </h:selectManyListbox>
                    <h:outputText value="ClassificationId:"/>
                    <h:selectOneMenu id="classificationId" value="#{skills.skills.classificationId}" title="ClassificationId" required="true" requiredMessage="The classificationId field is required." >
                        <f:selectItems value="#{classification.classificationItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                    <h:outputText value="UserSkillsCollection:"/>
                    <h:outputText escape="false" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getCollectionAsString'][skills.skills.userSkillsCollection == null ? jsfcrud_null : skills.skills.userSkillsCollection].jsfcrud_invoke}" title="UserSkillsCollection" />

                </h:panelGrid>
                <br />
                <h:commandLink action="#{skills.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{skills.listSetup}" value="Show All Skills Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
