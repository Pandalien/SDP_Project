/*
 * Software Development Practice, Stream 50 Team 2
 * Community Skills Search
 * EJB facade
 */
package sb;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import utils.StringUtils;

/**
 *
 * @author andyc
 */
public abstract class AbstractFacade<T> {

    @PersistenceContext(unitName = "CommunitySkillsSearchPU")
    private EntityManager em;
    
    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
    //find by any column name e.g email name suburb, column name is case-sensitive
    public List<T> findByXxx(String colName, Object value){
        String col = colName.toLowerCase();
        String Col = StringUtils.capitalize(colName);
        
        Query q = em.createNamedQuery(entityClass.getSimpleName() + ".findBy" + Col);
        q.setParameter(col, value); 
        
        return q.getResultList();
    }
    
    //check if one db entry is exist
    public boolean isExist(String colName, String value){
        
        List<T> items = findByXxx(colName, value);
        return items != null && !items.isEmpty();
    }
}
