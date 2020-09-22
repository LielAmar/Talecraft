package dev.lielamar.talecraft.listeners;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class OnChatEvent implements Listener {

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		if(p.getGameMode() == GameMode.SPECTATOR) {
			e.setCancelled(true);
			p.sendMessage(ChatColor.RED + "!הפוצ רותב טא'צב רבדל לוכי אל התא");
		} else
			e.setFormat(ChatColor.RED + "[" + ChatColor.RESET + "YT" + ChatColor.RED + "] " + ChatColor.RESET + "%1$s: %2$s");
	}
}
