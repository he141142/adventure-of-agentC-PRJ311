/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package advantureofagentc.gui;

import javax.swing.JFrame;
/**
 *
 * @author Administrator
 */
public class Window {
    final static int RONG = 900;
    final static int DAI = 600;
    
    public static JFrame window;
    public static GameScreen screen;
    
    
    
    public static void CreateWindow(){
      window = new JFrame("Advanture of agent C");
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      window.setBounds(20,20,RONG,DAI);
      window.setResizable(true);
      
      screen = new GameScreen();
      window.add(screen);
      
    } 
    
    public static void SetVisible(){
     if(window!=null)window.setVisible(true);
    }
}
