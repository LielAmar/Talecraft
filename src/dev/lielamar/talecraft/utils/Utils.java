package dev.lielamar.talecraft.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import dev.lielamar.talecraft.Main;
import dev.lielamar.talecraft.commands.StreamingUtils;
import dev.lielamar.talecraft.constants.Constants;

@SuppressWarnings("deprecation")
public class Utils {

	public static Map<UUID, UUID> spectatorsList = new HashMap<UUID, UUID>();
	
	private static final String[] spectatorMessage = {
			ChatColor.GRAY + "========== " + ChatColor.AQUA + "TaleCraft" + ChatColor.GRAY + " ==========",
			ChatColor.GRAY + "!TaleCraft ילארשיה םירבויטויה תרשל םיאבה םיכורב",
			ChatColor.GRAY + "!תמא ןמזב םיקחשמ םכילע םיבוהאה םירבויטויב תופצל םכתורשפאב",
			ChatColor.GRAY + "/Spectate <רבויטויה םש> :הדוקפה תא ומשר רבויטויל רגתשהל תנמ לע"
			};

	public static void setupHeavenLoc() {
		heavenLoc = new Location(
				Bukkit.getWorld(Main.getInstance().getConfig().getString("heaven.world")),
				Main.getInstance().getConfig().getDouble("heaven.x"),
				Main.getInstance().getConfig().getDouble("heaven.y"),
				Main.getInstance().getConfig().getDouble("heaven.z"),
				Main.getInstance().getConfig().getLong("heaven.yaw"),
				Main.getInstance().getConfig().getLong("heaven.pitch"));
	}
	
	public static Location heavenLoc = new Location(
			Bukkit.getWorld(Main.getInstance().getConfig().getString("heaven.world")),
			Main.getInstance().getConfig().getDouble("heaven.x"),
			Main.getInstance().getConfig().getDouble("heaven.y"),
			Main.getInstance().getConfig().getDouble("heaven.z"),
			Main.getInstance().getConfig().getLong("heaven.yaw"),
			Main.getInstance().getConfig().getLong("heaven.pitch"));
	
	public static void setSpectator(Player p) {
		
		if(StreamingUtils.streaming)
			p.kickPlayer(ChatColor.GRAY + "!תופצל תכלל םינמזומ !ולש ץורעב יח רודישב " + ChatColor.AQUA + Main.getInstance().getConfig().getString("streaming.youtuber"));
		
		p.setGameMode(GameMode.SPECTATOR);
		p.sendMessage(spectatorMessage);
		spectatorsList.put(p.getUniqueId(), getOnlineYoutuber());
		
		for(UUID u : Constants.youtubers) {
			Player yt = Bukkit.getPlayer(u);
			if(yt != null)
				yt.hidePlayer(Main.getInstance(), p);
		}
	}
	
	public static void setAboveHeadName(String action, Player p) {
		p.setPlayerListName(ChatColor.RED + "[" + ChatColor.RESET + "YT" + ChatColor.RED + "] " + ChatColor.RESET + p.getName());

		Scoreboard sb = p.getScoreboard();
		if (sb.getTeam(p.getName() + "1") == null) 
			sb.registerNewTeam(p.getName() + "1");
		Team team = sb.getTeam(p.getName() + "1");
		team.setPrefix(ChatColor.RED + "[" + ChatColor.RESET + "YT" + ChatColor.RED + "] " + ChatColor.RESET);
		team.setNameTagVisibility(NameTagVisibility.ALWAYS);
			
		switch (action) {
			case "create":
				team.addEntry(p.getName());
				break;
			case "update":
				team.unregister();
				sb.registerNewTeam(p.getName() + "1");
				team = sb.getTeam(p.getName() + "1");
				team.setPrefix(ChatColor.RED + "[" + ChatColor.RESET + "YT" + ChatColor.RED + "] " + ChatColor.RESET);
				team.setNameTagVisibility(NameTagVisibility.ALWAYS);
				team.addEntry(p.getName());
				break;
			case "destroy":
				team.unregister();
				break;
		}
	}
	
	public static UUID getOnlineYoutuber() {
		for(Player pl : Bukkit.getOnlinePlayers()) {
			if(Constants.isYoutuber(pl.getUniqueId()))
				return pl.getUniqueId();
		}
		return null;
	}
}
