import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.ArrayList;

public class StoresDash {
    private PrintStream outputStream;

    private ArrayList<Store> StoreHist;
    public StoresDash(ArrayList<Store> StoreHist, PrintStream outputStream) {
        this.StoreHist = StoreHist;
        this.outputStream = outputStream;
    }

    public void run() {
        JFrame frame = new JFrame("Store Dashboard");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel title = new JLabel("Store Dashboard");
        title.setFont(new Font(String.valueOf(title.getFont()), Font.PLAIN, 24));
        JButton back = new JButton("Back");
        JComboBox sortDropdown;
        ActionListener sortListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox parentCb = (JComboBox) e.getSource(); //Initializes parent dropdown
                int sortOption = parentCb.getSelectedIndex();
                //TODO
            }
        };

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        content.add(topPanel, BorderLayout.NORTH);
        String[] sortOptions = {"Number of Products sold(Low to High)", "Number of Products sold(High to Low)", "Number of Products sold(High to Low)",
                "Quantity(High to Low)"};
        sortDropdown = new JComboBox(sortOptions);
        sortDropdown.addActionListener(sortListener);
        topPanel.add(sortDropdown);
        topPanel.add(title);
        topPanel.add(back);

        JPanel midPanel = new JPanel();
        midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));
        content.add(midPanel, BorderLayout.CENTER);
        JTextArea storeDash = new JTextArea();
        storeDash.setBackground(Color.GRAY);
        storeDash.setOpaque(true);
        storeDash.setEditable(false);
        JScrollPane sp = new JScrollPane(storeDash, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        storeDash.setLineWrap(true);
        midPanel.add(storeDash);
        storeDash.setText("");
        //TODO add text to storeDash

        frame.pack();
        frame.setVisible(true);






    }
/*
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new StoresDash());
    }*/
}