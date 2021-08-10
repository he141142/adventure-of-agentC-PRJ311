/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package advantureofagentc.operation;
import advantureofagentc.operation.entities.EntityTile;
import advantureofagentc.operation.entities.GameLoop;
import advantureofagentc.operation.level.Floor;
import advantureofagentc.operation.level.Tile;
import advantureofagentc.util.DataSource;
import advantureofagentc.util.Direction;
import advantureofagentc.operation.level.world;
import java.util.Random;
import javax.swing.Timer;
import advantureofagentc.util.floorRes;

/**
 *
 * @author Administrator
 */
public class Operation {
    private static EntityTile player;
    private static Floor currFloor;
    public static Timer timer;
    public static Random randomizer;
    private static world world;
    public static void initializeGame(){
       randomizer= new Random();
       DataSource.init();
       world = new world(randomizer);
       player = new EntityTile("player", 2,6 , 20);
       player.setFacing(Direction.RIGHT);
       currFloor = floorRes.floor_base;
       timer = new Timer(50,new GameLoop());
       timer.start();
       /*System.out.println(currFloor.getName());
       String[] err=currFloor.getLevelData();
       for(int i =0 ;i<currFloor.getLevelData().length;i++){
           System.out.println(currFloor.getLevelData()[i]);
       }*/
    }
    public static EntityTile getPlayer(){
      return player;
    }

    public static Floor getCurrFloor() {
        return currFloor;
    }

    public static Floor getFloor(){
       return currFloor;
    }
    public static void movePlayer(int directX,int directY){
        switch(getObjectInfrontof( directX, directY).getName()){
            case "floor":
                player.setPosition(player.getPosX()+directX, player.getPosY()+ directY,true);
                break;
            case "wall":
                break;
            case "trap":
                player.Damage(randomizer.nextInt(2)+1);
                player.setPosition(directX+player.getPosX(), directY+player.getPosY(), true);
                currFloor.disableTrap(player.getPosX(), player.getPosY());
                System.out.println("Player health = "+player.getHealth());
                break;
       }
    } 
 
    public static void setPlayer(EntityTile palyer){
       player = palyer;
    }
    
    public static Tile getObjectInfrontof(int directX,int directY){
                    return currFloor.getEntityAtPos(directX+player.getPosX(), directY+player.getPosY());
    }
    
 
}
