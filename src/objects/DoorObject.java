package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import javax.swing.text.html.Option;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class DoorObject extends Object{

    private GamePanel gp;
    private BufferedImage imgClose;
    private BufferedImage imgOpen;
    private Optional<SuperObject> lockedBy = Optional.empty();

    public DoorObject(GamePanel gp) {

        this.gp = gp;
        this.setName("Door");
        try {
            this.imgClose = ImageIO.read(getClass().getResourceAsStream("/objects/CloseDoor.png"));
            this.imgOpen = ImageIO.read(getClass().getResourceAsStream("/objects/OpenDoor.png"));

            this.setImage(this.imgClose);
        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void interact() {

        if (this.lockedBy.isEmpty()) {
            changeState();
        } else {
            SuperObject obj = this.lockedBy.get();
            if (this.gp.getPlayer().getInventory().indexOf(obj) != -1) {
                changeState();
            } else {
                ArrayList<String> txt = new ArrayList<>();
                txt.add(this.lockedBy.get().getName() + " necessario para abrir a porta");
                gp.getDialogPannel().receiveDialog(txt);
            }
        }


    }

    public void changeState() {
        if (this.getImage().equals(this.imgClose)) {
            this.setImage(this.imgOpen);
            this.setCollision(false);
        } else {
            this.setImage(this.imgClose);
            this.setCollision(true);
        }
    }

    @Override
    public void use() {

    }

    public SuperObject getLockedBy() {
        return lockedBy.get();
    }

    public void setLockedBy(SuperObject lockedBy) {
        this.lockedBy = Optional.of(lockedBy);
    }
}
