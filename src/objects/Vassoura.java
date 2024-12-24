package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Vassoura extends Item{

    public Vassoura(GamePanel gp) {
        super(gp);

        this.setName("Vassoura");
        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/objects/Vassoura.png"));
            this.setImage(image);
        } catch(IOException e) {
            e.printStackTrace();
        }

    }

}
