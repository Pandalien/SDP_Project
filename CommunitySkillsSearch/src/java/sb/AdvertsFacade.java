/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb;

import entities.Adverts;
import entities.Classification;
import entities.Suburb;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
    
    
    public List<Adverts> findByVarious(int suburb_id, int classification_id) { //, List<String> keywords) {
        Query q = em.createNamedQuery("Adverts.findByVarious");
        q.setParameter("suburbId", new Suburb(suburb_id)); 
        q.setParameter("classificationId", new Classification(classification_id));         
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
