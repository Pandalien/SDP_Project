/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb;

import entities.Messages;
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
public class MessagesFacade extends AbstractFacade<Messages> {

    @PersistenceContext(unitName = "CommunitySkillsSearchPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MessagesFacade() {
        super(Messages.class);
    }
    
    //get unread messages
    public List<Messages> findByReceiverIdAndRead(User user, Boolean read) {
        Query q = em.createNamedQuery("Messages.findByReceiverIdAndRead");
        q.setParameter("receiverId", user);
        q.setParameter("read", read);
        return q.getResultList();
    }
    
    //get sent messages
    public List<Messages> findBySenderIdAndRead(User user, Boolean read) {
        Query q = em.createNamedQuery("Messages.findBySenderIdAndRead");
        q.setParameter("senderId", user);
        q.setParameter("read", read);
        return q.getResultList();
    }
}
