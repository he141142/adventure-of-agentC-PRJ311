/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package advantureofagentc.operation.entities;

/**
 *
 * @author Administrator
 */
public class StaticTile {
    private String name;
    public int worldPosX;
    public int worldPosY;
    

    public StaticTile(String name, int worldPosX, int worldPosY) {
        this.name = name;
        this.worldPosX = worldPosX;
        this.worldPosY = worldPosY;
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
            
}
