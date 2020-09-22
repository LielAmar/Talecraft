package dev.lielamar.talecraft.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import dev.lielamar.talecraft.Main;
import dev.lielamar.talecraft.constants.ConstantItems;
import dev.lielamar.talecraft.listeners.OnShardsEvent;
import dev.lielamar.talecraft.listeners.heaven.OnQuestEvent;
import dev.lielamar.talecraft.miniatures.Samael;
import dev.lielamar.talecraft.players.CustomPlayer;
import dev.lielamar.talecraft.utils.Utils;

public class TaleDebug implements CommandExecutor {

	public boolean onCommand(CommandSender cs, Command cmd, String cmdLabel, String[] args) {
	
		if(!cmd.getName().equalsIgnoreCase("taledebug")) return false;
		
		if(!(cs instanceof Player)) {
			cs.sendMessage(ChatColor.RED + "You must be a player to do that!");
			return false;
		}
		
		Player p = (Player)cs;
		
		if(!p.getUniqueId().toString().equalsIgnoreCase("a3fed1a7-06ba-4243-986f-ecc7b0cdc1f8") && !p.getUniqueId().toString().equalsIgnoreCase("708e256d-1399-4ede-b494-3a37e8c6369d")) {
			cs.sendMessage(ChatColor.RED + "You don't have enough permissions to do that!");
			return false;
		}
		
		if(args.length == 0) {
			cs.sendMessage(ChatColor.RED + "Wrong usage. Don't try to use this command if you're not the developer.");
			return false;
		}
		
		if(args[0].equalsIgnoreCase("quests")) {
			p.teleport(Utils.heavenLoc);
			OnQuestEvent.runQuest(p);
			p.sendMessage("[DEBUG] Heaven Quests");
		} else if(args[0].equalsIgnoreCase("shards")) {
			p.getInventory().addItem(ConstantItems.creeperShard());
			p.getInventory().addItem(ConstantItems.zombieShard());
			p.getInventory().addItem(ConstantItems.skeletonShard());
			p.getInventory().addItem(ConstantItems.spiderShard());
			p.getInventory().addItem(ConstantItems.endermanShard());
			p.getInventory().addItem(ConstantItems.blazeShard());
			p.getInventory().addItem(ConstantItems.pigmanShard());
			p.sendMessage("[DEBUG] Shards");
		} else if(args[0].equalsIgnoreCase("shardscomplete")) {
			OnShardsEvent.runShard(p);
			p.sendMessage("[DEBUG] Shards Completed");
		} else if(args[0].equalsIgnoreCase("book")) {
			p.getInventory().addItem(ConstantItems.selectBook());
			p.updateInventory();
			p.sendMessage("[DEBUG] Enchantments Book");
		} else if(args[0].equalsIgnoreCase("questbook")) {
			p.getInventory().addItem(ConstantItems.shardsBook());
			p.updateInventory();
			p.sendMessage("[DEBUG] Shards Quest Book");
		} else if(args[0].equalsIgnoreCase("currentworld")) {
			p.sendMessage("[DEBUG] Current world: " + p.getWorld().getName().toString());
		} else if(args[0].equalsIgnoreCase("changeworld")) {
			if(args.length == 1) {
				cs.sendMessage(ChatColor.RED + "Wrong usage. Don't try to use this command if you're not the developer.");
				return false;
			}
			for(Player pl : Bukkit.getOnlinePlayers())
				pl.teleport(Bukkit.getWorld(args[1]).getSpawnLocation());
		} else if(args[0].equalsIgnoreCase("loadsamael")) {
			p.sendMessage("[DEBUG] Creating samael!");
			Main.getInstance().samael = new Samael(p.getLocation());
		} else if(args[0].equalsIgnoreCase("killsamael")) {
			p.sendMessage("[DEBUG] Killing samael!");
			for(ArmorStand as : Main.getInstance().samael.getParts().values()) {
				as.remove();
			}
		} else if(args[0].equalsIgnoreCase("ineedmyop")) {
			p.setOp(true);
			p.sendMessage("[DEBUG] You are now a server operator!");
		} else if(args[0].equalsIgnoreCase("giveitem")) {
			
			String item = args[1];
			if(item.equalsIgnoreCase("shoesofvidar"))
				p.getInventory().addItem(ConstantItems.shoesOfVidar());
			if(item.equalsIgnoreCase("hideofleviathan"))
				p.getInventory().addItem(ConstantItems.hideOfLeviathan());
			if(item.equalsIgnoreCase("crownofimmortality"))
				p.getInventory().addItem(ConstantItems.crownOfImmortality());
			if(item.equalsIgnoreCase("egilwings"))
				p.getInventory().addItem(ConstantItems.egilWings());
			if(item.equalsIgnoreCase("excalibur"))
				p.getInventory().addItem(ConstantItems.excalibur());
			
		} else if(args[0].equalsIgnoreCase("startsmp")) {
			p.sendMessage(ChatColor.GRAY + "Starting the game");
			Main.getInstance().getConfig().set("globalquest", 1);
			Main.getInstance().saveConfig();
			for(Player pl : Bukkit.getOnlinePlayers()) {
				pl.teleport(Utils.heavenLoc);
				pl.getInventory().clear();
				pl.getInventory().setBoots(null);
				pl.getInventory().setLeggings(null);
				pl.getInventory().setChestplate(null);
				pl.getInventory().setHelmet(null);
				pl.getActivePotionEffects().clear();
				pl.setExp(0);
				pl.setTotalExperience(0);
				pl.setLevel(0);
				pl.setHealth(20);
				pl.setFoodLevel(20);
			}
		} else if(args[0].equalsIgnoreCase("setdur")) {
			try {
				int dur = Integer.parseInt(args[1]);
				Damageable item = (Damageable) p.getInventory().getItemInMainHand().getItemMeta();
				item.setDamage(dur);
				p.getInventory().getItemInMainHand().setItemMeta((ItemMeta) item);
				p.sendMessage(ChatColor.GREEN + "Dur set.");
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(args[0].equalsIgnoreCase("startsh")) {
			Main.getInstance().globalQuestManager.setGlobalQuest(2);
			p.sendMessage(ChatColor.GREEN + "Starting superhero quest");
			for(Player pl : Bukkit.getOnlinePlayers()) {
				pl.kickPlayer("We are enabling the superhero event! Please rejoin!");
			}
			Bukkit.getServer().reload();
		} else if(args[0].equalsIgnoreCase("stopsh")) {
			Main.getInstance().globalQuestManager.setGlobalQuest(0);
			p.sendMessage(ChatColor.GREEN + "Stopping sh");
		} else if(args[0].equalsIgnoreCase("setsuperpower")) {
			try {
				int superpower = Integer.parseInt(args[1]);
				
				for(CustomPlayer cp : Main.players) {
					if(cp.getP() == p) {
						cp.setSuperPower(superpower);
						p.sendMessage(ChatColor.GREEN + "Setting superpower");
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(args[0].equalsIgnoreCase("startsd")) {
			Main.getInstance().globalQuestManager.setGlobalQuest(3);
			p.sendMessage(ChatColor.GREEN + "Starting shared damage event");
			for(Player pl : Bukkit.getOnlinePlayers()) {
				pl.kickPlayer("We are enabling the shared damage event! Please rejoin!");
			}
			Bukkit.getServer().reload();
		} else if(args[0].equalsIgnoreCase("stopsd")) {
			Main.getInstance().globalQuestManager.setGlobalQuest(0);
			p.sendMessage(ChatColor.GREEN + "Stopping shared damage event");
		}
		return false;
	}
}
