package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {

    private GamePanel gp;

    public MouseHandler(GamePanel gp ) {
        this.gp = gp;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (this.gp.getAddObjectMode().isActivated()) {
            this.gp.getAddObjectMode().addObject();
        }
    }

    @Override
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
}
