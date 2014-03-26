/**
 * Trinidad esparza
 * CITP 290 
 * Spring 2014
 * Most of the classes being used are use as referance from
 * @author hoffmann, zachary
 *
 * 
 */
package esparzat.login;

import esparzat.data.Employee;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This arrays are use for Employee Login * @author Futuro
 */
public class RunGetEmployeeFiel {

	/**
	 * Provides an array of all employees in the system.
	 * 
	 * @return All employees
	 */
	public List<Employee> getEmployees() {

		List<Employee> emps = new ArrayList<Employee>();

		// Load remaining user
		File f = new File("employees.txt");

		if (!f.exists()) {
			try {
				f.createNewFile();
				insertAdminUser(f);
			} catch (IOException e) {
				System.out.println("Could not create employee file.");
			}
		}

		Scanner sc = null;
		try {
			sc = new Scanner(f);
		} catch (FileNotFoundException e) {
			System.out.println("The file was not  employee found.");
		}

		/**
		 * Will continue to read from the file one line at a time, until there
		 * are no remaining lines.
		 */
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

	private void insertAdminUser(File f) {
		PrintWriter pw = null;

		try {
			pw = new PrintWriter(f);
		} catch (FileNotFoundException e) {
			System.out.println("Could not locate file.");
		}

		pw.println("ADMIN" + "\t" + "administrator1" + "\t"
				+ Employee.ADMIN_LEVEL);

		pw.close();
	}
}
