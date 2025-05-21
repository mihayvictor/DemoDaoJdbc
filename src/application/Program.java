package application;

import java.util.Date;

import model.entities.Departement;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		Departement obj = new Departement(1, "Book");
		System.out.println(obj);
		
		Seller seller = new Seller(21, "Bob", "bob@gmail.com", new Date(), 3000.00, obj);
		
		System.out.println(seller);

	}

}
