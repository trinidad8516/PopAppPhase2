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

/**
 *This displays when employee wants to log in. 
 * @author Futuro
 */
//being requested from controller class
public class DisplayLoginView {
    public void askForUsername() {
        System.out.print("Username: ");
    }

    public void askForPassword() {
        System.out.print("Password: ");
    }

    public void printLoginError() {
        System.out.println("Erro: Invalid username and/or password.");
        System.out.println();
    }
}
