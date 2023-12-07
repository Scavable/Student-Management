package student.management.gui;

import student.management.database.DatabaseConnection;
import student.management.database.DatabaseSetup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class LoginWindow {

    private final JFrame frame = new JFrame("Login");
    private final JLabel labelUsername = new JLabel("Username:");
    private final JLabel labelPassword = new JLabel("Password:");
    private final JButton buttonSubmit = new JButton("Submit");
    private final JTextField tfUsername = new JTextField();
    private final JTextField tfPassword = new JTextField();
    public static boolean valid = false;

    public LoginWindow(){

        loginGUIListeners();
        frameBehavior();

    }

    private void loginGUIListeners() {

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.out.println("Frame closing");
                DatabaseSetup.StopService();
            }
        });

        buttonSubmit.addActionListener(e -> {
            try {
                new DatabaseConnection(tfUsername.getText(), tfPassword.getText());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        tfUsername.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    try {
                        new DatabaseConnection(tfUsername.getText(), tfPassword.getText());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });

        tfPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    try {
                        new DatabaseConnection(tfUsername.getText(), tfPassword.getText());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });

    }

    private void frameBehavior() {

        frame.getContentPane().setLayout(new GridLayout(0,2));
        frame.setPreferredSize(new Dimension(300,200));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.getContentPane().add(labelUsername);
        frame.getContentPane().add(tfUsername);
        frame.getContentPane().add(labelPassword);
        frame.getContentPane().add(tfPassword);
        frame.getContentPane().add(buttonSubmit);

        frame.pack();
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }
}
