package src;

//imports
import java.io.*;
import java.net.*;

public class serverThread extends Thread {
    public Socket socket;

    public serverThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            System.out.println("thread started running succesfully :DDDDDDDDDDDD");
            System.out.print("Thread \"" + Thread.currentThread().getId() + "\" is currently active");
            
            // gathering input
            InputStream input = socket.getInputStream();
            BufferedReader readClient = new BufferedReader(new InputStreamReader(input));

            // sending output
            OutputStream output = socket.getOutputStream();
            PrintWriter writeClient = new PrintWriter(output, true);

            writeClient.println("Hello, client!");

            for (int i = 0; i > 10; i++) {
                writeClient.println("test no. " + i);
            }
        } catch (IOException exception) {
            System.out.println("server exception");
        }
    }
}
