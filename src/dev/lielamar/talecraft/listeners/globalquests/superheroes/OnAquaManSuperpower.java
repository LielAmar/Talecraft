package dev.lielamar.talecraft.listeners.globalquests.superheroes;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import dev.lielamar.talecraft.Main;
import dev.lielamar.talecraft.players.CustomPlayer;

public class OnAquaManSuperpower implements Listener {

	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if(Main.getInstance().globalQuestManager.getQuest() != 2) return;
		
		if(!(e.getEntity() instanceof Player)) return;
		if(!(e.getCause() == DamageCause.DROWNING)) return;
		Player p = (Player)e.getEntity();
		
		for(CustomPlayer cp : Main.players) {
			if(cp.getP() == p) {
				if(cp.getSuperPower() != 5) return;
				e.setCancelled(true);
			}
		}
	}
	
}
