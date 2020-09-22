package dev.lielamar.talecraft.listeners.heaven;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.world.PortalCreateEvent;
import org.bukkit.event.world.PortalCreateEvent.CreateReason;

public class OnPortalEvent implements Listener {

	@EventHandler
	public void onPortal(PortalCreateEvent e) {
		if(e.getWorld().getName().toLowerCase().contains("heaven")) {
			if(!(e.getReason() == CreateReason.FIRE || e.getReason() == CreateReason.NETHER_PAIR))
				e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onWorldChange(PlayerPortalEvent e) {
		if(e.getFrom().getWorld().getName().equals(e.getTo().getWorld().getName())) return;
		
		if(e.getFrom().getWorld().getName().toLowerCase().equals("heaven")) {
			e.setTo(Bukkit.getWorld("Heaven_nether").getSpawnLocation());
		}
	}
}
