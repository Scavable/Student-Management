package net.scavable.database;

import javax.swing.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

public class DatabaseSetup {
    private static String serviceName = "MySQL80";
    static String workingDirectory = System.getProperty("user.dir");

    Connection con;

    public DatabaseSetup() {
        StartService();

        String url1 = "jdbc:mysql://localhost/students";
        String url2 = "jdbc:mysql://localhost/";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url1, "root", "");
            System.out.println("Connect successful to schema");
        } catch (ClassNotFoundException | SQLException e) {
            try {
                con = DriverManager.getConnection(url2, "root", "");
                Statement stat = con.createStatement();
                int status = stat.executeUpdate("Create Schema test_schema");
                System.out.println("Connection successful to database: created schema");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }finally {
            try {
                if(con.isValid(0)) {
                    con.close();
                    System.out.println("Connection closed");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private static void StartService() {
        try {
            InputStream input = Runtime.getRuntime().exec("cmd.exe /c sc query "+serviceName).getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(input));
            String line;
            while((line = br.readLine()) != null){
                if(line.contains("STATE") && !line.contains("RUNNING")){
                    int status = JOptionPane.showConfirmDialog(null, "MySQL Server is curently not running!\nWould you like to start the MySQL Server?", "MySQL Server",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                    if(status == 0) {
                        String[] script = {"cmd.exe", "/c", workingDirectory+"\\StartService.bat"};
                        Process proc = Runtime.getRuntime().exec(script);
                        TimeUnit.SECONDS.sleep(5);
                        break;
                    }else{
                        JOptionPane.showMessageDialog(null, "MySQL Server was not started\nStudent Management System is now quitting", "MySQL Server",JOptionPane.ERROR_MESSAGE);
                        System.exit(1);
                    }
                }
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void StopService(){
        try {
            InputStream input = Runtime.getRuntime().exec("cmd.exe /c sc query "+serviceName).getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(input));
            String line;
            while((line = br.readLine()) != null){
                if(line.contains("STATE") && line.contains("RUNNING")){
                    int status = JOptionPane.showConfirmDialog(null, "MySQL Server is curently running!\nWould you like to stop the MySQL Server?", "MySQL Server",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                    if(status == 0) {
                        String[] script = {"cmd.exe", "/c", workingDirectory+"\\StopService.bat"};
                        Process proc = Runtime.getRuntime().exec(script);
                        TimeUnit.SECONDS.sleep(5);
                        break;
                    }
                }
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
