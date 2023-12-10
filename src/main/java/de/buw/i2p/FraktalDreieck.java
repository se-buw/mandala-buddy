package de.buw.i2p;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.canvas.*;
import java.awt.*;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class FraktalDreieck extends Composite{
    private Vector2D center;
    private double radius; //Distanz von der Mitte zu jeder Ecke
    private double rotation;
    private boolean transparent;
    private Vector2D corner0;
    private Vector2D corner1;
    private Vector2D corner2;
    private Color canvas_color; //Linien Farbe des Kreises für den Canvas
    private java.awt.Color buffer_color;    //Linien Farbe des Kreises für das zu speichernde Bild
    private int recursionDepth;     //wie oft sich das Muster wiederholt


    public FraktalDreieck(Vector2D center, double radius, boolean transparent, int recursionDepth, double rotation){
        this.center =  center;
        this.radius = radius;
        this.transparent = transparent;
        this.recursionDepth = recursionDepth;
        this.rotation = rotation;
        canvas_color = Color.BLACK;
        buffer_color = java.awt.Color.BLACK;
        //Ecken werden initialisert(Gleichschenkliges Dreieck mit Winkeln 90° + rotation, 210° + rotation und 330° + rotation)
        //todo mit rotation rumprobieren um dreiecke i  verschiedene richtungen zeigen zu lassen, vor rotation random int zwischen 0 und 1
        corner0 = new Vector2D(center.getX() + radius * Math.sin(Math.PI/2 + rotation), center.getY() + radius * Math.cos(Math.PI/2 + rotation));
        corner1 = new Vector2D(center.getX() + radius * Math.sin(7 * Math.PI/6 + rotation), center.getY() + radius * Math.cos(7 * Math.PI/6 + rotation ));
        corner2 = new Vector2D(center.getX() + radius * Math.sin(11 * Math.PI/6 + rotation), center.getY() + radius * Math.cos(11 * Math.PI/6 + rotation));
    }

    public FraktalDreieck(Vector2D corner0, Vector2D corner1, Vector2D corner2, boolean transparent, int recursionDepth){
        this.corner0 = corner0;
        this.corner1 = corner1;
        this.corner2 = corner2;
        this.transparent = transparent;
        this.recursionDepth = recursionDepth;
    }

    //das Fraktal wird auf dem GraphicsContext für den Canvas gezeichnet
    public void print(GraphicsContext picture){
        double[] x_points = {corner0.getX(), corner1.getX(), corner2.getX()};
        double[] y_points = {corner0.getY(), corner1.getY(), corner2.getY()};


        if(!transparent){
            picture.setFill(Color.WHITE);
            picture.fillPolygon(x_points, y_points, 3);
        }

        //Umrandung wird gezeichnet
        picture.setStroke(canvas_color);
        picture.strokePolygon(x_points, y_points, 3);

        if(recursionDepth > 0){
            FraktalDreieck sub_fractal0 = new FraktalDreieck(corner0, corner0.add(0.5, corner1.subtract(corner0)), corner0.add(0.5, corner2.subtract(corner0)), transparent, recursionDepth - 1);
            FraktalDreieck sub_fractal1 = new FraktalDreieck(corner1.add(0.5, corner0.subtract(corner1)), corner1, corner1.add(0.5, corner2.subtract(corner1)), transparent, recursionDepth - 1);
            FraktalDreieck sub_fractal2 = new FraktalDreieck(corner2.add(0.5, corner0.subtract(corner2)), corner2.add(0.5, corner1.subtract(corner2)), corner2, transparent, recursionDepth - 1);

            sub_fractal0.print(picture);
            sub_fractal1.print(picture);
            sub_fractal2.print(picture);
        }



    }

    //das Fraktal wird auf dem GraphicsContext, der später gespeichert wird, gezeichnet
    public void save(Graphics2D gc_buffer){

    }
}


