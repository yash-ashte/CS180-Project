import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * SellerStoreGUI
 * <p>
 * GUI for sellers to manage individual store and its products (Slide 9)
 */

public class SellerStoreGUI extends JComponent implements Runnable {
    public SellerStoreGUI(Store store, PrintStream outputStream) {
        this.store = store;
        this.outputStream = outputStream;
    }
    private PrintStream outputStream;
    Store store;
    JFrame frame;

    //Top Panel
    JLabel titleLabel;

    //Left Panel
    JButton addProductButton;

    JTextField importField;
    JButton importButton;

    JTextField exportField;
    JButton exportButton;

    //Right Panel
    JButton customerStatButton;

    //Center Panel
    JLabel instructionLabel;

    //Bottom Panel
    JButton saleHistoryButton;
    JButton storeDelButton;

    @Override
    public void run() {
        frame = new JFrame();
        frame.setTitle("The Marketplace");

        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        //Top Panel
        JPanel topPanel = new JPanel();

        titleLabel = new JLabel("Manage " + store.getStoreName());

        topPanel.add(titleLabel);
        content.add(topPanel, BorderLayout.NORTH);

        //Left Panel
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        addProductButton = new JButton("Add Product");

        importField = new JTextField(10);
        importButton = new JButton("Import Product");

        exportField = new JTextField(10);
        exportButton = new JButton("Export Button");

        leftPanel.add(addProductButton);
        leftPanel.add(importField);
        leftPanel.add(importButton);
        leftPanel.add(exportField);
        leftPanel.add(exportButton);
        content.add(leftPanel, BorderLayout.WEST);

        //Right Panel
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.X_AXIS));

        customerStatButton = new JButton("View Customer Dashboard");
        rightPanel.add(customerStatButton);

        content.add(rightPanel, BorderLayout.EAST);

        //Center Panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        instructionLabel = new JLabel("Click \"Manage Product\" to edit product properties");

        JPanel scrollPanel = new JPanel();
        scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.PAGE_AXIS));

        if (store.getProducts().size() == 0) {
            scrollPanel.add(new JLabel("Shopping Cart is Empty!"));
        } else for (Product product : store.getProducts()) {
            JPanel productPanel = new JPanel();
            JLabel productLabel = new JLabel(product.getName());
            JButton productButton = new JButton("Manage Product");

            productPanel.add(productLabel);
            productPanel.add(productButton);
            //TODO add action event listener

            scrollPanel.add(productPanel);
        }

        JScrollPane scrollPane = new JScrollPane(scrollPanel);

        centerPanel.add(instructionLabel);
        centerPanel.add(scrollPane);
        content.add(centerPanel, BorderLayout.CENTER);

        //Bottom Panel
        JPanel bottomPanel = new JPanel();

        saleHistoryButton = new JButton("View Sales History");
        storeDelButton = new JButton("Delete Store");

        bottomPanel.add(saleHistoryButton);
        bottomPanel.add(storeDelButton);

        content.add(bottomPanel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        ArrayList<Product> testProducts = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            testProducts.add(new Product("Product" + (i + 1), "Store" + (i + 1), "Placeholder description", 10, 9.99, 100, 100.25));
        }
        Store testStore = new Store("Test Store", testProducts, null, 0);
        SwingUtilities.invokeLater(new SellerStoreGUI(testStore, null));
    }
}