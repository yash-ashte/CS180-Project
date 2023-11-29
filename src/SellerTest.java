import java.util.*;
/**
 * Project 4 - Seller Test
 *
 * A class to test Seller class's functionality
 */
public class SellerTest {

    public static void main(String[] args) {

        ArrayList<Product> products = new ArrayList<>();
        ArrayList<Store> stores = new ArrayList<Store>();
        ArrayList<Sale> saleHistory = new ArrayList<>();
        //PRODUCT CONSTRUCTOR PARAM
        //String name, String storeName, String desc, int quantity, double price, int sales, double revenue
        //STORE CONSTRUCTOR PARAM
        //String storeName, ArrayList<Product> products, ArrayList<Sale> saleHistory, double revenue
        //SALE CONSTRUCTOR PARAM
        //String customerName, String productName, double revenue

        Product product = new Product("test", "placeholder store", "placeholder desc", 0,
                9.99, 0, 0);
        Product product2 = new Product("test2", "placeholder store", "desc2", 0, 9.81, 10, 10);
        products.add(product);
        products.add(product2);

        Sale sale = new Sale("John Doe", "test", 0);
        Sale sale2 = new Sale("Jane Doe", "test2", 0);
        saleHistory.add(sale);
        saleHistory.add(sale2);

        stores.add(new Store("Store 1", products, saleHistory, 0));
        stores.add(new Store("Store 2", products, saleHistory, 0));

        Seller s = new Seller("email", "password", stores);
        Seller s2 = new Seller("email2", "password2", stores);

        //Write seller file test
        ArrayList<Seller> sellers = new ArrayList<Seller>(List.of(s,s2));
        Seller.writeSellerFile(sellers);

        //getSellers test
        ArrayList<Seller> test = Seller.getSellers();

        //toString test
        System.out.println(test);
        System.out.println(sellers);

        //test writeSellerFile
        boolean writeTest = Seller.writeSellerFile(test);

        //test addStore
        Store storeThree = new Store("Store 3", products, saleHistory, 0);
        s.addStore(storeThree);
        System.out.println(s.getStores());

        //test deleteStore
        s.removeStore(storeThree);
        System.out.println(s.getStores());
    }

}