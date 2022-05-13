package gui.layout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar {

    private JMenuItem[] colonyInformation;

    public MenuBar(){
        prepareMenus();
    }

    private void prepareMenus(){
        JMenu[] menus = new JMenu[]{ new JMenu("Actions"), new JMenu("Game"), new JMenu("Help") };

        for(JMenu item : menus){
            add(item);
        }
    }

    private void prepareActionsMenu(ActionListener clickListener){
        JMenu actions = new JMenu("Actions");

        JMenuItem start = new JMenuItem("Start"), stop = new JMenuItem("Stop");

        start.addActionListener(clickListener);
        stop.addActionListener(clickListener);

        actions.add(start);
        actions.add(stop);

        add(actions);
    }

    private void prepareColonyInformationMenus(int n){

    }
}
