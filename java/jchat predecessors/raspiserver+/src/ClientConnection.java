package src;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
//import java.util.Scanner;

import javax.swing.JTextArea;

public class ClientConnection {
    public Socket socket;
    public ClientConnection(String host, int port, Socket socket, JTextArea history) {
        this.socket = socket;

        
        
        System.out.println("this bit worked i guess lol");
        System.out.println(host);
        System.out.println(port);
    }

    public static void send(int port, String host, String toSend, Socket socket) {
        try {
            // sending output
            OutputStream output = socket.getOutputStream();
            PrintWriter writeServer = new PrintWriter(output, true);

            writeServer.write(toSend);
            socket.close();
        } catch (UnknownHostException exception) {
            System.out.println("Server not found: " + exception.getMessage());
        } catch (IOException exception) {
            System.out.println("IO error: " + exception.getMessage());
        }
    }

    public String recieve(int port, String host, Socket socket) {
        try {
            // gathering input
            InputStream input = socket.getInputStream();
            BufferedReader readServer = new BufferedReader(new InputStreamReader(input));

            while (true) {
                return readServer.readLine();
                // return "hiiiii";
            }
        } catch (UnknownHostException exception) {
            System.out.println("Server not found: " + exception.getMessage());
        } catch (IOException exception) {
            System.out.println("IO error: " + exception.getMessage());
        }
        System.out.println("smt went wrong idling");
        return "";
    }

}
