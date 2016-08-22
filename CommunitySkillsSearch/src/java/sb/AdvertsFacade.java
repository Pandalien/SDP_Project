/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb;

import entities.Adverts;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author andyc
 */
@Stateless
public class AdvertsFacade extends AbstractFacade<Adverts> {

    @PersistenceContext(unitName = "CommunitySkillsSearchPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdvertsFacade() {
        super(Adverts.class);
    }
    
}
