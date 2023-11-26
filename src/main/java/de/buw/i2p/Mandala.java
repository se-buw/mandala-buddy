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
    public  void generate(GraphicsContext gc_canvas/*,Graphics2D gc_buffer*/,  int num_segments){
        Composite c = new Composite(600.0f, 350.0f, 350.0f);
        c.generate(num_segments,  "Kreis");
        shapes.add(c);
        shapes.print(gc_canvas);//zum Schluss wird das Composite gezeichnet(auch in die Datei)
    }

    public void save(Graphics2D gc_buffer){
        shapes.save(gc_buffer);
    }

    public void clear(){
        shapes.clear();
    }

}
