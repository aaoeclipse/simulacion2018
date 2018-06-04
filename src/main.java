import DB.SQL;
import DB.SQLimpl;
import GUI.Main;
import utils.Controller;
import utils.Manager;

import java.util.Random;

public class main {
    static String[] firstName = {"Ger", "Ser", "Bren", "Al", "San", "Mar", "Sal", "Fer", "Luis", "Rord", "Mel", "Jos", "Jor", "Pedr", "Carl"};
    static String[] lastName = {"ardo", "ena", "don", "ejandra", "tiago", "iana", "mon", "nando", "fer", "igo", "anie", "eph", "alita", "los", "tin"};

    public static void main (String[] args) {
        Main maingui = new Main();
        Manager manager = new Manager();
        Controller controller = new Controller(maingui, manager);
        maingui.addController(controller);
    }
}
