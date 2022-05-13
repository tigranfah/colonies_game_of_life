package gui;

import core.*;
import gui.elements.Canvas;
import gui.elements.Square;
import gui.layout.MenuBar;
import gui.layout.Window;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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

        ArrayList<Colony> colonies = boardManager.getSetting().getColonies();
        bar = new MenuBar(colonies);
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
        JLabel l = new JLabel("Game Over! Winner is Colony number " + winner);
        p.add(l);

        w.add(p);

        w.makeVisible();
    }

    private void loop(){
        while (isRunning) {
            int winner = boardManager.isGameOver();
            if(winner != -1){
                stop();
                showGameOverWindow(winner);
            }

            updateBoard(boardManager.getBoard());
            bar.updateColonyCoins();
            boardManager.step();

            try {
                Thread.sleep(1000 / (GameSetting.GENERATIONS_PER_SECOND));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
