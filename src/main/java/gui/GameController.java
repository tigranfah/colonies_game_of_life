package gui;

import core.*;
import gui.elements.Canvas;
import gui.elements.Square;
import gui.layout.MenuBar;
import gui.layout.Window;

import javax.swing.*;
import java.awt.*;

public class GameController {

    private final BoardManager boardManager;
    private final Window window;
    private final Canvas canvas;
    private final MenuBar bar;

    private boolean isRunning = false;

    public GameController(BoardManager boardManager){
        this.boardManager = boardManager;

        window = new Window();

        window.setSize(new Dimension(boardManager.getBoard().getWidth() * Square.SIZE, boardManager.getBoard().getWidth() * Square.SIZE));

        bar = new MenuBar();
        canvas = new Canvas(boardManager.getBoard());

        prepareComponents();
    }

    public boolean isRunning(){
        return isRunning;
    }

    public void start(){
        isRunning = true;
    }

    public void stop(){
        isRunning = false;
    }

    public void run(){
        start();

        loop();
    }

    private void prepareComponents(){
        window.attachMenuBar(bar);
        window.attachCanvas(canvas);

        window.makeVisible();
    }

    private void updateBoard(Board board){
        canvas.updateBoard(board);
    }

    private void showGameOverWindow(int winner){
        Window w = new Window();
        w.setSize(200, 200);

        JPanel p = new JPanel();
        p.add(new JLabel("Game Over! Winner is Colony number " + winner));

        w.makeVisible();
    }

    private void loop(){
        while (isRunning) {
            for (Colony colony : boardManager.getSetting().getColonies()) {
                System.out.printf("%f ", colony.getCoins());
            }

            int winner = boardManager.isGameOver();
            if(winner != -1){
                stop();
                showGameOverWindow(winner);
            }

            updateBoard(boardManager.getBoard());
            boardManager.step();

            try {
                int generationsPerSecond = 10;
                Thread.sleep(1000 / generationsPerSecond);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
