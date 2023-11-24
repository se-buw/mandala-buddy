package de.buw.i2p;

import javafx.scene.canvas.GraphicsContext;

import java.util.Vector;

public class Mandala {

    private Composite shapes;


    public Mandala(){
        shapes = new Composite();
    }

    public  void generate(GraphicsContext picture, int num_segments){
        int num = (int)(4* Math.random() + 1);
        for (int i = 0; i < num; i++){
            Composite c = new Composite();
            c.generate(num_segments, 600.0f,350.0f, 350.0f, "Kreis");
            shapes.add(c);
        }
        shapes.print(picture);

    }
    //public  void print(){}
}
