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

/**
 *This arrays are use for Employee Login
 *  * @author Futuro
 */

public class LoginDisplayModel {

    public Employee[] getEmployees() {

        Employee[] emps = new Employee[3];

        emps[0] = new Employee();
        emps[0].setUsername("trinidad");
        emps[0].setPassword("Citp290isgreat".toCharArray());

        emps[1] = new Employee();
        emps[1].setUsername("hoffmanz");
        emps[1].setPassword("123Password".toCharArray());

        emps[2] = new Employee();
        emps[2].setUsername("sparta");
        emps[2].setPassword("isMyPetDog".toCharArray());

        return emps;
    }
}
