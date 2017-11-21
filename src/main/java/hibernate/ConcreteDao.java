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
    public List<T> readByName(String table, String name) {
        return super.readByName(table, name); //To change body of generated methods, choose Tools | Templates.
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

    @Override
    public T readOneByName(String table, String name) {
        return super.readOneByName(table, name); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<T> readByForeignkey(String table, int id) {
        return super.readByForeignkey(table, id); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
