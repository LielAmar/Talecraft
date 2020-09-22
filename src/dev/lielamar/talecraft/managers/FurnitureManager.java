package dev.lielamar.talecraft.managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.util.EulerAngle;

public class FurnitureManager {
	
	@SuppressWarnings("deprecation")
	public static ArmorStand spawnArmorStand(Location loc, ItemStack headItem, ItemStack handItem, ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots,
			boolean vis, boolean grav, boolean canPick, boolean customNameVis, boolean small, boolean basePlate, boolean arms,
			EulerAngle headPose, EulerAngle bodyPose, EulerAngle leftArmPose, EulerAngle rightArmPose, EulerAngle leftLegPose ,EulerAngle rightLegPose) {
		
		ArmorStand as = loc.getWorld().spawn(loc, ArmorStand.class);
		
		as.setHelmet(headItem);
		as.setItemInHand(handItem);
		
		as.setHelmet(helmet);
		as.setChestplate(chestplate);
		as.setLeggings(leggings);
		as.setBoots(boots);
		
		as.setVisible(vis);
		as.setGravity(grav);
		as.setCanPickupItems(canPick);
		as.setCustomNameVisible(customNameVis);
		as.setSmall(small);
		
		as.setBasePlate(basePlate);
		as.setArms(arms);
		
		as.setHeadPose(headPose);
		as.setBodyPose(bodyPose);
		as.setLeftArmPose(leftArmPose);
		as.setRightArmPose(rightArmPose);
		as.setLeftLegPose(leftLegPose);
		as.setRightLegPose(rightLegPose);
		
		return as;
	}
	
	@SuppressWarnings("deprecation")
	public static ArmorStand spawnArmorStand(Location loc, ItemStack headItem, ItemStack handItem,
			boolean vis, boolean grav, boolean canPick, boolean customNameVis, boolean small, boolean basePlate, boolean arms,
			EulerAngle headPose, EulerAngle bodyPose) {
		
		ArmorStand as = loc.getWorld().spawn(loc, ArmorStand.class);
		
		as.setHelmet(headItem);
		as.setItemInHand(handItem);
		
		as.setVisible(vis);
		as.setGravity(grav);
		as.setCanPickupItems(canPick);
		as.setCustomNameVisible(customNameVis);
		as.setSmall(small);
		
		as.setBasePlate(basePlate);
		as.setArms(arms);
		
		as.setHeadPose(headPose);
		as.setBodyPose(bodyPose);
		
		return as;
	}
	
	@SuppressWarnings("deprecation")
	public static ArmorStand spawnArmorStand(Location loc, ItemStack headItem, ItemStack handItem,
			boolean vis, boolean grav, boolean canPick, boolean customNameVis, boolean small, boolean basePlate, boolean arms,
			EulerAngle headPose, EulerAngle bodyPose, EulerAngle handPose) {
		
		ArmorStand as = loc.getWorld().spawn(loc, ArmorStand.class);
		
		as.setHelmet(headItem);
		as.setItemInHand(handItem);
		
		as.setVisible(vis);
		as.setGravity(grav);
		as.setCanPickupItems(canPick);
		as.setCustomNameVisible(customNameVis);
		as.setSmall(small);
		
		as.setBasePlate(basePlate);
		as.setArms(arms);
		
		as.setHeadPose(headPose);
		as.setBodyPose(bodyPose);
		as.setRightArmPose(handPose);
		
		return as;
	}
	
	public static ArmorStand getNewArmorStand(Location location, boolean visible, boolean mini) {
        ArmorStand as = location.getWorld().spawn(location, ArmorStand.class);
 
        as.setBasePlate(false);
        as.setArms(true);
        as.setVisible(visible);
        as.setCanPickupItems(false);
        as.setGravity(false);
        as.setSmall(mini);
 
        return as;
    }
	
	@SuppressWarnings("deprecation")
	public static ItemStack skull(String owner) {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setOwningPlayer(Bukkit.getOfflinePlayer(owner));
		item.setItemMeta(meta);
		return item;
	}
}
