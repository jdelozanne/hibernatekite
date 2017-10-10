/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.controller;

import java.util.logging.Logger;
import kiteshop.daos.BestelRegelDAO;
import kiteshop.daos.BestelRegelDAOInterface;
import kiteshop.pojos.BestelRegel;
import kiteshop.test.ProjectLog;

/**
 *
 * @author julia
 */
public class BestelRegelController {
    private final Logger logger = ProjectLog.getLogger();

    BestelRegelDAOInterface bestelRegelDAO;
    
    public BestelRegelController(){
        bestelRegelDAO = new BestelRegelDAO();
    }
    
    public void createBestelRegel(BestelRegel regel){
        bestelRegelDAO.createBestelRegel(regel);
        
    }
    
    public void showBestelling(int bestellingID){
        bestelRegelDAO.readBestelRegel(bestellingID);
    }
   
     
}

