<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>UserSkills Detail</title>
            <link rel="stylesheet" type="text/css" href="/CommunitySkillsSearch/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>UserSkills Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Level:"/>
                    <h:outputText value="#{userSkills.userSkills.level}" title="Level" />
                    <h:outputText value="Skills:"/>
                    <h:panelGroup>
                        <h:outputText value="#{userSkills.userSkills.skills}"/>
                        <h:panelGroup rendered="#{userSkills.userSkills.skills != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{skills.detailSetup}">
                                <f:param name="jsfcrud.currentUserSkills" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][userSkills.userSkills][userSkills.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentSkills" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][userSkills.userSkills.skills][skills.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="userSkills"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.UserSkillsController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{skills.editSetup}">
                                <f:param name="jsfcrud.currentUserSkills" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][userSkills.userSkills][userSkills.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentSkills" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][userSkills.userSkills.skills][skills.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="userSkills"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.UserSkillsController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{skills.destroy}">
                                <f:param name="jsfcrud.currentUserSkills" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][userSkills.userSkills][userSkills.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentSkills" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][userSkills.userSkills.skills][skills.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="userSkills"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.UserSkillsController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="User:"/>
                    <h:panelGroup>
                        <h:outputText value="#{userSkills.userSkills.user}"/>
                        <h:panelGroup rendered="#{userSkills.userSkills.user != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{user.detailSetup}">
                                <f:param name="jsfcrud.currentUserSkills" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][userSkills.userSkills][userSkills.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][userSkills.userSkills.user][user.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="userSkills"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.UserSkillsController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{user.editSetup}">
                                <f:param name="jsfcrud.currentUserSkills" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][userSkills.userSkills][userSkills.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][userSkills.userSkills.user][user.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="userSkills"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.UserSkillsController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{user.destroy}">
                                <f:param name="jsfcrud.currentUserSkills" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][userSkills.userSkills][userSkills.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][userSkills.userSkills.user][user.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="userSkills"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.UserSkillsController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>


                </h:panelGrid>
                <br />
                <h:commandLink action="#{userSkills.remove}" value="Destroy">
                    <f:param name="jsfcrud.currentUserSkills" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][userSkills.userSkills][userSkills.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{userSkills.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentUserSkills" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][userSkills.userSkills][userSkills.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{userSkills.createSetup}" value="New UserSkills" />
                <br />
                <h:commandLink action="#{userSkills.listSetup}" value="Show All UserSkills Items"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
