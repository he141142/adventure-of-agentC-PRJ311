/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package advantureofagentc.operation.entities;

import advantureofagentc.operation.level.Tile;
import advantureofagentc.util.Direction;

public class EntityTile extends Tile {

	private Direction facing;
	
	private int health;
	private int maxHealth;
	protected int strength;
	protected int defence;

    public void setHealth(int health) {
        this.health = health;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
	protected int motionOffsetX;
        protected int motionOffsetY;

    public int getMotionOffsetX() {
        return motionOffsetX;
    }

    
      public int getMotionOffsetY() {
        return motionOffsetY;
    }
    public void setMotionOffset(int motionOffsetX,int motionOffsetY) {
        this.motionOffsetY = motionOffsetY;
        this.motionOffsetX = motionOffsetX;
    }
	
	
	public EntityTile(String name, int posX, int posY, int health) {
		super(name, posX, posY);
		this.facing = Direction.LEFT;
		this.maxHealth = health;
		this.health = health;
	}
	
	public void setPosition(int dirX, int dirY, boolean animated) {
		
		if(animated) {
			if(dirX > worldPosX) motionOffsetX = 32;
			else if(dirX < worldPosX) motionOffsetX = -32;
			else if(dirY > worldPosY) motionOffsetY = 32;
			else if(dirY < worldPosY) motionOffsetY = -32;
		}
		
		this.worldPosX = dirX;
		this.worldPosY = dirY;
	
	}
	
        public void Damage(int damage){
            if(damage<=0) damage=1;
            this.health-=damage;
        }
        
	public Direction getFacing() {
		return facing;
	}
	
	public void setFacing(Direction facing) {
		this.facing = facing;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	
	public void heal(int amount) {
		this.health += amount;
		if(health>maxHealth)
			this.health = this.maxHealth;
	}
	
	public void damage(int amount) {
		if(amount <= 0) amount = 1;
		this.health -= amount;
	}
	
	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getDefence() {
		return defence;
	}

	public void setDefence(int defence) {
		this.defence = defence;
	}


	
	}

