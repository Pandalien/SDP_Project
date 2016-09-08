<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New UserSkills</title>
            <link rel="stylesheet" type="text/css" href="/CommunitySkillsSearch/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New UserSkills</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{userSkills.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="Level:"/>
                    <h:inputText id="level" value="#{userSkills.userSkills.level}" title="Level" />
                    <h:outputText value="Skills:"/>
                    <h:selectOneMenu id="skills" value="#{userSkills.userSkills.skills}" title="Skills" required="true" requiredMessage="The skills field is required." >
                        <f:selectItems value="#{skills.skillsItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                    <h:outputText value="User:"/>
                    <h:selectOneMenu id="user" value="#{userSkills.userSkills.user}" title="User" required="true" requiredMessage="The user field is required." >
                        <f:selectItems value="#{user.userItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{userSkills.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{userSkills.listSetup}" value="Show All UserSkills Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
