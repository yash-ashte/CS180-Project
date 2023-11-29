import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaleHistoryGUI extends JComponent implements Runnable{
    @Override
    public void run() {
        JFrame frame = new JFrame("Sale History");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel title = new JLabel("Sale History");
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
        botPanel.add(market);

        //JButton market = new JButton("Back to Marketplace");
        market.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                //  SwingUtilities.invokeLater(new Marketplace());
                ;

            }
        });

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new SaleHistoryGUI());
    }
}