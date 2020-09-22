package dev.lielamar.talecraft.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import dev.lielamar.talecraft.constants.Constants;
import dev.lielamar.talecraft.utils.Utils;

public class OnRespawnEvent implements Listener {

	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		
		if(!Constants.isYoutuber(p.getUniqueId()))
			Utils.setSpectator(p);
		
//		Utils.setupHeavenLoc();
//		
//		Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
//			
//			@Override
//			public void run() {
//				p.teleport(Utils.heavenLoc);
//			}
//		}, 2L);
	}	
}
