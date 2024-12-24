package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    private boolean upPressed, downPressed, leftPressed, rightPressed;
    private GamePanel gp;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            this.upPressed = true;
        }

        if (code == KeyEvent.VK_A) {
            this.leftPressed = true;
        }

        if (code == KeyEvent.VK_S) {
            this.downPressed = true;
        }

        if (code == KeyEvent.VK_D) {
            this.rightPressed = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            this.upPressed = false;
        }

        if (code == KeyEvent.VK_A) {
            this.leftPressed = false;
        }

        if (code == KeyEvent.VK_S) {
            this.downPressed = false;
        }

        if (code == KeyEvent.VK_D) {
            this.rightPressed = false;
        }

        if (code == KeyEvent.VK_ENTER) {
            if (!this.gp.isGamePaused()) {
                gp.getPlayer().playerInteraction();
            }
        }

        if (code == KeyEvent.VK_ESCAPE) {
            this.turnMovementsOff();
            this.gp.setGamePaused(!this.gp.isGamePaused());
            this.gp.setOnMenu(!this.gp.isOnMenu());
        }

        if (code == KeyEvent.VK_I) {
            this.turnMovementsOff();
            this.gp.setGamePaused(!this.gp.isGamePaused());
            this.gp.setOnInventory(!this.gp.isOnInventory());
        }
    }

    public boolean getUpPressed() {
        return this.upPressed;
    }

    public boolean getDownPressed() {
        return this.downPressed;
    }

    public boolean getLeftPressed() {
        return this.leftPressed;
    }

    public boolean getRightPressed() {
        return this.rightPressed;
    }

    private void turnMovementsOff() {
        this.upPressed = false;
        this.downPressed = false;
        this.leftPressed = false;
        this.rightPressed = false;
    }
}
