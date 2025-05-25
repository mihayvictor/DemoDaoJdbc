package application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("=== TEST 1: Seller findbyId: ===");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
		
		System.out.println("\n=== TEST 2: Seller findbyDepartment: ===");
		Department dep = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(dep);
		
		for(Seller obj: list) {
			System.out.println(obj);
		}
		
		System.out.println("\n=== TEST 3: Seller findAll: ===");
		list = sellerDao.findAll();
		
		for(Seller obj: list) {
			System.out.println(obj);
		}
		
		System.out.println("\n=== TEST 4: Seller insert: ===");
		Seller newseller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.00, dep);
		sellerDao.insert(newseller);
		System.out.println("Inserd! new id = " + newseller.getId());
		
	}

}
