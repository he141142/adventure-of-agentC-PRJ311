/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package advantureofagentc.operation.level;
import advantureofagentc.operation.entities.EntityTile;
import advantureofagentc.operation.entities.Monster;
import advantureofagentc.operation.entities.StaticTile;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class Floor {
    private static Tile floor[][];
    String name;
    String[] level_data;
    private static int startPosX,startPosY;
    private List<Monster> monsters;
  
    public Floor(String[] levelData, String name,int startX,int startY,Monster ...monsterss){
      floor = new Tile[levelData.length][];
        for (int y = 0; y < levelData.length; y++) {
            floor[y] = new Tile[levelData[y].length()];
            for (int x = 0; x < floor[y].length; x++) {
                switch(levelData[y].charAt(x)){
                    case '#':
                        floor[y][x] = new Tile("wall",x,y);
                        break;
                    case '.':
                        floor[y][x] = new Tile("floor", x ,y);
                        break;
                    case '%':
                        floor[y][x] = new Tile("trap", x, y);
                        break;
                    case '@':
                        floor[y][x] = new Tile("gate", x, y);
                        break;
                }
            }
        }
        this.startPosX=startX;
        this.startPosY=startY;
        
        this.name = name;
        this.level_data=levelData;
        
        this.monsters=new ArrayList<>();
        for(Monster monster: monsterss){
            monsters.add(monster);
        }
    }
    
    public String getName(){
      return name;
    }
    public String[] getLevelData(){
       return level_data;
    }
    
    
    public Floor(Floor copy) {
		floor = new Tile[copy.getSizeY()][];
		for(int y=0;y<copy.getSizeY();y++) {
			floor[y] = new Tile[copy.getSizeX()];
			for(int x=0;x<copy.getSizeX();x++) {
				floor[y][x] = new Tile(copy.getEntityAtPos(x, y).getName(), x, y);
			}
		}
		this.monsters = copy.getMonstersInFloor();
		
    }
    
    public int getSizeX(){
       return floor[0].length;
    }
    
    public int getSizeY(){
       return floor.length;
    }
    
    public Tile getEntityAtPos(int x,int y){
      return floor[y][x];
    }
    
    public boolean disableTrap(int x,int y){
        if(floor[y][x].getName()=="trap"){
            floor[y][x]=new Tile("floor",x ,y );
            return true;
        }
        return false;
    }
    
   private List<Monster> getMonstersInFloor(){
        return this.monsters;
    }

    public static int getStartPosX() {
        return startPosX;
    }

    public static int getStartPosY() {
        return startPosY;
    }
   
    public boolean killMonster(int posX,int posY){
        for (int i = 0; i < this.monsters.size(); i++) {
            int FindPosX = this.monsters.get(i).getPosX();
            int FindPosY = this.monsters.get(i).getPosY();
            if(FindPosX == posX&&FindPosY==posY){
               this.monsters.remove(i);
               return true;
            }
        }
        return false;
    }
   
    public boolean checkIfFaceMonster(int posX,int posY){
      for (int i = 0; i < this.monsters.size(); i++) {
            int FindPosX = this.monsters.get(i).getPosX();
            int FindPosY = this.monsters.get(i).getPosY();
            if(FindPosX == posX&&FindPosY==posY){
               return true;
            }
        }
        return false;
    }
    
}
