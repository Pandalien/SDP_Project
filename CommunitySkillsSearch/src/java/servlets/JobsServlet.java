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
import entities.Suburb;
import entities.User;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sb.AdvertsFacade;
import sb.RequirementsFacade;



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
        User user = getCurrentUser(request);
        if(user == null){
            login(request, response);
        }
        
        Adverts ad = new Adverts();
        
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
        
        //add the ad to database
        advertsFacade.create(ad);
        
        //create skills requirements db entries
        String [] skills = request.getParameterValues("skills");
        if (skills != null) {
            for (String skill : skills) {
                Requirements rq = new Requirements(new RequirementsPK(ad.getId(), Integer.parseInt(skill)));
                requirementsFacade.create(rq);
            }
        }
        
        //return a brief message
        sendMessage(request, response, ad.getTitle() + " created Successfully!");
    }
}
