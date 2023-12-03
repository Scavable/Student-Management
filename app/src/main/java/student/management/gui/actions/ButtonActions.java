package student.management.gui.actions;

import com.mysql.cj.xdevapi.Statement;
import student.management.database.DatabaseConnection;

import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class ButtonActions {

    public static ActionListener enrollAction() {
        return e -> {

        };
    }

    public static ActionListener unenrollAction() {
        return e -> {

        };
    }

    public static ActionListener viewAction () {
        return e -> {
            try {
                DatabaseConnection.Query("select * from students;");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        };
    }
}