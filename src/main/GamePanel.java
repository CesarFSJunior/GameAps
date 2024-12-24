package main;

import dialog.DialogKeyHandler;
import dialog.DialogPannel;
import entities.Player;
import map.TileManager;
import menus.InGameMenu;
import menus.Inventory;
import menus.InventoryKeyHandler;
import menus.MenuKeyHandler;
import objects.SuperObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable{

    // Screen Settings
    private final int originalTileSize = 16;
    private final int scale = 3;

    private final int tileSize = this.originalTileSize * this.scale;
    private final int maxScreenCol = 16;
    private final int maxScreenRow = 12;
    private final int screenWidth = this.maxScreenCol * this.tileSize;
    private final int screenHeight = this.maxScreenRow * this.tileSize;

    // World Settings
    private final int maxWorldCol = 34;
    private final int maxWorldRow = 34;
    private final int worldWidth = this.tileSize * this.maxWorldCol;
    private final int worldHeight = this.tileSize * this.maxWorldRow;

    // FPS
    private int FPS = 60;

    // GameSettings
    private boolean gamePaused = false;
    private boolean onMenu = false;
    private boolean onInventory = false;

    private Story story = new Story(this);
    private TileManager tm = new TileManager(this);
    private KeyListener keyHandler = new KeyHandler(this);
    private KeyListener menuKeyHandler = new MenuKeyHandler(this);
    private KeyListener inventoryKeyHandler = new InventoryKeyHandler(this);
    private KeyListener dialogKeyHandler = new DialogKeyHandler(this);
    private Thread gameThread;
    private CollisionChecker collisionChecker = new CollisionChecker(this);
    private Player player = new Player(this, (KeyHandler) keyHandler);
    private ArrayList<SuperObject> objects = new ArrayList<>();
    private AssetSetter ass = new AssetSetter(this);
    private InGameMenu inGameMenu = new InGameMenu(this);
    private Inventory inventory = new Inventory(this);
    private DialogPannel dialogPannel = new DialogPannel(this);
    private AddObjectMode addObjectMode = new AddObjectMode(this);
    private MouseMovementHandler mouseMovementHandler = new MouseMovementHandler();
    private MouseHandler mouseHandler = new MouseHandler(this);


    public GamePanel() {
        this.setPreferredSize(new Dimension(this.screenWidth, this.screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        this.addMouseMotionListener(mouseMovementHandler);
        this.addMouseListener(mouseHandler);
    }

    public void startGameThread() {
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/this.FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(this.gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta > 1) {
                if (!this.gamePaused) {
                    this.updateGame();
                }
                this.repaint();
                delta --;
                drawCount ++;
            }

            if ( timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }
    }
    public void updateGame() {
        player.Update();
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        tm.draw(g2);
        ass.draw(g2);
        addObjectMode.draw(g2);
        player.Draw(g2);
        dialogPannel.draw(g2);

        if (this.onMenu) {
            this.inGameMenu.draw(g2);
        } else if (this.onInventory) {
            this.inventory.draw(g2);
        }

        g2.dispose();
    }

    public int getTileSize() {
        return tileSize;
    }

    public int getMaxScreenCol() {
        return maxScreenCol;
    }

    public int getMaxScreenRow() {
        return maxScreenRow;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getMaxWorldCol() {
        return maxWorldCol;
    }

    public int getMaxWorldRow() {
        return maxWorldRow;
    }

    public Player getPlayer() {
        return player;
    }

    public int getWorldWidth() {
        return worldWidth;
    }

    public int getWorldHeight() {
        return worldHeight;
    }

    public int getScale() {
        return scale;
    }

    public CollisionChecker getCollisionChecker() {
        return collisionChecker;
    }

    public TileManager getTm() {
        return tm;
    }

    public KeyHandler getKeyHandler() {
        return (KeyHandler) keyHandler;
    }

    public ArrayList<SuperObject> getObjects() {
        return objects;
    }

    public void addObject(SuperObject object) {
        this.objects.add(object);
    }

    public void removeObject(SuperObject object) {
        this.objects.remove(object);
    }

    public AssetSetter getAss() {
        return ass;
    }

    public void setGamePaused(boolean paused) {
        this.gamePaused = paused;
    }

    public boolean isGamePaused() {
        return this.gamePaused;
    }

    public boolean isOnMenu() {
        return onMenu;
    }

    public void setOnMenu(boolean onMenu) {
        if (onMenu) {
            this.removeKeyListener(this.keyHandler);
            this.addKeyListener(this.menuKeyHandler);
        } else {
            this.removeKeyListener(this.menuKeyHandler);
            this.addKeyListener(this.keyHandler);
        }
        this.onMenu = onMenu;
    }

    public InGameMenu getInGameMenu() {
        return inGameMenu;
    }

    public MenuKeyHandler getMenuKeyHandler() {
        return (MenuKeyHandler) menuKeyHandler;
    }

    public boolean isOnInventory() {
        return onInventory;
    }

    public void setOnInventory(boolean onInventory) {
        if (onInventory) {
            this.removeKeyListener(this.keyHandler);
            this.addKeyListener(this.inventoryKeyHandler);
        } else {
            this.removeKeyListener(this.inventoryKeyHandler);
            this.addKeyListener(this.keyHandler);
        }
        this.onInventory = onInventory;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public DialogPannel getDialogPannel() {
        return dialogPannel;
    }

    public KeyListener getDialogKeyHandler() {
        return dialogKeyHandler;
    }

    public Story getStory() {
        return story;
    }

    public MouseMovementHandler getMouseMovementHandler() {
        return mouseMovementHandler;
    }

    public MouseHandler getMouseHandler() {
        return mouseHandler;
    }

    public AddObjectMode getAddObjectMode() {
        return addObjectMode;
    }
}