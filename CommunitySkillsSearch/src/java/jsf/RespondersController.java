/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import entities.Responders;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.faces.FacesException;
import javax.annotation.Resource;
import javax.transaction.UserTransaction;
import jsf.util.JsfUtil;
import entities.RespondersPK;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import jsf.util.PagingInfo;
import sb.RespondersFacade;

/**
 *
 * @author andyc
 */
public class RespondersController {
    boolean ERROR = false;
    public RespondersController() {
        pagingInfo = new PagingInfo();
        converter = new RespondersConverter();
    }
    private Responders responders = null;
    private List<Responders> respondersItems = null;
    private RespondersFacade jpaController = null;
    private RespondersConverter converter = null;
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

    public RespondersFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (RespondersFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "respondersJpa");
        }
        return jpaController;
    }

    public SelectItem[] getRespondersItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getRespondersItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Responders getResponders() {
        if (responders == null) {
            responders = (Responders) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentResponders", converter, null);
        }
        if (responders == null) {
            responders = new Responders();
        }
        return responders;
    }

    public String listSetup() {
        reset(true);
        return "responders_list";
    }

    public String createSetup() {
        reset(false);
        responders = new Responders();
        responders.setRespondersPK(new RespondersPK());
        return "responders_create";
    }

    public String create() {
        responders.getRespondersPK().setUserId(responders.getUser().getId());
        responders.getRespondersPK().setAdvertsId(responders.getAdverts().getId());
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(responders);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Responders was successfully created.");
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
        return scalarSetup("responders_detail");
    }

    public String editSetup() {
        return scalarSetup("responders_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        responders = (Responders) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentResponders", converter, null);
        if (responders == null) {
            String requestRespondersString = JsfUtil.getRequestParameter("jsfcrud.currentResponders");
            JsfUtil.addErrorMessage("The responders with id " + requestRespondersString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        responders.getRespondersPK().setUserId(responders.getUser().getId());
        responders.getRespondersPK().setAdvertsId(responders.getAdverts().getId());
        String respondersString = converter.getAsString(FacesContext.getCurrentInstance(), null, responders);
        String currentRespondersString = JsfUtil.getRequestParameter("jsfcrud.currentResponders");
        if (respondersString == null || respondersString.length() == 0 || !respondersString.equals(currentRespondersString)) {
            String outcome = editSetup();
            if ("responders_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit responders. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(responders);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Responders was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentResponders");
        RespondersPK id = converter.getId(idAsString);
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
                JsfUtil.addSuccessMessage("Responders was successfully deleted.");
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

    public List<Responders> getRespondersItems() {
        if (respondersItems == null) {
            getPagingInfo();
            respondersItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return respondersItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "responders_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "responders_list";
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
        responders = null;
        respondersItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Responders newResponders = new Responders();
        newResponders.setRespondersPK(new RespondersPK());
        String newRespondersString = converter.getAsString(FacesContext.getCurrentInstance(), null, newResponders);
        String respondersString = converter.getAsString(FacesContext.getCurrentInstance(), null, responders);
        if (!newRespondersString.equals(respondersString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
