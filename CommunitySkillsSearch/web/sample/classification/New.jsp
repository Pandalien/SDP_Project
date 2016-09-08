<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New Classification</title>
            <link rel="stylesheet" type="text/css" href="/CommunitySkillsSearch/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New Classification</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{classification.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="Id:"/>
                    <h:inputText id="id" value="#{classification.classification.id}" title="Id" required="true" requiredMessage="The id field is required." />
                    <h:outputText value="Name:"/>
                    <h:inputText id="name" value="#{classification.classification.name}" title="Name" />
                    <h:outputText value="SkillsCollection:"/>
                    <h:selectManyListbox id="skillsCollection" value="#{classification.classification.jsfcrud_transform[jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method.collectionToArray][jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method.arrayToList].skillsCollection}" title="SkillsCollection" size="6" converter="#{skills.converter}" >
                        <f:selectItems value="#{skills.skillsItemsAvailableSelectMany}"/>
                    </h:selectManyListbox>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{classification.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{classification.listSetup}" value="Show All Classification Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
