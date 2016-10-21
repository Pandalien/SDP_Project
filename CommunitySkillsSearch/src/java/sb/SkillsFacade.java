/*
 * Software Development Practice, Stream 50 Team 2
 * Community Skills Search
 * EJB facade for Skills
 */
package sb;

import entities.Skills;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author andyc
 */
@Stateless
public class SkillsFacade extends AbstractFacade<Skills> {

    @PersistenceContext(unitName = "CommunitySkillsSearchPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SkillsFacade() {
        super(Skills.class);
    }
    
}
