package student.management.database;
//Classes

import student.management.gui.LoginWindow;
import student.management.main.StudentManagement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    public static Connection con;
    static Properties properties = new Properties();

    public DatabaseConnection(String user, String password) throws SQLException {

        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream("Info.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            con = DriverManager.getConnection(properties.getProperty("databaseStudentManagement"), user, password);

            if(con.isValid(5)){
                LoginWindow.valid = true;
                User.setUser(user);
                User.setCon(con);
            }

            StudentManagement.Validation();

        } catch (SQLException e) {
            e.printStackTrace();

            String[] temp = e.getMessage().split("@");
            System.out.println(temp[0]);
        }
    }
}
