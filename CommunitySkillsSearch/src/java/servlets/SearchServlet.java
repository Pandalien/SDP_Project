/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entities.Adverts;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sb.AdvertsFacade;
import utils.Contract;

/**
 *
 * @author AD
 */
public class SearchServlet extends AbstractServlet {

    @EJB
    private AdvertsFacade advertsFacade;
      
    public void showall(HttpServletRequest request, HttpServletResponse response) {  
      List<Adverts> adverts = advertsFacade.findAll();
      request.setAttribute(Contract.ADVERTS, adverts);
      
      setCollectionSkills(request);
      setCollectionSuburbs(request);
      setCollectionClassifications(request);
      
      getView(request, response, "user/search.jsp");        
    }
    
    public void searchFor(HttpServletRequest request, HttpServletResponse response) {
      
      // Potentially this does may not require being logged in? 
      // However links in the search results to contact or apply etc will (and 
      // should be handled by the jsp page).
      
      String suburb_id = request.getParameter("suburb_id");
      String classification_id = request.getParameter("classification_id");
      String skills_id = request.getParameter("skills_id");
      String keywords = request.getParameter("keywords");
      String type = request.getParameter("type");

      
      List<Adverts> adverts = advertsFacade.findByVarious(Integer.parseInt(suburb_id), Integer.parseInt(classification_id));
      request.setAttribute(Contract.ADVERTS, adverts);
     
      
      setCollectionSkills(request);
      setCollectionSuburbs(request);
      setCollectionClassifications(request);
      
      getView(request, response, "user/search.jsp");              
    }
  
}
