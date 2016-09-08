/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import entities.UserSkills;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.faces.FacesException;
import javax.annotation.Resource;
import javax.transaction.UserTransaction;
import jsf.util.JsfUtil;
import entities.UserSkillsPK;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import jsf.util.PagingInfo;
import sb.UserSkillsFacade;

/**
 *
 * @author andyc
 */
public class UserSkillsController {
    boolean ERROR = false;
    public UserSkillsController() {
        pagingInfo = new PagingInfo();
        converter = new UserSkillsConverter();
    }
    private UserSkills userSkills = null;
    private List<UserSkills> userSkillsItems = null;
    private UserSkillsFacade jpaController = null;
    private UserSkillsConverter converter = null;
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

    public UserSkillsFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (UserSkillsFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "userSkillsJpa");
        }
        return jpaController;
    }

    public SelectItem[] getUserSkillsItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getUserSkillsItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public UserSkills getUserSkills() {
        if (userSkills == null) {
            userSkills = (UserSkills) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentUserSkills", converter, null);
        }
        if (userSkills == null) {
            userSkills = new UserSkills();
        }
        return userSkills;
    }

    public String listSetup() {
        reset(true);
        return "userSkills_list";
    }

    public String createSetup() {
        reset(false);
        userSkills = new UserSkills();
        userSkills.setUserSkillsPK(new UserSkillsPK());
        return "userSkills_create";
    }

    public String create() {
        userSkills.getUserSkillsPK().setUserId(userSkills.getUser().getId());
        userSkills.getUserSkillsPK().setSkillsId(userSkills.getSkills().getId());
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(userSkills);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("UserSkills was successfully created.");
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
        return scalarSetup("userSkills_detail");
    }

    public String editSetup() {
        return scalarSetup("userSkills_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        userSkills = (UserSkills) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentUserSkills", converter, null);
        if (userSkills == null) {
            String requestUserSkillsString = JsfUtil.getRequestParameter("jsfcrud.currentUserSkills");
            JsfUtil.addErrorMessage("The userSkills with id " + requestUserSkillsString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        userSkills.getUserSkillsPK().setUserId(userSkills.getUser().getId());
        userSkills.getUserSkillsPK().setSkillsId(userSkills.getSkills().getId());
        String userSkillsString = converter.getAsString(FacesContext.getCurrentInstance(), null, userSkills);
        String currentUserSkillsString = JsfUtil.getRequestParameter("jsfcrud.currentUserSkills");
        if (userSkillsString == null || userSkillsString.length() == 0 || !userSkillsString.equals(currentUserSkillsString)) {
            String outcome = editSetup();
            if ("userSkills_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit userSkills. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(userSkills);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("UserSkills was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentUserSkills");
        UserSkillsPK id = converter.getId(idAsString);
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
                JsfUtil.addSuccessMessage("UserSkills was successfully deleted.");
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

    public List<UserSkills> getUserSkillsItems() {
        if (userSkillsItems == null) {
            getPagingInfo();
            userSkillsItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return userSkillsItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "userSkills_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "userSkills_list";
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
        userSkills = null;
        userSkillsItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        UserSkills newUserSkills = new UserSkills();
        newUserSkills.setUserSkillsPK(new UserSkillsPK());
        String newUserSkillsString = converter.getAsString(FacesContext.getCurrentInstance(), null, newUserSkills);
        String userSkillsString = converter.getAsString(FacesContext.getCurrentInstance(), null, userSkills);
        if (!newUserSkillsString.equals(userSkillsString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
