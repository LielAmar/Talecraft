package dev.lielamar.talecraft.listeners.heaven;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import dev.lielamar.talecraft.Main;
import dev.lielamar.talecraft.constants.ConstantItems;
import dev.lielamar.talecraft.constants.Constants;
import dev.lielamar.talecraft.players.CustomPlayer;

public class OnQuestEvent implements Listener {

	private static final String prefix = ChatColor.AQUA + "Samael: ";
	
	@EventHandler
	public void onArmorstandClick(PlayerInteractEvent e) {
		if(!e.getPlayer().getWorld().getName().toLowerCase().contains("heaven")) return;
		
		if(!Constants.isYoutuber(e.getPlayer().getUniqueId())) return;

		if(e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_BLOCK) {
			if(e.getClickedBlock().getType() == Material.ARMOR_STAND) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onEntityDestory(EntityDamageEvent e) {
		if(!e.getEntity().getWorld().getName().toLowerCase().contains("heaven")) return;
		
		if(e.getEntity() instanceof ArmorStand) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onArmorstandClick2(PlayerInteractAtEntityEvent e) {
		if(!e.getPlayer().getWorld().getName().toLowerCase().contains("heaven")) return;
		
		if(!Constants.isYoutuber(e.getPlayer().getUniqueId())) return;
		
		if(e.getRightClicked() instanceof ArmorStand) {
			e.setCancelled(true);
			
			ArmorStand as = (ArmorStand) e.getRightClicked();
			if(as.getCustomName().toLowerCase().contains("samael")) {

				for(Player pl : Bukkit.getOnlinePlayers()) {
					if(Constants.isYoutuber(pl.getUniqueId())) {
						pl.sendMessage(prefix + ChatColor.GRAY + "!םכלש חקלה תא ודמלתש ןמזה עיגה .םכמ יל סאמנ");
					}
				}
				
				new BukkitRunnable() {
					@Override
					public void run() {
						for(Player pl : Bukkit.getOnlinePlayers()) {
							if(Constants.isYoutuber(pl.getUniqueId())) {
								pl.sendMessage(prefix + ChatColor.GRAY + "!וישכעמ ילש היהי םכלש םלועה");
							}
						}
					}
				}.runTaskLater(Main.getInstance(), 40L);
				
				new BukkitRunnable() {
					@Override
					public void run() {
						for(Player pl : Bukkit.getOnlinePlayers()) {
							if(Constants.isYoutuber(pl.getUniqueId())) {
								pl.sendMessage(prefix + ChatColor.GRAY + "!תוארתהל אלו םולש .ילש היעבה ויהת אל רתוי םתא");
							}
						}
					}
				}.runTaskLater(Main.getInstance(), 100L);
				
				new BukkitRunnable() {
					@Override
					public void run() {
						for(Player pl : Bukkit.getOnlinePlayers()) {
							if(Constants.isYoutuber(pl.getUniqueId())) {
								pl.teleport(Bukkit.getWorld("newworld").getSpawnLocation());
							}
						}
					}
				}.runTaskLater(Main.getInstance(), 160L);
				
				// TODO: HERE I ADD NEW GLOBAL QUESTS
//				if(Main.getInstance().getConfig().getInt("globalquest") == 1) {
//					runFirstGlobalQuest(e.getPlayer());
//				} else {
//					runQuest(e.getPlayer());
//				}
			}
		}
	}
	
	// ================= GLOBAL QUEST 1: FIRE CHARGE ================= \\
	public static void runFirstGlobalQuest(Player p) {
		boolean completed = checkFirstGlobalProgress(p);
		
		if(completed) {
			// end quest
			for(Player pl : Bukkit.getOnlinePlayers()) {
				if(Constants.isYoutuber(pl.getUniqueId())) {
					pl.getInventory().clear();
					pl.getInventory().setBoots(null);
					pl.getInventory().setLeggings(null);
					pl.getInventory().setChestplate(null);
					pl.getInventory().setHelmet(null);
					pl.getActivePotionEffects().clear();
					pl.setExp(0);
					pl.setLevel(0);
					pl.setTotalExperience(0);
					pl.setHealth(20);
					pl.setFoodLevel(20);
					pl.updateInventory();
					pl.getInventory().addItem(ConstantItems.shardsBook());
					pl.teleport(Bukkit.getWorld("world").getSpawnLocation());
				}
			}
			Main.getInstance().getConfig().set("globalquest", -1);
			Main.getInstance().saveConfig();
		} else {
			sendFirstGlobalMessage(p);
		}
	}
	
	public static boolean checkFirstGlobalProgress(Player p) {
		
		boolean fireCharge = false;
		boolean endCrystal = false;
		
		for(int i = 0; i < p.getInventory().getSize(); i++) {
			if(p.getInventory().getItem(i) == null || p.getInventory().getItem(i).getType() == Material.AIR) continue;
			if(p.getInventory().getItem(i).getType() == Material.FIRE_CHARGE)
				fireCharge = true;
			if(p.getInventory().getItem(i).getType() == Material.END_CRYSTAL)
				endCrystal = true;
		}	

		return (endCrystal && fireCharge);
	}
	
	public static void sendFirstGlobalMessage(Player p) {
		p.sendMessage(prefix + ChatColor.GRAY + "ירובע והשמ תושעל וכרטצת ,היינש תונמדזה םיצור םתא םא");
		
		Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getInstance(), new Runnable() {
			@Override
			public void run() {
				p.sendMessage(prefix + ChatColor.GRAY + "שדחמ םלועה תא רוציל ךירצ ינא ,םתתמו רחאמ");	
			}
		}, 60L);
		
		Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getInstance(), new Runnable() {
			@Override
			public void run() {
				p.sendMessage(prefix + ChatColor.GRAY + "!End Crystal-ו Fire Charge יל ואיבתש ךרטצא ,תאז תושעל לכואש תנמ לע");
			}
		}, 120L);
	}
	
	
	
	
	// ================= PERSONAL QUESTS: ALL 18 PERSONAL QUESTS ================= \\
	public static void runQuest(Player p) {
		CustomPlayer cp = null;
		for(CustomPlayer cpTmp : Main.players) {
			if(cpTmp.getP().getUniqueId().toString().equalsIgnoreCase(p.getUniqueId().toString())) {
				cp = cpTmp;
			}
		}
		
		if(cp == null) {
			p.sendMessage(ChatColor.RED + "Something went wrong and we can't give you a quest. Please contact the developer.");
			return;
		}
		
		int currentQuest = cp.getCurrentQuest();
		
		if(currentQuest == -1) {
			
			// Chances the player will go back to life
			Random r = new Random();
			int chances = r.nextInt(2+1+1)+1;
			if(chances == 1) {
				// Revive
				p.sendMessage(prefix + ChatColor.GRAY + "!החלצהב .םייחל ךתוא ריזחהל יתרחב");
				cp.setCurrentQuest(-1);
				p.getInventory().clear();
				p.getInventory().setBoots(null);
				p.getInventory().setLeggings(null);
				p.getInventory().setChestplate(null);
				p.getInventory().setHelmet(null);
				p.getActivePotionEffects().clear();
				p.setExp(0);
				p.setLevel(0);
				p.setTotalExperience(0);
				p.setHealth(20);
				p.setFoodLevel(20);
				p.updateInventory();
				
				if(p.getBedSpawnLocation() == null)
					p.teleport(Bukkit.getWorld("world").getSpawnLocation());
				else
					p.teleport(p.getBedSpawnLocation());
			} else {				
				// Give quest
				int id = cp.getAvailableQuest();
				cp.updateQuest(id, true);
				sendQuestMessageById(p, id);
			}
		} else {
			// Has quest
			
			// If completed
			boolean completed = checkProgress(p, currentQuest);
			
			if(completed) {
				// end quest
				cp.setCurrentQuest(-1);
				p.getInventory().clear();
				p.getInventory().setBoots(null);
				p.getInventory().setLeggings(null);
				p.getInventory().setChestplate(null);
				p.getInventory().setHelmet(null);
				p.getActivePotionEffects().clear();
				p.setExp(0);
				p.setLevel(0);
				p.setTotalExperience(0);
				p.setHealth(20);
				p.setFoodLevel(20);
				p.updateInventory();
				
				if(p.getBedSpawnLocation() == null)
					p.teleport(Bukkit.getWorld("world").getSpawnLocation());
				else
					p.teleport(p.getBedSpawnLocation());				
			} else {
				// repeat quest
				sendQuestMessageById(p, currentQuest);
			}
		}
	}
	
	public static void sendQuestMessageById(Player p, int id) {
		switch(id) {
			case 1:
				p.sendMessage(prefix + ChatColor.GRAY + "!דחא דנומייד גישהל איה ךלש המישמה");
				break;
			case 2:
				p.sendMessage(prefix + ChatColor.GRAY + "!Stone Cobble לש םיקאטס 15 יל איבהל איה ךלש המישמה");
				break;
			case 3:
				p.sendMessage(prefix + ChatColor.GRAY + "!Flesh Rotten 20 יל איבהל איה ךלש המישמה");
				break;
			case 4:
				p.sendMessage(prefix + ChatColor.GRAY + "!Bones 20 יל איבהל איה ךלש המישמה");
				break;
			case 5:
				p.sendMessage(prefix + ChatColor.GRAY + "!םיקייטס 20 יל איבהל איה ךלש המישמה");
				break;
			case 6:
				p.sendMessage(prefix + ChatColor.GRAY + "!תופוע 20 יל איבהל איה ךלש המישמה");
				break;
			case 7:
				p.sendMessage(prefix + ChatColor.GRAY + "!ןורייא 64 יל איבהל איה ךלש המישמה");
				break;
			case 8:
				p.sendMessage(prefix + ChatColor.GRAY + "!Wood Log 256 יל איבהל איה ךלש המישמה");
				break;
			case 9:
				p.sendMessage(prefix + ChatColor.GRAY + "!לרפרדנא גישהל איה ךלש המישמה");
				break;
			case 10:
				p.sendMessage(prefix + ChatColor.GRAY + "!Haybales 8 גישהל איה ךלש המישמה");
				break;
			case 11:
				p.sendMessage(prefix + ChatColor.GRAY + "!דחא ןאידיסבוא גישהל איה ךלש המישמה");
				break;
			case 12:
				p.sendMessage(prefix + ChatColor.GRAY + "!ןפצמ גישהל איה ךלש המישמה");
				break;
			case 13:
				p.sendMessage(prefix + ChatColor.GRAY + "!ןועש גישהל איה ךלש המישמה");
				break;
			case 14:
				p.sendMessage(prefix + ChatColor.GRAY + "!Pufferfish םע ילד גישהל איה ךלש המישמה");
				break;
			case 15:
				p.sendMessage(prefix + ChatColor.GRAY + "!הנוש גוסמ אוה קאטס לכשכ Wood Logs לש םיקאטס 4 גישהל איה ךלש המישמה");
				break;
			case 16:
				p.sendMessage(prefix + ChatColor.GRAY + "!Golden Apple גישהל איה ךלש המישמה");
				break;
			case 17:
				p.sendMessage(prefix + ChatColor.GRAY + "!הגוע גישהל איה ךלש המישמה");
				break;
			case 18:
				p.sendMessage(prefix + ChatColor.GRAY + "!םיסוטקק 64 גישהל איה ךלש המישמה");
				break;
			default:
				p.sendMessage(ChatColor.RED + "Something went wrong and we can't give you a quest. Please contact the developer.");
				return;
		}
		
		p.sendMessage(prefix + ChatColor.GRAY + "!םטייאה תא יל תתלו יילא רוזחל שי םישרדנה םימטייאה תא םכל שיש עגרב");
	}
	
	public static boolean checkProgress(Player p, int id) {
		if(id == 1) {
			// להשיג דיימונד אחד
			for(int i = 0; i < p.getInventory().getSize(); i++) {
				if(p.getInventory().getItem(i) == null || p.getInventory().getItem(i).getType() == Material.AIR) continue;
				if(p.getInventory().getItem(i).getType() == Material.DIAMOND)
					return true;
			}
		} else if(id == 2) {
			// להשיג 15 סטאקים של קאבל
			int counter = 0;
			for(int i = 0; i < p.getInventory().getSize(); i++) {
				if(p.getInventory().getItem(i) == null || p.getInventory().getItem(i).getType() == Material.AIR) continue;
				if(p.getInventory().getItem(i).getType() == Material.COBBLESTONE)
					counter+=p.getInventory().getItem(i).getAmount();
			}
			
			return (counter>=15*64);
		} else if(id == 3) {
			// להשיג 20 רוטןפלאש
			int counter = 0;
			for(int i = 0; i < p.getInventory().getSize(); i++) {
				if(p.getInventory().getItem(i) == null || p.getInventory().getItem(i).getType() == Material.AIR) continue;
				if(p.getInventory().getItem(i).getType() == Material.ROTTEN_FLESH)
					counter+=p.getInventory().getItem(i).getAmount();
			}
			return (counter>=20);
		} else if(id == 4) {
			// להשיג 20 עצמות
			int counter = 0;
			for(int i = 0; i < p.getInventory().getSize(); i++) {
				if(p.getInventory().getItem(i) == null || p.getInventory().getItem(i).getType() == Material.AIR) continue;
				if(p.getInventory().getItem(i).getType() == Material.BONE)
					counter+=p.getInventory().getItem(i).getAmount();
			}
			return (counter>=20);
		} else if(id == 5) {
			// להשיג 20 סטייקים
			int counter = 0;
			for(int i = 0; i < p.getInventory().getSize(); i++) {
				if(p.getInventory().getItem(i) == null || p.getInventory().getItem(i).getType() == Material.AIR) continue;
				if(p.getInventory().getItem(i).getType() == Material.COOKED_BEEF)
					counter+=p.getInventory().getItem(i).getAmount();
			}
			return (counter>=20);
		} else if(id == 6) {
			// להשיג 20 עופות
			int counter = 0;
			for(int i = 0; i < p.getInventory().getSize(); i++) {
				if(p.getInventory().getItem(i) == null || p.getInventory().getItem(i).getType() == Material.AIR) continue;
				if(p.getInventory().getItem(i).getType() == Material.COOKED_CHICKEN)
					counter+=p.getInventory().getItem(i).getAmount();
			}
			return (counter>=20);
		} else if(id == 7) {
			// להשיג 64 איירון
			int counter = 0;
			for(int i = 0; i < p.getInventory().getSize(); i++) {
				if(p.getInventory().getItem(i) == null || p.getInventory().getItem(i).getType() == Material.AIR) continue;
				if(p.getInventory().getItem(i).getType() == Material.IRON_INGOT)
					counter+=p.getInventory().getItem(i).getAmount();
			}
			return (counter>=64);
		} else if(id == 8) {
			// להשיג 256 ווד לוגס
			int counter = 0;
			for(int i = 0; i < p.getInventory().getSize(); i++) {
				if(p.getInventory().getItem(i) == null || p.getInventory().getItem(i).getType() == Material.AIR) continue;
				ItemStack item = p.getInventory().getItem(i);
				if(item.getType() == Material.ACACIA_LOG
						|| item.getType() == Material.BIRCH_LOG
						|| item.getType() == Material.DARK_OAK_LOG
						|| item.getType() == Material.JUNGLE_LOG
						|| item.getType() == Material.OAK_LOG
						|| item.getType() == Material.SPRUCE_LOG)
					counter+=item.getAmount();
			}
			return (counter>=256);
		} else if(id == 9) {
			// להשיג אנדרפרל
			for(int i = 0; i < p.getInventory().getSize(); i++) {
				if(p.getInventory().getItem(i) == null || p.getInventory().getItem(i).getType() == Material.AIR) continue;
				if(p.getInventory().getItem(i).getType() == Material.ENDER_PEARL)
					return true;
			}
		} else if(id == 10) {
			// להשיג אנדרפרל
			int counter = 0;
			for(int i = 0; i < p.getInventory().getSize(); i++) {
				if(p.getInventory().getItem(i) == null || p.getInventory().getItem(i).getType() == Material.AIR) continue;
				if(p.getInventory().getItem(i).getType() == Material.HAY_BLOCK)
					counter+=p.getInventory().getItem(i).getAmount();
			}
			return (counter>=8);
		} else if(id == 11) {
			// להשיג אובסידיאן
			for(int i = 0; i < p.getInventory().getSize(); i++) {
				if(p.getInventory().getItem(i) == null || p.getInventory().getItem(i).getType() == Material.AIR) continue;
				if(p.getInventory().getItem(i).getType() == Material.OBSIDIAN)
					return true;
			}
		} else if(id == 12) {
			// להשיג מצפן
			for(int i = 0; i < p.getInventory().getSize(); i++) {
				if(p.getInventory().getItem(i) == null || p.getInventory().getItem(i).getType() == Material.AIR) continue;
				if(p.getInventory().getItem(i).getType() == Material.COMPASS)
					return true;
			}
		} else if(id == 13) {
			// להשיג שעון
			for(int i = 0; i < p.getInventory().getSize(); i++) {
				if(p.getInventory().getItem(i) == null || p.getInventory().getItem(i).getType() == Material.AIR) continue;
				if(p.getInventory().getItem(i).getType() == Material.CLOCK)
					return true;
			}
		} else if(id == 14) {
			// להשיג דלי עם פאפרפיש
			for(int i = 0; i < p.getInventory().getSize(); i++) {
				if(p.getInventory().getItem(i) == null || p.getInventory().getItem(i).getType() == Material.AIR) continue;
				if(p.getInventory().getItem(i).getType() == Material.PUFFERFISH_BUCKET)
					return true;
			}
		} else if(id == 15) {
			// 4 סטאקים שונים של לוג
			Map<Material, Integer> types = new HashMap<Material, Integer>();
			for(int i = 0; i < p.getInventory().getSize(); i++) {
				if(p.getInventory().getItem(i) == null || p.getInventory().getItem(i).getType() == Material.AIR) continue;
				ItemStack item = p.getInventory().getItem(i);
				if(item.getType() == Material.ACACIA_LOG
						|| item.getType() == Material.BIRCH_LOG
						|| item.getType() == Material.DARK_OAK_LOG
						|| item.getType() == Material.JUNGLE_LOG
						|| item.getType() == Material.OAK_LOG
						|| item.getType() == Material.SPRUCE_LOG)
					if(types.containsKey(item.getType()))
						types.put(item.getType(), types.get(item.getType())+item.getAmount());
					else
						types.put(item.getType(), item.getAmount());
			}
			
			int counter = 0;
			for(Material material : types.keySet()) {
				if(types.get(material) >= 64) {
					counter++;
				}
			}
			return (counter>=4);
		} else if(id == 16) {
			// להשיג גולדן אפל
			for(int i = 0; i < p.getInventory().getSize(); i++) {
				if(p.getInventory().getItem(i) == null || p.getInventory().getItem(i).getType() == Material.AIR) continue;
				if(p.getInventory().getItem(i).getType() == Material.GOLDEN_APPLE)
					return true;
			}
		} else if(id == 17) {
			// להשיג עוגה
			for(int i = 0; i < p.getInventory().getSize(); i++) {
				if(p.getInventory().getItem(i) == null || p.getInventory().getItem(i).getType() == Material.AIR) continue;
				if(p.getInventory().getItem(i).getType() == Material.CAKE)
					return true;
			}
		} else if(id == 18) {
			// להשיג 64 קקטוסים
			int counter = 0;
			for(int i = 0; i < p.getInventory().getSize(); i++) {
				if(p.getInventory().getItem(i) == null || p.getInventory().getItem(i).getType() == Material.AIR) continue;
				if(p.getInventory().getItem(i).getType() == Material.CACTUS)
					counter+=p.getInventory().getItem(i).getAmount();
			}
			return (counter>=1);
		}
		return false;
	}
}
