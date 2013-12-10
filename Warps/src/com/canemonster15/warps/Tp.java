package com.canemonster15.warps;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Tp implements CommandExecutor{
	
	private Main plugin;
	public Tp(Main plugin){
		this.plugin = plugin;
	}
	String prefix = ChatColor.BLUE + "[BasicWarps] ";
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		
		if(cmd.getName().equalsIgnoreCase("tp")){
			if(!sender.hasPermission("bw.tp")){
				sender.sendMessage(plugin.noPerm);
			}else{
				if(args.length == 0){
					sender.sendMessage(prefix + "Usage: /tp <player>");
				}else if(args.length == 1){
					Player tplayer = Bukkit.getServer().getPlayer(args[0]);
					if(tplayer == null){
						sender.sendMessage(prefix + "Player not found!");
					}else{
						Player player = (Player) sender;
						player.teleport(tplayer);
					}
				}
			}
		}
		
		return true;
	}

}
