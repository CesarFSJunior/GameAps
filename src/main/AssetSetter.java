package main;

import npcs.*;
import objects.*;

import java.awt.*;
import java.util.Optional;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {

        this.gp = gp;

        this.setObject();

    }

    public void setObject() {
        DoorObject door = new DoorObject(this.gp);
        door.setWorldX(13 * gp.getTileSize());
        door.setWorldY(22 * gp.getTileSize());
        door.setCollision(true);
        this.gp.addObject(door);
        Placa placa = new Placa(this.gp);
        placa.setWorldX(11 * gp.getTileSize());
        placa.setWorldY(22 * gp.getTileSize());
        placa.setTxt("Prefeitura");
        this.gp.addObject(placa);
        DoorObject door2= new DoorObject(this.gp);
        door2.setWorldX(26 * gp.getTileSize());
        door2.setWorldY(22 * gp.getTileSize());
        door2.setCollision(true);
        this.gp.addObject(door2);
        DoorObject door3= new DoorObject(this.gp);
        door3.setWorldX(26 * gp.getTileSize());
        door3.setWorldY(5 * gp.getTileSize());
        door3.setCollision(true);
        this.gp.addObject(door3);
        DoorObject door4= new DoorObject(this.gp);
        door4.setWorldX(13 * gp.getTileSize());
        door4.setWorldY(6 * gp.getTileSize());
        door4.setCollision(true);
        this.gp.addObject(door4);
        Placa placa2 = new Placa(this.gp);
        placa2.setWorldX(24 * gp.getTileSize());
        placa2.setWorldY(22 * gp.getTileSize());
        placa2.setTxt("Centro de Reciclagem");
        placa2.changeToImgR();
        this.gp.addObject(placa2);
        Placa placa3 = new Placa(this.gp);
        placa3.setWorldX(11 * gp.getTileSize());
        placa3.setWorldY(6 * gp.getTileSize());
        placa3.setTxt("Estufa");
        this.gp.addObject(placa3);
        Placa placa4 = new Placa(this.gp);
        placa4.setWorldX(24 * gp.getTileSize());
        placa4.setWorldY(5 * gp.getTileSize());
        placa4.setTxt("Companhia de energia");
        this.gp.addObject(placa4);
        MajorSilva major = new MajorSilva(this.gp);
        major.setWorldX(14 * gp.getTileSize());
        major.setWorldY(19 * gp.getTileSize());
        this.gp.addObject(major);
        Computador computador = new Computador(this.gp);
        computador.setWorldX(12 * gp.getTileSize());
        computador.setWorldY(19 * gp.getTileSize());
        this.gp.addObject(computador);
        ChefeCentroReciclagem chefeCentroReciclagem = new ChefeCentroReciclagem(this.gp);
        chefeCentroReciclagem.setWorldX(23 * gp.getTileSize());
        chefeCentroReciclagem.setWorldY(19 * gp.getTileSize());
        this.gp.addObject(chefeCentroReciclagem);
        MaquinaReciclagem maquinaReciclagem = new MaquinaReciclagem(this.gp);
        maquinaReciclagem.setWorldX(25 * gp.getTileSize());
        maquinaReciclagem.setWorldY(19 * gp.getTileSize());
        this.gp.addObject(maquinaReciclagem);
        Gaia gaia = new Gaia(this.gp);
        gaia.setWorldX(13 * gp.getTileSize());
        gaia.setWorldY(3 * gp.getTileSize());
        this.gp.addObject(gaia);
        CEOCompanhiaEnergia ceo = new CEOCompanhiaEnergia(this.gp);
        ceo.setWorldX(26 * gp.getTileSize());
        ceo.setWorldY(2 * gp.getTileSize());
        this.gp.addObject(ceo);
        Vassoura vassoura = new Vassoura(this.gp);
        this.gp.getStory().setFirstMissionObject(vassoura);
        Lixo lixo = new Lixo(gp);
        lixo.setWorldX(6 * gp.getTileSize());
        lixo.setWorldY(28 * gp.getTileSize());
        lixo.setCollectableBy(vassoura);
        this.gp.addObject(lixo);
        Lixo lixo2 = new Lixo(gp);
        lixo2.setWorldX(12 * gp.getTileSize());
        lixo2.setWorldY(29 * gp.getTileSize());
        lixo2.setCollectableBy(vassoura);
        this.gp.addObject(lixo2);
        Lixo lixo3 = new Lixo(gp);
        lixo3.setWorldX(18 * gp.getTileSize());
        lixo3.setWorldY(26 * gp.getTileSize());
        lixo3.setCollectableBy(vassoura);
        this.gp.addObject(lixo3);
        Lixo lixo4 = new Lixo(gp);
        lixo4.setWorldX(24 * gp.getTileSize());
        lixo4.setWorldY(27 * gp.getTileSize());
        lixo4.setCollectableBy(vassoura);
        this.gp.addObject(lixo4);
        Lixo lixo5 = new Lixo(gp);
        lixo5.setWorldX(15 * gp.getTileSize());
        lixo5.setWorldY(27 * gp.getTileSize());
        lixo5.setCollectableBy(vassoura);
        this.gp.addObject(lixo5);
        this.gp.getStory().addFirstMissionGoal(lixo);
        this.gp.getStory().addFirstMissionGoal(lixo2);
        this.gp.getStory().addFirstMissionGoal(lixo3);
        this.gp.getStory().addFirstMissionGoal(lixo4);
        this.gp.getStory().addFirstMissionGoal(lixo5);
    }

    public void draw(Graphics2D g) {
        gp.getObjects().forEach(obj -> this.drawObject(obj, g));

    }

    private void drawObject(SuperObject obj, Graphics2D g) {
        int screenX = obj.getWorldX() - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();
        int screenY = obj.getWorldY() - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();

        if (screenX > obj.getWorldX()) {
            screenX = obj.getWorldX();
        }
        if (screenY > obj.getWorldY()) {
            screenY = obj.getWorldY();
        }


        //Faz com que a imagem so seja carregada se estiver na tela
        if (
                obj.getWorldX() + gp.getTileSize() > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX() //Esquerda
                        && obj.getWorldX() - gp.getTileSize() < gp.getPlayer().getWorldX() + ( gp.getScreenWidth() - gp.getPlayer().getScreenX()) // Direita
                        && obj.getWorldY() + gp.getTileSize() > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY() // Cima
                        && obj.getWorldY() - gp.getTileSize() < gp.getPlayer().getWorldY() + ( gp.getScreenHeight() - gp.getPlayer().getScreenY()) // Baixo
        ) {
            g.drawImage(obj.getImage(), screenX, screenY, obj.getImage().getWidth() * gp.getScale(), obj.getImage().getHeight() * gp.getScale(), null);
            g.setColor(Color.red);
            g.drawRect(screenX, screenY, 2, 2);
        }
    }

    private boolean checkIntBetween(int value, int number1, int number2) {

        if (value >= number1 && value <= number2) {
            return true;
        }
        return false;
    }

    public Optional<SuperObject> findObj(int col, int row) {

         Optional<SuperObject> object = gp.getObjects().stream().filter(obj ->
                 (obj.getWorldX()/gp.getTileSize()) == col
                 && (obj.getWorldY()/gp.getTileSize()) == row
         ).findFirst();

         return object;
    }

}
