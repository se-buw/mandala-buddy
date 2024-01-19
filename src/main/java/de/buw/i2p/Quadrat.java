
package de.buw.i2p;

import javafx.scene.canvas.GraphicsContext;


import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.awt.*;


public class Quadrat extends Composite {
    private Vector2D center;
    private Vector2D leftup;
    private Vector2D rightup;
    private Vector2D rightdown;
    private Vector2D leftdown;
    private double radius;       //Abstand vom Mittelpunkt des Rechtecks zu einer Ecke
    private double rotation;
    private Color color;        //Linien Farbe des Quadrats für den Canvas
    private java.awt.Color buffer_color;    //Linien Farbe des Quadrats für das zu speichernde Bild
    private Color canvas_background;
    private java.awt.Color buffer_background;
    private boolean transparent;    //true: nur Umrandung, false: gefülltes Quadrat


    public Quadrat(Vector2D center, double radius, boolean transparent, double rotationin, Color canvas_background, java.awt.Color buffer_background){
        this.center = center;
        this.radius = radius;
        this.rotation = rotationin;
        this.transparent = transparent;
        this.canvas_background = canvas_background;
        this.buffer_background = buffer_background;
        this.color = Color.BLACK;
        buffer_color = java.awt.Color.BLACK;
        leftdown = new Vector2D(center.getX() + radius * Math.cos(Math.PI/4 + Math.PI/2 + rotation), center.getY() + radius * Math.sin(Math.PI/4 + Math.PI/2 + rotation));
        rightdown = new Vector2D(center.getX() + radius * Math.cos(Math.PI/4 + rotation), center.getY() + radius * Math.sin(Math.PI/4 + rotation ));
        rightup = new Vector2D(center.getX() + radius * Math.cos(Math.PI/4 + 3 * Math.PI/2  + rotation), center.getY() + radius * Math.sin(Math.PI/4 + 3 * Math.PI/2 + rotation));
        leftup = new Vector2D(center.getX() + radius * Math.cos(Math.PI/4 + 2 * Math.PI/2  + rotation), center.getY() + radius * Math.sin(Math.PI/4 + 2 * Math.PI/2 + rotation));
    }

    //das Quadrat wird auf dem GraphicsContext für den Canvas gezeichnet
    public void print(GraphicsContext picture){
        double length = 2 * radius * (double)Math.sin(Math.PI/4.0);
        double[] x_points = {rightup.getX(), leftup.getX(), leftdown.getX(), rightdown.getX()};
        double[] y_points = {rightup.getY(), leftup.getY(), leftdown.getY(), rightdown.getY()};

        if(!transparent){//falls das Quadrat nicht transparent ist, wird das Quadrat zuerst gefüllt
            picture.setFill(canvas_background);
            picture.fillPolygon(x_points, y_points,4);
        }
        //danach wird die Umrandung gezeichnet
        picture.setStroke(color);
        picture.strokePolygon(x_points, y_points, 4);


    }

    //das Quadrat wird auf dem GraphicsContext, der später gespeichert wird, gezeichnet
    public void save(Graphics2D gc_buffer){
        double length = 2 * radius * (double)Math.sin(Math.PI/4.0);
        int[] x_points = {(int)rightup.getX(), (int)leftup.getX(), (int)leftdown.getX(), (int)rightdown.getX()};
        int[] y_points = {(int)rightup.getY(), (int)leftup.getY(), (int)leftdown.getY(), (int)rightdown.getY()};

        if(!transparent){ //falls das Quadrat nicht transparent ist, wird das Quadrat zuerst gefüllt
            gc_buffer.setColor(buffer_background);
            gc_buffer.fillPolygon(x_points, y_points, 4);
        }
        //danach wird die Umrandung gezeichnet
        gc_buffer.setColor(buffer_color);
        gc_buffer.drawPolygon(x_points, y_points,4);

    }


}