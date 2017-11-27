package setupdatabase;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;

import hibernate.AbstractDao;

import kiteshop.controller.AccountController;

import kiteshop.pojos.Account;
import kiteshop.pojos.Adres;
import kiteshop.pojos.AdresType;
import kiteshop.pojos.Klant;
import kiteshop.pojos.Product;

@Configurable
public class PeopleFactory {


	@Autowired @Qualifier("KlantDao")
	private static AbstractDao klantDAO;

	@Autowired @Qualifier("AdresDao")
	private static AbstractDao adresDAO;

	@Autowired @Qualifier("AccountDao")
	static
	AbstractDao accountDAO;

	@Autowired @Qualifier("ProductDao")
	static
	AbstractDao productdao;


	public static void createPeople(int i, EntityManagerFactory emf){

		String[] fn = {"Bram", "Tom", "Klaas", "Paul", "Steef", "Julia", "Jeroen", "Inge", "Sanne", "Jeanette"};

		String[] ln = {"Pelgrom", "Lozanne", "Brandsma", "Kort", "Lang", "Jansen", "Monnik", "Johnson", "Quin", "Morgan"};

		String[] sn = {"Kerkstraat","Schoolstraat", "Molenstraat", "Dorpsstraat", "Molenweg", "Kurt Callostraat", "Kwikstaart", "Langs de Baan", "Lavendelheide", "Leermos" }; 

		for(int j =0; j<i; j++){
			int rand1 = (int)(Math.random() *10);
			int rand2 = (int)(Math.random() *10);
			int rand3 = (int)(Math.random() *10);
			Klant k = new Klant();

			String voornaam = fn[rand1];

			k.setVoornaam(voornaam);
			String achternaam = ln[rand2];
			k.setAchternaam(achternaam);

			String email= voornaam+"@"+achternaam+".com";
			k.setEmail(email);

			String Telnr= "06-"+rand1+rand2+rand1+rand2+rand1+rand2+rand1+rand2;
			k.setTelefoonnummer(Telnr);

			Adres adres = new Adres();
			String straatnaam = sn[rand3];
			adres.setStraatnaam(straatnaam);

			int nr = rand1 + rand2 + rand3;
			adres.setHuisnummer(nr);

			String postcode = "1"+rand1+rand2+"0"+"GA";
			adres.setPostcode(postcode);

			String plaats = "Amsterdam";
			adres.setWoonplaats(plaats);
			adres.setAdresType(AdresType.BEZOEKADRES);
			k.setBezoekAdres(adres);

			
			adresDAO.create(adres);


		
			klantDAO.create(k);




		}
	}

	public static void createProducts(EntityManagerFactory emf){

		Account account = new Account();
		account.setGebruikersnaam("Julia");
		account.setWachtwoord("Pulia5");
		Account account2 = new Account();
		account2.setGebruikersnaam("Steef");
		account2.setWachtwoord("Peef5");

		
		accountDAO.create(account);
		accountDAO.create(account2);


		Product p = new Product("Cabrinha Contra", new BigDecimal(1224), 100);
		Product p1 = new Product("Wolkensturmer Spiderkites", new BigDecimal(169), 200);
		Product p2 = new Product("Peter Lynn Escape", new BigDecimal( 799), 50);
		Product p3 = new Product("Peter Lynn Voltage", new BigDecimal(269), 100);		
		Product p4 = new Product("Peter Lynn Swell ", new BigDecimal(1239), 100);
		Product p5 = new Product("Peter Lynn Fury", new BigDecimal(1224), 100);
		Product p6 = new Product("Brunotti Virtuoso kiteboard", new BigDecimal(399), 100);
		Product p7 = new Product("Brunotti Jade Kiteboard", new BigDecimal(389), 75);
		Product p8 = new Product("F-One Bandit", new BigDecimal(629), 100);
		Product p9 = new Product("F-One Furtive", new BigDecimal(999), 100);
		Product p10 = new Product("F-One Breeze", new BigDecimal(1149), 75);

	
		productdao.create(p);
		productdao.create(p2);
		productdao.create(p3);
		productdao.create(p4);
		productdao.create(p5);
		productdao.create(p6);
		productdao.create(p7);
		productdao.create(p8);
		productdao.create(p9);
		productdao.create(p10);



	}

}
