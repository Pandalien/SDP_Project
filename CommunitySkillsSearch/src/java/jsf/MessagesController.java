/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import entities.Messages;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.faces.FacesException;
import javax.annotation.Resource;
import javax.transaction.UserTransaction;
import jsf.util.JsfUtil;
import entities.MessagesPK;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import jsf.util.PagingInfo;
import sb.MessagesFacade;

/**
 *
 * @author andyc
 */
public class MessagesController {
    boolean ERROR = false;
    public MessagesController() {
        pagingInfo = new PagingInfo();
        converter = new MessagesConverter();
    }
    private Messages messages = null;
    private List<Messages> messagesItems = null;
    private MessagesFacade jpaController = null;
    private MessagesConverter converter = null;
    private PagingInfo pagingInfo = null;
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "CommunitySkillsSearchPU")
    private EntityManagerFactory emf = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(getJpaController().count());
        }
        return pagingInfo;
    }

    public MessagesFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (MessagesFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "messagesJpa");
        }
        return jpaController;
    }

    public SelectItem[] getMessagesItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getMessagesItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Messages getMessages() {
        if (messages == null) {
            messages = (Messages) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentMessages", converter, null);
        }
        if (messages == null) {
            messages = new Messages();
        }
        return messages;
    }

    public String listSetup() {
        reset(true);
        return "messages_list";
    }

    public String createSetup() {
        reset(false);
        messages = new Messages();
        messages.setMessagesPK(new MessagesPK());
        return "messages_create";
    }

    public String create() {
        messages.getMessagesPK().setReceiverId(messages.getUser1().getId());
        messages.getMessagesPK().setSenderId(messages.getUser().getId());
        // TODO: no setter methods were found in your primary key class
        //    entities.MessagesPK
        // and therefore initialization code need manual adjustments.
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(messages);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Messages was successfully created.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("messages_detail");
    }

    public String editSetup() {
        return scalarSetup("messages_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        messages = (Messages) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentMessages", converter, null);
        if (messages == null) {
            String requestMessagesString = JsfUtil.getRequestParameter("jsfcrud.currentMessages");
            JsfUtil.addErrorMessage("The messages with id " + requestMessagesString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        messages.getMessagesPK().setReceiverId(messages.getUser1().getId());
        messages.getMessagesPK().setSenderId(messages.getUser().getId());
        // TODO: no setter methods were found in your primary key class
        //    entities.MessagesPK
        // and therefore initialization code need manual adjustments.
        String messagesString = converter.getAsString(FacesContext.getCurrentInstance(), null, messages);
        String currentMessagesString = JsfUtil.getRequestParameter("jsfcrud.currentMessages");
        if (messagesString == null || messagesString.length() == 0 || !messagesString.equals(currentMessagesString)) {
            String outcome = editSetup();
            if ("messages_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit messages. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(messages);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Messages was successfully updated.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return detailSetup();
    }

    public String remove() {
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentMessages");
        MessagesPK id = converter.getId(idAsString);
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().remove(getJpaController().find(id));
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Messages was successfully deleted.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return relatedOrListOutcome();
    }

    private String relatedOrListOutcome() {
        String relatedControllerOutcome = relatedControllerOutcome();
        if ((ERROR)) {
            return relatedControllerOutcome;
        }
        return listSetup();
    }

    public List<Messages> getMessagesItems() {
        if (messagesItems == null) {
            getPagingInfo();
            messagesItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return messagesItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "messages_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "messages_list";
    }

    private String relatedControllerOutcome() {
        String relatedControllerString = JsfUtil.getRequestParameter("jsfcrud.relatedController");
        String relatedControllerTypeString = JsfUtil.getRequestParameter("jsfcrud.relatedControllerType");
        if (relatedControllerString != null && relatedControllerTypeString != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            Object relatedController = context.getApplication().getELResolver().getValue(context.getELContext(), null, relatedControllerString);
            try {
                Class<?> relatedControllerType = Class.forName(relatedControllerTypeString);
                Method detailSetupMethod = relatedControllerType.getMethod("detailSetup");
                return (String) detailSetupMethod.invoke(relatedController);
            } catch (ClassNotFoundException e) {
                throw new FacesException(e);
            } catch (NoSuchMethodException e) {
                throw new FacesException(e);
            } catch (IllegalAccessException e) {
                throw new FacesException(e);
            } catch (InvocationTargetException e) {
                throw new FacesException(e);
            }
        }
        return null;
    }

    private void reset(boolean resetFirstItem) {
        messages = null;
        messagesItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Messages newMessages = new Messages();
        newMessages.setMessagesPK(new MessagesPK());
        String newMessagesString = converter.getAsString(FacesContext.getCurrentInstance(), null, newMessages);
        String messagesString = converter.getAsString(FacesContext.getCurrentInstance(), null, messages);
        if (!newMessagesString.equals(messagesString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
