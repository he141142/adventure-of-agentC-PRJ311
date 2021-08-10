/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package customgame.Operation;

/**
 *
 * @author Administrator
 */
import customgame.Operation.Entities.EntityObject;
import customgame.Operation.Entities.Floor;
import customgame.Operation.Entities.Map;
import customgame.Operation.Entities.Monster;
import customgame.Operation.Entities.Object;
import customgame.Operation.Entities.Player;
import customgame.Operation.Items.Item;
import customgame.Resource.DataSource;
import customgame.Resource.Items;
import customgame.gui.Keyboard;
import customgame.gui.Render;
import customgame.utils.DIRECTION;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

public class Operation {
    private static Map map;
    private static Floor currFloor;
    public static Player player;
    public static Player coppy;
    private static Timer timer;
    private static Timer timer2;
    private static Random randomizer;
    private static Monster[] monsters;
    static Thread t1;
    static Thread t2;
    static Thread t3;
    public static boolean MainMenu;
    public static int select ;
    public static boolean mutation;
    public static void Start(){
       DataSource.Init();
       
       
       InitGame();
       setThread();
       timer=new Timer(12, new ActionListener() {

           @Override
       public void actionPerformed(ActionEvent e) {
        if(MainMenu){
           if(Keyboard.isKeyDown(KeyEvent.VK_W)){
               if(select > 0){ 
                  select--;
               }
           }else if(Keyboard.isKeyDown(KeyEvent.VK_S)){
               if(select < 1){
                  select++;
               }
           }else if(Keyboard.isKeyDown(KeyEvent.VK_ENTER)){
               switch(Render.optionsMenu[select]){
                   case Render.START_GAME :
                       MainMenu = false;
                       break;
                   case Render.QUIT_GAME :
                       System.exit(0);
                       break;
               }
           }
           
        }else{
        if(Keyboard.isKeyDown(KeyEvent.VK_W)){
           MovePlayer(0, -1);
        }
        else if(Keyboard.isKeyDown(KeyEvent.VK_A)){
           MovePlayer(-1, 0);
        Operation.getPlayer().setFacing(DIRECTION.LEFT);        
        }
        else if(Keyboard.isKeyDown(KeyEvent.VK_S)){
          MovePlayer(0, 1);
        }
        else if(Keyboard.isKeyDown(KeyEvent.VK_D)){
          MovePlayer(1, 0);
        getPlayer().setFacing(DIRECTION.RIGHT);        
        }
        else if(Keyboard.isKeyDown(KeyEvent.VK_I)){
         OpenInventoryUI();
        }
        else if(Keyboard.isKeyDown(KeyEvent.VK_E)){
         ControlInteractiveItem();
        }
        }     
           }
       });
       timer.start();
       t1.start();
       t2.start();
       t3.start();
       
    }
    
    public static void setThread(){
      mutation = false;
       t1= new Thread(){
  
           @Override
           public void run() {
               while(!checkIfPlayerDied()){
                  while(!mutation){
                   try {
                       moveMonsters();
                       Thread.sleep(200);
                   } catch (InterruptedException ex) {
                       Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
                   }
                  }
                  
                  synchronized(t2){
                    t2.notify();
                  }
                  synchronized(t3){
                    t3.notify();
                  }
                  synchronized(this){
                      try {
                          this.wait();
                      } catch (InterruptedException ex) {
                          Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
                      }
                  }
               }
           }
          
       };
       
       t2 = new Thread(){

           @Override
           public void run() {
               while(!checkIfPlayerDied()){
               synchronized(this){
                   try {
                       this.wait();
                   } catch (InterruptedException ex) {
                       Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
                  while(mutation){
                   try {
                       moveMonster2();
                       Thread.sleep(150);
                   } catch (InterruptedException ex) {
                       Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
                   }
                  }
                  
                  
               }
           }
         
       };
       
       t3 = new Thread(){
           @Override
           public void run() {
              while(!checkIfPlayerDied()){
                 synchronized(this){
                     try {
                         this.wait();
                     } catch (InterruptedException ex) {
                         Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
                 while(mutation){
                     try {
                         notice(5);
                         mutation = false;
                         //int health = player.getHealth();
                         int posX = player.worldPosX;
                         int posY = player.worldPosY; 
                         int tempGold = player.getGold();
                         player = new Player(coppy);
                         player.setGold(player.getGold()+tempGold);
                         player.setWorldPosX(posX);
                         player.setWorldPosY(posY);
                         player.inventoryOpen = false;
                         //player.setHealth(health);
                     } catch (InterruptedException ex) {
                         Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
                 synchronized(t1){
                     t1.notify();
                  }
              }
           }
          
       };
    }
            

    public static Floor getCurrFloor() {
        return currFloor;
    }

    public static Player getPlayer() {
        return player;
    }
    
    public static Monster[] getMonsters(){
        return monsters;
    }
    
    public static void InitGame(){
        randomizer= new Random();
         map = new Map(randomizer);
        currFloor= map.getFloor(0);
        monsters = currFloor.getMonsters();
        player = new Player("player", 2, 2, 100);
        player.setFacing(DIRECTION.RIGHT);
        /*
        currFloor = new Floor(new String[]{
                    "######################",
                    "##.........######....#",
                    "##.........##..##....#",
                    "##...................#",
                    "##.........######....#",
                    "##.........######....#",
                    "###################..#",
                    "#.#################..#",
                    "#..###.........####..#",
                    "#...##.........###...#",
                    "#....................#",
                    "#...##.........#######",
                    "#..###.........#######",
                    "#.######.......#######",
                    "######################",
                    
    } );*/
        MainMenu =true;
        select = 0;
      
    }
    
    public static void MovePlayer(int dirX,int dirY){
        if(player.isInventoryOpen()){return;}
        
        if(checkIfPlayerDied()){
           return;
        }
        if(player.isFinished){
           return;
        }
        
        if(fightMonster(dirX, dirY))return;
       switch(getObjectInfrontOf(player, dirX, dirY).getName()){
           case "floor":
               player.setPosition(player.getWorldPosX()+dirX, dirY+player.getWorldPosY());
               break;
           case "wall":   
               break;
            -case "gate": 0\=-0987Y6T5R4Q1       `123cxáDFUIOP[Ư\78/]               currFloor = map.getNextFloor();
               player.setPosition(currFloor.getStartPosX(), currFloor.getStartPosY());
               player.addFloors();
               if(map.flag==true){
                 player.isFinished = true;
               }
               monsters=currFloor.getMonsters();
               break;    
           case "trap":
               player.damage(randomizer.nextInt(2)+1);
               player.setPosition(player.getWorldPosX()+dirX, dirY+player.getWorldPosY());
               currFloor.disableTrap(player.getWorldPosX(), player.getWorldPosY());
               isMonster=true;
               System.out.println("Decrease health,player health: "+player.getHealth() );
               break;
          
       }
       //moveMonsters();
		
    }
    
    
    
    
    private static void moveMonster2(){
        for(Monster monster : monsters) {
			if(monster.getHealth() <= 0)
				continue;
                
                        switch(randomizer.nextInt(4)) {
				case 0:
					if(currFloor.checkMonster(monster.getWorldPosX()+1, monster.getWorldPosY())) {
						return;
					}
					else if(monster.getWorldPosX()+1 == player.getWorldPosX()&& monster.getWorldPosY()== player.getWorldPosY()) {
						player.damage(monster.getStrength() - player.getDeffence()/3);
						if(player.damageArmor())
                                                    System.out.println("Your armor broke!");
						break;
					}	
					if(getObjectInfrontOf(monster, 1, 0).getName() == "floor") {
						monster.setPosition(monster.getWorldPosX()+1, monster.getWorldPosY());
						break;
					}
				case 1:
					if(currFloor.checkMonster(monster.getWorldPosX()-1, monster.getWorldPosY())) {
						return;
					}
					else if(monster.getWorldPosX()-1 == player.getWorldPosX()&& monster.getWorldPosY()== player.getWorldPosY()) {
						player.damage(monster.getStrength() - player.getDeffence()/3);
						if(player.damageArmor())
                                                    System.out.println("Your armor broke!");
						break;
					}	
					if(getObjectInfrontOf(monster, -1, 0).getName() == "floor") {
						monster.setPosition(monster.getWorldPosX()-1, monster.getWorldPosY());
						break;
					}
				case 2:
					if(currFloor.checkMonster(monster.getWorldPosX(), monster.getWorldPosY()+1)) {
						return;
					}
					else if(monster.getWorldPosX()== player.getWorldPosX()&& monster.getWorldPosY()+1 == player.getWorldPosY()) {
						player.damage(monster.getStrength() - player.getDeffence()/3);
						if(player.damageArmor())
                                                    System.out.println("Your armor broke!");
						break;
					}	
					if(getObjectInfrontOf(monster, 0, 1).getName() == "floor") {
						monster.setPosition(monster.getWorldPosX(), monster.getWorldPosY()+1);
						break;
					}
				case 3:
					if(currFloor.checkMonster(monster.getWorldPosX(), monster.getWorldPosY()-1)) {
						return;
					}
					else if(monster.getWorldPosX()== player.getWorldPosX()&& monster.getWorldPosY()-1 == player.getWorldPosY()) {
						player.damage(monster.getStrength() - player.getDeffence()/3);
						if(player.damageArmor())
                                                    System.out.println("Your armor broke!");
						break;
					}	
					if(getObjectInfrontOf(monster, 0, -1).getName() == "floor") {
						monster.setPosition(monster.getWorldPosX(), monster.getWorldPosY()-1);
						break;
					}
				}
                
    
        }
        
    }
    
    private static void moveMonsters() {
		for(Monster monster : monsters) {
			if(monster.getHealth() <= 0)
				continue;
			
			if(!monster.huntPlayer) {
				switch(randomizer.nextInt(4)) {
				case 0:
					if(currFloor.checkMonster(monster.getWorldPosX()+1, monster.getWorldPosY())) {
						return;
					}
					else if(monster.getWorldPosX()+1 == player.getWorldPosX()&& monster.getWorldPosY()== player.getWorldPosY()) {
						player.damage(monster.getStrength() - player.getDeffence()/3);
						if(player.damageArmor())
                                                    System.out.println("Your armor broke!");
						break;
					}	
					if(getObjectInfrontOf(monster, 1, 0).getName() == "floor") {
						monster.setPosition(monster.getWorldPosX()+1, monster.getWorldPosY());
						break;
					}
				case 1:
					if(currFloor.checkMonster(monster.getWorldPosX()-1, monster.getWorldPosY())) {
						return;
					}
					else if(monster.getWorldPosX()-1 == player.getWorldPosX()&& monster.getWorldPosY()== player.getWorldPosY()) {
						player.damage(monster.getStrength() - player.getDeffence()/3);
						if(player.damageArmor())
                                                    System.out.println("Your armor broke!");
						break;
					}	
					if(getObjectInfrontOf(monster, -1, 0).getName() == "floor") {
						monster.setPosition(monster.getWorldPosX()-1, monster.getWorldPosY());
						break;
					}
				case 2:
					if(currFloor.checkMonster(monster.getWorldPosX(), monster.getWorldPosY()+1)) {
						return;
					}
					else if(monster.getWorldPosX()== player.getWorldPosX()&& monster.getWorldPosY()+1 == player.getWorldPosY()) {
						player.damage(monster.getStrength() - player.getDeffence()/3);
						if(player.damageArmor())
                                                    System.out.println("Your armor broke!");
						break;
					}	
					if(getObjectInfrontOf(monster, 0, 1).getName() == "floor") {
						monster.setPosition(monster.getWorldPosX(), monster.getWorldPosY()+1);
						break;
					}
				case 3:
					if(currFloor.checkMonster(monster.getWorldPosX(), monster.getWorldPosY()-1)) {
						return;
					}
					else if(monster.getWorldPosX()== player.getWorldPosX()&& monster.getWorldPosY()-1 == player.getWorldPosY()) {
						player.damage(monster.getStrength() - player.getDeffence()/3);
						if(player.damageArmor())
                                                    System.out.println("Your armor broke!");
						break;
					}	
					if(getObjectInfrontOf(monster, 0, -1).getName() == "floor") {
						monster.setPosition(monster.getWorldPosX(), monster.getWorldPosY()-1);
						break;
					}
				}
			} else {
		              int deltaX = player.getWorldPosX() - monster.getWorldPosX();
                              int deltaY = player.getWorldPosY() - monster.getWorldPosY();
                              float arg0 = (float)deltaY/(float)deltaX;
                              
                              if((arg0>1||arg0<-1) && player.getWorldPosY()>monster.getWorldPosY()){
                                     if(monster.getWorldPosY()+1==player.getWorldPosY() &&  monster.getWorldPosX()==player.getWorldPosX()){
                                         player.damage(monster.getStrength()-player.getDeffence()/3);
                                         System.out.println("Your are damaged by monster: "+monster.getName());
                                     }
                                     else if(getObjectInfrontOf(monster, 0, 1).getName()=="floor"){
                                        monster.setPosition(monster.getWorldPosX(), monster.getWorldPosY()+1);
                                     }
                              }
                              else if((arg0>1||arg0<-1)&& player.getWorldPosY()<monster.getWorldPosY()){
                                  if(monster.getWorldPosY()-1==player.getWorldPosY()&&monster.getWorldPosX()==player.getWorldPosX()){
                                     player.damage(monster.getStrength()-player.getDeffence());
                                        System.out.println("Your are damaged by monster: "+monster.getName());
                                  }
                                  else if(getObjectInfrontOf(monster, 0, -1).getName()=="floor"){
                                     monster.setPosition(monster.getWorldPosX(),monster.getWorldPosY() -1);
                                  }
                              }
                              else if((arg0<1 && arg0>-1) && player.getWorldPosX()>monster.getWorldPosX()){
                                  if(monster.getWorldPosY()==player.getWorldPosY() && monster.getWorldPosX()+1==player.getWorldPosX()){
                                    player.damage(monster.getStrength()-player.getDeffence()/3);
                                        System.out.println("Your are damaged by monster: "+monster.getName());
                                        
                                  }else if(getObjectInfrontOf(monster, 1, 0).getName()=="floor"){
                                        monster.setPosition(monster.getWorldPosX()+1, monster.getWorldPosY());
                                  }
                              }
                              else if((arg0<1 && arg0 >-1) && player.getWorldPosX()<monster.getWorldPosX()){
                                 if(monster.getWorldPosY()==player.getWorldPosY() && monster.getWorldPosX()-1==player.getWorldPosX()){
                                     player.damage(monster.getStrength()-player.getDeffence()/3);
                                        System.out.println("Your are damaged by monster: "+monster.getName());
                                 }
                                 else if(getObjectInfrontOf(monster, -1, 0).getName()=="floor"){
                                       monster.setPosition(monster.getWorldPosX()-1, monster.getWorldPosY());
                                 }
                              }
			}
		}
	}
    public static boolean isMonster = false;
    
    public static Object getObjectInfrontOf(EntityObject player,int dirX,int dirY){
       return currFloor.getObjectAtPos(dirX+ player.getWorldPosX(), dirY+player.getWorldPosY());
    }

    public static void setPlayer( Player p) {
        player = p;
    }
     

    
    public static void OpenInventoryUI(){
       if(player.getHealth()>0){
          player.setInventoryOpen(!player.isInventoryOpen());
       }
    }
    
    	private static void pickupItem(int itemPosX, int itemPosY) {
		switch(currFloor.getObjectAtPos(itemPosX, itemPosY).getName()) {
		case "red_potion":
			if(player.ReceiveItem(Items.HP_POTION)) {
				currFloor.removeCollectedItem(itemPosX, itemPosY);
			}
			else {
			}
			break;
		case "gold_bag":
			int g = randomizer.nextInt(5)+3;
			player.receiveGold(g);
			currFloor.removeCollectedItem(itemPosX, itemPosY);
			break;
		case "key":
			if(player.ReceiveItem(Items.KEY)) {
				currFloor.removeCollectedItem(itemPosX, itemPosY);
			}
			else {
			}
			break;
		case "purple_potion":
			if(player.ReceiveItem(Items.STR_FRUIT)) {
				currFloor.removeCollectedItem(itemPosX, itemPosY);
			}
			else {
			}
			break;
		case "lime_potion":
			if(player.ReceiveItem(Items.POISON)) {
				currFloor.removeCollectedItem(itemPosX, itemPosY);
			}
			else {
			}
			break;
		case "green_potion":
			if(player.ReceiveItem(Items.DEF_POTION)) {
				currFloor.removeCollectedItem(itemPosX, itemPosY);
			}
			else {
			}
			break;
		case "yellow_potion":
			if(player.ReceiveItem(Items.MAX_POTION)) {
				currFloor.removeCollectedItem(itemPosX, itemPosY);
			}
			else {
			}
			break;
		case "closed_chest":
			switch(randomizer.nextInt(9)) {
			case 0:
				player.setWeaponEquipped(Items.SHORT_SWORD);
				break;
			case 1:
				player.setArmorEquipped(Items.LIGHT_ARMOR);
				break;
			case 2:
				player.setWeaponEquipped(Items.STICK);
				break;
			case 3:
				player.setArmorEquipped(Items.BRONZE_ARMOR);
				break;
			case 4:
				player.setWeaponEquipped(Items.AXE);
				break;
			case 5:
				player.setArmorEquipped(Items.MEDIEVAL_ARMOR);
				break;
			case 6:
				player.setWeaponEquipped(Items.GREAT_SWORD);
				break;
			case 7:
				player.setArmorEquipped(Items.MISTERIOUS_ARMOR);
				break;
			case 8:
				int gold = randomizer.nextInt(9)+6;
				player.receiveGold(gold);
				break;
			}
			currFloor.removeCollectedItem(itemPosX, itemPosY);
			break;
		}
	}
	
        
        private static void usePlayerItem(int index) {
		Item item = player.getInventoryItem(index);
		
		if(item == null) return;
		
		if(item == Items.HP_POTION) {
			player.heal(10);
		}
                
                
		else if(item == Items.KEY) {
			if(currFloor.getObjectAtPos(player.getWorldPosX()+1, player.getWorldPosY()).getName() == "door") {
				currFloor.OpenDoor(player.getWorldPosX()+1, player.getWorldPosY());
			}
		      else if(currFloor.getObjectAtPos(player.getWorldPosX()-1, player.getWorldPosY()).getName() == "door") {
				currFloor.OpenDoor(player.getWorldPosX()-1, player.getWorldPosY());
			}
			else if(currFloor.getObjectAtPos(player.getWorldPosX(), player.getWorldPosY()+1).getName() == "door") {
				currFloor.OpenDoor(player.getWorldPosX(), player.getWorldPosY()+1);
			}
			else if(currFloor.getObjectAtPos(player.getWorldPosX(), player.getWorldPosY()-1).getName() == "door") {
				currFloor.OpenDoor(player.getWorldPosX(), player.getWorldPosY()-1);
			}
			else {
				                        System.out.println("Doesnt have any door in here,cant use key!");
				return;
			}
			                 System.out.println("You used the key to open the door!");
		}
                            
		else if(item == Items.STR_FRUIT) {
			player.buffStrength();
		}
		else if(item == Items.POISON) {
			mutation =true;
                       // int health = player.getHealth();
                        coppy = new Player(player);
                        player = new Player("devil", player.worldPosX, player.worldPosY, player.getMaxHealth());
                        player.setStrength(20);
                        coppy.removeItem(index);
                        
                        //player.setHealth(health);
		}
		else if(item == Items.DEF_POTION) {
			player.buffDeffence();
		}
		else if(item == Items.MAX_POTION) {
			player.increaseHealth(5);
		}
		player.removeItem(index);
	}
    
        public static void ControlInteractiveItem(){
            pickupItem(player.worldPosX+1, player.worldPosY);
            pickupItem(player.worldPosX-1, player.worldPosY);
            pickupItem(player.worldPosX, player.worldPosY+1);
            pickupItem(player.worldPosX, player.worldPosY-1);
        }
        
        public static void ControlMouseClick(int MousePosX,int MousePosY){
           if(player.isInventoryOpen()){
               if(Render.inventorySlot1.contains(MousePosX, MousePosY)){
                   usePlayerItem(0);
               }else if(Render.inventorySlot2.contains(MousePosX, MousePosY)){
                   usePlayerItem(1);
               }else if(Render.inventorySlot3.contains(MousePosX, MousePosY)){
                   usePlayerItem(2);
               }
           }
        }
     
        public  static boolean checkIfPlayerDied(){
           if(player.getHealth()<=0){
               System.out.println("You are death!");
              return true;
           }else 
               return false;
        }
        
        public static boolean fightMonster(int x,int y){
          if(currFloor.getMonsterAt(player.getWorldPosX()+x, player.getWorldPosY()+y)!=null){
              Monster monster = currFloor.getMonsterAt(player.getWorldPosX()+x, player.getWorldPosY()+y);
              monster.damage(player.getStrength()-monster.getDeffence()/3);
              
              if(player.damageWeapon()){
                  System.out.println("Your weap-on broke!");
                  
              }
              if(monster.getHealth()<=0){
                 currFloor.RemoveMonster(monster.getWorldPosX(), monster.getWorldPosY());
                 int gold = randomizer.nextInt(8)+12;
                  System.out.println("Your collect "+gold+" gold when kill the monster");
                  player.receiveGold(gold);
                  
              }
              else{
                  System.out.println("You atack the monster and it fight back to you!");
                  player.damage(monster.getStrength()-player.getDeffence()/3);
                  if(player.damageArmor())
                  System.out.println("Your armor broke!");
              }
              return true;
          }
            return false;
        }
        
        public static void notice(int k) throws InterruptedException{
           while(k>=0){
               Thread.sleep(1000);
               k--;
           }
        }
        
        
}
        
