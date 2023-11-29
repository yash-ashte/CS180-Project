import java.util.*;

/**
 * The Marketplace
 *
 * A class to contain the main method which will
 * act as the marketplace itself by asking for
 * various information to the user to execute
 * different tasks.
 *
 * @author Yash Ashtekar, lab sec 10
 *
 * @version 15/06/2023
 *
 */
public class Marketplace {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);     // Scanner object for inputs

        User currentUser = null;     // User object for user using the markteplace

        /*
         * The following lines will creates ArrayLists
         * for every class that already has objects
         * created.
         */
        ArrayList<Customer> customers = new ArrayList<Customer>();
        ArrayList<Seller> sellers = new ArrayList<Seller>();
        ArrayList<Product> products = new ArrayList<Product>();
        ArrayList<Store> stores = new ArrayList<Store>();
        try {
            customers = Customer.createCustomersFromFile("Customers.txt");
        } catch (Exception e) {
        }
        try {
            sellers = Seller.getSellers();
        } catch (Exception e) {
        }
        try {
            products = Product.readProductFile("Products.txt");
        } catch (Exception e) {
        }
        try {
            stores = Store.readStore("Stores.txt", products);
        } catch (Exception e) {
        }

        System.out.println("Welcome to The Marketplace!");

        /*
         * The following code will allow the user to choose
         * whether they are signing in or creating an account.
         * The loop will allow the user to keep choosing until
         * they enter either 1 or 2.
         */
        String response = "0";      // String to take in response from user
        int responseNum = Integer.parseInt(response);       // int to store value of response as an int
        while (responseNum != 1 && responseNum != 2) {
            System.out.println("Sign in or create an account?");
            System.out.println("1. Sign in\n2. Create an account");
            response = input.nextLine();
            try {
                responseNum = Integer.parseInt(response);
            } catch (Exception e) {
            }
            if (responseNum != 1 && responseNum != 2) {
                System.out.println("Error, please choose either 1 or 2.");
            } else if (responseNum == 1 && (customers == null && sellers == null ||
                    (customers.size() == 0 && sellers.size() == 0))) {
                System.out.println("Error, no accounts have been created yet.");
                responseNum = 0;
            }
        }


        String email = "";      // String to store the email that the user enters upon request
        String password = "";       // String to store the password that the user enters upon request
        /*
         * This if statement is for the previous input request.
         * If responseNum is equal to one, the if body will
         * execute, allowing the user to sign in. Else, the
         * else body will execute, allowing the user to
         * create a new account.
         */
        if (responseNum == 1) {

            while (true) {

                System.out.print("Email: ");
                email = input.nextLine();
                System.out.print("Password: ");
                password = input.nextLine();
                /*
                 * Checks if email and password match those of
                 * a current customer or seller. If it does,
                 * currentUser is set to that user. If not,
                 * prints an error message and asks again.
                 */


                for (int i = customers.size()-1; i >= 0; i--) {
                    Customer check = customers.get(i);      // Customer object to store the customer being compared
                    if (check.getEmailAddress().equals(email) && check.getPassword().equals(password)) {
                        currentUser = check;
                        customers.remove(check);

                        for (int j = 0; j < check.getShoppingCart().size(); j++) {
                            Product cartItem = check.getShoppingCart().get(j);
                            Product productCheck = null;
                            for (int k = 0; k < products.size(); k++) {
                                productCheck = products.get(k);
                                if (cartItem.getName().equals(productCheck.getName()) &&
                                        cartItem.getStoreName().equals(productCheck.getStoreName())) {
                                    break;
                                }
                                productCheck = null;
                            }

                            if (productCheck == null) {
                                customers.get(i).removeProduct(cartItem);
                                System.out.println("A product has been removed from your shopping cart.");
                            } else if (cartItem.getQuantity() > productCheck.getQuantity()) {
                                customers.get(i).removeProduct(cartItem);
                                System.out.println("A product has been removed from your shopping cart.");
                            }
                        }
                        break;
                    }
                }
                for (int i = sellers.size()-1; i >= 0 ; i--) {
                    Seller check = sellers.get(i);        // Seller object to store the seller being compared
                    if (check.getEmail().equals(email) && check.getPassword().equals(password)) {
                        currentUser = check;
                        sellers.remove(check);
                        break;
                    }
                }

                if (currentUser != null) {
                    System.out.println("Logged in as " + currentUser.getEmail() + "");
                    break;
                }


                System.out.println("Error, username or password does not match.");
                String createNew = "0";     // String for upcoming input
                int createNewNum = 0;       // int for createNew
                while (createNewNum != 1 && createNewNum != 2) {
                    System.out.println("What would you like to do?");
                    System.out.println("1. Try again\n2. Create new account");
                    createNew = input.nextLine();
                    try {
                        createNewNum = Integer.parseInt(createNew);
                    } catch (Exception e) {
                    }

                    if (createNewNum != 1 && createNewNum != 2) {
                        System.out.println("Error, please choose either 1 or 2.");
                    }
                }

                if (createNewNum == 2) {
                    String customerSeller = "0";        // String to take in response from user
                    int customerSellerNum = Integer.parseInt(customerSeller);//int to store value of response as an int
                    /*
                     * This while loop will allow the new user to select
                     * whether or not they are a customer or seller. The
                     * while loop will allow the user to continue choosing
                     * until they enter a valid input of either 1 or 2.
                     */
                    while (customerSellerNum != 1 && customerSellerNum != 2) {
                        System.out.println("Are you a customer or seller?");
                        System.out.println("1. Customer\n2. Seller");
                        customerSeller = input.nextLine();
                        try {
                            customerSellerNum = Integer.parseInt(customerSeller);
                        } catch (Exception e) {
                        }
                        if (customerSellerNum != 1 && customerSellerNum != 2) {
                            System.out.println("Error, please choose either 1 or 2.");
                        }
                    }

                    email = "";
                    while (email.equals("")) {
                        System.out.println("Please enter your email: ");
                        email = input.nextLine();

                        /*
                         * The following try catch statements will
                         * go through the ArrLists for customer and
                         * sellers to make sure that an account with
                         * the inputted email does not already
                         * exist.
                         */
                        try {
                            for (int i = 0; i < customers.size(); i++) {
                                if (email.equals(customers.get(i).getEmailAddress())) {
                                    System.out.println("Error, email already in use.");
                                    System.out.println("Please enter a different email.");
                                    email = "";
                                }
                            }
                        } catch (Exception e) {
                        }
                        try {
                            for (int i = 0; i < sellers.size(); i++) {
                                if (email.equals(sellers.get(i).getEmail())) {
                                    System.out.println("Error, email already in use.");
                                    System.out.println("Please enter a different email.");
                                    email = "";
                                }
                            }
                        } catch (Exception e) {
                        }
                    }

                    /*
                     * The following while loop will go through the password
                     * creation for the account to make sure that password
                     * is the desired password.
                     */
                    while (true) {
                        System.out.println("Please enter your new password: ");
                        password = input.nextLine();
                        System.out.println("Please verify your password: ");
                        // String to store the password again to check if password is actual desired password
                        String passwordCheck = input.nextLine();

                        /*
                         * The following if statement checks to make sure
                         * password match. If not, the while loop will
                         * continue to execute.
                         */
                        if (password.equals(passwordCheck)) {
                            break;
                        } else {
                            System.out.println("Error, password do not match. Please enter passsword again");
                        }
                    }

                    System.out.println("What is your name?");
                    String name = input.nextLine();     // String to store user's name for the user object;

                    /*
                     * The following if else statement will complete the
                     * construction of the new user. If the
                     * customerSellerNum input from before was 1, a
                     * customer is created. Else, a seller is created.
                     * After creating the new user, the currentUser
                     * is set to than new user.
                     */
                    if (customerSellerNum == 1) {
                        Customer newCustomer = new Customer(name, email, password);// Customer object for the new User
                        currentUser = newCustomer;
                    } else {
                        Seller newSeller = new Seller(email, password, new ArrayList<Store>());// Seller object 
                        currentUser = newSeller;
                    }
                    break;
                }
            }
        } else {
            String customerSeller = "0";        // String to take in response from user
            int customerSellerNum = Integer.parseInt(customerSeller);       // int to store value of response as an int
            /*
             * This while loop will allow the new user to select
             * whether or not they are a customer or seller. The
             * while loop will allow the user to continue choosing
             * until they enter a valid input of either 1 or 2.
             */
            while (customerSellerNum != 1 && customerSellerNum != 2) {
                System.out.println("Are you a customer or seller?");
                System.out.println("1. Customer\n2. Seller");
                customerSeller = input.nextLine();
                try {
                    customerSellerNum = Integer.parseInt(customerSeller);
                } catch (Exception e) {
                }
                if (customerSellerNum != 1 && customerSellerNum != 2) {
                    System.out.println("Error, please choose either 1 or 2.");
                }
            }

            while (email.equals("")) {
                System.out.println("Please enter your email: ");
                email = input.nextLine();

                /*
                 * The following try catch statements will
                 * go through the ArrLists for customer and
                 * sellers to make sure that an account with
                 * the inputted email does not already
                 * exist.
                 */
                try {
                    for (int i = 0; i < customers.size(); i++) {
                        if (email.equals(customers.get(i).getEmailAddress())) {
                            System.out.println("Error, email already in use.");
                            System.out.println("Please enter a different email.");
                            email = "";
                        }
                    }
                } catch (Exception e) {
                }
                try {
                    for (int i = 0; i < sellers.size(); i++) {
                        if (email.equals(sellers.get(i).getEmail())) {
                            System.out.println("Error, email already in use.");
                            System.out.println("Please enter a different email.");
                            email = "";
                        }
                    }
                } catch (Exception e) {
                }
            }


            /*
             * The following while loop will go through the password
             * creation for the account to make sure that password
             * is the desired password.
             */
            while (true) {
                System.out.println("Please enter your new password: ");
                password = input.nextLine();
                System.out.println("Please verify your password: ");
                // String to store the password again to check if password is actual desired password
                String passwordCheck = input.nextLine();

                /*
                 * The following if statement checks to make sure
                 * password match. If not, the while loop will
                 * continue to execute.
                 */
                if (password.equals(passwordCheck)) {
                    break;
                } else {
                    System.out.println("Error, password do not match. Please enter passsword again.");
                }
            }

            System.out.println("What is your name?");
            String name = input.nextLine();     // String to store user's name for the user object;

            /*
             * The following if else statement will complete the
             * construction of the new user. If the
             * customerSellerNum input from before was 1, a
             * customer is created. Else, a seller is created.
             * After creating the new user, the currentUser
             * is set to than new user.
             */
            if (customerSellerNum == 1) {
                Customer newCustomer = new Customer(name, email, password);      // Customer object for the new User
                currentUser = newCustomer;
            } else {
                Seller newSeller = new Seller(email, password, new ArrayList<Store>());//Seller object for the new User
                currentUser = newSeller;
            }
        }


        String choice = "0";        // String to store selection of user in the following if else statement
        int choiceNum = Integer.parseInt(choice);       // int to store choice
        if (currentUser instanceof Customer) {
            Customer current = (Customer) currentUser;
            while (true) {
                choiceNum = 0;

                if (products.isEmpty()) {
                    System.out.println("No products up for sale: Marketplace is currently closed.");
                    customers.add(current);
                    break;
                }
                while (!(choiceNum >= 1 && choiceNum <= 5)) {
                    System.out.println("Please choose an option from below.");
                    String print = "1. View all products\n2. Search for product\n3. Sort marketplace\n";
                    print += "4. Manage cart\n5. View purchase history";
                    System.out.println(print);
                    choice = input.nextLine();
                    try {
                        choiceNum = Integer.parseInt(choice);
                    } catch (Exception e) {
                    }
                    if (!(choiceNum >= 1 && choiceNum <= 5)) {
                        System.out.println("Error, please enter a choice between 1 and 5.");
                    }
                }

                // These if statements execute based on the given input from before.
                if (choiceNum == 1) {
                    for (int i = 0; i < products.size(); i++) {
                        String print = "Store: " + products.get(i).getStoreName();      // String for the printed value
                        print += ", Name: " + products.get(i).getName();
                        print += ", Price: " + products.get(i).getPrice();
                        System.out.println((i + 1) + " " + print);
                    }

                    String productChoice = "0";     // String for the product to be chosen
                    int productChoiceNum = Integer.parseInt(productChoice);     // int for productChoice
                    // Allows user to continue entering inputs until a valid input is entered
                    while (!(productChoiceNum >= 1 && productChoiceNum <= products.size())) {
                        System.out.println("Please select a product to view more information.");
                        productChoice = input.nextLine();
                        try {
                            productChoiceNum = Integer.parseInt(productChoice);
                        } catch (Exception e) {
                        }

                        if (!(productChoiceNum >= 1 && productChoiceNum <= products.size())) {
                            System.out.println("Error, please select a valid product");
                        }
                    }

                    String print = products.get(productChoiceNum - 1).getDesc() + ", ";     // String to be printed
                    print += "Available: " + products.get(productChoiceNum - 1).getQuantity();
                    System.out.println(print);

                    // Allows user to continue entering inputs until a valid input is entered
                    String yesNo = "0";     // String to store decision for yes or no question
                    int yesNoNum = Integer.parseInt(yesNo);     // int to store yesNo
                    while (yesNoNum != 1 && yesNoNum != 2) {
                        System.out.println("Would you like to add this item to your cart?");
                        System.out.println("1. Yes\n2. No");
                        yesNo = input.nextLine();
                        try {
                            yesNoNum = Integer.parseInt(yesNo);
                        } catch (Exception e) {
                        }

                        if (yesNoNum != 1 && yesNoNum != 2) {
                            System.out.println("Error, please choose either 1 or 2.");
                        }
                    }

                    if (yesNoNum == 1) {
                        String quantityToAdd = "0";     // String for amount to add
                        int quantityToAddNum = 0;       // int for quantityToAdd
                        while (quantityToAddNum == 0) {
                            System.out.println("How many would you like to add?");
                            quantityToAdd = input.nextLine();
                            try {
                                quantityToAddNum = Integer.parseInt(quantityToAdd);
                            } catch (Exception e) {
                            }

                            if (quantityToAddNum == 0) {
                                System.out.println("Error, please input a valid amount.");
                            }
                            if (quantityToAddNum > products.get(productChoiceNum - 1).getQuantity()) {
                                System.out.println("Error, please input a valid amount.");
                                quantityToAddNum = 0;
                            }
                        }
                        Product productAdd = products.get(productChoiceNum - 1);
                        productAdd.setQuantity(quantityToAddNum);
                        current.addProduct(productAdd);
                    }
                } else if (choiceNum == 2) {
                    System.out.println("What would you like to search for?");
                    String search = input.nextLine();        // String to store search

                    ArrayList<Product> matches = new ArrayList<Product>();       // ArrList for matches in search
                    // Adds each product that has matching name or store to search to a new ArrList
                    for (int i = 0; i < products.size(); i++) {
                        Product check = products.get(i);        // Product object to store product to check
                        if (check.getName().equals(search) || check.getStoreName().equals(search) ||
                                check.getDesc().indexOf(search) > -1) {
                            matches.add(check);
                        }
                    }

                    // Prints out all matches and allows user to pick from them
                    if (matches.size() > 0) {
                        for (int i = 0; i < matches.size(); i++) {
                            String print = "Store: " + matches.get(i).getStoreName();  // String for the printed value
                            print += ", Name: " + matches.get(i).getName();
                            print += ", Price: " + matches.get(i).getPrice();
                            System.out.println((i + 1) + " " + print);
                        }

                        String productChoice = "0";     // String for the product to be chosen
                        int productChoiceNum = Integer.parseInt(productChoice);     // int for productChoice
                        // Allows user to continue entering inputs until a valid input is entered
                        while (!(productChoiceNum >= 1 && productChoiceNum <= matches.size())) {
                            System.out.println("Please select a product to view more information.");
                            productChoice = input.nextLine();
                            try {
                                productChoiceNum = Integer.parseInt(productChoice);
                            } catch (Exception e) {
                            }

                            if (!(productChoiceNum >= 1 && productChoiceNum <= matches.size())) {
                                System.out.println("Error, please select a valid product");
                            }
                        }

                        String print = matches.get(productChoiceNum - 1).getDesc() + ", ";     // String to be printed
                        print += "Available: " + matches.get(productChoiceNum - 1).getQuantity();
                        System.out.println(print);

                        // Allows user to continue entering inputs until a valid input is entered
                        String yesNo = "0";     // String to store decision for yes or no question
                        int yesNoNum = Integer.parseInt(yesNo);     // int to store yesNo
                        while (yesNoNum != 1 && yesNoNum != 2) {
                            System.out.println("Would you like to add this item to your cart?");
                            System.out.println("1. Yes\n2. No");
                            yesNo = input.nextLine();
                            try {
                                yesNoNum = Integer.parseInt(yesNo);
                            } catch (Exception e) {
                            }

                            if (yesNoNum != 1 && yesNoNum != 2) {
                                System.out.println("Error, please choose either 1 or 2.");
                            }
                        }

                        if (yesNoNum == 1) {
                            String quantityToAdd = "0";     // String for amount to add
                            int quantityToAddNum = 0;       // int for quantityToAdd
                            while (quantityToAddNum == 0) {
                                System.out.println("How many would you like to add?");
                                quantityToAdd = input.nextLine();
                                try {
                                    quantityToAddNum = Integer.parseInt(quantityToAdd);
                                } catch (Exception e) {
                                }

                                if (quantityToAddNum == 0) {
                                    System.out.println("Error, please input a valid amount.");
                                }
                                if (quantityToAddNum > matches.get(productChoiceNum - 1).getQuantity()) {
                                    System.out.println("Error, please input a valid amount.");
                                    quantityToAddNum = 0;
                                }
                            }
                            Product productAdd = matches.get(productChoiceNum - 1);
                            productAdd.setQuantity(quantityToAddNum);
                            current.addProduct(productAdd);
                        }
                    } else {
                        System.out.println("No matches to your search.");
                    }
                } else if (choiceNum == 3) {
                    ArrayList<Product> sortedProducts = new ArrayList<Product>();
                    sortedProducts.add(products.get(0));

                    String sortChoice = "0";        // String for upcoming decision
                    int sortChoiceNum = Integer.parseInt(sortChoice);       // int for sortChoice
                    while (!(sortChoiceNum >= 1 && sortChoiceNum <= 4)) {
                        System.out.println("How would you like to sort the products?");
                        String print = "1. Price Low-High\n2. Price High-low\n";
                        print += "3. Quantity Low-High\n4. Quantity High-Low";
                        System.out.println(print);
                        sortChoice = input.nextLine();
                        try {
                            sortChoiceNum = Integer.parseInt(sortChoice);
                        } catch (Exception e) {
                        }

                        if (!(sortChoiceNum >= 1 && sortChoiceNum <= 4)) {
                            System.out.println("Error, please choose a valid choice.");
                        }
                    }

                    if (sortChoiceNum == 1) {
                        for (int i = 0; i < products.size(); i++) {
                            for (int j = 0; j < sortedProducts.size(); j++) {
                                if (products.get(i).getPrice() < sortedProducts.get(j).getPrice()) {
                                    sortedProducts.add(j, products.get(i));
                                    j = sortedProducts.size();
                                }
                            }
                            if (sortedProducts.indexOf(products.get(i)) < 0) {
                                sortedProducts.add(products.get(i));
                            }
                        }
                    } else if (sortChoiceNum == 2) {
                        for (int i = 0; i < products.size(); i++) {
                            for (int j = 0; j < sortedProducts.size(); j++) {
                                if (products.get(i).getPrice() > sortedProducts.get(j).getPrice()) {
                                    sortedProducts.add(j, products.get(i));
                                    j = sortedProducts.size();
                                }
                            }
                            if (sortedProducts.indexOf(products.get(i)) < 0) {
                                sortedProducts.add(products.get(i));
                            }
                        }
                    } else if (sortChoiceNum == 3) {
                        for (int i = 0; i < products.size(); i++) {
                            for (int j = 0; j < sortedProducts.size(); j++) {
                                if (products.get(i).getQuantity() < sortedProducts.get(j).getQuantity()) {
                                    sortedProducts.add(j, products.get(i));
                                    j = sortedProducts.size();
                                }
                            }
                            if (sortedProducts.indexOf(products.get(i)) < 0) {
                                sortedProducts.add(products.get(i));
                            }
                        }
                    } else {
                        for (int i = 0; i < products.size(); i++) {
                            for (int j = 0; j < sortedProducts.size(); j++) {
                                if (products.get(i).getQuantity() > sortedProducts.get(j).getQuantity()) {
                                    sortedProducts.add(j, products.get(i));
                                    j = sortedProducts.size();
                                }
                            }
                            if (sortedProducts.indexOf(products.get(i)) < 0) {
                                sortedProducts.add(products.get(i));
                            }
                        }
                    }

                    for (int i = 0; i < sortedProducts.size(); i++) {
                        // String for the printed value
                        String print = "Store: " + sortedProducts.get(i).getStoreName();
                        print += ", Name: " + sortedProducts.get(i).getName();
                        print += ", Price: " + sortedProducts.get(i).getPrice();
                        System.out.println((i + 1) + " " + print);
                    }

                    String productChoice = "0";     // String for the product to be chosen
                    int productChoiceNum = Integer.parseInt(productChoice);     // int for productChoice
                    // Allows user to continue entering inputs until a valid input is entered
                    while (!(productChoiceNum >= 1 && productChoiceNum <= sortedProducts.size())) {
                        System.out.println("Please select a product to view more information.");
                        productChoice = input.nextLine();
                        try {
                            productChoiceNum = Integer.parseInt(productChoice);
                        } catch (Exception e) {
                        }

                        if (!(productChoiceNum >= 1 && productChoiceNum <= sortedProducts.size())) {
                            System.out.println("Error, please select a valid product");
                        }
                    }

                    // String to be printed
                    String print = sortedProducts.get(productChoiceNum - 1).getDesc() + ", ";
                    print += "Available: " + sortedProducts.get(productChoiceNum - 1).getQuantity();
                    System.out.println(print);

                    // Allows user to continue entering inputs until a valid input is entered
                    String yesNo = "0";     // String to store decision for yes or no question
                    int yesNoNum = Integer.parseInt(yesNo);     // int to store yesNo
                    while (yesNoNum != 1 && yesNoNum != 2) {
                        System.out.println("Would you like to add this item to your cart?");
                        System.out.println("1. Yes\n2. No");
                        yesNo = input.nextLine();
                        try {
                            yesNoNum = Integer.parseInt(yesNo);
                        } catch (Exception e) {
                        }

                        if (yesNoNum != 1 && yesNoNum != 2) {
                            System.out.println("Error, please choose either 1 or 2.");
                        }
                    }

                    if (yesNoNum == 1) {
                        String quantityToAdd = "0";     // String for amount to add
                        int quantityToAddNum = 0;       // int for quantityToAdd
                        while (quantityToAddNum == 0) {
                            System.out.println("How many would you like to add?");
                            quantityToAdd = input.nextLine();
                            try {
                                quantityToAddNum = Integer.parseInt(quantityToAdd);
                            } catch (Exception e) {
                            }

                            if (quantityToAddNum == 0) {
                                System.out.println("Error, please input a valid amount.");
                            }
                            if (quantityToAddNum > sortedProducts.get(productChoiceNum - 1).getQuantity()) {
                                System.out.println("Error, please input a valid amount.");
                                quantityToAddNum = 0;
                            }
                        }
                        Product productAdd = sortedProducts.get(productChoiceNum - 1);
                        productAdd.setQuantity(quantityToAddNum);
                        current.addProduct(productAdd);
                    }
                } else if (choiceNum == 4) {
                    String purchaseRemove = "0";        // String for upcoming decision
                    int purchaseRemoveNum = Integer.parseInt(purchaseRemove);       // int for purchase remove
                    while (purchaseRemoveNum != 1 && purchaseRemoveNum != 2) {
                        System.out.println("Would you like to purchase items from your cart or remove items?");
                        System.out.println("1. Purchase\n2. Remove");
                        purchaseRemove = input.nextLine();
                        try {
                            purchaseRemoveNum = Integer.parseInt(purchaseRemove);
                        } catch (Exception e) {
                        }

                        if (purchaseRemoveNum != 1 && purchaseRemoveNum != 2) {
                            System.out.println("Error, please choose either 1 or 2.");
                        }
                    }

                    if (purchaseRemoveNum == 1) {
                        if (!(current.getShoppingCart().isEmpty())) {
                            for (int i = 0; i < current.getShoppingCart().size(); i++) {
                                Product toBuy = current.getShoppingCart().get(i);
                                Product check = null;
                                for (int j = 0; j < products.size(); j++) {
                                    check = products.get(j);
                                    if (check.getName().equals(toBuy.getName()) &&
                                            check.getStoreName().equals(toBuy.getStoreName())) {
                                        products.get(j).recordSale(toBuy.getQuantity());
                                        String storeName = check.getStoreName();
                                        for (int k = 0; k < stores.size(); k++) {
                                            if (stores.get(k).getStoreName().equals(storeName)) {
                                                stores.get(k).addSale(check, current.getName(), toBuy.getQuantity());
                                            }
                                        }
                                        break;
                                    }
                                }
                                current.recordPurchaseHistory(toBuy, toBuy.getQuantity(), check.getPrice());
                            }
                        } else {
                            System.out.println("No items in shopping cart!");
                        }
                    } else {
                        if (!(current.getShoppingCart().isEmpty())) {
                            String remove = "0";        // String to store requested remove product
                            int removeNum = Integer.parseInt(remove);       // int to store remove
                            while (!(removeNum >= 1 && removeNum <= current.getShoppingCart().size())) {
                                System.out.println("Which item would you like to remove?");
                                for (int i = 0; i < current.getShoppingCart().size(); i++) {
                                    System.out.println((i + 1) + " " + current.getShoppingCart().get(i).toString());
                                }
                                remove = input.nextLine();
                                try {
                                    removeNum = Integer.parseInt(remove);
                                } catch (Exception e) {
                                }

                                if (!(removeNum >= 1 && removeNum <= current.getShoppingCart().size())) {
                                    System.out.println("Error, please enter a valid choice.");
                                }
                            }

                            Product removeP = products.get(removeNum - 1);      // Product to be removed

                            current.removeProduct(removeP);
                        } else {
                            System.out.println("Error: No items in Cart!");
                        }
                    }
                } else {
                    System.out.println("Enter name of file.");
                    String fileName = input.nextLine();
                    current.exportPurchaseHistory(fileName);
                }


                // Allows the user to choose whether or not they would like to continue.
                // If yes, the loop executes, if no, the loop is broken. 
                String yesNo = "0";     // String to store decision for yes or no question
                int yesNoNum = Integer.parseInt(yesNo);     // int to store yesNo
                while (yesNoNum != 1 && yesNoNum != 2) {
                    System.out.println("Would you like to continue using the marketplace?");
                    System.out.println("1. Yes\n2. No");
                    yesNo = input.nextLine();
                    try {
                        yesNoNum = Integer.parseInt(yesNo);
                    } catch (Exception e) {
                    }

                    if (yesNoNum != 1 && yesNoNum != 2) {
                        System.out.println("Error, please choose either 1 or 2.");
                    }
                }

                if (yesNoNum == 2) {
                    customers.add(current);
                    break;
                }
            }
        } else {
            Seller current = (Seller) currentUser;
            while (true) {
                choiceNum = 0;
                while (!(choiceNum >= 1 && choiceNum <= 3)) {
                    System.out.println("Please choose an option from below.");
                    System.out.println("1. Create a new store\n2. Manage products\n3. View sales");
                    choice = input.nextLine();
                    try {
                        choiceNum = Integer.parseInt(choice);
                    } catch (Exception e) {
                    }
                    if (!(choiceNum >= 1 && choiceNum <= 3)) {
                        System.out.println("Error, please enter a choice between 1 and 3.");
                    }
                }

                if (choiceNum == 1) {
                    String storeName = "";      // String for name of store
                    while (storeName.equals("")) {
                        System.out.println("Please enter the name of your store.");
                        storeName = input.nextLine();
                        for (int i = 0; i < stores.size(); i++) {
                            if (storeName.equals(stores.get(i).getStoreName())) {
                                System.out.println("Error, this store already exists.");
                                storeName = "";
                            }
                        }
                    }
                    Store storeToAdd = new Store(storeName, null, null, 0.0);        // Store object to be added
                    System.out.println("Created new store: " + storeName);
                    current.addStore(storeToAdd);
                    stores.add(storeToAdd);
                } else if (choiceNum == 2 && current.getStores().size() < 1) {
                    System.out.println("You have no stores to manage products for!");
                } else if (choiceNum == 2) {
                    String manage = "0";        // String for which manage option
                    int manageNum = 0;      // int for manage
                    while (!(manageNum >= 1 && manageNum <=4)) {
                        System.out.println("What would you like to do?");
                        System.out.println("1. Add products (Requires CSV)\n" +
                                "2. Edit products\n3. Remove products\n4. Export");
                        manage = input.nextLine();
                        try {
                            manageNum = Integer.parseInt(manage);
                        } catch (Exception e) {
                        }

                        if (!(manageNum >= 1 && manageNum <= 4)) {
                            System.out.println("Error, please enter a choice between 1 and 3.");
                        }
                    }

                    String store = "0";     // String for which store to manage
                    int storeNum = 0;       // int for storeToManage
                    while (!(storeNum >= 1 && storeNum <= current.getStores().size())) {
                        System.out.println("Which store would you like to manage?");
                        for (int i = 0; i < current.getStores().size(); i++) {
                            System.out.println((i + 1) + " " + current.getStores().get(i).getStoreName());
                        }
                        store = input.nextLine();
                        try {
                            storeNum = Integer.parseInt(store);
                        } catch (Exception e) {
                        }

                        if (!(storeNum >= 1 && storeNum <= current.getStores().size())) {
                            System.out.println("Error, please enter a valid choice.");
                        }
                    }

                    if (manageNum == 1) {
                        System.out.print("Please enter the filename for your import: ");
                        String fileName = input.nextLine();
                        ArrayList<Product> productsToAdd = null;
                        try {
                            productsToAdd = Product.readProductFile(fileName);
                            System.out.println("Products Imported.");
                        } catch (Exception e) {
                            System.out.println("Error, file not found.");
                        }

                        for (int i = 0; i < productsToAdd.size(); i++) {
                            current.getStores().get((storeNum - 1)).addProduct(productsToAdd.get(i));
                            products.add(productsToAdd.get(i));
                        }
                    } else if (manageNum == 2) {
                        if (current.getStores().get(storeNum - 1).getProducts() != null) {
                            String editOption = "0";        // String for what to edit
                            int editOptionNum = 0;      // int for editOption
                            while (editOptionNum != 1 && editOptionNum != 2) {
                                System.out.println("What would you like to do?");
                                System.out.println("1. Change quantity\n2. Change price");
                                editOption = input.nextLine();
                                try {
                                    editOptionNum = Integer.parseInt(editOption);
                                } catch (Exception e) {
                                }

                                if (editOptionNum != 1 && editOptionNum != 2) {
                                    System.out.println("Error, please choose either 1 or 2.");
                                }
                            }

                            String product = "0";     // String for product to manage
                            int productNum = 0;       // int for productToEdit
                            while (!(productNum >= 1 && productNum <=
                                    current.getStores().get(storeNum - 1).getProducts().size())) {
                                System.out.println("Which product would you like to edit?");
                                // ArrList for products to be edited
                                ArrayList<Product> edit = current.getStores().get(storeNum - 1).getProducts();
                                for (int i = 0; i < edit.size(); i++) {
                                    System.out.println((i + 1) + " " + edit.get(i).getName());
                                }
                                product = input.nextLine();
                                try {
                                    productNum = Integer.parseInt(product);
                                } catch (Exception e) {
                                }

                                if (!(productNum >= 1 && productNum <=
                                        current.getStores().get(storeNum - 1).getProducts().size())) {
                                    System.out.println("Error, please choose a valid option.");
                                }
                            }

                            if (editOptionNum == 1) {
                                String quantity = "0";      // String for new quantity
                                int quantityNum = 0;        // int for quantity
                                do {
                                    System.out.println("What is the new quantity?");
                                    quantity = input.nextLine();
                                    try {
                                        quantityNum = Integer.parseInt(quantity);
                                    } catch (Exception e) {
                                        quantityNum = -1;
                                    }

                                    if (quantityNum < 0) {
                                        System.out.println("Error, please enter a number greater than -1.");
                                    }
                                } while (!(quantityNum >= 0));

                                storeNum --;
                                productNum--;
                                current.getStores().get(storeNum).getProducts().get(productNum).setQuantity(quantityNum);
                            } else {
                                String price = "0";     // String for new price
                                double priceNum = 0.0;      // double price
                                while (priceNum <= 0.0) {
                                    System.out.println("What is the new price?");
                                    price = input.nextLine();
                                    try {
                                        priceNum = Double.parseDouble(price);
                                    } catch (Exception e) {
                                    }

                                    if (priceNum <= 0.0) {
                                        System.out.println("Error, please enter a number greater than 0.");
                                    }
                                }

                                storeNum--;
                                productNum--;
                                current.getStores().get(storeNum).getProducts().get(productNum).setPrice(priceNum);
                            }
                        } else {
                            System.out.println("Error, no products to edit.");
                        }
                    } else if (manageNum == 3) {
                        if (current.getStores().get(storeNum - 1).getProducts() != null) {
                            String product = "0";     // String for product to manage
                            int productNum = 0;       // int for productToEdit
                            Product delete = null;
                            while (!(productNum >= 1 && productNum <=
                                    current.getStores().get(storeNum - 1).getProducts().size())) {
                                System.out.println("Which product would you like to delete?");
                                // ArrList for products to be deleted
                                ArrayList<Product> edit = current.getStores().get(storeNum - 1).getProducts();
                                for (int i = 0; i < edit.size(); i++) {
                                    System.out.println((i + 1) + " " + edit.get(i).getName());
                                }
                                product = input.nextLine();
                                try {
                                    productNum = Integer.parseInt(product);
                                    delete = edit.get(productNum);
                                } catch (Exception e) {
                                }

                                if (!(productNum >= 1 && productNum <=
                                        current.getStores().get(storeNum - 1).getProducts().size())) {
                                    System.out.println("Error, please choose a valid option.");
                                }
                            }

                            storeNum--;
                            productNum--;
                            current.getStores().get(storeNum).deleteProduct(delete);
                        } else {
                            System.out.println("Error, no products to delete.");
                        }
                    } else {
                        System.out.println("Enter name of file.");
                        String fileName = input.nextLine();
                        ArrayList<Product> export = current.getStores().get(storeNum - 1).getProducts();
                        try {
                            Product.updateProductFile(export, fileName);
                        } catch (Exception e) {
                        }
                    }
                } else {
                    if (current.getStores().size() > 0) {
                        String sortChoice = "0";        // String for sort option
                        int sortChoiceNum = 0;      // int sortChoice
                        while (sortChoiceNum != 1 && sortChoiceNum != 2) {
                            System.out.println("How would you like to sort the sales?");
                            System.out.println("1. Revenue Low-High\n2. Revenue High-Low");
                            sortChoice = input.nextLine();
                            try {
                                sortChoiceNum = Integer.parseInt(sortChoice);
                            } catch (Exception e) {
                            }

                            if (sortChoiceNum != 1 && sortChoiceNum != 2) {
                                System.out.println("Error, please choose either 1 or 2.");
                            }
                        }

                        String store = "0";     // String for which store to manage
                        int storeNum = 0;       // int for storeToManage
                        while (!(storeNum >= 1 && storeNum <= current.getStores().size())) {
                            System.out.println("Which store would you like to view sales for?");
                            for (int i = 0; i < current.getStores().size(); i++) {
                                System.out.println((i + 1) + " " + current.getStores().get(i).getStoreName());
                            }
                            store = input.nextLine();
                            try {
                                storeNum = Integer.parseInt(store);
                            } catch (Exception e) {
                            }

                            if (!(storeNum >= 1 && storeNum <= current.getStores().size())) {
                                System.out.println("Error, please enter a valid choice.");
                            }
                        }


                        Store currentStore = current.getStores().get(storeNum - 1);
                        ArrayList<Sale> saleHistory = currentStore.getSaleHistory();
                        if (saleHistory == null) {
                            System.out.println("Error, store has not completed a sale yet.");
                        } else {
                            ArrayList<Sale> sortedHistory = new ArrayList<Sale>();
                            sortedHistory.add(saleHistory.get(0));
                            if (sortChoiceNum == 1) {
                                for (int i = 0; i < saleHistory.size(); i++) {
                                    for (int j = 0; j < sortedHistory.size(); j++) {
                                        if (saleHistory.get(i).getRevenue() < sortedHistory.get(j).getRevenue()) {
                                            sortedHistory.add(j, saleHistory.get(i));
                                            j = sortedHistory.size();
                                        }
                                    }
                                    if (sortedHistory.indexOf(saleHistory.get(i)) < 0) {
                                        sortedHistory.add(saleHistory.get(i));
                                    }
                                }
                            } else {
                                for (int i = 0; i < saleHistory.size(); i++) {
                                    for (int j = 0; j < sortedHistory.size(); j++) {
                                        if (saleHistory.get(i).getRevenue() > sortedHistory.get(j).getRevenue()) {
                                            sortedHistory.add(j, saleHistory.get(i));
                                            j = sortedHistory.size();
                                        }
                                    }
                                    if (sortedHistory.indexOf(saleHistory.get(i)) < 0) {
                                        sortedHistory.add(saleHistory.get(i));
                                    }
                                }
                            }

                            for (int i = 0; i < sortedHistory.size(); i++) {
                                System.out.println((i + 1) + " " + sortedHistory.get(i).toString());
                            }
                        }

                    } else {
                        System.out.println("You have no stores to view information for!");
                    }
                }


                // Allows the user to choose whether or not they would like to continue.
                // If yes, the loop executes, if no, the loop is broken. 
                String yesNo = "0";     // String to store decision for yes or no question
                int yesNoNum = Integer.parseInt(yesNo);     // int to store yesNo
                while (yesNoNum != 1 && yesNoNum != 2) {
                    System.out.println("Would you like to continue using the marketplace?");
                    System.out.println("1. Yes\n2. No");
                    yesNo = input.nextLine();
                    try {
                        yesNoNum = Integer.parseInt(yesNo);
                    } catch (Exception e) {
                    }

                    if (yesNoNum != 1 && yesNoNum != 2) {
                        System.out.println("Error, please choose either 1 or 2.");
                    }
                }

                if (yesNoNum == 2) {
                    sellers.add(current);
                    break;
                }
            }
        }

        System.out.println("Would you like to update your account?");
        // Allows the user to choose whether or not they would like to continue.
        // If yes, the loop executes, if no, the loop is broken. 
        String yesNo = "0";     // String to store decision for yes or no question
        int yesNoNum = Integer.parseInt(yesNo);     // int to store yesNo
        while (yesNoNum != 1 && yesNoNum != 2) {
            System.out.println("1. Yes\n2. No");
            yesNo = input.nextLine();
            try {
                yesNoNum = Integer.parseInt(yesNo);
            } catch (Exception e) {
            }

            if (yesNoNum != 1 && yesNoNum != 2) {
                System.out.println("Error, please choose either 1 or 2.");
            }
        }
        while (yesNoNum == 1) {
            String edit = "0";      // String to store requested edit
            int editNum = Integer.parseInt(edit);       // int to store edit
            while (editNum != 1 && editNum != 2 && editNum != 3) {
                System.out.println("What would you like to change?");
                System.out.println("1. Email\n2. Password\n3. Delete account");
                edit = input.nextLine();
                try {
                    editNum = Integer.parseInt(edit);
                } catch (Exception e) {
                }

                if (editNum != 1 && editNum != 2 && editNum!= 3) {
                    System.out.println("Error, please choose a number between 1 and 3.");
                }
            }


            if (editNum == 1) {

                email = "";
                while (email.equals("")) {
                    System.out.println("Please enter your new email: ");
                    email = input.nextLine();

                    /*
                     * The following try catch statements will
                     * go through the ArrLists for customer and
                     * sellers to make sure that an account with
                     * the inputted email does not already
                     * exist.
                     */
                    try {
                        for (int i = 0; i < customers.size(); i++) {
                            if (email.equals(customers.get(i).getEmailAddress())) {
                                System.out.println("Error, email already in use.");
                                System.out.println("Please enter a different email.");
                                email = "";
                            }
                        }
                    } catch (Exception e) {
                    }
                    try {
                        for (int i = 0; i < sellers.size(); i++) {
                            if (email.equals(sellers.get(i).getEmail())) {
                                System.out.println("Error, email already in use.");
                                System.out.println("Please enter a different email.");
                                email = "";
                            }
                        }
                    } catch (Exception e) {
                    }
                }

                currentUser.setEmail(email);

            } else if (editNum == 2) {
                System.out.print("Please enter your new password: ");
                currentUser.setPassword(input.nextLine());
            } else {
                if (currentUser instanceof Customer) {
                    customers.remove(currentUser);
                } else {
                    sellers.remove(currentUser);
                }
            }

            String continueEdit = "0";      // String for upcoming prompt
            int continueEditNum = 0;        // int for continueEdit
            while (continueEditNum != 1 && continueEditNum != 2) {
                System.out.println("Would you like to continue editing your account?");
                System.out.println("1. Yes\n2. No");
                continueEdit = input.nextLine();
                try {
                    continueEditNum = Integer.parseInt(continueEdit);
                } catch (Exception e) {
                }

                if (continueEditNum != 1 && continueEditNum != 2) {
                    System.out.println("Error, please choose either 1 or 2.");
                }
            }

            if (continueEditNum == 2) {
                break;
            }
        }

        System.out.println("Thank you for using our marketplace!");

        Customer.writeCustomerFile(customers, "Customers.txt");
        try {
            Product.updateProductFile(products, "Products.txt");
        } catch (Exception e) {
        }
        try {
            Seller.writeSellerFile(sellers);
        } catch (Exception e) {
        }
        try {
            Store.writeStoreFile(stores);
        } catch (Exception e) {
        }
    }
}