import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

import java.util.ArrayList;

public class bot {
  public static void main(String[] args) {
    DiscordApi api = new DiscordApiBuilder().setToken("its a secret!
        .login().join();

    // getting link and changing permissions (changing last char of the link)
    String link = api.createBotInvite();
    char[] editPerms = link.toCharArray();
    editPerms[editPerms.length - 1] = '8'; // 8 is permission level- can be calculated at DCDP
    link = String.valueOf(editPerms);

    System.out.println(
        System.lineSeparator() + "you can invite my archiver bot with the link: " + link + System.lineSeparator()); // printing
                                                                                                                    // out
                                                                                                                    // link

    // commands
    api.addMessageCreateListener(event -> { // listener for message being sent (or created) and checking message is
      // not sent by the bot
      if (!event.getMessageAuthor().isYourself()) {

        String msg = event.getMessageContent();

        if (msg.charAt(0) == '>') { // checking if message is a command or not, and splitting it

          String cmd = msg.substring(1);
          String[] cmdArgs = cmd.split(" ");

          if (cmdArgs[0].equalsIgnoreCase("createCommand")) {
            event.getChannel().sendMessage("message will be created here");
            event.getChannel().sendMessage("message name will be " + cmdArgs[1]);
          } else if (cmdArgs[0].equalsIgnoreCase("exit")) {
            event.getChannel().sendMessage("cya <3");
            System.exit(0);
          }
        }
      }

    });
  }
}
