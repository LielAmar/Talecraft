package dev.lielamar.talecraft.listeners.globalquests.superheroes;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import dev.lielamar.talecraft.Main;
import dev.lielamar.talecraft.players.CustomPlayer;

public class OnCaptainAmericaSuperpower implements Listener {

	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if(Main.getInstance().globalQuestManager.getQuest() != 2) return;
		
		if(!(e.getEntity() instanceof Player)) return;
		if(!(e.getCause() == DamageCause.FALL)) return;
		Player p = (Player)e.getEntity();
		if(p.getInventory().getItemInOffHand().getType() != Material.SHIELD) return;
		
		for(CustomPlayer cp : Main.players) {
			if(cp.getP() == p) {
				if(cp.getSuperPower() != 2) return;
				e.setCancelled(true);
				p.playSound(p.getLocation(), Sound.ITEM_SHIELD_BLOCK, 2f, 2f);
			}
		}
	}
}
