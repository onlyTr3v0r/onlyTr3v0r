//package io.github.onlytr3v0r.archiver;

//imports
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.message.*;

import java.util.stream.Stream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Discord {
    public static void main(String[] args) {
        System.out.println(System.lineSeparator());

        // check if user is on windows
        boolean isWindows = (System.getProperty("os.name").toLowerCase().startsWith("windows"));

        String logsDirPath = (isWindows) ? ".\\logs" : "./logs";
        String dcLogsDirPath = (isWindows) ? ".\\logs\\dc-logs" : "./logs/dc-logs";
        String dcLogPath = (isWindows) ? ".\\logs\\dc-logs\\log-" + System.currentTimeMillis() + ".txt"
                : "./logs/dc-logs/log-" + System.currentTimeMillis() + ".txt";

        File logsDir = new File(logsDirPath);
        File dcLogsDir = new File(dcLogsDirPath);
        File dcLog = new File(dcLogPath);

        try {
            if (logsDir.mkdir()) { // create and print info about log directory
                System.out.println("Directory " + logsDir.getName() + " created at " + logsDir.getAbsolutePath());
            }
            if (dcLogsDir.mkdir()) { // create and print info about discord log directory
                System.out.println("Directory " + dcLogsDir.getName() + " created at " + dcLogsDir.getAbsolutePath());
            }
            if (dcLog.createNewFile()) { // create and print info about discord log file
                System.out.println("File " + dcLog.getName() + " created at " + dcLog.getAbsolutePath());
            }
        } catch (IOException exception) { // catch IOException
            System.out.println("error occured while creating files and directories :(");
            exception.printStackTrace();
        }

        System.out.println(System.lineSeparator());

        //super secret token! 
        //#region
            DiscordApi api = new DiscordApiBuilder().setToken("ODU2MjM4ODk1OTQ3NTc5Mzky.YM-InA.qfYnmRIlbnepoRzVfbjrjqVO_lg")
                    .login().join(); // logging the bot in
            //#endregion

        // getting link and changing permissions (changing last char of the link)
        String link = api.createBotInvite();
        char[] editPerms = link.toCharArray();
        editPerms[editPerms.length - 1] = '8'; // 8 is permission level- can be calculated at DCDP
        link = String.valueOf(editPerms);

        System.out.println(System.lineSeparator() + "you can invite my archiver bot with the link: " + link + System.lineSeparator()); // printing out link

        // commands
        api.addMessageCreateListener(event -> { // listener for message being sent (or created) and checking message is
                                                // not sent by the bot
            if (!event.getMessageAuthor().isYourself()) {

                String msg = event.getMessageContent();

                if (msg.charAt(0) == '>') { // checking if message is a command or not, and splitting it

                    String cmd = msg.substring(1);
                    String[] cmdArgs = cmd.split(" ");

                    try {
                        if (cmdArgs[0].equals("archive")) { // if command == archive
                            try {
                                FileWriter writer = new FileWriter(dcLog);
                                if (cmdArgs[1].equals("all")) { // if user wants to archive all

                                    Stream<Message> msgs = event.getChannel().getMessagesAsStream();

                                    for (Message msgIterator : (Iterable<Message>) msgs::iterator) {
                                        archive(msgIterator, writer);
                                    }

                                } else if (cmdArgs[1].equals("before")) { // if user wants to archive only after a date

                                    Stream<Message> msgs = event.getChannel().getMessagesAsStream();

                                    // funky code to loop through the stream of messages
                                    for (Message msgIterator : (Iterable<Message>) msgs::iterator) {
                                        if (!event.getMessage().getCreationTimestamp().toString().split("T")[0]
                                                .equals(cmdArgs[2])) {

                                            archive(msgIterator, writer);

                                        }
                                    }

                                } else if (cmdArgs[1].equals("after")) {

                                }
                                writer.close();
                            } catch (IOException exception) {
                                System.out.println("Error occured");
                                exception.printStackTrace();
                            }
                        } else if (cmdArgs[0].equals("credits")) {
                            event.getChannel().sendMessage("made by ^-^ Trevor");
                        } else if (cmdArgs[0].equals("help")) {
                            event.getChannel().sendMessage("help is on the way!");
                        } else if (cmdArgs[0].equals("exit")) {
                            event.getChannel().sendMessage("cya <3"); // cleanup
                            System.exit(0);
                        }
                    } catch (NumberFormatException exception) {
                        event.getChannel().sendMessage("Please enter a valid date.");
                    }
                }
            }
        });
    }

    public static void archive(Message message, FileWriter writer) {
        try {
            String author = message.getAuthor().toString().split(":")[2].trim().replace(")", ""); // getting the author
                                                                                                  // and formatting

            String edited = (message.getLastEditTimestamp().isPresent()) ? " (edited) |" : " ";// checking if message
                                                                                               // was edited

            String content = message.getReadableContent(); // getting message content

            String time = " " + LocalDateTime.ofInstant(message.getCreationTimestamp(), ZoneId.systemDefault())
                    .format(DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm"));

            writer.append(author + "|" + time + "|" + edited + content + System.lineSeparator());
        } catch (IOException exception) {
            System.out.println("Error occured");
            exception.printStackTrace();
        }
    }
}
