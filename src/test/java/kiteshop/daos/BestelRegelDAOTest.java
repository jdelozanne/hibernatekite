/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.daos;

import java.util.ArrayList;
import kiteshop.pojos.BestelRegel;
import kiteshop.pojos.Bestelling;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author julia
 */
public class BestelRegelDAOTest {
    
    public BestelRegelDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createBestelRegel method, of class BestelRegelDAO.
     */
    @org.junit.Test
    public void testCreateBestelRegel() {
        System.out.println("createBestelRegel");
        BestelRegel regel = null;
        BestelRegelDAO instance = new BestelRegelDAO();
        instance.createBestelRegel(regel);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readBestelRegel method, of class BestelRegelDAO.
     */
    @org.junit.Test
    public void testReadBestelRegel() {
        System.out.println("readBestelRegel");
        int bestellingID = 0;
        BestelRegelDAO instance = new BestelRegelDAO();
        instance.readBestelRegel(bestellingID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateBestelRegel method, of class BestelRegelDAO.
     */
    @org.junit.Test
    public void testUpdateBestelRegel() {
        System.out.println("updateBestelRegel");
        BestelRegel regel = null;
        BestelRegelDAO instance = new BestelRegelDAO();
        instance.updateBestelRegel(regel);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteBestelRegel method, of class BestelRegelDAO.
     */
    @org.junit.Test
    public void testDeleteBestelRegel() {
        System.out.println("deleteBestelRegel");
        BestelRegel regel = null;
        BestelRegelDAO instance = new BestelRegelDAO();
        instance.deleteBestelRegel(regel);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readBestelRegelsByBestelling method, of class BestelRegelDAO.
     */
    @org.junit.Test
    public void testReadBestelRegelsByBestelling() {
        System.out.println("readBestelRegelsByBestelling");
        Bestelling bestelling = null;
        BestelRegelDAO instance = new BestelRegelDAO();
        ArrayList<BestelRegel> expResult = null;
        ArrayList<BestelRegel> result = instance.readBestelRegelsByBestelling(bestelling);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
