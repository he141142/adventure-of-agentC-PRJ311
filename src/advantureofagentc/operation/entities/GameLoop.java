/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package advantureofagentc.operation.entities;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import advantureofagentc.gui.Keyboard;
import advantureofagentc.operation.Operation;
import advantureofagentc.util.Direction;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Administrator
 */
public class GameLoop implements ActionListener {
 public GameLoop(){
   
 }
    @Override
    public void actionPerformed(ActionEvent arg0) {
        if(Keyboard.keyIsDown(KeyEvent.VK_W)){
                Operation.movePlayer(0, -1);  
                /*EntityTile player2;
                 player2=new EntityTile("trap", Operation.getPlayer().getPosX(), Operation.getPlayer().getPosY(), Operation.getPlayer().getHealth());
                 Operation.setPlayer(player2);
                        */
        }
         if(Keyboard.keyIsDown(KeyEvent.VK_D)){
           Operation.movePlayer(1, 0);
           Operation.getPlayer().setFacing(Direction.RIGHT);
        }
          if(Keyboard.keyIsDown(KeyEvent.VK_A)){
           Operation.movePlayer(-1, 0);
           Operation.getPlayer().setFacing(Direction.LEFT);
        }
           if(Keyboard.keyIsDown(KeyEvent.VK_S)){
           Operation.movePlayer(0, 1);
        }
        
    }
    
}
