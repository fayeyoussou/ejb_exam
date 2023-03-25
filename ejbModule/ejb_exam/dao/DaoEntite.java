package ejb_exam.dao;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.jboss.logging.Logger;

import ejb_exam.config.Constante;


public abstract class DaoEntite<T, ID extends Serializable> {
	private static final Logger log = Logger.getLogger(DaoEntite.class);
    @PersistenceContext(unitName = Constante.PERSISTENCE_NAME)
    protected EntityManager em;

    public T save(T entity) {
        try {
            em.persist(entity);
            return entity;
        } catch (Exception e) {
        	log.error(e.fillInStackTrace());
        	return null;
            
        }
    }

    public Boolean delete(ID id) {
    	try {
    		T entity = em.find(getTypeClass(), id);
            if (entity != null) {
                em.remove(entity);
                return true;
            }
            return false;
		} catch (Exception e) {
			log.error(e.fillInStackTrace());
			return false;
		}
        
    }

    public T edit(T entity) {
        try {
            return em.merge(entity);
        } catch (Exception e) {
        	log.error(e.fillInStackTrace());
        	return null;
        }
    }

    public T getById(ID id) {
    	try {
    		return em.find(getTypeClass(), id);
		} catch (Exception e) {
			log.error(e.fillInStackTrace());
			return null;
		}
        
    }

    public List<T> filterByFieldName(String fieldName, Object value) {
    	try {
    		CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<T> query = builder.createQuery(getTypeClass());
            Root<T> root = query.from(getTypeClass());
            query.where(builder.equal(root.get(fieldName), value));
            return em.createQuery(query).getResultList();
		} catch (Exception e) {
			log.error(e.fillInStackTrace());
			return Collections.emptyList();
		}
        
    }
    public T findOneByFieldName(String fieldName, Object value) {
        try {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<T> query = builder.createQuery(getTypeClass());
            Root<T> root = query.from(getTypeClass());
            query.where(builder.equal(root.get(fieldName), value));
            TypedQuery<T> typedQuery = em.createQuery(query).setMaxResults(1);
            List<T> resultList = typedQuery.getResultList();
            return resultList.isEmpty() ? null : resultList.get(0);
        } catch (Exception e) {
            log.error(e.fillInStackTrace());
            return null;
        }
    }
    public List<T> list() {
    	try {
    		CriteriaQuery<T> criteria = em.getCriteriaBuilder().createQuery(getTypeClass());
            criteria.select(criteria.from(getTypeClass()));
            return em.createQuery(criteria).getResultList();
		} catch (Exception e) {
			log.error(e.fillInStackTrace());
			return Collections.emptyList();
		}
        
    }

    protected abstract Class<T> getTypeClass();
}


