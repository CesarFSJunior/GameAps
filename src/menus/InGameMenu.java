package menus;

import main.GamePanel;

import java.awt.*;
import java.util.Arrays;

public class InGameMenu {

    private GamePanel gp;
    private SelectedItem selectedItem = SelectedItem.Voltar;
    private boolean updated = false;

    public InGameMenu(GamePanel gp) {
        this.gp = gp;
    }

    public void menuDown() {
        this.selectedItem = SelectedItem.values()[(this.selectedItem.ordinal() + 1) % SelectedItem.values().length];
    }

    public void interact() {
        switch (this.selectedItem) {
            case SelectedItem.Voltar:
                this.gp.setOnMenu(false);
                this.gp.setGamePaused(false);
            break;
            case SelectedItem.Sair:
                System.exit(0);
            break;
        }
    }

    public void draw(Graphics2D g) {

        g.setColor(new Color(0, 0, 0, 128));
        g.setFont(new Font("Times New Roman", Font.BOLD, 16));
        g.fillRect(0, 0, gp.getScreenWidth(), gp.getScreenHeight());

        Arrays.stream(SelectedItem.values()).forEach(item -> drawRect(item, g, this.selectedItem));

    }

    public void drawRect(SelectedItem item, Graphics2D g, SelectedItem actualItem) {

        Color c = new Color(93, 93, 93);

            if(item.ordinal() == actualItem.ordinal()) {
                c = new Color(153, 153, 153);
            }

            int y = (gp.getScreenHeight() / (SelectedItem.values().length + 2)) * (item.ordinal() + 1);

            g.setColor(c);
            g.fillRect((gp.getScreenWidth() / 4), y, (gp.getScreenWidth() / 4) * 2, 30);
            g.setColor(Color.black);
            g.drawString(item.name(), (gp.getScreenWidth() / 4) * 2 - 10, y + (30 / 2) + 6);

    }

}
