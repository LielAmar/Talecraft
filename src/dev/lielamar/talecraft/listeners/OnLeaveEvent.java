package dev.lielamar.talecraft.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import dev.lielamar.talecraft.Main;
import dev.lielamar.talecraft.constants.Constants;
import dev.lielamar.talecraft.players.CustomPlayer;
import dev.lielamar.talecraft.utils.Utils;

public class OnLeaveEvent implements Listener {

	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		
		Player p = e.getPlayer();
		
		CustomPlayer remove = null;
		for(CustomPlayer pl : Main.players) {
			if(pl.getP().getUniqueId().toString().equalsIgnoreCase(p.getUniqueId().toString())) {
				remove = pl;
			}
		}
		
		if(remove != null) {
			remove.saveConfig();
			Main.players.remove(remove);
		}
		
		if(Utils.spectatorsList.containsKey(p.getUniqueId())) {
			Utils.spectatorsList.remove(p.getUniqueId());
		}
		
		boolean found = Constants.isYoutuber(p.getUniqueId());
		
		if(!found) {
			Utils.setSpectator(p);
			e.setQuitMessage(null);
		} else {
			
			for(Player pl : Bukkit.getOnlinePlayers()) {
				if(Utils.spectatorsList.containsKey(pl.getUniqueId())) {
					if(Utils.spectatorsList.get(pl.getUniqueId()) == p.getUniqueId()) {
						Utils.spectatorsList.put(pl.getUniqueId(), Utils.getOnlineYoutuber());
					}
				}
			}
			
			Main.players.add(new CustomPlayer(p));
			e.setQuitMessage(ChatColor.RED + p.getName() + ChatColor.GRAY + " left the game");
			Utils.setAboveHeadName("destroy", p);
		}
	}
}
