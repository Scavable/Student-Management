package student.management.scripts.sql.preset;

import student.management.database.DatabaseConnection;
import student.management.database.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentsViewAll {
    static String query = "select * from students;";
    public static ResultSet StudentsViewAll() throws SQLException {
        ResultSet rs = null;
        Connection con = User.getCon();
        if(con.isValid(5)){
            System.out.println("User Connection Is Alive");
            rs = con.createStatement().executeQuery(query);
            while(rs.next()){
                for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++)
                    System.out.println(rs.getString(i));
            }
        }
        return rs;
    }
}
