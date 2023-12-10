package de.buw.i2p;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.canvas.*;
import java.awt.*;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class FraktalDreieck extends Composite{
    private Vector2D centerPoint;
    private double radius; //Distanz von der Mitte zu jeder Ecke
    private double rotation;
    private boolean transparent;
    private Vector2D corner0;
    private Vector2D corner1;
    private Vector2D corner2;
    private Color canvas_color; //Linien Farbe des Kreises für den Canvas
    private java.awt.Color buffer_color;    //Linien Farbe des Kreises für das zu speichernde Bild
    private int recursionDepth;     //wie oft sich das Muster wiederholt


    public FraktalDreieck(Vector2D centerPoint, double radius, boolean transparent, int recursionDepth, double rotation){
        this.centerPoint =  centerPoint;
        this.radius = radius;
        this.transparent = transparent;
        this.recursionDepth = recursionDepth;
        this.rotation = rotation;
        canvas_color = Color.BLACK;
        buffer_color = java.awt.Color.BLACK;
        //Ecken werden initialisert(Gleichschenkliges Dreieck mit Winkeln 90° + rotation, 210° + rotation und 330° + rotation)
        //todo mit rotation rumprobieren um dreiecke i  verschiedene richtungen zeigen zu lassen
        corner0 = new Vector2D(centerPoint.getX() + radius * Math.sin(Math.PI/2 ), centerPoint.getY() + radius * Math.cos(Math.PI/2 ));
        corner1 = new Vector2D(centerPoint.getX() + radius * Math.sin(7 * Math.PI/6 ), centerPoint.getY() + radius * Math.cos(7 * Math.PI/6 ));
        corner2 = new Vector2D(centerPoint.getX() + radius * Math.sin(11 * Math.PI/6 ), centerPoint.getY() + radius * Math.cos(11 * Math.PI/6 ));
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

        //todo create subfractals and recursively call the print method on them
        /*
        Fractal1 left_sub_Fractal = new Fractal1(corner_0,
                                            new Point2D(corner_0.getX() + (corner_1.getX() - corner_0.getX())/2,corner_0.getY() + (corner_1.getY() - corner_0.getY())/2),
                                            new Point2D(corner_0.getX() + (corner_2.getX() - corner_0.getX())/2,corner_0.getY() + (corner_2.getY() - corner_0.getY())/2),
                                            -- recursion_Depth);

        Fractal1 right_sub_Fractal = new Fractal1(corner_0,
                new Point2D(corner_0.getX() + (corner_1.getX() - corner_0.getX())/2,corner_0.getY() + (corner_1.getY() - corner_0.getY())/2),
                new Point2D(corner_0.getX() + (corner_2.getX() - corner_0.getX())/2,corner_0.getY() + (corner_2.getY() - corner_0.getY())/2),
                -- recursion_Depth);

        Fractal1 top_sub_Fractal = new Fractal1(corner_0,
                new Point2D(corner_0.getX() + (corner_1.getX() - corner_0.getX())/2,corner_0.getY() + (corner_1.getY() - corner_0.getY())/2),
                new Point2D(corner_0.getX() + (corner_2.getX() - corner_0.getX())/2,corner_0.getY() + (corner_2.getY() - corner_0.getY())/2),
                -- recursion_Depth);

         */
    }

    //das Fraktal wird auf dem GraphicsContext, der später gespeichert wird, gezeichnet
    public void save(Graphics2D gc_buffer){

    }

    public void rotate(double angle){
        corner0 = new Vector2D(corner0.getX() * Math.cos(angle)- corner0.getY() * Math.sin(angle), corner0.getX() * Math.sin(angle) + corner0.getY() * Math.cos(angle));
        corner1 = new Vector2D(corner1.getX() * Math.cos(angle)- corner1.getY() * Math.sin(angle), corner1.getX() * Math.sin(angle) + corner1.getY() * Math.cos(angle));
        corner2 = new Vector2D(corner2.getX() * Math.cos(angle)- corner2.getY() * Math.sin(angle), corner2.getX() * Math.sin(angle) + corner2.getY() * Math.cos(angle));
    }


}


