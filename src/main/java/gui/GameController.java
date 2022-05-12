package gui;

import core.*;
import gui.elements.Canvas;
import gui.layout.MenuBar;
import gui.layout.Window;
import utils.Matrix;
import utils.Pattern;

import javax.swing.*;

public class GameController {
    private final Window window;
    private final Canvas canvas;
    private final MenuBar bar;

    public static void main(String[] args) {
        GameSetting setting = new GameSetting(BoardManager.GameType.COLONIES);
        setting.setKingPositions(
                new Position[] {
                        new Position(10, 10),
                        new Position(30, 15)
                }
        );
        BoardManager boardManager = new BoardManager(setting,80, 80);


        Pattern pat = boardManager.getSetting().getColony(1).getStrategy().generatePattern();
        Matrix<Worker> mat = pat.toWorkerMatrix(1);

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
