package dev.lielamar.talecraft;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.WorldCreator;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import dev.lielamar.talecraft.commands.Bed;
import dev.lielamar.talecraft.commands.LuciferItem;
import dev.lielamar.talecraft.commands.Spectate;
import dev.lielamar.talecraft.commands.Streaming;
import dev.lielamar.talecraft.commands.StreamingUtils;
import dev.lielamar.talecraft.commands.TaleDebug;
import dev.lielamar.talecraft.constants.Constants;
import dev.lielamar.talecraft.listeners.OnBedEvent;
import dev.lielamar.talecraft.listeners.OnChatEvent;
import dev.lielamar.talecraft.listeners.OnEndermanDrop;
import dev.lielamar.talecraft.listeners.OnJoinEvent;
import dev.lielamar.talecraft.listeners.OnLeaveEvent;
import dev.lielamar.talecraft.listeners.OnRespawnEvent;
import dev.lielamar.talecraft.listeners.OnServerPing;
import dev.lielamar.talecraft.listeners.OnSpectatorCommand;
import dev.lielamar.talecraft.listeners.globalquests.shareddamage.OnSharedDamage;
import dev.lielamar.talecraft.listeners.globalquests.superheroes.OnAquaManSuperpower;
import dev.lielamar.talecraft.listeners.globalquests.superheroes.OnArrowSuperpower;
import dev.lielamar.talecraft.listeners.globalquests.superheroes.OnBlackPantherSuperpower;
import dev.lielamar.talecraft.listeners.globalquests.superheroes.OnCaptainAmericaSuperpower;
import dev.lielamar.talecraft.listeners.globalquests.superheroes.OnCaptainMarvelSuperpower;
import dev.lielamar.talecraft.listeners.globalquests.superheroes.OnDeadpoolSuperpower;
import dev.lielamar.talecraft.listeners.globalquests.superheroes.OnFlashSuperpower;
import dev.lielamar.talecraft.listeners.globalquests.superheroes.OnHulkSuperpower;
import dev.lielamar.talecraft.listeners.globalquests.superheroes.OnIronmanSuperpower;
import dev.lielamar.talecraft.listeners.globalquests.superheroes.OnThorSuperpower;
import dev.lielamar.talecraft.listeners.globalquests.superheroes.OnVisionSuperpower;
import dev.lielamar.talecraft.listeners.heaven.OnBedPlaceEvent;
import dev.lielamar.talecraft.listeners.heaven.OnEnderchest;
import dev.lielamar.talecraft.listeners.heaven.OnHeavenProtection;
import dev.lielamar.talecraft.listeners.heaven.OnPlayerMove;
import dev.lielamar.talecraft.listeners.heaven.OnPortalEvent;
import dev.lielamar.talecraft.listeners.heaven.OnQuestEvent;
import dev.lielamar.talecraft.listeners.lucifer.OnLucifer;
import dev.lielamar.talecraft.listeners.protection.OnOpEvent;
import dev.lielamar.talecraft.managers.GlobalQuestManager;
import dev.lielamar.talecraft.miniatures.Samael;
import dev.lielamar.talecraft.players.CustomPlayer;
import dev.lielamar.talecraft.utils.Utils;

public class Main extends JavaPlugin {

	private static Main instance = null;
	
	private ConsoleCommandSender console = null;
	private PluginDescriptionFile pdf = null;
	
	public static List<CustomPlayer> players;
	public Samael samael = null;
	public GlobalQuestManager globalQuestManager;
	
	
	@Override
	public void onEnable() {
		
		instance = this;
		
		players = new LinkedList<CustomPlayer>();
		
		console = Bukkit.getConsoleSender();
		pdf = getDescription();
		saveDefaultConfig();
		StreamingUtils.streaming = getConfig().getBoolean("streaming.value");
		
		for(Player pl : Bukkit.getOnlinePlayers()) {
			if(Constants.isYoutuber(pl.getUniqueId()))
				players.add(new CustomPlayer(pl));
			else
				Utils.spectatorsList.put(pl.getUniqueId(), Utils.getOnlineYoutuber());
		}

		globalQuestManager = new GlobalQuestManager();
		
		
		setupHeaven();
		
		registerCommands();
		registerEvents();
		
		console.sendMessage(ChatColor.GREEN + "Enabling " + ChatColor.YELLOW + pdf.getFullName() + ChatColor.GREEN + " by " + pdf.getAuthors().toString() + " v" + pdf.getVersion() + "!");
	}
	
	@Override
	public void onDisable() {
		console.sendMessage(ChatColor.RED + "Disabling " + ChatColor.YELLOW + pdf.getFullName() + ChatColor.RED + "!");
	}
	
	public void registerCommands() {
		getCommand("startstreaming").setExecutor(new Streaming());
		getCommand("stopstreaming").setExecutor(new Streaming());
		getCommand("confirm").setExecutor(new Streaming());
		getCommand("spectate").setExecutor(new Spectate());
		getCommand("taledebug").setExecutor(new TaleDebug());
		getCommand("bed").setExecutor(new Bed());
		getCommand("luciferitem").setExecutor(new LuciferItem());
	}
	
	public void registerEvents() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new OnJoinEvent(), this);   		// When a player joins the game
		pm.registerEvents(new OnChatEvent(), this);    		// When a player chats
		pm.registerEvents(new OnRespawnEvent(), this);  		// When a player dies
		pm.registerEvents(new OnLeaveEvent(), this);   		// When a player leaves the game
		pm.registerEvents(new OnBedEvent(), this);     		// Allow night-skip when there are more than 2 players in bed
		pm.registerEvents(new OnEndermanDrop(), this); 		// Enderman drop enderpearls
//		pm.registerEvents(new OnShardsEvent(), this);  		// Shards events (get shards, do things with them etc...)
//		pm.registerEvents(new OnEnchantmentsEvent(), this); // Custom Anvil events
		pm.registerEvents(new OnServerPing(), this); // Server Motd
		pm.registerEvents(new OnSpectatorCommand(), this); // Prevent spectators from doing commands
		pm.registerEvents(new OnLucifer(), this); // Lucifer events
		pm.registerEvents(new LuciferItem(), this); // Lucifer inventories event
		
		// Protection
		pm.registerEvents(new OnOpEvent(), this);
		
		// Heaven
		pm.registerEvents(new OnHeavenProtection(), this);  // Heaven Protection
		pm.registerEvents(new OnBedPlaceEvent(), this);     // Prevent changing spawn point
		pm.registerEvents(new OnQuestEvent(), this);   		// Quest handler
		pm.registerEvents(new OnPlayerMove(), this);   		// Border handler
		pm.registerEvents(new OnPortalEvent(), this); 		// Block the ability to portal away while in heaven
		pm.registerEvents(new OnEnderchest(), this);   		// Block the ability to open enderchests while in heaven
		
		// The end
//		pm.registerEvents(new OnTheEnd(), this);
		
		// Superpowers
		if(globalQuestManager.getQuest() == 2) {
			pm.registerEvents(new OnThorSuperpower(), this);
			pm.registerEvents(new OnCaptainAmericaSuperpower(), this);
			pm.registerEvents(new OnFlashSuperpower(), this);
			pm.registerEvents(new OnHulkSuperpower(), this);
			pm.registerEvents(new OnAquaManSuperpower(), this);
			pm.registerEvents(new OnArrowSuperpower(), this);
			pm.registerEvents(new OnDeadpoolSuperpower(), this);
			pm.registerEvents(new OnCaptainMarvelSuperpower(), this);
			pm.registerEvents(new OnIronmanSuperpower(), this);
			pm.registerEvents(new OnBlackPantherSuperpower(), this);
			pm.registerEvents(new OnVisionSuperpower(), this);
		}
		
		if(globalQuestManager.getQuest() == 3) {
			pm.registerEvents(new OnSharedDamage(), this);
		}
		
		Random rnd = new Random();
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				for(Player pl : Bukkit.getOnlinePlayers()) {
					if(Constants.isYoutuber(pl.getUniqueId())) {
						if(players.size() > 1) {
							if(rnd.nextBoolean()) continue;
						}
						
						Location loc = pl.getLocation();
						int rndX = rnd.nextInt(8)-4;
						int rndZ = rnd.nextInt(8)-4;
						Location heaven = new Location(Utils.heavenLoc.getWorld(), Utils.heavenLoc.getX() + rndX, Utils.heavenLoc.getY(), Utils.heavenLoc.getZ() + rndZ);
						pl.teleport(heaven);
						pl.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 30, 1));
						pl.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 30, 1));
						pl.damage(0);
						
						new BukkitRunnable() {
							@Override
							public void run() {
								pl.teleport(loc);
							}
						}.runTaskLater(Main.getInstance(), 30L);
						break;
					}
				}
			}
		}.runTaskTimer(this, 36000, 36000);
	}
	
	public static Main getInstance() {
		return instance;
	}
	
	public void setupHeaven() {
		Bukkit.createWorld(new WorldCreator("Heaven"));
		WorldCreator wc = new WorldCreator("Heaven_nether");
		wc.environment(Environment.NETHER);
		wc.createWorld();
		
		for(World world : Bukkit.getWorlds()) {
			world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
		}
	}
}
