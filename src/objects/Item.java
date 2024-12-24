package objects;

import main.GamePanel;

import java.awt.image.BufferedImage;

public abstract class Item implements SuperObject {

    private BufferedImage image;
    private String name;
    private boolean collision;
    private int worldX, worldY;
    private GamePanel gp;

    public Item(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void setImage(BufferedImage image) {
        this.image = image;
    }

    @Override
    public BufferedImage getImage() {
        return this.image;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    @Override
    public boolean getCollision() {
        return this.collision;
    }

    @Override
    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    @Override
    public int getWorldX() {
        return this.worldX;
    }

    @Override
    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    @Override
    public int getWorldY() {
        return this.worldY;
    }

    @Override
    public void interact() {
        this.gp.removeObject(this);
        this.gp.getPlayer().addItemToInventory(this);
    }

    @Override
    public void use() {

    }

    @Override
    public boolean isUseEnable() {
        return false;
    }

    public GamePanel getGp() {
        return gp;
    }
}
