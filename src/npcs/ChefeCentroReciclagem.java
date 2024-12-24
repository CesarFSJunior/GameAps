package npcs;

import main.GamePanel;
import objects.Object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ChefeCentroReciclagem extends Object {

    GamePanel gp;

    public ChefeCentroReciclagem(GamePanel gp) {
        this.gp = gp;
        this.setName("Chefe centro de reciclagem");
        this.setCollision(true);
        try {
            this.setImage(ImageIO.read(getClass().getResourceAsStream("/npcs/CheefeCentroReciclagem.png")));
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
