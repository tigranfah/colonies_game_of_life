package gui.layout;

import javax.swing.*;

public class MenuBar extends JMenuBar {

    public MenuBar(){
        prepareMenus();
    }

    private void prepareMenus(){
        JMenu[] menus = new JMenu[]{ new JMenu("Actions"), new JMenu("Game"), new JMenu("Help") };

        for(JMenu item : menus){
            add(item);
        }
    }
}
