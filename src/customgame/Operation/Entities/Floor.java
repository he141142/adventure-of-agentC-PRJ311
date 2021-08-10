/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package customgame.Operation.Entities;

import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class Floor {
     private Object floor[][];
    String name;
    String[] level_data;
    private  int startPosX,startPosY;
    private ArrayList<Monster> monsters;
  
    public Floor(String[] levelData,int startPosX,int startPosY,Monster ...manyMonsters){
      floor = new Object[levelData.length][];
        for (int y = 0; y < levelData.length; y++) {
            floor[y] = new Object[levelData[y].length()];
            for (int x = 0; x < floor[y].length; x++) {
                switch(levelData[y].charAt(x)){
                    case '#':
                        floor[y][x] = new Object("wall",x,y);
                        break;
                    case '.':
                        floor[y][x] = new Object("floor", x ,y);
                        break;
                    case '%':
                        floor[y][x] = new Object("trap", x, y);
                        break;
                    case '@':
                        floor[y][x] = new Object("gate", x, y);
                        break;
                    case 'p':
			floor[y][x] = new Object("red_potion", x, y);
			break;
		     case 't':
			floor[y][x] = new Object("table", x, y);
			break;
		    case 'G':
			floor[y][x] = new Object("gold_bag", x, y);
			break;
		    case '!':
		        floor[y][x] = new Object("key", x, y);
			break;
		    case 's':
			floor[y][x] = new Object("purple_potion", x, y);
			break;
		    case 'v':
			floor[y][x] = new Object("green_potion", x, y);
			break;
		    case 'd':
			floor[y][x] = new Object("lime_potion", x, y);
			break;
		    case 'c':
			floor[y][x] = new Object("drawer", x, y);
		        break;
		    case 'g':
			floor[y][x] = new Object("yellow_potion", x, y);
			break;
		    case 'e':
			floor[y][x] = new Object("table2", x, y);
			break;    
                    case '$':
                        floor[y][x] = new Object("closed_chest", x, y);
                        break;
                    case '8':
                        floor[y][x] = new Object("door", x, y);
                        break;
                }
            }
        }
        
        this.monsters = new ArrayList<Monster>();
		for(Monster m : manyMonsters) {
			this.monsters.add(m);
	}
        this.startPosX=startPosX;
        this.startPosY=startPosY;
        
        
      
    }

    public int getStartPosX() {
        return startPosX;
    }

    public int getStartPosY() {
        return startPosY;
    }
        public int getSizeX(){
       return floor[0].length;
    }
    
    public int getSizeY(){
       return floor.length;
    }
    
    public Object getObjectAtPos(int x,int y){
      return floor[y][x];
    }
    
    public void setObjectAtPos(String name,int x,int y){
     floor[y][x] = new Object(name, x, y);
    }
    
    public boolean disableTrap(int x,int y){
        if(floor[y][x].getName()=="trap"){
            floor[y][x]=new Object("floor",x ,y );
            return true;
        }
        return false;
    }
    
    public Monster[] getMonsters(){
         Monster[] coppy = new Monster[monsters.size()];
         coppy = monsters.toArray(coppy);
         return coppy;
    }
    
    public void removeCollectedItem(int x,int y){  
        switch(this.getObjectAtPos(x, y).getName()){
            case "lime_potion":
            case "key":
            case "red_potion":
            case "green_potion":    
                setObjectAtPos("table", x, y);
                break;
            case "purple_potion":
            case "yellow_potion":
                setObjectAtPos("table2", x, y);
                break;
            case "gold_bag":
                setObjectAtPos("floor", x, y);
                break;
            case "closed_chest":
                setObjectAtPos("open_chest ", x, y);
                break;
        }
    }
    
    public void OpenDoor(int posX,int posY){
         if (this.getObjectAtPos(posX, posY).getName() == "door"){
             this.setObjectAtPos("floor", posX, posY);
         }
    }
    
    public boolean checkMonster(int x, int y) {
		for(int i=0;i<monsters.size();i++) {
			if(monsters.get(i).getWorldPosX()== x && monsters.get(i).getWorldPosY()== y)
				return true;
		}
		return false;
	}
    
    public Monster getMonsterAt(int x,int y){
        for(Monster m : monsters){
          if(m.getWorldPosX()==x && m.getWorldPosY()==y){
              return m;
          }
        }
        return null;
    }
    
    
    public boolean RemoveMonster(int x,int y){
          for (int i = 0; i < monsters.size(); i++) {
            if(monsters.get(i).getWorldPosX()==x && monsters.get(i).getWorldPosY()==y){
                monsters.remove(i);
                System.out.println("Monster has been killed!");
                return true;
            }
        }
          return false;
    }
}
