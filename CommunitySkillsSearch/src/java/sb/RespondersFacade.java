/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb;

import entities.Responders;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author andyc
 */
@Stateless
public class RespondersFacade extends AbstractFacade<Responders> {

    @PersistenceContext(unitName = "CommunitySkillsSearchPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RespondersFacade() {
        super(Responders.class);
    }
    
    public List<Responders> findByUserId(int id) {
        Query q = em.createNamedQuery("Responders.findByUserId");
        q.setParameter("userId", id);

        return q.getResultList();
    }
    
    public List<Responders> findByAdvertsId(int id) {
        Query q = em.createNamedQuery("Responders.findByAdvertsId");
        q.setParameter("advertsId", id);

        return q.getResultList();
    }
}
