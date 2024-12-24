package menus;

import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InventoryKeyHandler implements KeyListener {

    private GamePanel gp;

    public InventoryKeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_S) {
            gp.getInventory().down();
        }

        if(code == KeyEvent.VK_W) {
            gp.getInventory().top();
        }

        if(code == KeyEvent.VK_D) {
            gp.getInventory().right();
        }

        if(code == KeyEvent.VK_A) {
            gp.getInventory().left();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_ESCAPE) {
            gp.getInventory().back();
        }

        if (code == KeyEvent.VK_I) {
            this.gp.getInventory().closeInventory();
        }

        if(code == KeyEvent.VK_ENTER) {
            if (!this.gp.getInventory().isItemSelected()) {
                gp.getInventory().setItemSelected(true);
            } else {
                gp.getInventory().selectedItemAction();
            }
        }
    }
}
