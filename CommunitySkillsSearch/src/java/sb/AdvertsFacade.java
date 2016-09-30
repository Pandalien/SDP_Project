/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb;

import entities.Adverts;
import entities.Classification;
import entities.Suburb;
import entities.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.*;
import utils.SearchParams;

/**
 *
 * @author andyc, AD
 */
@Stateless
@LocalBean
public class AdvertsFacade extends AbstractFacade<Adverts> {

    @PersistenceContext(unitName = "CommunitySkillsSearchPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdvertsFacade() {
        super(Adverts.class);
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
    public List<Adverts> findByVarious(SearchParams search) {
        // default find all query string
        String qs = "select a.* from adverts a";
        
        // list of "where" conditions:        
        ArrayList<String> where = new ArrayList();
        ArrayList<Object> params = new ArrayList();

        int index = 0;


        if (SearchParams.validateSkillsId(search.skills_id)) {
          qs += ", requirements r";
          where.add("r.skills_id=?" + ++index + " and r.adverts_id=a.id");
          params.add(search.skills_id);
        }
        
        if (SearchParams.validateSuburbId(search.suburb_id)) {
          where.add("a.suburb_id=?" + ++index);
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
              where.add("(a.content like ?" + ++index + " or a.title like ?" + ++index + ")");
              params.add("%"+s+"%");
              params.add("%"+s+"%");
            }
          }
        
        // combine into one query string
        if (where.size() > 0) 
          qs += " where " + String.join(" and ", where);        
        
        // native SQL query returns Adverts objects
        Query q = em.createNativeQuery(qs, Adverts.class);   
        
        // set parameters
        for (int i = 0; i < params.size(); i++) 
          q.setParameter(i + 1, params.get(i));        

        return q.getResultList();
    }
    
    //find advert by user object (not Id)
    public List<Adverts> findByUser(User object){
        Query q = em.createNamedQuery("Adverts.findByUserId");
        q.setParameter("user", object); 
        return q.getResultList();
    }
    
/*    
    @PermitAll
    public int verify() {
        String result = null;
        Query q = em.createNamedQuery("Adverts.findAll");
        Collection entities = q.getResultList();
        int s = entities.size();
        for (Object o : entities) {
            Adverts a = (Adverts)o;
            System.out.println("Found: " + a.getTitle());
        }

        return s;
    }

    @PermitAll
    public void insert(Adverts a) {
      em.persist(a);
    }    
*/
    
}
