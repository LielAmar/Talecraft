package dev.lielamar.talecraft.listeners.globalquests.shareddamage;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import dev.lielamar.talecraft.Main;
import dev.lielamar.talecraft.constants.Constants;

public class OnSharedDamage implements Listener {

	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {
			if(!Constants.isYoutuber(((Player)e.getEntity()).getUniqueId())) return;
			if(Main.getInstance().globalQuestManager.getQuest() == 3) {
				for(Player pl : Bukkit.getOnlinePlayers()) {
					if(Constants.isYoutuber(pl.getUniqueId())) {
						pl.damage(e.getDamage()/2);
					}
				}
			}
		}
	}
}
