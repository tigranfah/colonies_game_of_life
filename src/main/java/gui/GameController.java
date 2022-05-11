package gui;

import core.Board;
import core.BoardManager;
import gui.elements.Canvas;
import gui.layout.MenuBar;
import gui.layout.Window;

import javax.swing.*;

public class GameController {
    private final Window window;
    private final Canvas canvas;
    private final MenuBar bar;

    public static void main(String[] args){

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
