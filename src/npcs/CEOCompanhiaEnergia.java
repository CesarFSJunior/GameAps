package npcs;

import main.GamePanel;
import objects.Object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class CEOCompanhiaEnergia extends Object {

    GamePanel gp;

    public CEOCompanhiaEnergia(GamePanel gp) {
        this.gp = gp;
        this.setName("CEO Companhia Energia");
        this.setCollision(true);
        try {
            this.setImage(ImageIO.read(getClass().getResourceAsStream("/npcs/CEOCompaniaEnergia.png")));
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
