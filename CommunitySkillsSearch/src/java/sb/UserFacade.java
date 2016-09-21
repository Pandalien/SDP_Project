/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb;

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
public class UserFacade extends AbstractFacade<User> {
    @PersistenceContext(unitName = "CommunitySkillsSearchPU")
    private EntityManager em;
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    
    //return user object, require username and password. this funciont can be used for user logging in
    public User findByUsernameAndPassword(Object username, Object password){
        Query q = em.createNamedQuery("User.findByUsernameAndPassword");
        q.setParameter("name", username);
        q.setParameter("password", password);
        
        List<User> users = q.getResultList();
        
        if (users != null && users.size() > 0) {
            return users.get(0);
        }else{
            return null;
        }
    }
}
