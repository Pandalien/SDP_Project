/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb;

import entities.Suburb;
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
