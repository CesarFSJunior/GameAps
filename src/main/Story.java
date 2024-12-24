package main;

import objects.Muda;
import objects.PainelSolar;
import objects.SuperObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;

public class Story {

    private GamePanel gp;
    private int gamePercentage = 0;
    private int storyTime = 0;
    private Optional<SuperObject> firstMissionObject;
    private ArrayList<SuperObject> firstMissionGoal = new ArrayList<>();
    private ArrayList<SuperObject> gaiaMission = new ArrayList<>();
    private ArrayList<SuperObject> CEOMission = new ArrayList<>();

    public Story(GamePanel gp) {
        this.gp = gp;
        Muda muda = new Muda(this.gp);
        Muda muda2 = new Muda(this.gp);
        Muda muda3 = new Muda(this.gp);
        this.gaiaMission.add(muda);
        this.gaiaMission.add(muda2);
        this.gaiaMission.add(muda3);

        PainelSolar ps = new PainelSolar(this.gp);
        PainelSolar ps2 = new PainelSolar(this.gp);
        PainelSolar ps3 = new PainelSolar(this.gp);
        this.CEOMission.add(ps);
        this.CEOMission.add(ps2);
        this.CEOMission.add(ps3);
    }

    public void eventTriger(SuperObject triger) {
        ArrayList<String> falas = new ArrayList<>();

        if (triger.getName().equals("Super Computador") && this.storyTime >= 1) {
            falas.add("Os Niveis de poluição atuais estão em " + (100 - gamePercentage) + "%");
        }

        if (triger.getName().equals("Prefeito Silva") && this.storyTime == 0) {
            falas.addAll(this.primeiroDialogoMajorSilva());
            this.storyTime ++;
        } else if (triger.getName().equals("Prefeito Silva") && this.verifyFirstMissionProgress() && this.storyTime == 1) {
            falas.addAll(segundoDialogoMajorSilva());
            this.gamePercentage += 25;
            this.storyTime ++;
        } else if (triger.getName().equals("Super Computador") && this.storyTime == 1) {
            falas.addAll(primeiroDialogoSuperComputador());
        } else if (triger.getName().equals("Chefe centro de reciclagem") && this.storyTime == 2) {
            falas.addAll(primeiroDialogoCentroDeReciclagem());
        } else if (triger.getName().equals("Maquina Reciclagem") && this.storyTime == 3) {
            falas.addAll(dialogoMaquinaReciclagem());
        } else if (triger.getName().equals("Chefe centro de reciclagem") && this.storyTime == 4) {
            falas.addAll(segundoDialogoChefeCentroReciclagem());
        } else if (triger.getName().equals("Gaia") && this.storyTime == 5) {
            falas.addAll(primeiroDialogoGaia());
        } else if (triger.getName().equals("Gaia") && this.storyTime == 6) {
            falas.addAll(segundoDialogoGaia());
        } else if (triger.getName().equals("CEO Companhia Energia") && this.storyTime == 7) {
            falas.addAll(primeiroDialogoCEO());
        } else if (triger.getName().equals("CEO Companhia Energia") && this.storyTime == 8) {
            falas.addAll(segundoDialogoCEO());
        } else if (triger.getName().equals("Super Computador") && this.storyTime == 9) {
            falas.addAll(endGameSuperComputador());
        }

        this.gp.getDialogPannel().receiveDialog(falas);
    }

    private ArrayList<String> primeiroDialogoMajorSilva() {
        ArrayList<String> falas = new ArrayList<>();
        falas.add("Vamos analisar os indicadores ambientais.");
        falas.add("Você pode ver os níveis de poluição no painel de controle!");
        falas.add("Vamos começar com um projeto de reciclagem?");

        return falas;
    }

    private ArrayList<String> segundoDialogoMajorSilva() {
        ArrayList<String> falas = new ArrayList<>();
        falas.add("Incrivel você recolheu todo o lixo da praia");
        falas.add("Agora leve o lixo para o centro de reciclagem!");

        return falas;
    }

    private ArrayList<String> primeiroDialogoSuperComputador() {

        ArrayList<String> falas = new ArrayList<>();
        falas.add("Você deve recolher o lixo na praia para reduzir esse nivel");
        falas.add("Utilize essa vasoura para recolher o lixo");
        falas.add("Fale com o Major Silva quando terminar");

        if (!this.firstMissionObject.isEmpty()) {
            this.gp.getPlayer().addItemToInventory(this.firstMissionObject.get());
            this.firstMissionObject = Optional.empty();
        }
        return falas;
    }

    public ArrayList<String> primeiroDialogoCentroDeReciclagem() {

        ArrayList<String> falas = new ArrayList<>();
        falas.add("Boa tarde Sou o Chefe do centro de reciclagem");
        falas.add("Vejo que possui alguns itens para a reciclagem");
        falas.add("interaja com a máquina ao lado para reciclar os itens");
        falas.add("Ela ira separar os itens e enviá-los para a reciclagem");
        this.storyTime ++;
        return falas;

    }

    public ArrayList<String> dialogoMaquinaReciclagem() {

        ArrayList<String> falas = new ArrayList<>();
        falas.add("Parabéns você conseguiu ajudar o meio ambiente");
        falas.add("Todo o lixo que você trouxe foi reciclado");
        falas.add("Fale com o chefe do centro de reciclagem novamente");

        gp.getPlayer().getInventory().removeAll(firstMissionGoal);
        this.storyTime ++;
        this.gamePercentage += 25;
        return falas;

    }

    public ArrayList<String> segundoDialogoChefeCentroReciclagem() {

        ArrayList<String> falas = new ArrayList<>();
        falas.add("Muito obrigado por ajudar o planeta!");
        falas.add("Fiquei sabendo que estão procurando por ajuda!");
        falas.add("Procure pela Gaia na estufa!");
        this.storyTime ++;
        return falas;

    }

    public ArrayList<String> primeiroDialogoGaia() {

        ArrayList<String> falas = new ArrayList<>();
        falas.add("Boa tarde eu sou a Gaia");
        falas.add("Poderia me ajudar a plantar essas mudas de árvore?");
        falas.add("Você só precisa utilizalas no seu inventario");
        falas.add("Lembre-se que devem ficar na grama");
        this.storyTime ++;

        gp.getPlayer().getInventory().addAll(this.gaiaMission);

        return falas;

    }

    public ArrayList<String> segundoDialogoGaia() {
        ArrayList<String> falas = new ArrayList<>();
        for (SuperObject obj : this.gaiaMission) {
            if (gp.getPlayer().getInventory().indexOf(obj) != -1) {
                return falas;
            }
        }

        falas.add("Obrigado por plantar essas mudas");
        falas.add("Você acaba de ajudar o meio ambiente");
        falas.add("Fale com o presidente da compania de energia");
        falas.add("Fiquei sabendo que ele esta buscando ajuda");

        this.storyTime ++;
        this.gamePercentage += 25;

        return falas;

    }

    public ArrayList<String> primeiroDialogoCEO() {

        ArrayList<String> falas = new ArrayList<>();
        falas.add("Boa tarde sou o CEO da companhia de energia");
        falas.add("Estou buscando novos métodos de geração de energia");
        falas.add("Estamos testando painéis solares");
        falas.add("Queremos utiliza-los pois são mais sustentaveis");
        falas.add("Pode instala-los para mim?");
        this.storyTime ++;

        gp.getPlayer().getInventory().addAll(this.CEOMission);

        return falas;

    }

    public ArrayList<String> segundoDialogoCEO() {
        ArrayList<String> falas = new ArrayList<>();
        for (SuperObject obj : this.CEOMission) {
            if (gp.getPlayer().getInventory().indexOf(obj) != -1) {
                return falas;
            }
        }

        falas.add("Obrigado por instalar esses paineis");
        falas.add("Você acaba de implementar uma fonte sustentavel");
        falas.add("Fale com o supercomputador e veja o nível de poluição");

        this.storyTime ++;
        this.gamePercentage += 25;

        return falas;

    }

    public ArrayList<String> endGameSuperComputador() {
        ArrayList<String> falas = new ArrayList<>();

        falas.add("Parabéns você reduziu os indicadores e ajudou o planeta");

        return falas;

    }

    private boolean verifyFirstMissionProgress() {

        for (SuperObject obj : this.firstMissionGoal) {
            if (this.gp.getPlayer().getInventory().indexOf(obj) == -1) {
                return false;
            }
        }
        return true;
    }

    public void setFirstMissionObject(SuperObject firstMissionObject) {
        this.firstMissionObject = Optional.of(firstMissionObject);
    }

    public void addFirstMissionGoal(SuperObject object) {
        this.firstMissionGoal.add(object);
    }

    public void setGaiaMission(SuperObject gaiaMission) {
        this.gaiaMission.add(gaiaMission);
    }
}
