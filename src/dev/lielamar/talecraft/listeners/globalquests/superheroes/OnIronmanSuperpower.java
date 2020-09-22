package dev.lielamar.talecraft.listeners.globalquests.superheroes;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import dev.lielamar.talecraft.Main;
import dev.lielamar.talecraft.players.CustomPlayer;

public class OnIronmanSuperpower implements Listener {

	@EventHandler
	public void onDoubleJump(PlayerMoveEvent e) {
		if(Main.getInstance().globalQuestManager.getQuest() != 2) return;
		
		Player p = e.getPlayer();
		
		for(CustomPlayer cp : Main.players) {
			if(cp.getP() == p) {
				if(cp.getSuperPower() != 9) return;
				if(p.getGameMode() != GameMode.CREATIVE && p.getLocation().subtract(0, 1, 0).getBlock().getType() != Material.AIR && !p.isFlying()) {
					p.setAllowFlight(true);
				}
			}
		}
	}
	
	@EventHandler
	public void onFlight(PlayerToggleFlightEvent e) {
		if(Main.getInstance().globalQuestManager.getQuest() != 2) return;
		
		Player p = e.getPlayer();
		
		for(CustomPlayer cp : Main.players) {
			if(cp.getP() == p) {
				if(cp.getSuperPower() != 9) return;
				if(p.getGameMode() == GameMode.CREATIVE) return;
				
				e.setCancelled(true);
				p.setAllowFlight(false);
				p.setFlying(false);
				p.setVelocity(p.getLocation().getDirection().multiply(1.5).setY(1));
			}
		}
	}
}
