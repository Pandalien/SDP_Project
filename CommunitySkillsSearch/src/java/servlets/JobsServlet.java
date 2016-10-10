/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.MessageType;
import beans.RequestData;
import entities.Adverts;
import entities.Classification;
import entities.Requirements;
import entities.RequirementsPK;
import entities.Responders;
import entities.RespondersPK;
import entities.Skills;
import entities.Suburb;
import entities.User;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sb.AdvertsFacade;
import sb.RequirementsFacade;
import sb.RespondersFacade;
import utils.Contract;
import utils.StringUtils;



/**
 *
 * @author andyc
 */
public class JobsServlet extends AbstractServlet {

    @EJB
    private RespondersFacade respondersFacade;

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
        ad.setClosed(Boolean.FALSE);
        
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
                rq.setLevel(1);
                requirementsFacade.create(rq);
            }
        }
        
        alertSuccess(request, msg);
        try {
            response.sendRedirect(request.getContextPath() + "/jobs?action=view&id=" + ad.getId());
        } catch (IOException ex) {
            openings(request, response);
        }
    }
    
    public void view(HttpServletRequest request, HttpServletResponse response) {
        int id = getRequestId(request);
        if (id != -1) {
            Adverts ad = advertsFacade.find(id);
            
            if (ad != null) {
                request.setAttribute(Contract.VIEW_ADVERT, ad);
                
                User user = getCurrentUser(request);
                //get responder info
                if (user != null) {
                    Responders responder = respondersFacade.find(new RespondersPK(user.getId(), ad.getId()));
                    request.setAttribute(Contract.ADVERT_RESPONDERS, responder);
                    request.setAttribute(Contract.CURRENT_USER, user);
                }
                
                getView(request, response, "jobs/view.jsp");
                return;
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
    
    
    //user created adverts page
    public void openings(HttpServletRequest request, HttpServletResponse response) {
        User user = getCurrentUser(request);
        if (user == null) {
            login(request, response);
            return;
        }
        
        List<Adverts> ads = advertsFacade.findByUser(user);
        if (ads != null) {
            request.setAttribute(Contract.ADVERTS, ads);

            for (Adverts ad : ads) {
                List<Responders> responders = respondersFacade.findByAdvertsId(ad.getId());
                if (responders != null) {
                    ad.setRespondersCollection(responders);
                }
            }
        }
        
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
        RequestData data = getAuthenticatedData(request, response);
        if (data == null) {
            return;
        }

        //confirm with user first
        showConfirmPage(request, response, "Are you sure you want to delete this job?", "jobs?action=delete", data.id);
    }
    
    public void deletePost(HttpServletRequest request, HttpServletResponse response) {
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
        RequestData data = getAuthenticatedData(request, response);
        if (data == null) {
            return;
        }
        
        //confirm with user first
        showConfirmPage(request, response, "Do you want to apply to this job?", "jobs?action=apply", data.id);
    }
    
    public void applyPost(HttpServletRequest request, HttpServletResponse response) {
        RequestData data = getAuthenticatedData(request, response);
        if (data == null) {
            return;
        }
        
        //user cannot apply to their own ad
        Adverts ad = advertsFacade.find(data.id);
        if (Objects.equals(ad.getUserId().getId(), data.user.getId())) {
            alertWarning(request, "You cannot apply to your own ad.");
            showGoBackPage(request, response);
            return;
        }
        
        //create database entry
        Responders responder = new Responders(data.user.getId(), ad.getId());
        responder.setFeedback("");
        responder.setMessage("");
        responder.setTime(new Date());
        respondersFacade.create(responder);
        
        //go to the ad page
        alertSuccess(request, "You successfully applied " + ad.getTitle());
        view(request, response);
    }
    
    public void cancel(HttpServletRequest request, HttpServletResponse response) {
        RequestData data = getAuthenticatedData(request, response);
        if (data == null) {
            return;
        }
        
        //confirm with user first
        showConfirmPage(request, response, "Do you want to cancel you application to this job?", "jobs?action=cancel", data.id);
    }
    
    public void cancelPost(HttpServletRequest request, HttpServletResponse response) {
        RequestData data = getAuthenticatedData(request, response);
        if (data == null) {
            return;
        }
        
        Adverts ad = advertsFacade.find(data.id);

        if (ad != null) {
            Responders responder = new Responders(data.user.getId(), ad.getId());
            respondersFacade.remove(responder);
            
            alertSuccess(request, "Your appliction has been removed from the job " + ad.getTitle());
            view(request, response);
            return;
        }

        alertWarning(request, "The job not found.");
        showGoBackPage(request, response);
    }
    
    public void applications(HttpServletRequest request, HttpServletResponse response) {
        User user = getCurrentUser(request);
        if (user == null) {
            login(request, response);
            return;
        }
        
        List<Adverts> ads = new ArrayList<>();
        
        //get my applications list
        List<Responders> responders = respondersFacade.findByUserId(user.getId());
        if (responders != null) {
            for (Responders responder : responders) {
                Adverts ad = advertsFacade.find(responder.getRespondersPK().getAdvertsId());
                if (ad != null) {
                    responder.setAdverts(ad);
                    responder.setUser(userFacade.find(responder.getRespondersPK().getUserId()));
                    //set me as the only responder
                    List<Responders> meAsResponders = new ArrayList<>(1);
                    meAsResponders.add(responder);
                    
                    ad.setRespondersCollection(meAsResponders);
                    ads.add(ad);
                }
            }
        }
        
        request.setAttribute(Contract.ADVERTS, ads);
        getView(request, response, "jobs/applications.jsp");
    }
    
    //view received applictions
    public void applicants(HttpServletRequest request, HttpServletResponse response) {
        User user = getCurrentUser(request);
        if (user == null) {
            login(request, response);
            return;
        }

        List<Adverts> ads = advertsFacade.findByUser(user);
        if (ads != null) {
            List<Responders> all = new ArrayList<>();
            request.setAttribute(Contract.USER_ADVERTS, ads);
            
            for(Adverts ad : ads){
                List<Responders> responders = respondersFacade.findByAdvertsId(ad.getId());
                for(Responders r : responders){
                    r.setUser(userFacade.find(r.getRespondersPK().getUserId()));
                    r.setAdverts(advertsFacade.find(r.getRespondersPK().getAdvertsId()));
                }
                all.addAll(responders);
            }
            
            request.setAttribute(Contract.ADVERT_RESPONDERS, all);
        }
        
        getView(request, response, "jobs/applicants.jsp");
    }
    
    //view received applictions
    public void assign(HttpServletRequest request, HttpServletResponse response) {
        RequestData data = getAuthenticatedData(request, response);
        if (data == null) {
            return;
        }
        
        int workerId = Integer.parseInt((String) request.getParameter("userid"));
        User worker = userFacade.find(workerId);
        if (worker == null) {
            alertDanger(request, "The worker was not found, please select anther worker.");
            return;
        }
        
        showConfirmPage(request, response, "Do you want to select " + worker.getName()+" (rating: " + worker.getRating() +")", "jobs?action=assign&userid="+workerId, data.id);
    }
    
    public void assignPost(HttpServletRequest request, HttpServletResponse response) {
        RequestData data = getAuthenticatedData(request, response);
        if (data == null) {
            return;
        }

        Adverts ad = advertsFacade.find(data.id);
        if (ad == null) {
            alertDanger(request, "The job was not found.");
            return;
        }
        
        int workerId = Integer.parseInt((String) request.getParameter("userid"));
        User worker = userFacade.find(workerId);
        if (worker == null) {
            alertDanger(request, "The worker was not found, please select anther worker.");
            return;
        }

        Responders responder = respondersFacade.find(new RespondersPK(workerId, data.id));
        if (responder == null) {
            alertDanger(request, "The worker was not found, or the application has been withdrawn.");
            return;
        }
        
        responder.setStatus(Contract.ResponderStatus.SELECTED.ordinal());
        respondersFacade.edit(responder);
        
        alertSuccess(request, worker.getName() + " has been assigned to the job: " + ad.getTitle());
        applicants(request, response);
    }
    
    @Override
    protected void invokeMethod(HttpServletRequest req, HttpServletResponse resp, boolean doPost) {
        req.setAttribute("current_path", "Jobs");
        super.invokeMethod(req, resp, doPost);
    }
}
