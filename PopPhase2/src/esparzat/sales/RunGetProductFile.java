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
public class RunGetProductFile {

	/**
	 * Provides an array of all products in the inventory.
	 * 
	 * @return All products
	 */
	
	public List<Product> getProduct() {

		List<Product> prod = new ArrayList<Product>();
		File p = new File("products.txt");
	
		if (!p.exists()) {
			try {
				p.createNewFile();
				insertProduct(p);
			} catch (IOException e) {
				System.out.println("Could not create products file.");
			}
		}
		
		Scanner sc = null;
		try {
			sc = new Scanner(p);
		} catch (FileNotFoundException e) {
			System.out.println("The file was not products found.");
		}

		/**
		 * Will continue to read from the file one line at a time, until there
		 * are no remaining lines.
		 */
		while (sc.hasNext()) {
			Product p1 = new Product();
			String prodData = sc.nextLine();
			String[] prodFields = prodData.split("\t", -1);
			p1.setUpc(prodFields[0]);
			p1.setDescription(prodFields[1]);
			p1.setPrice(new BigDecimal(prodFields[2]));
			p1.setQuantity(Integer.valueOf(prodFields[3]));
			p1.setNumInStock(Integer.valueOf(prodFields[4]));
			prod.add(p1);
		}

		return prod;
	}
	
	
	
	private void insertProduct(File p) {
	PrintWriter pw = null;
	
	try {
		pw = new PrintWriter(p);
	} catch (FileNotFoundException e) {
		System.out.println("Could not locate file.");
}
	}}


	
		
	
	