package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Placa extends Object{

    private ArrayList<String> txt;
    private GamePanel gp;
    private BufferedImage image2;

    public Placa(GamePanel gp) {
        this.gp = gp;
        this.setName("Placa");
        this.setCollision(true);
        try {
            this.setImage(ImageIO.read(getClass().getResourceAsStream("/objects/Placa.png")));
            this.image2 = ImageIO.read(getClass().getResourceAsStream("/objects/PlacaR.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void interact() {
        this.gp.getDialogPannel().receiveDialog(this.txt);
    }

    @Override
    public void use() {

    }

    public String getTxt() {
        return txt.get(0);
    }

    public void setTxt(String txt) {
        ArrayList<String> list = new ArrayList<>();
        list.add(txt);
        this.txt = list;
    }

    public void changeToImgR() {
        this.setImage(this.image2);
    }
}
