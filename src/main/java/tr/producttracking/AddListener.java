package tr.producttracking;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddListener implements ActionListener {

    private DefaultListModel<String> tasks_list_model;

    private JTextField full_name_textfield;
    private JFormattedTextField tel_no_textfield;
    private JTextField email_textfield;
    private JFormattedTextField payment_date_textfield;
    private JComboBox product_status_combobox;
    private JTextPane comment_textpane;

    private JPanel add_panel;

    public AddListener(JTextField full_name_textfield, JFormattedTextField tel_no_textfield, JTextField email_textfield, JFormattedTextField payment_date_textfield, JComboBox product_status_combobox, JTextPane comment_textpane, DefaultListModel<String> task_list_model) {
        this.full_name_textfield = full_name_textfield;
        this.tel_no_textfield = tel_no_textfield;
        this.email_textfield = email_textfield;
        this.payment_date_textfield = payment_date_textfield;
        this.product_status_combobox = product_status_combobox;
        this.comment_textpane = comment_textpane;
        this.tasks_list_model = task_list_model;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String fullName = full_name_textfield.getText();
        String phoneNo = tel_no_textfield.getText();
        String email = email_textfield.getText();
        LocalDate paymentDate = LocalDate.parse(payment_date_textfield.getText(), formatter);// Örneğin "YYYY-MM-DD" formatında
        String productStatus = (String) product_status_combobox.getSelectedItem();
        String comment = comment_textpane.getText();

        Task newTask = new Task(tasks_list_model.getSize() + 1, fullName);
        newTask.setPhone_no(phoneNo);
        newTask.setEmail(email);
        newTask.setPayment_date(paymentDate);
        newTask.setProduct_status(productStatus);
        newTask.setComment(comment);

        tasks_list_model.addElement(newTask.getFull_name());
    }

}
