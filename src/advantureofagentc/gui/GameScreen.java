/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package advantureofagentc.gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import advantureofagentc.operation.Operation;
/**
 *
 * @author Administrator
 */
public class GameScreen extends JPanel {
     private Render render;
    public GameScreen(){
     super();
     this.setFocusable(true);
     this.addKeyListener(new Keyboard());
     this.render = new Render();
    }
    
    @Override
    protected void paintComponent(Graphics graphics){
      super.paintComponent(graphics);
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, Window.RONG, Window.DAI);
        graphics.setColor(Color.WHITE);
      
        //Operation.initializeGame();
        render.renderLevel(Operation.getCurrFloor(),Operation.getPlayer() ,graphics);
        render.renderEntity(Operation.getPlayer(), graphics);
        repaint();
    }
}
