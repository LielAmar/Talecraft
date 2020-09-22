package dev.lielamar.talecraft.listeners.globalquests.superheroes;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import dev.lielamar.talecraft.Main;
import dev.lielamar.talecraft.players.CustomPlayer;

public class OnCaptainMarvelSuperpower implements Listener {

	private Map<UUID, Long> cooldown = new HashMap<UUID, Long>();
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if(Main.getInstance().globalQuestManager.getQuest() != 2) return;
		
		if(e.getAction() != Action.LEFT_CLICK_AIR) return;
		if(e.getPlayer().getInventory().getItemInMainHand() == null || e.getPlayer().getInventory().getItemInMainHand().getType() == Material.AIR) {
			Player p = e.getPlayer();
			
			for(CustomPlayer cp : Main.players) {
				if(cp.getP() == p) {
					if(cp.getSuperPower() != 8) return;
					
					if(cooldown.containsKey(e.getPlayer().getUniqueId()) && System.currentTimeMillis()-cooldown.get(e.getPlayer().getUniqueId()) < 300) {
						e.getPlayer().sendMessage(ChatColor.RED + "You are still in cooldown!");
						return;
					}
					
					Location current = p.getLocation();
					Location eye = p.getTargetBlock(null, 200).getLocation();
					
					double x = current.getX();
					double y = current.getY()+1;
					double z = current.getZ();
					double xDiff = (eye.getX()-x)/50;
					double yDiff = (eye.getY()-y)/50;
					double zDiff = (eye.getZ()-z)/50;
					
					for(int i = 0; i < 50; i++) {
						
						Location newLoc = new Location(current.getWorld(), x, y, z);
						
						Particle.DustOptions dustOptions = new Particle.DustOptions(Color.RED, 1);
						current.getWorld().spawnParticle(Particle.REDSTONE, newLoc, 0, dustOptions);
						
						for(Entity ent : newLoc.getWorld().getNearbyEntities(newLoc, 0.5, 0.5, 0.5)) {
							if(ent instanceof LivingEntity) {
								if(ent != p) ((LivingEntity)ent).damage(4);
							}
						}
						
						x+=xDiff;
						y+=yDiff;
						z+=zDiff;
					}
					cooldown.put(e.getPlayer().getUniqueId(), System.currentTimeMillis());
				}
			}
		}
	}
}
