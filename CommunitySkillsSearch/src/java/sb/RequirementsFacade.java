/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb;

import entities.Adverts;
import entities.Requirements;
import entities.User;
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
}
