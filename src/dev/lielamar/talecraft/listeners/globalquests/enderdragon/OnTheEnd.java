package dev.lielamar.talecraft.listeners.globalquests.enderdragon;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World.Environment;
import org.bukkit.WorldCreator;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.potion.PotionEffect;

import dev.lielamar.talecraft.Main;
import dev.lielamar.talecraft.constants.Constants;
import dev.lielamar.talecraft.utils.Utils;

public class OnTheEnd implements Listener {

	@EventHandler
	public void onJoin(PlayerChangedWorldEvent e) {
		if(e.getPlayer().getWorld().getEnvironment() == Environment.THE_END) {
			e.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "Ender Dragon: " + ChatColor.GRAY + "!המויא תועט םתישע ,אה אה אה");
		} else if(e.getPlayer().getWorld().getName().toLowerCase().contains("heaven")) {
			e.getPlayer().sendMessage(ChatColor.AQUA + "Samael: " + ChatColor.GRAY + "!יל סאמנ ,הז והז !?םתא בוש");
			if(Bukkit.getWorld("newworld") == null) {
				Bukkit.createWorld(new WorldCreator("newworld").environment(Environment.NORMAL));
				Bukkit.createWorld(new WorldCreator("newworld_nether").environment(Environment.NETHER));
				Bukkit.createWorld(new WorldCreator("newworld_the_end").environment(Environment.NETHER));
			}
		}
	}
	
	@EventHandler
	public void onDeath(EntityDamageEvent e) {
		if(!(e.getEntity() instanceof Player)) return;
		Player p = (Player)e.getEntity();
		
		if(!(p.getWorld().getEnvironment() == Environment.THE_END)) return;
		
		if(e.getCause() == DamageCause.DRAGON_BREATH || e.getCause() == DamageCause.CONTACT || e.getCause() == DamageCause.ENTITY_EXPLOSION || e.getCause() == DamageCause.ENTITY_ATTACK) {
			e.setDamage(30);
		}
		
		if(p.getHealth()-e.getDamage() > 0) return;
		
		e.setDamage(0);
		
		Utils.setupHeavenLoc();
		
		Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				for(Player pl : Bukkit.getOnlinePlayers()) {
					if(!Constants.isYoutuber(pl.getUniqueId())) continue;
					pl.damage(0);
					pl.getInventory().clear();
					pl.setExp(0);
					pl.setLevel(0);
					pl.setHealth(20);
					pl.setAbsorptionAmount(0);
					pl.setFoodLevel(20);
					for(PotionEffect pe : pl.getActivePotionEffects())
						pl.removePotionEffect(pe.getType());
					pl.teleport(Utils.heavenLoc);
				}
			}
		}, 2L);
	}	
	
	@EventHandler
	public void onDragonDamage(EntityDamageEvent e) {
		if(!(e.getEntity() instanceof EnderDragon)) return;
		e.setDamage(0);
	}
}