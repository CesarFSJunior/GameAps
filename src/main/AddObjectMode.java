package main;

import objects.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AddObjectMode {

    private GamePanel gp;
    private SuperObject obj;
    private boolean activated = false;
    private int worldX, worldY;

    public AddObjectMode(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g) {
        if (activated) {
            worldX = ((gp.getPlayer().getWorldX() + (gp.getMouseMovementHandler().getMouseX() - gp.getPlayer().getScreenX())) / gp.getTileSize()) * gp.getTileSize();
            worldY = ((gp.getPlayer().getWorldY() + (gp.getMouseMovementHandler().getMouseY() - gp.getPlayer().getScreenY())) / gp.getTileSize()) * gp.getTileSize();

            int screenX = worldX - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();
            int screenY = worldY - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();

            BufferedImage img = this.applyRedFilter(obj.getImage());

            if (this.gp.getTm().getMapTileNum()[worldX / gp.getTileSize()][worldY / gp.getTileSize()] == 0 ||
                    this.gp.getTm().getMapTileNum()[worldX / gp.getTileSize()][worldY / gp.getTileSize()] == 6) {
                img = this.applyGreenFilter(obj.getImage());
            }


            g.drawImage(img, screenX , screenY, gp.getTileSize(), gp.getTileSize(), null);
        }
    }

    public void addObject() {
        if (this.gp.getTm().getMapTileNum()[worldX / gp.getTileSize()][worldY / gp.getTileSize()] == 0 ||
                this.gp.getTm().getMapTileNum()[worldX / gp.getTileSize()][worldY / gp.getTileSize()] == 6) {
            this.obj.setWorldX(worldX);
            this.obj.setWorldY(worldY);
            this.gp.getPlayer().getInventory().remove(this.obj);
            this.gp.addObject(this.obj);
            this.activated = false;
        }
    }

    public BufferedImage applyGreenFilter(BufferedImage original) {
        int width = original.getWidth();
        int height = original.getHeight();

        // Cria uma nova imagem com o mesmo tamanho da original
        BufferedImage greenImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Itera sobre cada pixel da imagem
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Pega o valor RGB do pixel
                int rgb = original.getRGB(x, y);

                // Separa os componentes de cor (red, green, blue)
                Color color = new Color(rgb);
                int green = color.getGreen();
                int alpha = (rgb >> 24) & 0xff;

                // Cria uma nova cor com o valor de cinza para os três componentes
                Color greenColor = new Color(0, green, 0, alpha);

                // Define o pixel na nova imagem
                greenImage.setRGB(x, y, greenColor.getRGB());
            }
        }

        return greenImage;
    }

    public BufferedImage applyRedFilter(BufferedImage original) {
        int width = original.getWidth();
        int height = original.getHeight();

        // Cria uma nova imagem com o mesmo tamanho da original
        BufferedImage redImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Itera sobre cada pixel da imagem
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Pega o valor RGB do pixel
                int rgb = original.getRGB(x, y);

                // Separa os componentes de cor (red, green, blue)
                Color color = new Color(rgb);
                int red = color.getRed();
                int alpha = (rgb >> 24) & 0xff;

                // Cria uma nova cor com o valor de cinza para os três componentes
                Color redColor = new Color(red, 0, 0, alpha);

                // Define o pixel na nova imagem
                redImage.setRGB(x, y, redColor.getRGB());
            }
        }

        return redImage;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(SuperObject object) {
        this.obj = object;
        this.activated = true;
    }

}
