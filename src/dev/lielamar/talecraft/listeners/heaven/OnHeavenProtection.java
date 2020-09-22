package dev.lielamar.talecraft.listeners.heaven;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

import dev.lielamar.talecraft.utils.Utils;

public class OnHeavenProtection implements Listener {

	@EventHandler
	public void onSpawn(EntitySpawnEvent e) {
		if(!e.getEntity().getWorld().getName().toString().equalsIgnoreCase(Utils.heavenLoc.getWorld().getName().toString())) return;
		
		if(e.getEntity() instanceof LivingEntity) {
			if(e.getEntity() instanceof ArmorStand) return;
			if(!(e.getEntity() instanceof Player)) {
				if (e.getEntity().getLocation().distance(Utils.heavenLoc) < 75
						|| Math.abs(e.getEntity().getLocation().getX()-Utils.heavenLoc.getX()) < 75
						&& Math.abs(e.getEntity().getLocation().getZ()-Utils.heavenLoc.getZ()) < 75) {
					e.setCancelled(true);
				}
			}
		}
	}

	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if(!e.getBlock().getWorld().getName().toString().equalsIgnoreCase(Utils.heavenLoc.getWorld().getName().toString())) return;
		
		if (e.getBlock().getLocation().distance(Utils.heavenLoc) < 75
				|| Math.abs(e.getBlock().getLocation().getX()-Utils.heavenLoc.getX()) < 75
				&& Math.abs(e.getBlock().getLocation().getZ()-Utils.heavenLoc.getZ()) < 75) {
			if(e.getPlayer().isOp()) return;
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		if(!e.getBlock().getWorld().getName().toString().equalsIgnoreCase(Utils.heavenLoc.getWorld().getName().toString())) return;
		
		if (e.getBlock().getLocation().distance(Utils.heavenLoc) < 75
				|| Math.abs(e.getBlock().getLocation().getX()-Utils.heavenLoc.getX()) < 75
				&& Math.abs(e.getBlock().getLocation().getZ()-Utils.heavenLoc.getZ()) < 75) {
			if(e.getPlayer().isOp()) return;
			e.setCancelled(true);
		}
	}
}
