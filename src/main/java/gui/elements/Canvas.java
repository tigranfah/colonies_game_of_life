package gui.elements;

import core.Board;
import core.Cell;
import core.Position;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {

    private Board board;

    public Canvas(Board board){
        this.board = board;
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponents(graphics);

        for (int i = 0; i < board.getHeight(); ++i) {
            for (int j = 0; j < board.getWidth(); ++j) {
                Cell c = (board.getCellAt(new Position(j, i)));

                if(c.isAlive())
                    new Square(i, j, new Color(255, 0 ,0)).render(graphics);
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
