/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package customgame.Resource;

import customgame.Operation.Items.Amor;
import customgame.Operation.Items.Item;
import customgame.Operation.Items.Weapon;

/**
 *
 * @author Administrator
 */
public class Items {
    public static final Item HP_POTION = new Item("hp_potion", "Health potion", "Restores 10 HP");
	public static final Item KEY = new Item("small_key", "Small key", "Can be used once to open a locked door");
	public static final Item STR_FRUIT = new Item("str_fruit", "Fruit of Strength", "Temporary increases strenght");
	public static final Item POISON = new Item("psn_potion", "Mysterious potion", "Its effect is unknown");
	public static final Item DEF_POTION = new Item("def_potion", "Defence potion", "Its effect is unknown");
	public static final Item MAX_POTION = new Item("max_potion", "Max potion", "Increases max HP");
	public static final Weapon SHORT_SWORD = new Weapon("short_sword", "Short Sword", 5,5);
	public static final Weapon STICK = new Weapon("stick", "Stick", 7,8);
	public static final Weapon AXE = new Weapon("axe", "Axe", 8,9);
	public static final Weapon GREAT_SWORD = new Weapon("great_sword", "Great Sword", 9,13);
	public static final Amor LIGHT_ARMOR = new Amor("light_armor", "Light Armor", 4,7);
	public static final Amor BRONZE_ARMOR = new Amor("bronze_armor", "Bronze Armor", 5,8);
	public static final Amor MEDIEVAL_ARMOR = new Amor("medieval_armor", "Medieval Armor",7,10);
	public static final Amor MISTERIOUS_ARMOR = new Amor("misterious_armor", "Misterious Armor", 9,13);
}
