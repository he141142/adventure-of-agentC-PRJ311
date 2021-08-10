/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package customgame;
import customgame.Operation.Operation;
import customgame.gui.Window;

/**
 *
 * @author Administrator
 */
public class CustomGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Window.CreateWindow();
        Operation.Start();
        Window.setVisible();
    }
    
}
