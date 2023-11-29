import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * SellerStoreListGUI
 * <p>
 * GUI for sellers to manage their list of stores (Slide 7)
 */

public class SellerStoreListGUI extends JComponent implements Runnable {
    public SellerStoreListGUI(ArrayList<Store> stores, PrintStream outputStream) {
        this.stores = stores;
        this.outputStream = outputStream;
    }
    private PrintStream outputStream;
    private ArrayList<Store> stores;

    JFrame frame;

    //Top Panel
    JLabel titleLabel;

    //Center Panel
    JLabel instructionLabel;

    //Bottom Panel
    JButton cartButton;
    JButton addStoreButton;
    JButton profileButton;

    @Override
    public void run() {
        frame = new JFrame();
        frame.setTitle("The Marketplace");

        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        //Top Panel
        JPanel topPanel = new JPanel();

        titleLabel = new JLabel("Store List");

        topPanel.add(titleLabel);

        content.add(topPanel, BorderLayout.NORTH);

        //Center Panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));

        if (stores.size() == 0) {
            centerPanel.add(new JLabel("You have no stores!"));
        } else for (Store store : stores) {
            JPanel productPanel = new JPanel();
            JLabel productLabel = new JLabel(store.getStoreName());
            JButton productButton = new JButton("View Store");

            productPanel.add(productLabel);
            productPanel.add(productButton);
            //TODO add action event listener

            centerPanel.add(productPanel);
        }

        JScrollPane scrollPane = new JScrollPane(centerPanel);

        content.add(scrollPane, BorderLayout.CENTER);

        //Bottom Panel
        JPanel bottomPanel = new JPanel();

        cartButton = new JButton("View Shopping Cart Metrics");
        addStoreButton = new JButton("Add Store");
        profileButton = new JButton("Edit Profile");

        bottomPanel.add(cartButton);
        bottomPanel.add(addStoreButton);
        bottomPanel.add(profileButton);

        content.add(bottomPanel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    //test PSVM
    public static void main(String[] args) {
        ArrayList<Product> testProducts = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            testProducts.add(new Product("Product" + (i + 1), "Store" + (i + 1), "Placeholder description", 10, 9.99, 100, 100.25));
        }
        ArrayList<Store> testStores = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            testStores.add(new Store("Store" + (i + 1), testProducts, null, 0));
        }
        SwingUtilities.invokeLater(new SellerStoreListGUI(testStores, null));
    }
}