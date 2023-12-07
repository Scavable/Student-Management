package student.management.database;

import java.sql.Connection;

public class User {
    static String user;
    static Connection con;

    public static void setUser(String user) {
        User.user = user;
    }

    public static void setCon(Connection con) {
        User.con = con;
    }

    public static String getUser() {
        return user;
    }

    public static Connection getCon() {
        return con;
    }
}
