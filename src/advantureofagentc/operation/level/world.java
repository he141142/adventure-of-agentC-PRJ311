/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package advantureofagentc.operation.level;

import advantureofagentc.util.floorRes;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Administrator
 */
public class world {
    private int floorAt;
    private List<Floor> floors;
    public world(Random randommizer){
       this.floors = new ArrayList<Floor>();
       List<Floor> temporaryMap = new ArrayList<>();
        this.floorAt=0;
        this.floors.add(floorRes.floor_base);
      temporaryMap.add(floorRes.LEVEL_2);
      temporaryMap.add(floorRes.LEVEL_3);
       
       while(!temporaryMap.isEmpty()){
          int choice = temporaryMap.size() ==1?0: randommizer.nextInt(temporaryMap.size()-1)+1;
          this.floors.add(temporaryMap.get(choice));
          temporaryMap.remove(choice);
       }
    }
    
    public Floor getFloor(int index){
       return floors.get(index);
    }
    
    public int getFloorAt(){
      return floorAt;
    }
    
    public Floor getNextFloor(){
       floorAt++;
       if(floorAt==floors.size()){
         floorAt--;
       }
       return floors.get(floorAt);
    }
    
    public Floor prevFloor(){
        if(floorAt!=0){
         floorAt--;
        }
        return floors.get(floorAt);
    }
}
