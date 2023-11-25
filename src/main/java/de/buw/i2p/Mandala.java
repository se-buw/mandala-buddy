package de.buw.i2p;

import javafx.scene.canvas.GraphicsContext;

import java.util.Vector;

public class Mandala {

    private Composite shapes;


    public Mandala(){
        shapes = new Composite();
    }

    public  void generate(GraphicsContext picture, int num_segments){
        Composite c = new Composite(600.0f, 350.0f, 350.0f);
        c.generate(num_segments,  "Kreis");
        shapes.add(c);
        shapes.print(picture);

    }
}
