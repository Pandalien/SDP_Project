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
    
    
    public List<Adverts> findByVarious(int suburb_id, int classification_id) {
        Query q = em.createNamedQuery("Adverts.findByVarious");
        q.setParameter("suburbId", new Suburb(suburb_id)); 
        q.setParameter("classificationId", new Classification(classification_id));         
        return q.getResultList();
    }
    
    
    public List<Adverts> findByVarious(int suburb_id, int classification_id, List<String> keywords) {

        // default find all
        String qs = "select * from Adverts";
        
        ArrayList<String> where = new ArrayList();
        
        if (SearchParams.validateSuburbID(suburb_id))
          where.add("suburb_id=" + suburb_id);
        
        if (SearchParams.validateClassificationID(classification_id))
          where.add("classification_id=" + classification_id);
        
        if (keywords != null)
          for (String s: keywords) 
            where.add("(content like '%"+s+"%' or title like '%"+s+"%')");
        
        // combine into one query string
        if (where.size() > 0) 
          qs += " where " + String.join(" and ", where);

        // native SQL query returns Adverts objects
        Query q = em.createNativeQuery(qs, Adverts.class);        
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
