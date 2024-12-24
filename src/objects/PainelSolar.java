package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PainelSolar extends Object{

    private GamePanel gp;
    private BufferedImage imgClose;

    public PainelSolar(GamePanel gp) {

        this.gp = gp;
        this.setName("Painel Solar");
        this.setCollision(true);
        try {
            this.imgClose = ImageIO.read(getClass().getResourceAsStream("/objects/painelSolar.png"));

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
