/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entities.Adverts;
import entities.Requirements;
import entities.RequirementsPK;
import entities.User;
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
        
        //set a list of skills to request attributes
        setCollectionSkills(request);
        
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
        
        //add the ad to database
        advertsFacade.create(ad);
        
        //create skills requirements db entries
        String [] skills = request.getParameterValues("skills");
        for (String skill : skills) {
            Requirements rq = new Requirements(new RequirementsPK(ad.getId(), Integer.parseInt(skill)));
            requirementsFacade.create(rq);
        }
        
        //return a brief message
        sendMessage(request, response, ad.getTitle() + " created Successfully!");
    }
}
