/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package customgame.Operation.Entities;

import customgame.Operation.Items.Amor;
import customgame.Operation.Items.Item;
import customgame.Operation.Items.Weapon;

/**
 *
 * @author Administrator
 */
public class Player extends EntityObject {
    private int floors;
    public boolean isFinished=false;
    public boolean inventoryOpen;
    private int gold;
    private Weapon weaponEquipped;
     private Amor armorEquipped;
     public Item[] inventory;
     private int strengthBuff;
     private int deffenceBuff;
public static final int INVENTORY_SIZE = 3;
    public Weapon getWeaponEquipped() {
        return weaponEquipped;
    }

    public void setWeaponEquipped(Weapon weaponEquipped) {
        this.weaponEquipped = weaponEquipped;
    }

    public Amor getArmorEquipped() {
        return armorEquipped;
    }

    public void setArmorEquipped(Amor armorEquipped) {
        this.armorEquipped = armorEquipped;
    }

    public Player(String name, int worldPosX, int worldPosY, int health) {
        super(name, worldPosX, worldPosY, health);
        this.floors=0;
        this.inventoryOpen=false;
        this.inventory=new Item[INVENTORY_SIZE];
    }
    
    public Player(Player coppy){
      super(coppy);
      this.floors=coppy.floors;
      this.inventoryOpen=coppy.inventoryOpen;
      this.inventory=coppy.inventory;
      this.gold=coppy.gold;
    }

    public int addFloors() {
        return ++floors;
    }

    public int getFloors() {
        return floors;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    public boolean isInventoryOpen() {
        return inventoryOpen;
    }

    public void setInventoryOpen(boolean inventoryOpen) {
        this.inventoryOpen = inventoryOpen;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public static int getINVENTORY_SIZE() {
        return INVENTORY_SIZE;
    }
    
    public Item getInventoryItem(int index){
       return inventory[index];
    }
    
    public boolean ReceiveItem(Item item) {
		for(int i=0;i<INVENTORY_SIZE;i++) {
			if(this.inventory[i] == null) {
				this.inventory[i] = item;
				return true;
			}
		}
		return false;
	}
   
    /*
    public void strengthBuff(int amount){
        int initialStrength = this.getStrength();
        if(this.weaponEquipped!=null){
            this.setStrength(initialStrength+this.weaponEquipped.getAtk());
            initialStrength = this.getStrength();
        }
        this.setStrength(initialStrength+amount);
    }
    */
    
    public void buffStrength(){
       this.strengthBuff = 10;
    }
    
    public void buffDeffence(){
        this.deffenceBuff = 10;
    }
    @Override
    public int getStrength(){
        int atk = super.getStrength();
        if(this.weaponEquipped!=null){atk+=weaponEquipped.getAtk();}
        return strengthBuff > 0 ? atk +5 : atk;
    }
    
    @Override
    public int getDeffence(){
       int def = super.getDeffence();
       if(this.armorEquipped!=null) def+=armorEquipped.getDeff();
       return deffenceBuff > 0 ? def +5 : def;
    }
   public void increaseHealth(int amount) {
		this.setMaxHealth(this.getMaxHealth()+amount); 
		this.setHealth(this.getHealth()+amount);
	}
   
   public void removeItem(int index) {
		try {
			this.inventory[index] = null;
		} catch(ArrayIndexOutOfBoundsException e) {
			return;
		}
	}
   
   public boolean damageArmor() {
		if(this.armorEquipped == null) {
			return false;
		}
		else { 
			this.armorEquipped.reduceReliability();
		}
		if(this.armorEquipped.getReliability()<= 0) {
			this.armorEquipped = null;
                        System.out.println("Your armor had been destroyed!");
			return true;
		}
		return false;
	}
   
   public boolean damageWeapon(){
       if(this.weaponEquipped==null){
          return false;
       }else{
          this.weaponEquipped.reduceReliability();
       }
       
       if(this.weaponEquipped.getReliability()<=0){
          this.weaponEquipped=null;
           System.out.println("Your weapon had been destroyed!");
          return true;
       }
       return false;
    
   }
   
   public void receiveGold(int amount){
      this.gold+=amount;
   }
}
