package com.canemonster15.warps;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Home implements CommandExecutor{
	
	public Main plugin;
	public Home(Main plugin){
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		
		if(cmd.getName().equalsIgnoreCase("home")){
			if(!sender.hasPermission("bw.home")){
				sender.sendMessage(plugin.noPerm);
			}else{
				if(!plugin.homes.contains(sender.getName()+ ".X")){
					sender.sendMessage(plugin.prefix + "You don't have a set home!");
				}else{
					double x = plugin.homes.getDouble(sender.getName() + ".X");
					double y = plugin.homes.getDouble(sender.getName() + ".Y");
					double z = plugin.homes.getDouble(sender.getName() + ".Z");
					World w = Bukkit.getServer().getWorld(plugin.homes.getString(sender.getName() + ".World"));
					Player player = (Player) sender;
					player.teleport(new Location(w,x,y,z));
				}
			}
		}
		
		return true;
	}

}
