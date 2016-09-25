/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entities.Adverts;
import entities.User;
import entities.UserSkills;
import java.util.*;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sb.AdvertsFacade;
import sb.UserSkillsFacade;
import utils.Contract;
import utils.SearchParams;

/**
 *
 * @author AD
 */
public class SearchServlet extends AbstractServlet {

    @EJB
    private AdvertsFacade advertsFacade;
    
    @EJB
    private UserSkillsFacade userSkillsFacade;
      
/*    
    public void showall(HttpServletRequest request, HttpServletResponse response) {  
      List<Adverts> adverts = advertsFacade.findAll();
      request.setAttribute(Contract.ADVERTS, adverts);
      
      setCollectionSkills(request);
      setCollectionSuburbs(request);
      setCollectionClassifications(request);
      
      getView(request, response, "user/search.jsp");        
    }
*/
    
    public void searchFor(HttpServletRequest request, HttpServletResponse response) {
      
      String suburb_id_param = request.getParameter("suburb");
      String classification_id_param = request.getParameter("classification");
      String skills_id_param = request.getParameter("skills");
      String keywords_param = request.getParameter("keywords");
      String type_param = request.getParameter("type");
      
      // parse search inputs:
      // -1 for any int means "all"
      int suburb_id = SearchParams.parseId(suburb_id_param);      
      int classification_id = SearchParams.parseId(classification_id_param);
      int skills_id = SearchParams.parseId(skills_id_param);
      List<String> keywords = SearchParams.parseKeywords(keywords_param);
      int type = SearchParams.parseType(type_param);
      
      
      SearchParams search = new SearchParams();
      search.setSuburbId(suburb_id);
      search.setClassificationId(classification_id);
      search.setSkillsId(skills_id);
      search.setKeywords(keywords_param);
      search.setType(type);
      
      
      if (type == SearchParams.JOB) {
        List<Adverts> adverts = advertsFacade.findByVarious(suburb_id, classification_id, keywords);
        request.setAttribute(Contract.ADVERTS, adverts);
      }
      else if (type == SearchParams.WORKER) {
        List<UserSkills> userSkills;
        if (SearchParams.validateSkillsId(skills_id))
          userSkills = userSkillsFacade.findBySkillsId(skills_id);
        else
          userSkills = userSkillsFacade.findAll();
        
        List<User> users = new ArrayList();
        for (UserSkills us: userSkills) {
          User u = us.getUser();
          if (u != null && u.getVisible().booleanValue()) {
            if (SearchParams.validateSuburbId(suburb_id)) {
              if (suburb_id == u.getSuburbId().getId()) 
                users.add(u);
            }
            else
              users.add(u);
            
          }
            
        }
        request.setAttribute(Contract.USERS, users);
      }
      
      request.setAttribute("search", search);
      
      setCollectionSkills(request);
      setCollectionSuburbs(request);
      setCollectionClassifications(request);
      
      getView(request, response, "user/search.jsp");              
    }
  
}
