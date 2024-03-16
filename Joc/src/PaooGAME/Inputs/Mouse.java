package PaooGAME.Inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import PaooGAME.GamePanel;

public class Mouse implements MouseListener, MouseMotionListener {
    private GamePanel gamePanel; // pt a putea fi vizibile modificarile asupra obiectului in gmP
    public Mouse(GamePanel gamePanel){
        this.gamePanel=gamePanel;

    }
    @Override
    public void mouseClicked(MouseEvent e)
    {
    }


    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
