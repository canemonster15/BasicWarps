package com.canemonster15.warps;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWarp implements CommandExecutor{
	
	private Main plugin;
	public SetWarp(Main Plugin){
		this.plugin = plugin;
	}
	String prefix = ChatColor.BLUE + "[BasicWarps] ";
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		
		Player player = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("setwarp")){
			if(!sender.hasPermission("bw.setwarp")){
				sender.sendMessage(prefix + "Invalid Permissions!");
			}else{
				if(args.length == 0){
					sender.sendMessage(prefix + "Usage: /setwarp <name>");
				}else if(args.length == 1){
					plugin.warps.addDefault(args[0] + ".X", player.getLocation().getY());
					plugin.warps.addDefault(args[0] + ".Y", player.getLocation().getY());
					plugin.warps.addDefault(args[0] + ".Z", player.getLocation().getZ());
					plugin.warps.addDefault(args[0] + ".World", player.getLocation().getWorld().getName());
					plugin.saveConfig();
					player.sendMessage(prefix + "Warp created!");
				}
			}
		}
		
		return true;
	}
}
