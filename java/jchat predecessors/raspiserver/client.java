/**
 * Write a description of class client here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
//imports
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class client
{
    public static void main(String[] args) {
        if (args.length < 3) return;

        //gathering information
        String hostname = args[0];
        int port = Integer.parseInt(args[1]);
        String clientName = args[2];

        try (Socket socket = new Socket(hostname, port)) {
            //gathering input
            InputStream input = socket.getInputStream();
            BufferedReader readServer = new BufferedReader(new InputStreamReader(input));

            //sending output
            OutputStream output = socket.getOutputStream();
            PrintWriter writeServer = new PrintWriter(output, true);

            //accept greetings
            writeServer.println(clientName);
            System.out.println(readServer.readLine());

            //gathering user input
            Scanner scanner = new Scanner(System.in);
            String userInput = "";

            do {
                System.out.println("Input below: ");
                userInput = scanner.nextLine();

                writeServer.println(clientName + ">" + userInput);
            }
            while (!userInput.equals("exit"));
            scanner.close();
            System.out.println("If I dont see you again, good morning, good evening and good night!");
        }
        catch (UnknownHostException exception){
            System.out.println("Server not found: " + exception.getMessage()); 
        }
        catch (IOException exception) {
            System.out.println("IO error: " + exception.getMessage());
        }
    }
}
