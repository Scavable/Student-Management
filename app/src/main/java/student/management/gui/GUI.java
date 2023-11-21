package student.management.gui;

import student.management.database.DatabaseSetup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUI {
    JFrame frame = new JFrame("Student Management System");
    JPanel panelSelect = new JPanel();
    JPanel panelInfo = new JPanel();
    JScrollPane scrollPaneSelect = new JScrollPane();
    JScrollPane scrollPaneInfo = new JScrollPane();

    public GUI(){
        scrollPaneBehavior();
        scrollPaneInfoBehavior();
        scrollPaneSelectBehavior();
        panelSelectBehavior();
        panelInfoBehavior();
        frameBehavior();
    }

    private void scrollPaneSelectBehavior() {

    }

    private void scrollPaneInfoBehavior() {

    }

    private void scrollPaneBehavior() {
        scrollPaneSelect.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneInfo.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneSelect.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPaneInfo.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    }

    private void frameBehavior() {
        frame.addWindowStateListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                DatabaseSetup.StopService();
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(1,2));
        frame.add(panelSelect);
        frame.add(panelInfo);

        frame.pack();

        frame.setVisible(true);
    }

    private void panelInfoBehavior() {

    }

    private void panelSelectBehavior() {
        panelSelect.setLayout(new GridLayout(0,1));
        panelSelect.add(new JButton("Enrool"));
        panelSelect.add(new JButton("Un-Enroll"));

    }
}
