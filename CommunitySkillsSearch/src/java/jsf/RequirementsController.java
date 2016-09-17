/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import entities.Requirements;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.faces.FacesException;
import javax.annotation.Resource;
import javax.transaction.UserTransaction;
import jsf.util.JsfUtil;
import entities.RequirementsPK;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import jsf.util.PagingInfo;
import sb.RequirementsFacade;

/**
 *
 * @author andyc
 */
public class RequirementsController {
    boolean ERROR = false;
    public RequirementsController() {
        pagingInfo = new PagingInfo();
        converter = new RequirementsConverter();
    }
    private Requirements requirements = null;
    private List<Requirements> requirementsItems = null;
    private RequirementsFacade jpaController = null;
    private RequirementsConverter converter = null;
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

    public RequirementsFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (RequirementsFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "requirementsJpa");
        }
        return jpaController;
    }

    public SelectItem[] getRequirementsItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getRequirementsItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Requirements getRequirements() {
        if (requirements == null) {
            requirements = (Requirements) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentRequirements", converter, null);
        }
        if (requirements == null) {
            requirements = new Requirements();
        }
        return requirements;
    }

    public String listSetup() {
        reset(true);
        return "requirements_list";
    }

    public String createSetup() {
        reset(false);
        requirements = new Requirements();
        requirements.setRequirementsPK(new RequirementsPK());
        return "requirements_create";
    }

    public String create() {
        requirements.getRequirementsPK().setAdvertsId(requirements.getAdverts().getId());
        requirements.getRequirementsPK().setSkillsId(requirements.getSkills().getId());
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(requirements);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Requirements was successfully created.");
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
        return scalarSetup("requirements_detail");
    }

    public String editSetup() {
        return scalarSetup("requirements_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        requirements = (Requirements) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentRequirements", converter, null);
        if (requirements == null) {
            String requestRequirementsString = JsfUtil.getRequestParameter("jsfcrud.currentRequirements");
            JsfUtil.addErrorMessage("The requirements with id " + requestRequirementsString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        requirements.getRequirementsPK().setAdvertsId(requirements.getAdverts().getId());
        requirements.getRequirementsPK().setSkillsId(requirements.getSkills().getId());
        String requirementsString = converter.getAsString(FacesContext.getCurrentInstance(), null, requirements);
        String currentRequirementsString = JsfUtil.getRequestParameter("jsfcrud.currentRequirements");
        if (requirementsString == null || requirementsString.length() == 0 || !requirementsString.equals(currentRequirementsString)) {
            String outcome = editSetup();
            if ("requirements_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit requirements. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(requirements);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Requirements was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentRequirements");
        RequirementsPK id = converter.getId(idAsString);
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
                JsfUtil.addSuccessMessage("Requirements was successfully deleted.");
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

    public List<Requirements> getRequirementsItems() {
        if (requirementsItems == null) {
            getPagingInfo();
            requirementsItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return requirementsItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "requirements_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "requirements_list";
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
        requirements = null;
        requirementsItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Requirements newRequirements = new Requirements();
        newRequirements.setRequirementsPK(new RequirementsPK());
        String newRequirementsString = converter.getAsString(FacesContext.getCurrentInstance(), null, newRequirements);
        String requirementsString = converter.getAsString(FacesContext.getCurrentInstance(), null, requirements);
        if (!newRequirementsString.equals(requirementsString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
