package menus;

import main.GamePanel;
import main.KeyHandler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MenuKeyHandler implements KeyListener {

    private boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, escPressed;
    private GamePanel gp;

    public MenuKeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_S) {
            if (!this.downPressed) {
                gp.getInGameMenu().menuDown();
            }
            this.downPressed = true;
        }

        if (code == KeyEvent.VK_ENTER) {
            if (!this.enterPressed) {
                gp.getInGameMenu().interact();
            }
            this.enterPressed = true;
        }

    }

    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_S) {
            this.downPressed = false;
        }

        if (code == KeyEvent.VK_ENTER) {
            this.enterPressed = false;
        }

        if (code == KeyEvent.VK_ESCAPE) {
            this.gp.setGamePaused(!this.gp.isGamePaused());
            this.gp.setOnMenu(!this.gp.isOnMenu());
        }

    }

}
