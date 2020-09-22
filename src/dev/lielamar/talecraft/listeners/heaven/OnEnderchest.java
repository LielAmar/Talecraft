package dev.lielamar.talecraft.listeners.heaven;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class OnEnderchest implements Listener {

	@EventHandler
	public void onEnderchest(PlayerInteractEvent e) {
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(e.getClickedBlock() != null && e.getClickedBlock().getType() == Material.ENDER_CHEST) {
				if(e.getClickedBlock().getWorld().getName().toLowerCase().contains("heaven")) {
					e.setCancelled(true);
				}
			}
		}
	}
	
}
