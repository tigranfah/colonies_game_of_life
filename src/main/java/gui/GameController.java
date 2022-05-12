package gui;

import core.Board;
import core.BoardManager;
import core.Position;
import gui.elements.Canvas;
import gui.layout.MenuBar;
import gui.layout.Window;

import javax.swing.*;

public class GameController {
    private final Window window;
    private final Canvas canvas;
    private final MenuBar bar;

    public static void main(String[] args) {
        BoardManager boardManager = new BoardManager(30, 30);

        for (int i = 0; i < boardManager.getBoard().getHeight(); ++i) {
            for (int j = 0; j < boardManager.getBoard().getWidth(); ++j) {
                if (Math.random() > 0.4)
                    boardManager.setAlive(new Position(j, i));
            }
        }

        GameController g = new GameController(boardManager.getBoard());

        while(true){
            boardManager.step();
            g.updateBoard(boardManager.getBoard());

            try {
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public GameController(Board board){
        window = new Window();
        bar = new MenuBar();
        canvas = new Canvas(board);

        prepareComponents();
    }

    private void prepareComponents(){
        window.attachMenuBar(bar);
        window.attachCanvas(canvas);

        window.makeVisible();
    }

    public void updateBoard(Board board){
        canvas.updateBoard(board);
    }
}
