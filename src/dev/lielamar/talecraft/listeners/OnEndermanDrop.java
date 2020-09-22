package dev.lielamar.talecraft.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class OnEndermanDrop implements Listener {

	@EventHandler
	public void onEndermanDrop(EntityDeathEvent e) {
//		if(!e.getEntity().getWorld().getName().toLowerCase().contains("heaven")) return;
//		if(!(e.getEntity().getType() == EntityType.ENDERMAN)) return;
//		
//		Random r = new Random();
//		int i = r.nextInt(2+1+1)+1;
//		
//		if(i==2)
//			e.getDrops().clear();
	}
}
