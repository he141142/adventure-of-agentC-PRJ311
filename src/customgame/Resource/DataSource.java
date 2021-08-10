/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package customgame.Resource;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Administrator
 */
public class DataSource {
     private static HashMap<String,BufferedImage> images;
     
     public static void Init(){
        images = new HashMap<>();
        File folder = new File("res/entities");
         for (File file : folder.listFiles()) {
            try {
                images.put(file.getName().replaceAll(".png", ""), ImageIO.read(file));
            } catch (IOException ex) {
                Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
     }
     public static BufferedImage getImage(String name){
            BufferedImage i = images.get(name);
            if(images!=null)return i;
            return images.get("error");
     }
}

