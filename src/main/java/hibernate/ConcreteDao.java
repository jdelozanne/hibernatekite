/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManagerFactory;

/**
 *
 * @author julia
 * @param <T>
 */
public class ConcreteDao<T extends Serializable> extends AbstractDao<T> implements DaoInterface<T>{

    public ConcreteDao() {
    }

    public ConcreteDao(Class<T> type, EntityManagerFactory entityfactory) {
        super(type,entityfactory );
    }
    
    @Override
    public List<T> readByName(String name) {
        return super.readByName(name); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<T> readAll() {
        return super.readAll(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T readById(Serializable id) {
        return super.readById(id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(T domain) {
        super.update(domain); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(T domain) {
        super.create(domain); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(T domain) {
        super.delete(domain); //To change body of generated methods, choose Tools | Templates.
    }
    
}
