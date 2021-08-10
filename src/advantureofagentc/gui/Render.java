/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package advantureofagentc.gui;

import advantureofagentc.operation.entities.EntityTile;
import advantureofagentc.operation.entities.StaticTile;
import advantureofagentc.operation.level.Tile;
import advantureofagentc.operation.level.Floor;
import advantureofagentc.util.DataSource;
import advantureofagentc.util.Direction;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
/**
 *
 * @author Administrator
 */
public class Render {
    private static final int ZOOM_LEVEL = 2;
    public static void renderEntity(EntityTile entityData,Graphics graphics){
        BufferedImage img = DataSource.getImage(entityData.getName());
        //graphics.drawImage(img, entityData.getPosX(), entityData.getPosY(),img.getWidth()*ZOOM_LEVEL, img.getHeight()*ZOOM_LEVEL,null);
        if(entityData.getFacing()==Direction.RIGHT){
          img=flipImage(img);
        }
                int drawPosX = (Window.RONG/2)-(img.getWidth()/10)*ZOOM_LEVEL;
		int drawPosY = (Window.DAI/2)-(img.getHeight()/10)*ZOOM_LEVEL;
		graphics.drawImage(img, drawPosX, drawPosY, img.getWidth()*ZOOM_LEVEL, img.getHeight()*ZOOM_LEVEL, null);
    }

    
    public static void renderLevel(Floor floorData,EntityTile player,Graphics graphics){
        for (int y = 0; y < floorData.getSizeY(); y++) {
            for (int x = 0; x < floorData.getSizeX(); x++) {
                BufferedImage img = DataSource.getImage(floorData.getEntityAtPos(x,y).getName());
                //int drawPosX = (floorData.getEntityAtPos(x, y).getWorldPosX()-1)*img.getWidth()*ZOOM_LEVEL+((Window.RONG/2)-player.getPosX()*(img.getWidth()*ZOOM_LEVEL));
                //int drawPosY = (floorData.getEntityAtPos(x, y).getWorldPosY()-1)*img.getHeight()*ZOOM_LEVEL+((Window.DAI/2)-player.getPosY()*(img.getHeight()*ZOOM_LEVEL));
                 int drawPosX=CaculateOffsetX(img, floorData.getEntityAtPos(x, y), player);
                 int drawPosY=CaculateOffsetY(img, floorData.getEntityAtPos(x, y), player);
                graphics.drawImage(img, drawPosX, drawPosY,img.getWidth()*ZOOM_LEVEL,img.getHeight()*ZOOM_LEVEL ,null);
            }
        }
    }
   
    public static int CaculateOffsetX(BufferedImage img, Tile tile, EntityTile player){
		return tile.getPosX()*img.getWidth()*ZOOM_LEVEL + ((Window.RONG/2)-player.getPosX()*img.getWidth()*ZOOM_LEVEL-(img.getWidth()/10)*ZOOM_LEVEL);//+player.getMotionOffsetX()*ZOOM_LEVEL;
    }
    private static int CaculateOffsetY(BufferedImage img, Tile tile, EntityTile player) {
		return tile.getPosY()*img.getHeight()*ZOOM_LEVEL + ((Window.DAI/2)-player.getPosY()*img.getHeight()*ZOOM_LEVEL-(img.getHeight()/10)*ZOOM_LEVEL);//+player.getMotionOffsetY()*ZOOM_LEVEL;
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
    

}
