/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.daos;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;

import kiteshop.pojos.Klant;
import kiteshop.test.ProjectLog;
import testConnection.DBConnectInit;

/**
 *
 * @author Steef P
 */
public class DatabaseTest {

    private static final Logger logger = ProjectLog.getLogger();

    Connection connection;

    public DatabaseTest() {
    	DBConnect.setPathOfActivePropopertyFiletoTest();	
        this.connection = DBConnect.getConnection();
    }

    private static final String DATABASE = "TestKiteshop";

    void initializeDatabase() {
        logger.info("Entering initializeDatabase()");
        
        // Prepare the SQL statements to drop the DATABASE and recreate itff
        //@Julia, dit is allemaal gecopy/paste van de tekst die verschijnt als je forward engineer doet

        String dropDatabase = "DROP DATABASE IF EXISTS " + DATABASE;
        String createDatabase = "CREATE DATABASE IF NOT EXISTS " + DATABASE;
        String create_account = "CREATE TABLE IF NOT EXISTS `" + DATABASE + "`.`account`  (\n"
                + "  `accountID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,\n"
                + "  `gebruikersnaam` VARCHAR(45) NOT NULL,\n"
                + "  `wachtwoord` VARCHAR(45) NOT NULL,\n"
                + "  `Accountcol` VARCHAR(45) NULL DEFAULT NULL,\n"
                + "  PRIMARY KEY (`accountID`))\n"
                + "ENGINE = InnoDB";  // de engine kun je ook weghalen dan werkt het ook SP
        String create_product = "CREATE TABLE IF NOT EXISTS `" + DATABASE + "`.`product` (\n"
                + "  `idProduct` INT(11) NOT NULL AUTO_INCREMENT,\n"
                + "  `productnaam` VARCHAR(45) NOT NULL,\n"
                + "  `omschrijving` VARCHAR(60) NOT NULL,\n"
                + "  `prijs` DECIMAL(10,2) UNSIGNED NOT NULL,\n"
                + "  PRIMARY KEY (`idProduct`),\n"
                + "  UNIQUE INDEX `idProduct_UNIQUE` (`idProduct` ASC))\n"
                + "ENGINE = InnoDB";
        String create_klant = "CREATE TABLE IF NOT EXISTS `" + DATABASE + "`.`klant`(\n"
                + "  `KlantID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,\n"
                + "  `voornaam` VARCHAR(45) NOT NULL,\n"
                + "  `tussenvoegsel` VARCHAR(45) NULL DEFAULT NULL,\n"
                + "  `achternaam` VARCHAR(45) NOT NULL,\n"
                + "  `emailadres` VARCHAR(45) NULL DEFAULT NULL,\n"
                + "  `straatnaam` VARCHAR(45) NOT NULL,\n"
                + "  `huisnummer` INT(11) NOT NULL,\n"
                + "  `toevoeging` VARCHAR(45) NULL DEFAULT NULL,\n"
                + "  `postcode` VARCHAR(45) NOT NULL,\n"
                + "  `plaats` VARCHAR(45) NOT NULL,\n"
                + "  `telefoonnummer` VARCHAR(20) NOT NULL,\n"
                + "  PRIMARY KEY (`KlantID`),\n"
                + "  UNIQUE INDEX `telefoonnummer_UNIQUE` (`telefoonnummer` ASC))\n"
                + "ENGINE = InnoDB";
        String create_bestelregel = "CREATE TABLE IF NOT EXISTS `" + DATABASE + "`.`bestel_regel` (\n"
                + "  `bestel_regelID` INT(11) NOT NULL AUTO_INCREMENT,\n"
                + "  `aantal` INT(11) NOT NULL,\n"
                + "  `Bestelling_bestellingID` INT(10) UNSIGNED NOT NULL,\n"
                + "  `product_idProduct` INT(11) NOT NULL,\n"
                + "  PRIMARY KEY (`bestel_regelID`),\n"
                + "  UNIQUE INDEX `bestel_regelID_UNIQUE` (`bestel_regelID` ASC),\n"
                + "  INDEX `fk_Bestel_regel_Bestelling1_idx` (`Bestelling_bestellingID` ASC),\n"
                + "  INDEX `fk_bestel_regel_product1_idx` (`product_idProduct` ASC),\n"
                + "  CONSTRAINT `fk_Bestel_regel_Bestelling1`\n"
                + "    FOREIGN KEY (`Bestelling_bestellingID`)\n"
                + "    REFERENCES `" + DATABASE + "`.`bestelling` (`bestellingID`)\n"
                + "    ON DELETE NO ACTION\n"
                + "    ON UPDATE NO ACTION,\n"
                + "  CONSTRAINT `fk_bestel_regel_product1`\n"
                + "    FOREIGN KEY (`product_idProduct`)\n"
                + "    REFERENCES `" + DATABASE + "`.`product` (`idProduct`)\n"
                + "    ON DELETE NO ACTION\n"
                + "    ON UPDATE NO ACTION)\n"
                + "ENGINE = InnoDB";
        String create_bestelling = "CREATE TABLE IF NOT EXISTS `" + DATABASE + "`.`bestelling` (\n"
                + "  `bestellingID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,\n"
                + "  `klantID` INT(10) UNSIGNED NOT NULL,\n"
                + "  `bestel_regelID` INT(11) NOT NULL,\n"
                + "  `totaalprijs` DECIMAL(10,2) NOT NULL,\n"
                + "  PRIMARY KEY (`bestellingID`),\n"
                + "  INDEX `klantID_idx` (`klantID` ASC),\n"
                + "  CONSTRAINT `klantID`\n"
                + "    FOREIGN KEY (`klantID`)\n"
                + "    REFERENCES `" + DATABASE + "`.`klant` (`KlantID`)\n"
                + "    ON DELETE NO ACTION\n"
                + "    ON UPDATE NO ACTION)\n"
                + "ENGINE = InnoDB";

        try {

        	Connection initConnection = DBConnectInit.getConnection();
            Statement stat = initConnection.createStatement();
         // Executes the given SQL statement, which may be an INSERT, UPDATE, or DELETE statement or an SQL statement that returns nothing, such as an SQL DDL statement. ExecuteQuery kan niet gebruikt worden voor DDL statements
          
            stat.executeUpdate(dropDatabase);
            stat.executeUpdate(createDatabase); 
            stat.executeUpdate(create_account);
            stat.executeUpdate(create_product);
            stat.executeUpdate(create_klant);
            stat.executeUpdate(create_bestelling);
            stat.executeUpdate(create_bestelregel);
           
            /*
                stat.executeUpdate(trigger);
             */

        } catch (SQLException ex) {
            logger.info("SQLException" + ex);
        }
    }

    void populateDatabase() {
    	logger.info("Entering populate database");

    	// Prepare the SQL statements to insert the test data into the DATABASE
    	String insert_account = "INSERT INTO `" + DATABASE + "`.`account` (`gebruikersnaam`, `wachtwoord`) VALUES ('Steef2', 'Bier2');";
    	String insert_product = "INSERT INTO `" + DATABASE + "`.`product` (`idProduct`, `productnaam`, `omschrijving`, `prijs`) VALUES ('1', 'Cabrinha Drifter', 'Cabrinha Drifter 2017 Kite Only Red/Blue - 4,5 meter', '719.00'), ('2', 'Cabrinha Chaos', 'Cabrinha Chaos 2017 Kite Only Yellow/Orange - 5,5 meter', '719.00'),('3', 'Brunotti Virtuoso', 'Brunotti Dimension Kiteboard 136 (model 2015)', '399.00');";
    	String insert_klant = "INSERT INTO `" + DATABASE + "`.`klant` (`KlantID`, `voornaam`, `achternaam`, `emailadres`, `straatnaam`, `huisnummer`, `postcode`, `plaats`, `telefoonnummer`) VALUES ('1', 'Steef', 'Pelgrom', 'stevey@hotmail.com', 'Hendriklaan', '38', '5034KL', 'Tilburg', '06-56847965');";
    	String insert_bestelling = "INSERT INTO `" + DATABASE + "`.`bestelling` (`bestellingID`, `klantID`) VALUES ('1', '1');";
    	String insert_bestelregel = "INSERT INTO `" + DATABASE + "`.`bestel_regel` (`bestel_regelID`, `aantal`, `Bestelling_bestellingID`, `product_idProduct`) VALUES ('1', '4', '1', '2'), ('2', '1', '1', '2');";
    	
  
        try {

            // Execute the SQL statements to insert the test data into the DATABASE
            Statement stat = connection.createStatement();
            
            stat.executeUpdate(insert_account);
            stat.executeUpdate(insert_product);
            stat.executeUpdate(insert_klant);
            //stat.executeUpdate(insert_bestelling);
            //stat.executeUpdate(insert_bestelregel);
          

        } catch (SQLException ex) {
            logger.info("SQLException" + ex);
        }
    }
    
    public static void main(String args[]){
    	
    	
    	
    	new DatabaseTest().initializeDatabase();
    	new DatabaseTest().populateDatabase();
    	
    	KlantDAO klantDAO = new KlantDAO();
    	
    	logger.info("Beginning klantDAO readselectedklantenAchternaam");
    	ArrayList<Klant> testKlant = klantDAO.readSelectedKlantenAchternaam("Pelgrom");
    	System.out.println(testKlant);
    	
    	
    }
    	

}
