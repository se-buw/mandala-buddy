package de.buw.i2p;

import javafx.scene.canvas.GraphicsContext;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.awt.*;

//die Mandalaklasse wird definiert
public class Mandala {
    private Composite shapes;   //Composite in dem die Formen des Mandalas gespeichert sind

    public Mandala(){
        shapes = new Composite();
    }
    //in dieser Funktion wird das Mandala generiert indem das Composite erzeugt wird und wiederum
    public  void generate(GraphicsContext gc_canvas, int num_segments, String shape){
        Composite c = new Composite(new Vector2D(350.0, 350.0), 600.0);    //ein neues Composite erzeugt und durch generate mit zufälligen Formen gefüllt
        c.generate(num_segments,  shape);
        shapes = c;     //dieses Composite wird zum Composite des MAndalas
        shapes.print(gc_canvas);    //das Composite wird auf dem GraphicContext gezeichnet
    }

    //das Mandala wird auf dem GraphicContext, der später gespeichert werden kann, gezeichnet
    public void save(Graphics2D gc_buffer){
        shapes.save(gc_buffer);
    }

    //alle Elemente werden aus dem Composite des Mandalas gelöscht
    public void clear(){
        shapes.clear();
    }

}
