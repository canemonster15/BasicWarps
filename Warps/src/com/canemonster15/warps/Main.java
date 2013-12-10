package com.canemonster15.warps;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	public final Logger log = Logger.getLogger("Minecraft");
	public String prefix = ChatColor.BLUE + "[BasicWarps] ";
	public String noPerm = ChatColor.BLUE + prefix + "Invalid Permissions!";
	public File warpsFile;
	public File homesFile;
	public FileConfiguration warps;
	public FileConfiguration homes;
	
	public void onEnable(){
		log.info("[BasicWarps]Enabled!");
		getConfig().options().copyDefaults(true);
		getCommand("tp").setExecutor(new Tp(this));
		getCommand("tph").setExecutor(new Tph(this));
		getCommand("setwarp").setExecutor(new SetWarp(this));
		getCommand("warp").setExecutor(new Warp(this));
		getCommand("sethome").setExecutor(new SetHome(this));
		getCommand("home").setExecutor(new Home(this));
		warpsFile = new File(getDataFolder(), "warps.yml");
		homesFile = new File(getDataFolder(), "homes.yml");
		try{
			firstRun();
		}catch(Exception e){
			e.printStackTrace();
		}
		warps = new YamlConfiguration();
		homes = new YamlConfiguration();
		loadYamls();
	}
	
	public void saveYamls() {
		try{
			warps.save(warpsFile);
			homes.save(homesFile);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadYamls() {
		try{
			warps.load(warpsFile);
			homes.load(homesFile);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void firstRun() throws Exception {
		if(!warpsFile.exists()){
			warpsFile.getParentFile().mkdirs();
			copy(getResource("warps.yml"), warpsFile);
		}
		if(!homesFile.exists()){
			homesFile.getParentFile().mkdirs();
			copy(getResource("homes.yml"), homesFile);
		}
	}
	
	private void copy(InputStream in, File file) {
		try{
			OutputStream out = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int len;
			while((len=in.read(buf))>0){
				out.write(buf,0,len);
			}
			out.close();
			in.close();
		}catch(Exception e ) {
			e.printStackTrace();
		}
	}
	
	public void onDisable(){
		log.info("[BasicWarps]Disabled!");
		saveConfig();
	}
	
	public void setWarp(Player player, String name){
		warps.addDefault(name + ".X", player.getLocation().getX());
		warps.addDefault(name + ".Y", player.getLocation().getY());
		warps.addDefault(name + ".Z", player.getLocation().getZ());
		warps.addDefault(name + ".World", player.getLocation().getWorld().getName());
		player.sendMessage(prefix + "Warp created!");
	}
	
	public void warp(Player player, String name){
		double x = getConfig().getDouble(name + ".X");
		double y = getConfig().getDouble(name + ".Y");
		double z = getConfig().getDouble(name + ".Z");
		World w = Bukkit.getServer().getWorld(getConfig().getString(name + ".World"));
		player.teleport(new Location(w,x,y,z));
	}
	
	
	
	
	
}
