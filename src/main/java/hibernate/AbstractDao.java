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

	EntityManagerFactory entityfactory;

	private Class<T> entityClass;

	public AbstractDao() {
		this.entityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public AbstractDao(Class<T> type,EntityManagerFactory entityfactory ) {
		this.entityClass = type;
		this.entityfactory =entityfactory;
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
		return em.createQuery( "from " + this.entityClass.getName() )
				.getResultList();        }

	@Override
	public List<T> readByName(String name) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

}
