import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * SellerStoreListGUI
 * <p>
 * GUI for sellers to view metrics regarding # purchases by each customer
 * and # sales for each product (Slide 15)
 */

public class SellerStoreStatisticsGUI extends JComponent implements Runnable {
    public SellerStoreStatisticsGUI(ArrayList<String> customerNames, ArrayList<String> customerPurchases,
                                    ArrayList<String> productNames, ArrayList<String> productSales,
                                    PrintStream outputStream) {
        this.store = store;
        this.customerNames = customerNames;
        this.customerPurchases = customerPurchases;
        this.productNames = productNames;
        this.productSales = productSales;
    }
    private PrintStream outputStream;
    ArrayList<String> customerNames;
    ArrayList<String> customerPurchases;

    ArrayList<String> productNames;
    ArrayList<String> productSales;
    private Store store;

    JFrame frame;

    //Top Panel
    JLabel titleLabel;

    JComboBox sortCustomerDropdown;
    ActionListener customerSortListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox parentCb = (JComboBox) e.getSource(); //Initializes parent dropdown
            int sortOption = parentCb.getSelectedIndex();
        }
    };


    JComboBox sortProductDropdown;
    ActionListener productSortListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox parentCb = (JComboBox) e.getSource(); //Initializes parent dropdown
            int sortOption = parentCb.getSelectedIndex();
        }
    };

    //Bottom Panel
    JButton backButton;

    @Override
    public void run() {
        frame = new JFrame();
        frame.setTitle("The Marketplace");

        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        //Top Panel
        JPanel topPanel = new JPanel();

        titleLabel = new JLabel("Store Statistics Dashboard");

        String[] sortCustomerOptions = {"# of purchases(High to Low)", "# of purchases(Low to High)"};
        sortCustomerDropdown = new JComboBox(sortCustomerOptions);
        sortCustomerDropdown.addActionListener(customerSortListener);

        String[] sortProductOptions = {"# of sales(High to Low)", "# of sales(Low to High)"};
        sortProductDropdown = new JComboBox(sortProductOptions);
        sortProductDropdown.addActionListener(productSortListener);

        topPanel.add(sortCustomerDropdown);
        topPanel.add(titleLabel);
        topPanel.add(sortProductDropdown);
        content.add(topPanel, BorderLayout.NORTH);

        //Customer List Panel
        JPanel customerListPanel = new JPanel();
        customerListPanel.setLayout(new BoxLayout(customerListPanel, BoxLayout.Y_AXIS));

        if (customerNames.size() == 0) {
            customerListPanel.add(new JLabel("This store has not had any customers yet."));
        } else for (int i = 0; i < customerNames.size(); i++) {
            JPanel customerPanel = new JPanel();
            JLabel customerLabel = new JLabel(customerNames.get(i) + ": " + customerPurchases.get(i));

            customerPanel.add(customerLabel);

            customerListPanel.add(customerPanel);
        }

        JScrollPane customerScrollPane = new JScrollPane(customerListPanel);

        //Product List Panel
        JPanel productListPanel = new JPanel();
        productListPanel.setLayout(new BoxLayout(productListPanel, BoxLayout.Y_AXIS));

        if (productNames.size() == 0) {
            productListPanel.add(new JLabel("This store has no products."));
        } else for (int i = 0; i < productNames.size(); i++) {
            JPanel productPanel = new JPanel();
            JLabel productLabel = new JLabel(productNames.get(i) + ": " + productSales.get(i));

            productPanel.add(productLabel);

            productListPanel.add(productPanel);
        }

        JScrollPane productScrollPane = new JScrollPane(productListPanel);

        //Center Panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout());
        centerPanel.add(customerScrollPane);
        centerPanel.add(productScrollPane);

        content.add(centerPanel, BorderLayout.CENTER);

        //Bottom Panel
        JPanel bottomPanel = new JPanel();

        backButton = new JButton("Back");

        bottomPanel.add(backButton);
        content.add(bottomPanel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        ArrayList<String> testNames = new ArrayList<>();
        ArrayList<String> testPurchases = new ArrayList<>();
        ArrayList<String> testProductNames = new ArrayList<>();
        ArrayList<String> testProductSales = new ArrayList<>();

        for(int i = 0; i < 20; i++) {
            testNames.add("Customer " + (i + 1));
            testPurchases.add("100");
            testProductNames.add("Product " + (i + 1));
            testProductSales.add("100");
        }
        SwingUtilities.invokeLater(new SellerStoreStatisticsGUI(testNames, testPurchases,
                testProductNames, testProductSales, null));
    }
}