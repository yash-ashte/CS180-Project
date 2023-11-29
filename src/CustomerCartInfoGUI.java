import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * CustomerCartInfoGUI
 * <p>
 * GUI for sellers to see which of their products are in customer carts
 *
 */
public class CustomerCartInfoGUI extends JFrame implements Runnable {

    private PrintStream outputStream;
    private JPanel totalProducts;
    private JButton backButton;
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == backButton) {
                //TODO: go to previous page
            }
        }
    };

    ArrayList<Product> products;
    int total;

    public CustomerCartInfoGUI(PrintStream outputStream, ArrayList<Product> products, int total) {
        this.outputStream = outputStream;
        this.products = products;
        this.total = total;
    }

    public void run() {
        JFrame frame = new JFrame();
        frame.setTitle("Products in Customer Carts");

        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        //Top panel
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel(String.valueOf(total)));
        topPanel.add(backButton);
        content.add(topPanel, BorderLayout.NORTH);

        //Center Panel
        JPanel centerPanel = new JPanel();
        //Products in Carts panel
        JPanel productsInCartPanel = new JPanel();
        productsInCartPanel.setLayout(new BoxLayout(productsInCartPanel, BoxLayout.Y_AXIS));

        if (total == 0) {
            productsInCartPanel.add(new JLabel("No products in carts yet."));
        } else {
            for(Product p : products) {
                JPanel productPanel = new JPanel();
                JLabel productLabel = new JLabel("Name: " + p.getName() + "; Store: " + p.getStoreName() + "; Info: " + p.getDesc());

                productPanel.add(productLabel);

                productsInCartPanel.add(productPanel);
            }
        }

        JScrollPane inCartScrollPane = new JScrollPane(productsInCartPanel);

        centerPanel.add(inCartScrollPane);
        content.add(centerPanel, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        ArrayList<Product> testProducts = new ArrayList<>();
        int testInt = 0;
        PrintStream testStream = null;
        SwingUtilities.invokeLater(new CustomerCartInfoGUI(testStream, testProducts, testInt));
    }
}