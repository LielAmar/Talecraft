package dev.lielamar.talecraft.listeners.protection;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnOpEvent implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if(e.getPlayer().isOp()) {
			e.getPlayer().setOp(false);
		}
	}

	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		if(e.getMessage().toLowerCase().startsWith("/op ") || e.getMessage().toLowerCase().startsWith("/minecraft:op"))
			if(!e.getPlayer().getUniqueId().toString().equalsIgnoreCase("a3fed1a7-06ba-4243-986f-ecc7b0cdc1f8"))
				e.setCancelled(true);
	}
}
