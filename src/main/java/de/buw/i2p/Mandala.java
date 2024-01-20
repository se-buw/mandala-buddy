package de.buw.i2p;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.awt.*;

//die Mandalaklasse wird definiert
public class Mandala {
    protected Composite shapes;   //Composite in dem die Formen des Mandalas gespeichert sind
    protected int num_segments;
    protected String shape;
    protected int pixelSize;
    protected int pixelSizeSave;

    public Mandala(){
        shapes = new Composite();
    }
    //in dieser Funktion wird das Mandala generiert indem das Composite erzeugt wird und wiederum
    public  void generate(GraphicsContext gc_canvas, int num_segments_in, String shape_in, Color background_color, int pixelSize_in, int pixelSizeSave_in){
    	pixelSizeSave = pixelSizeSave_in;
    	pixelSize = pixelSize_in;
        java.awt.Color buffer_background = new java.awt.Color((int)(background_color.getRed() * 255), (int)(background_color.getGreen() * 255), (int)(background_color.getBlue() * 255));   //convert to java.awt.Color
        Composite c = new Composite(new Vector2D(pixelSize/2, pixelSize/2), pixelSize * 0.9f, background_color, buffer_background);    //ein neues Composite erzeugt und durch generate mit zufälligen Formen gefüllt
        num_segments = num_segments_in;
        shape = shape_in;
        c.generate(num_segments,  shape);
        shapes = c;     //dieses Composite wird zum Composite des MAndalas
        shapes.print(gc_canvas);    //das Composite wird auf dem GraphicContext gezeichnet
    }

    //das Mandala wird auf dem GraphicContext, der später gespeichert werden kann, gezeichnet
    public void save(Graphics2D gc_buffer){
    	// Erhöhe die Auflösung und generiere die Positionen der Shapes neu
    	shapes.setCenter(new Vector2D(pixelSizeSave/2, pixelSizeSave/2));
    	shapes.setDiameter(pixelSizeSave * 0.9f);
    	
    	shapes.deleteComp_containerContents();
        shapes.generate(num_segments, shape);
        shapes.save(gc_buffer);
    }

    //alle Elemente werden aus dem Composite des Mandalas gelöscht
    public void clear(){
        shapes.clear();
    }

}
