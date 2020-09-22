package dev.lielamar.talecraft.listeners.globalquests.superheroes;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import dev.lielamar.talecraft.Main;
import dev.lielamar.talecraft.players.CustomPlayer;

public class OnDeadpoolSuperpower implements Listener {

	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if(Main.getInstance().globalQuestManager.getQuest() != 2) return;
		
		if(!(e.getEntity() instanceof Player)) return;
		Player p = (Player)e.getEntity();
		
		for(CustomPlayer cp : Main.players) {
			if(cp.getP() == p) {
				if(cp.getSuperPower() != 7) return;
				if(p.getHealth()-e.getDamage() <= 0) {
					e.setCancelled(true);
					p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 30*20, 1));
					p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 30*20, 1));
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_HURT, 2f, 2f);
				}
			}
		}
	}
}
