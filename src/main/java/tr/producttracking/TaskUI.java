package tr.producttracking;

import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import java.awt.*;


public class TaskUI{
    private JPanel main_panel;
    private JButton delete_button;
    private JButton full_name_label;
    private JButton tip_label;

    private Task task;

    public TaskUI(Task task) {
        this.task = task;

        main_panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        //main_panel.setSize(new Dimension(100, 50));


        FontIcon delete_icon = FontIcon.of(Feather.FTH_TRASH);
        delete_button.setIcon(delete_icon);

        full_name_label.setContentAreaFilled(false);
        full_name_label.setText(task.getFull_name());
        full_name_label.addActionListener(e -> SelectedTask());

        tip_label.setContentAreaFilled(false);
        tip_label.setText(task.getProduct_status());

        delete_button.addActionListener(e -> DeleteTask());

    }

    private void DeleteTask() {
        System.out.println("Deneme");
    }

    private void SelectedTask() {
        System.out.println("Selected: " + task.getFull_name());
    }

    public JPanel getMain_panel() {
        return main_panel;
    }

    private void createUIComponents() {
        main_panel = new SelectablePanel();
        // TODO: place custom component creation code here
    }
}

