package ru.vsu.cs.skofenko;

import ru.vsu.cs.skofenko.logic.ParserAndPainter;
import ru.vsu.cs.util.SwingUtils;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class FrameMain extends JFrame {

    private JPanel panelMain;
    private JButton constructButton;
    private JTextField textField;
    private JLabel labelImg;

    public FrameMain() {
        this.setTitle("Task 12");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();

        constructButton.addActionListener(e -> {
            try {
                BufferedImage img = ParserAndPainter.parseAndPaint(textField.getText());
                labelImg.setText("");
                labelImg.setIcon(new ImageIcon(img));
            } catch (Exception exception) {
                SwingUtils.showErrorMessageBox(exception);
            }
        });
    }
}
