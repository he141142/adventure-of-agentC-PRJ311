/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package customgame.Operation.Entities;

/**
 *
 * @author Administrator
 */
public class Object {
    
    public String name;
    public int worldPosX;
    public int worldPosY;
    private boolean isCollectible=false;

    public Object(String name, int PosX, int PosY) {
        this.name = name;
        this.worldPosX = PosX;
        this.worldPosY = PosY;
        if(name == "red_potion" || name == "gold_bag" || name == "key" || name == "purple_potion" || name == "lime_potion" || name == "green_potion" || name == "yellow_potion" || name == "closed_chest")
			this.isCollectible = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWorldPosX() {
        return worldPosX;
    }

    public void setWorldPosX(int worldPosX) {
        this.worldPosX = worldPosX;
    }

    public int getWorldPosY() {
        return worldPosY;
    }

    public void setWorldPosY(int worldPosY) {
        this.worldPosY = worldPosY;
    }

    public boolean isIsCollectible() {
        return isCollectible;
    }
    
    
    
}
