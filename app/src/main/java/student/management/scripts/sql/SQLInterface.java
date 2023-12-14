package student.management.scripts.sql;

import student.management.database.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface SQLInterface {
    ResultSet Query(Connection con) throws SQLException;
}
