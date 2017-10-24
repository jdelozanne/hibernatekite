/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.controller;

/**
 *
 * @author julia
 */
public class HoofdController {

    private String database;

    public HoofdController(String db) {
        this.database = db;
    }
    
    public String getCurrentDatabase(){
        return this.database;
    }
}
