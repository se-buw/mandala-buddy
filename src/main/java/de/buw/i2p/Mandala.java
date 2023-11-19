package de.buw.i2p;

import javafx.scene.canvas.GraphicsContext;

import java.util.Vector;

public class Mandala {

    private Composite shapes;


    public Mandala(){
        shapes = new Composite();
    }
    public  void print(){};
    public  void generate(GraphicsContext picture, int num_segments){
        System.out.println("Mandala generate");
        int num = (int)(4* Math.random() + 1);
        //Composite[] comp= new Composite[num];
        Composite c = new Composite();
        for (int i = 0; i < num; i++){
            //comp[i] = new Composite();
            //comp[i].generate(num_segments, "Kreis");
            c.generate(num_segments, "Kreis");
            shapes.add(c);
        }
        System.out.println("Composites erstellt");
        shapes.print(picture);



    };

}
