/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package customgame.gui;

import customgame.Operation.Entities.EntityObject;
import customgame.Operation.Entities.Floor;
import customgame.Operation.Entities.Monster;
import customgame.Operation.Entities.Object;
import customgame.Operation.Entities.Player;
import customgame.Resource.DataSource;
import customgame.utils.DIRECTION;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class Render {
        public static String[] optionsMenu;
        public static int selected;
        public static final String START_GAME = "Start Game!";
	public static final String QUIT_GAME = "Quit game";
        public static final Rectangle inventory = new Rectangle(150, 50, 700, 500);
	public static final Rectangle inventorySlot1 = new Rectangle(510, 150, 330, 60);
	public static final Rectangle inventorySlot2 = new Rectangle(510, 220, 330, 60);
	public static final Rectangle inventorySlot3 = new Rectangle(510, 290, 330, 60);
	public static final Rectangle weaponSlot = new Rectangle(160, 150, 330, 60);
	public static final Rectangle armorSlot = new Rectangle(160, 220, 330, 60);
       
        

    public static final int ZOOM_LEVEL = 2;
    private boolean Opeation;
    public void RenderPlayer(EntityObject player,Graphics graphics){
          BufferedImage images = DataSource.getImage(player.name);
          if(player.name.equalsIgnoreCase("devil")){
              images = flipImage(images);
          }
          if(player.getFacing()==DIRECTION.RIGHT){
               images=flipImage(images);
          }
          int drawPosX = (Window.WIDTH/2)-(images.getWidth()/2)*ZOOM_LEVEL;
          int drawPosY = (Window.HEIGHT/2)-(images.getHeight()/2)*ZOOM_LEVEL;
          graphics.drawImage(images, drawPosX, drawPosY, images.getWidth()*ZOOM_LEVEL,images.getHeight()*ZOOM_LEVEL, null);
    }
    
    
    public void renderLevel(Floor floorData, Player player, Graphics graphics) {
		for(int y=0;y<floorData.getSizeY();y++) {
			for(int x=0;x<floorData.getSizeX();x++) {
				BufferedImage img = DataSource.getImage(floorData.getObjectAtPos(x, y).getName());
				int drawPosX = calculateOffsetX(img, floorData.getObjectAtPos(x, y), player);
				int drawPosY = calculateOffsetY(img, floorData.getObjectAtPos(x, y), player);
				graphics.drawImage(img, drawPosX, drawPosY, img.getWidth()*ZOOM_LEVEL, img.getHeight()*ZOOM_LEVEL, null);
			}
		}
	}

    private int calculateOffsetY(BufferedImage img, Object obj, Player player) {
        return obj.getWorldPosY()*img.getHeight()*ZOOM_LEVEL + ((Window.HEIGHT/2)-player.getWorldPosY()*img.getHeight()*ZOOM_LEVEL-(img.getHeight()/2)*ZOOM_LEVEL);
    }

    private int calculateOffsetX(BufferedImage img, Object obj, Player player) {
        return obj.getWorldPosX()*img.getWidth()*ZOOM_LEVEL + ((Window.WIDTH/2)-player.getWorldPosX()*img.getWidth()*ZOOM_LEVEL-(img.getWidth()/2)*ZOOM_LEVEL);
    }
    
    private static BufferedImage flipImage(BufferedImage img){
      int w = img.getWidth();
      int h =img.getHeight();
      BufferedImage flip = new BufferedImage(h, w, img.getType());
       for (int i = 0; i < w; i++) {
           for (int j = 0; j < h; j++) {
               flip.setRGB(i, j, img.getRGB(w-1-i, j));
           }
       }
       return flip;
   }
    
    public void renderUI(Player player, Floor floorData,boolean mutation, Graphics2D graphics, Point mousePosition) {
		graphics.setColor(Color.BLACK);
		graphics.fillRoundRect(5, 5, 100, 150, 10, 10);
		graphics.setColor(Color.WHITE);
		graphics.drawRoundRect(5, 5, 100, 150, 10, 10);
		
		graphics.setFont(new Font("dalog", Font.PLAIN, 20));
                if(mutation){
		graphics.drawString("-DEVIL-", 10, 25);
                }else{
		graphics.drawString("-LONG-", 10, 25);
                }
		graphics.setFont(new Font("dialog", Font.PLAIN, 12));
		graphics.drawString("HP: "+player.getHealth()+"/"+player.getMaxHealth(), 10, 45);
		graphics.drawString("STR: "+player.getStrength(), 10, 65);
		graphics.drawString("DEF: "+player.getDeffence(), 10, 80);
		graphics.drawString("Gold: "+player.getGold(), 10, 100);
		graphics.drawString("Floors: "+player.getFloors(), 10, 120);
		
		for(int y=0;y<floorData.getSizeY();y++) {
			for(int x=0;x<floorData.getSizeX();x++) {
				if(floorData.getObjectAtPos(x, y).isIsCollectible()) {
					int drawPosX = floorData.getObjectAtPos(x, y).getWorldPosX()*32*ZOOM_LEVEL + ((Window.WIDTH/2)-player.getWorldPosX()*32*ZOOM_LEVEL-(32/2)*ZOOM_LEVEL);
					int drawPosY = floorData.getObjectAtPos(x, y).getWorldPosY()*32*ZOOM_LEVEL + ((Window.HEIGHT/2)-player.getWorldPosY()*32*ZOOM_LEVEL-(32/2)*ZOOM_LEVEL);
					
					if((player.getWorldPosX() == x-1 && player.getWorldPosY() == y) || (player.getWorldPosX() == x+1 && player.getWorldPosY() == y) || (player.getWorldPosX() == x && player.getWorldPosY()== y-1) || (player.getWorldPosX() == x && player.getWorldPosY() == y+1)) {
						BufferedImage image = DataSource.getImage("E");
						graphics.drawImage(image, drawPosX+8*ZOOM_LEVEL, drawPosY-8*ZOOM_LEVEL, image.getWidth()*ZOOM_LEVEL, image.getHeight()*ZOOM_LEVEL, null);
					}
				}
			}
		}
		
		if(player.isInventoryOpen()) {
			graphics.setColor(Color.BLACK);
			graphics.fillRoundRect(inventory.x, inventory.y, inventory.width, inventory.height, 10, 10);
			graphics.setColor(Color.WHITE);
			graphics.drawRoundRect(inventory.x, inventory.y, inventory.width, inventory.height, 10, 10);
			
			graphics.setFont(new Font("Dialog", Font.PLAIN, 40));
			graphics.drawString("- Inventory -", 160, 90);
			graphics.setFont(new Font("Dialog", Font.PLAIN, 20));
			graphics.drawString("HP: "+player.getHealth()+"/"+player.getMaxHealth()+"     STR: "+player.getStrength()+"   DEF: "+player.getDeffence()+"     Gold: "+player.getGold(), 160, 120);
			
			if(inventorySlot1.contains(mousePosition))
				graphics.setStroke(new BasicStroke(3));
			
			graphics.drawRoundRect(inventorySlot1.x, inventorySlot1.y, inventorySlot1.width, inventorySlot1.height, 10, 10);
			graphics.drawRoundRect(inventorySlot1.x, inventorySlot1.y, 60, inventorySlot1.height, 10, 10);
			
			graphics.setStroke(new BasicStroke(1));
			
			if(inventorySlot2.contains(mousePosition))
				graphics.setStroke(new BasicStroke(2));
			
			graphics.drawRoundRect(inventorySlot2.x, inventorySlot2.y, inventorySlot2.width, inventorySlot2.height, 10, 10);
			graphics.drawRoundRect(inventorySlot2.x, inventorySlot2.y, 60, inventorySlot2.height, 10, 10);
			
			graphics.setStroke(new BasicStroke(1));
			
			if(inventorySlot3.contains(mousePosition))
				graphics.setStroke(new BasicStroke(3));
			
			graphics.drawRoundRect(inventorySlot3.x, inventorySlot3.y, inventorySlot3.width, inventorySlot3.height, 10, 10);
			graphics.drawRoundRect(inventorySlot3.x, inventorySlot3.y, 60, inventorySlot3.height, 10, 10);
			
			graphics.setStroke(new BasicStroke(1));
			
			graphics.drawRoundRect(weaponSlot.x, weaponSlot.y, weaponSlot.width, weaponSlot.height, 10, 10);
			graphics.drawRoundRect(weaponSlot.x, weaponSlot.y, 60, weaponSlot.height, 10, 10);
			
			if(player.getWeaponEquipped()!= null) {
				BufferedImage image = DataSource.getImage(player.getWeaponEquipped().getName());
				graphics.drawImage(image, weaponSlot.x+7, weaponSlot.y+7, image.getWidth()*3, image.getHeight()*3, null);
				graphics.setFont(new Font("Dialog", Font.PLAIN, 20));
				graphics.drawString(player.getWeaponEquipped().getName(), weaponSlot.x+65, weaponSlot.y+22);
				graphics.setFont(new Font("Dialog", Font.PLAIN, 15));
				graphics.drawString(player.getWeaponEquipped().getDescription(), weaponSlot.x+65, weaponSlot.y+48);
			}
			
			graphics.drawRoundRect(armorSlot.x, armorSlot.y, armorSlot.width, armorSlot.height, 10, 10);
			graphics.drawRoundRect(armorSlot.x, armorSlot.y, 60, armorSlot.height, 10, 10);
			
			if(player.getArmorEquipped()!= null) {
				BufferedImage image = DataSource.getImage(player.getArmorEquipped().getName());
				graphics.drawImage(image, armorSlot.x+7, armorSlot.y+7, image.getWidth()*3, image.getHeight()*3, null);
				graphics.setFont(new Font("Dialog", Font.PLAIN, 20));
				graphics.drawString(player.getArmorEquipped().getName(), armorSlot.x+65, armorSlot.y+22);
				graphics.setFont(new Font("Dialog", Font.PLAIN, 15));
				graphics.drawString(player.getArmorEquipped().getDescription(), armorSlot.x+65, armorSlot.y+48);
			}
			
		for(int i=0;i<Player.INVENTORY_SIZE;i++) {
				if(player.getInventoryItem(i) != null) {
					BufferedImage image = DataSource.getImage(player.getInventoryItem(i).getName());
					graphics.drawImage(image, inventorySlot1.x+7, 157+i*70, image.getWidth()*3, image.getHeight()*3, null);
					graphics.setFont(new Font("Dialog", Font.PLAIN, 20));
					graphics.drawString(player.getInventoryItem(i).getDisplayName(), inventorySlot1.x+65, 170+i*70);
					graphics.setFont(new Font("Dialog", Font.PLAIN, 15));
					graphics.drawString(player.getInventoryItem(i).getDescription(), inventorySlot1.x+65, 195+i*70);
				}
			}
		}
                if(player.getHealth() <= 0) {
			graphics.setColor(Color.BLACK);
			graphics.fillRoundRect(inventory.x, inventory.y, inventory.width, inventory.height, 10, 10);
			graphics.setColor(Color.WHITE);
			graphics.drawRoundRect(inventory.x, inventory.y, inventory.width, inventory.height, 10, 10);

			graphics.setFont(new Font("Dialog", Font.PLAIN, 40));
			graphics.drawString("Game Over!!!", 200, 130);
			
			graphics.setFont(new Font("Dialog", Font.PLAIN, 30));
			graphics.drawString("Gold collected: "+player.getGold(), 200, 240);
			graphics.drawString("Your rank is A+", 200, 350);
		}
                if(player.isFinished){
                        graphics.setColor(Color.BLACK);
			graphics.fillRoundRect(inventory.x, inventory.y, inventory.width, inventory.height, 10, 10);
			graphics.setColor(Color.WHITE);
			graphics.drawRoundRect(inventory.x, inventory.y, inventory.width, inventory.height, 10, 10);

			graphics.setFont(new Font("Dialog", Font.PLAIN, 40));
			graphics.drawString("YOU WIN THE GAME !!!", 200, 130);
			
			graphics.setFont(new Font("Dialog", Font.PLAIN, 30));
			graphics.drawString("Gold collected: "+player.getGold(), 200, 240);
			graphics.drawString("Your rank is A+", 200, 350);
                }
}
    public void RenderMonster(Monster[] monsters,Player player,Graphics graphics){
       if(monsters.length==0){
          return;
       }
       
       for(Monster m:monsters){
          BufferedImage img = DataSource.getImage(m.getName());
          int drawPosX = calculateOffsetX(img, m, player);
          int drawPosY = calculateOffsetY(img, m, player);
          if(m.getHealth()>0)
              graphics.drawImage(img, drawPosX, drawPosY, img.getWidth()*ZOOM_LEVEL, img.getHeight()*ZOOM_LEVEL, null);
       }
    
    }
    
       
    public void RenderMenu(Graphics graphics,int select){
         this.optionsMenu = new String[] {START_GAME, QUIT_GAME};
         graphics.setColor(Color.BLACK);
		graphics.fillRect ( 0 , 0 , Window.WIDTH , Window.HEIGHT );
		
		graphics.setFont(new Font("Araial", Font.PLAIN, 25));
		for(int i=0;i<this.optionsMenu.length;i++) {
			if(i==select) graphics.setColor(Color.RED);
			else graphics.setColor(Color.WHITE);
			graphics.drawString(this.optionsMenu[i], 20, 50 + i * 120);
		}
    }
}
