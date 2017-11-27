/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import kiteshop.pojos.Bestelling;
import kiteshop.pojos.Klant;

/**
 *
 * @author julia
 * @param <T>
 * @param <PK>
 *
 */
public abstract class AbstractDao<T extends Serializable> implements DaoInterface<T> {

    EntityManagerFactory entityfactory;

    private Class<T> entityClass;

    public AbstractDao() {
        this.entityClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public AbstractDao(Class<T> type, EntityManagerFactory entityfactory) {
        this.entityClass = type;
        this.entityfactory = entityfactory;
    }

    @Override
    public void delete(T domain) {
        EntityManager em = this.entityfactory.createEntityManager();
        T d = em.find(this.entityClass, 3);
        em.getTransaction().begin();
        em.remove(d);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void create(T domain) {
        EntityManager em = entityfactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(domain);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(T domain) {
        EntityManager em = entityfactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(domain);
        em.getTransaction().commit();
        em.close();

    }

    @Override
    public T readById(Serializable id) {
        EntityManager em = entityfactory.createEntityManager();
        em.getTransaction().begin();
        T object = em.find(this.entityClass, id);
        em.getTransaction().commit();
        return object;
    }
    //nog uitzoeken hoe je een list kan vullen

    @Override
    public List<T> readAll() {
        EntityManager em = entityfactory.createEntityManager();
        return em.createQuery("from " + this.entityClass.getName())
                .getResultList();
    }

    @Override
    public List<T> readByName(String table, String name) {
        EntityManager em = entityfactory.createEntityManager();
        CriteriaBuilder builder = this.entityfactory.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(this.entityClass);
        Root<T> root = criteria.from(this.entityClass);
        criteria.select(root);
        criteria.where(builder.like(root.get(table), name));

        List<T> names = em.createQuery(criteria).getResultList();

        return names;
    }
    
    @Override
    public List<T> readByNameLike(String table, String name) {
        EntityManager em = entityfactory.createEntityManager();
        CriteriaBuilder builder = this.entityfactory.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(this.entityClass);
        Root<T> root = criteria.from(this.entityClass);
        criteria.select(root);
        criteria.where(builder.like(root.get(table), "%"+name+"%"));

        List<T> names = em.createQuery(criteria).getResultList();

        return names;
    }

    @Override
    public T readOneByName(String table, String name) {
EntityManager em = entityfactory.createEntityManager();
        CriteriaBuilder builder = this.entityfactory.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(this.entityClass);
        Root<T> root = criteria.from(this.entityClass);
        criteria.select(root);
        criteria.where(builder.equal(root.get(table), name));
        T resultName = null;

        List<T> names = em.createQuery(criteria).getResultList();
        if(!names.isEmpty()){
        resultName = names.get(0);
        }

        return resultName;    }
    
    @Override
    public List<T> readByForeignkey(String table, int id) {
        EntityManager em = entityfactory.createEntityManager();
        CriteriaBuilder builder = this.entityfactory.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(this.entityClass);
        Root<T> root = criteria.from(this.entityClass);
        criteria.select(root);
        criteria.where(builder.equal(root.get(table), id));

        List<T> orders = em.createQuery(criteria).getResultList();

        return orders;
    }

}
