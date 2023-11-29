import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * ShoppingCartGUI
 * <p>
 * GUI for customers to manage products in their shopping cart and make purchases
 */

public class ShoppingCartGUI extends JComponent implements Runnable {
    public ShoppingCartGUI(ArrayList<Product> products, PrintStream outputStream) {
        this.products = products;
        this.outputStream = outputStream;
    }
    private PrintStream outputStream;
    private ArrayList<Product> products;
    JFrame frame;

    //Top panel
    JLabel titleLabel;

    //Bottom Panel
    JButton marketButton;
    JButton purchaseAllButton;
    JButton purchaseHistoryButton;

    ActionListener bottomListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == marketButton) {

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

        titleLabel = new JLabel("Shopping Cart");

        topPanel.add(titleLabel);

        content.add(topPanel, BorderLayout.NORTH);

        //Center panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));

        if (products.size() == 0) {
            centerPanel.add(new JLabel("Shopping Cart is Empty!"));
        } else for (Product product : products) {
            JPanel productPanel = new JPanel();
            JLabel productLabel = new JLabel(product.getName() + " | " + product.getStoreName() + " | "
                    + product.getPrice());
            JButton productButton = new JButton("Remove Product");

            productPanel.add(productLabel);
            productPanel.add(productButton);
            //TODO add action event listener

            centerPanel.add(productPanel);
        }

        JScrollPane scrollPane = new JScrollPane(centerPanel);

        content.add(scrollPane, BorderLayout.CENTER);

        //Bottom Panel
        JPanel bottomPanel = new JPanel();

        marketButton = new JButton("Return to Market");
        marketButton.addActionListener(bottomListener);
        purchaseAllButton = new JButton("Purchase All");
        purchaseHistoryButton = new JButton("Purchase History");

        bottomPanel.add(marketButton);
        bottomPanel.add(purchaseAllButton);
        bottomPanel.add(purchaseHistoryButton);

        content.add(bottomPanel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    //Test PSVM
    public static void main(String[] args) {
        ArrayList<Product> testProducts = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            testProducts.add(new Product("Product" + (i + 1), "Store" + (i + 1), "Placeholder description", 10, 9.99, 100, 100.25));
        }
        SwingUtilities.invokeLater(new ShoppingCartGUI(testProducts, null));
    }
}