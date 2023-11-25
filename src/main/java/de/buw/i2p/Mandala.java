package de.buw.i2p;

import javafx.scene.canvas.GraphicsContext;
import java.awt.*;

//die Mandalaklasse wird definiert
public class Mandala {

    private Composite shapes;
//besitzt ein Composite als Attribut
    public Mandala(){
        shapes = new Composite();
    }
//in dieser Funktion wird das Mandala generiert indem das Composite erstellt wird und entsprechend Objekte hinzugefügt werden
    public  void generate(GraphicsContext gc_canvas, Graphics2D gc_buffer, int num_segments){
        int num = (int)(4* Math.random() + 1);
        for (int i = 0; i < num; i++){
            Composite c = new Composite();
            c.generate(num_segments, "Kreis");
            shapes.add(c);
        }//zum Schluss wird das Composite gezeichnet(auch in die Datei)
        shapes.print(gc_canvas, gc_buffer);
    }

}
