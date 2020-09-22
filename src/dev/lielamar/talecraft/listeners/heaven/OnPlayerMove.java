package dev.lielamar.talecraft.listeners.heaven;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class OnPlayerMove implements Listener {

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if(p.getWorld().getName().toLowerCase().contains("heaven")) {
			if(e.getTo().getX() > 750 || e.getTo().getX() < -750 || e.getTo().getZ() > 750 || e.getTo().getZ() < -750) {
				e.setTo(e.getFrom());
			}
		}
	}
}
