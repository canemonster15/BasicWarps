package com.canemonster15.warps;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHome implements CommandExecutor{
	
	public Main plugin;
	public SetHome(Main plugin){
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		Player player = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("setwarp")){
			if(!sender.hasPermission("bw.sethome")){
				sender.sendMessage(plugin.noPerm);
			}else{
				plugin.homes.addDefault(sender.getName() + ".X", player.getLocation().getX());
				plugin.homes.addDefault(sender.getName() + ".Y", player.getLocation().getY());
				plugin.homes.addDefault(sender.getName() + ".Z", player.getLocation().getZ());
				plugin.homes.addDefault(sender.getName() + ".World", player.getLocation().getWorld().getName());
				sender.sendMessage(plugin.prefix + "Home set!");
			}
		}
		
		return true;
	}

}
