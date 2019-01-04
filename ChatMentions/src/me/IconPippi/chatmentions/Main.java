package me.IconPippi.chatmentions;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.IconPippi.chatmentions.chat.Mentions;
import me.IconPippi.chatmentions.commands.Commands;

public class Main extends JavaPlugin {
	
	public void onEnable() {
		if (!Bukkit.getServer().getPluginManager().isPluginEnabled("PermissionsEx") && !Bukkit.getServer().getPluginManager().isPluginEnabled("ActionBarAPI")) {
				System.out.println("&4Could not find PermissionsEx or ActionBarAPI plugins, this may cause some malfunctionings");
			
				startUp();
		} else {
			startUp();
		}
	}
	
	public void startUp() {
		this.getConfig().options().copyDefaults(true);
		this.saveDefaultConfig();
		new Commands(this);
		new Mentions(this);
	}
	 
	public void onDisable() {
		this.saveDefaultConfig();
	}
}
