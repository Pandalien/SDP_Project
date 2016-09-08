/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import entities.Skills;
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
import sb.SkillsFacade;

/**
 *
 * @author andyc
 */
public class SkillsController {
    boolean ERROR = false;
    public SkillsController() {
        pagingInfo = new PagingInfo();
        converter = new SkillsConverter();
    }
    private Skills skills = null;
    private List<Skills> skillsItems = null;
    private SkillsFacade jpaController = null;
    private SkillsConverter converter = null;
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

    public SkillsFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (SkillsFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "skillsJpa");
        }
        return jpaController;
    }

    public SelectItem[] getSkillsItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getSkillsItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Skills getSkills() {
        if (skills == null) {
            skills = (Skills) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentSkills", converter, null);
        }
        if (skills == null) {
            skills = new Skills();
        }
        return skills;
    }

    public String listSetup() {
        reset(true);
        return "skills_list";
    }

    public String createSetup() {
        reset(false);
        skills = new Skills();
        return "skills_create";
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(skills);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Skills was successfully created.");
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
        return scalarSetup("skills_detail");
    }

    public String editSetup() {
        return scalarSetup("skills_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        skills = (Skills) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentSkills", converter, null);
        if (skills == null) {
            String requestSkillsString = JsfUtil.getRequestParameter("jsfcrud.currentSkills");
            JsfUtil.addErrorMessage("The skills with id " + requestSkillsString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String skillsString = converter.getAsString(FacesContext.getCurrentInstance(), null, skills);
        String currentSkillsString = JsfUtil.getRequestParameter("jsfcrud.currentSkills");
        if (skillsString == null || skillsString.length() == 0 || !skillsString.equals(currentSkillsString)) {
            String outcome = editSetup();
            if ("skills_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit skills. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(skills);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Skills was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentSkills");
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
                JsfUtil.addSuccessMessage("Skills was successfully deleted.");
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

    public List<Skills> getSkillsItems() {
        if (skillsItems == null) {
            getPagingInfo();
            skillsItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return skillsItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "skills_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "skills_list";
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
        skills = null;
        skillsItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Skills newSkills = new Skills();
        String newSkillsString = converter.getAsString(FacesContext.getCurrentInstance(), null, newSkills);
        String skillsString = converter.getAsString(FacesContext.getCurrentInstance(), null, skills);
        if (!newSkillsString.equals(skillsString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
