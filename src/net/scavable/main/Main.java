package net.scavable.main;

import net.scavable.database.DatabaseSetup;
import net.scavable.gui.GUI;
import net.scavable.gui.LoginGUI;

public class Main {

    static LoginGUI loginGUI;

    public static void main(String[] args) {
        new Setup();
        new DatabaseSetup();
        loginGUI = new LoginGUI();

    }
    public static void Validation(){

        if(LoginGUI.valid) {
            loginGUI.getFrame().dispose();
            new GUI();
        }
    }
}
