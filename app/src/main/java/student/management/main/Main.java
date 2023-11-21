package student.management.main;

import student.management.database.DatabaseSetup;
import student.management.gui.GUI;
import student.management.gui.LoginGUI;

public class Main {

    static LoginGUI loginGUI;

    public static void main(String[] args) {
        Setup.Setup();
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
