package de.buw.i2p;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.awt.*;

import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;



public class FraktalKreis extends Composite {
    protected Vector2D center;
    protected double radius;
    protected Color canvas_color; //Linien Farbe des Kreises für den Canvas
    protected java.awt.Color buffer_color;//Linien Farbe des Kreises für das zu speichernde Bild
    protected Color canvas_background;
    protected java.awt.Color buffer_background;
    protected int recursionDepth;
    protected boolean transparent;

    public FraktalKreis(Vector2D center, double radius, boolean transparent, int recursionDepth, Color canvas_background, java.awt.Color buffer_background) {
        this.center = center;
        this.radius = radius;
        this.recursionDepth = recursionDepth;
        this.transparent = transparent;
        this.canvas_background = canvas_background;
        this.buffer_background = buffer_background;
        canvas_color = Color.BLACK;
        buffer_color = java.awt.Color.BLACK;
    }
    public void print(GraphicsContext picture) {
        if(!transparent){ //falls der Kreis nicht transparent ist, wird der Kreis zuerst gefüllt
            picture.setFill(canvas_background);
            picture.fillOval(center.getX() - radius, center.getY() - radius, 2*radius, 2*radius);
        }
        //danach wird die Umrandung gezeichnet
        picture.setStroke(canvas_color);
        picture.strokeOval(center.getX() - radius, center.getY() - radius, 2*radius, 2*radius);

        if (recursionDepth > 0) {
            double newRadius = radius / 2.0;

            FraktalKreis sub_fractal0 = new FraktalKreis(new Vector2D(center.getX() + newRadius, center.getY()), newRadius, transparent, recursionDepth - 1, canvas_background, buffer_background);
            FraktalKreis sub_fractal1 = new FraktalKreis(new Vector2D(center.getX() - newRadius, center.getY()), newRadius, transparent, recursionDepth - 1, canvas_background, buffer_background);
            FraktalKreis sub_fractal2 = new FraktalKreis(new Vector2D(center.getX(), center.getY() + newRadius), newRadius, transparent, recursionDepth - 1, canvas_background, buffer_background);
            FraktalKreis sub_fractal3 = new FraktalKreis(new Vector2D(center.getX(), center.getY() - newRadius), newRadius, transparent, recursionDepth - 1, canvas_background, buffer_background);

            sub_fractal0.print(picture);
            sub_fractal1.print(picture);
            sub_fractal2.print(picture);
            sub_fractal3.print(picture);
            }
        }

    //das Fraktal wird auf dem GraphicsContext, der später gespeichert wird, gezeichnet
    public void save(Graphics2D gc_buffer) {
        if(!transparent){ //falls der Kreis nicht transparent ist, wird der Kreis zuerst gefüllt
            gc_buffer.setColor(buffer_background);
            gc_buffer.fillOval((int)(center.getX() - radius), (int)(center.getY() - radius), (int)(2 * radius), (int)(2 * radius));
        }
        //danach wird die Umrandung gezeichnet
        gc_buffer.setColor(buffer_background);
        gc_buffer.drawOval((int)(center.getX() - radius), (int)(center.getY() - radius), (int)(2 * radius), (int)(2 * radius));

        if (recursionDepth > 0) {
            double newRadius = radius / 2.0;

            FraktalKreis sub_fractal0 = new FraktalKreis(new Vector2D(center.getX() + newRadius, center.getY()), newRadius, transparent, recursionDepth - 3, canvas_background, buffer_background);
            FraktalKreis sub_fractal1 = new FraktalKreis(new Vector2D(center.getX() - newRadius, center.getY()), newRadius, transparent, recursionDepth - 3, canvas_background, buffer_background);
            FraktalKreis sub_fractal2 = new FraktalKreis(new Vector2D(center.getX(), center.getY() + newRadius), newRadius, transparent, recursionDepth - 3, canvas_background, buffer_background);
            FraktalKreis sub_fractal3 = new FraktalKreis(new Vector2D(center.getX(), center.getY() - newRadius), newRadius, transparent, recursionDepth - 3, canvas_background, buffer_background);

            sub_fractal0.save(gc_buffer);
            sub_fractal1.save(gc_buffer);
            sub_fractal2.save(gc_buffer);
            sub_fractal3.save(gc_buffer);
        }
    }

    }

