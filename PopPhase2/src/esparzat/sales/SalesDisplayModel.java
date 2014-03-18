/**
 * Trinidad esparza
 * CITP 290 
 * Spring 2014
 * Most of the classes being used are use as referance from
 * @author hoffmann, zachary
 *
 * 
 */
package esparzat.sales;

import esparzat.data.Product;
import esparzat.data.Employee;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 *
 * @author Futuro
 */
public class SalesDisplayModel {

	/**
	 * Provides an array of all products in the inventory.
	 * 
	 * @return All products
	 */
	public Product[] getInventory() {
		Product[] ps = new Product[3];

		ps[0] = new Product();
		ps[0].setUpc("1");
		ps[0].setDescription("BANANAS");
		ps[0].setPrice(new BigDecimal(.59));
		ps[0].setNumInStock(10);

		ps[1] = new Product();
		ps[1].setUpc("2");
		ps[1].setDescription("APPLES");
		ps[1].setPrice(new BigDecimal(.79));
		ps[1].setNumInStock(23);

		ps[2] = new Product();
		ps[2].setUpc("3");
		ps[2].setDescription("PEARS");
		ps[2].setPrice(new BigDecimal(.65));
		ps[2].setNumInStock(5);

		return ps;
	}

	public void addNewEmployee(Employee emp) {
		
		List<Employee> emps = getEmployees();
		emps.add(emp);
		
		File f = new File("employees.txt");
		
		PrintWriter pw = null;

		try {
			pw = new PrintWriter(f);
		} catch (FileNotFoundException e) {
			System.out.println("Could not locate file.");
		}
		
		for(Employee em: emps) {
			pw.println(em.getUsername() + "\t" + (new String(em.getPassword())) + "\t" + em.getAccessLevel());
		}
		
		pw.close();
	}
	
	public List<Employee> getEmployees() {

		List<Employee> emps = new ArrayList<Employee>();
		
		File f = new File("employees.txt");

		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				System.out.println("Could not create file.");
			}
		}

		Scanner sc = null;

		try {
			sc = new Scanner(f);
		} catch (FileNotFoundException e) {
			System.out.println("Could not open file.");
		}

		while (sc.hasNext()) {
			Employee e = new Employee();
			String empData = sc.nextLine();
			String[] empFields = empData.split("\t", -1);
			e.setUsername(empFields[0]);
			e.setPassword(empFields[1].toCharArray());
			e.setAccessLevel(empFields[2]);
			emps.add(e);
		}

		return emps;
	}


}