package gui.layout;

import core.Colony;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.function.Consumer;

public class MenuBar extends JMenuBar {

    private JMenuItem[] colonyInformation;
    private ArrayList<Colony> colonies;

    public MenuBar(ArrayList<Colony> colonies){
        this.colonies = colonies;
        prepareMenus();

        prepareColonyInformationMenus();
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

    private void prepareColonyInformationMenus(){
        colonyInformation = new JMenuItem[colonies.size()];
        for(int i = 0; i < colonies.size(); i++){
            colonyInformation[i] = new JMenuItem("Colony " + (i + 1));
        }
    }

    public void updateColonyCoins(){
        colonies.forEach(new Consumer<Colony>() {
            public void accept(Colony colony) {
                int i = colony.getColonyIndex() - 1;
                colonyInformation[i].setText("Colony " + i +" has " + colony.getCoins() + " points.");
            }
        });
    }
}
