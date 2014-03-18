/**
 * Trinidad esparza
 * CITP 290 
 * Spring 2014
 * Most of the classes being used are use as reference from
 * @author hoffmann, zachary
 *
 * 
 */
package esparzat.data;

/**
 *
 * @author Futuro
 */
public class Employee {

    public static final String MANAGER_LEVEL = "MANAGER";
    public static final String EMPLOYEE_LEVEL = "EMPLOYEE";
    public static final String ADMIN_LEVEL = "ADMIN";
    //created getters and setters
    private String username;
    private char[] password;
    private String accessLevel;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLeve) {
        this.accessLevel = accessLeve;
    }
}
