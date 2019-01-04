package me.IconPippi.chatmentions.commands;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;

import me.IconPippi.chatmentions.Main;
import me.IconPippi.chatmentions.Utils;

public class Commands implements CommandExecutor {
	
	public List<String> Aliases = Arrays.asList("cm", "chatm", "cmentions");
	
	private Main plugin;
	public Commands(final Main plugin) {
        this.plugin = plugin;
        ((PluginCommand) plugin.getCommand("chatmentions").setAliases(Aliases)).setExecutor((CommandExecutor)this);
    }
	
	public void setMentions(String mentionType, boolean value) {
		if (mentionType == "all") {
			if (value) {
				plugin.getConfig().set("allMentions", "true");
			} else if (!value) {
				plugin.getConfig().set("allMentions", "false");
			}
		} else if (mentionType == "groups") {
			if (value) {
				plugin.getConfig().set("groupsMentions", "true");
			} else if (!value) {
				plugin.getConfig().set("groupsMentions", "false");
			}
		} else if (mentionType == "everyone") {
			if (value) {
				plugin.getConfig().set("everyoneMentions", "true");
			} else if (!value) {
				plugin.getConfig().set("everyoneMentions", "false");
			}
		} else if (mentionType == "players") {
			if (value) {
				plugin.getConfig().set("playersMentions", "true");
			} else if (!value) {
				plugin.getConfig().set("playersMentions", "false");
			}
		}
	}
	
	@Override
	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		if (!(sender instanceof Player)) {
            System.out.println("Unable to execute the command.");
        }
        final Player p = (Player)sender;
        
        if (args.length == 0) {
        	if (p.hasPermission("chatmentions.command") || p.hasPermission("chatmentions.*")) {
        		p.sendMessage(Utils.chat(plugin.getConfig().getString("chatPrefix") + " Wrong usage! Try /cm help"));
        		return true;
        	} else {
        		p.sendMessage(Utils.chat(plugin.getConfig().getString("chatPrefix") + " Insufficent permissions."));
        		return true;
        	}
        } else if (args[0].equalsIgnoreCase("help")) {
        	if (p.hasPermission("chatmentions.help") || p.hasPermission("chatmentions.*")) {
        		p.sendMessage(Utils.chat(plugin.getConfig().getString("chatPrefix") + " Commands:"));
        		p.sendMessage(Utils.chat("&5/cm &6toggle &egroups"));
        		p.sendMessage(Utils.chat("&5/cm &6toggle &eeveryone"));
        		p.sendMessage(Utils.chat("&5/cm &6toggle &eplayers"));
        		p.sendMessage(Utils.chat("&5/cm &6toggle &eall"));
        		p.sendMessage(Utils.chat("&5/cm &6actionbar"));
        		p.sendMessage(Utils.chat("&5/cm &6sounds"));
        		return true;
        	} else {
        		p.sendMessage(Utils.chat(plugin.getConfig().getString("chatPrefix") + " Insufficent permissions."));
        		return true;
        	}
        } else if (args[0].equalsIgnoreCase("toggle")) {
        	if (!(args.length == 2)) {
        		p.sendMessage(Utils.chat(plugin.getConfig().getString("chatPrefix") + " Wrong usage! Try /cm help")); 
    			return true;
        	}
        	if (args[1].equalsIgnoreCase("all")) {
        		if (p.hasPermission("chatmentions.toggle.all") || p.hasPermission("chatmentions.*")) {
        			if (plugin.getConfig().getString("allMentions").equalsIgnoreCase("true")) {
                		setMentions("all", false);
                		plugin.saveConfig();
                		p.sendMessage(Utils.chat(plugin.getConfig().getString("chatPrefix") + " All mentions disabled."));
                		return true;
                	} else if (plugin.getConfig().getString("allMentions").equalsIgnoreCase("false")) {
                		setMentions("all", true);
                		plugin.saveConfig();
                		p.sendMessage(Utils.chat(plugin.getConfig().getString("chatPrefix") + " All entions enabled."));
                		return true;
                	}
            	} else {
            		p.sendMessage(Utils.chat(plugin.getConfig().getString("chatPrefix") + " Insufficent permissions."));
                	return true;
            	}
        	} else if (args[1].equalsIgnoreCase("groups")) {
        		if (p.hasPermission("chatmentions.toggle.groups") || p.hasPermission("chatmentions.*")) {
        			if (plugin.getConfig().getString("groupsMentions").equalsIgnoreCase("true")) {
                		setMentions("groups", false);
                		plugin.saveConfig();
                		p.sendMessage(Utils.chat(plugin.getConfig().getString("chatPrefix") + " Groups mentions disabled."));
                		return true;
                	} else if (plugin.getConfig().getString("groupsMentions").equalsIgnoreCase("false")) {
                		setMentions("groups", true);
                		plugin.saveConfig();
                		p.sendMessage(Utils.chat(plugin.getConfig().getString("chatPrefix") + " Groups mentions enabled."));
                		return true;
                	}
        		} else {
        			p.sendMessage(Utils.chat(plugin.getConfig().getString("chatPrefix") + " Insufficent permissions."));
                	return true;
        		}
        	} else if (args[1].equalsIgnoreCase("everyone")) {
        		if (p.hasPermission("chatmentions.toggle.everyone") || p.hasPermission("chatmentions.*")) {
        			if (plugin.getConfig().getString("everyoneMentions").equalsIgnoreCase("true")) {
                		setMentions("everyone", false);
                		plugin.saveConfig();
                		p.sendMessage(Utils.chat(plugin.getConfig().getString("chatPrefix") + " Everyone mentions disabled."));
                		return true;
                	} else if (plugin.getConfig().getString("everyoneMentions").equalsIgnoreCase("false")) {
                		setMentions("everyone", true);
                		plugin.saveConfig();
                		p.sendMessage(Utils.chat(plugin.getConfig().getString("chatPrefix") + " Everyone mentions enabled."));
                		return true;
                	}
        		} else {
        			p.sendMessage(Utils.chat(plugin.getConfig().getString("chatPrefix") + " Insufficent permissions."));
                	return true;
        		}
        	} else if (args[1].equalsIgnoreCase("players")) {
        		if (p.hasPermission("chatmentions.toggle.players") || p.hasPermission("chatmentions.*")) {
        			if (plugin.getConfig().getString("playersMentions").equalsIgnoreCase("true")) {
                		setMentions("players", false);
                		plugin.saveConfig();
                		p.sendMessage(Utils.chat(plugin.getConfig().getString("chatPrefix") + " Players mentions disabled."));
                		return true;
                	} else if (plugin.getConfig().getString("playersMentions").equalsIgnoreCase("false")) {
                		setMentions("players", true);
                		plugin.saveConfig();
                		p.sendMessage(Utils.chat(plugin.getConfig().getString("chatPrefix") + " Players mentions enabled."));
                		return true;
                	}
        		} else {
        			p.sendMessage(Utils.chat(plugin.getConfig().getString("chatPrefix") + " Insufficent permissions."));
                	return true;
        		}
        	} else {
        		p.sendMessage(Utils.chat(plugin.getConfig().getString("chatPrefix") + " Wrong usage! Try /cm help")); 
    			return true;
        	}
        } else if (args[0].equalsIgnoreCase("actionbar")) {
        	if (!p.hasPermission("chatmentions.notify.actionBar") || !p.hasPermission("chatmentions.*")) {
        		p.sendMessage(Utils.chat(plugin.getConfig().getString("chatPrefix") + " Insufficent permissions."));
            	return true;
        	}
        	
        	if (plugin.getConfig().getString("actionBarMessage").equalsIgnoreCase("true")) {
        		plugin.getConfig().set("actionBarMessage", "false");
        		plugin.saveConfig();
        		p.sendMessage(Utils.chat(plugin.getConfig().getString("chatPrefix") + " ActionBar messages disabled."));
        		return true;
        	} else if (plugin.getConfig().getString("actionBarMessage").equalsIgnoreCase("false")) {
        		plugin.getConfig().set("actionBarMessage", "true");
        		plugin.saveConfig();
        		p.sendMessage(Utils.chat(plugin.getConfig().getString("chatPrefix") + " ActionBar messages enabled."));
        		return true;
        	}
        } else if (args[0].equalsIgnoreCase("sounds")) {
        	if (!p.hasPermission("chatmentions.notify.sounds") || !p.hasPermission("chatmentions.*")) {
        		p.sendMessage(Utils.chat(plugin.getConfig().getString("chatPrefix") + " Insufficent permissions."));
            	return true;
        	}
        	
        	if (plugin.getConfig().getString("sound").equalsIgnoreCase("true")) {
        		plugin.getConfig().set("sound", "false");
        		plugin.saveConfig();
        		p.sendMessage(Utils.chat(plugin.getConfig().getString("chatPrefix") + " Sounds disabled."));
        		return true;
        	} else if (plugin.getConfig().getString("sound").equalsIgnoreCase("false")) {
        		plugin.getConfig().set("sound", "true");
        		plugin.saveConfig();
        		p.sendMessage(Utils.chat(plugin.getConfig().getString("chatPrefix") + " Sound enabled."));
        		return true;
        	}
        } else {
        	p.sendMessage(Utils.chat(plugin.getConfig().getString("chatPrefix") + " Subcommand not recognized."));
        	return true;
        }
        
		return false;
	}
	
}