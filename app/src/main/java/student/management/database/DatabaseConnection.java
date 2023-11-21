package student.management.database;
//Classes
import student.management.main.Main;
import student.management.gui.LoginGUI;

//Libraries
import java.sql.*;

public class DatabaseConnection {
    public static Connection con;
    public DatabaseConnection(String user, String password) throws SQLException {
        String url = "jdbc:mysql://localhost/";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            LoginGUI.valid = true;
            Main.Validation();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

            String[] temp = e.getMessage().split("@");
            System.out.println(temp[0]);
            temp = null;
            System.gc();
        }
    }
}
