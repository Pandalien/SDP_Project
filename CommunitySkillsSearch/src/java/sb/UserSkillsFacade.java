/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb;

import entities.Adverts;
import entities.User;
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
    
    
}
