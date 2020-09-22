package dev.lielamar.talecraft.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.lielamar.talecraft.constants.Constants;
import dev.lielamar.talecraft.listeners.OnBedEvent;

public class Bed implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String cmdLabel, String[] args) {
		
		if(!cmd.getName().equalsIgnoreCase("bed")) return false;
		
		if(!(cs instanceof Player)) {
			cs.sendMessage(ChatColor.RED + "You must be a player to do that!");
			return false;
		}
		
		Player p = (Player)cs;
		
		if(!Constants.isYoutuber(p.getUniqueId())) {
			cs.sendMessage(ChatColor.RED + "You don't have enough permissions to do that!");
			return false;
		}
		
		if(args.length == 0) {
			cs.sendMessage(ChatColor.RED + "Wrong usage. Don't try to use this command if you're not the developer.");
			return false;
		}
		
		if(OnBedEvent.bedStatus.getVotes().contains(p.getUniqueId())) {
			p.sendMessage(ChatColor.RED + "You have already voted for the night-skip!");
			return false;
		}
		
		if(args[0].equalsIgnoreCase("accept")) {
			OnBedEvent.bedStatus.setAccept(OnBedEvent.bedStatus.getAccept()+1);
			OnBedEvent.bedStatus.addList(p.getUniqueId());
			p.sendMessage(ChatColor.GRAY + "You have voted to skip the night");
		} else if(args[0].equalsIgnoreCase("deny")) {
			OnBedEvent.bedStatus.setDeny(OnBedEvent.bedStatus.getDeny()+1);
			OnBedEvent.bedStatus.addList(p.getUniqueId());
			p.sendMessage(ChatColor.GRAY + "You have voted to keep the night");
		}
		return false;
	}
	
}
