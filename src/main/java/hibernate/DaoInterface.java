/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author julia
 * @param <T>
 * @param <PK>
 */
public interface DaoInterface <T, PK extends Serializable> {
     
    public void create(T domain);
         
    public void update(T domain);
         
    public void delete(T domain);
     
    public T readById(Serializable id);
    
    public List<T> readAll();
    
    public List<T> readByName(String name);
}
