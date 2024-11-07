package tr.producttracking;

import javax.swing.*;
import java.awt.*;

public class MainUI extends JFrame {

    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JComboBox comboBox1;
    private JTextPane textPane1;
    private JList list1;
    private JTextField textField5;
    private JButton search_button;
    private JButton add_button;
    private JPanel main_panel;

    public MainUI() {
        add(main_panel);
        setSize(new Dimension(400,600));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
