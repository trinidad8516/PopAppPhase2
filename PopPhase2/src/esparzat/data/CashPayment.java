/**
 * Trinidad esparza
 * CITP 290 
 * Spring 2014
 * Most of the classes being used are use as referance from
 * @author hoffmann, zachary
 *
 * 
 */
package esparzat.data;

/**
 *
 * @author Futuro
 */
public class CashPayment extends Payment {
    
	@Override
	public String toString() {
		return "Cash: " + this.getFormattedAmount();
	}

}