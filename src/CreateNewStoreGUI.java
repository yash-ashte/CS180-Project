import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;

/**
 * CreateNewStoreGUI
 * <p>
 * GUI for sellers to create a new store
 */
public class CreateNewStoreGUI extends JFrame implements Runnable {

    private JButton createStoreButton;
    private JButton cancelButton;
    private PrintStream outputStream;

    private JTextField storeName;

    public CreateNewStoreGUI (PrintStream outputStream) {
        this.outputStream = outputStream;
    }

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == createStoreButton) {
                if (storeName.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a store name", "Create Store",
                            JOptionPane.ERROR_MESSAGE);
                }
                //TODO: Check if store name already exists
                //TODO: Create new store
            }
            if (e.getSource() == cancelButton) {
                //TODO: go to previous page
            }
        }
    };

    public void run() {
        JFrame frame = new JFrame();
        frame.setTitle("Create Store");

        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        storeName = new JTextField("", 30);
        createStoreButton = new JButton("Create Store!");

        centerPanel.add(storeName);
        centerPanel.add(createStoreButton);

        content.add(centerPanel, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        PrintStream testStream = null;
        SwingUtilities.invokeLater(new CreateNewStoreGUI(testStream));
    }
}