package student.management.gui.actions;

import student.management.scripts.sql.preset.StudentsViewAll;

import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                ResultSet rs = StudentsViewAll.StudentsViewAll();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        };
    }
}