package dev.lielamar.talecraft.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.lielamar.talecraft.constants.Constants;
import dev.lielamar.talecraft.utils.Utils;

public class Spectate implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String cmdLabel, String[] args) {
		
		if(!(cs instanceof Player)) {
			cs.sendMessage(ChatColor.RED + "You must be a player to do that!");
			return false;
		}
		
		Player p = (Player)cs;
		
		if(Constants.isYoutuber(p.getUniqueId())) {
			cs.sendMessage(ChatColor.RED + "You can't do that since you're a youtuber!");
			return false;
		}
		
		if(cmd.getName().equalsIgnoreCase("spectate")) {
			if(args.length == 0) {
				p.sendMessage(ChatColor.RED + "/Spectate <רבויטויה םש> :האבה הרוצב הדוקפב ושמתשה אנא");
				return false;
			}
			
			Player youtuber = Bukkit.getPlayer(args[0]);
			if(youtuber == null) {
				p.sendMessage(ChatColor.AQUA + args[0] + ChatColor.RED + " םשב ןקחשה תא אוצמל ונחלצה אל");
				return false;
			}
			
			if(!Constants.isYoutuber(youtuber.getUniqueId())) {
				p.sendMessage(ChatColor.RED + "!רבויטוי אל אוהש ןוויכמ " + ChatColor.AQUA + args[0] + ChatColor.RED + " לא רגתשהל ןתינ אל");
				return false;
			}
			
			p.teleport(youtuber);
			Utils.spectatorsList.put(p.getUniqueId(), youtuber.getUniqueId());
		}
		return false;
	}
	
}
