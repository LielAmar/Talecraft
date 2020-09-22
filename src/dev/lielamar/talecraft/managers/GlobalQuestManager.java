package dev.lielamar.talecraft.managers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import dev.lielamar.talecraft.Main;
import dev.lielamar.talecraft.players.CustomPlayer;

public class GlobalQuestManager {

	private int quest;
	
	public GlobalQuestManager() {
		quest = Main.getInstance().getConfig().getInt("globalquest");
		
		setup(quest);
	}
	
	private void setup(int quest) {
	}
	
	public void setGlobalQuest(int quest) {
		this.quest = quest;
		Main.getInstance().getConfig().set("globalquest", quest);
		Main.getInstance().saveConfig();
		
		setup(quest);
	}
	
	public int getQuest() {
		return this.quest;
	}
	
	public void setupPlayerQuest(Player p, CustomPlayer cp) {
		if(quest == 0) {
			cp.setSuperPower(-1);
		}
		if(quest == 2) {
			if(cp.getSuperPower() == -1) {
				cp.setSuperPower(cp.getAvailableSuperpower());
			}
			p.sendMessage(ChatColor.GRAY + "You superpower is: " + ChatColor.AQUA + intToStringSuperpower(cp.getSuperPower()));
		}
		
		if(quest == 3) {
			p.sendMessage(ChatColor.GRAY + "The " + ChatColor.YELLOW + "Shared Damage " + ChatColor.GRAY + "event is enabled!");
		}
	}
	
	// Superpowers
	private String intToStringSuperpower(int superpower) {
		if(superpower == 1) return "Summon lightnings with any wood axe in main hand!";
		if(superpower == 2) return "Block falldamage with any shield in off hand!";
		if(superpower == 3) return "Run lightning-fast!";
		if(superpower == 4) return "Deal x2 damage to ANY entity!";
		if(superpower == 5) return "Breathe underwater!";
		if(superpower == 6) return "Hit every arrow!";
		if(superpower == 7) return "Never die!";
		if(superpower == 8) return "Shoot lasers with your hands!";
		if(superpower == 9) return "Double jump to the air!";
		if(superpower == 10) return "Get reduced damage!";
		if(superpower == 11) return "Mobs don't attack you!";
		return "";
	}
}
