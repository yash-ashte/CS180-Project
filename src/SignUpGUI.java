import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.PrintStream;
import java.net.*;

/**
 * SignUpGUI
 * <p>
 * GUI for users creating a new customer/seller profile
 */
public class SignUpGUI extends JComponent implements Runnable {
    public SignUpGUI(PrintStream outputStream) {
        this.outputStream = outputStream;
    }
    PrintStream outputStream;
    JFrame frame;
    JButton signUpButton;
    JButton cancelButton;
    JTextField nameField;
    JTextField emailField;
    JTextField passField;
    JTextField passConfirmField;

    @Override
    public void run() {
        frame = new JFrame();
        frame.setTitle("The Marketplace");

        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        nameField = new JTextField("", 30);
        emailField = new JTextField("", 30);
        passField = new JTextField("", 30);
        passConfirmField = new JTextField("", 30);

        signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nameField.getText().isBlank()) { //if conditions checking for any fields that are blank/incorrect
                    JOptionPane.showMessageDialog(null, "Name cannot be empty!", "Sign Up",
                            JOptionPane.ERROR_MESSAGE);
                } else if (emailField.getText().isBlank()) {
                    JOptionPane.showMessageDialog(null, "Email cannot be empty!", "Sign Up",
                            JOptionPane.ERROR_MESSAGE);
                } else if (passField.getText().isBlank()) {
                    JOptionPane.showMessageDialog(null, "Password cannot be empty!", "Sign Up",
                            JOptionPane.ERROR_MESSAGE);
                } else if (!(passConfirmField.getText().equals(passField.getText()))) {
                    JOptionPane.showMessageDialog(null, "Passwords did not match!", "Sign Up",
                            JOptionPane.ERROR_MESSAGE);
                } else { //if all conditions pass then prompt seller/customer
                    String[] options = {"Seller", "Customer"};
                    int userSelection = JOptionPane.showOptionDialog(null, "Are you a seller or a customer?",
                            "Select an account type",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                    if (userSelection == 0) {
                        //TODO
                    } else if (userSelection == 1) {

                    }
                }
            }
        });

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });

        centerPanel.add(new JLabel("Name"));
        centerPanel.add(nameField);

        centerPanel.add(new JLabel("E-Mail"));
        centerPanel.add(emailField);

        centerPanel.add(new JLabel("Password"));
        centerPanel.add(passField);

        centerPanel.add(new JLabel("Confirm Password"));
        centerPanel.add(passConfirmField);

        centerPanel.add(signUpButton);

        content.add(centerPanel, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    //Test PSVM
    /*
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new SignUpGUI(null));
    }
    */

}