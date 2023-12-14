package student.management.scripts.sql.preset;

import student.management.scripts.sql.SQLInterface;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentsViewAll implements SQLInterface {
    static String query = "select * from students;";

    @Override
    public ResultSet Query(Connection con) throws SQLException {
        ResultSet rs = null;
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
