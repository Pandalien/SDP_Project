/*
 * Software Development Practice, Stream 50 Team 2
 * Community Skills Search
 * EJB facade for classifiction
 */
package sb;

import entities.Classification;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author andyc
 */
@Stateless
public class ClassificationFacade extends AbstractFacade<Classification> {

    @PersistenceContext(unitName = "CommunitySkillsSearchPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClassificationFacade() {
        super(Classification.class);
    }
    
}
