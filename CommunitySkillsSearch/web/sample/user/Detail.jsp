<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>User Detail</title>
            <link rel="stylesheet" type="text/css" href="/CommunitySkillsSearch/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>User Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Id:"/>
                    <h:outputText value="#{user.user.id}" title="Id" />
                    <h:outputText value="Name:"/>
                    <h:outputText value="#{user.user.name}" title="Name" />
                    <h:outputText value="Password:"/>
                    <h:outputText value="#{user.user.password}" title="Password" />
                    <h:outputText value="JoinedDate:"/>
                    <h:outputText value="#{user.user.joinedDate}" title="JoinedDate" >
                        <f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
                    </h:outputText>
                    <h:outputText value="Gender:"/>
                    <h:outputText value="#{user.user.gender}" title="Gender" />
                    <h:outputText value="Visible:"/>
                    <h:outputText value="#{user.user.visible}" title="Visible" />
                    <h:outputText value="Introduction:"/>
                    <h:outputText value="#{user.user.introduction}" title="Introduction" />
                    <h:outputText value="Email:"/>
                    <h:outputText value="#{user.user.email}" title="Email" />
                    <h:outputText value="Dob:"/>
                    <h:outputText value="#{user.user.dob}" title="Dob" >
                        <f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
                    </h:outputText>
                    <h:outputText value="Rating:"/>
                    <h:outputText value="#{user.user.rating}" title="Rating" />
                    <h:outputText value="Phone:"/>
                    <h:outputText value="#{user.user.phone}" title="Phone" />
                    <h:outputText value="SuburbId:"/>
                    <h:panelGroup>
                        <h:outputText value="#{user.user.suburbId}"/>
                        <h:panelGroup rendered="#{user.user.suburbId != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{suburb.detailSetup}">
                                <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][user.user][user.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentSuburb" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][user.user.suburbId][suburb.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="user"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.UserController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{suburb.editSetup}">
                                <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][user.user][user.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentSuburb" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][user.user.suburbId][suburb.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="user"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.UserController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{suburb.destroy}">
                                <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][user.user][user.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentSuburb" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][user.user.suburbId][suburb.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="user"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.UserController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>

                    <h:outputText value="AdvertsCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty user.user.advertsCollection}" value="(No Items)"/>
                        <h:dataTable value="#{user.user.advertsCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty user.user.advertsCollection}">
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Id"/>
                                </f:facet>
                                <h:outputText value="#{item.id}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Title"/>
                                </f:facet>
                                <h:outputText value="#{item.title}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Content"/>
                                </f:facet>
                                <h:outputText value="#{item.content}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Advertscol"/>
                                </f:facet>
                                <h:outputText value="#{item.advertscol}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Closed"/>
                                </f:facet>
                                <h:outputText value="#{item.closed}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="UserId"/>
                                </f:facet>
                                <h:outputText value="#{item.userId}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText escape="false" value="&nbsp;"/>
                                </f:facet>
                                <h:commandLink value="Show" action="#{adverts.detailSetup}">
                                    <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][user.user][user.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentAdverts" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][adverts.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="user" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.UserController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{adverts.editSetup}">
                                    <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][user.user][user.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentAdverts" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][adverts.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="user" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.UserController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{adverts.destroy}">
                                    <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][user.user][user.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentAdverts" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][adverts.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="user" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.UserController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>
                    <h:outputText value="MessagesCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty user.user.messagesCollection}" value="(No Items)"/>
                        <h:dataTable value="#{user.user.messagesCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty user.user.messagesCollection}">
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Id"/>
                                </f:facet>
                                <h:outputText value="#{item.messagesPK.id}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Content"/>
                                </f:facet>
                                <h:outputText value="#{item.content}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Read"/>
                                </f:facet>
                                <h:outputText value="#{item.read}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Time"/>
                                </f:facet>
                                <h:outputText value="#{item.time}">
                                    <f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
                                </h:outputText>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="User"/>
                                </f:facet>
                                <h:outputText value="#{item.user}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="User1"/>
                                </f:facet>
                                <h:outputText value="#{item.user1}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText escape="false" value="&nbsp;"/>
                                </f:facet>
                                <h:commandLink value="Show" action="#{messages.detailSetup}">
                                    <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][user.user][user.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentMessages" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][messages.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="user" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.UserController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{messages.editSetup}">
                                    <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][user.user][user.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentMessages" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][messages.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="user" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.UserController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{messages.destroy}">
                                    <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][user.user][user.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentMessages" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][messages.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="user" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.UserController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>
                    <h:outputText value="MessagesCollection1:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty user.user.messagesCollection1}" value="(No Items)"/>
                        <h:dataTable value="#{user.user.messagesCollection1}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty user.user.messagesCollection1}">
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Id"/>
                                </f:facet>
                                <h:outputText value="#{item.messagesPK.id}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Content"/>
                                </f:facet>
                                <h:outputText value="#{item.content}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Read"/>
                                </f:facet>
                                <h:outputText value="#{item.read}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Time"/>
                                </f:facet>
                                <h:outputText value="#{item.time}">
                                    <f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
                                </h:outputText>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="User"/>
                                </f:facet>
                                <h:outputText value="#{item.user}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="User1"/>
                                </f:facet>
                                <h:outputText value="#{item.user1}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText escape="false" value="&nbsp;"/>
                                </f:facet>
                                <h:commandLink value="Show" action="#{messages.detailSetup}">
                                    <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][user.user][user.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentMessages" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][messages.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="user" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.UserController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{messages.editSetup}">
                                    <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][user.user][user.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentMessages" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][messages.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="user" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.UserController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{messages.destroy}">
                                    <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][user.user][user.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentMessages" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][messages.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="user" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.UserController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>
                    <h:outputText value="RespondersCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty user.user.respondersCollection}" value="(No Items)"/>
                        <h:dataTable value="#{user.user.respondersCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty user.user.respondersCollection}">
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Message"/>
                                </f:facet>
                                <h:outputText value="#{item.message}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Time"/>
                                </f:facet>
                                <h:outputText value="#{item.time}">
                                    <f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
                                </h:outputText>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Feedback"/>
                                </f:facet>
                                <h:outputText value="#{item.feedback}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Rating"/>
                                </f:facet>
                                <h:outputText value="#{item.rating}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Status"/>
                                </f:facet>
                                <h:outputText value="#{item.status}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Adverts"/>
                                </f:facet>
                                <h:outputText value="#{item.adverts}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="User"/>
                                </f:facet>
                                <h:outputText value="#{item.user}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText escape="false" value="&nbsp;"/>
                                </f:facet>
                                <h:commandLink value="Show" action="#{responders.detailSetup}">
                                    <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][user.user][user.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentResponders" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][responders.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="user" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.UserController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{responders.editSetup}">
                                    <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][user.user][user.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentResponders" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][responders.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="user" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.UserController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{responders.destroy}">
                                    <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][user.user][user.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentResponders" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][responders.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="user" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.UserController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>
                    <h:outputText value="UserSkillsCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty user.user.userSkillsCollection}" value="(No Items)"/>
                        <h:dataTable value="#{user.user.userSkillsCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty user.user.userSkillsCollection}">
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Level"/>
                                </f:facet>
                                <h:outputText value="#{item.level}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Skills"/>
                                </f:facet>
                                <h:outputText value="#{item.skills}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="User"/>
                                </f:facet>
                                <h:outputText value="#{item.user}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText escape="false" value="&nbsp;"/>
                                </f:facet>
                                <h:commandLink value="Show" action="#{userSkills.detailSetup}">
                                    <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][user.user][user.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentUserSkills" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][userSkills.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="user" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.UserController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{userSkills.editSetup}">
                                    <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][user.user][user.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentUserSkills" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][userSkills.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="user" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.UserController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{userSkills.destroy}">
                                    <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][user.user][user.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentUserSkills" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][userSkills.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="user" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.UserController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{user.remove}" value="Destroy">
                    <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][user.user][user.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{user.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentUser" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][user.user][user.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{user.createSetup}" value="New User" />
                <br />
                <h:commandLink action="#{user.listSetup}" value="Show All User Items"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
