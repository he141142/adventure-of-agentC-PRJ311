/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package customgame.Operation.Items;

/**
 *
 * @author Administrator
 */
public class Amor extends Item{
private int totalReliability;
private int reliability;
private int deff;

    public Amor(String name,String displayName,int deff,int reliability) {
        super(name,displayName, "defence: "+deff+"   reliability: "+reliability+"/"+reliability);
        this.deff=deff;
        this.totalReliability=reliability;
        this.reliability=reliability;
    }

    public int getDeff() {
        return deff;
    }

    public void setDeff(int deff) {
        this.deff = deff;
    }

    public int getTotalReliability() {
        return totalReliability;
    }

    public void setTotalReliability(int totalReliability) {
        this.totalReliability = totalReliability;
    }

    public int getReliability() {
        return reliability;
    }

    public void setReliability(int reliability) {
        this.reliability = reliability;
    }
    
    public void reduceReliability(){
        this.reliability--;
        this.setDescription("defence: "+deff+"   reliability: "+reliability+"/"+totalReliability);
    }
    
}
