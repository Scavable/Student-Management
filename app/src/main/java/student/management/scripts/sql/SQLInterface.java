package student.management.scripts.sql;

import java.sql.ResultSet;

@FunctionalInterface
public interface SQLInterface {
    ResultSet Query(String query);
}
