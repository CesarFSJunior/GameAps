package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class KeyItem extends Item{

    public KeyItem(GamePanel gp) {
        super(gp);

        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
            this.setImage(image);
        } catch(IOException e) {
            e.printStackTrace();
        }

    }

}
