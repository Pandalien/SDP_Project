<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing UserSkills</title>
            <link rel="stylesheet" type="text/css" href="/CommunitySkillsSearch/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Editing UserSkills</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Level:"/>
                    <h:inputText id="level" value="#{userSkills.userSkills.level}" title="Level" />
                    <h:outputText value="Skills:"/>
                    <h:outputText value=" #{userSkills.userSkills.skills}" title="Skills" />
                    <h:outputText value="User:"/>
                    <h:outputText value=" #{userSkills.userSkills.user}" title="User" />

                </h:panelGrid>
                <br />
                <h:commandLink action="#{userSkills.edit}" value="Save">
                    <f:param name="jsfcrud.currentUserSkills" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][userSkills.userSkills][userSkills.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{userSkills.detailSetup}" value="Show" immediate="true">
                    <f:param name="jsfcrud.currentUserSkills" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][userSkills.userSkills][userSkills.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <h:commandLink action="#{userSkills.listSetup}" value="Show All UserSkills Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
