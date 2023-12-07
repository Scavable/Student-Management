package student.management.gui;

import student.management.database.DatabaseSetup;
import student.management.gui.actions.ButtonActions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StudentManagementWindow {
    JFrame frame = new JFrame("Student Management System");

    JPanel panelInfo = new JPanel();
    JScrollPane scrollPaneSelect = new JScrollPane();
    JScrollPane scrollPaneInfo = new JScrollPane();

    public StudentManagementWindow(){
        JPanel panelSelect = new JPanel();

        scrollPaneBehavior();
        scrollPaneInfoBehavior();
        scrollPaneSelectBehavior();
        panelSelectBehavior(panelSelect);
        panelInfoBehavior();
        frameBehavior(panelSelect);
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

    private void frameBehavior(JPanel panelSelect) {
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

    private void panelSelectBehavior(JPanel panelSelect) {
        panelSelect.setLayout(new GridLayout(0,1));

        JButton enroll = new JButton("Enroll");
        JButton unenroll = new JButton("Un-enroll");
        JButton view = new JButton("View");

        enroll.addActionListener(ButtonActions.enrollAction());
        unenroll.addActionListener(ButtonActions.unenrollAction());
        view.addActionListener(ButtonActions.viewAction());

        panelSelect.add(enroll);
        panelSelect.add(unenroll);
        panelSelect.add(view);


    }
}
