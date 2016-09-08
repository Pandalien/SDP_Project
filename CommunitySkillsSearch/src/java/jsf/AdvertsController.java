/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import entities.Adverts;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.faces.FacesException;
import javax.annotation.Resource;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;
import jsf.util.JsfUtil;
import jsf.util.PagingInfo;
import sb.AdvertsFacade;

/**
 *
 * @author andyc
 */
public class AdvertsController {
    boolean ERROR = false;
    public AdvertsController() {
        pagingInfo = new PagingInfo();
        converter = new AdvertsConverter();
    }
    private Adverts adverts = null;
    private List<Adverts> advertsItems = null;
    private AdvertsFacade jpaController = null;
    private AdvertsConverter converter = null;
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

    public AdvertsFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (AdvertsFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "advertsJpa");
        }
        return jpaController;
    }

    public SelectItem[] getAdvertsItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getAdvertsItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Adverts getAdverts() {
        if (adverts == null) {
            adverts = (Adverts) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentAdverts", converter, null);
        }
        if (adverts == null) {
            adverts = new Adverts();
        }
        return adverts;
    }

    public String listSetup() {
        reset(true);
        return "adverts_list";
    }

    public String createSetup() {
        reset(false);
        adverts = new Adverts();
        return "adverts_create";
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(adverts);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Adverts was successfully created.");
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
        return scalarSetup("adverts_detail");
    }

    public String editSetup() {
        return scalarSetup("adverts_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        adverts = (Adverts) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentAdverts", converter, null);
        if (adverts == null) {
            String requestAdvertsString = JsfUtil.getRequestParameter("jsfcrud.currentAdverts");
            JsfUtil.addErrorMessage("The adverts with id " + requestAdvertsString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String advertsString = converter.getAsString(FacesContext.getCurrentInstance(), null, adverts);
        String currentAdvertsString = JsfUtil.getRequestParameter("jsfcrud.currentAdverts");
        if (advertsString == null || advertsString.length() == 0 || !advertsString.equals(currentAdvertsString)) {
            String outcome = editSetup();
            if ("adverts_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit adverts. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(adverts);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Adverts was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentAdverts");
        Integer id = new Integer(idAsString);
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
                JsfUtil.addSuccessMessage("Adverts was successfully deleted.");
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

    public List<Adverts> getAdvertsItems() {
        if (advertsItems == null) {
            getPagingInfo();
            advertsItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return advertsItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "adverts_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "adverts_list";
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
        adverts = null;
        advertsItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Adverts newAdverts = new Adverts();
        String newAdvertsString = converter.getAsString(FacesContext.getCurrentInstance(), null, newAdverts);
        String advertsString = converter.getAsString(FacesContext.getCurrentInstance(), null, adverts);
        if (!newAdvertsString.equals(advertsString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
