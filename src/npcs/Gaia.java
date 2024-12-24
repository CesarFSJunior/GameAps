package npcs;

import main.GamePanel;
import objects.Object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Gaia extends Object {

    GamePanel gp;

    public Gaia(GamePanel gp) {
        this.gp = gp;
        this.setName("Gaia");
        this.setCollision(true);
        try {
            this.setImage(ImageIO.read(getClass().getResourceAsStream("/npcs/gaia.png")));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void interact() {

        gp.getStory().eventTriger(this);

    }

    @Override
    public void use() {

    }
}
