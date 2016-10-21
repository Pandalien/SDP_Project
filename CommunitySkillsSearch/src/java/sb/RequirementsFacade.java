/*
 * Software Development Practice, Stream 50 Team 2
 * Community Skills Search
 * EJB facade for Requirements
 */
package sb;

import entities.Requirements;
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
public class RequirementsFacade extends AbstractFacade<Requirements> {

    @PersistenceContext(unitName = "CommunitySkillsSearchPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RequirementsFacade() {
        super(Requirements.class);
    }
    
    //find Requirements by ad id
    public List<Requirements> findByAdvertsId(int id) {
        Query q = em.createNamedQuery("Requirements.findByAdvertsId");
        q.setParameter("advertsId", id);
        return q.getResultList();
    }
    
    public List<Requirements> findBySkillsId(int id) {
        Query q = em.createNamedQuery("Requirements.findBySkillsId");
        q.setParameter("skillsId", id);
        return q.getResultList();
    }
}
