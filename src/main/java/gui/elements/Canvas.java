package gui.elements;

import core.*;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {

    private static final Color[] defaultColors = new Color[]{ Color.RED, Color.BLUE, Color.MAGENTA };

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
                        cellColor = Color.GREEN;
                    }

                    new Square(i, j, cellColor).render(graphics);
                }
                else
                    new Square(i, j).render(graphics);
            }
        }

        Square.setupSquareGrid(graphics, 80, 80);
    }

    public void updateBoard(Board board){
        this.board = board;
        repaint();
    }
}
