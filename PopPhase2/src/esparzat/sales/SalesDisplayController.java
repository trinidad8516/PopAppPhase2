/**
 * Trinidad esparza CITP 290 Spring 2014 Most of the classes being used are use
 * as referance from
 *
 * @author hoffmann, zachary
 *
 *
 */
package esparzat.sales;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Scanner;

import esparzat.data.Employee;
import esparzat.data.Product;
import esparzat.payment.CashPayment;
import esparzat.payment.CheckPayment;
import esparzat.payment.CreditPayment;
import esparzat.payment.Payment;



/**
 * @author Originally written by Zack Hoffmann modified and re-arrange by
 * Trinidad Esparza
 */
public class SalesDisplayController {

	private Employee emp;
	private Product[] invoice;
	private Payment[] payments;

	// Used to keep track of how many items are in each array.
	private int invoiceSize;
	private int paymentsSize;

	private SalesDisplayView view;
	private SalesDisplayModel model;

	private Scanner sc;

	private String lastUpc;

	/**
	 * Constructor for specifying the currently logged in employee.
	 * 
	 * @param emp
	 *            The employee currently logged in.
	 */
	public SalesDisplayController(Employee emp) {
		this.emp = emp;
		invoice = new Product[1000];
		payments = new Payment[100];
		invoiceSize = 0;
		paymentsSize = 0;
		view = new SalesDisplayView();
		model = new SalesDisplayModel();
		sc = new Scanner(System.in);
	}

	/**
	 * Kicks off operation of the controller.
	 * 
	 */
	public void run() {

		boolean exit = false;
		boolean payment = false;

		while (!exit) {

			// Begin Order Phase
			while (!exit && !payment) {
				view.printInvoice(Arrays.copyOf(invoice, invoiceSize),
						calcSubtotal());
				view.displayOrderMenu();
				String choice = sc.nextLine();
				// Add
				if (choice.equals("1")) {
					addProduct();
				}
				// Remove
				else if (choice.equals("2")) {
					removeProduct();
				}
				// Finish
				else if (choice.equals("3")) {
					if (invoiceSize == 0) {
						view.displayMustHaveProduct();
					} else {
						payment = true;
					}
				}
				// Log Out
				else if (choice.equals("4")) {
					exit = true;
					sc.close();
				} else if (choice.equals("5")) {
					if (emp.getAccessLevel().equals(Employee.MANAGER_LEVEL)
							|| emp.getAccessLevel()
									.equals(Employee.ADMIN_LEVEL)) {
						addNewUser();
					} else {
						System.out.println("You can't do that!");
					}
				}
				// Error - invalid choice
				else {
					view.displayInvalidChoice(choice);
				}
			}
			// End Order Phase

			// Begin Payment Phase
			if (payment) {
				BigDecimal totalDue = calcSubtotal().add(
						calcSubtotal().multiply(Payment.TAX_RATE));
				BigDecimal amountDue = totalDue;
				BigDecimal amountPaid = new BigDecimal(0);
				do {

					view.printInvoice(Arrays.copyOf(invoice, invoiceSize),
							calcSubtotal());
					view.printPayments(Arrays.copyOf(payments, paymentsSize),
							amountPaid, amountDue);
					view.displayPaymentMenu();
					String choice = sc.nextLine();

					// Add
					if (choice.equals("1")) {
						addPayment();
					}
					// Remove
					else if (choice.equals("2")) {
						removePayment();
					}
					// Error - invalid choice
					else {
						view.displayInvalidChoice(choice);
					}

					amountPaid = calcAmountPaid();
					amountDue = totalDue.subtract(amountPaid);
				} while (amountDue.compareTo(BigDecimal.ZERO) > 0);

				view.displayChangeDue(amountDue.multiply(new BigDecimal(-1)));

				payment = false;
				invoice = new Product[1000];
				payments = new Payment[100];
				invoiceSize = 0;
				paymentsSize = 0;
			}
			// End Payment Phase
		}

	}

	private void addNewUser() {
		Employee e = new Employee();
		System.out.print("Username: ");
		e.setUsername(sc.nextLine());
		System.out.print("Password: ");
		e.setPassword(sc.nextLine().toCharArray());
		System.out.print("Manager? (y/n) ");
		e.setAccessLevel(sc.nextLine().equalsIgnoreCase("y") ? Employee.MANAGER_LEVEL
				: Employee.EMPLOYEE_LEVEL);
		model.addNewEmployee(e);
		

	}

	/**
	 * Calculates the subtotal based on all products listed in the invoice.
	 * 
	 * @return The invoice subtotal
	 */
	private BigDecimal calcSubtotal() {

		BigDecimal subtotal = new BigDecimal(0);
		for (int i = 0; i < invoiceSize; i++) {
			Product p = invoice[i];
			subtotal = subtotal.add(p.getPrice().multiply(
					new BigDecimal(p.getQuantity())));
		}

		return subtotal;
	}

	/**
	 * Calculates the amount paid by the customer so far towards the current
	 * sale.
	 * 
	 * @return The total amount paid toward the sale
	 */
	private BigDecimal calcAmountPaid() {
		BigDecimal subtotal = new BigDecimal(0);
		for (int i = 0; i < paymentsSize; i++) {
			Payment p = payments[i];
			subtotal = subtotal.add(p.getAmount());
		}

		return subtotal;
	}

	/**
	 * Handles the adding of a product to the invoice.
	 */
	private void addProduct() {
		view.askForUpc();
		String upc = sc.nextLine();
		Product tempProduct = null;

		// If no UPC was entered and there is a previous one available..
		if (upc.trim().length() == 0 && lastUpc != null) {
			upc = lastUpc;
		}

		// Search for the product in the inventory
		for (Product p : model.getInventory()) {
			if (p.getUpc().equals(upc)) {
				// This is often referred to as a "copy constructor"
				// It ensures that the object in our invoice and the object
				// in our inventory remain separate data and will become
				// important later on.
				tempProduct = new Product(p);
				break;
			}
		}

		// If there is no match in the inventory...
		if (tempProduct == null) {
			view.displayProductNotFound(upc);
		}
		// ...otherwise proceed to add
		else {
			// Save this as the new previous UPC
			lastUpc = upc;

			// Bogus quantity value to check later
			int quantity = -1;

			// Repeat until the bogus quantity is set to something valid
			do {
				view.askForQuantity();
				String qs = sc.nextLine();

				// Did a little cheating here...
				// The second expression uses a "regular expression" to
				// perform pattern matching on the input. It ensures that it
				// only
				// contains numbers.
				if (qs.trim().length() >= 0 && qs.matches("^[0-9]+$")) {
					quantity = Integer.parseInt(qs);
				} else if (qs.trim().length() == 0) {
					quantity = 1;
				} else {
					view.displayInvalidQuantity(qs);
				}
			} while (quantity == -1);

			tempProduct.setQuantity(quantity);
			boolean match = false;

			// Check to see if the product is already in the invoice
			// Need to use indexed for loop here since much of our invoice
			// is null
			for (int i = 0; i < invoiceSize; i++) {
				// If it is, just increase its quantity
				Product p = invoice[i];
				if (p.getUpc().equals(tempProduct.getUpc())) {
					p.setQuantity(p.getQuantity() + tempProduct.getQuantity());
					match = true;
				}
			}

			// If it wasn't already in the invoice then add it
			if (!match) {
				invoice[invoiceSize] = tempProduct;
				invoiceSize++;
			}
		}
	}

	/**
	 * Handles the removal of a product from the invoice.
	 */
	private void removeProduct() {
		view.askForUpc();
		String upc = sc.nextLine();

		// If no UPC was entered and there is a previous one available..
		if (upc.trim().length() == 0 && lastUpc != null) {
			upc = lastUpc;
		}

		int match = -1;

		for (int i = 0; i < invoiceSize; i++) {
			if (invoice[i].getUpc().equals(upc)) {
				match = i;
			}
		}

		if (match == -1) {
			view.displayProductNotFound(upc);
		} else {

			// Bogus quantity value to check later
			int quantity = -1;

			// Repeat until the bogus quantity is set to something valid
			do {
				view.askForQuantity();
				String qs = sc.nextLine();

				// Did a little cheating here...
				// The second expression uses a "regular expression" to
				// perform pattern matching on the input. It ensures that it
				// only
				// contains numbers.
				if (qs.trim().length() >= 0 && qs.matches("^[0-9]+$")) {
					quantity = Integer.parseInt(qs);
				} else if (qs.trim().length() == 0) {
					quantity = 1;
				} else {
					view.displayInvalidQuantity(qs);
				}
			} while (quantity == -1);

			// If there is more present then the total to remove then just
			// decrease the quantity.
			if (invoice[match].getQuantity() > quantity) {
				invoice[match].setQuantity(invoice[match].getQuantity()
						- quantity);
			}
			// Otherwise, remove the whole product by writing over it in the
			// array. This is accomplished by shifting the remaining elements
			// over.
			else {
				for (int i = match; i < invoice.length - 1; i++) {
					invoice[i] = invoice[i + 1];
				}

				// Then just decrease the number of invoice spaces used
				invoiceSize--;
				// No need to remove the product in the last space. IF we add
				// another product later on it will be overwritten.
			}
		}

	}

	/**
	 * Handles the adding of a payment towards the sale.
	 */
	private void addPayment() {
		Payment p = null;

		do {
			view.displayPaymentTypeMenu();
			String choice = sc.nextLine();
			// Cash
			if (choice.equals("1")) {
				p = newCashPayment();
			}
			// Credit
			else if (choice.equals("2")) {
				p = newCreditPayment();
			}
			// Check
			else if (choice.equals("3")) {
				p = newCheckPayment();
			}
			// Error - invalid choice
			else {
				view.displayInvalidChoice(choice);
			}
		} while (p == null);

		payments[paymentsSize] = p;
		paymentsSize++;

	}

	/**
	 * Handles the removal of a payment from the sale.
	 */
	private void removePayment() {

		int paymentNumber = -1;

		do {
			view.askForPaymentNumber();
			String pns = sc.nextLine();

			if (pns.trim().length() >= 0 && pns.matches("^[0-9]+$")) {
				paymentNumber = Integer.parseInt(pns);

				if (paymentNumber > paymentsSize || paymentNumber < 1) {
					view.displayInvalidChoice(pns);
					paymentNumber = -1;
				}
			} else {
				view.displayInvalidChoice(pns);
			}

		} while (paymentNumber < 1);

		paymentNumber--;

		for (int i = paymentNumber; i < paymentsSize - 1; i++) {
			payments[i] = payments[i + 1];
		}

		paymentsSize--;

	}

	/**
	 * Gets a dollar amount - typically for a payment - from the user.
	 * 
	 * @return The dollar amount input
	 */
	private BigDecimal getAmount() {
		BigDecimal amount = null;

		do {
			view.askForAmount();
			String as = sc.nextLine();

			if (as.matches("^[0-9.]+$")) {
				amount = new BigDecimal(as);
			} else {
				view.displayInvalidQuantity(as);
			}
		} while (amount == null);

		return amount;
	}

	/**
	 * Creates a new cash payment from the user.
	 * 
	 * @return A new cash payment
	 */
	private Payment newCashPayment() {
		CashPayment p = new CashPayment();

		p.setAmount(getAmount());
		return p;
	}

	/**
	 * Creates a new credit payment from the user.
	 * 
	 * @return A new credit payment
	 */
	private Payment newCreditPayment() {
		CreditPayment p = new CreditPayment();

		p.setAmount(getAmount());
		view.askForCreditCardNumber();
		p.setCardNum(sc.nextLine());
		view.askForExpirationDate();
		p.setExpirationDate(sc.nextLine());

		return p;
	}

	/**
	 * Creates a new check payment from the user.
	 * 
	 * @return A new check payment
	 */
	private Payment newCheckPayment() {
		CheckPayment p = new CheckPayment();

		p.setAmount(getAmount());
		view.askForAccountNumber();
		p.setAccountNum(sc.nextLine());
		view.askForRoutingNumber();
		p.setRoutingNum(sc.nextLine());
		view.askForCheckNumber();
		p.setCheckNum(sc.nextLine());

		return p;
	}
}
