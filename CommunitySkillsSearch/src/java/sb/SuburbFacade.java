/*
 * Software Development Practice, Stream 50 Team 2
 * Community Skills Search
 * EJB facade for Suburb
 */
package sb;

import entities.Suburb;
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
public class SuburbFacade extends AbstractFacade<Suburb> {

    @PersistenceContext(unitName = "CommunitySkillsSearchPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SuburbFacade() {
        super(Suburb.class);
    }
    
    public Suburb findById(int id) {
        Query q = em.createNamedQuery("Suburb.findById");
        q.setParameter("id", id);

        List<Suburb> Suburb = q.getResultList();

        if (Suburb != null && Suburb.size() > 0) {
            return Suburb.get(0);
        } else {
            return null;
        }
    }
}
