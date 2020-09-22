package dev.lielamar.talecraft.players;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import dev.lielamar.talecraft.Main;

public class CustomPlayer {
	
	private final int amount_of_quests = 18;
	private final int amount_of_superpowers = 11;
	
	private Player p;
	private File file;
	private FileConfiguration config;
	private int currentQuest;
	private int superpower;
	private boolean reseted;
	
	private int luciferLevel;
	
	public CustomPlayer(Player p) {
		this.p = p;
		
		File folder = new File(Main.getInstance().getDataFolder() + "/players/");
		if(!folder.exists()) folder.mkdir();
		
		this.file = new File(Main.getInstance().getDataFolder() + "/players/" + p.getUniqueId().toString() + ".yml");
		createPlayerConfig();
		this.config = YamlConfiguration.loadConfiguration(this.file);
		copyDefaults();
		
		setCurrentQuest(this.config.getInt("currentquest"));
		setSuperPower(this.config.getInt("superpower"));
		setReseted(this.config.getBoolean("reseted"));
		setLuciferLevel(this.config.getInt("luciferlevel"));
	}

	public Player getP() { return p; }
	public void setP(Player p) { this.p = p; }

	public File getFile() { return file; }
	public void setFile(File file) { this.file = file; }

	public FileConfiguration getConfig() { return config; }
	public void setConfig(FileConfiguration config) { this.config = config; }

	public void createPlayerConfig() {
		if(!file.exists()) {
			try { file.createNewFile(); } catch (IOException e) { e.printStackTrace(); }
		}
	}
	
	public void copyDefaults() {
		if(!this.config.contains("name")) this.config.set("name", p.getName());
		if(!this.config.contains("currentquest")) this.config.set("currentquest", -1);
		if(!this.config.contains("superpower")) this.config.set("superpower", -1);
		if(!this.config.contains("reseted")) this.config.set("reseted", false);
		if(!this.config.contains("luciferlevel")) this.config.set("luciferlevel", 0);

		for(int i = 1; i <= amount_of_quests; i++) {
			if(!this.config.contains("quests." + i))
				this.config.set("quests." + i, false);
		}
		saveConfig();
	}
	
	public boolean saveConfig() {
		try { this.config.save(this.file); return true; } catch(IOException e) { e.printStackTrace(); return false; }
	}
	
	public int getCurrentQuest() {
		return this.currentQuest;
	}
	
	public void updateQuest(int id, boolean value) {
		this.config.set("quests." + id, value);
		if(value)
			this.setCurrentQuest(id);
			
		boolean hasMore = false;
		for(int i = 1; i <= amount_of_quests; i++) {
			if(this.config.getBoolean("quests." + i)) {
				hasMore = true;
			}
		}
		
		if(!hasMore) {
			for(int i = 1; i <= amount_of_quests; i++)
				this.config.set("quests." + i, false);
		}
		
		saveConfig();
	}
	
	public boolean getQuest(int id) {
		return this.config.getBoolean("quests." + id);
	}
	
	public int getAvailableQuest() {
		Random r = new Random();
		int id = r.nextInt(amount_of_quests-1+1)+1;
		while(this.config.getBoolean("quests." + id) == true || id == 13) {
			id = r.nextInt(amount_of_quests-1+1)+1;
		}
		
		return id;
	}

	public void setCurrentQuest(int currentQuest) {
		this.config.set("currentquest", currentQuest);
		this.saveConfig();
		this.currentQuest = currentQuest; 
	}
	
	
	public int getAvailableSuperpower() {
		Random r = new Random();
		return r.nextInt(amount_of_superpowers-1+1)+1;
	}
	
	public int getSuperPower() {
		return this.superpower;
	}
	
	public void setSuperPower(int superpower) {
		this.config.set("superpower", superpower);
		this.saveConfig();
		this.superpower = superpower; 
	}
	
	public boolean getReseted() {
		return this.reseted;
	}
	
	public void setReseted(boolean reseted) {
		this.config.set("reseted", reseted);
		this.saveConfig();
		this.reseted = reseted; 
	}
	
	public int getLuciferlevel() {
		return this.luciferLevel;
	}
	
	public void setLuciferLevel(int luciferLevel) {
		this.config.set("luciferlevel", luciferLevel);
		this.saveConfig();
		this.luciferLevel = luciferLevel; 
	}
}
