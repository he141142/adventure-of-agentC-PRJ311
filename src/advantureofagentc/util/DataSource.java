/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package advantureofagentc.util;

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
     private static HashMap<String, BufferedImage> img;
     
     public static void init(){
        img = new HashMap<>();
        
        File folder = new File("img/entities");
        
        for(File file : folder.listFiles()){
            try {
                img.put(file.getName().replaceAll(".png", ""), ImageIO.read(file));
            } catch (IOException ex) {
                System.err.println("failed to read file "+ folder.getName());
                Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
     }
     
     public static BufferedImage getImage(String name){
         return img.get(name);
     }
}
