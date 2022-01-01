//imports
import java.io.*;
import java.net.*;
import java.util.Date;

public class server {
    public static void main(String[] args) {
        //make sure all required variables are present
        if (args.length < 2) return;

        //starting variables
        int port = Integer.parseInt(args[0]);
        String serverName = args[1];
        final int maxClients = 50;
        int connectedClients = 0;
        String inputTxt = "";
        String[] print;

        //handle IOExcpetion caused by constructors, apparently
        try (ServerSocket serversocket = new ServerSocket(port)) {

            //print boot text
            System.out.println("Server \"" + serverName + "\" is active on port: \"" + port + "\"");
            
            //create file/dir objects
            File logDir = new File();
            File logFile = new File("./log-files/logFile.txt");

            //check if file/dir is already there, and if not create file/dir and print text.
            if (!logDir.exists()) {
                logDir.mkdir();   
                System.out.println("\"logDir\" created at: \"" + logDir.getAbsolutePath() + "\"");
            } 
            if (!logFile.exists()) {
                logFile.createNewFile();
                System.out.println("\"logFile\" created at: \"" + logFile.getAbsolutePath() + "\"");   
            }
            
            //create fileWriter to write to files (no duh)
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter("/home/pi/javaserver/log-files/logFile.txt", true));
            System.out.println("fileWriter has been initialised.");
            
            //section off log file
            fileWriter.write("[Server] Server has  been activated at/on: \"" + new Date().toString() + "\". Beginning logging.");

            //print final boot text
            System.out.println("There are: " + connectedClients + " clients currently connected to \"" + serverName + "\".");
            System.out.println("Good luck <3");
            
            //repeating loop & stopping excess clients from joining
            while (true) {
                if (connectedClients <= maxClients) {
                    //connect clients
                    Socket socket = serversocket.accept();
                    connectedClients++;
                    System.out.println("A wild Client has appeared!");
                    System.out.println("There are: " + connectedClients + " clients currently connected to \"" + serverName + "\".");

                    //gathering input
                    InputStream input = socket.getInputStream();
                    BufferedReader readClient = new BufferedReader(new InputStreamReader(input));

                    //sending output
                    OutputStream output = socket.getOutputStream();
                    PrintWriter writeClient = new PrintWriter(output, true);

                    //greeting client
                    writeClient.println("Hello, client \"" + readClient.readLine() + "\"! You are connected to: \"" + serverName + "\" on port \"" + port + "\".");

                    while (true) {
                        inputTxt = readClient.readLine();
                        if (inputTxt != null) {
                            if (inputTxt.split(">", 2)[1].equals("exit")) {
                                //handle client exit 
                                connectedClients--;
                                System.out.println("There are: \"" + connectedClients + "\" clients currently connected to \"" + serverName + "\".");
                                socket.close();
                                break;
                            } else {
                                //sorting, printing  and logging text
                                print = inputTxt.split(">", 2);   
                                fileWriter.newLine();
                                
                                fileWriter.write("[Client] [" + new Date().toString() + "] " + print[0] + ": " + print [1]);   
                                System.out.println(print[0] + ": " + print [1]);
                            }
                        }
                    }
                    //cleanup
                    fileWriter.newLine();
                    fileWriter.write("[Server] If I dont see you again, good morning, good evening and good night!");
                    fileWriter.newLine();
                    fileWriter.close();
                }
            }
        }

        catch(IOException exception)  {
            System.out.println("Server error: " + exception.getMessage());
            exception.printStackTrace();
        }
    }
}