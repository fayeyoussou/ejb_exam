package ejb_exam.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ejb_exam.config.Constante;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public abstract class DaoEntite<T, ID extends Serializable> {

    @PersistenceContext(unitName = Constante.PERSISTENCE_NAME)
    protected EntityManager em;

    public T save(T entity) {
        try {
            em.persist(entity);
            return entity;
        } catch (Exception e) {
        	log.error(e.getMessage());
        	return null;
            
        }
    }

    public Boolean delete(ID id) {
        T entity = em.find(getTypeClass(), id);
        if (entity != null) {
            em.remove(entity);
            return true;
        }
        return false;
    }

    public T edit(T entity) {
        try {
            return em.merge(entity);
        } catch (Exception e) {
        	log.error(e.getMessage());
        	return null;
        }
    }

    public T getById(ID id) {
        return em.find(getTypeClass(), id);
    }

    public List<T> filterByFieldName(String fieldName, Object value) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(getTypeClass());
        Root<T> root = query.from(getTypeClass());
        query.where(builder.equal(root.get(fieldName), value));
        return em.createQuery(query).getResultList();
    }
    public List<T> list() {
        CriteriaQuery<T> criteria = em.getCriteriaBuilder().createQuery(getTypeClass());
        criteria.select(criteria.from(getTypeClass()));
        return em.createQuery(criteria).getResultList();
    }

    protected abstract Class<T> getTypeClass();
}


