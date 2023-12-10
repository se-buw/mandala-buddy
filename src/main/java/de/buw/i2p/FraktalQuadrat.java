package de.buw.i2p;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.awt.*;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class FraktalQuadrat extends Composite {
    private Vector2D center;
    private double radius;
    private Color canvas_color; //Linien Farbe des Kreises für den Canvas
    private java.awt.Color buffer_color;//Linien Farbe des Kreises für das zu speichernde Bild
    private int recursionDepth;
    private boolean transparent;

    public FraktalQuadrat(Vector2D center, double radius, boolean transparent, int recursionDepth) {
        this.center = center;
        this.radius = radius;
        this.recursionDepth = recursionDepth;
        this.transparent = transparent;
        canvas_color = Color.BLACK;
        buffer_color = java.awt.Color.BLACK;
    }

    public void print(GraphicsContext picture) {
        double length = 2 * radius * (double)Math.sin(Math.PI/4.0);

        if(!transparent){//falls das Quadrat nicht transparent ist, wird das Quadrat zuerst gefüllt
            picture.setFill(Color.WHITE);
            picture.fillRect(center.getX() - (length/2), center.getY() - (length/2), length, length);
        }
        //danach wird die Umrandung gezeichnet
        picture.setStroke(canvas_color);
        picture.strokeRect(center.getX() - (length/2), center.getY() - (length/2), length, length);
        if (recursionDepth > 0) {
            double newRadius = radius / 2.0 ;
            FraktalQuadrat sub_fractal0 = new FraktalQuadrat(new Vector2D(center.getX() + newRadius, center.getY()), newRadius, transparent, recursionDepth - 1);
            FraktalQuadrat sub_fractal1 = new FraktalQuadrat(new Vector2D(center.getX() - newRadius, center.getY()), newRadius, transparent, recursionDepth - 1);
            FraktalQuadrat sub_fractal2 = new FraktalQuadrat(new Vector2D(center.getX(), center.getY() + newRadius), newRadius, transparent, recursionDepth - 1);
            FraktalQuadrat sub_fractal3 = new FraktalQuadrat(new Vector2D(center.getX(), center.getY() - newRadius), newRadius, transparent, recursionDepth - 1);

            sub_fractal0.print(picture);
            sub_fractal1.print(picture);
            sub_fractal2.print(picture);
            sub_fractal3.print(picture);
        }

    }

    //das Fraktal wird auf dem GraphicsContext, der später gespeichert wird, gezeichnet
    public void save(Graphics2D gc_buffer) {

    }
}
//todo change other datatypes to vectors too

