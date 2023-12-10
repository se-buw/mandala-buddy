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
    private Color canvas_background;
    private java.awt.Color buffer_background;
    private int recursionDepth;
    private boolean transparent;

    public FraktalQuadrat(Vector2D center, double radius, boolean transparent, int recursionDepth, Color canvas_background, java.awt.Color buffer_background) {
        this.center = center;
        this.radius = radius;
        this.recursionDepth = recursionDepth;
        this.canvas_background = canvas_background;
        this.buffer_background = buffer_background;
        this.transparent = transparent;
        canvas_color = Color.BLACK;
        buffer_color = java.awt.Color.BLACK;
    }

    public void print(GraphicsContext picture) {
        double length = 2 * radius * (double)Math.sin(Math.PI/4.0);

        if(!transparent){//falls das Quadrat nicht transparent ist, wird das Quadrat zuerst gefüllt
            picture.setFill(canvas_background);
            picture.fillRect(center.getX() - (length/2), center.getY() - (length/2), length, length);
        }
        //danach wird die Umrandung gezeichnet
        picture.setStroke(canvas_color);
        picture.strokeRect(center.getX() - (length/2), center.getY() - (length/2), length, length);
        if (recursionDepth > 0) {
            double newRadius = radius / 2.0 ;
            FraktalQuadrat sub_fractal0 = new FraktalQuadrat(new Vector2D(center.getX() + newRadius, center.getY()), newRadius, transparent, recursionDepth - 1, canvas_background, buffer_background);
            FraktalQuadrat sub_fractal1 = new FraktalQuadrat(new Vector2D(center.getX() - newRadius, center.getY()), newRadius, transparent, recursionDepth - 1, canvas_background, buffer_background);
            FraktalQuadrat sub_fractal2 = new FraktalQuadrat(new Vector2D(center.getX(), center.getY() + newRadius), newRadius, transparent, recursionDepth - 1, canvas_background, buffer_background);
            FraktalQuadrat sub_fractal3 = new FraktalQuadrat(new Vector2D(center.getX(), center.getY() - newRadius), newRadius, transparent, recursionDepth - 1, canvas_background, buffer_background);

            sub_fractal0.print(picture);
            sub_fractal1.print(picture);
            sub_fractal2.print(picture);
            sub_fractal3.print(picture);
        }

    }

    //das Fraktal wird auf dem GraphicsContext, der später gespeichert wird, gezeichnet
    public void save(Graphics2D gc_buffer) {
        double length = 2 * radius * (double)Math.sin(Math.PI/4.0);

        if(!transparent){//falls das Quadrat nicht transparent ist, wird das Quadrat zuerst gefüllt
            gc_buffer.setColor(buffer_background);
            gc_buffer.fillRect((int)(center.getX() - (length/2)), (int)(center.getY() - (length/2)), (int)length, (int)length);
        }
        //danach wird die Umrandung gezeichnet
        gc_buffer.setColor(buffer_background);
        gc_buffer.drawRect((int)(center.getX() - (length/2)), (int)(center.getY() - (length/2)), (int)length, (int)length);
        if (recursionDepth > 0) {
            double newRadius = radius / 2.0 ;
            FraktalQuadrat sub_fractal0 = new FraktalQuadrat(new Vector2D(center.getX() + newRadius, center.getY()), newRadius, transparent, recursionDepth - 1, canvas_background, buffer_background);
            FraktalQuadrat sub_fractal1 = new FraktalQuadrat(new Vector2D(center.getX() - newRadius, center.getY()), newRadius, transparent, recursionDepth - 1, canvas_background, buffer_background);
            FraktalQuadrat sub_fractal2 = new FraktalQuadrat(new Vector2D(center.getX(), center.getY() + newRadius), newRadius, transparent, recursionDepth - 1, canvas_background, buffer_background);
            FraktalQuadrat sub_fractal3 = new FraktalQuadrat(new Vector2D(center.getX(), center.getY() - newRadius), newRadius, transparent, recursionDepth - 1, canvas_background, buffer_background);

            sub_fractal0.save(gc_buffer);
            sub_fractal1.save(gc_buffer);
            sub_fractal2.save(gc_buffer);
            sub_fractal3.save(gc_buffer);
        }
    }
}
//todo change other datatypes to vectors too

