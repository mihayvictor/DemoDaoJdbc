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
		
		System.out.println("\n=== TEST 3: Department insert: ===");
		Department newDep = new Department(null, "Moda");
		departmentDao.insert(newDep);
		System.out.println("Department " + newDep.getId() + " inserted!");
		
		
		
		sc.close();
	}

}
