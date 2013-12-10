package com.canemonster15.warps;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Warp implements CommandExecutor{
	
	private Main plugin;
	public Warp(Main plugin){
		this.plugin = plugin;
	}
	String prefix = ChatColor.BLUE + "[BasicWarps] ";
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		
		if(cmd.getName().equalsIgnoreCase("warp")){
			if(!sender.hasPermission("bw.warp")){
				sender.sendMessage(prefix + "Invalid Permissions!");
			}else{
				if(args.length == 0){
					sender.sendMessage(prefix + "Usage: /warp <name>");
				}else if(args.length == 1){
					String name = args[0];
					if(!plugin.getConfig().contains(name + ".X")){
						sender.sendMessage(prefix + "Warp does not exist!");
					}else{
						Player player = (Player) sender;
						double x = plugin.warps.getDouble(name + ".X");
						double y = plugin.warps.getDouble(name + ".Y");
						double z = plugin.warps.getDouble(name + ".Z");
						World w = Bukkit.getServer().getWorld(plugin.warps.getString(name + ".World"));
						player.teleport(new Location(w,x,y,z));
						player.sendMessage(prefix + "Warped!");
					}
				}
			}
		}
		
		return true;
	}

}
