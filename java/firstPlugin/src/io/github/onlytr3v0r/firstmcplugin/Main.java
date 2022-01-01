package io.github.onlytr3v0r.firstmcplugin;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;

public class Main extends JavaPlugin {

	
    @Override
    public void onEnable() {
    }
    @Override
    public void onDisable() {
    }
    
    
	
	@Override public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("firstcommand")) {
			sender.sendMessage("this is my first command in my first plugin :DDDDD");
			return true; 
		}
	return false;
	} 
		
}
