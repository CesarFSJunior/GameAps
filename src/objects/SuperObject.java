package objects;

import java.awt.image.BufferedImage;

public interface SuperObject {

    public void setImage(BufferedImage image);

    public BufferedImage getImage();

    public void setName(String name);

    public String getName();

    public void setCollision(boolean collision);

    public boolean getCollision();

    public void setWorldX(int worldX);

    public int getWorldX();

    public void setWorldY(int worldY);

    public int getWorldY();

    public void interact();

    public void use();

    public boolean isUseEnable();
}
