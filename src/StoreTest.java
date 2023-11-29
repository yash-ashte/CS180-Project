import java.util.ArrayList;

/**
 * StoreTest.java
 *
 * Test cases for Store class.
 *
 * @author Yash Asthekar
 * @version 13/06/2022
 */
public class StoreTest {
    public static void main(String[] args) {
        Sale addSale = new Sale("Test cust", "test Prod", 10);
        Sale addSale2 = new Sale("Test cust2", "test Prod2", 15);
        ArrayList<Sale> sales = new ArrayList<Sale>();
        ArrayList<Sale> sales2 = new ArrayList<>();
        sales.add(addSale);
        sales2.add(addSale2);

        ArrayList<Product> products = new ArrayList<Product>();
        ArrayList<Product> products2 = new ArrayList<>();

        Store s1 = new Store("Store 1", products, sales, 0);
        // Store s2 = new Store("Store 2", ));
        Store s3 = new Store("Store 3", products2, sales2, 0);
        Product add = new Product("test", "Store 1", "test descr"
                , 1, 10.0, 1, 10);
        s1.addProduct(add);
        System.out.println(s1.getProducts().get(0));
        s1.addSale(add, "test cust", 3);
        s1.calcStoreRevenue();
        System.out.println(s1.getRevenue());

        ArrayList<Store> storeList = new ArrayList<>();
        storeList.add(s1);
        storeList.add(s3);
        Store.writeStoreFile(storeList);

        s1.deleteProduct(add);
        System.out.println(s1.toString());


    }
}