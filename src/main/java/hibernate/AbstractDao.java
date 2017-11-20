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

/**
 *
 * @author julia
 * @param <T>
 * @param <PK>
 *
 */
public abstract class AbstractDao<T extends Serializable> implements DaoInterface<T> {

    private Class<T> entityClass;

    public AbstractDao() {
        this.entityClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public AbstractDao(Class<T> type) {
        this.entityClass = type;
    }

    EntityManagerFactory entityfactory = Persistence.createEntityManagerFactory("hibertest");
    EntityManager em = entityfactory.createEntityManager();

    @Override
    public void delete(T domain) {
        domain = this.em.merge(domain);
        this.em.remove(domain);
    }

    @Override
    public void create(T domain) {
        em.getTransaction().begin();
        em.persist(domain);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(T domain) {
        em.getTransaction().begin();
        em.merge(domain);
        em.getTransaction().commit();
        
    }

    @Override
    public T readById(Serializable id) {
        em.getTransaction().begin();
        T object = em.find(this.entityClass, id);
        em.getTransaction().commit();
        return object;
    }
//nog uitzoeken hoe je een list kan vullen
    @Override
    public List<T> readAll() {
return em.createQuery( "from " + this.entityClass.getName() )
       .getResultList();        }

    @Override
    public List<T> readByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
