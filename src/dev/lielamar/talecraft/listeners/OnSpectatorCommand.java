package dev.lielamar.talecraft.listeners;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import dev.lielamar.talecraft.constants.Constants;
import dev.lielamar.talecraft.utils.Utils;

public class OnSpectatorCommand implements Listener {

	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		if(!Constants.isYoutuber(e.getPlayer().getUniqueId())) {
			if(e.getMessage().toLowerCase().contains(":")) {
				e.setCancelled(true);
			} else if(e.getMessage().toLowerCase().contains("/tell")
					|| e.getMessage().toLowerCase().contains("/help")
					|| e.getMessage().toLowerCase().contains("/list")
					|| e.getMessage().toLowerCase().contains("/me")
					|| e.getMessage().toLowerCase().contains("/msg")
					|| e.getMessage().toLowerCase().contains("/teammsg")
					|| e.getMessage().toLowerCase().contains("/tm")
					|| e.getMessage().toLowerCase().contains("/trigger")
					|| e.getMessage().toLowerCase().contains("/w"))
				e.setCancelled(true);
			else if(e.getMessage().toLowerCase().contains("/pl")
					|| e.getMessage().toLowerCase().contains("/plugins")) {
				e.setMessage("Plugins (1): " + ChatColor.GREEN + "Talecraft");
			}
		} 
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if(!(Constants.isYoutuber(e.getPlayer().getUniqueId())))
			e.setCancelled(true);
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if(Constants.isYoutuber(p.getUniqueId())) return;
		if(Constants.isSpectator(p.getUniqueId())) return;
		
		if(!Utils.spectatorsList.containsKey(p.getUniqueId()))
			Utils.spectatorsList.put(p.getUniqueId(), Utils.getOnlineYoutuber());
		UUID yt = Utils.spectatorsList.get(p.getUniqueId());
		if(yt == null) {
			if(Math.abs(p.getLocation().getX()-200) > 100 || Math.abs(p.getLocation().getZ()-200) > 100) {
				p.teleport(p.getWorld().getHighestBlockAt(200, 200).getLocation());
			}
		} else {
			Player youtuber = Bukkit.getPlayer(yt);
			if(youtuber == null) {
				Utils.spectatorsList.put(p.getUniqueId(), Utils.getOnlineYoutuber());
				return;
			}
			if(Math.abs(p.getLocation().getX()-youtuber.getLocation().getX()) > 100 || Math.abs(p.getLocation().getZ()-youtuber.getLocation().getZ()) > 100) {
				p.teleport(youtuber.getLocation());
			}
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {
			Player p = (Player)e.getEntity();
			if(!Constants.isYoutuber(p.getUniqueId())) e.setCancelled(true);
		}
	}
}
