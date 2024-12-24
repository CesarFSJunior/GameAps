package npcs;

import main.GamePanel;
import objects.Object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.ArrayList;

public class Computador extends Object {

    private GamePanel gp;


    public Computador(GamePanel gp) {
        this.gp = gp;
        this.setName("Super Computador");
        this.setCollision(true);
        try {
            this.setImage(ImageIO.read(getClass().getResourceAsStream("/npcs/ComputadorOn.png")));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void interact() {

        this.gp.getStory().eventTriger(this);
    }

    @Override
    public void use() {

    }
}
