package src;

//imports
import java.io.*;
import java.net.*;

public class serverMain {
    public static void main(String[] args) {
        if (args.length < 1) return;
        
        int port = Integer.parseInt(args[0]);
        System.out.print(port);

        try (ServerSocket serversocket = new ServerSocket(port)) {
            System.out.println("Good luck <3");

            while (true) {
                Socket socket = serversocket.accept();
                System.out.println("A wild Client has appeared!");
                new serverThread(socket).start();
            }

        } catch (IOException exception) {
            System.out.println("Server error: " + exception.getMessage());
            exception.printStackTrace();
        }

    }
}