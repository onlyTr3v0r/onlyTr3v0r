package src;

//imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class tabBuilder extends ClientConnection implements ActionListener, KeyListener {

    public tabBuilder(String host, int port) {
        super(host, port, null, null);
    }

    public static JPanel buildTab(JFrame frame, int port, String host) {
        // designating message history area
        JTextArea history = new JTextArea("debug" + System.lineSeparator());
        JScrollPane scrollPane = new JScrollPane(history);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        history.setEditable(false);

        // creating bottom row
        JPanel inputPanel = new JPanel();
        JTextField inputField = new JTextField(50);
        JLabel enterText = new JLabel("Enter text");
        JButton send = new JButton("Send message");
        JButton clear = new JButton("Clear");

        // event listener for clear button
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                inputField.setText("");
                System.out.println("clear button pressed.");
            }
        });

        // event listener for send button
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String userInput = inputField.getText();
                history.append(userInput + System.lineSeparator());
                inputField.setText("");
                System.out.println("Send button pressed.");
            }
        });

        // enter key listener
        inputField.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent event) {
                // checking if keypress was enter
                if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                    // handling enter key press
                    String userInput = inputField.getText();
                    history.append(userInput + System.lineSeparator());
                    inputField.setText("");
                    System.out.println("enter key pressed");
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });



        // adding components to bottom panel
        inputPanel.add(enterText);
        inputPanel.add(inputField);
        inputPanel.add(send);
        inputPanel.add(clear);

        // setting panel layout & returning it
        JPanel result = new JPanel(new BorderLayout());
        result.setSize(500, 200);
        result.add(inputPanel, BorderLayout.SOUTH);
        result.add(scrollPane, BorderLayout.CENTER);
        return result;
    }

    // method called whenver new session button is pressed
    public static void getConnectionDetails(JFrame frame) {
        // printing text & ceating panel/ popup
        System.out.println("new session button presssed");
        JPanel dialogPanel = new JPanel();
        JDialog popup = new JDialog(frame, "Input connection.");
        popup.setSize(1000, 200);
        dialogPanel.setSize(300, 100);

        // creating text fields & buttons and adding them
        JTextField inputHostName = new JTextField(20);
        JTextField inputPortNumber = new JTextField(20);
        JLabel hostLabel = new JLabel("input hostname");
        JLabel portLabel = new JLabel("input portname");
        JButton confirm = new JButton("confirm");
        dialogPanel.add(hostLabel);
        hostLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dialogPanel.add(inputHostName);
        dialogPanel.add(portLabel);
        portLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dialogPanel.add(inputPortNumber);
        dialogPanel.add(confirm);
        confirm.setAlignmentX(Component.CENTER_ALIGNMENT);

        // setting layout to boxLayout layout manager
        BoxLayout boxlayout = new BoxLayout(dialogPanel, BoxLayout.Y_AXIS);
        dialogPanel.setLayout(boxlayout);
        popup.add(dialogPanel);

        // finalising and setting visibilty of popup
        popup.pack();
        popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        popup.setLocationRelativeTo(null);
        popup.setVisible(true);

        // event listener for confirm button
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                // gathering the inut when button is pressed
                String hostName = inputHostName.getText();
                int portNumber = 0;
                // checking if the port is actually an integer
                try {
                    portNumber = Integer.parseInt(inputPortNumber.getText());
                } catch (NumberFormatException exception) {
                    // creating second popup to tell user something is invalid
                    invalidAns(frame);
                }

                if (hostName.isEmpty() || Integer.toString(portNumber).isEmpty()) {
                    // creating second popup to tell user something is invalid
                    invalidAns(frame);
                }

                // resetting text input boxes and creating a connection (WiP)
                popup.dispose();
                inputHostName.setText("");
                inputPortNumber.setText("");
                System.out.println("confirm button pressed.");

                
            }
        });
    }

    public static void createConnection(String host, int port, String toSend, JTextArea history) {
        try {
            Socket socket = new Socket(host, port);
            ClientConnection connection = new ClientConnection(host, port, socket, history);

            send(port, host, toSend, connection.socket);

            socket.close();
        } catch (UnknownHostException exception) {
            System.out.println("Server not found: " + exception.getMessage());
        } catch (IOException exception) {
            System.out.println("IO error: " + exception.getMessage());
        }
    }

    public static void recieveIncoming(ClientConnection connection, int port, String host, JTextArea history) {
        while (true) {
            //Message msg = new Message(connection.recieve(port, host));
            //appendMessage(history, msg.messageContents);
        }
    }

    public static void appendMessage(JTextArea history, String toAppend) {
        history.append(toAppend + System.lineSeparator());
    }

    // method to ask user for another answer if they entered an invalid answer
    public static void invalidAns(JFrame frame) {
        // printing text and creating dialog box/ label
        System.out.println("user misentered something");
        JDialog notInt = new JDialog(frame, "Invalid answers.");
        JLabel warningText = new JLabel(
                "One or more answer is invalid.Pleas emake an integere is in the port field and neither field is empty.");

        // adding label and finalising
        notInt.add(warningText);
        warningText.setAlignmentX(Component.CENTER_ALIGNMENT);
        notInt.setLocationRelativeTo(null);
        notInt.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        notInt.setAlwaysOnTop(true);
        notInt.setSize(400, 75);
        notInt.setVisible(true);
    }

    // dk really what these do /shrug
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
