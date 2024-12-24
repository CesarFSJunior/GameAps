package objects;

import java.awt.image.BufferedImage;

public abstract class Object implements SuperObject{

    private BufferedImage image;
    private String name;
    private boolean collision;
    private int worldX, worldY;

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
    public boolean isUseEnable() {
        return true;
    }
}
