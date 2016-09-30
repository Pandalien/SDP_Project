/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entities.Adverts;
import entities.Classification;
import entities.Requirements;
import entities.RequirementsPK;
import entities.Skills;
import entities.Suburb;
import entities.User;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sb.AdvertsFacade;
import sb.RequirementsFacade;
import utils.Contract;
import utils.StringUtils;



/**
 *
 * @author andyc
 */
public class JobsServlet extends AbstractServlet {

    @EJB
    private RequirementsFacade requirementsFacade;

    
    @EJB
    private AdvertsFacade advertsFacade;
    
    public void create(HttpServletRequest request, HttpServletResponse response) {
        //login required to create an ad
        User user = getCurrentUser(request);
        if (user == null) {
            login(request, response);
            return;
        }
        
        //set a list of skills, suburbs and classifications to request attributes
        setCollectionSkills(request);
        setCollectionSuburbs(request);
        setCollectionClassifications(request);
        
        getView(request, response, "jobs/create.jsp");
    }
    
    public void createPost(HttpServletRequest request, HttpServletResponse response) {
        //login required to create an ad
        boolean isUpdate;
        
        User user = getCurrentUser(request);
        if(user == null){
            login(request, response);
            return;
        }
        
        Adverts ad;
        
        int oldId = getRequestId(request);
        if (oldId == -1) {
            isUpdate = false;
            //create new
            ad = new Adverts();
        }else{
            isUpdate = true;
            ad = advertsFacade.find(oldId);
            if (ad == null) {
                alertWarning(request, "This job was not found.");
                showGoBackPage(request, response);
                return;
            }
        }
        
        ad.setTitle(request.getParameter("title"));
        ad.setContent(request.getParameter("content"));
        ad.setUserId(user);
        ad.setClassificationId(new Classification(Integer.parseInt(request.getParameter("classification"))));
        ad.setSuburbId(new Suburb(Integer.parseInt(request.getParameter("suburb"))));
        
        String dateStr = request.getParameter("expiry_date");
        Date date = null;
        if (dateStr != null && !dateStr.isEmpty()) {
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
            } catch (ParseException ex) {
            }
        }
        
        if (date == null){
            Date today = new Date();
            date = new Date(today.getTime() + (1000 * 60 * 60 * 24 * 14)); 
        }
        
        ad.setExpiryDate(date);
        
        String msg;
        
        if (isUpdate) {
            advertsFacade.edit(ad);
            
            //delete old requirements
            List<Requirements> reqs = requirementsFacade.findByAdvertsId(ad.getId());
            if (reqs != null) {
                for(Requirements r : reqs){
                    requirementsFacade.remove(r);

                }
            }
            
            msg = ad.getTitle() + " updated Successfully!";
        }else{
            //add the ad to database
            advertsFacade.create(ad);

            //return a brief message
            msg = ad.getTitle() + " created Successfully!";
        }
        
        //create skills requirements db entries
        String[] skills = request.getParameterValues("skills");
        if (skills != null) {
            for (String skill : skills) {
                Requirements rq = new Requirements(new RequirementsPK(ad.getId(), Integer.parseInt(skill)));
                requirementsFacade.create(rq);
            }
        }
        
        alertSuccess(request, msg);
        openings(request, response);
    }
    
    public void view(HttpServletRequest request, HttpServletResponse response) {
        int id = getRequestId(request);
        if (id != -1) {
            Adverts ad = advertsFacade.find(id);

            if (ad != null) {
                request.setAttribute(Contract.VIEW_ADVERT, ad);
                getView(request, response, "jobs/view.jsp");
            }
        }
        alertWarning(request, "The job not found.");
        showGoBackPage(request, response);
    }
    
    public void edit(HttpServletRequest request, HttpServletResponse response) {
        int id = getRequestId(request);
        if (id != -1) {
            Adverts ad = advertsFacade.find(id);

            if (ad != null) {
                List<Requirements> reqs = requirementsFacade.findByAdvertsId(ad.getId());
                List<Integer> reSkIds = new ArrayList<>();
                for(Requirements r : reqs){
                    reSkIds.add(r.getRequirementsPK().getSkillsId());
                }
        
                //a list of current required skill ids
                request.setAttribute(Contract.ADVERT_SKILL_IDS, reSkIds);
                request.setAttribute(Contract.VIEW_ADVERT, ad);
                
                create(request, response);
                return;
            }
        }

        alertWarning(request, "The job not found.");
        showGoBackPage(request, response);
    }
    
    
    public void openings(HttpServletRequest request, HttpServletResponse response) {
        User user = getCurrentUser(request);
        if (user == null) {
            login(request, response);
            return;
        }
        
        List<Adverts> ads = advertsFacade.findByUser(user);
        request.setAttribute(Contract.USER_ADVERTS, ads);
        getView(request, response, "jobs/openings.jsp");
    }
    
    private void vacancyWrapper(HttpServletRequest request, HttpServletResponse response, Boolean isClosed) {
        int id = getRequestId(request);
        if (id != -1) {
            Adverts ad = advertsFacade.find(id);

            if (ad != null) {
                ad.setClosed(isClosed);
                advertsFacade.edit(ad);

                openings(request, response);
                return;
            }
        }

        alertWarning(request, "The job not found.");
        showGoBackPage(request, response);
    }
    
    public void close(HttpServletRequest request, HttpServletResponse response) {
        vacancyWrapper(request, response, Boolean.TRUE);
    }
    
    public void open(HttpServletRequest request, HttpServletResponse response) {
        vacancyWrapper(request, response, Boolean.FALSE);
    }
    
    public void delete(HttpServletRequest request, HttpServletResponse response) {
        int id = getRequestId(request);
        if (id != -1) {
            Adverts ad = advertsFacade.find(id);

            if (ad != null) {
                advertsFacade.remove(ad);

                openings(request, response);
                return;
            }
        }

        alertWarning(request, "The job not found.");
        showGoBackPage(request, response);
    }
    
    public void apply(HttpServletRequest request, HttpServletResponse response) {
        User user = getCurrentUser(request);
        if (user == null) {
            login(request, response);
            return;
        }
        
        int id = getRequestId(request);
        if (id == -1) {
            alertWarning(request, "The job not found.");
            showGoBackPage(request, response);
            return;
        }
        
        //confirm with user first
        showConfirmPage(request, response, "Do you want to apply to this job?", "jobs?action=apply", id);
    }
    
    public void applyPost(HttpServletRequest request, HttpServletResponse response) {
        alertSuccess(request, "Got it!");
        showGoBackPage(request, response);
    }
}
