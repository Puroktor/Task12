package ru.vsu.cs.skofenko.logic;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MyBufferedImage {
    private BufferedImage bufferedImage;
    private Graphics2D g2d;
    private int charWidth;

    public MyBufferedImage() {
        bufferedImage = new BufferedImage(100, 200, BufferedImage.TYPE_INT_ARGB);
        initG2d(bufferedImage);
    }

    private void initG2d(BufferedImage img) {
        g2d = img.createGraphics();
        g2d.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
        g2d.setColor(Color.BLACK);
        charWidth = g2d.getFontMetrics().charWidth('+');
    }

    public void drawStringAndResize(String text, int x, int y) {
        if (y > bufferedImage.getHeight() || x + text.length() * charWidth > bufferedImage.getWidth()) {
            BufferedImage resized = new BufferedImage(bufferedImage.getWidth() * 2, bufferedImage.getHeight() * 2,
                    BufferedImage.TYPE_INT_ARGB);
            initG2d(resized);
            g2d.drawImage(bufferedImage, 0, 0, null);
            bufferedImage = resized;
            drawStringAndResize(text, x, y);
        }
        g2d.drawString(text, x, y);
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }
}
