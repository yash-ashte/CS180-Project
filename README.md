# The Marketplace
### Originally Created by:
Yash Ashtekar, Isaac Fuksman, Andrew McLain, Lance Salvador, & Swara Savagaonkar
<br/>
### Updated by Yash Ashtekar during Summer 2023 and added on GitHub on 29/11/2023

<br/>

**The Marketplace**™ is the newest, coolest place for anyone to sell and buy any product that they could want!

---

## Compilation and Running Instructions

- Download the files of the repository into a directory.
- Ensure that the following java files have been compiled and exsist as a `.class` file:
    - Marketplace
    - User
    - Seller
    - Customer
    - Product
    - Store
    - Purchase
    - Sale
    - FileFormatException
- If any are missing, compile the java source files with
    > `$ javac <file>.java`
- Ensure that the following files are present in the folder (they may be blank before any data has been saved.)
    - Customers.txt
    - Seller.txt
    - Product.txt
    - Store.txt
- Once all files are present, run the Marketplace file in a terminal window using the command
    > `$ java Marketplace`

### Note: Using Intellij IDE might prompt errors like "org.junit package not found"

- Using the marketplace: Enter numbers to select menu options in the terminal.

<br/>

--- 

<br/>

## Descriptions of Classes 

<br/>

### Marketplace

Marketplace is the central hub of **The Marketplace**™. It includes the main method and acts as the driver program, creating the user experience. The Marketplace class allows users to log in, create an account, and view statistics. Customers are able to browse for and purchase products, and sellers are able to manage stores and products. In addition, the marketplace loads and saves all data about users and products.

The Marketplace uses methods and objects from all of the classes in the project in order to create the central user experience.

The Marketplace class was tested through MarketplaceTest. The main method of the class runs a JUnit test to ensure that the marketplace method runs and terminal output is correct.

<br/>

### User

Users are a representation of all accounts of the marketplace, both customers and sellers. The user class defines an email address and password for all users.

The customer and seller class are both subclasses of User. In addition, the marketplace uses objects of the User class to manage accounts.

All of the methods of the User class are tested both in the test cases for Customer, and the test method for Seller.

<br/>

### Seller

Sellers are anyone who creates an account as a seller, intending to bring products to market for others to buy. A seller has a list of stores, created by the seller, in which they choose to market products. The seller class provides functionality for creating new accounts, adding and removing stores, adding and removing products from a store, and saving/loading the data of sellers.

The seller class is a subclass of user and uses its email/password management. In addition, Sellers uses the Store class in order to represent all the stores that the seller has created. It also uses the product class in order to add them to the respective stores. The marketplace uses the Seller class in order to manage all aspects of the market that relate to sellers.

The seller class was tested through SellerTest, which provides a main method to test the methods of the class.

<br/>

### Customer

Customers are anyone using the marketplace to search for and purchase products. A customer has a shopping cart of products and a purchase history that allows them to make and save purchases. The class provides functionality for creating new accounts, adding and removing products from a cart, recording when a purchase is made, exporting the purchase history, and saving/loading all of the data for customers.

Customer is also a subclass of user, using email and password management. Customers use a list of Products to represent the shopping cart, and a list of Purchases to represent the history of the customer. The marketplace uses the class to represent and manage all of the customers, shopping carts, and histories.

The customer class was tested throught the file CustomerTestCases, which provides a JUnit test for all of the methods of the class.

<br/>

### Product

Products make up the marketplace- they are created or imported by sellers and purchased by customers. A product wraps data for the product's name, associated store, description, quantity available, price, total sales, and revenue. The class provides functionality for exporting and importing as CSV files, and saving/loading data for all products in the marketplace.

To import products, sellers need to provide a CSV file with the following format:
1 row per product with values < Name,Store Name,Description,Quantity,Price,Sales,Revenue >

Products are used by both types of users in the marketplace- Customers and Sellers. Stores also use the class to group products. The marketplace also uses the product class in order to implement the market that users buy and sell from.

The product class was tested through the file ProductTest, which provides a main method used to test the methods of the class.

<br/>

### Store 

Stores are created by Sellers and allow them to group their products for ease of use by customers. A store has a name, list of products, total revenue, and a sale history. The class provides functionality for adding and removing products, updating history, calculating revenue, and saving/loading data for all stores.

Stores contain a list of the products in the store. The class is used by sellers in order to make modifications and add products. The marketplace uses the class to manage all stores in the market and allow users to interact with the various products within.

The store class was tested through the file StoreTest, which provides a main method to test the functionality of the class.

<br/>

### Purchase

The purchase class is a supporting class to Customer, which helps manage the purchase history for customers. It wraps data of a purchase: a product, quantity bought, price paid, and date and time. It provides functionality for exporting the history as well as converting a purchase into a format useable by Customer, in order to save and load data.

The purchase class is used primarily by the customer class, with each customer having a list of purchases in their history. The marketplace uses the class to manage the history of each customer that makes a purchase.

The purchase class was tested in the tests for Customer, CustomerTestCases. All critical methods (supporting the customer) were tested in the provided JUnit test cases.

<br/>

### Sale

The sale class is a supporting class to Store, helping the manage the sale history of a store. It wraps data of the customer who bought it, the product bought, and the revenue from the sale.

The class is used by the Store class when saving and loading data of a store, in order to save its sale history. The marketplace uses the class to manage the sale history for each created store.

The sale class is tested in the tests for Store, including all of the critical methods to assist store.

<br/>

### FileFormatException

This exception is thrown when importing data from a file that is malformed. The marketplace's main method is able to catch these exceptions and inform the user.

The exception is used by the customer and product classes. 
