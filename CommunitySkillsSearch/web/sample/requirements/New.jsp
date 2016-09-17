<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New Requirements</title>
            <link rel="stylesheet" type="text/css" href="/WebApplication3/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New Requirements</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{requirements.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="Level:"/>
                    <h:inputText id="level" value="#{requirements.requirements.level}" title="Level" />
                    <h:outputText value="Adverts:"/>
                    <h:selectOneMenu id="adverts" value="#{requirements.requirements.adverts}" title="Adverts" required="true" requiredMessage="The adverts field is required." >
                        <f:selectItems value="#{adverts.advertsItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                    <h:outputText value="Skills:"/>
                    <h:selectOneMenu id="skills" value="#{requirements.requirements.skills}" title="Skills" required="true" requiredMessage="The skills field is required." >
                        <f:selectItems value="#{skills.skillsItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{requirements.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{requirements.listSetup}" value="Show All Requirements Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
