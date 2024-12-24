package menus;

import main.GamePanel;
import objects.SuperObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Inventory {

    private GamePanel gp;
    private BufferedImage background;
    private BufferedImage itemMenu;
    private int selectedItem = 0;
    private boolean itemSelected;
    private int itemOption = 2;

    public Inventory(GamePanel gp) {

        this.gp = gp;

        try {
            this.background = ImageIO.read(getClass().getResourceAsStream("/menus/Inventory.png"));
            this.itemMenu = ImageIO.read(getClass().getResourceAsStream("/menus/itemMenu.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    private SuperObject getItemById(int id) {
        return gp.getPlayer().getInventory().get(id);
    }

    public void top() {
        if (!this.itemSelected) {
            this.selectedItem = foundTop(this.selectedItem);
        } else {
            if ((this.itemOption == 1 && !getItemById(this.selectedItem).isUseEnable()) || this.itemOption == 0) {
                this.itemOption = 2;
            } else {
                this.itemOption -= 1;
            }
        }
    }

    private int foundTop(int i) {
        if (gp.getPlayer().getInventory().size() != 0) {
            i = i - 6;
            if (i < 0) {
                i = 18 + i;
            }
            if (i > gp.getPlayer().getInventory().size() - 1) {
                return foundTop(i);
            }
        }
        return i;
    }

    public void down() {
        if (!this.itemSelected) {
            this.selectedItem = foundBottom(this.selectedItem);
        } else {
            if (this.itemOption == 2 && getItemById(this.selectedItem).isUseEnable()){
                this.itemOption = 0;
            }
            else if (this.itemOption == 2) {
                this.itemOption = 1;
            }
            else {
                this.itemOption += 1;
            }
        }
    }

    private int foundBottom(int i) {
        i = i + 6;
        if (i > gp.getPlayer().getInventory().size() - 1) {
            i = i % 6;
        }
        if (i < 0 ) {
            return foundBottom(i);
        }
        return i;
    }

    public void left() {
        if (!this.itemSelected) {
            if (this.selectedItem - 1 >= 0) {
                this.selectedItem -= 1;
            } else if (gp.getPlayer().getInventory().size() >= 1) {
                this.selectedItem = gp.getPlayer().getInventory().size() - 1;
            }
        }
    }

    public void right() {
        if (!this.itemSelected) {
            if (this.selectedItem + 1 < gp.getPlayer().getInventory().size()) {
                this.selectedItem += 1;
            } else {
                this.selectedItem = 0;
            }
        }
    }

    public void draw(Graphics2D g) {

        int imageWidth = this.background.getWidth() * this.gp.getScale();
        int imageHeight = this.background.getHeight() * this.gp.getScale();

        int imageScreenX = (this.gp.getScreenWidth() - imageWidth) / 2;
        int imageScreenY = (this.gp.getScreenHeight() - imageHeight) / 2;

        g.drawImage(this.background, imageScreenX, imageScreenY, imageWidth, imageHeight, null);

        g.setColor(new Color(199, 125, 90));

        int selectedX = imageScreenX + ((this.selectedItem % 6) * (2 * gp.getScale())) + (this.selectedItem % 6 * (16 * gp.getScale()) + (2 * gp.getScale()));
        int selectedY = imageScreenY + (2 * gp.getScale()) + ((this.selectedItem / 6)) * (16 * gp.getScale()) + ((2 * gp.getScale()) * (this.selectedItem / 6));

        g.fillRect(selectedX, selectedY, gp.getTileSize(), gp.getTileSize());

        this.gp.getPlayer().getInventory().forEach(item -> drawItem(item, g));

        if (this.itemSelected) {
            int xSelectedBox =  selectedX + 4 + gp.getTileSize();
            int ySelectedBox = selectedY - 4 - (this.itemMenu.getHeight() * gp.getScale());
            g.drawImage(this.itemMenu, xSelectedBox, ySelectedBox, (this.itemMenu.getWidth() * gp.getScale()), (this.itemMenu.getHeight() * gp.getScale()), null);

            g.setColor(new Color(199, 125, 90));
            g.fillRect(xSelectedBox + (1 * gp.getScale()), ySelectedBox + (((this.itemOption + 1) * 1) * gp.getScale())
                    + (this.itemOption * 6 * gp.getScale()), ((this.itemMenu.getWidth() - 2)  * gp.getScale()), (6 * gp.getScale()));

            g.setFont(new Font("Times New Roman", Font.BOLD, 14));
            g.setColor(Color.black);
            int stringY = ySelectedBox + (gp.getScale() * (0 + 1)) + 14 + (18 * 0);
            g.drawString("Usar", xSelectedBox + (2 * gp.getScale()), stringY);
            stringY = ySelectedBox + (gp.getScale() * (1 + 1)) + 14 + (18 * 1);
            g.drawString("Descrição", xSelectedBox + (2 * gp.getScale()), stringY);
            stringY = ySelectedBox + (gp.getScale() * (2 + 1)) + 14 + (18 * 2);
            g.drawString("Sair", xSelectedBox + (2 * gp.getScale()), stringY);
        }
    }

    private void drawItem(SuperObject obj, Graphics2D g) {

        int imageWidth = this.background.getWidth() * this.gp.getScale();
        int imageHeight = this.background.getHeight() * this.gp.getScale();

        int imageScreenX = (this.gp.getScreenWidth() - imageWidth) / 2;
        int imageScreenY = (this.gp.getScreenHeight() - imageHeight) / 2;

        int index = gp.getPlayer().getInventory().indexOf(obj);

        int newScreenX = imageScreenX + ((index % 6) * (2 * gp.getScale())) + (index % 6 * (16 * gp.getScale()) + (2 * gp.getScale()));
        int newScreenY = imageScreenY + (2 * gp.getScale()) + ((index / 6)) * (16 * gp.getScale()) + ((2 * gp.getScale()) * (index / 6));

        g.drawImage(obj.getImage(), newScreenX,newScreenY, gp.getTileSize(), gp.getTileSize(), null);

    }

    public boolean isItemSelected() {
        return itemSelected;
    }

    public void setItemSelected(boolean itemSelected) {
        if (itemSelected) {
            if (!getItemById(this.selectedItem).isUseEnable()) {
                this.itemOption = 1;
            } else {
                this.itemOption = 0;
            }
        }
        this.itemSelected = itemSelected;
    }

    public void back() {
        if (this.itemSelected) {
            this.setItemSelected(false);
        } else {
            this.gp.setOnInventory(!this.gp.isOnInventory());
            this.gp.setOnMenu(!this.gp.isOnMenu());
        }
    }

    public void selectedItemAction() {
        switch (this.itemOption) {
            case 0:
                this.closeInventory();
                getItemById(this.selectedItem).use();
            break;
            case 1:
                ArrayList<String> descricao = new ArrayList<>();
                descricao.add(getItemById(this.selectedItem).getName());
                this.gp.getDialogPannel().receiveDialog(descricao);
            break;
            case 2:
                this.setItemSelected(false);
            break;
        }
    }

    public void closeInventory() {
        this.setItemSelected(false);
        this.gp.setGamePaused(!this.gp.isGamePaused());
        this.gp.setOnInventory(!this.gp.isOnInventory());
    }
}
