import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Project 4 - Seller
 *
 * A class to contain all the functionality of the Seller class
 */
public class Seller extends User {

    private ArrayList<Store> stores;
    private static String sellerFile = "Seller.txt";

    //constructor
    public Seller(String email, String password, ArrayList<Store> stores) {
        super(email, password);
        this.stores = stores;
    }

    //getters and setters
    public ArrayList<Store> getStores() {
        return stores;
    }


    public void setStores(ArrayList<Store> stores) {
        this.stores = stores;
    }

    public String getSellerFile() {
        return sellerFile;
    }

    public void addStore(Store s) {
        stores.add(s);
    }

    public boolean removeStore(Store s) {
        if (stores.indexOf(s) != -1) {
            stores.remove(s);
            return true;
        }
        return false;
    }

    //Adds new product to a store
    public void addToStore(String name, String storeName, String desc, int quantity,  double price, int sales, double revenue) {
        Product createProduct = new Product(name, storeName, desc, quantity, price, sales, revenue);
        for (int i = 0; i < stores.size(); i++) {
            if (stores.get(i).getStoreName().equals(storeName)) {
                stores.get(i).addProduct(createProduct);
            }
        }
    }

    public void addToStore(Product product) { //Adds to store that matches product's storename
        for (int i = 0; i < stores.size(); i++) {
            if (stores.get(i).getStoreName().equals(product.getStoreName())) {
                stores.get(i).addProduct(product);
            }
        }
    }

    //Deletes product to a store
    public void deleteFromStore(Product product) {
        for (int i = 0; i < stores.size(); i++) {
            if (stores.get(i).getStoreName().equals(product.getStoreName())) {
                stores.get(i).deleteProduct(product);
            }
        }
    }

    //writes seller to seller file
    public static boolean writeSellerFile(ArrayList<Seller> sellers) {
        File sellerData = new File(sellerFile);
        try {
            PrintWriter pw = new PrintWriter(sellerData);

            for (Seller s : sellers) {
                pw.println("Email:" + s.getEmail());
                pw.println("Password:" + s.getPassword());
                pw.println("Stores: ");
                for (Store store : s.getStores()) {
                    pw.println(store.getStoreName());
                }
                pw.println();
            }

            pw.close();
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }

    }

    //Converts a seller's info into a string
    public String toString() {
        String sellerStr = "Email: " + getEmail() + ", Password: " + getPassword() + ", ";
        ArrayList<Store> stores = this.getStores();
        if (stores.size() == 1) {
            sellerStr += "Store: " + stores.get(0).getStoreName();
        } else {
            sellerStr += "Stores: ";
            sellerStr += (stores.get(0).getStoreName() + ", ");
            for (int i = 1; i < stores.size(); i++) {
                sellerStr += (stores.get(i).getStoreName() + ", ");
            }
        }
        return sellerStr;
    }

    //returns an ArrayList of ALL sellers
    public static ArrayList<Seller> getSellers() {
        ArrayList<Seller> sellers = new ArrayList<>();
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(new File(sellerFile)));
            String line = bfr.readLine();
            while (line != null) {
                String[] lineSplit = line.split(":");
                String email = lineSplit[1];

                line = bfr.readLine();
                String[] lineSplitTwo = line.split(":");
                String password = lineSplitTwo[1];

                line = bfr.readLine();
                ArrayList<Store> stores = new ArrayList<>();
                line = bfr.readLine();
                while (!line.equals("")) {
                    System.out.println(line);
                    stores.add(new Store(line, null, null, 0));
                    line = bfr.readLine();
                }
                sellers.add(new Seller(email, password, stores));
                line = bfr.readLine();
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        return sellers;
    }
}