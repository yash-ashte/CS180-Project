import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;

/**
 * ChangeProfileGUI
 * <p>
 * GUI for sellers and customers to edit their profile info
 */
public class ChangeProfileGUI extends JComponent implements Runnable {

    public ChangeProfileGUI(PrintStream outputStream) {
        this.outputStream = outputStream;
    }

    JButton updateProfileButton;

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == updateProfileButton) {
                if (!(passConfirmField.getText().equals(passField.getText()))) {
                    JOptionPane.showMessageDialog(null, "Passwords did not match!", "Update Profile",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    //TODO: update user profile
                }
            }
        }
    };
    private JTextField nameField;
    private JTextField emailField;
    private JTextField passField;
    private JTextField passConfirmField;

    private PrintStream outputStream;

    public void run() {
        JFrame frame = new JFrame();
        frame.setTitle("Update Profile");

        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        nameField = new JTextField("", 30);
        emailField = new JTextField("", 30);
        passField = new JTextField("", 30);
        passConfirmField = new JTextField("", 30);
        updateProfileButton = new JButton("Update Profile");

        centerPanel.add(nameField);
        centerPanel.add(emailField);
        centerPanel.add(passField);
        centerPanel.add(passConfirmField);
        centerPanel.add(updateProfileButton);

        content.add(centerPanel, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        PrintStream testStream = null;
        SwingUtilities.invokeLater(new ChangeProfileGUI(testStream));
    }

}