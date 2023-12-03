package student.management.database;

import javax.swing.*;
import java.io.*;
import java.sql.*;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DatabaseSetup {
    private static String serviceName = "MySQL80";
    static String workingDirectory = System.getProperty("user.dir");

    Connection con;

    public DatabaseSetup() {
        StartService();

        Properties properties = new Properties();
        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream("Info.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            con = DriverManager.getConnection(properties.getProperty("databaseStudentManagement"), properties.getProperty("userRoot"), properties.getProperty("userRootPassword"));
            if(con != null)
                System.out.println("Connect successful to schema");
            CreateTables();
        } catch (SQLException e) {
            try {
                con = DriverManager.getConnection(properties.getProperty("databaseRoot"), properties.getProperty("userRoot"), properties.getProperty("userRootPassword"));
                Statement stat = con.createStatement();
                int status = stat.executeUpdate("Create Schema student_management");
                System.out.println(status);
                System.out.println("Connection successful to database: created schema");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }finally {
            try {
                if(con.isValid(0)) {
                    con.close();
                    System.out.println("Database setup connection closed");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private void CreateTables() {
        System.out.println("Create Tables Method");
        String studentsTable = """
                Create Table students (
                    id int primary key auto_increment,
                    first_name varchar(25) not null,
                    last_name varchar(25) not null
                );
                """;
        try {
            ResultSet rs = con.createStatement().executeQuery("show tables;");
            if(!rs.next()){
                con.createStatement().executeUpdate(studentsTable);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
