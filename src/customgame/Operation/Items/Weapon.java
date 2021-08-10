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
public class Weapon extends Item {
    private int atk;
    private int reliability;
    private int totalReliability;
    private String displayName;
    public Weapon(String name, String displayName,int atk,int reliability) {
        super(name,displayName ,"atk: "+atk+"   reliability: "+reliability+"/"+reliability);
        this.atk=atk;
        this.reliability=reliability;
        this.totalReliability=reliability;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getReliability() {
        return reliability;
    }

    public void setReliability(int reliability) {
        this.reliability = reliability;
    }

    public int getTotalReliability() {
        return totalReliability;
    }

    public void setTotalReliability(int totalReliability) {
        this.totalReliability = totalReliability;
    }
    
       public void reduceReliability(){
        this.reliability--;
        this.setDescription("atk: "+atk+"   reliability: "+reliability+"/"+totalReliability);
    }

    
}
