package entities;

import main.GamePanel;
import main.KeyHandler;
import objects.DoorObject;
import objects.Muda;
import objects.SuperObject;

import javax.imageio.ImageIO;
import javax.swing.text.html.Option;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class Player extends Entity{

    private GamePanel gp;
    private KeyHandler kH;

    private int screenX;
    private int screenY;

    private ArrayList<SuperObject> inventory = new ArrayList<>();

    public Player(GamePanel gp, KeyHandler keyHandler) {
        this.gp = gp;
        this.kH = keyHandler;

        this.screenX = gp.getScreenWidth() / 2 - (gp.getTileSize()/2);
        this.screenY = gp.getScreenHeight() / 2 - (gp.getTileSize()/2);

        this.setSolidArea(new Rectangle(2 * gp.getScale(), 10 * gp.getScale(), 12 * gp.getScale(), 6 * gp.getScale()));

        this.SetDefaultValues();
        this.getPlayerImage();
    }

    public void getPlayerImage() {
        try {

            setUp(ImageIO.read(getClass().getResourceAsStream("/player/PlayerUp.png")));
            setUp1(ImageIO.read(getClass().getResourceAsStream("/player/PlayerWalkingUp1.png")));
            setUp2(ImageIO.read(getClass().getResourceAsStream("/player/PlayerWalkingUp2.png")));
            setDown(ImageIO.read(getClass().getResourceAsStream("/player/PlayerDown.png")));
            setDown1(ImageIO.read(getClass().getResourceAsStream("/player/PlayerWalkingDown1.png")));
            setDown2(ImageIO.read(getClass().getResourceAsStream("/player/PlayerWalkingDown2.png")));
            setLeft(ImageIO.read(getClass().getResourceAsStream("/player/PlayerLeft.png")));
            setLeft1(ImageIO.read(getClass().getResourceAsStream("/player/PlayerWalkingLeft1.png")));
            setLeft2(ImageIO.read(getClass().getResourceAsStream("/player/PlayerWalkingLeft2.png")));
            setRight(ImageIO.read(getClass().getResourceAsStream("/player/PlayerRight.png")));
            setRight1(ImageIO.read(getClass().getResourceAsStream("/player/PlayerWalkingRight1.png")));
            setRight2(ImageIO.read(getClass().getResourceAsStream("/player/PlayerWalkingRight2.png")));

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void SetDefaultValues() {
        this.setWorldX(gp.getTileSize() * 4);
        this.setWorldY(gp.getTileSize() * 27);
        this.setSpeed(4);
        this.setDirection(Directions.Down);
    }

    public void updateScreenPosition() {

        int limiteWidthLeft = gp.getScreenWidth() / 2 - (gp.getTileSize()/2);
        int limiteHeightTop = gp.getScreenHeight() / 2 - (gp.getTileSize()/2);
        int limiteWidthRight = gp.getScreenWidth() / 2 + (gp.getTileSize()/2);
        int limiteHeightBottom = gp.getScreenHeight() / 2 + (gp.getTileSize()/2);

        //L limite
        if (this.getWorldX() - limiteWidthLeft < 0) {
            this.setScreenX(this.getWorldX());
        }

        //T limite
        if (this.getWorldY() - limiteHeightTop < 0) {
            this.setScreenY(this.getWorldY());
        }

        //R limite
        if (this.getWorldX() + limiteWidthRight >= gp.getWorldWidth() && kH.getRightPressed() &&
            this.getScreenX() < gp.getScreenWidth() - gp.getTileSize() && !this.isCollisionOnRight()) {
            this.setScreenX(this.getScreenX() + this.getSpeed());
        }

        // B Limite
        if (this.getWorldY() + limiteHeightBottom >= gp.getWorldHeight() && kH.getDownPressed() &&
            this.getScreenY() < gp.getScreenHeight() - gp.getTileSize() && !this.isCollisionOnBottom()) {
            this.setScreenY(this.getScreenY() + this.getSpeed());
        }

        // L Travar no meio
        if (this.getScreenX() >= limiteWidthLeft && kH.getLeftPressed() && !this.isCollisionOnLeft()) {
            this.setScreenX(this.getScreenX() - this.getSpeed());
        }

        //T Travar Meio
        if (this.getScreenY() >= limiteHeightTop && kH.getUpPressed() && !this.isCollisionOnTop()) {
            this.setScreenY(this.getScreenY() - this.getSpeed());
        }

    }

    public void Update() {
        if (kH.getUpPressed() || kH.getDownPressed() || kH.getLeftPressed() || kH.getRightPressed()) {
            setMooving(true);
        } else {
            setMooving(false);
        }

        gp.getCollisionChecker().checkTile(this);
        gp.getCollisionChecker().checkWorld(this);
        gp.getCollisionChecker().checkObjects(this);

        if (kH.getUpPressed()) {
            this.setDirection(Directions.Up);
            if (!this.isCollisionOnTop()) {
                this.setWorldY(this.getWorldY() - this.getSpeed());
            }
        }
        if (kH.getDownPressed()) {
            this.setDirection(Directions.Down);
            if (!this.isCollisionOnBottom()) {
                this.setWorldY(this.getWorldY() + this.getSpeed());
            }
        }
        if (kH.getLeftPressed()) {
            this.setDirection(Directions.Left);
            if (!this.isCollisionOnLeft()) {
                this.setWorldX(this.getWorldX() - this.getSpeed());
            }
        }
        if (kH.getRightPressed()) {
            this.setDirection(Directions.Right);
            if (!this.isCollisionOnRight()) {
                this.setWorldX(this.getWorldX() + this.getSpeed());
            }
        }

        this.updateScreenPosition();

        this.setCollisionOnTop(false);
        this.setCollisionOnBottom(false);
        this.setCollisionOnLeft(false);
        this.setCollisionOnRight(false);

        increaseSpriteCounter();

    }

    public void Draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (this.getDirection()) {
            case Up :
                if (getSpriteNumber() == 1) {
                    image = getUp();
                } else if (getSpriteNumber() == 2) {
                    image = getUp1();
                } else if (getSpriteNumber() == 3) {
                    image = getUp2();
                }
                break;
            case Down:
                if (getSpriteNumber() == 1) {
                    image = getDown();
                } else if (getSpriteNumber() == 2) {
                    image = getDown1();
                } else if (getSpriteNumber() == 3) {
                    image = getDown2();
                }
                break;
            case Right:
                if (getSpriteNumber() == 1) {
                    image = getRight();
                } else if (getSpriteNumber() == 2) {
                    image = getRight1();
                } else if (getSpriteNumber() == 3) {
                    image = getRight2();
                }
                break;
            case Left:
                if (getSpriteNumber() == 1) {
                    image = getLeft();
                } else if (getSpriteNumber() == 2) {
                    image = getLeft1();
                } else if (getSpriteNumber() == 3) {
                    image = getLeft2();
                }
                break;
        }
        g2.drawImage(image, this.screenX, this.screenY, gp.getTileSize(), gp.getTileSize(), null);

        //Ponto mostrando real posição do player e mostradores de posição
        g2.setColor(Color.white);
        g2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        String pos = "World X: " + this.getWorldX() + ", World Y: " + this.getWorldY();
        g2.drawString(pos, 20, 20);
        String screenPos = "Screen X: " + this.getScreenX() + ", Screen Y: " + this.getScreenY();
        g2.drawString(screenPos, 20, 40);
        g2.setColor(Color.red);
        g2.drawRect(this.getScreenX(), this.getScreenY(), 2, 2);

    }

    public void playerInteraction() {

        int entityLeftWorldX = this.getWorldX() + this.getSolidArea().x;
        int entityRightWorldX = this.getWorldX() + this.getSolidArea().x + this.getSolidArea().width;
        int entityTopWorldY = this.getWorldY() + this.getSolidArea().y;
        int entityBottomWorldY = this.getWorldY() + this.getSolidArea().y + this.getSolidArea().height;

        int colL = entityLeftWorldX / gp.getTileSize();
        int rowT = entityTopWorldY / gp.getTileSize();
        int colR = entityRightWorldX / gp.getTileSize();
        int rowB = entityBottomWorldY / gp.getTileSize();

        Optional<SuperObject> object1 = Optional.empty();
        Optional<SuperObject> object2 = Optional.empty();

        switch(gp.getPlayer().getDirection()) {
            case Directions.Up :
                rowT -= 1;
                object1 = gp.getAss().findObj(colL, rowT);
                object2 = gp.getAss().findObj(colR, rowT);
                break;
            case Directions.Down:
                rowB += 1;
                object1 = gp.getAss().findObj(colL, rowB);
                object2 = gp.getAss().findObj(colR, rowB);
                break;
            case Directions.Left:
                colL -= 1;
                object1 = gp.getAss().findObj(colL, rowT);
                object2 = gp.getAss().findObj(colL, rowB);
                break;
            case Directions.Right:
                colR += 1;
                object1 = gp.getAss().findObj(colR, rowT);
                object2 = gp.getAss().findObj(colR, rowB);
        }

        if (!object1.isEmpty()) {
            object1.get().interact();
        } else if (!object2.isEmpty()) {
            object2.get().interact();
        }

    }

    public int getScreenX() {
        return screenX;
    }

    private void setScreenX(int screenX) {
        this.screenX = screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    private void setScreenY(int screenY) {
        this.screenY = screenY;
    }

    public ArrayList<SuperObject> getInventory() {
        return inventory;
    }

    public void addItemToInventory(SuperObject item) {
        this.inventory.add(item);
    }
}
