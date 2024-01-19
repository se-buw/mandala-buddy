package de.buw.i2p;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.awt.*;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class FraktalQuadrat extends Composite {
    private Vector2D center;
    private Vector2D leftup;
    private Vector2D rightup;
    private Vector2D rightdown;
    private Vector2D leftdown;
    private double radius;
    private double rotation;
    private Color canvas_color; //Linien Farbe des Kreises für den Canvas
    private java.awt.Color buffer_color;//Linien Farbe des Kreises für das zu speichernde Bild
    private Color canvas_background;
    private java.awt.Color buffer_background;
    private int recursionDepth;
    private boolean transparent;

    public FraktalQuadrat(Vector2D center, double radius, boolean transparent, int recursionDepth, double rotationin, Color canvas_background, java.awt.Color buffer_background) {
        this.center = center;
        this.radius = radius;
        this.rotation = rotationin;
        this.recursionDepth = recursionDepth;
        this.canvas_background = canvas_background;
        this.buffer_background = buffer_background;
        this.transparent = transparent;
        canvas_color = Color.BLACK;
        buffer_color = java.awt.Color.BLACK;
        leftdown = new Vector2D(center.getX() + radius * Math.cos(Math.PI/4 + Math.PI/2 + rotation), center.getY() + radius * Math.sin(Math.PI/4 + Math.PI/2 + rotation));
        rightdown = new Vector2D(center.getX() + radius * Math.cos(Math.PI/4 + rotation), center.getY() + radius * Math.sin(Math.PI/4 + rotation ));
        rightup = new Vector2D(center.getX() + radius * Math.cos(Math.PI/4 + 3 * Math.PI/2  + rotation), center.getY() + radius * Math.sin(Math.PI/4 + 3 * Math.PI/2 + rotation));
        leftup = new Vector2D(center.getX() + radius * Math.cos(Math.PI/4 + 2 * Math.PI/2  + rotation), center.getY() + radius * Math.sin(Math.PI/4 + 2 * Math.PI/2 + rotation));
    }

    public void print(GraphicsContext picture) {
        double length = 2 * radius * (double)Math.sin(Math.PI/4.0);
        double[] x_points = {rightup.getX(), leftup.getX(), leftdown.getX(), rightdown.getX()};
        double[] y_points = {rightup.getY(), leftup.getY(), leftdown.getY(), rightdown.getY()};
        
        if(!transparent){//falls das Quadrat nicht transparent ist, wird das Quadrat zuerst gefüllt
            picture.setFill(canvas_background);
            //picture.fillRect(center.getX() - (length/2), center.getY() - (length/2), length, length);
            picture.fillPolygon(x_points, y_points, 4);
            
        }
        //danach wird die Umrandung gezeichnet
        picture.setStroke(canvas_color);
        //picture.strokeRect(center.getX() - (length/2), center.getY() - (length/2), length, length);
        picture.strokePolygon(x_points, y_points, 4);
        
        if (recursionDepth > 0) {
            double newRadius = radius / 2.0 ;
            FraktalQuadrat sub_fractal0 = new FraktalQuadrat(new Vector2D(center.getX() + newRadius * Math.cos(Math.PI/2 + rotation) , center.getY() + newRadius * Math.sin(Math.PI/2 + rotation)) , newRadius, transparent, recursionDepth - 10, rotation, canvas_background, buffer_background);
            FraktalQuadrat sub_fractal1 = new FraktalQuadrat(new Vector2D(center.getX() + newRadius * Math.cos(2*Math.PI/2 + rotation), center.getY() + newRadius * Math.sin(2 * Math.PI/2 + rotation) ), newRadius, transparent, recursionDepth - 10, rotation, canvas_background, buffer_background);
            FraktalQuadrat sub_fractal2 = new FraktalQuadrat(new Vector2D(center.getX() + newRadius * Math.cos(3*Math.PI/2 + rotation), center.getY() + newRadius * Math.sin(3 * Math.PI/2 + rotation) ), newRadius, transparent, recursionDepth - 10, rotation, canvas_background, buffer_background);
            FraktalQuadrat sub_fractal3 = new FraktalQuadrat(new Vector2D(center.getX() + newRadius * Math.cos(4*Math.PI/2 + rotation), center.getY() + newRadius * Math.sin(4 * Math.PI/2 + rotation) ), newRadius, transparent, recursionDepth - 10, rotation, canvas_background, buffer_background);

            sub_fractal1.print(picture);
            sub_fractal3.print(picture);
            sub_fractal0.print(picture);
            sub_fractal2.print(picture);
        }

    }

    //das Fraktal wird auf dem GraphicsContext, der später gespeichert wird, gezeichnet
    public void save(Graphics2D gc_buffer) {
        double length = 2 * radius * (double)Math.sin(Math.PI/4.0);
        int[] x_points = {(int)rightup.getX(), (int)leftup.getX(), (int)leftdown.getX(), (int)rightdown.getX()};
        int[] y_points = {(int)rightup.getY(), (int)leftup.getY(), (int)leftdown.getY(), (int)rightdown.getY()};

        if(!transparent){//falls das Quadrat nicht transparent ist, wird das Quadrat zuerst gefüllt
            gc_buffer.setColor(buffer_background);
            gc_buffer.fillPolygon(x_points,y_points,4);
        }
        //danach wird die Umrandung gezeichnet
        gc_buffer.setColor(buffer_color);
        gc_buffer.drawPolygon(x_points, y_points, 4);
        
        if (recursionDepth > 0 ) {
            double newRadius = radius / 2.0 ;
            
            FraktalQuadrat sub_fractal0 = new FraktalQuadrat(new Vector2D(center.getX() + newRadius * Math.cos(Math.PI/2 + rotation) , center.getY() + newRadius * Math.sin(Math.PI/2 + rotation)) , newRadius, transparent, recursionDepth - 10, rotation, canvas_background, buffer_background);
            FraktalQuadrat sub_fractal1 = new FraktalQuadrat(new Vector2D(center.getX() + newRadius * Math.cos(2*Math.PI/2 + rotation), center.getY() + newRadius * Math.sin(2 * Math.PI/2 + rotation) ), newRadius, transparent, recursionDepth - 10, rotation, canvas_background, buffer_background);
            FraktalQuadrat sub_fractal2 = new FraktalQuadrat(new Vector2D(center.getX() + newRadius * Math.cos(3*Math.PI/2 + rotation), center.getY() + newRadius * Math.sin(3 * Math.PI/2 + rotation) ), newRadius, transparent, recursionDepth - 10, rotation, canvas_background, buffer_background);
            FraktalQuadrat sub_fractal3 = new FraktalQuadrat(new Vector2D(center.getX() + newRadius * Math.cos(4*Math.PI/2 + rotation), center.getY() + newRadius * Math.sin(4 * Math.PI/2 + rotation) ), newRadius, transparent, recursionDepth - 10, rotation, canvas_background, buffer_background);

            sub_fractal1.save(gc_buffer);
            sub_fractal3.save(gc_buffer);
            sub_fractal0.save(gc_buffer);
            sub_fractal2.save(gc_buffer);
        }
    }
}
//todo change other datatypes to vectors too

