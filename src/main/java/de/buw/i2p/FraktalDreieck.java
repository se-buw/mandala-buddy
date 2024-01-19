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
    private Color canvas_stroke_color; //Linien Farbe des Kreises für den Canvas
    private java.awt.Color buffer_stroke_color;    //Linien Farbe des Kreises für das zu speichernde Bild
    private Color canvas_background;
    private java.awt.Color buffer_background;
    private int recursionDepth;     //wie oft sich das Muster wiederholt


    public FraktalDreieck(Vector2D center, double radius, boolean transparent, int recursionDepth, double rotationin, Color canvas_background, java.awt.Color buffer_background){
        this.center =  center;
        this.radius = radius;
        this.transparent = transparent;
        this.recursionDepth = recursionDepth;
        this.rotation = rotationin;
        this.canvas_background = canvas_background;
        this.buffer_background = buffer_background;
        canvas_stroke_color = Color.BLACK;
        buffer_stroke_color = java.awt.Color.BLACK;
        //Ecken werden initialisert(Gleichschenkliges Dreieck mit Winkeln 90° + rotation, 210° + rotation und 330° + rotation)
        //todo mit rotation rumprobieren um dreiecke i  verschiedene richtungen zeigen zu lassen, vor rotation random int zwischen 0 und 1
        corner0 = new Vector2D(center.getX() + radius * Math.cos(0 + rotation), center.getY() + radius * Math.sin(0 + rotation));
        corner1 = new Vector2D(center.getX() + radius * Math.cos(2 * Math.PI/3 + rotation), center.getY() + radius * Math.sin(2 * Math.PI/3 + rotation ));
        corner2 = new Vector2D(center.getX() + radius * Math.cos(4 * Math.PI/3 + rotation), center.getY() + radius * Math.sin(4 * Math.PI/3 + rotation));
    }

    public FraktalDreieck(Vector2D corner0, Vector2D corner1, Vector2D corner2, boolean transparent, int recursionDepth, Color canvas_background, java.awt.Color buffer_background){
        this.corner0 = corner0;
        this.corner1 = corner1;
        this.corner2 = corner2;
        this.transparent = transparent;
        this.recursionDepth = recursionDepth;
        this.canvas_background = canvas_background;
        this.buffer_background = buffer_background;
    }

    //das Fraktal wird auf dem GraphicsContext für den Canvas gezeichnet
    public void print(GraphicsContext picture){
        double[] x_points = {corner0.getX(), corner1.getX(), corner2.getX()};
        double[] y_points = {corner0.getY(), corner1.getY(), corner2.getY()};


        if(!transparent){
        	picture.setFill(canvas_background);
        	picture.fillPolygon(x_points, y_points, 3);
        }

        //Umrandung wird gezeichnet
        picture.setStroke(canvas_stroke_color);
        picture.strokePolygon(x_points, y_points, 3);

        if(recursionDepth > 0){
            FraktalDreieck sub_fractal0 = new FraktalDreieck(corner0, corner0.add(0.5, corner1.subtract(corner0)), corner0.add(0.5, corner2.subtract(corner0)), transparent, recursionDepth - 1, canvas_background, buffer_background);
            FraktalDreieck sub_fractal1 = new FraktalDreieck(corner1.add(0.5, corner0.subtract(corner1)), corner1, corner1.add(0.5, corner2.subtract(corner1)), transparent, recursionDepth - 1, canvas_background, buffer_background);
            FraktalDreieck sub_fractal2 = new FraktalDreieck(corner2.add(0.5, corner0.subtract(corner2)), corner2.add(0.5, corner1.subtract(corner2)), corner2, transparent, recursionDepth - 1, canvas_background, buffer_background);

            sub_fractal0.print(picture);
            sub_fractal1.print(picture);
            sub_fractal2.print(picture);
        }
       


    }

    //das Fraktal wird auf dem GraphicsContext, der später gespeichert wird, gezeichnet
    public void save(Graphics2D gc_buffer){
        int[] x_points = {(int)corner0.getX(), (int)corner1.getX(), (int)corner2.getX()};
        int[] y_points = {(int)corner0.getY(), (int)corner1.getY(), (int)corner2.getY()};

        // Füllt die Farbe nur in der ersten Rekursionstiefe aus, da die anderen Dreiecke innerhalb des obersten liegen
        if(!transparent && recursionDepth == 3){
        	gc_buffer.setColor(buffer_background);
        	gc_buffer.fillPolygon(x_points, y_points, 3);
        }
        gc_buffer.setColor(buffer_stroke_color);
        gc_buffer.drawPolygon(x_points, y_points, 3);
        
        // Weitere Rekursion, wenn Rekursionstiefe noch nicht erreicht wurde
        if(recursionDepth > 0){
            FraktalDreieck sub_fractal0 = new FraktalDreieck(corner0, corner0.add(0.5, corner1.subtract(corner0)), corner0.add(0.5, corner2.subtract(corner0)), transparent, recursionDepth - 1, canvas_background, buffer_background);
            FraktalDreieck sub_fractal1 = new FraktalDreieck(corner1.add(0.5, corner0.subtract(corner1)), corner1, corner1.add(0.5, corner2.subtract(corner1)), transparent, recursionDepth - 1, canvas_background, buffer_background);
            FraktalDreieck sub_fractal2 = new FraktalDreieck(corner2.add(0.5, corner0.subtract(corner2)), corner2.add(0.5, corner1.subtract(corner2)), corner2, transparent, recursionDepth - 1, canvas_background, buffer_background);

            sub_fractal0.save(gc_buffer);
            sub_fractal1.save(gc_buffer);
            sub_fractal2.save(gc_buffer);
        }
    }
}


