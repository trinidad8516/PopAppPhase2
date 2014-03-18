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

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 *
 * @author Futuro
 */
public class Product {

    private String upc;
    private int quantity;
    private BigDecimal price;
    private int numInStock;
    private String description;
    public int setQuantity;

	public Product() {
		
	}
    
    public Product(Product p) {
    	this.upc = p.getUpc();
		this.description = p.getDescription();
		this.price = p.getPrice();
		this.quantity = p.getQuantity();
		this.numInStock = p.getNumInStock();
	}

	public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getNumInStock() {
        return numInStock;
    }

    public void setNumInStock(int numInStock) {
        this.numInStock = numInStock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        return description + " " + nf.format(price) + " x " + quantity + " = "
                + nf.format(price.multiply(new BigDecimal(quantity)));
    }
}
