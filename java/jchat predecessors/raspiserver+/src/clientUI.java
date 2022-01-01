package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class clientUI extends tabBuilder {
    
    public clientUI(String host, int port) {
        super(host, port);
    }

    public static void main(String[] args) {
        // creating window
        JFrame frame = new JFrame("javA chaTrooM");
        ImageIcon imageIcon = new ImageIcon("java_chatroom_icon.png");
        frame.setIconImage(imageIcon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 500);

        // creating top row
        JMenuBar menus = new JMenuBar();
        JMenu sessions = new JMenu("sessions");
        JMenu config = new JMenu("config");
        JMenuItem newSession = new JMenuItem("New Session");
        JMenuItem loadSession = new JMenuItem("Load Session");
        JMenuItem closeSession = new JMenuItem("Close session");
        JMenuItem saveSession = new JMenuItem("Save Session");
        JMenuItem boolLog = new JMenuItem("Toggle logging");
        JMenuItem help = new JMenuItem("Help");
        menus.add(sessions);
        menus.add(config);
        sessions.add(newSession);
        sessions.add(loadSession);
        sessions.add(saveSession);
        sessions.add(closeSession);
        config.add(boolLog);
        config.add(help);
        JTabbedPane tabbedPane = new JTabbedPane();
        // tabs (temp)
        

        // event listener for New Session button
        newSession.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                getConnectionDetails(frame);
            }
        });

        // event listener for Close Session button
        closeSession.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                tabbedPane.remove(tabbedPane.getSelectedIndex());
            }
        });
        
        //finalising
        frame.add(BorderLayout.NORTH, menus);
        frame.add(tabbedPane);        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        getConnectionDetails(frame);
    }

    public static void addTab(JPanel toAdd, JTabbedPane tabbedPane) {
        tabbedPane.addTab("title", toAdd);
    }

}
