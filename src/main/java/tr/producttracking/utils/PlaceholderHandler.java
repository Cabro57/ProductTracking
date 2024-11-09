package tr.producttracking.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class PlaceholderHandler implements FocusListener {
    private final JTextField textField;
    private final String placeholder;
    private boolean showingPlaceholder;

    public PlaceholderHandler(JTextField textField, String placeholder) {
        this.textField = textField;
        this.placeholder = placeholder;
        this.showingPlaceholder = true;

        // Başlangıçta placeholder göster
        showPlaceholder();

        // Odak dinleyicisini ekle
        textField.addFocusListener(this);
    }

    private void showPlaceholder() {
        textField.setText(placeholder);
        textField.setForeground(Color.GRAY); // Placeholder rengini gri yapıyoruz
        showingPlaceholder = true;
    }

    private void hidePlaceholder() {
        textField.setText("");
        textField.setForeground(Color.LIGHT_GRAY); // Normal yazı rengini siyah yapıyoruz
        showingPlaceholder = false;
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (showingPlaceholder) {
            hidePlaceholder();
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (textField.getText().isEmpty()) {
            showPlaceholder();
        }
    }

    // Placeholder görünürken getText() çağrılırsa boş string döndür
    public String getText() {
        return showingPlaceholder ? "" : textField.getText();
    }
}
