package utils;

import GUI.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    Main gui;
    Manager manager;

    public Controller(Main gui, Manager manager){
        this.gui = gui;
        this.manager = manager;
    }

    public void generate(){
        manager.generate();
    }
    public void CSVGenerator(){
        manager.csvGenerate();
    }


}
