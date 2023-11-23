
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
    public Kreis(float center_x_, float center_y_, float radius_, boolean transparent){
        center_x = center_x_;
        center_y = center_y_;
        radius = radius_;
        transparent = transparent;
        canvas_color = Color.BLACK;
        buffer_color = java.awt.Color.BLACK;
    }//ein Kreis besitzt einen Mittelpunkt, Radius, sowie eine Farbe(eine für canvas, eine für buffer)

    //in dieser Funktion wird die Printfunktion gezeichnet
    public void print(GraphicsContext gc_canvas, Graphics2D gc_buffer){
        //es werden die Farben gesetzt
        gc_canvas.setStroke(canvas_color);
        gc_canvas.setFill(Color.WHITE);
        gc_buffer.setColor(java.awt.Color.WHITE);
        if(!transparent){
            gc_canvas.fillOval(center_x - radius, center_y - radius, 2*radius, 2*radius);
            gc_buffer.fillOval((int)center_x - (int)radius, (int)center_y - (int)radius, 2*(int)radius, 2*(int)radius);
        }
        //die Kreise werden gezeichnet
        gc_buffer.setColor(buffer_color);
        gc_canvas.strokeOval(center_x - radius, center_y - radius, 2*radius, 2*radius);
        gc_buffer.drawOval((int)center_x - (int)radius, (int)center_y - (int)radius, 2*(int)radius, 2*(int)radius);
    }
    public void rotate(){
    }
}

