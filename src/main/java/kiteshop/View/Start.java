/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.View;

/**
 *
 * @author Steef P
 */
public class Start {
    
    public static void main(String[] args) {
		new HoofdController(new DaoFactorySql()).start();

	}
    
}
