package tr.producttracking;

import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.swing.FontIcon;
import tr.producttracking.utils.SettingsManager;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SettingsUI extends JDialog {
    private JPanel main_panel;
    private JTextField set_title_textfield;
    private JButton ok_button;
    private JLabel title_set_label;
    private JPanel footer_panel;
    private JPanel body_panel;
    private JButton cancel_button;
    private JTextField storage_path_textfield;
    private JLabel storage_path_label;

    private final MainUI app;

    public SettingsUI(MainUI app) {
        this.app = app;

        setTitle("Ayarlar");
        setSize(new Dimension(350, 530));
        setLocationRelativeTo(null);

        body_panel.setBorder(new MatteBorder(1,0,0,0, Color.decode("#515458")));

        set_title_textfield.setText(SettingsManager.getSetting("title").toString());

        JButton storage_path_button = new JButton(FontIcon.of(Feather.FTH_FOLDER, Color.LIGHT_GRAY));
        storage_path_button.setBorder(null);
        storage_path_button.addActionListener(e -> OpenStorageFolder());

        String storage_path = System.getProperty("user.home") + SettingsManager.getSettingstoString("storage_path");
        storage_path_textfield.setText(storage_path);
        storage_path_textfield.putClientProperty("JTextField.trailingComponent", storage_path_button);

        footer_panel.setBorder(new MatteBorder(1,0,0,0, Color.decode("#222326")));

        ok_button.setBorderPainted(false);
        ok_button.addActionListener(e -> OkButton());

        cancel_button.addActionListener(e -> dispose());

        add(main_panel);
    }

    private void OkButton() {
        app.setTitle_panel(set_title_textfield.getText());
        SettingsManager.setSetting("title", set_title_textfield.getText());
        SettingsManager.saveSettings();
        dispose();
    }

    private void OpenStorageFolder() {
        String SETTINGS_FOLDER_PATH = storage_path_textfield.getText();
        try {
            File settingsFolder = new File(SETTINGS_FOLDER_PATH);
            if (!settingsFolder.exists()) {
                System.out.println("Klasör bulunamadı: " + SETTINGS_FOLDER_PATH);
            } else if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(settingsFolder); // Klasörü aç
            } else {
                System.out.println("Desktop özelliği desteklenmiyor.");
            }
        } catch (IOException e) {
            System.err.println("Klasör açılırken bir hata oluştu: " + e.getMessage());
        }
    }
}