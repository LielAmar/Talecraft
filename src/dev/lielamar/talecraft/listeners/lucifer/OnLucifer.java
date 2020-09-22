package dev.lielamar.talecraft.listeners.lucifer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import dev.lielamar.talecraft.Main;
import dev.lielamar.talecraft.constants.ConstantItems;
import dev.lielamar.talecraft.players.CustomPlayer;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.trait.Equipment;
import net.citizensnpcs.api.trait.trait.Equipment.EquipmentSlot;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.TextComponent;

public class OnLucifer implements Listener {

	public static final String prefix = ChatColor.RED + "Lucifer: " + ChatColor.GRAY;
	
	private List<UUID> players;
	
	public OnLucifer() {
		this.players = new ArrayList<UUID>();
	}
	
	@EventHandler
	public void onNPC(NPCRightClickEvent e) {
		Player p = (Player)e.getClicker();
		
		if(e.getNPC().getName().toLowerCase().contains("lucifer")) {
			CustomPlayer cp = new CustomPlayer(p);
			int lvl = cp.getLuciferlevel();
			
			if(e.getNPC().getTrait(Equipment.class) == null)
				e.getNPC().addTrait(Equipment.class);
			
			if(!this.players.contains(p.getUniqueId()))
				runLucifer(cp, lvl, e.getNPC());
		}
	}

	public void runLucifer(CustomPlayer cp, int level, NPC npc) {
		Player p = cp.getP();
		
		if(level == 0) {
			this.players.add(p.getUniqueId());
			p.sendMessage(prefix + p.getName() + " ייה");
			
			// First message
			new BukkitRunnable() {
				int counter = 0;
				
				@Override
				public void run() {
					if(counter == 40) {
						p.sendMessage(prefix + ".ריבסהל חמשא ינא לבא ,לובלבה תא ןיבמ ינא"); // אני מבין את הבלבול אבל אני אשמח להסביר
						p.sendMessage(prefix + ".רופיסה לכ אל הז לבא ,ילש חא אוה לאמס ,ןכ"); // כן, סמאל הוא אח שלי, אבל זה לא כל הסיפור
					} else if(counter == 240) {
						p.sendMessage(prefix + "םיכאלמה לכ תא אלכ לאמס םינש הברה ינפל"); // לפני הרבה שנים סמאל כלא את כל המלאכים
						p.sendMessage(prefix + ",חורבל חילצהש דיחיה ינאו ןדע ןגב"); // בגן עדן ואני היחיד שהצליח לברוח
						p.sendMessage(prefix + ".ייתוחוכ בור תא יתדביא ,יתחרבשכ לבא"); // אבל כ
					} else if(counter == 440) {
						p.sendMessage(prefix + ",םלועה תא סרה לאמסו םכתא חצינ ןוקרדהשכ"); // כשהדרקון ניצח אתכם וסמאל הרס את העולם
						p.sendMessage(prefix + ".הפל רגתשהל ידכ ייתוחוכ תיראשב יתשמתשה ינא"); // אני השתמשתי בשארית כוחותיי כדי להשתגר לפה
					} else if(counter == 640) {
						p.sendMessage(prefix + ",םיחוטב ויהת אל םעפ ףא םתא לאמס תא חצננ אל םא ירעצל");
						p.sendMessage(prefix + ".וילע רובגל לוכיש דיחיה ינא .טושפ הזכ אל הז לבא");
					} else if(counter == 840) {
						p.sendMessage(prefix + "םיכאלמה לש דובאה חוכהש תרפסמ הנשי הדגא");
						p.sendMessage(prefix + ",המצוע יבר ,םידחוימ םיצפחב אצמנ");
						p.sendMessage(prefix + "!ל'גנאקרא לש חוכ לבקי ,םלוכב קיזחיש ימו");
					} else if(counter == 1040) {
						p.sendMessage(prefix + "וכרטצת םתא ,לאמס תא חצנל םיצור םתא םא");
						p.sendMessage(prefix + "דיחיה ינא יכ הלאה םיצפחה לכ תא יל איבהל");
						p.sendMessage(prefix + ".םלוכב טולשל לוכיש");
					} else if(counter == 1240) {
						p.sendMessage(prefix + "ןכלו דבל הז תא תושעל ולכות אל םתא");
						p.sendMessage(prefix + ",רחא םלועמ םיפסונ םדא ינב הפל יתרגיש");
						p.sendMessage(prefix + ".דימשה לאמס ותוא םגש");
					} else if(counter == 1440) {
						p.sendMessage(prefix + "!דימתלו תחא לאמס תא סיבהל לכונש ןימאמ ינא ,דחיב");
					} else if(counter == 1640) {
						p.sendMessage(prefix + ".ךשמהב םכל רוזעתש ינממ הנטק הנתמ הנה");
						p.getInventory().addItem(ConstantItems.selectBook());
						cp.setLuciferLevel(1);
						players.remove(p.getUniqueId());
					}
					counter++;
				}
			}.runTaskTimer(Main.getInstance(), 0, 1L);
		} else if(level ==  1) {
			this.players.add(p.getUniqueId());
			if(npc.getTrait(Equipment.class).get(EquipmentSlot.BOOTS) == null) {
				int index = -1;
				for(int i = 0; i < 36; i++) {
					if(p.getInventory().getItem(i) == null || p.getInventory().getItem(i).getType() == Material.AIR) continue;
					if(isSimilar(p.getInventory().getItem(i), ConstantItems.shoesOfVidar()))
						index = i;
				}
				
				if(index == -1) {
					new BukkitRunnable() {
						int counter = 0;
						@Override
						public void run() {
							if(counter == 0) {
								p.sendMessage(prefix + ",תוידגאה םיילענה תא הז ךרטצאש ןושארה רבדה"); // ,תוידגאה םיילענה תא הז ךרטצאש ןושארה רבדה
								p.sendMessage(prefix + "!ראדיו לאה לש םיילענה");
							} else if(counter == 200) {
								p.sendMessage(prefix + "ןכותב תוליכמ ולאה םיילענה");
								p.sendMessage(prefix + "!התומכ ןיאש הנגה");
								
								TextComponent txt = new TextComponent(prefix + "" + ChatColor.YELLOW + "" + ChatColor.BOLD + "!תוידגאה םיילענה תא יל ואיבה");
								txt.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/luciferitem shoesofvidar"));
								p.spigot().sendMessage(txt);
								players.remove(p.getUniqueId());
							}
							counter++;
						}
					}.runTaskTimer(Main.getInstance(), 0, 1L);
				} else {
					p.getInventory().removeItem(p.getInventory().getItem(index));
					p.updateInventory();
					npc.getTrait(Equipment.class).set(EquipmentSlot.BOOTS, ConstantItems.shoesOfVidar());
					players.remove(p.getUniqueId());
					runLucifer(cp, level, npc);
				}
			} else if(npc.getTrait(Equipment.class).get(EquipmentSlot.LEGGINGS) == null) {
				int index = -1;
				for(int i = 0; i < 36; i++) {
					if(p.getInventory().getItem(i) == null || p.getInventory().getItem(i).getType() == Material.AIR) continue;
					if(isSimilar(p.getInventory().getItem(i), ConstantItems.hideOfLeviathan()))
						index = i;
				}
				
				if(index == -1) {
					new BukkitRunnable() {
						int counter = 0;
						@Override
						public void run() {
							if(counter == 0) {
								p.sendMessage(prefix + ",תועודיה םייסנכמה תא הז ךרטצאש ינשה קלחה"); // ,תוידגאה םיילענה תא הז ךרטצאש ןושארה רבדה
								p.sendMessage(prefix + "!ן'תיוול לש םייסנכמה");
							} else if(counter == 200) {
								p.sendMessage(prefix + "ן!תורידח יתלב ןה ולאה םייסנכמה");
								
								TextComponent txt = new TextComponent(prefix + "" + ChatColor.YELLOW + "" + ChatColor.BOLD + "!תוידגאה םייסנכמה תא יל ואיבה");
								txt.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/luciferitem hideofleviathan"));
								p.spigot().sendMessage(txt);
								players.remove(p.getUniqueId());
							}
							counter++;
						}
					}.runTaskTimer(Main.getInstance(), 0, 1L);
				} else {
					p.getInventory().removeItem(p.getInventory().getItem(index));
					p.updateInventory();
					npc.getTrait(Equipment.class).set(EquipmentSlot.LEGGINGS, ConstantItems.hideOfLeviathan());
					players.remove(p.getUniqueId());
					runLucifer(cp, level, npc);
				}
			} else if(npc.getTrait(Equipment.class).get(EquipmentSlot.HELMET) == null) {
				int index = -1;
				for(int i = 0; i < 36; i++) {
					if(p.getInventory().getItem(i) == null || p.getInventory().getItem(i).getType() == Material.AIR) continue;
					if(isSimilar(p.getInventory().getItem(i), ConstantItems.crownOfImmortality()))
						index = i;
				}
				
				if(index == -1) {
					new BukkitRunnable() {
						int counter = 0;
						@Override
						public void run() {
							if(counter == 0) {
								p.sendMessage(prefix + ",ותומכ ןיאש רתכה אוה המישרב ישילשה ץפחה"); // ,תוידגאה םיילענה תא הז ךרטצאש ןושארה רבדה
								p.sendMessage(prefix + "!תוומ-לאה רתכ");
							} else if(counter == 200) {
								p.sendMessage(prefix + "!תוומ-לא ןבל ךופהי רתכה שבול");
								
								TextComponent txt = new TextComponent(prefix + "" + ChatColor.YELLOW + "" + ChatColor.BOLD + "!תוומ-לאה רתכ תא יל ואיבה");
								txt.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/luciferitem crownofimmortality"));
								p.spigot().sendMessage(txt);
								players.remove(p.getUniqueId());
							}
							counter++;
						}
					}.runTaskTimer(Main.getInstance(), 0, 1L);
				} else {
					p.getInventory().removeItem(p.getInventory().getItem(index));
					p.updateInventory();
					npc.getTrait(Equipment.class).set(EquipmentSlot.HELMET, ConstantItems.crownOfImmortality());
					players.remove(p.getUniqueId());
					runLucifer(cp, level, npc);
				}
			} else if(npc.getTrait(Equipment.class).get(EquipmentSlot.CHESTPLATE) == null) {
				int index = -1;
				for(int i = 0; i < 36; i++) {
					if(p.getInventory().getItem(i) == null || p.getInventory().getItem(i).getType() == Material.AIR) continue;
					if(isSimilar(p.getInventory().getItem(i), ConstantItems.egilWings()))
						index = i;
				}
				
				if(index == -1) {
					new BukkitRunnable() {
						int counter = 0;
						@Override
						public void run() {
							if(counter == 0) {
								p.sendMessage(prefix + ",תודחוימה םייפנכה אוה ונלש אבה ץפחה"); // ,תוידגאה םיילענה תא הז ךרטצאש ןושארה רבדה
								p.sendMessage(prefix + "!ליגא לש םייפנכה");
							} else if(counter == 200) {
								p.sendMessage(prefix + "!הפועתה תלוכי תא יל וריזחי הלאה םייפנכה");
								
								TextComponent txt = new TextComponent(prefix + "" + ChatColor.YELLOW + "" + ChatColor.BOLD + "!םייפנכה תא יל ואיבה");
								txt.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/luciferitem egilwings"));
								p.spigot().sendMessage(txt);
								players.remove(p.getUniqueId());
							}
							counter++;
						}
					}.runTaskTimer(Main.getInstance(), 0, 1L);
				} else {
					p.getInventory().removeItem(p.getInventory().getItem(index));
					p.updateInventory();
					npc.getTrait(Equipment.class).set(EquipmentSlot.CHESTPLATE, ConstantItems.egilWings());
					players.remove(p.getUniqueId());
					runLucifer(cp, level, npc);
				}
			} else if(npc.getTrait(Equipment.class).get(EquipmentSlot.HAND) == null) {
				int index = -1;
				for(int i = 0; i < 36; i++) {
					if(p.getInventory().getItem(i) == null || p.getInventory().getItem(i).getType() == Material.AIR) continue;
					if(isSimilar(p.getInventory().getItem(i), ConstantItems.excalibur()))
						index = i;
				}
				
				if(index == -1) {
					new BukkitRunnable() {
						int counter = 0;
						@Override
						public void run() {
							if(counter == 0) {
								p.sendMessage(prefix + ",לוכי לכה קשנה תא ךרטצא ינא ,ףוסבלו"); // ,תוידגאה םיילענה תא הז ךרטצאש ןושארה רבדה
								p.sendMessage(prefix + "!רבילקסקא - תידגאה ברחה");
							} else if(counter == 200) {
								p.sendMessage(prefix + "!ןבאהמ ברחה תא ףולשל םילוכי םייוארה קר");
								
								TextComponent txt = new TextComponent(prefix + "" + ChatColor.YELLOW + "" + ChatColor.BOLD + "!רבילקסקא תא יל ואיבה");
								txt.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/luciferitem excalibur"));
								p.spigot().sendMessage(txt);
								players.remove(p.getUniqueId());
							}
							counter++;
						}
					}.runTaskTimer(Main.getInstance(), 0, 1L);
				} else {
					p.getInventory().removeItem(p.getInventory().getItem(index));
					p.updateInventory();
					npc.getTrait(Equipment.class).set(EquipmentSlot.HAND, ConstantItems.excalibur());
					players.remove(p.getUniqueId());
					runLucifer(cp, level, npc);
				}
			} else {
				// TODO: Lucifer flies
			}
		}
	}
	
	/**
	 * @param item1       First Item
	 * @param item2       Second Item
	 * @return            Whether or not the two items are the same
	 */
	public boolean isSimilar(ItemStack item1, ItemStack item2) {
		if(item1.hasItemMeta() && item2.hasItemMeta() && item1.getItemMeta().hasDisplayName() && item2.getItemMeta().hasDisplayName() && item1.getItemMeta().getDisplayName().equalsIgnoreCase(item2.getItemMeta().getDisplayName())
				&& item1.getType() == item2.getType() && areEqual(item1.getEnchantments(), item2.getEnchantments()))
			return true;
		return false;
	}
	
	private boolean areEqual(Map<Enchantment, Integer> first, Map<Enchantment, Integer> second) {
	    if(first.size() != second.size()) {
	        return false;
	    }
	 
	    return first.entrySet().stream()
	      .allMatch(e -> e.getValue().equals(second.get(e.getKey())));
	}
}
