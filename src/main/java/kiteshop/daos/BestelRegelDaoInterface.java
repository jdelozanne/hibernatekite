/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.daos;

import java.util.ArrayList;
import java.util.List;
import kiteshop.pojos.BestelRegel;
import kiteshop.pojos.Bestelling;

/**
 *
 * @author julia
 */
public interface BestelRegelDaoInterface {
    
    void createBestelRegel(BestelRegel regel);
    
    public List<BestelRegel> readBestelRegelsByBestelling(Bestelling bestelling);

    void updateBestelRegel(BestelRegel regel);

    void deleteBestelRegel(BestelRegel regel);
    
}
