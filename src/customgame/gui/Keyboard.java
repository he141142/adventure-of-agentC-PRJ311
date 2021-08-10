/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package customgame.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Administrator
 */
public class Keyboard implements KeyListener {
    private static int delay;
	
    public static boolean keys[];
    public Keyboard(){
        keys = new boolean[100];
        delay=32;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent arg0) {
        keys[arg0.getKeyCode()]=true;
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        keys[arg0.getKeyCode()]=false;
    }
    
    public static boolean isKeyDown(int key){
               if(keys[key]==true &&delay<=0){
                   delay=32;
                     return true;
                     
               }else{
                   delay--;
               }
               return false;
    }
    
}
