package dev.lielamar.talecraft.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.lielamar.talecraft.Main;
import dev.lielamar.talecraft.constants.Constants;

public class Streaming implements CommandExecutor {
	
	private static Map<UUID, Boolean> streamingQueue = new HashMap<UUID, Boolean>();
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String cmdLabel, String[] args) {
		
		if(!(cs instanceof Player)) {
			cs.sendMessage(ChatColor.RED + "You must be a player to do that!");
			return false;
		}
		
		Player p = (Player)cs;
		
		if(!Constants.isYoutuber(p.getUniqueId())) {
			cs.sendMessage(ChatColor.RED + "You don't have enough permissions to do that!");
			return false;
		}
		
		if(cmd.getName().equalsIgnoreCase("startstreaming")) {
			p.sendMessage(ChatColor.GREEN + "If you wish to start streaming please confirm by doing /confirm in the next " + ChatColor.AQUA + "10" + ChatColor.GREEN + " seconds!");
			streamingQueue.put(p.getUniqueId(), true);
			startTimer(p);
		} else if(cmd.getName().equalsIgnoreCase("stopstreaming")) {
			p.sendMessage(ChatColor.RED + "If you wish to stop streaming please confirm by doing /confirm in the next " + ChatColor.AQUA + "10" + ChatColor.RED + " seconds!");
			streamingQueue.put(p.getUniqueId(), false);
			startTimer(p);
		} else if(cmd.getName().equalsIgnoreCase("confirm")) {
			
			if(!streamingQueue.containsKey(p.getUniqueId())) {
				p.sendMessage(ChatColor.RED + "You don't have anything to confirm");
				return false;
			}
			
			boolean value = streamingQueue.get(p.getUniqueId());
			
			if(value) {
				p.sendMessage(ChatColor.GREEN + "Trying to start your stream");
				StreamingUtils.startStreaming(p);
			} else {
				p.sendMessage(ChatColor.RED + "Trying to stop the stream");
				StreamingUtils.stopStreaming(p);
			}
			
			streamingQueue.remove(p.getUniqueId());
		}
		return false;
	}
	
	public void startTimer(Player p) {
		Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getInstance(), new Runnable() {
			@Override
			public void run() {
				if(streamingQueue.containsKey(p.getUniqueId())) {
					streamingQueue.remove(p.getUniqueId());
					p.sendMessage(ChatColor.RED + "10 seconds have passed. Please repeat your command.");
				}
			}
		}, 10*20L);
	}
}
