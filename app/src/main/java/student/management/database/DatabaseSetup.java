package student.management.database;

import javax.swing.*;
import java.io.*;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DatabaseSetup {
    private static final String serviceName = "MySQL80";
    static String workingDirectory = System.getProperty("user.dir");

    Connection con;

    public DatabaseSetup(Properties properties) {
        StartService();

        try {
            con = DriverManager.getConnection(properties.getProperty("databaseStudentManagement"), properties.getProperty("userRoot"), properties.getProperty("userRootPassword"));
            if(con != null)
                System.out.println("Connect successful to schema");
            CreatePresetTables();
        } catch (SQLException e) {
            try {
                con = DriverManager.getConnection(properties.getProperty("databaseRoot"), properties.getProperty("userRoot"), properties.getProperty("userRootPassword"));
                int status = con.createStatement().executeUpdate("Create Schema student_management");
                if(status == 1)
                    System.out.println("Connection successful to database: created schema");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }finally {
            try {
                if(con != null && con.isValid(0)) {
                    con.close();
                    System.out.println("Database setup connection closed");
                }
            } catch (SQLException e) {
                System.exit(1);
            }
        }
    }

    private void CreatePresetTables() {
        List<String> tables = getTables();

        try {
            ResultSet rs = con.createStatement().executeQuery("show tables;");
            if(!rs.next()){
                for(String table: tables) {
                    con.createStatement().executeUpdate(table);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static List<String> getTables() {
        String createStudentsTable = """
                Create Table students (
                    id int auto_increment,
                    first_name varchar(25) not null,
                    last_name varchar(25) not null,
                    major varchar(50) not null,
                    primary key (id)
                );
                """;
        String createCoursesTable = """
                Create Table courses (
                    id int auto_increment,
                    title varchar(50) not null,
                    level int not null,
                    capacity int not null,
                    professor varchar(25) not null,
                    teacher_assistant varchar(25),
                    primary key (id)
                );
                """;
        String createEnrolledTable = """
                Create Table enrolled (
                    id int auto_increment,
                    student_id int,
                    course_id int,
                    primary key (id),
                    foreign key (student_id) references students(id),
                    foreign key (course_id) references courses(id)
                );
                """;
        String createGradesTable = """
                
                """;
        String createCreditsTable = """
                
                """;

        List<String> list = new LinkedList<>();
        list.add(createStudentsTable);
        list.add(createCoursesTable);
        list.add(createEnrolledTable);
        list.add(createGradesTable);
        list.add(createCreditsTable);
        return list;
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
