package dev.lielamar.talecraft.listeners.globalquests.superheroes;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import dev.lielamar.talecraft.Main;
import dev.lielamar.talecraft.players.CustomPlayer;

public class OnFlashSuperpower implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		e.getPlayer().setWalkSpeed(0.2f);
		if(Main.getInstance().globalQuestManager.getQuest() != 2) return;
	
		for(CustomPlayer cp : Main.players) {
			if(cp.getP() == e.getPlayer()) {
				if(cp.getSuperPower() != 3) return;
				e.getPlayer().setWalkSpeed(0.4f);
			}
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		e.getPlayer().setWalkSpeed(0.2f);
	}
}
