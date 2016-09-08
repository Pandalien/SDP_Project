/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import entities.Suburb;
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
import sb.SuburbFacade;

/**
 *
 * @author andyc
 */
public class SuburbController {
    boolean ERROR = false;
    public SuburbController() {
        pagingInfo = new PagingInfo();
        converter = new SuburbConverter();
    }
    private Suburb suburb = null;
    private List<Suburb> suburbItems = null;
    private SuburbFacade jpaController = null;
    private SuburbConverter converter = null;
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

    public SuburbFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (SuburbFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "suburbJpa");
        }
        return jpaController;
    }

    public SelectItem[] getSuburbItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getSuburbItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Suburb getSuburb() {
        if (suburb == null) {
            suburb = (Suburb) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentSuburb", converter, null);
        }
        if (suburb == null) {
            suburb = new Suburb();
        }
        return suburb;
    }

    public String listSetup() {
        reset(true);
        return "suburb_list";
    }

    public String createSetup() {
        reset(false);
        suburb = new Suburb();
        return "suburb_create";
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(suburb);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Suburb was successfully created.");
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
        return scalarSetup("suburb_detail");
    }

    public String editSetup() {
        return scalarSetup("suburb_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        suburb = (Suburb) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentSuburb", converter, null);
        if (suburb == null) {
            String requestSuburbString = JsfUtil.getRequestParameter("jsfcrud.currentSuburb");
            JsfUtil.addErrorMessage("The suburb with id " + requestSuburbString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String suburbString = converter.getAsString(FacesContext.getCurrentInstance(), null, suburb);
        String currentSuburbString = JsfUtil.getRequestParameter("jsfcrud.currentSuburb");
        if (suburbString == null || suburbString.length() == 0 || !suburbString.equals(currentSuburbString)) {
            String outcome = editSetup();
            if ("suburb_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit suburb. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(suburb);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Suburb was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentSuburb");
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
                JsfUtil.addSuccessMessage("Suburb was successfully deleted.");
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

    public List<Suburb> getSuburbItems() {
        if (suburbItems == null) {
            getPagingInfo();
            suburbItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return suburbItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "suburb_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "suburb_list";
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
        suburb = null;
        suburbItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Suburb newSuburb = new Suburb();
        String newSuburbString = converter.getAsString(FacesContext.getCurrentInstance(), null, newSuburb);
        String suburbString = converter.getAsString(FacesContext.getCurrentInstance(), null, suburb);
        if (!newSuburbString.equals(suburbString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
