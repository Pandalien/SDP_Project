<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing Requirements</title>
            <link rel="stylesheet" type="text/css" href="/WebApplication3/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Editing Requirements</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Level:"/>
                    <h:inputText id="level" value="#{requirements.requirements.level}" title="Level" />
                    <h:outputText value="Adverts:"/>
                    <h:outputText value=" #{requirements.requirements.adverts}" title="Adverts" />
                    <h:outputText value="Skills:"/>
                    <h:outputText value=" #{requirements.requirements.skills}" title="Skills" />

                </h:panelGrid>
                <br />
                <h:commandLink action="#{requirements.edit}" value="Save">
                    <f:param name="jsfcrud.currentRequirements" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][requirements.requirements][requirements.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{requirements.detailSetup}" value="Show" immediate="true">
                    <f:param name="jsfcrud.currentRequirements" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][requirements.requirements][requirements.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <h:commandLink action="#{requirements.listSetup}" value="Show All Requirements Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
