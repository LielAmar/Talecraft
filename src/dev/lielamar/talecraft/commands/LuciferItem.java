package dev.lielamar.talecraft.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import dev.lielamar.talecraft.constants.ConstantItems;
import dev.lielamar.talecraft.constants.Constants;

public class LuciferItem implements CommandExecutor, Listener {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String cmdLabel, String[] args) {
		if(!cmd.getName().equalsIgnoreCase("luciferitem")) return false;
		
		if(!(cs instanceof Player)) {
			cs.sendMessage(ChatColor.RED + "You must be a player to do that!");
			return false;
		}
		
		Player p = (Player)cs;
		
		if(!Constants.isYoutuber(p.getUniqueId())) {
			cs.sendMessage(ChatColor.RED + "You don't have enough permissions to do that!");
			return false;
		}
		
		if(args.length == 0) {
			cs.sendMessage(ChatColor.RED + "Wrong usage. Don't try to use this command if you're not the developer.");
			return false;
		}
		
		if(args[0].equalsIgnoreCase("shoesofvidar")) {
			Inventory inv = Bukkit.createInventory(null, 9, ChatColor.GRAY + "Shoes Of Vidar");
			inv.setItem(4, ConstantItems.shoesOfVidar());
			p.openInventory(inv);
		} else if(args[0].equalsIgnoreCase("hideofleviathan")) {
			Inventory inv = Bukkit.createInventory(null, 9, ChatColor.GRAY + "Hide Of Leviathan");
			inv.setItem(4, ConstantItems.hideOfLeviathan());
			p.openInventory(inv);
		} else if(args[0].equalsIgnoreCase("crownofimmortality")) {
			Inventory inv = Bukkit.createInventory(null, 9, ChatColor.GRAY + "Crown Of Immortality");
			inv.setItem(4, ConstantItems.crownOfImmortality());
			p.openInventory(inv);
		} else if(args[0].equalsIgnoreCase("egilwings")) {
			Inventory inv = Bukkit.createInventory(null, 9, ChatColor.GRAY + "Egil Wings");
			inv.setItem(4, ConstantItems.egilWings());
			p.openInventory(inv);
		} else if(args[0].equalsIgnoreCase("excalibur")) {
			Inventory inv = Bukkit.createInventory(null, 9, ChatColor.GRAY + "Excalibur");
			inv.setItem(4, ConstantItems.excalibur());
			p.openInventory(inv);
		}
		return false;
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if(e.getInventory() == null || e.getClickedInventory() == null || e.getWhoClicked() == null) return;
		
		if(e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;
		
		if(!e.getView().getTitle().equals(ChatColor.GRAY + "Shoes Of Vidar")) return;
		if(!e.getView().getTitle().equals(ChatColor.GRAY + "Hide Of Leviathan")) return;
		if(!e.getView().getTitle().equals(ChatColor.GRAY + "Crown Of Immortality")) return;
		if(!e.getView().getTitle().equals(ChatColor.GRAY + "Egil Wings")) return;
		if(!e.getView().getTitle().equals(ChatColor.GRAY + "Excalibur")) return;
		
		e.setCancelled(true);
	}
}
