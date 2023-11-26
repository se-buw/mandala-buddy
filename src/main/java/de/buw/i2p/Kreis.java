
package de.buw.i2p;


import javafx.scene.paint.Color;
import javafx.scene.canvas.*;
import java.awt.*;

//Definition der Kreisklasse
public class Kreis extends Composite {

    private float radius;
    private float center_x;
    private float center_y;
    private Color canvas_color;
    private java.awt.Color buffer_color;
    private boolean transparent;

//die Kreisklasse wird definiert
    public Kreis(float center_x, float center_y, float radius, boolean transparent){
        this.center_x = center_x;
        this.center_y = center_y;
        this.radius = radius;
        this.transparent = transparent;
        canvas_color = Color.BLACK;
        buffer_color = java.awt.Color.BLACK;
    }//ein Kreis besitzt einen Mittelpunkt, Radius, sowie eine Farbe(eine für canvas, eine für buffer)

    //in dieser Funktion wird die Printfunktion gezeichnet

    public void print(GraphicsContext gc_canvas){
        //es werden die Farben gesetzt
        if(!transparent){
            gc_canvas.setFill(Color.WHITE);
            gc_canvas.fillOval(center_x - radius, center_y - radius, 2*radius, 2*radius);
        }
        //die Kreise werden gezeichnet
        gc_canvas.setStroke(canvas_color);
        gc_canvas.strokeOval(center_x - radius, center_y - radius, 2*radius, 2*radius);



    }

    public void save(Graphics2D gc_buffer){
        //es werden die Farben gesetzt
        //gc_buffer.setColor(buffer_color);
        if(!transparent){
            gc_buffer.setColor(java.awt.Color.WHITE);
            gc_buffer.fillOval((int)center_x - (int)radius, (int)center_y - (int)radius, 2*(int)radius, 2*(int)radius);
        }
        //die Kreise werden gezeichnet
        gc_buffer.setColor(buffer_color);
        gc_buffer.drawOval((int)center_x - (int)radius, (int)center_y - (int)radius, 2*(int)radius, 2*(int)radius);

    }

    public void rotate(){
    }
}

