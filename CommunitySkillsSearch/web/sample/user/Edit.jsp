<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing User</title>
            <link rel="stylesheet" type="text/css" href="/CommunitySkillsSearch/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Editing User</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Id:"/>
                    <h:outputText value="#{user.user.id}" title="Id" />
                    <h:outputText value="Name:"/>
                    <h:inputText id="name" value="#{user.user.name}" title="Name" required="true" requiredMessage="The name field is required." />
                    <h:outputText value="Password:"/>
                    <h:inputText id="password" value="#{user.user.password}" title="Password" required="true" requiredMessage="The password field is required." />
                    <h:outputText value="JoinedDate (MM/dd/yyyy HH:mm:ss):"/>
                    <h:inputText id="joinedDate" value="#{user.user.joinedDate}" title="JoinedDate" required="true" requiredMessage="The joinedDate field is required." >
                        <f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
                    </h:inputText>
                    <h:outputText value="Gender:"/>
                    <h:inputText id="gender" value="#{user.user.gender}" title="Gender" />
                    <h:outputText value="Visible:"/>
                    <h:inputText id="visible" value="#{user.user.visible}" title="Visible" />
                    <h:outputText value="Introduction:"/>
                    <h:inputText id="introduction" value="#{user.user.introduction}" title="Introduction" />
                    <h:outputText value="Email:"/>
                    <h:inputText id="email" value="#{user.user.email}" title="Email" required="true" requiredMessage="The email field is required." />
                    <h:outputText value="Dob (MM/dd/yyyy HH:mm:ss):"/>
                    <h:inputText id="dob" value="#{user.user.dob}" title="Dob" >
                        <f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
                    </h:inputText>
                    <h:outputText value="Rating:"/>
                    <h:inputText id="rating" value="#{user.user.rating}" title="Rating" />
                    <h:outputText value="Phone:"/>
                    <h:inputText id="phone" value="#{user.user.phone}" title="Phone" />
                    <h:outputText value="AdvertsCollection:"/>
                    <h:selectManyListbox id="advertsCollection" value="#{user.user.jsfcrud_transform[jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method.collectionToArray][jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method.arrayToList].advertsCollection}" title="AdvertsCollection" size="6" converter="#{adverts.converter}" >
                        <f:selectItems value="#{adverts.advertsItemsAvailableSelectMany}"/>
                    </h:selectManyListbox>
                    <h:outputText value="MessagesCollection:"/>
                    <h:outputText escape="false" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getCollectionAsString'][user.user.messagesCollection == null ? jsfcrud_null : user.user.messagesCollection].jsfcrud_invoke}" title="MessagesCollection" />
                    <h:outputText value="MessagesCollection1:"/>
                    <h:outputText escape="false" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getCollectionAsString'][user.user.messagesCollection1 == null ? jsfcrud_null : user.user.messagesCollection1].jsfcrud_invoke}" title="MessagesCollection1" />
                    <h:outputText value="RespondersCollection:"/>
                    <h:outputText escape="false" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getCollectionAsString'][user.user.respondersCollection == null ? jsfcrud_null : user.user.respondersCollection].jsfcrud_invoke}" title="RespondersCollection" />
                    <h:outputText value="SuburbId:"/>
                    <h:selectOneMenu id="suburbId" value="#{user.user.suburbId}" title="SuburbId" required="true" requiredMessage="The suburbId field is required." >
                        <f:selectItems value="#{suburb.suburbItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                    <h:outputText value="UserSkillsCollection:"/>
                    <h:outputText escape="false" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getCollectionAsString'][user.user.userSkillsCollection == null ? jsfcrud_null : user.user.userSkillsCollection].jsfcrud_invoke}" title="UserSkillsCollection" />

                </h:panelGrid>
                <br />
                <h:commandLink action="#{user.edit}" value="Save">
                    <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][user.user][user.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{user.detailSetup}" value="Show" immediate="true">
                    <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][user.user][user.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <h:commandLink action="#{user.listSetup}" value="Show All User Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
