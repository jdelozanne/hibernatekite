/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.daos;

import java.util.ArrayList;
import kiteshop.pojos.BestelRegel;

/**
 *
 * @author julia
 */
public interface BestelRegelDAOInterface {
    
    void createBestelRegel(BestelRegel regel);

    void readBestelRegel(int bestellingID);

    void updateBestelRegel(BestelRegel regel);

    void deleteBestelRegel(BestelRegel regel);
    
}
