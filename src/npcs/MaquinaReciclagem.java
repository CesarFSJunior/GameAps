package npcs;

import main.GamePanel;
import objects.Object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class MaquinaReciclagem extends Object {

    private GamePanel gp;


    public MaquinaReciclagem(GamePanel gp) {
        this.gp = gp;
        this.setName("Maquina Reciclagem");
        this.setCollision(true);
        try {
            this.setImage(ImageIO.read(getClass().getResourceAsStream("/npcs/MaquinaReciclagem.png")));
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
