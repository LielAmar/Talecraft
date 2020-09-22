package dev.lielamar.talecraft.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import dev.lielamar.talecraft.Main;
import dev.lielamar.talecraft.constants.Constants;

public class StreamingUtils {

	public static boolean streaming = false;
	
	/**
	 * @param p        Player to start a stream for
	 */
	public static void startStreaming(Player p) {
		if(Main.getInstance().getConfig().getBoolean("streaming.value")) {
			p.sendMessage(ChatColor.RED + Main.getInstance().getConfig().getString("streaming.youtuber") + " is already streaming and kicked all of the spectators!");
			return;
		}
		
		Main.getInstance().getConfig().set("streaming.value", true);
		Main.getInstance().getConfig().set("streaming.youtuber", p.getName());
		Main.getInstance().saveConfig();
		streaming = true;
		
		for(Player pl : Bukkit.getOnlinePlayers()) {
			if(!Constants.isYoutuber(pl.getUniqueId())) {
				pl.sendMessage(ChatColor.AQUA + "!תופצל תכלל םינמזומ - ולש בויטויה ץורעב יח רודיש ליחתמ " + ChatColor.YELLOW + p.getName());
				pl.sendMessage(ChatColor.AQUA + "...תוינש רפסמ דועב םכתא יאצוי תרשה");
				
				pl.kickPlayer(ChatColor.AQUA + "!תופצל תכלל םינמזומ - ולש בויטויה ץורעב יח רודיש ליחתמ " + ChatColor.YELLOW + p.getName());
			}
		}
		
		Bukkit.broadcastMessage(ChatColor.YELLOW + p.getName() + ChatColor.AQUA + " started a stream!");
	}
	
	/**
	 * Stops the activated stream
	 * 
	 * @param p        Player who stops the stream
	 */
	public static void stopStreaming(Player p) {
		if(!Main.getInstance().getConfig().getBoolean("streaming.value")) {
			p.sendMessage(ChatColor.RED + "There isn't a stream going on!");
			return;
		}
		
		Main.getInstance().getConfig().set("streaming.value", false);
		Main.getInstance().getConfig().set("streaming.youtuber", "null");
		Main.getInstance().saveConfig();
		streaming = false;
		
		Bukkit.broadcastMessage(ChatColor.YELLOW + p.getName() + ChatColor.AQUA + " stopped the stream. Players can watch you now!");
	}
}
