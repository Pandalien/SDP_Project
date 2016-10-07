/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb;

import entities.Adverts;
import entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import utils.SearchParams;

/**
 *
 * @author andyc, AD
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {
    @PersistenceContext(unitName = "CommunitySkillsSearchPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    
    //return user object, require username and password. this funciont can be used for user logging in
    public User findByUsernameAndPassword(Object username, Object password) {
      // Note from AD: I changed this to check the password in java code as 
      // database queries are case insensitive. 
      
        Query q = em.createNamedQuery("User.findByName");
//        Query q = em.createNamedQuery("User.findByUsernameAndPassword");
        q.setParameter("name", username);
//        q.setParameter("password", password);
        
        List<User> users = q.getResultList();
        
        if (users != null && users.size() > 0) {
            User user = users.get(0);
            if (user != null && user.getPassword().equals(password))
                return user;
        }

        return null;
    }
    
    public User findByUsernameAndEmail(Object username, Object email) {
        Query q = em.createNamedQuery("User.findByName");
        q.setParameter("name", username);
        List<User> users = q.getResultList();
        if (users != null && users.size() > 0) {
            User user = users.get(0);
            if (user != null && user.getEmail().equals(email))
                return user;
        }
        return null;
    }
    
    
    //find user by username
    public List<User> findByName(Object username){
        Query q = em.createNamedQuery("User.findByName");
        q.setParameter("name", username); 
        return q.getResultList();
    }
    
    //find user by email
    public List<User> findByEmail(Object username){
        Query q = em.createNamedQuery("User.findByEmail");
        q.setParameter("name", username); 
        return q.getResultList();
    } 
    
    // native sql query
    public List<User> findByVarious(SearchParams search) {
        // default find all query string
        String qs = "select u.* from user u";
        
        // list of "where" conditions:        
        ArrayList<String> where = new ArrayList();
        ArrayList<Object> params = new ArrayList();
        
        int index = 0;

        if (SearchParams.validateSkillsId(search.skills_id)) {
          qs += ", user_skills us";
          where.add("us.skills_id=?" + ++index + " and us.user_id=u.id");
          params.add(search.skills_id);
        }
        
        if (SearchParams.validateSuburbId(search.suburb_id)) {
          where.add("u.suburb_id=?" + ++index);
          params.add(search.suburb_id);
        }
        
/*        
        if (SearchParams.validateClassificationId(classification_id)) {
          where.add("classification_id=?" + ++index);
          params.add(classification_id);
        }
*/        
        if (search.keywords != null)
          for (String s: search.keywords) {
            if (s.length() > 0) {
              where.add("(u.introduction like ?" + ++index + ")");
              params.add("%"+s+"%");
            }
          }
        
        // combine into one query string
        if (where.size() > 0) 
          qs += " where " + String.join(" and ", where);        
        
        // native SQL query returns Adverts objects
        Query q = em.createNativeQuery(qs, User.class);   
        
        // set parameters
        for (int i = 0; i < params.size(); i++) 
          q.setParameter(i + 1, params.get(i));        

        return q.getResultList();
      
    }
}
