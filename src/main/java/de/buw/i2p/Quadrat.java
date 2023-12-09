
package de.buw.i2p;

import javafx.scene.canvas.GraphicsContext;


import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.awt.*;


public class Quadrat extends Composite {
    private Vector2D center;
    private double radius;       //Abstand vom Mittelpunkt des Rechtecks zu einer Ecke
    private Color color;        //Linien Farbe des Quadrats für den Canvas
    private java.awt.Color buffer_color;    //Linien Farbe des Quadrats für das zu speichernde Bild
    private boolean transparent;    //true: nur Umrandung, false: gefülltes Quadrat


    public Quadrat(Vector2D center, double radius, boolean transparent){
        this.center = center;
        this.radius = radius;
        this.transparent = transparent;
        this.color = Color.BLACK;
        buffer_color = java.awt.Color.BLACK;
    }

    //das Quadrat wird auf dem GraphicsContext für den Canvas gezeichnet
    public void print(GraphicsContext picture){
        double length = 2 * radius * (double)Math.sin(Math.PI/4.0);

        if(!transparent){//falls das Quadrat nicht transparent ist, wird das Quadrat zuerst gefüllt
            picture.setFill(Color.WHITE);
            picture.fillRect(center.getX() - (length/2), center.getY() - (length/2), length, length);
        }
        //danach wird die Umrandung gezeichnet
        picture.setStroke(color);
        picture.strokeRect(center.getX() - (length/2), center.getY() - (length/2), length, length);


    }

    //das Quadrat wird auf dem GraphicsContext, der später gespeichert wird, gezeichnet
    public void save(Graphics2D gc_buffer){
        double length = 2 * radius * (double)Math.sin(Math.PI/4.0);

        if(!transparent){ //falls das Quadrat nicht transparent ist, wird das Quadrat zuerst gefüllt
            gc_buffer.setColor(java.awt.Color.WHITE);
            gc_buffer.fillRect((int)(center.getX() - (length/2)), (int)(center.getY() - (length/2)), (int)length, (int)length);
        }
        //danach wird die Umrandung gezeichnet
        gc_buffer.setColor(buffer_color);
        gc_buffer.drawRect((int)(center.getX() - (length/2)), (int)(center.getY() - (length/2)), (int)length, (int)length);

    }


}