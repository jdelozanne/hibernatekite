/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate;

import java.io.Serializable;

/**
 *
 * @author julia
 * @param <T>
 */
public class ConcreteDao<T extends Serializable> extends AbstractDao<T> implements DaoInterface<T>{
    
}
