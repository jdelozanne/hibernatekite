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
        String create_account = "CREATE TABLE IF NOT EXISTS `" + DATABASE + "`..`account` (\n"
                + "  `accountID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,\n"
                + "  `gebruikersnaam` VARCHAR(45) NOT NULL,\n"
                + "  `wachtwoord` VARCHAR(45) NOT NULL,\n"
                + "  `account_type` VARCHAR(20) NULL DEFAULT NULL,\n"
                + "  PRIMARY KEY (`accountID`))\n"
                + "ENGINE = InnoDB";  // de engine kun je ook weghalen dan werkt het ook SP
        String create_product = "CREATE TABLE IF NOT EXISTS `" + DATABASE + "`.`product` (\n"
                + "  `productID` INT(10) NOT NULL,\n"
                + "  `productnaam` VARCHAR(45) NOT NULL,\n"
                + "  `voorraad` INT(5) NOT NULL,\n"
                + "  `prijs` DECIMAL(10,2) UNSIGNED NOT NULL,\n"
                + "  PRIMARY KEY (`productID`),\n"
                + "  UNIQUE INDEX `idProduct_UNIQUE` (`productID` ASC))\n"
                + "ENGINE = InnoDB";
        String create_klant = "CREATE TABLE IF NOT EXISTS `" + DATABASE + "`.`klant` (\n"
                + "  `KlantID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,\n"
                + "  `voornaam` VARCHAR(45) NOT NULL,\n"
                + "  `tussenvoegsel` VARCHAR(45) NULL DEFAULT NULL,\n"
                + "  `achternaam` VARCHAR(45) NOT NULL,\n"
                + "  `emailadres` VARCHAR(45) NULL DEFAULT NULL,\n"
                + "  `telefoonnummer` VARCHAR(20) NULL DEFAULT NULL,\n"
                + "  PRIMARY KEY (`KlantID`))\n"
                + "ENGINE = InnoDB";
        String create_bestelregel = "CREATE TABLE IF NOT EXISTS `" + DATABASE + "`.`bestel_regel` (\n"
                + "  `bestel_regelID` INT(10) NOT NULL,\n"
                + "  `productID` INT(10) NOT NULL,\n"
                + "  `aantal` INT(5) NOT NULL,\n"
                + "  `bestellingID` INT(10) UNSIGNED NULL,\n"
                + "  PRIMARY KEY (`bestel_regelID`),\n"
                + "  UNIQUE INDEX `bestel_regelID_UNIQUE` (`bestel_regelID` ASC),\n"
                + "  INDEX `fk_bestel_regel_product1_idx` (`productID` ASC),\n"
                + "  INDEX `bestellingID_idx` (`bestellingID` ASC),\n"
                + "  CONSTRAINT `fk_bestel_regel_product1`\n"
                + "    FOREIGN KEY (`productID`)\n"
                + "    REFERENCES `juliaworkshop`.`product` (`productID`)\n"
                + "    ON DELETE NO ACTION\n"
                + "    ON UPDATE NO ACTION,\n"
                + "  CONSTRAINT `bestellingID`\n"
                + "    FOREIGN KEY (`bestellingID`)\n"
                + "    REFERENCES `juliaworkshop`.`bestelling` (`bestellingID`)\n"
                + "    ON DELETE NO ACTION\n"
                + "    ON UPDATE NO ACTION)\n"
                + "ENGINE = InnoDB";
        String create_bestelling = "CREATE TABLE IF NOT EXISTS `" + DATABASE + "`.`bestelling` (\n"
                + "  `bestellingID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,\n"
                + "  `klantID` INT(10) UNSIGNED NOT NULL,\n"
                + "  `totaalprijs` DECIMAL(6,2) NULL,\n"
                + "  PRIMARY KEY (`bestellingID`),\n"
                + "  INDEX `klantID_idx` (`klantID` ASC),\n"
                + "  CONSTRAINT `klantID`\n"
                + "    FOREIGN KEY (`klantID`)\n"
                + "    REFERENCES `juliaworkshop`.`klant` (`KlantID`)\n"
                + "    ON DELETE NO ACTION\n"
                + "    ON UPDATE NO ACTION)\n"
                + "ENGINE = InnoDB";

        String create_adres = "CREATE TABLE IF NOT EXISTS `" + DATABASE + "`.`adres` (\n"
                + "  `adresID` INT UNSIGNED NOT NULL AUTO_INCREMENT,\n"
                + "  `klantIDadres` INT UNSIGNED NOT NULL,\n"
                + "  `straatnaam` VARCHAR(45) NOT NULL,\n"
                + "  `huisnummer` INT(5) UNSIGNED NOT NULL,\n"
                + "  `toevoeging` VARCHAR(3) NULL,\n"
                + "  `postcode` VARCHAR(6) NOT NULL,\n"
                + "  `woonplaats` VARCHAR(45) BINARY NOT NULL,\n"
                + "  `adres_type` VARCHAR(45) NULL,\n"
                + "  PRIMARY KEY (`adresID`),\n"
                + "  INDEX `klantID_idx` (`klantIDadres` ASC),\n"
                + "  CONSTRAINT `klantIDadres`\n"
                + "    FOREIGN KEY (`klantIDadres`)\n"
                + "    REFERENCES `juliaworkshop`.`klant` (`KlantID`)\n"
                + "    ON DELETE NO ACTION\n"
                + "    ON UPDATE NO ACTION)\n"
                + "ENGINE = InnoDB;";

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
            stat.executeUpdate(create_adres);

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

    public static void main(String args[]) {

        new DatabaseTest().initializeDatabase();
        new DatabaseTest().populateDatabase();

        KlantDAO klantDAO = new KlantDAO();

        logger.info("Beginning klantDAO readselectedklantenAchternaam");
        ArrayList<Klant> testKlant = klantDAO.readSelectedKlantenAchternaam("Pelgrom");
        System.out.println(testKlant);

    }

}
