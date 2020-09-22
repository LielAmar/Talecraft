package dev.lielamar.talecraft.miniatures;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.util.EulerAngle;

import dev.lielamar.talecraft.managers.FurnitureManager;

public class Samael {

	private HashMap<String, ArmorStand> parts = new HashMap<>();

	public HashMap<String, ArmorStand> getParts() {
		return parts;
	}
	
	@SuppressWarnings("deprecation")
	public Samael(Location playerLocation) {
    	
    	Location loc = new Location(playerLocation.getWorld(), playerLocation.getX(), playerLocation.getY(), playerLocation.getZ());
    	
    	Location locSamael = loc.clone().add(0, 0, 0);
    	ItemStack helmet = new ItemStack(Material.PLAYER_HEAD);
    	SkullMeta skull = (SkullMeta) helmet.getItemMeta();
    	skull.setOwningPlayer(Bukkit.getOfflinePlayer("Kaworu"));
    	helmet.setItemMeta(skull);
    	
    	ItemStack chestplate = new ItemStack(Material.IRON_CHESTPLATE);
    	ItemStack leggings = new ItemStack(Material.IRON_LEGGINGS);
    	ItemStack boots = new ItemStack(Material.IRON_BOOTS);
        ArmorStand samael = FurnitureManager.spawnArmorStand(locSamael, null, new ItemStack(Material.TRIDENT, 1), helmet, chestplate, leggings, boots,
        		 true, false, false, false, false, false, true,
         		/* Head */ new EulerAngle(Math.toRadians(20), 0, 0), // first arg: down
         		/* Body */ new EulerAngle(0, 0, 0),
         		/* Left Arm */ new EulerAngle(Math.toRadians(-15), 0, Math.toRadians(-13)), // first arg: back, 3rd arg: left
         		/* Right Arm */ new EulerAngle(Math.toRadians(-25), 0, Math.toRadians(20)), // first arg: back, 3rd arg: right
         		/* Left Leg */ new EulerAngle(Math.toRadians(20), 0, Math.toRadians(-13)),  // first arg: back, 3rd arg: left
         		/* Right Leg */ new EulerAngle(Math.toRadians(10), 0, Math.toRadians(13))   // first arg: back, 3rd arg: right
         		);
        samael.setCustomName("samael");
        parts.put("samael", samael);
    	
        
        Location locLeft1 = loc.clone().add(-0.5, -0.15, 0);
        ArmorStand left1 = FurnitureManager.spawnArmorStand(locLeft1, new ItemStack(Material.WHITE_BANNER, 1), null, false, false, false, false,
        		false, false, true,
        		new EulerAngle(Math.toRadians(-5), 0, Math.toRadians(85)), // First arg: back, second arg: top, third arg: side
        		EulerAngle.ZERO);
        left1.setCustomName("samaelleft1");
        parts.put("left1", left1);
        
        Location locLeft2 = loc.clone().add(0.2, -0.05, -0.1);
        ArmorStand left2 = FurnitureManager.spawnArmorStand(locLeft2, new ItemStack(Material.WHITE_BANNER, 1), null, false, false, false, false,
      		false, false, true,
      		new EulerAngle(Math.toRadians(-5), 0, Math.toRadians(65)), // First arg: back, second arg: top, third arg: side
      		EulerAngle.ZERO);
        left2.setCustomName("samaelleft2");
        parts.put("left2", left2);
        
        Location locLeft3 = loc.clone().add(-0.2, 0.15, -0.1);
        ArmorStand left3 = FurnitureManager.spawnArmorStand(locLeft3, new ItemStack(Material.WHITE_BANNER, 1), null, false, false, false, false,
      		false, false, true,
      		new EulerAngle(Math.toRadians(-5), 0, Math.toRadians(55)), // First arg: back, second arg: top, third arg: side
      		EulerAngle.ZERO);
        left3.setCustomName("samaelleft3");
        parts.put("left3", left3);
        
        Location locLeft4 = loc.clone().add(0.5, -0.05, -0.05);
        ArmorStand left4 = FurnitureManager.spawnArmorStand(locLeft4, new ItemStack(Material.WHITE_BANNER, 1), null, false, false, false, false,
      		false, false, true,
      		new EulerAngle(Math.toRadians(-5), 0, Math.toRadians(85)), // First arg: back, second arg: top, third arg: side
      		EulerAngle.ZERO);
        left4.setCustomName("samaelleft4");
        parts.put("left4", left4);
        
        Location locLeft5 = loc.clone().add(0.1, -0.05, 0.1);
        ArmorStand left5 = FurnitureManager.spawnArmorStand(locLeft5, new ItemStack(Material.WHITE_BANNER, 1), null, false, false, false, false,
      		false, false, true,
      		new EulerAngle(Math.toRadians(-5), 0, Math.toRadians(115)), // First arg: back, second arg: top, third arg: side
      		EulerAngle.ZERO);
        left5.setCustomName("samaelleft5");
        parts.put("left5", left5);

        Location locLeft7 = loc.clone().add(0.5, 0.1, 0);
        ArmorStand left7 = FurnitureManager.spawnArmorStand(locLeft7, new ItemStack(Material.WHITE_BANNER, 1), null, false, false, false, false,
      		false, false, true,
      		new EulerAngle(Math.toRadians(-5), 0, Math.toRadians(115)), // First arg: back, second arg: top, third arg: side
      		EulerAngle.ZERO);
        left7.setCustomName("samaelleft7");
        parts.put("left7", left7);
        
        Location locLeft6 = loc.clone().add(1, 0.15, -0.1);
        ArmorStand left6 = FurnitureManager.spawnArmorStand(locLeft6, new ItemStack(Material.WHITE_BANNER, 1), null, false, false, false, false,
      		true, false, true,
      		new EulerAngle(Math.toRadians(-5), 0, Math.toRadians(135)), // First arg: back, second arg: top, third arg: side
      		EulerAngle.ZERO);
        left6.setCustomName("samaelleft6");
        parts.put("left6", left6);
        
        Location locLeft8 = loc.clone().add(1.3, 0.15, -0.1);
        ArmorStand left8 = FurnitureManager.spawnArmorStand(locLeft8, new ItemStack(Material.WHITE_BANNER, 1), null, false, false, false, false,
      		true, false, true,
      		new EulerAngle(Math.toRadians(-5), 0, Math.toRadians(120)), // First arg: back, second arg: top, third arg: side
      		EulerAngle.ZERO);
        left8.setCustomName("samaelleft8");
        parts.put("left8", left8); 
        
        Location locLeft9 = loc.clone().add(1.4, 0.3, -0.1);
        ArmorStand left9 = FurnitureManager.spawnArmorStand(locLeft9, new ItemStack(Material.WHITE_BANNER, 1), null, false, false, false, false,
      		true, false, true,
      		new EulerAngle(Math.toRadians(-5), 0, Math.toRadians(103)), // First arg: back, second arg: top, third arg: side
      		EulerAngle.ZERO);
        left9.setCustomName("samaelleft9");
        parts.put("left9", left9);
        
        Location locLeft10 = loc.clone().add(1.4, 0.5, -0.15);
        ArmorStand left10 = FurnitureManager.spawnArmorStand(locLeft10, new ItemStack(Material.WHITE_BANNER, 1), null, false, false, false, false,
      		true, false, true,
      		new EulerAngle(Math.toRadians(-5), 0, Math.toRadians(90)), // First arg: back, second arg: top, third arg: side
      		EulerAngle.ZERO);
        left10.setCustomName("samaelleft10");
        parts.put("left10", left10);
        
        Location locLeft11 = loc.clone().add(2.8, -0.3, -0.5);
        ArmorStand left11 = FurnitureManager.spawnArmorStand(locLeft11, null, new ItemStack(Material.WHITE_BANNER, 1), false, false, false, false,
      		false, false, true,
      		EulerAngle.ZERO, // First arg: back, second arg: top, third arg: side
      		EulerAngle.ZERO,
      		new EulerAngle(0, Math.toRadians(270), Math.toRadians(20)));
        left11.setCustomName("samaelleft11");
        parts.put("left11", left11);

        
        
        // RIGHT SIDE
        Location locRight1 = loc.clone().add(0.5, -0.15, 0);
        ArmorStand right1 = FurnitureManager.spawnArmorStand(locRight1, new ItemStack(Material.WHITE_BANNER, 1), null, false, false, false, false,
        		false, false, true,
        		new EulerAngle(Math.toRadians(-5), 0, Math.toRadians(-85)), // First arg: back, second arg: top, third arg: side
        		EulerAngle.ZERO);
        right1.setCustomName("samaelright1");
        parts.put("right1", right1);
        
        Location locRight2 = loc.clone().add(-0.2, -0.05, -0.1);
        ArmorStand right2 = FurnitureManager.spawnArmorStand(locRight2, new ItemStack(Material.WHITE_BANNER, 1), null, false, false, false, false,
      		false, false, true,
      		new EulerAngle(Math.toRadians(-5), 0, Math.toRadians(-65)), // First arg: back, second arg: top, third arg: side
      		EulerAngle.ZERO);
        right2.setCustomName("samaelright2");
        parts.put("right2", right2);
        
        Location locRight3 = loc.clone().add(0.2, 0.15, -0.1);
        ArmorStand right3 = FurnitureManager.spawnArmorStand(locRight3, new ItemStack(Material.WHITE_BANNER, 1), null, false, false, false, false,
      		false, false, true,
      		new EulerAngle(Math.toRadians(-5), 0, Math.toRadians(-55)), // First arg: back, second arg: top, third arg: side
      		EulerAngle.ZERO);
        right3.setCustomName("samaelright3");
        parts.put("right3", right3);
        
        Location locRight4 = loc.clone().add(-0.5, -0.05, -0.05);
        ArmorStand right4 = FurnitureManager.spawnArmorStand(locRight4, new ItemStack(Material.WHITE_BANNER, 1), null, false, false, false, false,
      		false, false, true,
      		new EulerAngle(Math.toRadians(-5), 0, Math.toRadians(-85)), // First arg: back, second arg: top, third arg: side
      		EulerAngle.ZERO);
        right4.setCustomName("samaelright4");
        parts.put("right4", right4);
    
        Location locRight5 = loc.clone().add(-0.1, -0.05, 0.1);
        ArmorStand right5 = FurnitureManager.spawnArmorStand(locRight5, new ItemStack(Material.WHITE_BANNER, 1), null, false, false, false, false,
      		false, false, true,
      		new EulerAngle(Math.toRadians(-5), 0, Math.toRadians(-115)), // First arg: back, second arg: top, third arg: side
      		EulerAngle.ZERO);
        right5.setCustomName("samaelright5");
        parts.put("right5", right5);

        Location locRight7 = loc.clone().add(-0.5, 0.1, 0);
        ArmorStand right7 = FurnitureManager.spawnArmorStand(locRight7, new ItemStack(Material.WHITE_BANNER, 1), null, false, false, false, false,
      		false, false, true,
      		new EulerAngle(Math.toRadians(-5), 0, Math.toRadians(-115)), // First arg: back, second arg: top, third arg: side
      		EulerAngle.ZERO);
        right7.setCustomName("samaelright7");
        parts.put("right7", right7);
        
        Location locRight6 = loc.clone().add(-1, 0.15, -0.1);
        ArmorStand right6 = FurnitureManager.spawnArmorStand(locRight6, new ItemStack(Material.WHITE_BANNER, 1), null, false, false, false, false,
      		true, false, true,
      		new EulerAngle(Math.toRadians(-5), 0, Math.toRadians(-135)), // First arg: back, second arg: top, third arg: side
      		EulerAngle.ZERO);
        right6.setCustomName("samaelright6");
        parts.put("right6", right6);
        
        Location locRight8 = loc.clone().add(-1.3, 0.15, -0.1);
        ArmorStand right8 = FurnitureManager.spawnArmorStand(locRight8, new ItemStack(Material.WHITE_BANNER, 1), null, false, false, false, false,
      		true, false, true,
      		new EulerAngle(Math.toRadians(-5), 0, Math.toRadians(-120)), // First arg: back, second arg: top, third arg: side
      		EulerAngle.ZERO);
        right8.setCustomName("samaelright8");
        parts.put("right8", right8); 
        
        Location locRight9 = loc.clone().add(-1.4, 0.3, -0.1);
        ArmorStand right9 = FurnitureManager.spawnArmorStand(locRight9, new ItemStack(Material.WHITE_BANNER, 1), null, false, false, false, false,
      		true, false, true,
      		new EulerAngle(Math.toRadians(-5), 0, Math.toRadians(-103)), // First arg: back, second arg: top, third arg: side
      		EulerAngle.ZERO);
        right9.setCustomName("samaelright9");
        parts.put("right9", right9);
        
        Location locRight10 = loc.clone().add(-1.4, 0.5, -0.15);
        ArmorStand right10 = FurnitureManager.spawnArmorStand(locRight10, new ItemStack(Material.WHITE_BANNER, 1), null, false, false, false, false,
      		true, false, true,
      		new EulerAngle(Math.toRadians(-5), 0, Math.toRadians(-90)), // First arg: back, second arg: top, third arg: side
      		EulerAngle.ZERO);
        right10.setCustomName("samaelright10");
        parts.put("right10", right10);
        
        Location locRight11 = loc.clone().add(-3, -0.5, -0.5);
        ArmorStand right11 = FurnitureManager.spawnArmorStand(locRight11, null, new ItemStack(Material.WHITE_BANNER, 1), false, false, false, false,
      		false, false, true,
      		EulerAngle.ZERO, // First arg: back, second arg: top, third arg: side
      		EulerAngle.ZERO,
      		new EulerAngle(0, Math.toRadians(270), Math.toRadians(-20)));
        right11.setCustomName("samaelright11");
        parts.put("right11", right11);
	}	
}
