import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.PrintStream;
import java.net.*;
import java.util.ArrayList;

/**
 * CustomerMarketplaceGUI
 * <p>
 * GUI for customers to search and purchase products on marketplace
 */

public class CustomerMarketplaceGUI extends JComponent implements Runnable {
    public CustomerMarketplaceGUI(ArrayList<Product> products, PrintStream outputStream) {
        this.products = products;
        this.outputStream = outputStream;
    }
    private PrintStream outputStream;
    private ArrayList<Product> products;
    JFrame frame;

    //Top Panel Elements
    JTextField searchField;
    JButton searchButton;
    JLabel welcomeLabel;
    JButton profileButton;
    JComboBox sortDropdown;
    ActionListener sortListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox parentCb = (JComboBox) e.getSource(); //Initializes parent dropdown
            int sortOption = parentCb.getSelectedIndex();
            //TODO
        }
    };

    //Center Panel Test Data
    private ArrayList<Product> testProducts = new ArrayList<>();

    //Bottom Panel Elements
    JButton dashboardButton;
    JButton allProductsButton;
    JButton cartButton;
    JButton historyButton;

    ActionListener bottomListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == cartButton) {

            }
        }
    };

    @Override
    public void run() {
        frame = new JFrame();
        frame.setTitle("The Marketplace");

        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        //Top Panel
        JPanel topPanel = new JPanel();

        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });
        welcomeLabel = new JLabel("Welcome to the Marketplace");
        profileButton = new JButton("Edit Profile");
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });
        String[] sortOptions = {"Price(Low to High)", "Price(High to Low)", "Quantity(Low to High)",
                "Quantity(High to Low)"};
        sortDropdown = new JComboBox(sortOptions);
        sortDropdown.addActionListener(sortListener);

        topPanel.add(searchField);
        topPanel.add(searchButton);
        topPanel.add(welcomeLabel);
        topPanel.add(profileButton);
        topPanel.add(sortDropdown);

        content.add(topPanel, BorderLayout.NORTH);

        //Center panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));

        for (Product product : products) {
            JPanel productPanel = new JPanel();
            JLabel productLabel = new JLabel(product.getName() + " | " + product.getStoreName() + " | "
                    + product.getPrice());
            JButton productButton = new JButton("View Product");

            productPanel.add(productLabel);
            productPanel.add(productButton);
            //TODO add action event listener

            centerPanel.add(productPanel);
        }

        JScrollPane scrollPane = new JScrollPane(centerPanel);

        content.add(scrollPane, BorderLayout.CENTER);

        //Bottom panel
        JPanel bottomPanel = new JPanel();

        dashboardButton = new JButton("View Stores");
        dashboardButton.addActionListener(bottomListener);

        allProductsButton = new JButton("View All Products");
        allProductsButton.addActionListener(bottomListener);

        cartButton = new JButton("Manage Cart");
        cartButton.addActionListener(bottomListener);
        //TODO check for empty cart

        historyButton = new JButton("View Purchase History");
        historyButton.addActionListener(bottomListener);

        bottomPanel.add(dashboardButton);
        bottomPanel.add(allProductsButton);
        bottomPanel.add(cartButton);
        bottomPanel.add(historyButton);

        content.add(bottomPanel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    //Test PSVM
    public static void main(String[] args) {
        PrintStream testStream = null;
        ArrayList<Product> testProducts = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            testProducts.add(new Product("Product" + (i + 1), "Store" + (i + 1), "Placeholder description", 10, 9.99, 100, 100.25));
        }
        SwingUtilities.invokeLater(new CustomerMarketplaceGUI(testProducts, testStream));
    }
}