package dialog;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class DialogPannel {

    private GamePanel gp;
    private BufferedImage background;
    private boolean activated = false;
    private ArrayList<String> txtList;
    private int actualTxt;
    private KeyListener oldKl;

    public DialogPannel(GamePanel gp) {
        this.gp = gp;
        try {
            this.background = ImageIO.read(getClass().getResourceAsStream("/dialog/DialogBg.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void receiveDialog(ArrayList<String> txt) {
        if (!txt.isEmpty()) {
            this.setActivated(true);
            this.actualTxt = 0;
            this.txtList = txt;
        }
    }

    public void draw(Graphics2D g) {

        if (this.isActivated()) {

            g.drawImage(this.background, gp.getTileSize() * 3, gp.getTileSize() * 9, this.background.getWidth() * gp.getScale(), this.background.getHeight() * gp.getScale(), null);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Times New Roman", Font.BOLD, 16));
            String text = this.txtList.get(this.actualTxt);
            g.drawString(text, gp.getTileSize() * 4, gp.getTileSize() * 10 + 4);
        }
    }

    private boolean isActivated() {
        return activated;
    }

    private void setActivated(boolean activated) {

        if (activated) {
            this.oldKl = this.gp.getKeyListeners()[0];
            this.gp.removeKeyListener(this.oldKl);
            this.gp.addKeyListener(this.gp.getDialogKeyHandler());
        } else {
            this.gp.removeKeyListener(this.gp.getDialogKeyHandler());
            this.gp.addKeyListener(this.oldKl);
        }

        this.activated = activated;
    }

    public void nextTxt() {
        int dialogsSize = this.txtList.size();
        if (this.actualTxt + 1 == dialogsSize) {
            this.actualTxt = 0;
            this.setActivated(!this.isActivated());
        }
        this.actualTxt += 1;
    }

}
