package dev.lielamar.talecraft.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;

import dev.lielamar.talecraft.Main;
import dev.lielamar.talecraft.constants.BedStatus;
import dev.lielamar.talecraft.constants.Constants;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.TextComponent;

public class OnBedEvent implements Listener {

	public static BedStatus bedStatus = null;
	
	@EventHandler
	public void onBed(PlayerBedEnterEvent e) {
		
		if(!e.getBedEnterResult().toString().toLowerCase().contains("ok")) return;
		
		TextComponent accept = new TextComponent(ChatColor.GREEN + "" + ChatColor.BOLD + "[ACCEPT] ");
		accept.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/bed accept"));
		TextComponent deny = new TextComponent(ChatColor.RED + "" + ChatColor.BOLD + "[DENY]");
		deny.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/bed deny"));
		
		accept.addExtra(deny);
		
		for(Player pl : Bukkit.getOnlinePlayers()) {
			if(Constants.isYoutuber(pl.getUniqueId())) {
				bedStatus = new BedStatus(e.getBed().getWorld().getTime(), System.currentTimeMillis(), 0, 0);
				pl.sendMessage(ChatColor.AQUA + e.getPlayer().getName() + ChatColor.GRAY + " wants to skip the night");
				pl.spigot().sendMessage(accept);
			}
		}
		
		Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
			public void run() {
				if(e.getPlayer().isSleeping()) {
					double oldHealth = e.getPlayer().getHealth();
					e.getPlayer().damage(1);
			        e.getPlayer().setHealth(oldHealth);
				}
			}
		}, 100L);
	}
	
	@EventHandler
	public void onBedLeave(PlayerBedLeaveEvent e) {
		if(bedStatus.getAccept() < bedStatus.getDeny()) {
			e.getBed().getWorld().setTime(bedStatus.getCurrentWorldTime());
			Bukkit.broadcastMessage(ChatColor.RED + "Keeping the night...");
		} else {
			e.getBed().getWorld().setTime(0);
			Bukkit.broadcastMessage(ChatColor.GREEN + "Skipping the night...");
		}
	}
}
