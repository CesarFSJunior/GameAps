package npcs;

import main.GamePanel;
import objects.Object;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class MajorSilva extends Object {

    GamePanel gp;

    public MajorSilva(GamePanel gp) {
        this.gp = gp;
        this.setName("Prefeito Silva");
        this.setCollision(true);
        try {
            this.setImage(ImageIO.read(getClass().getResourceAsStream("/npcs/MajorSilva.png")));
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
