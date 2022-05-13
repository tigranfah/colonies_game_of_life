package gui.elements;

import core.*;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {

    public static final Color[] defaultColors = new Color[]{
            Color.RED,
            Color.BLUE,
            Color.MAGENTA,
            Color.GREEN,
            new Color(123, 8, 1),
            new Color(3, 123, 63),
            new Color(53, 13, 90),
            new Color(255, 153, 204),
            new Color(204, 255, 153),
            new Color(153, 0, 204),
    };

    private Board board;

    public Canvas(Board board){
        this.board = board;
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponents(graphics);

        for (int i = 0; i < board.getHeight(); ++i) {
            for (int j = 0; j < board.getWidth(); ++j) {
                ColonyCell c = (board.getCellAt(new Position(j, i)));

                if(c.isAlive()) {
                    Color cellColor = defaultColors[c.getColonyIndex() - 1];

                    if(c.toString().equals(String.valueOf(c.getColonyIndex()))){
                        cellColor = new Color(246, 162, 29);
                    }

                    new Square(j, i, cellColor).render(graphics);
                }
                else
                    new Square(j, i).render(graphics);
            }
        }

        Square.setupSquareGrid(graphics, board.getWidth(), board.getHeight());
    }

    public void updateBoard(Board board){
        this.board = board;
        repaint();
    }
}
