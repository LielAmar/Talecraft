package dev.lielamar.talecraft.listeners;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import dev.lielamar.talecraft.constants.ConstantItems;
import net.citizensnpcs.api.event.NPCRightClickEvent;

public class OnShardsEvent implements Listener {

	public static final String prefix = ChatColor.RED + "Lucifer: ";
	
	@EventHandler
	public void onNPC(NPCRightClickEvent e) {
		Player p = (Player)e.getClicker();
		
		if(e.getNPC().getName().toLowerCase().contains("lucifer"))
			runShard(p);
	}
	
	@EventHandler
	public void onMobDeath(EntityDeathEvent e) {
		if(e.getEntity().getWorld().getName().toLowerCase().contains("heaven")) return;
		
		Random r = new Random();
		if(e.getEntity().getType() == EntityType.CREEPER) {
			int chance = r.nextInt(100+1+1)+1;
			if(chance==1) {
				e.getDrops().add(ConstantItems.creeperShard());
			}
		} else if(e.getEntity().getType() == EntityType.ZOMBIE) {
			int chance = r.nextInt(100+1+1)+1;
			if(chance==1) {
				e.getDrops().add(ConstantItems.zombieShard());
			}
		} else if(e.getEntity().getType() == EntityType.SKELETON) {
			int chance = r.nextInt(100+1+1)+1;
			if(chance==1) {
				e.getDrops().add(ConstantItems.skeletonShard());
			}
		} else if(e.getEntity().getType() == EntityType.SPIDER) {
			int chance = r.nextInt(100+1+1)+1;
			if(chance==1) {
				e.getDrops().add(ConstantItems.spiderShard());
			}
		} else if(e.getEntity().getType() == EntityType.ENDERMAN) {
			int chance = r.nextInt(100+1+1)+1;
			if(chance==1) {
				e.getDrops().add(ConstantItems.endermanShard());
			}
		} else if(e.getEntity().getType() == EntityType.BLAZE) {
			int chance = r.nextInt(100+1+1)+1;
			if(chance==1) {
				e.getDrops().add(ConstantItems.blazeShard());
			}
		} else if(e.getEntity().getType() == EntityType.PIG_ZOMBIE) {
			int chance = r.nextInt(100+1+1)+1;
			if(chance==1) {
				e.getDrops().add(ConstantItems.pigmanShard());
			}
		}
	}
	
	public static void runShard(Player p) {
		int creeperShard = -1;
		int zombieShard = -1;
		int skeletonShard = -1;
		int spiderShard = -1;
		int endermanShard = -1;
		int blazeShard = -1;
		int pigmanShard = -1;
		
		for(int i=0; i<p.getInventory().getSize();i++) {
			if(p.getInventory().getItem(i) == null || p.getInventory().getItem(i).getType() == Material.AIR) continue;
			
			if(p.getInventory().getItem(i).getType() == ConstantItems.creeperShard().getType()
					&& p.getInventory().getItem(i).hasItemMeta() && p.getInventory().getItem(i).getItemMeta().hasDisplayName()
					&& p.getInventory().getItem(i).getItemMeta().getDisplayName().equals(ConstantItems.creeperShard().getItemMeta().getDisplayName()))
				creeperShard = i;
			else if(p.getInventory().getItem(i).getType() == ConstantItems.zombieShard().getType()
					&& p.getInventory().getItem(i).hasItemMeta() && p.getInventory().getItem(i).getItemMeta().hasDisplayName()
					&& p.getInventory().getItem(i).getItemMeta().getDisplayName().equals(ConstantItems.zombieShard().getItemMeta().getDisplayName()))
				zombieShard = i;
			else if(p.getInventory().getItem(i).getType() == ConstantItems.skeletonShard().getType()
					&& p.getInventory().getItem(i).hasItemMeta() && p.getInventory().getItem(i).getItemMeta().hasDisplayName()
					&& p.getInventory().getItem(i).getItemMeta().getDisplayName().equals(ConstantItems.skeletonShard().getItemMeta().getDisplayName()))
				skeletonShard = i;
			else if(p.getInventory().getItem(i).getType() == ConstantItems.spiderShard().getType()
					&& p.getInventory().getItem(i).hasItemMeta() && p.getInventory().getItem(i).getItemMeta().hasDisplayName()
					&& p.getInventory().getItem(i).getItemMeta().getDisplayName().equals(ConstantItems.spiderShard().getItemMeta().getDisplayName()))
				spiderShard = i;
			else if(p.getInventory().getItem(i).getType() == ConstantItems.endermanShard().getType()
					&& p.getInventory().getItem(i).hasItemMeta() && p.getInventory().getItem(i).getItemMeta().hasDisplayName()
					&& p.getInventory().getItem(i).getItemMeta().getDisplayName().equals(ConstantItems.endermanShard().getItemMeta().getDisplayName()))
				endermanShard = i;
			else if(p.getInventory().getItem(i).getType() == ConstantItems.blazeShard().getType()
					&& p.getInventory().getItem(i).hasItemMeta() && p.getInventory().getItem(i).getItemMeta().hasDisplayName()
					&& p.getInventory().getItem(i).getItemMeta().getDisplayName().equals(ConstantItems.blazeShard().getItemMeta().getDisplayName()))
				blazeShard = i;
			else if(p.getInventory().getItem(i).getType() == ConstantItems.pigmanShard().getType()
					&& p.getInventory().getItem(i).hasItemMeta() && p.getInventory().getItem(i).getItemMeta().hasDisplayName()
					&& p.getInventory().getItem(i).getItemMeta().getDisplayName().equals(ConstantItems.pigmanShard().getItemMeta().getDisplayName()))
				pigmanShard = i;
		}
		
		if(creeperShard == -1 || zombieShard == -1 || skeletonShard == -1 || spiderShard == -1
				|| endermanShard == -1 || blazeShard == -1 || pigmanShard == -1) {
			p.sendMessage(prefix + ChatColor.GRAY + "!םישרדנה םיקלחה תעבש לכ תא ךל ןיא");
			return;
		} 
		
		p.getInventory().getItem(creeperShard).setAmount(p.getInventory().getItem(creeperShard).getAmount()-1);
		p.getInventory().getItem(skeletonShard).setAmount(p.getInventory().getItem(skeletonShard).getAmount()-1);
		p.getInventory().getItem(zombieShard).setAmount(p.getInventory().getItem(zombieShard).getAmount()-1);
		p.getInventory().getItem(spiderShard).setAmount(p.getInventory().getItem(spiderShard).getAmount()-1);
		p.getInventory().getItem(endermanShard).setAmount(p.getInventory().getItem(endermanShard).getAmount()-1);
		p.getInventory().getItem(blazeShard).setAmount(p.getInventory().getItem(blazeShard).getAmount()-1);
		p.getInventory().getItem(pigmanShard).setAmount(p.getInventory().getItem(pigmanShard).getAmount()-1);
		p.updateInventory();
		
		giveBook(p);
	}
	
	public static void giveBook(Player p) {
		p.sendMessage(OnShardsEvent.prefix + ChatColor.GRAY + ".ינממ הנטק הנתמ הנה .דואמ הפי ,ממה");
		
		p.getInventory().addItem(ConstantItems.selectBook());
		return;
	}
}
