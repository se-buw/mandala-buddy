
package de.buw.i2p;

import javafx.scene.canvas.GraphicsContext;


import javafx.scene.paint.*;
import javafx.scene.paint.Color;

import java.awt.*;


public class Quadrat extends Composite {
    private float center_x;     ///x-Koordinate des Mittelpunkt des Quadrats
    private float center_y;     //y-Koordinate des Mittelpunkt des Quadrats
    private float radius;       //Abstand vom Mittelpunkt des Rechtecks zu einer Ecke
    private Color color;        //Linien Farbe des Quadrats für den Canvas
    private java.awt.Color buffer_color;    //Linien Farbe des Quadrats für das zu speichernde Bild
    private boolean transparent;    //true: nur Umrandung, false: gefülltes Quadrat


    public Quadrat(float corner_x, float corner_y, float radius, boolean transparent){
        this.center_x = corner_x;
        this.center_y = corner_y;
        this.radius = radius;
        this.transparent = transparent;
        this.color = Color.BLACK;
        buffer_color = java.awt.Color.BLACK;
    }

    //das Quadrat wird auf dem GraphicsContext für den Canvas gezeichnet
    public void print(GraphicsContext picture){
        float length = 2 * radius * (float)Math.sin(Math.PI/4.0);

        if(!transparent){//falls das Quadrat nicht transparent ist, wird das Quadrat zuerst gefüllt
            picture.setFill(Color.WHITE);
            picture.fillRect(center_x - (length/2), center_y - (length/2), length, length);
        }
        //danach wird die Umrandung gezeichnet
        picture.setStroke(color);
        picture.strokeRect(center_x - (length/2), center_y - (length/2), length, length);


    }

    //das Quadrat wird auf dem GraphicsContext, der später gespeichert wird, gezeichnet
    public void save(Graphics2D gc_buffer){
        float length = 2 * radius * (float)Math.sin(Math.PI/4.0);

        if(!transparent){ //falls das Quadrat nicht transparent ist, wird das Quadrat zuerst gefüllt
            gc_buffer.setColor(java.awt.Color.WHITE);
            gc_buffer.fillRect((int)(center_x - (length/2)), (int)(center_y - (length/2)), (int)length, (int)length);
        }
        //danach wird die Umrandung gezeichnet
        gc_buffer.setColor(buffer_color);
        gc_buffer.drawRect((int)(center_x - (length/2)), (int)(center_y - (length/2)), (int)length, (int)length);

    }


}