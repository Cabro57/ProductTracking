package tr.producttracking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class SelectablePanel extends JPanel {
    private boolean isSelected = false;

    public SelectablePanel() {

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                toggleSelection();
            }
        });
    }

    public void toggleSelection() {
        isSelected = !isSelected;
        updateBackground();
    }

    private void updateBackground() {
        if (isSelected) {
            setBackground(Color.CYAN);
        } else {
            setBackground(Color.WHITE);
        }
        repaint();
    }
}
