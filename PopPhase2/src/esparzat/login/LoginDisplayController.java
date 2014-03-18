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
import esparzat.sales.SalesDisplayController;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Originally written by Zack Hoffmann modified and re-arrange by
 * Trinidad Esparza
 */
public class LoginDisplayController {
    public void run() {
        LoginDisplayView view = new LoginDisplayView();
        LoginDisplayModel model = new LoginDisplayModel();

        Scanner sc = new Scanner(System.in);

        Employee tempEmp = null;
        while (tempEmp == null) {

             //calls view class for username and password

            view.askForUsername();
            String username = sc.nextLine();
            view.askForPassword();
            char[] password = sc.nextLine().toCharArray();

            /**
             * Compares the input username and password with .data.Employee and
             * LoginDisplayMode.
             */
            for (Employee e : model.getEmployees()) {
                if (e.getUsername().equals(username)
                        && Arrays.equals(e.getPassword(), password)) {
                    tempEmp = e;
                    break;
                }
            }
            // displays error message if entered incorrectly 
            if (tempEmp == null) {
                view.printLoginError();
            }
        }  //end of the while loop

        /**
         * If the username and password match the user gets prompt to Sales
         * Display Phase
         */
        new SalesDisplayController(tempEmp).run();
        
        sc.close();
    }
}
