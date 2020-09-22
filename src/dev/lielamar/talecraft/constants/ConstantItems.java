package dev.lielamar.talecraft.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class ConstantItems {

	public static ItemStack getItem(Material material, int amount, String displayName, String[] lore, boolean enchanted) {
		ItemStack item = new ItemStack(material, amount);
		ItemMeta meta = item.getItemMeta();
		meta.setLore(Arrays.asList(lore));
		meta.setDisplayName(displayName);
		if(enchanted) {
			item.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack getBook(Enchantment enchantment, int level) {
        ItemStack item = new ItemStack(Material.ENCHANTED_BOOK, 1);
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
        meta.addStoredEnchant(enchantment, level, true);
        item.setItemMeta(meta);
        return item;
    }
	
	public static ItemStack shardsBook() {
		
		ItemStack item = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta meta = (BookMeta) item.getItemMeta();
		meta.setTitle(ChatColor.GRAY + "Talecraft Shards Quest");
		meta.setAuthor(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Lucifer");
		
		List<String> pages = new ArrayList<String>();
		pages.add(ChatColor.BLACK + "Hello dear " + ChatColor.RED + "Youtuber" + ChatColor.BLACK + "!\n"
				+ ChatColor.BLACK + "I couldn't help but notice you came back to life, and unfortunately for you, the world you live in now is full of monsters.\n"
				+ ChatColor.BLACK + "I have an offer for you, an offer that would benefit us both.");
		pages.add(ChatColor.BLACK + "My offer is to kill the monsters from your world." + "\n"
				+ ChatColor.BLACK + "Do it and I will reward you with a price-less item!");
		pages.add(ChatColor.BLACK + "Kill these monsters for me:"
				+ "\n" + ChatColor.GREEN + "Creeper"
				+ "\n" + ChatColor.RED + "Zombie"
				+ "\n" + ChatColor.BLACK + "Skeleton"
				+ "\n" + ChatColor.GRAY + "Spider"
				+ "\n" + ChatColor.DARK_RED + "Enderman"
				+ "\n" + ChatColor.YELLOW + "Blaze"
				+ "\n" + ChatColor.LIGHT_PURPLE + "Pigman");
		
		meta.setPages(pages);
		item.setItemMeta(meta);
		
		return item;
	}
	
	public static ItemStack creeperShard() {
		ItemStack item = getItem(Material.GREEN_DYE, 1, ChatColor.GREEN + "Creeper Shard", new String[] {
				ChatColor.RESET + "" + ChatColor.GRAY + "1/7"
		}, true);
		return item;
	}
	
	
	public static ItemStack zombieShard() {
		return getItem(Material.RED_DYE, 1, ChatColor.RED + "Zombie Shard", new String[] {
				ChatColor.RESET + "" + ChatColor.GRAY + "2/7"
		}, true); 
	}
	
	public static ItemStack skeletonShard() {
		return getItem(Material.WHITE_DYE, 1, ChatColor.WHITE + "Skeleton Shard", new String[] {
				ChatColor.RESET + "" + ChatColor.GRAY + "3/7"
		}, true); 
	}
	
	public static ItemStack spiderShard() {
		return getItem(Material.GRAY_DYE, 1, ChatColor.GRAY + "Spider Shard", new String[] {
				ChatColor.RESET + "" + ChatColor.GRAY + "4/7"
		}, true); 
	}
	
	public static ItemStack endermanShard() {
		return getItem(Material.BROWN_DYE, 1, ChatColor.DARK_RED + "Enderman Shard", new String[] {
				ChatColor.RESET + "" + ChatColor.GRAY + "5/7"
		}, true); 
	}
	
	public static ItemStack blazeShard() {
		return getItem(Material.YELLOW_DYE, 1, ChatColor.YELLOW + "Blaze Shard", new String[] {
				ChatColor.RESET + "" + ChatColor.GRAY + "6/7"
		}, true); 
	}
	
	public static ItemStack pigmanShard() {
		return getItem(Material.PINK_DYE, 1, ChatColor.LIGHT_PURPLE + "Pigman Shard", new String[] {
				ChatColor.RESET + "" + ChatColor.GRAY + "7/7"
		}, true); 
	}
	
	public static ItemStack selectBook() {
		return getItem(Material.ENCHANTED_BOOK, 1, ChatColor.AQUA + "Enchantment Selector", new String[] {
				ChatColor.RESET + "" + ChatColor.GRAY + "Rewarded for getting 7/7 shards!",
				ChatColor.RESET + "" + ChatColor.GRAY + "Choose an enchantment wisely!"
		}, false);
	}
	
	public static ItemStack protection() { return getBook(Enchantment.PROTECTION_ENVIRONMENTAL, 5); }
	public static ItemStack fire_protection() { return getBook(Enchantment.PROTECTION_FIRE, 5); }
	public static ItemStack blast_protection() { return getBook(Enchantment.PROTECTION_EXPLOSIONS, 5); }
	public static ItemStack projectile_protection() { return getBook(Enchantment.PROTECTION_PROJECTILE, 5); }
	public static ItemStack feather_falling() { return getBook(Enchantment.PROTECTION_FALL, 5); }
	public static ItemStack respiration() { return getBook(Enchantment.OXYGEN, 4); }
	public static ItemStack thorns() { return getBook(Enchantment.THORNS, 4); }
	public static ItemStack depth_strider() { return getBook(Enchantment.DEPTH_STRIDER, 4); }
	public static ItemStack frost_walker() { return getBook(Enchantment.FROST_WALKER, 3); }
	public static ItemStack sharpness() { return getBook(Enchantment.DAMAGE_ALL, 6); }
	public static ItemStack smite() { return getBook(Enchantment.DAMAGE_UNDEAD, 6); }
	public static ItemStack bane_of_arthropods() { return getBook(Enchantment.DAMAGE_ARTHROPODS, 6); }
	public static ItemStack knockback() { return getBook(Enchantment.KNOCKBACK, 3); }
	public static ItemStack fire_aspect() { return getBook(Enchantment.FIRE_ASPECT, 3); }
	public static ItemStack looting() { return getBook(Enchantment.LOOT_BONUS_MOBS, 4); }
	public static ItemStack sweeping_edge() { return getBook(Enchantment.SWEEPING_EDGE, 4); }
	public static ItemStack efficiency() { return getBook(Enchantment.DIG_SPEED, 6); }
	public static ItemStack unbreaking() { return getBook(Enchantment.DURABILITY, 4); }
	public static ItemStack furtune() { return getBook(Enchantment.LOOT_BONUS_BLOCKS, 4); }
	public static ItemStack power() { return getBook(Enchantment.ARROW_DAMAGE, 6); }
	public static ItemStack punch() { return getBook(Enchantment.ARROW_KNOCKBACK, 3); }
	public static ItemStack luck() { return getBook(Enchantment.LUCK, 4); }
	public static ItemStack lure() { return getBook(Enchantment.LURE, 4); }
	public static ItemStack loyalty() { return getBook(Enchantment.LOYALTY, 4); }
	public static ItemStack impaling() { return getBook(Enchantment.IMPALING, 6); }
	public static ItemStack riptide() { return getBook(Enchantment.RIPTIDE, 4); }
	public static ItemStack quick_charge() { return getBook(Enchantment.QUICK_CHARGE, 4); }
	public static ItemStack piercing() { return getBook(Enchantment.PIERCING, 5); }
	public static ItemStack mending() { return getBook(Enchantment.MENDING, 2); }
	
	public static final String bookInventoryName = ChatColor.AQUA + "Enchantment Selector";
	
	public static Inventory bookInventory() {
		Inventory inv = Bukkit.createInventory(null, 36, bookInventoryName);

		inv.setItem(0, protection());
		inv.setItem(1, fire_protection());
		inv.setItem(2, blast_protection());
		inv.setItem(3, projectile_protection());
		inv.setItem(4, feather_falling());
		inv.setItem(5, respiration());
		inv.setItem(6, thorns());
		inv.setItem(7, depth_strider());
		inv.setItem(8, frost_walker());
		inv.setItem(9, sharpness());
		inv.setItem(10, smite());
		inv.setItem(11, bane_of_arthropods());
		inv.setItem(12, knockback());
		inv.setItem(13, fire_aspect());
		inv.setItem(14, looting());
		inv.setItem(15, sweeping_edge());
		inv.setItem(16, efficiency());
		inv.setItem(17, unbreaking());
		inv.setItem(18, furtune());
		inv.setItem(19, power());
		inv.setItem(20, punch());
		inv.setItem(21, luck());
		inv.setItem(22, lure());
		inv.setItem(23, loyalty());
		inv.setItem(24, impaling());
		inv.setItem(25, riptide());
		inv.setItem(26, quick_charge());
		inv.setItem(27, piercing());
		inv.setItem(28, mending());

		return inv;
	}
	
	public static ItemStack shoesOfVidar() {
		ItemStack item = new ItemStack(Material.DIAMOND_BOOTS);
		item.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Shoes Of Vidar");
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack hideOfLeviathan() {
		ItemStack item = new ItemStack(Material.DIAMOND_LEGGINGS);
		item.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Hide Of Leviathan");
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack crownOfImmortality() {
		ItemStack item = new ItemStack(Material.GOLDEN_HELMET);
		item.addEnchantment(Enchantment.DURABILITY, 3);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Crown of Immortality");
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack egilWings() {
		ItemStack item = new ItemStack(Material.ELYTRA);
		item.addEnchantment(Enchantment.DURABILITY, 3);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Egil Wings");
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack excalibur() {
		ItemStack item = new ItemStack(Material.IRON_SWORD);
		item.addEnchantment(Enchantment.DAMAGE_ALL, 5);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Excalibur");
		item.setItemMeta(meta);
		return item;
	}
}
