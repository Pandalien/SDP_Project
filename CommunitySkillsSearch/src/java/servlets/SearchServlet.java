/*
 * Software Development Practice, Stream 50 Team 2
 * Community Skills Search
 * Servlet for testing
 */
package servlets;

import entities.Adverts;
import entities.User;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.Contract;
import utils.SearchParams;

/**
 *
 * @author AD
 */
public class SearchServlet extends AbstractServlet {

    //return page for search results
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
      
      if (search.type == SearchParams.JOB) {
        
        List<Adverts> adverts = advertsFacade.findByVarious(search);
        request.setAttribute(Contract.ADVERTS, adverts);
        
      }
      else if (search.type == SearchParams.WORKER) {
        
        List<User> users = userFacade.findByVarious(search);     
        request.setAttribute(Contract.USERS, users);
      }
      
      request.setAttribute("search", search);
      
      setCollectionSkills(request);
      setCollectionSuburbs(request);
      setCollectionClassifications(request);
      
      getView(request, response, "user/search.jsp");              
    }
  
}
