import javax.swing.*;import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;

/**
 * AddProductGUI
 * <p>
 * GUI for sellers to add a new product to their store
 *
 * @author Swara Savagaonkar, CS180 Project 05
 * @version 12/05/22
 */
public class AddProductGUI extends JComponent implements Runnable {
    private PrintStream outputStream;

    private JTextField productNameField;
    private JTextField priceField;
    private JTextField quantityField;
    private JTextField descriptionField;

    private JButton addProductButton;
    private JButton cancelButton;
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == addProductButton) {
                if (productNameField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a product name", "Add Product",
                            JOptionPane.ERROR_MESSAGE);
                }
                //TODO: add product to store
            } else if (e.getSource() == cancelButton) {
                //TODO: go to previous page
            }
        }
    };

    public AddProductGUI(PrintStream outputStream) {
        this.outputStream = outputStream;
    }

    public void run() {
        JFrame frame = new JFrame();
        frame.setTitle("Add Product");

        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        //Center Panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        productNameField = new JTextField("", 30);
        priceField = new JTextField("", 30);
        quantityField = new JTextField("", 30);
        descriptionField = new JTextField("", 30);

        centerPanel.add(productNameField);
        centerPanel.add(priceField);
        centerPanel.add(quantityField);
        centerPanel.add(descriptionField);

        content.add(centerPanel, BorderLayout.CENTER);

        //Bottom Panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        cancelButton = new JButton("Cancel");
        addProductButton = new JButton("Add Product");

        bottomPanel.add(cancelButton);
        bottomPanel.add(addProductButton);

        content.add(bottomPanel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        PrintStream testStream = null;
        SwingUtilities.invokeLater(new AddProductGUI(testStream));
    }

}