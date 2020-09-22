package dev.lielamar.talecraft.listeners.globalquests.superheroes;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;

import dev.lielamar.talecraft.Main;
import dev.lielamar.talecraft.players.CustomPlayer;

public class OnThorSuperpower implements Listener {

	private Map<UUID, Long> cooldown = new HashMap<UUID, Long>();
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if(Main.getInstance().globalQuestManager.getQuest() != 2) return;
		
		if(e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
		if(e.getClickedBlock() == null || e.getClickedBlock().getType() == Material.AIR) return;
		if(e.getPlayer().getInventory().getItemInMainHand().getType() != Material.WOODEN_AXE) return;
		if(cooldown.containsKey(e.getPlayer().getUniqueId()) && System.currentTimeMillis()-cooldown.get(e.getPlayer().getUniqueId()) < 2500) {
			e.getPlayer().sendMessage(ChatColor.RED + "You are still in cooldown!");
			return;
		}
		
		for(CustomPlayer cp : Main.players) {
			if(cp.getP() == e.getPlayer()) {
				if(cp.getSuperPower() != 1) return;
				e.getClickedBlock().getWorld().strikeLightning(e.getClickedBlock().getLocation());
				cooldown.put(e.getPlayer().getUniqueId(), System.currentTimeMillis());
			}
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if(Main.getInstance().globalQuestManager.getQuest() != 2) return;
		
		if(!(e.getEntity() instanceof Player)) return;
		if(!(e.getCause() == DamageCause.LIGHTNING)) return;
		Player p = (Player)e.getEntity();
		
		for(CustomPlayer cp : Main.players) {
			if(cp.getP() == p) {
				if(cp.getSuperPower() != 1) return;
				e.setCancelled(true);
			}
		}
	}
}
