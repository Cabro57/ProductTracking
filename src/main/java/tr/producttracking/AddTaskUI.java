package tr.producttracking;

import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.swing.FontIcon;
import raven.datetime.component.date.DatePicker;
import tr.producttracking.utils.Status;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;

public class AddTaskUI extends JDialog{
    private JPanel main_panel;
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
    private JButton add_button;

    public AddTaskUI() {
        setTitle("Yeni İş Ekle");
        setSize(new Dimension(350, 530));
        setLocationRelativeTo(null);
        add(main_panel);

        DatePicker datePicker = new DatePicker();
        datePicker.setEditorIcon(FontIcon.of(Feather.FTH_CALENDAR, Color.LIGHT_GRAY));
        datePicker.setEditor(payment_date_textfield);
        datePicker.setSelectedDate(LocalDate.now());

        for (String status : Status.load()) {
            product_status_combobox.addItem(status);
        }
        product_status_combobox.setSelectedItem(null);

        comment_textarea.setLineWrap(true);
        comment_textarea.setWrapStyleWord(true);

        add_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            String product_status = product_status_combobox.getSelectedItem().toString();
            if (!Status.load().contains(product_status)) {
                System.out.println(product_status);
                new Status(product_status);
                }

            dispose();
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
            throw new RuntimeException(e);
        }
        // TODO: place custom component creation code here
    }
}
