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
public interface DaoInterface <T extends Serializable> {
     
    public void create(T domain);
         
    public void update(T domain);
         
    public void delete(T domain);
     
    public T readById(Serializable id);
    
    public List<T> readAll();
    
    public List<T> readByName(String table, String name);
    
    public T readOneByName(String table, String name);
    
    public List<T> readByForeignkey(String table, int id);

	List<T> readByNameLike(String table, String name);
}
