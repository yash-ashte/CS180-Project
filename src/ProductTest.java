import java.io.FileNotFoundException;
import java.util.ArrayList;
/**
 * ProductTest
 * <p>
 * Test cases for Product class
 */

public class ProductTest {
    public static void main(String[] args) {
        Product product = new Product("test", "placeholder store", "placeholder desc", 0,
                9.99, 0, 0);
        Product product2 = new Product("test", "placeholder store", "desc2", 0, 9.81, 10, 10);
        System.out.println(product.equals(product2));
        if (product.recordSale(10)) System.out.println(product.getQuantity());
        else System.out.println("Cant sell");
        System.out.println(product);
        try {
            System.out.println(Product.parseProduct("test,4,34,3"));
        } catch (FileFormatException e) {
            System.out.println("Cannot parse");
        }
        product.printUI();
        Product nullProduct = null;

        System.out.println();
        try {
            ArrayList<Product> productList = Product.readProductFile("productList.txt");
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("no file");
        }
        product.exportProduct();

    }
}