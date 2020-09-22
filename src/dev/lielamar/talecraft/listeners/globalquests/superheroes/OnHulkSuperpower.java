package dev.lielamar.talecraft.listeners.globalquests.superheroes;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import dev.lielamar.talecraft.Main;
import dev.lielamar.talecraft.players.CustomPlayer;

public class OnHulkSuperpower implements Listener {

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		if(Main.getInstance().globalQuestManager.getQuest() != 2) return;
		if(!(e.getDamager() instanceof Player)) return;
		Player p = (Player)e.getDamager();
		for(CustomPlayer cp : Main.players) {
			if(cp.getP() == p) {
				if(cp.getSuperPower() != 4) return;
				e.setDamage(e.getDamage()*2);
			}
		}
	}
}
