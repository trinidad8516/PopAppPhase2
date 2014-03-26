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
public class CreditPayment extends Payment {

    private String cardNum;
    private String expirationDate;

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
   
    @Override
	public String toString() {
		return "Credit (#" + cardNum + "): " + this.getFormattedAmount();
	}
}
