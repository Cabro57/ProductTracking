package tr.producttracking;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FlatDarkLaf.setup();

                JFrame frame = new MainUI();
                frame.setVisible(true);
            }
        });
    }
}