package de.buw.i2p;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;

import java.util.Vector;

public class EventHandler_Object {
    private String ausgelesenes_objekt;
    public void auslesen(ChoiceBox<String> choicebox){
        choicebox.setOnAction(event -> {
            ausgelesenes_objekt = choicebox.getValue();
        });
    }

    public String wert(){
     return ausgelesenes_objekt;
    }
}