package gui.elements;

import java.awt.*;
import java.util.ConcurrentModificationException;

class Square {

    public static final int SIZE = 10;

    private final int x, y;
    private final Color background;

    public Square(int x, int y, Color color){
        this.x = x;
        this.y = y;

        background = color;
    }

    public Square(int x, int y){
        this(x, y, Color.white);
    }

    public void render(Graphics graphics){
        graphics.setColor(background);
        graphics.fillRect(SIZE + (SIZE * x), SIZE + (SIZE * y), SIZE, SIZE);
    }

    public static void setupSquareGrid(Graphics graphics, int width, int height){
        graphics.setColor(Color.BLACK);

        for (int i=0; i <= width; i++) {
            graphics.drawLine((i * SIZE) + SIZE, SIZE, (i * SIZE) + SIZE, SIZE + (SIZE * height));
        }

        for (int i=0; i <= height; i++) {
            graphics.drawLine(SIZE,(i * SIZE) + SIZE, SIZE * (width + 1), (i * SIZE ) + SIZE);
        }
    }
}
