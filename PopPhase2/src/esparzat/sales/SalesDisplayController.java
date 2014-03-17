/**
 * Trinidad esparza CITP 290 Spring 2014 Most of the classes being used are use
 * as referance from
 *
 * @author hoffmann, zachary
 *
 *
 */
package esparzat.sales;

import esparzat.data.Employee;
import esparzat.data.Product;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Originally written by Zack Hoffmann modified and re-arrange by
 * Trinidad Esparza
 */
public class SalesDisplayController {

    private Employee emp;
    private Product[] invoice;
    private int invoiceSize;
    private int quantity;
    private SalesDisplayView view;
    private SalesDisplayModel model;
    private Scanner sc;

    public SalesDisplayController(Employee emp) {
        this.emp = emp;
        invoice = new Product[1000];
        invoiceSize = 0;
        view = new SalesDisplayView();
        model = new SalesDisplayModel();
        sc = new Scanner(System.in);
    }

    public void run() {
        invoice = new Product[1000];
        //Menu: options 1-4, option 4 is for log out.
        boolean exit = false;
        while (!exit) {
            view.printInvoice(Arrays.copyOf(invoice, invoiceSize));
            view.displayMenu();
            String choice = sc.nextLine();
            // Add
            switch (choice) {
                case "1":  // add
                    addProduct();
                    break;
                case "2":  //remove
                    removeProduct();
                    break;
                case "3":  //finish(go to phase2)
                    break;
                case "4":
                    exit = true; //log out
                    break;
                default:
                    System.out.println("Error: Invalid choice.");
                    break;  //not a valid choice.
                // Adds products to invoice

            }
        }
    }
//THIS WILL ADD A PRODUCT

    public void addProduct() {
        view.askForUpc();
        String upc = sc.nextLine();
        Product tempProduct = null;
        //Compares input product UPC with current inventory located in .data.Products.
        for (Product p : model.getInventory()) {
            if (p.getUpc().equals(upc)) {
                tempProduct = p;
                break;
            }
        }
// If product match was found ask to Quantity
        if (tempProduct != null) {
            view.askForQuantity();
            quantity = sc.nextInt();
            sc.nextLine();
            tempProduct.setQuantity(quantity);
            invoice[invoiceSize] = tempProduct;  //add the products in the invoice
            invoiceSize++;

        } //If NO UPC Provided
        else {
            view.printNoUPCProvidedError();
        }
        
       /** if (invoice == tempProuct){
            
        }*/
    }
//THIS WILL REMOVE A PRODUCT

    private void removeProduct() {
        view.askForUpc();
        String upc = sc.nextLine();
        Product tempProduct = null;
        // Compares input product UPC with current inventory located in .data.Products.
        for (Product p : model.getInventory()) {
            if (p.getUpc().equals(upc)) {
                tempProduct = p;
                break;
            }
        }
        // If product match was found ask for Quantity
        if (tempProduct != null) {
            view.askForQuantity();
            quantity = sc.nextInt();
            sc.nextLine();
            tempProduct.setQuantity(quantity);
            invoice[invoiceSize] = tempProduct;  //remove the products in the invoice
            invoiceSize--;
        } //If NO UPC Provided
        else {
            view.printNoUPCProvidedError();
        }
    }
}
