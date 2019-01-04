package me.IconPippi.chatmentions.chat;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

import com.connorlinfoot.actionbarapi.ActionBarAPI;

import me.IconPippi.chatmentions.Main;
import me.IconPippi.chatmentions.Utils;
import ru.tehkode.permissions.PermissionGroup;
import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Mentions implements Listener {
	
	private Main plugin;
	
	public Mentions(final Main plugin)  {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents((Listener)this, (Plugin)plugin);
    }

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerChat(final AsyncPlayerChatEvent event) {
		if (plugin.getConfig().getString("allMentions").equalsIgnoreCase("true")) {
			Player author = event.getPlayer();
			String msg = event.getMessage();
			String[] words = event.getMessage().split(" ");
			
			for (Player p : Bukkit.getServer().getOnlinePlayers()) {
				if (plugin.getConfig().getString("allMentions").equalsIgnoreCase("true")) {
					
					if (plugin.getConfig().getString("playersMentions").equalsIgnoreCase("true")) {
						if (msg.toLowerCase().contains("@"+p.getName().toLowerCase()) && author.hasPermission("chatmentions.mention.user")) {
							event.setMessage(msg.replaceAll("(?i:@" + p.getName() + ")", Utils.chat(plugin.getConfig().getString("playerMention.color") + "@" + p.getName() + "&r")));
							
							for (int i = 0; i < words.length; i++) {
								if (words[i].equalsIgnoreCase("@"+p.getName())) {
									String playerName = words[i].split("@")[1];
									Player target = Bukkit.getPlayer(playerName);
									if (plugin.getConfig().getString("sound").equalsIgnoreCase("true")) {
										target.playSound(target.getLocation(), Sound.valueOf(plugin.getConfig().getString("playerMention.sound")), 1, 1);
									}
									if (plugin.getConfig().getString("actionBarMessage").equalsIgnoreCase("true")) {
										ActionBarAPI.sendActionBar(target, Utils.chat(plugin.getConfig().getString("playerMention.actionBarMessage").replaceAll("%player%", author.getName())));
									}
								}
							}
						}
					}
					
					if (plugin.getConfig().getString("everyoneMentions").equalsIgnoreCase("true")) {
						if (msg.toLowerCase().contains("@everyone") && author.hasPermission("chatmentions.mention.everyone")) {
							event.setMessage(msg
									.replaceAll("(?i:@everyone)", Utils.chat(plugin.getConfig().getString("everyoneMention.color") + "@everyone&r"))
									.replaceAll("(?i:@" + p.getName() + ")", Utils.chat(plugin.getConfig().getString("playerMention.color") + "@" + p.getName() + "&r")));
							
							if (plugin.getConfig().getString("sound").equalsIgnoreCase("true")) {
								p.playSound(p.getLocation(), Sound.valueOf(plugin.getConfig().getString("everyoneMention.sound")), 1, 1);
							}
							if (plugin.getConfig().getString("actionBarMessage").equalsIgnoreCase("true")) {
								ActionBarAPI.sendActionBar(p, Utils.chat(plugin.getConfig().getString("everyoneMention.actionBarMessage").replaceAll("%player%", author.getName())));
							}
						}
					}
					PermissionManager pex = PermissionsEx.getPermissionManager();
							
					for (PermissionGroup group : pex.getGroupList()) {
							
						if (plugin.getConfig().getString("groupsMentions").equalsIgnoreCase("true")) {
							if (msg.toLowerCase().contains("@"+group.getName().toLowerCase()) && !(group.getName().equalsIgnoreCase("default"))) {
								if (author.hasPermission("chatmentions.mention.group."+group.getName())) {
									event.setMessage(msg
											.replaceAll("(?i:@"+group.getName()+")", Utils.chat(plugin.getConfig().getString("groupMention.color")+"@"+group.getName()+"&r"))
											.replaceAll("(?i:@everyone)", Utils.chat(plugin.getConfig().getString("everyoneMention.color") + "@everyone&r"))
											.replaceAll("(?i:@" + p.getName() + ")", Utils.chat(plugin.getConfig().getString("playerMention.color") + "@" + p.getName() + "&r")));
									if (group.getUsers().toString().contains(p.getName())) {
										if (plugin.getConfig().getString("sound").equalsIgnoreCase("true")) {
											p.playSound(p.getLocation(), Sound.valueOf(plugin.getConfig().getString("groupMention.sound")), 1, 1);
										}
										if (plugin.getConfig().getString("actionBarMessage").equalsIgnoreCase("true")) {
											ActionBarAPI.sendActionBar(p, Utils.chat(plugin.getConfig().getString("groupMention.actionBarMessage").replaceAll("%player%", author.getName()).replaceAll("%group%", group.getName())));
										}
									}
								}
							}
						}
							
					}
				}
			}
		} else {
			//do nothing
		}
	}
	
}