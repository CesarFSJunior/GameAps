package map;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    private GamePanel gp;
    private Tile[] tile;
    private int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;

        this.tile = new Tile[20];
        this.mapTileNum = new int[gp.getMaxWorldCol()][gp.getMaxWorldRow()];

        this.getTileImage();
        this.loadMap("/maps/map01.txt");
    }

    public void getTileImage() {

        try {
            this.tile[0] = new Tile();
            this.tile[0].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/Grass.png")));

            this.tile[1] = new Tile();
            this.tile[1].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/Water.png")));
            this.tile[1].setCollision(true);

            this.tile[2] = new Tile();
            this.tile[2].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/Wall.png")));
            this.tile[2].setCollision(true);

            this.tile[3] = new Tile();
            this.tile[3].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/Tree.png")));
            this.tile[3].setCollision(true);

            this.tile[4] = new Tile();
            this.tile[4].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/Sand.png")));

            this.tile[5] = new Tile();
            this.tile[5].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/Dirth.png")));

            this.tile[6] = new Tile();
            this.tile[6].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/Grass1.png")));

            this.tile[7] = new Tile();
            this.tile[7].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/LDirth.png")));

            this.tile[8] = new Tile();
            this.tile[8].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/RDirth.png")));

            this.tile[9] = new Tile();
            this.tile[9].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/TDirth.png")));

            this.tile[10] = new Tile();
            this.tile[10].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/BDirth.png")));

            this.tile[11] = new Tile();
            this.tile[11].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/TRODirth.png")));

            this.tile[12] = new Tile();
            this.tile[12].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/BRODirth.png")));

            this.tile[13] = new Tile();
            this.tile[13].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/Madeira.png")));

            this.tile[14] = new Tile();
            this.tile[14].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/TijoloVermelho.png")));
            this.tile[14].setCollision(true);

            this.tile[15] = new Tile();
            this.tile[15].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/PisoMetal.png")));

            this.tile[16] = new Tile();
            this.tile[16].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/LBIDirth.png")));

            this.tile[17] = new Tile();
            this.tile[17].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/Glass.png")));
            this.tile[17].setCollision(true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String mapPath) {

        try {

            InputStream is = getClass().getResourceAsStream(mapPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.getMaxWorldCol() && row < gp.getMaxWorldRow()) {
                String line = br.readLine();

                while ( col < gp.getMaxWorldCol()) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    this.mapTileNum[col][row] = num;
                    col ++;
                }
                if (col == gp.getMaxWorldCol()) {
                    col = 0;
                    row ++;
                }
            }

            br.close();

        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.getMaxWorldCol() && worldRow < gp.getMaxWorldRow()) {

            int tileNum = this.mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.getTileSize();
            int worldY = worldRow * gp.getTileSize();
            int screenX = worldX - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();
            int screenY = worldY - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();


            if (screenX > worldX) {
                screenX = worldX;
            }
            if (screenY > worldY) {
                screenY = worldY;
            }

            int limiteWidth = gp.getScreenWidth() / 2 + (gp.getTileSize()/2);
            int limiteHeight = gp.getScreenHeight() / 2 + (gp.getTileSize()/2);

            if (gp.getPlayer().getWorldX() + limiteWidth > gp.getWorldWidth() && worldCol >= gp.getMaxWorldCol() - gp.getMaxScreenCol()) {
                screenX = (worldCol - (gp.getMaxWorldRow() - gp.getMaxScreenCol())) * gp.getTileSize();
            }

            if (gp.getPlayer().getWorldY() + limiteHeight > gp.getWorldHeight() && worldRow >= gp.getMaxWorldRow() - gp.getMaxScreenRow()) {
                screenY = (worldRow - (gp.getMaxWorldRow() - gp.getMaxScreenRow())) * gp.getTileSize();
            }

            //Faz com que a imagem so seja carregada se estiver na tela
            if (
                worldX + gp.getTileSize() > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX() //Esquerda
                && worldX - gp.getTileSize() < gp.getPlayer().getWorldX() + ( gp.getScreenWidth() - gp.getPlayer().getScreenX()) // Direita
                && worldY + gp.getTileSize() > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY() // Cima
                && worldY - gp.getTileSize() < gp.getPlayer().getWorldY() + ( gp.getScreenHeight() - gp.getPlayer().getScreenY()) // Baixo
            ) {

                g2.drawImage(this.tile[tileNum].getImage(), screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
            }

            worldCol ++;
            if (worldCol == gp.getMaxWorldCol()) {
                worldCol = 0;
                worldRow++;
            }
        }

    }

    public Tile[] getTile() {
        return tile;
    }

    public int[][] getMapTileNum() {
        return mapTileNum;
    }
}
