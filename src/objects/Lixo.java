package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class Lixo extends Item{

    private Optional<SuperObject> collectableBy = Optional.empty();

    public Lixo(GamePanel gp) {
        super(gp);

        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/objects/Lixo.png"));
            this.setName("Lixo");
            this.setImage(image);
        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void interact() {
        SuperObject obj = this.collectableBy.get();
        if (this.getGp().getPlayer().getInventory().indexOf(obj) != -1) {
            super.interact();
        } else {
            ArrayList<String> txt = new ArrayList<>();
            txt.add(this.collectableBy.get().getName() + " necessario para coletar o lixo!");
            this.getGp().getDialogPannel().receiveDialog(txt);
        }

    }

    public SuperObject getCollectableBy() {
        return collectableBy.get();
    }

    public void setCollectableBy(SuperObject collectableBy) {
        this.collectableBy = Optional.of(collectableBy);
    }
}

