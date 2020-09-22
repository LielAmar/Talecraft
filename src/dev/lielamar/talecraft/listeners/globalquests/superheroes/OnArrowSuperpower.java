package dev.lielamar.talecraft.listeners.globalquests.superheroes;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.util.Vector;

import dev.lielamar.talecraft.Main;
import dev.lielamar.talecraft.players.CustomPlayer;

public class OnArrowSuperpower implements Listener {

	public OnArrowSuperpower() {
		if(Main.getInstance().globalQuestManager.getQuest() != 2) return;
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				for(Entity e : Bukkit.getWorld("world").getEntities()) {
					if(!(e instanceof Arrow)) continue;
					Arrow arrow = (Arrow)e;
					if(arrow.isOnGround()) continue;
					if(!(arrow.getShooter() instanceof Player)) continue;
					Player shooter = (Player) arrow.getShooter();
					for(CustomPlayer cp : Main.players) {
						if(cp.getP() == shooter) {
							if(cp.getSuperPower() != 6) return;
							for(Entity nearby : arrow.getNearbyEntities(3, 3, 3)) {
								if(nearby == arrow.getShooter()) continue;
								if(nearby.getType().isAlive()) {
									Location from = arrow.getLocation();
			                        Location to = nearby.getLocation();
			                        Vector vFrom = from.toVector();
			                        Vector vTo = to.toVector();
			                        Vector direction = vTo.subtract(vFrom).normalize();
			                        arrow.setVelocity(direction);
			                        break;
								}
							}
						}
					}
				}
			}
		}, 1, 1);
	}
}
