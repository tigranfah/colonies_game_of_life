package gui.layout;

import gui.elements.Canvas;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private static final Dimension DEFAULT_WINDOW_SIZE = new Dimension(800, 800);
    private static final Dimension MINIMUM_WINDOW_SIZE = new Dimension(400, 400);

    public Window(){
        prepare();
        centerOnScreen();
    }

    private void prepare(){
        setTitle("Group Project - Almost Game of Life");

        setMinimumSize(MINIMUM_WINDOW_SIZE);
        setSize(DEFAULT_WINDOW_SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    private void centerOnScreen(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setLocation((screenSize.width - getWidth())/2, (screenSize.height - getHeight())/2);
    }

    public void makeVisible(){
        setVisible(true);
    }

    public void attachMenuBar(JMenuBar menuBar){
        setJMenuBar(menuBar);
    }

    public void attachCanvas(Canvas canvas){
        add(canvas);
    }
}
