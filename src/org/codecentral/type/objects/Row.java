package org.codecentral.type.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 * A {@link GameObject} that displays {@link Letter}s coming from right to left.
 */
public class Row extends GameObject {

    private static final int DEFAULT_HEIGHT = 128;

    private List<Letter> letters = new ArrayList<>();

    /**
     * Creates a new row with {@link #DEFAULT_HEIGHT}
     *
     * @see #Row(int, int, int, int)
     */
    public Row(int x, int y, int width) {
        this(x, y, width, DEFAULT_HEIGHT);
    }

    /**
     * Creates a new Row at the given position and with the given dimensions.
     */
    public Row(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    private void drawLetters(Graphics graphics) {
        for (Letter letter : letters) {
            letter.onDraw(graphics);
        }
    }

    @Override
    public void onUpdate() {
        if (letters.isEmpty()) {
            for (int i = 0; i < 10; i++) {
                letters.add(new Letter(200, 200, 100, 100));
            }
        }
        for (Letter letter : letters) {
            if (checkX(letter))
                letters.remove(letters.indexOf(letter));
        }

        letters.forEach(Letter::onUpdate);
    }

    public boolean checkX(Letter letter) {
        return (letter.getX() <= -letter.getWidth());
    }

    @Override
    public void onDraw(Graphics g) {
        // Draw this row
        g.setColor(Color.BLACK);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
        // Draw the letters
        drawLetters(g);
    }
}
