/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.daos;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author julia
 */
public interface DaoInterface <T> {
    public List<T> loadAll();
     
    public void save(T domain);
         
    public void update(T domain);
         
    public void delete(T domain);
     
    public T get(Serializable id);
     
}
