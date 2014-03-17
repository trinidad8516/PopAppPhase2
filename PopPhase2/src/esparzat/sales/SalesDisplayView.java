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

import esparzat.data.Product;

/**
 *
 * @author Futuro
 */
class SalesDisplayView {

    public void printInvoice(Product[] invoice) {
        System.out.println("+----------------------------------+");
        /*
         for (int i = 0; i < invoice.length; i++) {
         System.out.println(invoice[i]);
         }*/


        for (Product p : invoice) {
            System.out.println("|  " + p);
            
            
        }

        System.out.println("+----------------------------------+");
        System.out.println();
    }

    public void displayMenu() {
        System.out.println("Welcome to Point of Purchase System by Elcaro");
        System.out.println("Please choose one of the following:");
        System.out.println("1) Add");
        System.out.println("2) Remove");
        System.out.println("3) Finish");
        System.out.println("4) Log Out");
    }

    /**
     * ask for the UPC
     */
    public void askForUpc() {
        System.out.print("UPC: ");
    }

    /**
     * ask for the quantity
     */
    public void askForQuantity() {
        System.out.print("Quantity: ");
    }

    /**
     * not matching UPC
     */
    public void printNoMatchingUPC() {
        System.out.println("Error: Invalid UPC");
        System.out.println();
    }

    /**
     * no upc provided by employee
     */
    public void printNoUPCProvidedError() {
        System.out.println("Error: No UPC provided");
        System.out.println();
    }

    public void printQuantityError() {
        System.out.println("Erro: Could not remove desired quantity.");
        System.out.println();
    }
    //remove 

    public void printNotInInvetoryError() {
        System.out.println("Error: Product not present.");
        System.out.println();
    }
}
