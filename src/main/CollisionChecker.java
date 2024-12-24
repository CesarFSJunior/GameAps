package main;

import entities.Directions;
import entities.Entity;
import objects.SuperObject;

import java.util.Optional;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {

        int entityLeftWorldX = entity.getWorldX() + entity.getSolidArea().x;
        int entityRightWorldX = entity.getWorldX() + entity.getSolidArea().x + entity.getSolidArea().width;
        int entityTopWorldY = entity.getWorldY() + entity.getSolidArea().y;
        int entityBottomWorldY = entity.getWorldY() + entity.getSolidArea().y + entity.getSolidArea().height;

//        int entityLeftCol = entityLeftWorldX/gp.getTileSize();
//        int entityRightCol = entityRightWorldX/gp.getTileSize();
//        int entityTopRow = entityTopWorldY/gp.getTileSize();
//        int entityBottomRow = entityBottomWorldY/gp.getTileSize();

        if (gp.getKeyHandler().getUpPressed()) {
            int entityLeftCol = entityLeftWorldX/gp.getTileSize();
            int entityRightCol = entityRightWorldX/gp.getTileSize();

            int entityTopRow = (entityTopWorldY - entity.getSpeed()) / gp.getTileSize();
            int tileNum1 = gp.getTm().getMapTileNum()[entityLeftCol][entityTopRow];
            int tileNum2 = gp.getTm().getMapTileNum()[entityRightCol][entityTopRow];
            if (gp.getTm().getTile()[tileNum1].isCollision() || gp.getTm().getTile()[tileNum2].isCollision()) {
                entity.setCollisionOnTop(true);
            }
        }

        if (gp.getKeyHandler().getDownPressed()) {
            int entityLeftCol = entityLeftWorldX/gp.getTileSize();
            int entityRightCol = entityRightWorldX/gp.getTileSize();

            int entityBottomRowY = (entityBottomWorldY + entity.getSpeed());
            if (entityBottomRowY < gp.getWorldHeight()) {
                int entityBottomRow = entityBottomRowY / gp.getTileSize();
                int tileNum1 = gp.getTm().getMapTileNum()[entityLeftCol][entityBottomRow];
                int tileNum2 = gp.getTm().getMapTileNum()[entityRightCol][entityBottomRow];
                if (gp.getTm().getTile()[tileNum1].isCollision() || gp.getTm().getTile()[tileNum2].isCollision()) {
                    entity.setCollisionOnBottom(true);
                }
            }
        }

        if (gp.getKeyHandler().getLeftPressed()) {
            int entityRightCol = entityRightWorldX/gp.getTileSize();
            int entityTopRow = entityTopWorldY/gp.getTileSize();
            int entityBottomRow = entityBottomWorldY/gp.getTileSize();

            int entityLeftCol = (entityLeftWorldX - entity.getSpeed()) / gp.getTileSize();
            int tileNum1 = gp.getTm().getMapTileNum()[entityLeftCol][entityTopRow];
            int tileNum2 = gp.getTm().getMapTileNum()[entityLeftCol][entityBottomRow];
            if (gp.getTm().getTile()[tileNum1].isCollision() || gp.getTm().getTile()[tileNum2].isCollision()) {
                entity.setCollisionOnLeft(true);
            }
        }

        if (gp.getKeyHandler().getRightPressed()) {
            int entityLeftCol = entityLeftWorldX/gp.getTileSize();
            int entityTopRow = entityTopWorldY/gp.getTileSize();
            int entityBottomRow = entityBottomWorldY/gp.getTileSize();

            int entityRightColX = (entityRightWorldX + entity.getSpeed());
            if (entityRightColX < gp.getWorldWidth()) {
                int entityRightCol = entityRightColX / gp.getTileSize();
                int tileNum1 = gp.getTm().getMapTileNum()[entityRightCol][entityTopRow];
                int tileNum2 = gp.getTm().getMapTileNum()[entityRightCol][entityBottomRow];
                if (gp.getTm().getTile()[tileNum1].isCollision() || gp.getTm().getTile()[tileNum2].isCollision()) {
                    entity.setCollisionOnRight(true);
                }
            }
        }
    }

    public void checkWorld(Entity entity) {

        if (this.gp.getKeyHandler().getLeftPressed()) {
            if (entity.getWorldX() - entity.getSpeed() < 0) {
                   entity.setCollisionOnLeft(true);
            }
        }

        if (this.gp.getKeyHandler().getRightPressed()) {
            if (entity.getWorldX() + entity.getSpeed() > this.gp.getWorldWidth() - this.gp.getTileSize()) {
                entity.setCollisionOnRight(true);
            }
        }

        if (this.gp.getKeyHandler().getUpPressed()) {
            if (entity.getWorldY() - entity.getSpeed() < 0) {
                entity.setCollisionOnTop(true);
            }
        }

        if (this.gp.getKeyHandler().getDownPressed()) {
            // if we take out the -2 the game will broken when you walk for any side while being in the bottom of the map
            if (entity.getWorldY() + entity.getSpeed() > this.gp.getWorldHeight() - this.gp.getTileSize() - 2) {
                entity.setCollisionOnBottom(true);
            }
        }

    }
    
    public void checkObjects(Entity entity) {

        int entityLeftWorldX = entity.getWorldX() + entity.getSolidArea().x;
        int entityRightWorldX = entity.getWorldX() + entity.getSolidArea().x + entity.getSolidArea().width;
        int entityTopWorldY = entity.getWorldY() + entity.getSolidArea().y;
        int entityBottomWorldY = entity.getWorldY() + entity.getSolidArea().y + entity.getSolidArea().height;

        int colL = entityLeftWorldX / gp.getTileSize();
        int rowT = entityTopWorldY / gp.getTileSize();
        int colR = entityRightWorldX / gp.getTileSize();
        int rowB = entityBottomWorldY / gp.getTileSize();

        Optional<SuperObject> object1 = Optional.empty();
        Optional<SuperObject> object2 = Optional.empty();

        if (gp.getKeyHandler().getUpPressed()) {
            int rowTt = (entityTopWorldY - entity.getSpeed()) / gp.getTileSize();
            object1 = gp.getAss().findObj(colL, rowTt);
            object2 = gp.getAss().findObj(colR, rowTt);
            if (!object1.isEmpty()) {
                if (object1.get().getCollision()) {
                   entity.setCollisionOnTop(true);
                }
            } else if (!object2.isEmpty()) {
                if (object2.get().getCollision()) {
                    entity.setCollisionOnTop(true);
                }
            }
        }

        if (gp.getKeyHandler().getDownPressed()) {
            int rowBt = (entityBottomWorldY + entity.getSpeed()) / gp.getTileSize();
            object1 = gp.getAss().findObj(colL, rowBt);
            object2 = gp.getAss().findObj(colR, rowBt);
            if (!object1.isEmpty()) {
                if (object1.get().getCollision()) {
                    entity.setCollisionOnBottom(true);
                }
            } else if (!object2.isEmpty()) {
                if (object2.get().getCollision()) {
                    entity.setCollisionOnBottom(true);
                }
            }
        }

        if (gp.getKeyHandler().getLeftPressed()) {
            int colLt = (entityLeftWorldX - entity.getSpeed()) / gp.getTileSize();
            object1 = gp.getAss().findObj(colLt, rowT);
            object2 = gp.getAss().findObj(colLt, rowB);
            if (!object1.isEmpty()) {
                if (object1.get().getCollision()) {
                    entity.setCollisionOnLeft(true);
                }
            } else if (!object2.isEmpty()) {
                if (object2.get().getCollision()) {
                    entity.setCollisionOnLeft(true);
                }
            }
        }

        if (gp.getKeyHandler().getRightPressed()) {
            int colRt = (entityRightWorldX + entity.getSpeed()) / gp.getTileSize();
            object1 = gp.getAss().findObj(colRt, rowT);
            object2 = gp.getAss().findObj(colRt, rowB);
            if (!object1.isEmpty()) {
                if (object1.get().getCollision()) {
                    entity.setCollisionOnRight(true);
                }
            } else if (!object2.isEmpty()) {
                if (object2.get().getCollision()) {
                    entity.setCollisionOnRight(true);
                }
            }
        }

    }

}
