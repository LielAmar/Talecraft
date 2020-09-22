package dev.lielamar.talecraft.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class OnServerPing implements Listener {

	@EventHandler
    public void on(ServerListPingEvent e) {
        e.setMotd(ChatColor.AQUA + "" + ChatColor.BOLD + "          Talecraft  " + ChatColor.GRAY + "(Play-IL)" + "\n"
        		+ ChatColor.YELLOW + "          It's time for something new");
    }
}
