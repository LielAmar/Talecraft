package dev.lielamar.talecraft.listeners.heaven;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class OnBedPlaceEvent implements Listener {

	@EventHandler
	public void onBedPlace(BlockPlaceEvent e) {
		if(e.getBlock().getWorld().getName().toLowerCase().contains("heaven")) {
			if(e.getBlock().getType().toString().toLowerCase().contains("bed")) e.setCancelled(true);
		}
	}
	
}
