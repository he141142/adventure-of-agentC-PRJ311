/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package customgame.gui;

import javax.swing.JFrame;

/**
 *
 * @author Administrator
 */
public class Window {
       public static final int WIDTH = 900;
	public static final int HEIGHT = 600;
	private static JFrame window;
	private static GameScreen screen;
        public static void CreateWindow(){
                window = new JFrame("CUSTOM GAME");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(20, 20, WIDTH, HEIGHT);
		window.setResizable(true);
		
		screen = new GameScreen();
		window.add(screen);
        }
                public static void setVisible() {
		if(window!=null) window.setVisible(true);
	}
                
}
