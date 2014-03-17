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
import java.math.BigDecimal;

/**
 *
 * @author Futuro
 */
class SalesDisplayModel {

    Product[] getInventory() {
        Product[] ps = new Product[5];
     
        ps[0] = new Product();
        ps[0].setUpc("04963406");
        ps[0].setDescription(" Coca-Cola Classic Can 12oz");
        ps[0].setPrice(new BigDecimal(.60));
        ps[0].setNumInStock(500);

        ps[1] = new Product();
        ps[1].setUpc("60411088");
        ps[1].setDescription("Lay's Orig. Chips 180g");
        ps[1].setPrice(new BigDecimal(1.25));
        ps[1].setNumInStock(100);

        ps[2] = new Product();
        ps[2].setUpc("60411090");
        ps[2].setDescription("Lay's SC&O Chips 180g");
        ps[2].setPrice(new BigDecimal(1.25));
        ps[2].setNumInStock(50);

        ps[3] = new Product();
        ps[3].setUpc("08830380");
        ps[3].setDescription("Apple Yogurt Smoothie 9oz");
        ps[3].setPrice(new BigDecimal(1.55));
        ps[3].setNumInStock(100);

        ps[4] = new Product();
        ps[4].setUpc("08830381");
        ps[4].setDescription("Mango Blended Yogurt 32oz");
        ps[4].setPrice(new BigDecimal(2.05));
        ps[4].setNumInStock(50);

        return ps;
    }
}
