package gui.layout;

import core.Colony;

import javax.swing.*;
import java.util.ArrayList;
import java.util.function.Consumer;

public class MenuBar extends JMenuBar {

    private JMenuItem[] colonyInformation;
    private final ArrayList<Colony> colonies;

    public MenuBar(ArrayList<Colony> colonies){
        this.colonies = colonies;

        prepareColonyInformationMenus();
    }

    private void prepareColonyInformationMenus(){
        JMenuItem gameMenu = new JMenu("Game");

        colonyInformation = new JMenuItem[colonies.size()];
        for(int i = 0; i < colonies.size(); i++){
            colonyInformation[i] = new JMenuItem("Colony " + (i + 1));
            gameMenu.add(colonyInformation[i]);
        }

        add(gameMenu);
    }

    public void updateColonyCoins(){
        colonies.forEach(new Consumer<Colony>() {
            public void accept(Colony colony) {
                int i = colony.getColonyIndex() - 1;
                colonyInformation[i].setText(String.format("Colony %d has %.3f coins", colony.getColonyIndex(), colony.getCoins()));
            }
        });
    }
}
