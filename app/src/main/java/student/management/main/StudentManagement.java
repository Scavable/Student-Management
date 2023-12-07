package student.management.main;

import student.management.database.DatabaseSetup;
import student.management.database.User;
import student.management.gui.StudentManagementWindow;
import student.management.gui.LoginWindow;

import java.io.IOException;
import java.util.Properties;

public class StudentManagement {

    static LoginWindow loginWindow;

    public static void main(String[] args) {
        Properties properties = new Properties();

        try {
            properties.load(StudentManagement.class.getClassLoader().getResourceAsStream("Info.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Setup.Setup();
        new DatabaseSetup(properties);
        loginWindow = new LoginWindow();

    }
    public static void Validation(){

        if(LoginWindow.valid) {
            loginWindow.getFrame().dispose();
            new StudentManagementWindow();
        }
    }
}
