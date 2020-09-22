package dev.lielamar.talecraft.listeners;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import dev.lielamar.talecraft.constants.ConstantItems;
import dev.lielamar.talecraft.constants.Constants;

public class OnEnchantmentsEvent implements Listener {
	
	@EventHandler
	public void onAnvil(PrepareAnvilEvent e) {
		if(e.getInventory().getItem(0).getType().toString().toLowerCase().contains("book")) return;
		
		if(e.getInventory().getItem(1) == null) return;
		
		ItemStack book = e.getInventory().getItem(1);
		EnchantmentStorageMeta bookMeta = (EnchantmentStorageMeta) book.getItemMeta();
		for(Enchantment enchantment : bookMeta.getStoredEnchants().keySet()) {
			if(enchantment.canEnchantItem(e.getResult()))
				e.getResult().addUnsafeEnchantment(enchantment, bookMeta.getStoredEnchants().get(enchantment));
		}
	}
	
	@EventHandler
	public void onBook(PlayerInteractEvent e) {
		if(!Constants.isYoutuber(e.getPlayer().getUniqueId())) return;
		
		if(e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
		Player p = e.getPlayer();
		if(p.getInventory().getItemInMainHand().equals(ConstantItems.selectBook())) {
			p.openInventory(ConstantItems.bookInventory());
		}
	}
	
	@EventHandler
	public void onBookSelect(InventoryClickEvent e) {
		if(e.getInventory() == null || e.getClickedInventory() == null || e.getWhoClicked() == null) return;
		
		Player p = (Player) e.getWhoClicked();
		if(e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;
		
		if(!e.getView().getTitle().equals(ConstantItems.bookInventoryName)) return;
		
		if(e.getCurrentItem().equals(ConstantItems.protection())) {
			removeBookFromPlayer(p);
			p.getInventory().addItem(ConstantItems.protection());
			e.setCancelled(true);
			p.closeInventory();
			return;
		} else if(e.getCurrentItem().equals(ConstantItems.fire_protection())) {
			removeBookFromPlayer(p);
			p.getInventory().addItem(ConstantItems.fire_protection());
			e.setCancelled(true);
			p.closeInventory();
			return;
		} else if(e.getCurrentItem().equals(ConstantItems.blast_protection())) {
			removeBookFromPlayer(p);
			p.getInventory().addItem(ConstantItems.blast_protection());
			e.setCancelled(true);
			p.closeInventory();
			return;
		} else if(e.getCurrentItem().equals(ConstantItems.projectile_protection())) {
			removeBookFromPlayer(p);
			p.getInventory().addItem(ConstantItems.projectile_protection());
			e.setCancelled(true);
			p.closeInventory();
			return;
		} else if(e.getCurrentItem().equals(ConstantItems.feather_falling())) {
			removeBookFromPlayer(p);
			p.getInventory().addItem(ConstantItems.feather_falling());
			e.setCancelled(true);
			p.closeInventory();
			return;
		} else if(e.getCurrentItem().equals(ConstantItems.respiration())) {
			removeBookFromPlayer(p);
			p.getInventory().addItem(ConstantItems.respiration());
			e.setCancelled(true);
			p.closeInventory();
			return;
		} else if(e.getCurrentItem().equals(ConstantItems.thorns())) {
			removeBookFromPlayer(p);
			p.getInventory().addItem(ConstantItems.thorns());
			e.setCancelled(true);
			p.closeInventory();
			return;
		} else if(e.getCurrentItem().equals(ConstantItems.depth_strider())) {
			removeBookFromPlayer(p);
			p.getInventory().addItem(ConstantItems.depth_strider());
			e.setCancelled(true);
			p.closeInventory();
			return;
		} else if(e.getCurrentItem().equals(ConstantItems.frost_walker())) {
			removeBookFromPlayer(p);
			p.getInventory().addItem(ConstantItems.depth_strider());
			e.setCancelled(true);
			p.closeInventory();
			return;
		} else if(e.getCurrentItem().equals(ConstantItems.sharpness())) {
			removeBookFromPlayer(p);
			p.getInventory().addItem(ConstantItems.sharpness());
			e.setCancelled(true);
			p.closeInventory();
			return;
		} else if(e.getCurrentItem().equals(ConstantItems.smite())) {
			removeBookFromPlayer(p);
			p.getInventory().addItem(ConstantItems.smite());
			e.setCancelled(true);
			p.closeInventory();
			return;
		} else if(e.getCurrentItem().equals(ConstantItems.bane_of_arthropods())) {
			removeBookFromPlayer(p);
			p.getInventory().addItem(ConstantItems.bane_of_arthropods());
			e.setCancelled(true);
			p.closeInventory();
			return;
		} else if(e.getCurrentItem().equals(ConstantItems.knockback())) {
			removeBookFromPlayer(p);
			p.getInventory().addItem(ConstantItems.knockback());
			e.setCancelled(true);
			p.closeInventory();
			return;
		} else if(e.getCurrentItem().equals(ConstantItems.fire_aspect())) {
			removeBookFromPlayer(p);
			p.getInventory().addItem(ConstantItems.fire_aspect());
			e.setCancelled(true);
			p.closeInventory();
			return;
		} else if(e.getCurrentItem().equals(ConstantItems.looting())) {
			removeBookFromPlayer(p);
			p.getInventory().addItem(ConstantItems.looting());
			e.setCancelled(true);
			p.closeInventory();
			return;
		} else if(e.getCurrentItem().equals(ConstantItems.sweeping_edge())) {
			removeBookFromPlayer(p);
			p.getInventory().addItem(ConstantItems.sweeping_edge());
			e.setCancelled(true);
			p.closeInventory();
			return;
		} else if(e.getCurrentItem().equals(ConstantItems.efficiency())) {
			removeBookFromPlayer(p);
			p.getInventory().addItem(ConstantItems.efficiency());
			e.setCancelled(true);
			p.closeInventory();
			return;
		} else if(e.getCurrentItem().equals(ConstantItems.unbreaking())) {
			removeBookFromPlayer(p);
			p.getInventory().addItem(ConstantItems.unbreaking());
			e.setCancelled(true);
			p.closeInventory();
			return;
		} else if(e.getCurrentItem().equals(ConstantItems.furtune())) {
			removeBookFromPlayer(p);
			p.getInventory().addItem(ConstantItems.furtune());
			e.setCancelled(true);
			p.closeInventory();
			return;
		} else if(e.getCurrentItem().equals(ConstantItems.power())) {
			removeBookFromPlayer(p);
			p.getInventory().addItem(ConstantItems.power());
			e.setCancelled(true);
			p.closeInventory();
			return;
		} else if(e.getCurrentItem().equals(ConstantItems.punch())) {
			removeBookFromPlayer(p);
			p.getInventory().addItem(ConstantItems.punch());
			e.setCancelled(true);
			p.closeInventory();
			return;
		} else if(e.getCurrentItem().equals(ConstantItems.luck())) {
			removeBookFromPlayer(p);
			p.getInventory().addItem(ConstantItems.luck());
			e.setCancelled(true);
			p.closeInventory();
			return;
		} else if(e.getCurrentItem().equals(ConstantItems.lure())) {
			removeBookFromPlayer(p);
			p.getInventory().addItem(ConstantItems.lure());
			e.setCancelled(true);
			p.closeInventory();
			return;
		} else if(e.getCurrentItem().equals(ConstantItems.loyalty())) {
			removeBookFromPlayer(p);
			p.getInventory().addItem(ConstantItems.loyalty());
			e.setCancelled(true);
			p.closeInventory();
			return;
		} else if(e.getCurrentItem().equals(ConstantItems.impaling())) {
			removeBookFromPlayer(p);
			p.getInventory().addItem(ConstantItems.impaling());
			e.setCancelled(true);
			p.closeInventory();
			return;
		} else if(e.getCurrentItem().equals(ConstantItems.riptide())) {
			removeBookFromPlayer(p);
			p.getInventory().addItem(ConstantItems.riptide());
			e.setCancelled(true);
			p.closeInventory();
			return;
		} else if(e.getCurrentItem().equals(ConstantItems.quick_charge())) {
			removeBookFromPlayer(p);
			p.getInventory().addItem(ConstantItems.quick_charge());
			e.setCancelled(true);
			p.closeInventory();
			return;
		} else if(e.getCurrentItem().equals(ConstantItems.piercing())) {
			removeBookFromPlayer(p);
			p.getInventory().addItem(ConstantItems.piercing());
			e.setCancelled(true);
			p.closeInventory();
			return;
		} else if(e.getCurrentItem().equals(ConstantItems.mending())) {
			removeBookFromPlayer(p);
			p.getInventory().addItem(ConstantItems.mending());
			e.setCancelled(true);
			p.closeInventory();
			return;
		} else {
			return;
		}
	}
	
	public void removeBookFromPlayer(Player p) {
		for(int i = 0; i < p.getInventory().getSize(); i++) {
			if(p.getInventory().getItem(i) == null || p.getInventory().getItem(i).getType() == Material.AIR) continue;
			if(p.getInventory().getItem(i).equals(ConstantItems.selectBook())) {
				p.getInventory().setItem(i, null);
				return;
			}
		}
	}
}
