/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package customgame.Operation.Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import customgame.Resource.World;

/**
 *
 * @author Administrator
 */
public class Map {
    private List<Floor> floors ;
    private int floorAt;
    public boolean flag=false;
    public Map(Random randomizer){
       this.floors = new ArrayList<>();
        List<Floor> temp_level = new ArrayList<>();
        this.floors.add(World.BASE_LEVEL);
        temp_level.add(World.LEVEL_FINISH);
        temp_level.add(World.LEVEL_2);
        temp_level.add(World.LEVEL_3);
        temp_level.add(World.LEVEL_5);
        temp_level.add(World.LEVEL_6);
        temp_level.add(World.LEVEL_7);
        temp_level.add(World.LEVEL_8);
        temp_level.add(World.LEVEL_9);
        temp_level.add(World.LEVEL_1);
        
        
        
        while(!temp_level.isEmpty()){
            int choice = temp_level.size() == 1 ? 0 : randomizer.nextInt(temp_level.size()-1)+1;
            this.floors.add(temp_level.get(choice));
            temp_level.remove(choice);
        }
       
    
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public int getFloorAt() {
        return floorAt;
    }
    
    public Floor getFloor(int index){
       return this.floors.get(index);
    }
    
    public Floor getNextFloor(){
        floorAt++;
        if(floorAt==floors.size()){
           this.flag = true;
           this.floorAt--;
        }
        return floors.get(floorAt);
    }
    

}
