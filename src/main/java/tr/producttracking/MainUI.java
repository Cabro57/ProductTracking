package tr.producttracking;

import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.swing.FontIcon;
import raven.datetime.component.date.DatePicker;
import tr.producttracking.utils.Status;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class MainUI extends JFrame {

    private JPanel main_panel;

    private JPanel title_panel;
    private JButton settings_button;
    private JLabel title_label;
    private JButton close_button;

    private JPanel list_panel;
    private JFormattedTextField search_textfield;
    private JList tasks_list;
    private JButton add_button;

    private JPanel detail_panel;
    private JLabel full_name_label;
    private JTextField full_name_textfield;
    private JLabel tel_no_label;
    private JFormattedTextField tel_no_textfield;
    private JLabel email_label;
    private JTextField email_textfield;
    private JLabel payment_date_label;
    private JFormattedTextField payment_date_textfield;
    private JLabel product_status_label;
    private JComboBox product_status_combobox;
    private JLabel comment_label;
    private JTextArea comment_textarea;
    private JButton editable_button;

    private DefaultListModel<String> tasks_list_model;

    public MainUI() {

        add(main_panel);
        setSize(new Dimension(600,700));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true);

        JButton clear_button = new JButton(FontIcon.of(Feather.FTH_X, 18, Color.LIGHT_GRAY));

        search_textfield.putClientProperty("JTextField.trailingComponent", clear_button);
        search_textfield.putClientProperty("JTextField.leadingIcon", FontIcon.of(Feather.FTH_SEARCH, 20, Color.LIGHT_GRAY));

        //tasks_list_model = new DefaultListModel<>();
        //tasks_list.setModel(tasks_list_model);



        DatePicker datePicker = new DatePicker();
        datePicker.setEditorIcon(FontIcon.of(Feather.FTH_CALENDAR, Color.LIGHT_GRAY));
        datePicker.setEditor(payment_date_textfield);
        //datePicker.setSelectedDate(LocalDate.now());

        for (String status : Status.load()) {
            product_status_combobox.addItem(status);
        }
        product_status_combobox.setSelectedItem(null);

        comment_textarea.setBorder(new LineBorder(Color.GRAY,1));
        comment_textarea.setText("Selam yavuz nasılsın!!!");

        add_button.setIcon(FontIcon.of(Feather.FTH_USER_PLUS, Color.LIGHT_GRAY));
        add_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog add_task = new AddTaskUI();
                add_task.setVisible(true);
            }
        });

        clear_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search_textfield.setText("");
            }
        });

        settings_button.setIcon(FontIcon.of(Feather.FTH_SETTINGS, Color.LIGHT_GRAY));
        settings_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MainUI.this,
                        "Yapım aşamasında!!!", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        FontIcon close_icon = FontIcon.of(Feather.FTH_X, 24, Color.LIGHT_GRAY);
        close_button.setIcon(close_icon);
        close_button.setContentAreaFilled(false);

        close_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        editable_button.setIcon(FontIcon.of(Feather.FTH_USER_CHECK, Color.LIGHT_GRAY));
        editable_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!full_name_textfield.isEditable()) {

                    full_name_textfield.setEditable(true);
                    tel_no_textfield.setEditable(true);
                    email_textfield.setEditable(true);
                    payment_date_textfield.setEditable(true);
                    comment_textarea.setEditable(true);
                }
                else {
                    full_name_textfield.setEditable(false);
                    tel_no_textfield.setEditable(false);
                    email_textfield.setEditable(false);
                    payment_date_textfield.setEditable(false);
                    comment_textarea.setEditable(false);
                }

            }
        });
    }

    private void createUIComponents() {
        try {
            MaskFormatter phone_formatter = new MaskFormatter("+90 (###) ### ## ##");
            phone_formatter.setPlaceholderCharacter('_');
            tel_no_textfield = new JFormattedTextField(phone_formatter);
            tel_no_textfield.setColumns(10);

        } catch (ParseException e) {
            tel_no_textfield = new JFormattedTextField();
            throw new RuntimeException(e);
        }
        // TODO: place custom component creation code here
    }


}
