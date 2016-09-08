/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import entities.Classification;
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
import sb.ClassificationFacade;

/**
 *
 * @author andyc
 */
public class ClassificationController {
    boolean ERROR = false;
    public ClassificationController() {
        pagingInfo = new PagingInfo();
        converter = new ClassificationConverter();
    }
    private Classification classification = null;
    private List<Classification> classificationItems = null;
    private ClassificationFacade jpaController = null;
    private ClassificationConverter converter = null;
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

    public ClassificationFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (ClassificationFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "classificationJpa");
        }
        return jpaController;
    }

    public SelectItem[] getClassificationItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getClassificationItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Classification getClassification() {
        if (classification == null) {
            classification = (Classification) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentClassification", converter, null);
        }
        if (classification == null) {
            classification = new Classification();
        }
        return classification;
    }

    public String listSetup() {
        reset(true);
        return "classification_list";
    }

    public String createSetup() {
        reset(false);
        classification = new Classification();
        return "classification_create";
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(classification);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Classification was successfully created.");
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
        return scalarSetup("classification_detail");
    }

    public String editSetup() {
        return scalarSetup("classification_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        classification = (Classification) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentClassification", converter, null);
        if (classification == null) {
            String requestClassificationString = JsfUtil.getRequestParameter("jsfcrud.currentClassification");
            JsfUtil.addErrorMessage("The classification with id " + requestClassificationString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String classificationString = converter.getAsString(FacesContext.getCurrentInstance(), null, classification);
        String currentClassificationString = JsfUtil.getRequestParameter("jsfcrud.currentClassification");
        if (classificationString == null || classificationString.length() == 0 || !classificationString.equals(currentClassificationString)) {
            String outcome = editSetup();
            if ("classification_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit classification. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(classification);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Classification was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentClassification");
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
                JsfUtil.addSuccessMessage("Classification was successfully deleted.");
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

    public List<Classification> getClassificationItems() {
        if (classificationItems == null) {
            getPagingInfo();
            classificationItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return classificationItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "classification_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "classification_list";
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
        classification = null;
        classificationItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Classification newClassification = new Classification();
        String newClassificationString = converter.getAsString(FacesContext.getCurrentInstance(), null, newClassification);
        String classificationString = converter.getAsString(FacesContext.getCurrentInstance(), null, classification);
        if (!newClassificationString.equals(classificationString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
