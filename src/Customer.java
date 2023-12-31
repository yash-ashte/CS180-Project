import java.util.*;
import java.io.*;
import java.time.*;


/**
 *
 *    Customer
 *
 *    A class that represents a customer
 *
 */
public class Customer extends User{

    private String name;

    private ArrayList<Product> shoppingCart;

    private ArrayList<Purchase> purchaseHistory;

    //Constructors
    public Customer (String name, String emailAddress, String password, ArrayList<Product> shoppingCart,
                     ArrayList<Purchase> purchaseHistory) {
        super(emailAddress, password);
        this.name = name;
        this.shoppingCart = shoppingCart;
        this.purchaseHistory = purchaseHistory;
    }

    public Customer (String name, String emailAddress, String password) {
        super(emailAddress, password);
        this.name = name;
        shoppingCart = new ArrayList<Product>();
        purchaseHistory = new ArrayList<Purchase>();
    }

    public Customer (String csvFileLine) throws FileFormatException {
        String[] params = csvFileLine.split(",");
        if (params.length != 3)
            throw new FileFormatException("Customer file is malformed");
        name = params[0];
        super.setEmail(params[1]);
        super.setPassword(params[2]);
        shoppingCart = null; //cart/history will be created through file-reading program
        purchaseHistory = null;
    }

    //Edit arrays
    public boolean addProduct(Product product) {
        if (shoppingCart.indexOf(product) != -1) {
            return false;
        } else {
            shoppingCart.add(product);
            return true;
        }
    }

    public boolean removeProduct(Product product) {
        if (shoppingCart.indexOf(product) == -1) {
            return false;
        } else {
            shoppingCart.remove(product);
            return true;
        }
    }

    public void recordPurchaseHistory(Product product, int quantity, double price) {
        Purchase p = new Purchase(product, quantity, price);
        purchaseHistory.add(p);
    }

    public void recordPurchaseHistory(Purchase p) {
        purchaseHistory.add(p);
    }

    //Printing/Exporting Methods
    @Override
    public String toString() {
        return "<Customer: name:" + name + ", email:" + super.getEmail() + ">";
    }

    public String customerToFile() { //Formats data of customer for storage in a file
        String customerString = "-\n";
        customerString += name + "," + super.getEmail() + "," + super.getPassword() + "\n";

        customerString += "<Cart>\n";
        if (shoppingCart != null) {
            for (Product p : shoppingCart) {
                customerString += p.getStoreName() + "," + p.getName() + "\n";
            }
        }

        customerString += "<History>\n";
        if (purchaseHistory != null) {
            for (Purchase p : purchaseHistory) {
                customerString += p.purchaseToCSV();
            }
        }

        return customerString;
    }


    public boolean exportPurchaseHistory(String fileName) { //Exporting History for the Customer 
        PrintWriter writer;

        try {
            writer = new PrintWriter(fileName);

            writer.println("Purchase history for " + name);
            writer.flush();

            for (Purchase p : purchaseHistory) {
                writer.println(p.purchaseHistoryToExport());
                writer.flush();
            }

            writer.close();
            return true;

        } catch (IOException e) {
            return false;
        }
    }

    //Static method- takes a list of all customers and updates the file.
    public static boolean writeCustomerFile(ArrayList<Customer> customersToAdd, String fileName) {
        PrintWriter writer;

        try {
            writer = new PrintWriter(new FileOutputStream((new File(fileName)), false));

            for (Customer c : customersToAdd){
                writer.println(c.customerToFile());
            }

            writer.flush();
            writer.close();
            return true;

        } catch (IOException e) {
            return false;
        }
    }

    //Takes in a customer file (list of all customers) and returns a list of customers
    public static ArrayList<Customer> createCustomersFromFile(String filename) throws FileFormatException {

        ArrayList<Customer> customerList = new ArrayList<Customer>();

        try {
            Scanner fileReader = new Scanner(new File(filename));

            while (fileReader.hasNextLine()) {

                String line = fileReader.nextLine();

                if (line.equals("-")) {
                    String customerLine = fileReader.nextLine();

                    Customer c = new Customer(customerLine);

                    line = fileReader.nextLine();
                    if (line.equals("<Cart>")) {

                        line = fileReader.nextLine();
                        ArrayList<Product> productList = new ArrayList<Product>();

                        while (!(line.equals("<History>") || line.equals(""))) {
                            String[] cartParams = line.split(",");
                            if (cartParams.length != 2) {
                                throw new FileFormatException("Cart is malformed.");
                            }
                            Product p = new Product(cartParams[1], cartParams[0], null, -1, -1, -1, -1);
                            //Creates a product based only on identifier. (Store name/product name)
                            productList.add(p);
                            line = fileReader.nextLine();
                        }

                        c.setShoppingCart(productList);

                    } else {
                        throw new FileFormatException("Customer file has no cart");
                    }

                    if (line.equals("<History>")) {
                        line = fileReader.nextLine();
                        ArrayList<Purchase> purchaseList = new ArrayList<Purchase>();

                        while (!(line.equals("-") || line.equals(""))) {
                            String[] purchaseParams = line.split(",");
                            if (purchaseParams.length != 6) {
                                throw new FileFormatException("History is malformed.");
                            }

                            int quantity;
                            double price;
                            LocalDate date;
                            LocalTime time;
                            try {
                                quantity = Integer.parseInt(purchaseParams[2]);
                                price = Double.parseDouble(purchaseParams[3]);
                                date = LocalDate.parse(purchaseParams[4]);
                                time = LocalTime.parse(purchaseParams[5]);
                            } catch (NumberFormatException e) {
                                throw new FileFormatException("History is malformed.");
                            }

                            Purchase p = new Purchase((new Product(purchaseParams[1], purchaseParams[0],
                                    null, -1, -1, -1, -1)),
                                    quantity, price, date, time);
                            //Creates a product in the purchase using only the identifier- Store name and product name
                            purchaseList.add(p);
                            line = fileReader.nextLine();
                        }

                        c.setPurchaseHistory(purchaseList);

                    } else {
                        throw new FileFormatException("Customer File has no listed history");
                    }

                    customerList.add(c);

                } else {
                    throw new FileFormatException("Customer file is malformed");
                }

            }

            return customerList;

        } catch (IOException e) {
            return new ArrayList<Customer>();
        }

    }

    //Equals, getters, and setters
    @Override
    public boolean equals(Object o) {

        if (o instanceof Customer) {
            Customer c = (Customer) o;
            if (c.getName().equals(this.name) &&
                    c.getEmailAddress().equals(super.getEmail()) &&
                    c.getPassword().equals(super.getPassword()) &&
                    ((c.getShoppingCart() == null && this.shoppingCart == null) ||
                            c.getShoppingCart().equals(this.shoppingCart))
                    && ((c.getPurchaseHistory() == null && this.purchaseHistory == null) ||
                    (c.getPurchaseHistory().equals(this.purchaseHistory)))){


                return true;
            }
        }

        return false;

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return super.getEmail();
    }

    public void setEmailAddress(String emailAddress) {
        super.setEmail(emailAddress);
    }

    public String getPassword() {
        return super.getPassword();
    }

    public void setPassword(String password) {
        super.setPassword(password);
    }

    public ArrayList<Product> getShoppingCart() {
        return shoppingCart;
    }

    public ArrayList<Purchase> getPurchaseHistory() {
        return purchaseHistory;
    }

    public void setShoppingCart (ArrayList<Product> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public void setPurchaseHistory (ArrayList<Purchase> purchaseHistory) {
        this.purchaseHistory = purchaseHistory;
    }


}