import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;

/**
 * EditProductGUI
 * <p>
 * GUI for sellers to edit a product in their store
 */
public class EditProductGUI extends JComponent implements Runnable {
    private PrintStream outputStream;
    private JTextField priceField;
    private JTextField quantityField;
    private JTextField descriptionField;

    private JButton editProductButton;
    private JButton cancelButton;

    private JButton removeProductButton;
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == editProductButton) {
                //TODO: edit product
            } else if (e.getSource() == cancelButton) {
                //TODO: go to previous page
            } else if (e.getSource() == removeProductButton) {
                //TODO: delete product
            }
        }
    };

    public EditProductGUI(PrintStream outputStream) {
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
        priceField = new JTextField("", 30);
        quantityField = new JTextField("", 30);
        descriptionField = new JTextField("", 30);

        centerPanel.add(priceField);
        centerPanel.add(quantityField);
        centerPanel.add(descriptionField);

        content.add(centerPanel, BorderLayout.CENTER);

        //Bottom Panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        cancelButton = new JButton("Cancel");
        editProductButton = new JButton("Add Product");
        removeProductButton = new JButton("Remove Product");

        bottomPanel.add(cancelButton);
        bottomPanel.add(editProductButton);
        bottomPanel.add(removeProductButton);

        content.add(bottomPanel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        PrintStream testStream = null;
        SwingUtilities.invokeLater(new EditProductGUI(testStream));
    }

}