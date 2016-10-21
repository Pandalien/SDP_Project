/*
 * Software Development Practice, Stream 50 Team 2
 * Community Skills Search
 * EJB facade for Responder
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
    
    public Responders findByUserAndAdvertId(int userId, int advertId) {
        Query q = em.createNamedQuery("Responders.findByUserAndAdvertId");
        q.setParameter("userId", userId);
        q.setParameter("advertsId", advertId);
        
        List<Responders> responders = q.getResultList();
        
        if (responders != null && responders.size() > 0) {
            Responders responder = responders.get(0);
            if (responder != null)
                return responder;
        }
        return null;
    }
}
