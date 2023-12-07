package student.management.main;

import student.management.database.DatabaseSetup;
import student.management.gui.GUI;
import student.management.gui.LoginGUI;

import java.io.IOException;
import java.util.Properties;

public class StudentManagement {

    static LoginGUI loginGUI;

    public static void main(String[] args) {
        Properties properties = new Properties();

        try {
            properties.load(StudentManagement.class.getClassLoader().getResourceAsStream("Info.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Setup.Setup();
        new DatabaseSetup(properties);
        loginGUI = new LoginGUI();

    }
    public static void Validation(){

        if(LoginGUI.valid) {
            loginGUI.getFrame().dispose();
            new GUI();
        }
    }
}
