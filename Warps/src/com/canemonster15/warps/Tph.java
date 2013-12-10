package com.canemonster15.warps;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Tph implements CommandExecutor{
	
	private Main plugin;
	public Tph(Main plugin){
		this.plugin = plugin;
	}
	String prefix = ChatColor.BLUE + "[BasicWarps] ";
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		
		Player player = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("tph")){
			if(!sender.hasPermission("bw.tph")){
				sender.sendMessage(plugin.noPerm);
			}else{
				if(args.length == 0){
					sender.sendMessage(prefix + "Usage: /tph <player>");
				}else if(args.length == 1){
					Player tplayer = Bukkit.getServer().getPlayer(args[0]);
					if(tplayer == null){
						sender.sendMessage(prefix + "Player not found!");
					}else{
						tplayer.teleport(player);
					}
				}
			}
		}
		
		return true;
	}

}
