package entities;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    private int worldX, worldY;
    private int speed;
    private boolean isMooving = false;

    private BufferedImage down, down1, down2, up, up1, up2, left, left1, left2, right, right1, right2;
    private Directions direction;

    private int spriteQuantity = 3;
    private int spriteCounter = 0;
    private int spriteNumber = 1;

    private Rectangle solidArea;
    private boolean collisionOnTop, collisionOnBottom, collisionOnLeft, collisionOnRight = false;

    public int getWorldX() {
        return this.worldX;
    }

    public void setWorldX(int x) {
        this.worldX = x;
    }

    public int getWorldY() {
        return this.worldY;
    }

    public void setWorldY(int y) {
        this.worldY = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public BufferedImage getDown() {
        return down;
    }

    public void setDown(BufferedImage down) {
        this.down = down;
    }

    public BufferedImage getDown1() {
        return down1;
    }

    public void setDown1(BufferedImage down1) {
        this.down1 = down1;
    }

    public BufferedImage getDown2() {
        return down2;
    }

    public void setDown2(BufferedImage down2) {
        this.down2 = down2;
    }

    public BufferedImage getUp() {
        return up;
    }

    public void setUp(BufferedImage up) {
        this.up = up;
    }

    public BufferedImage getUp1() {
        return up1;
    }

    public void setUp1(BufferedImage up1) {
        this.up1 = up1;
    }

    public BufferedImage getUp2() {
        return up2;
    }

    public void setUp2(BufferedImage up2) {
        this.up2 = up2;
    }

    public BufferedImage getLeft() {
        return left;
    }

    public void setLeft(BufferedImage left) {
        this.left = left;
    }

    public BufferedImage getLeft1() {
        return left1;
    }

    public void setLeft1(BufferedImage left1) {
        this.left1 = left1;
    }

    public BufferedImage getLeft2() {
        return left2;
    }

    public void setLeft2(BufferedImage left2) {
        this.left2 = left2;
    }

    public BufferedImage getRight() {
        return right;
    }

    public void setRight(BufferedImage right) {
        this.right = right;
    }

    public BufferedImage getRight1() {
        return right1;
    }

    public void setRight1(BufferedImage right1) {
        this.right1 = right1;
    }

    public BufferedImage getRight2() {
        return right2;
    }

    public void setRight2(BufferedImage right2) {
        this.right2 = right2;
    }

    public Directions getDirection() {
        return direction;
    }

    public void setDirection(Directions direction) {
        this.direction = direction;
    }

    public int getSpriteCounter() {
        return spriteCounter;
    }

    public void increaseSpriteCounter() {
        if (this.isMooving) {
            if (this.spriteCounter > 12) {
                this.increaseSpriteNumber();
                this.spriteCounter = 0;
            } else {
                this.spriteCounter++;
            }
        } else {
            this.increaseSpriteNumber();
            this.spriteCounter = 0;
        }

    }

    public int getSpriteNumber() {
        return spriteNumber;
    }

    private void increaseSpriteNumber() {
        if (isMooving) {
            if (this.getSpriteNumber() >= this.spriteQuantity) {
                this.spriteNumber = 1;
            } else {
                this.spriteNumber++;
            }
        } else {
            this.spriteNumber = 1;
        }
    }

    public boolean isMooving() {
        return isMooving;
    }

    public void setMooving(boolean mooving) {
        isMooving = mooving;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
    }

    public boolean isCollisionOnTop() {
        return collisionOnTop;
    }

    public void setCollisionOnTop(boolean collisionOnTop) {
        this.collisionOnTop = collisionOnTop;
    }

    public boolean isCollisionOnBottom() {
        return collisionOnBottom;
    }

    public void setCollisionOnBottom(boolean collisionOnBottom) {
        this.collisionOnBottom = collisionOnBottom;
    }

    public boolean isCollisionOnLeft() {
        return collisionOnLeft;
    }

    public void setCollisionOnLeft(boolean collisionOnLeft) {
        this.collisionOnLeft = collisionOnLeft;
    }

    public boolean isCollisionOnRight() {
        return collisionOnRight;
    }

    public void setCollisionOnRight(boolean collisionOnRight) {
        this.collisionOnRight = collisionOnRight;
    }
}
