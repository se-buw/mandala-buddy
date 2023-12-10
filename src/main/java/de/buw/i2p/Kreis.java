
package de.buw.i2p;


import javafx.scene.paint.Color;
import javafx.scene.canvas.*;
import java.awt.*;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;


//Definition der Kreisklasse
public class Kreis extends Composite {

    private double radius;       //radius des Kreises
    private Vector2D center;
    private Color canvas_color; //Linien Farbe des Kreises für den Canvas
    private java.awt.Color buffer_color;    //Linien Farbe des Kreises für das zu speichernde Bild
    private Color canvas_background;
    private java.awt.Color buffer_background;
    private boolean transparent;    //true: nur Umrandung, false: gefüllter Kreis


    public Kreis(Vector2D center, double radius, boolean transparent, Color canvas_background, java.awt.Color buffer_background){
        this.center = center;
        this.radius = radius;
        this.transparent = transparent;
        this.canvas_background = canvas_background;
        this.buffer_background = buffer_background;
        canvas_color = Color.BLACK;
        buffer_color = java.awt.Color.BLACK;
    }

    //der Kreis wird auf dem GraphicsContext für den Canvas gezeichnet
    public void print(GraphicsContext gc_canvas){
        if(!transparent){ //falls der Kreis nicht transparent ist, wird der Kreis zuerst gefüllt
            gc_canvas.setFill(canvas_background);
            gc_canvas.fillOval(center.getX() - radius, center.getY() - radius, 2*radius, 2*radius);
        }
        //danach wird die Umrandung gezeichnet
        gc_canvas.setStroke(canvas_color);
        gc_canvas.strokeOval(center.getX() - radius, center.getY() - radius, 2*radius, 2*radius);



    }

    //der Kreis wird auf dem GraphicsContext, der später gespeichert wird, gezeichnet
    public void save(Graphics2D gc_buffer){
        if(!transparent){//falls der Kreis nicht transparent ist, wird der Kreis zuerst gefüllt
            gc_buffer.setColor(buffer_background);
            gc_buffer.fillOval((int)center.getX() - (int)radius, (int)center.getY() - (int)radius, 2*(int)radius, 2*(int)radius);
        }
        //danach wird die Umrandung gezeichnet
        gc_buffer.setColor(buffer_color);
        gc_buffer.drawOval((int)center.getX() - (int)radius, (int)center.getY() - (int)radius, 2*(int)radius, 2*(int)radius);

    }

}

