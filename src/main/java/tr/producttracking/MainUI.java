package tr.producttracking;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.intellijthemes.FlatDarkFlatIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialDarkerIJTheme;
import raven.datetime.component.date.DatePicker;

import javax.swing.*;
import java.awt.*;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;

public class MainUI extends JFrame {

    private JTextField full_name_textfield;
    private JFormattedTextField tel_no_textfield;
    private JTextField email_textfield;
    private JFormattedTextField payment_date_textfield;
    private JComboBox product_status_combobox;
    private JTextPane comment_textpane;
    private JList tasks_list;
    private JButton add_button;
    private JPanel main_panel;
    private JList list1;
    private JButton editor_button;

    private DefaultListModel<String> tasks_list_model;

    public MainUI() {

        add(main_panel);
        setSize(new Dimension(600,960));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        //setUndecorated(true);

        //tasks_list_model = new DefaultListModel<>();
        //tasks_list.setModel(tasks_list_model);

        //DatePicker datePicker = new DatePicker();
        //datePicker.setEditor(payment_date_textfield);
        //editor_button.addActionListener(e -> datePicker.showPopup());

        //add_button.addActionListener(new AddListener(full_name_textfield, tel_no_textfield, email_textfield, payment_date_textfield, product_status_combobox, comment_textpane, tasks_list_model));

    }


    /*private void createUIComponents() {
        try {
            MaskFormatter telNoFormatter = new MaskFormatter("(###) ### ## ##");
            telNoFormatter.setPlaceholderCharacter('_');
            tel_no_textfield = new JFormattedTextField(telNoFormatter);
        } catch (ParseException e) {
            tel_no_textfield = new JFormattedTextField();
            throw new RuntimeException(e);
        }
        // TODO: place custom component creation code here
    }*/
}
