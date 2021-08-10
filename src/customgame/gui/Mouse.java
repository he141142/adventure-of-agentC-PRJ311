/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package customgame.gui;

import customgame.Operation.Operation;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/**
 *
 * @author Administrator
 */
public class Mouse implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
         Operation.ControlMouseClick(e.getX(), e.getY());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
