package tr.producttracking;

import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.swing.FontIcon;
import raven.datetime.component.date.DatePicker;
import tr.producttracking.utils.SettingsManager;
import tr.producttracking.utils.Status;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class MainUI extends JFrame {

    private JPanel main_panel;

    private JPanel title_panel;
    private JButton settings_button;
    private JLabel title_label;
    private JButton close_button;

    private JPanel list_panel;
    private JFormattedTextField search_textfield;
    private JButton clear_button;
    private JScrollPane task_scrollpane;
    private JPanel tasks_panel;
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
    private JList tasks_list;
    private JButton remove_button;

    private DefaultListModel<Task> tasks_list_model;

    private TasksDatabase tasksDatabase;

    private SettingsManager settingsManager;

    private JToolBar date_toolbar;

    private DatePicker datePicker;
    public MainUI() {
        tasksDatabase = new TasksDatabase("taskDB.csv");
        settingsManager = new SettingsManager();


        add(main_panel);
        setSize(new Dimension(600,700));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        //setUndecorated(true);
        setResizable(false);

        settings_button.setBorderPainted(false);
        settings_button.setIcon(FontIcon.of(Feather.FTH_SETTINGS, Color.LIGHT_GRAY));
        settings_button.addActionListener(e -> new SettingsUI(MainUI.this).setVisible(true));

        title_label.setText(SettingsManager.getSettingstoString("title"));

        clear_button = new JButton(FontIcon.of(Feather.FTH_X, 12, Color.GRAY));
        clear_button.addActionListener(e -> search_textfield.setText(""));

        //search_textfield.putClientProperty("JTextField.trailingComponent", clear_button);
        search_textfield.putClientProperty("JTextField.leadingIcon", FontIcon.of(Feather.FTH_SEARCH, 20, Color.LIGHT_GRAY));
        search_textfield.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!search_textfield.getText().isEmpty()) {
                    search_textfield.putClientProperty("JTextField.trailingComponent", clear_button);
                } else {
                    // TextField boşsa clear_button kaldırılır
                    search_textfield.putClientProperty("JTextField.trailingComponent", null);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (!search_textfield.getText().isEmpty()) {
                    search_textfield.putClientProperty("JTextField.trailingComponent", clear_button);
                } else {
                    // TextField boşsa clear_button kaldırılır
                    search_textfield.putClientProperty("JTextField.trailingComponent", null);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (!search_textfield.getText().isEmpty()) {
                    search_textfield.putClientProperty("JTextField.trailingComponent", clear_button);
                } else {
                    // TextField boşsa clear_button kaldırılır
                    search_textfield.putClientProperty("JTextField.trailingComponent", null);
                }
            }
        });

        tasks_list_model = new DefaultListModel<>();
        tasks_list.setModel(tasks_list_model);

        tasks_list.addListSelectionListener(this::SelectTaskOpen);

        ListAddTask();

        DatePicker datePicker = new DatePicker();
        datePicker.setEditorIcon(FontIcon.of(Feather.FTH_CALENDAR, Color.LIGHT_GRAY));
        //datePicker.setSelectedDate(LocalDate.now());
        datePicker.setEditor(payment_date_textfield);
        date_toolbar = (JToolBar) payment_date_textfield.getClientProperty("JTextField.trailingComponent");
        date_toolbar.getComponent(0).setEnabled(false);

        for (String status : Status.load()) {
            product_status_combobox.addItem(status);
        }
        product_status_combobox.setSelectedItem(null);

        comment_textarea.setBorder(new LineBorder(Color.GRAY,1));

        add_button.setIcon(FontIcon.of(Feather.FTH_USER_PLUS, Color.LIGHT_GRAY));
        add_button.addActionListener(e -> new AddTaskUI(MainUI.this).setVisible(true));


        editable_button.setIcon(FontIcon.of(Feather.FTH_USER_CHECK, Color.LIGHT_GRAY));
        editable_button.addActionListener(e -> EditableMode());

        remove_button.setIcon(FontIcon.of(Feather.FTH_TRASH, 20, Color.LIGHT_GRAY));
        remove_button.addActionListener(e -> SelectTaskDelete());
    }

    private void EditableMode() {
        if (!full_name_textfield.isEditable()) {

            full_name_textfield.setEditable(true);
            tel_no_textfield.setEditable(true);
            email_textfield.setEditable(true);
            payment_date_textfield.setEditable(true);
            date_toolbar.getComponent(0).setEnabled(true);
            comment_textarea.setEditable(true);
        }
        else {
            full_name_textfield.setEditable(false);
            tel_no_textfield.setEditable(false);
            email_textfield.setEditable(false);
            payment_date_textfield.setEditable(false);
            date_toolbar.getComponent(0).setEnabled(false);
            comment_textarea.setEditable(false);
        }
    }

    private void SettingsOpen() {
        JOptionPane.showMessageDialog(MainUI.this,
                "Yapım aşamasında!!!", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
    }

    private void SelectTaskOpen(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) { // Seçim sabitlendiğinde çalışır
            Task selectedItem = (Task) tasks_list.getSelectedValue(); // Seçilen öğeyi al
            if (selectedItem != null) {
                full_name_textfield.setText(selectedItem.getFull_name());
                tel_no_textfield.setText(selectedItem.getPhone_no());
                email_textfield.setText(selectedItem.getEmail());
                payment_date_textfield.setText(selectedItem.getPayment_datetoString());
                product_status_combobox.setSelectedItem(selectedItem.getProduct_status());
                comment_textarea.setText(selectedItem.getComment());
            }
        }
    }

    protected void SelectTaskDelete() {
        Task selectedItem = (Task) tasks_list.getSelectedValue();
        if (selectedItem != null) {
            tasksDatabase.remove(selectedItem);
            tasksDatabase.save();
            ListAddTask();
        }
    }

    protected void ListAddTask() {
        if (!tasks_list_model.isEmpty()) {
            tasks_list_model.clear();
        }
        for (Task task : tasksDatabase) {
            tasks_list_model.addElement(task);
        }
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

    public TasksDatabase getTasksDatabase() {
        return tasksDatabase;
    }

    public void setTitle_panel(String title) {
        title_label.setText(title);
    }
}
