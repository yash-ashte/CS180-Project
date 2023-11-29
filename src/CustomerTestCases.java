import org.junit.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;
import java.time.*;

import static org.junit.Assert.assertEquals;

/**
 *
 *    CustomerTestCases
 *
 *    Tests for Customer Class
 */
public class CustomerTestCases {

    @Test
    public void testName() {

        Customer c1 = new Customer("Robert Smith 3", "Robert@gmail.com", "pass123", null, null);
        Customer c2 = new Customer(null, null, null, null, null);

        assertEquals(c1.getName(), "Robert Smith 3");
        assertEquals(c2.getName(), null);
        c1.setName("Robert 2");
        assertEquals(c1.getName(), "Robert 2");

    }

    @Test
    public void testEmail() {

        Customer c1 = new Customer("Robert Smith 3", "Robert@gmail.com", "pass123", null, null);
        Customer c2 = new Customer(null, null, null, null, null);

        assertEquals(c1.getEmailAddress(), "Robert@gmail.com");
        assertEquals(c2.getEmailAddress(), null);

    }

    @Test
    public void testPassword() {

        Customer c1 = new Customer("Robert Smith 3", "Robert@gmail.com", "pass123", null, null);
        Customer c2 = new Customer(null, null, null, null, null);

        assertEquals(c1.getPassword(), "pass123");
        assertEquals(c2.getPassword(), null);

    }

    @Test
    public void testEquals() {

        Customer c1 = new Customer("Robert Smith 3", "Robert@gmail.com", "pass123", null, null);
        Customer c2 = new Customer("Robert Smith 3", "Robert@gmail.com", "pass123", null, null);
        Customer c3 = new Customer("Jim Smith", "Robert@gmail.com", "pass456", null, null);

        assertEquals(c1.equals(c2), true);
        assertEquals(c2.equals(c3), false);

    }

    @Test
    public void testCSVLine() {

        Customer c1 = null;
        try {
            c1 = new Customer("bob,Rob@yahoo.com,pass123");
        } catch (FileFormatException e ) {
        }
        Customer c2 = new Customer("bob", "Rob@yahoo.com", "pass123", null, null);

        assertEquals(c1, c2);

    }

    @Test
    public void testCart() {

        Product p = new Product("My Product", "The Store", "A cool product", 10, 24, 4, 3);
        Product p2 = new Product("Product 2", "The Store", "A new product", 2, 18, 19, 1);
        Product p3 = new Product("Product 3", "Target", "A nice product", 3, 6, 2, 44);

        ArrayList<Product> pList = new ArrayList<Product>();
        ArrayList<Product> pListAdded = new ArrayList<Product>();
        ArrayList<Product> pListRemoved = new ArrayList<Product>();
        pList.add(p);
        pList.add(p2);

        ArrayList<Product> pList2 = new ArrayList<Product>(pList);

        pListAdded.add(p);
        pListAdded.add(p2);
        pListAdded.add(p3);

        pListRemoved.add(p);

        Customer c = new Customer("Bob", "email@email.com", "password", pList, null);
        Customer c2 = new Customer("Bob", "email@email.com", "password", pList2, null);

        c.addProduct(p3);
        c2.removeProduct(p2);

        assertEquals(c.getShoppingCart(), pListAdded);
        assertEquals(c2.getShoppingCart(), pListRemoved);
    }

    @Test
    public void testAddPurchase() {

        Purchase p1 = new Purchase(new Product("Product", "Store", "a good product", 0, 0, 0, 0), 2, 4);
        Purchase p2 = new Purchase(new Product("Product 2", "Store 2", "a better product", 0, 0, 0, 0), 1, 8);
        Purchase p3 = new Purchase(new Product("Product 3", "Store 3", "a worse product", 0, 0, 0, 0), 10, 4);

        ArrayList<Purchase> plist = new ArrayList<Purchase>();
        plist.add(p1);
        plist.add(p2);
        ArrayList<Purchase> plistAdded = new ArrayList<Purchase>();
        plistAdded.add(p1);
        plistAdded.add(p2);
        plistAdded.add(p3);

        Customer c = new Customer("Bob", "email@email.com", "password", null, plist);

        c.recordPurchaseHistory(new Product("Product 3", "Store 3", "a worse product", 0, 0, 0, 0), 10, 4);

        assertEquals(c.getPurchaseHistory(), plistAdded);

    }

    @Test
    public void testToString() {

        Customer c1 = new Customer("Robert Smith 3", "Robert@gmail.com", "pass123", null, null);

        String s = c1.toString();

        assertEquals("<Customer: name:Robert Smith 3, email:Robert@gmail.com>", s);
    }

    @Test
    public void testPurchaseHistory() {

        Purchase p1 = new Purchase(new Product("Product", "Store", "a good product", 0, 0, 0, 0), 2, 4, LocalDate.of(2022, 11, 10), LocalTime.of(2, 20, 4));
        Purchase p2 = new Purchase(new Product("Product 2", "Store 2", "a better product", 0, 0, 0, 0), 1, 8, LocalDate.of(2022, 11, 10), LocalTime.of(2, 20, 4));
        Purchase p3 = new Purchase(new Product("Product 3", "Store 3", "a worse product", 0, 0, 0, 0), 10, 4, LocalDate.of(2022, 11, 10), LocalTime.of(2, 20, 4));

        ArrayList<Purchase> plist = new ArrayList<Purchase>();
        plist.add(p1);
        plist.add(p2);
        plist.add(p3);

        Customer c1 = new Customer("Robert Smith 3", "Robert@gmail.com", "pass123", null, plist);

        c1.exportPurchaseHistory("Test_Case_Purchase_History.txt");

        Scanner reader;

        ArrayList<String> expected = new ArrayList<String>();
        expected.add("Purchase history for Robert Smith 3");
        expected.add("Product: 2 were purchased from Store for $8.00 on 2022-11-10 at 02:20:04.");
        expected.add("Product 2: 1 was purchased from Store 2 for $8.00 on 2022-11-10 at 02:20:04.");
        expected.add("Product 3: 10 were purchased from Store 3 for $40.00 on 2022-11-10 at 02:20:04.");

        ArrayList<String> fileContents = new ArrayList<String>();
        try {
            reader = new Scanner(new File("Test_Case_Purchase_History.txt"));

            while (reader.hasNextLine()) {
                fileContents.add(reader.nextLine());
            }

        } catch (Exception e) {
        }

        assertEquals(expected, fileContents);

        File file = new File("Test_Case_Purchase_History.txt");
        file.delete();

    }

    @Test
    public void testSaveCustomers() {

        Product p = new Product("My Product", "The Store", "A cool product", 10, 24, 4, 3);
        Product p2 = new Product("Product 2", "Walmart", "A new product", 2, 18, 19, 1);
        Product p3 = new Product("Product 3", "Target", "A nice product", 3, 6, 2, 44);

        Purchase pur1 = new Purchase(p, 2, 4, LocalDate.of(2022, 11, 10), LocalTime.of(2, 20, 4));
        Purchase pur2 = new Purchase(p2, 1, 8, LocalDate.of(2022, 11, 10), LocalTime.of(2, 20, 4));
        Purchase pur3 = new Purchase(p3, 10, 4, LocalDate.of(2022, 11, 10), LocalTime.of(2, 20, 4));

        ArrayList<Purchase> purchaseList = new ArrayList<Purchase>();
        purchaseList.add(pur1);
        purchaseList.add(pur2);
        purchaseList.add(pur3);

        ArrayList<Product> productList = new ArrayList<Product>();
        productList.add(p);
        productList.add(p2);

        Customer c1 = new Customer("Robert Smith 3", "Robert@gmail.com", "pass123", productList, purchaseList);
        Customer c2 = new Customer("Bill", "bill@yahoo.com", "mypassword", null, null);

        ArrayList<Customer> cList = new ArrayList<Customer>();
        cList.add(c1);
        cList.add(c2);

        Customer.writeCustomerFile(cList, "Test_Cases_Customers.txt");

        Scanner reader;

        ArrayList<String> fileContents = new ArrayList<String>();
        try {
            reader = new Scanner(new File("Test_Cases_Customers.txt"));

            while (reader.hasNextLine()) {
                fileContents.add(reader.nextLine());
            }

        } catch (Exception e) {
        }

        ArrayList<String> expected = new ArrayList<String>(List.of(("-"), ("Robert Smith 3,Robert@gmail.com,pass123"),
                ("<Cart>"), ("The Store,My Product"),
                ("Walmart,Product 2"), ("<History>"),
                ("The Store,My Product,2,4.0,2022-11-10,02:20:04"),
                ("Walmart,Product 2,1,8.0,2022-11-10,02:20:04"),
                ("Target,Product 3,10,4.0,2022-11-10,02:20:04"),
                (""), ("-"),("Bill,bill@yahoo.com,mypassword"),
                ("<Cart>"), ("<History>"), ("")));

        assertEquals(expected, fileContents);

    }

    @Test
    public void testLoadCustomers() {

        ArrayList<String> inputStrings = new ArrayList<String>(List.of(("-"), ("Robert Smith 3,Robert@gmail.com,pass123"),
                ("<Cart>"), ("The Store,My Product"),
                ("Walmart,Product 2"), ("<History>"),
                ("The Store,My Product,2,4.0,2022-11-10,02:20:04"),
                ("Walmart,Product 2,1,8.0,2022-11-10,02:20:04"),
                ("Target,Product 3,10,4.0,2022-11-10,02:20:04"),
                (""), ("-"), ("Bill,bill@yahoo.com,mypassword"),
                ("<Cart>"), ("<History>"), ("")));


        try {
            PrintWriter writer = new PrintWriter(new File("Test_input.txt"));
            for (String s: inputStrings) {
                writer.println(s);
            }
            writer.flush();

        } catch (Exception e) {
        }

        ArrayList<Customer> customers;
        try {
            customers = Customer.createCustomersFromFile("Test_input.txt");
        } catch (FileFormatException e) {
            customers = null;
        }

        Product p = new Product("My Product", "The Store", null, -1, -1, -1, -1);
        Product p2 = new Product("Product 2", "Walmart", null, -1, -1, -1, -1);
        Product p3 = new Product("Product 3", "Target", null, -1, -1, -1, -1);

        Purchase pur1 = new Purchase(p, 2, 4, LocalDate.of(2022, 11, 10), LocalTime.of(2, 20, 4));
        Purchase pur2 = new Purchase(p2, 1, 8, LocalDate.of(2022, 11, 10), LocalTime.of(2, 20, 4));
        Purchase pur3 = new Purchase(p3, 10, 4, LocalDate.of(2022, 11, 10), LocalTime.of(2, 20, 4));

        ArrayList<Purchase> purchaseList = new ArrayList<Purchase>();
        purchaseList.add(pur1);
        purchaseList.add(pur2);
        purchaseList.add(pur3);

        ArrayList<Product> productList = new ArrayList<Product>();
        productList.add(p);
        productList.add(p2);

        Customer c1 = new Customer("Robert Smith 3", "Robert@gmail.com", "pass123", productList, purchaseList);
        Customer c2 = new Customer("Bill", "bill@yahoo.com", "mypassword", null, null);

        ArrayList<Customer> expectedCustomers = new ArrayList<Customer>();
        expectedCustomers.add(c1);
        expectedCustomers.add(c2);

        assertEquals(expectedCustomers.get(0).getShoppingCart(), customers.get(0).getShoppingCart());


    }





}