/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package customgame.Operation.Entities;
import customgame.utils.DIRECTION;


/**
 *
 * @author Administrator
 */
public class EntityObject extends Object {
private DIRECTION facing;
private int strength;
private int deffence;
private int health;
private int maxHealth;
    public EntityObject(String name, int worldPosX, int worldPosY,int health) {
        super(name, worldPosX, worldPosY);
        this.maxHealth = health;
        this.health = health;
    }
    
    public EntityObject(EntityObject coppy){
       super(coppy.name, coppy.worldPosX, coppy.worldPosY);
       this.maxHealth = coppy.maxHealth;
       this.health = coppy.health;
    }
    
    
    
    public void setPosition(int dirX, int dirY) {
		this.worldPosX = dirX;
		this.worldPosY = dirY;
	}
    public DIRECTION getFacing() {
        return facing;
    }

    public void setFacing(DIRECTION facing) {
        this.facing = facing;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDeffence() {
        return deffence;
    }

    public void setDeffence(int deffence) {
        this.deffence = deffence;
    }

    public int getHealth() {
        if(health<0)health=0;
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
    
    public void damage(int amount) {
		if(amount <= 0) amount = 1;
		this.health -= amount;
    }
 
   public void heal(int amount) {
		this.health += amount;
		if(health>maxHealth)
			this.health = this.maxHealth; 
   }
   
  
   
}
