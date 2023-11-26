
package de.buw.i2p;


import javafx.scene.paint.Color;
import javafx.scene.canvas.*;
import java.awt.*;

//Definition der Kreisklasse
public class Kreis extends Composite {

    private float radius;       //radius des Kreises
    private float center_x;     //x-Koordinate des Mittelpunkt des Kreises
    private float center_y;     //y-Koordinate des Mittelpunkt des Kreises
    private Color canvas_color; //Linien Farbe des Kreises für den Canvas
    private java.awt.Color buffer_color;    //Linien Farbe des Kreises für das zu speichernde Bild
    private boolean transparent;    //true: nur Umrandung, false: gefüllter Kreis


    public Kreis(float center_x, float center_y, float radius, boolean transparent){
        this.center_x = center_x;
        this.center_y = center_y;
        this.radius = radius;
        this.transparent = transparent;
        canvas_color = Color.BLACK;
        buffer_color = java.awt.Color.BLACK;
    }

    //der Kreis wird auf dem GraphicsContext für den Canvas gezeichnet
    public void print(GraphicsContext gc_canvas){
        if(!transparent){ //falls der Kreis nicht transparent ist, wird der Kreis zuerst gefüllt
            gc_canvas.setFill(Color.WHITE);
            gc_canvas.fillOval(center_x - radius, center_y - radius, 2*radius, 2*radius);
        }
        //danach wird die Umrandung gezeichnet
        gc_canvas.setStroke(canvas_color);
        gc_canvas.strokeOval(center_x - radius, center_y - radius, 2*radius, 2*radius);



    }

    //der Kreis wird auf dem GraphicsContext, der später gespeichert wird, gezeichnet
    public void save(Graphics2D gc_buffer){
        if(!transparent){//falls der Kreis nicht transparent ist, wird der Kreis zuerst gefüllt
            gc_buffer.setColor(java.awt.Color.WHITE);
            gc_buffer.fillOval((int)center_x - (int)radius, (int)center_y - (int)radius, 2*(int)radius, 2*(int)radius);
        }
        //danach wird die Umrandung gezeichnet
        gc_buffer.setColor(buffer_color);
        gc_buffer.drawOval((int)center_x - (int)radius, (int)center_y - (int)radius, 2*(int)radius, 2*(int)radius);

    }

}

