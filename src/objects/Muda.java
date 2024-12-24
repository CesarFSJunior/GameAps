package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class Muda extends Object{

    private GamePanel gp;
    private BufferedImage imgClose;

    public Muda(GamePanel gp) {

        this.gp = gp;
        this.setName("Muda");
        this.setCollision(true);
        try {
            this.imgClose = ImageIO.read(getClass().getResourceAsStream("/objects/Muda.png"));

            this.setImage(this.imgClose);
        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void interact() {



    }

    @Override
    public void use() {

        gp.getAddObjectMode().setActivated(this);

    }
}
