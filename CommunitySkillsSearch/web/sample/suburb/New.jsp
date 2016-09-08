<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New Suburb</title>
            <link rel="stylesheet" type="text/css" href="/CommunitySkillsSearch/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New Suburb</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{suburb.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="Id:"/>
                    <h:inputText id="id" value="#{suburb.suburb.id}" title="Id" required="true" requiredMessage="The id field is required." />
                    <h:outputText value="Suburb:"/>
                    <h:inputText id="suburb" value="#{suburb.suburb.suburb}" title="Suburb" required="true" requiredMessage="The suburb field is required." />
                    <h:outputText value="UserCollection:"/>
                    <h:selectManyListbox id="userCollection" value="#{suburb.suburb.jsfcrud_transform[jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method.collectionToArray][jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method.arrayToList].userCollection}" title="UserCollection" size="6" converter="#{user.converter}" >
                        <f:selectItems value="#{user.userItemsAvailableSelectMany}"/>
                    </h:selectManyListbox>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{suburb.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{suburb.listSetup}" value="Show All Suburb Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
