package tr.producttracking;

import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.swing.FontIcon;
import raven.datetime.component.date.DatePicker;
import tr.producttracking.utils.Status;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    private MainUI app;

    public AddTaskUI(MainUI app) {
        this.app = app;

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

        add_button.addActionListener(e -> AddTask());
    }

    private void AddTask() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String fullName = full_name_textfield.getText();
        String phoneNo = tel_no_textfield.getText().replaceAll("[^0-9+]", "");
        String email = email_textfield.getText();
        LocalDate paymentDate = LocalDate.parse(payment_date_textfield.getText(), formatter);
        String productStatus = (String) product_status_combobox.getSelectedItem();
        String comment = comment_textarea.getText();

        TasksDatabase tdb = app.getTasksDatabase();

        Task newTask = new Task(tdb.getSafeID(), fullName);
        newTask.setPhone_no(phoneNo);
        newTask.setEmail(email);
        newTask.setPayment_date(paymentDate);
        newTask.setProduct_status(productStatus);
        newTask.setComment(comment);
        System.out.println(newTask.toString());

        tdb.add(newTask);
        tdb.save();

        new Status(product_status_combobox.getSelectedItem().toString());

        app.ListAddTask();


        dispose();
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

    public JTextField getFull_name_textfield() {
        return full_name_textfield;
    }

    public JFormattedTextField getTel_no_textfield() {
        return tel_no_textfield;
    }

    public JTextField getEmail_textfield() {
        return email_textfield;
    }

    public JFormattedTextField getPayment_date_textfield() {
        return payment_date_textfield;
    }

    public JComboBox getProduct_status_combobox() {
        return product_status_combobox;
    }

    public JTextArea getComment_textarea() {
        return comment_textarea;
    }
}
