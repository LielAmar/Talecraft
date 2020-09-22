package dev.lielamar.talecraft.listeners.globalquests.superheroes;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import dev.lielamar.talecraft.Main;
import dev.lielamar.talecraft.players.CustomPlayer;

public class OnVisionSuperpower implements Listener {

	@EventHandler
	public void onTarget(EntityTargetEvent e) {
		if(Main.getInstance().globalQuestManager.getQuest() != 2) return;
		
		if(!(e.getTarget() instanceof Player)) return;
		Player p = (Player)e.getTarget();
		
		for(CustomPlayer cp : Main.players) {
			if(cp.getP() == p) {
				if(cp.getSuperPower() != 11) return;
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if(Main.getInstance().globalQuestManager.getQuest() != 2) return;
		
		if(!(e.getEntity() instanceof Player)) return;
		if(!(e.getCause() == DamageCause.ENTITY_ATTACK)) return;
		Player p = (Player)e.getEntity();
		
		for(CustomPlayer cp : Main.players) {
			if(cp.getP() == p) {
				if(cp.getSuperPower() != 11) return;
				e.setCancelled(true);
			}
		}
	}
}
