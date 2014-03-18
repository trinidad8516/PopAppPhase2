/**
 * Trinidad esparza
 * CITP 290 
 * Spring 2014
 * Most of the classes being used are use as referance from
 * @author hoffmann, zachary
 *
 * 
 */
package esparzat.payment;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 *
 * @author Futuro
 */
public class Payment {

    /**
     * This is the current tax rate.
     */
    public static final BigDecimal TAX_RATE = new BigDecimal(0.06);
    private BigDecimal amount;

    /**
     * Accesses this payment's dollar amount.
     *
     * @return The dollar amount of this payment.
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Updates this payment's dollar amount.
     *
     * @param amount The amount to set the payment's dollar amount to.
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
	/**
	 * Returns the amount in a currency formatted String.
	 * 
	 * @return The amount as a String
	 */
    public String getFormattedAmount() {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		return nf.format(amount);
	}
}