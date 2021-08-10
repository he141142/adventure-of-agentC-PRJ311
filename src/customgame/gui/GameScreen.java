/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package customgame.gui;

import customgame.Operation.Entities.EntityObject;
import customgame.Operation.Operation;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Point;


/**
 *
 * @author Administrator
 */
public class GameScreen extends JPanel {
    private Render render;
    
    GameScreen(){
     super();
     this.setFocusable(true);
     this.render=new Render();
     this.addKeyListener(new Keyboard());
     this.addMouseListener(new Mouse());
    }
    
    @Override
 protected void paintComponent(Graphics graphics) {   
          super.paintComponents(graphics);
         try{ 
          graphics.setColor(Color.BLACK);
          graphics.fillRect(0, 0, Window.WIDTH*800, Window.HEIGHT*900);
          
          if(Operation.MainMenu){
             render.RenderMenu(graphics,Operation.select);
          }else{
	  render.renderLevel(Operation.getCurrFloor(), Operation.getPlayer(), graphics);
          render.RenderPlayer(Operation.player, graphics);
          render.RenderMonster(Operation.getMonsters(), Operation.getPlayer(), graphics);
          render.renderUI(Operation.getPlayer(), Operation.getCurrFloor(),Operation.mutation , (Graphics2D)graphics, this.getMouseLocation());
          }
         }catch(Exception e){
                System.err.println("Error when rendering...");
                e.printStackTrace();
                System.exit(-1);
         }            
                      repaint();

 } 
 
 public Point getMouseLocation() {
		if(getMousePosition() != null)
			return getMousePosition();
		else
			return new Point(-1, -1);
	}
}
