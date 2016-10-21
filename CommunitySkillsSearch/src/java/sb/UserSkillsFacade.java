/*
 * Software Development Practice, Stream 50 Team 2
 * Community Skills Search
 * EJB facade for UserSkills
 */
package sb;

import entities.UserSkills;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author andyc, AD
 */
@Stateless
public class UserSkillsFacade extends AbstractFacade<UserSkills> {

    @PersistenceContext(unitName = "CommunitySkillsSearchPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserSkillsFacade() {
        super(UserSkills.class);
    }
    
    //find advert by user object (not Id)
    public List<UserSkills> findBySkillsId(int skills_id){
        Query q = em.createNamedQuery("UserSkills.findBySkillsId");
        q.setParameter("skillsId", new Integer(skills_id)); 
        return q.getResultList();
    }
    public List<UserSkills> findByUserId(int id){
        Query q = em.createNamedQuery("UserSkills.findByUserId");
        q.setParameter("userId", new Integer(id)); 
        return q.getResultList();
    }
    
}
