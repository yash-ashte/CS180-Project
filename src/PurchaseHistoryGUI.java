import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PurchaseHistoryGUI extends JComponent implements Runnable {
    JTextField fn;

    public void run() {
        JFrame frame = new JFrame("Purchase History");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel title = new JLabel("Purchase History");
        title.setFont(new Font(String.valueOf(title.getFont()), Font.PLAIN, 24));

        JPanel topPanel = new JPanel();
        content.add(topPanel, BorderLayout.NORTH);
        topPanel.add(title);

        JPanel midPanel = new JPanel();
        midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));
        content.add(midPanel, BorderLayout.CENTER);
        JLabel phTitle = new JLabel("Your Purchase History is as follows");
        phTitle.setBackground(Color.GRAY);
        JTextArea purchaseHist = new JTextArea();
        purchaseHist.setBackground(Color.GRAY);
        purchaseHist.setOpaque(true);
        purchaseHist.setEditable(false);
        JScrollPane sp = new JScrollPane(purchaseHist, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        purchaseHist.setLineWrap(true);
        // purchaseHist.setText("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        midPanel.add(phTitle);
        midPanel.add(purchaseHist);
        midPanel.add(sp);
        //TODO set purchase history text as provided by server



        JPanel botPanel = new JPanel(new FlowLayout());
        content.add(botPanel, BorderLayout.SOUTH);
        JButton market = new JButton("Back to Marketplace");
        fn  = new JTextField("Enter Filename Here");
        fn.setColumns(20);
        JButton export = new JButton("Export");
        JButton cart = new JButton("Manage Cart");
        botPanel.add(market);
        botPanel.add(fn);
        botPanel.add(export);
        botPanel.add(cart);

        market.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                //  SwingUtilities.invokeLater(new Marketplace());
                ;

            }
        });

        cart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                //SwingUtilities.invokeLater(new cart());
                ;

            }
        });

        export.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String file = fn.getText();
                //TODO export to given filename
                JOptionPane.showMessageDialog(null, "Succesfully Exported",
                        "EXPORTED", JOptionPane.INFORMATION_MESSAGE);


            }
        });






        frame.pack();
        frame.setVisible(true);



    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new PurchaseHistoryGUI());
    }
}