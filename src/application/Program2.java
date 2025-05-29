package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		System.out.println("=== TEST 1: Department findbyId: ===");
		Department dep = departmentDao.findById(8);
		System.out.println(dep);
		
		System.out.println("\n=== TEST 2: Department findbyAll: ===");
		List <Department> objs = departmentDao.findAll();
		
		for (Department obj : objs) {
			System.out.println(obj);
		}
		
		/*
		 * System.out.println("\n=== TEST 3: Department insert: ==="); Department newDep
		 * = new Department(null, "Moda"); departmentDao.insert(newDep);
		 * System.out.println("Department " + newDep.getId() + " inserted!");
		 * 
		 * System.out.println("\n=== TEST 4: Department update: ==="); dep =
		 * departmentDao.findById(1); dep.setName("Computers");
		 * departmentDao.update(dep);
		 */
		
		System.out.println("\n=== TEST 5: Department delete: ===");
		System.out.print("Enter by Id for delete test: ");
		int id = sc.nextInt();
		departmentDao.deleteById(id);
		Department newDep1 = departmentDao.findById(id);
		if (newDep1 == null) {
			System.out.println("Deleted completed!");
		}
		
		
		
		sc.close();
	}

}
