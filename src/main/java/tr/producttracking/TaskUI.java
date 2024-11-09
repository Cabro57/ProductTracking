package tr.producttracking;

import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;

public class TaskUI {
    private JPanel main_panel;
    private JButton delete_button;
    private JLabel full_name_label;
    private JLabel tip_label;

    private Task task;

    public TaskUI() {
        FontIcon delete_icon = FontIcon.of(Feather.FTH_TRASH);
        delete_button.setIcon(delete_icon);
    }
}
