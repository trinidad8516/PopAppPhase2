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

import java.math.BigDecimal;
import java.text.NumberFormat;

import esparzat.data.Product;
import esparzat.payment.Payment;

/**
 *
 * @author Futuro
 */
class SalesDisplayView {

    public void printInvoice(Product[] invoice,  BigDecimal subtotal ) {
        
    	NumberFormat nf = NumberFormat.getCurrencyInstance();
    	
    	System.out.println("+----------------------------------+");

        for (Product p : invoice) {
            System.out.println("|  " + p);
 
        }

        System.out.println("+----------------------------------+");
		System.out.println("Subtotal: " + nf.format(subtotal));
		System.out.println("Tax: " + nf.format(subtotal.multiply(Payment.TAX_RATE)));
		System.out.println("Total: " + nf.format(subtotal.add(subtotal.multiply(Payment.TAX_RATE))));

	}
    
    public void printPayments(Payment[] payments, BigDecimal amountPaid,
			BigDecimal amountDue) {
		NumberFormat nf = NumberFormat.getCurrencyInstance();

		System.out.println("+----------------------------------+");
		for (Payment p : payments) {
			System.out.println("|  " + p);
		}

		System.out.println("+----------------------------------+");
		System.out.println("Amount Paid: " + nf.format(amountPaid));
		System.out.println("Amount Due: " + nf.format(amountDue));

	}


    public void displayOrderMenu() {
        System.out.println("Welcome to Point of Purchase System by Elcaro");
        System.out.println("Please choose one of the following:");
        System.out.println("1) Add");
        System.out.println("2) Remove");
        System.out.println("3) Finish");
        System.out.println("4) Log Out");
		System.out.println("5) Users");
		System.out.println("6) Inventory");
    }
    public void displayPaymentMenu() {
		System.out.println("Please choose one of the following:");
		System.out.println("1) Add Payment");
		System.out.println("2) Remove Payment");
    }
    
    public void displayUserManagementtMenu() {
		System.out.println("Please choose one of the following:");
		System.out.println("1) Add Employee");
		System.out.println("2) Remove Employee");
		System.out.println("3) Modify Employee");
    }
    
    public void displayUserInventoryMenu() {
		System.out.println("Please choose one of the following:");
		System.out.println("1) Add products");
		System.out.println("2) Remove products");
		System.out.println("3) Modify products");
    }

    public void askForUpc() {
        System.out.print("UPC: ");
    }

    public void askForQuantity() {
        System.out.print("Quantity: ");
    }
    public void displayProductNotFound(String upc) {
	    	System.out.println("Error: Product not present.");
    }
    
    public void displayInvalidQuantity(String qs) {
		System.out.println("Error: Could not remove the desired quantity");
	}

	public void displayInvalidChoice(String choice) {
		System.out.println(choice + " is not a valid choice.");
	}

	public void displayMustHaveProduct() {
		System.out
				.println("You must have at least one product in your invoice to complete a sale.");
	}

	public void displayChangeDue(BigDecimal changeDue) {
		NumberFormat nf = NumberFormat.getCurrencyInstance();

		System.out.println("Change Due: " + nf.format(changeDue));

	}
	
	public void displayPaymentTypeMenu() {
		System.out.println("Please choose one of the following:");
		System.out.println("1) Cash");
		System.out.println("2) Credit");
		System.out.println("3) Check");

	}
	
	public void askForAmount() {
		System.out.print("Payment Amount: ");
	}

	public void askForCreditCardNumber() {
		System.out.print("Credit Card Number: ");
	}

	public void askForExpirationDate() {
		System.out.print("Expiration Date: ");
	}

	public void askForAccountNumber() {
		System.out.print("Account Number: ");
	}

	public void askForRoutingNumber() {
		System.out.print("Routing Number: ");
	}

	public void askForCheckNumber() {
		System.out.println("Check Number: ");
	}

	public void askForPaymentNumber() {
		System.out.println("Line number of payment to remove: ");

	}

}