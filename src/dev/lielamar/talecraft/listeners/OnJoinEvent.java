package dev.lielamar.talecraft.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;

import dev.lielamar.talecraft.Main;
import dev.lielamar.talecraft.constants.Constants;
import dev.lielamar.talecraft.players.CustomPlayer;
import dev.lielamar.talecraft.utils.Utils;

public class OnJoinEvent implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if(p == null) return;
		
		p.setPlayerListHeaderFooter(ChatColor.GRAY + "!" + ChatColor.AQUA + "Talecraft" + ChatColor.GRAY + " ילארשיה םירבויטויה תרשב םיקחשמ םתא",
				ChatColor.GRAY + "!" + ChatColor.AQUA + "Talecraft.co.il " + ChatColor.GRAY + ":ב ורקב םירבויטויהו הנועה ,תרשה תודוא עדימה לכל"
						+ "\n" + ChatColor.GRAY + "!" + ChatColor.AQUA + "Play-IL.co.il " + ChatColor.GRAY + "תוסחב תרשה");

		boolean found = Constants.isYoutuber(p.getUniqueId());
		
		if(Bukkit.getOnlinePlayers().size() > Constants.MAX_PLAYERS+1) {
			if(!found)
				p.kickPlayer(ChatColor.RED + "!אלמ אוהש ןוויכמ עגרכ תרשל ףרטצהל ןתינ אל");
		}
		
		if(!found) {
			Utils.setSpectator(p);
			e.setJoinMessage(null);
		} else {
			CustomPlayer cp = new CustomPlayer(p);
			Main.players.add(cp);
			
			if(!cp.getReseted()) {
				cp.setReseted(true);
				p.teleport(Bukkit.getServer().getWorlds().get(0).getSpawnLocation());
				p.setExp(0);
				p.setLevel(0);
				p.setFoodLevel(20);
				p.setHealth(20);
				p.setAbsorptionAmount(0);
				for(PotionEffect pet : p.getActivePotionEffects())
					p.removePotionEffect(pet.getType());
				p.getInventory().clear();
				p.getEnderChest().clear();
				p.getInventory().setBoots(null);
				p.getInventory().setLeggings(null);
				p.getInventory().setChestplate(null);
				p.getInventory().setHelmet(null);
				p.updateInventory();
				p.sendMessage(ChatColor.GRAY + "!?םלועה הפיא !?הפ הרוק המ ...עגר");
			}
			
			Main.getInstance().globalQuestManager.setupPlayerQuest(p, cp);
			
			e.setJoinMessage(ChatColor.RED + p.getName() + ChatColor.GRAY + " joined the game");
			for(Player pl : Bukkit.getOnlinePlayers()) {
				if(!Constants.isYoutuber(pl.getUniqueId()))
					p.hidePlayer(Main.getInstance(), pl);
			}
			Utils.setAboveHeadName("create", p);
		}
	}
}
