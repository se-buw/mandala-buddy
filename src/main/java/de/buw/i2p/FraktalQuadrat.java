package de.buw.i2p;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.awt.*;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class FraktalQuadrat extends Composite {
    private Vector2D centerPoint;
    private float radius;
    private Color canvas_color; //Linien Farbe des Kreises für den Canvas
    private java.awt.Color buffer_color;//Linien Farbe des Kreises für das zu speichernde Bild
    private int recursionDepth;

    public FraktalQuadrat(Vector2D centerPoint, float radius, int recursionDepth) {
        this.centerPoint = centerPoint;
        this.radius = radius;
        this.recursionDepth = recursionDepth;
        canvas_color = Color.BLACK;
        buffer_color = java.awt.Color.BLACK;
    }

    public void print(GraphicsContext gc_canvas) {

        //todo create subfractals and recursively call the print method on them

    }

    //das Fraktal wird auf dem GraphicsContext, der später gespeichert wird, gezeichnet
    public void save(Graphics2D gc_buffer) {

    }
}
//todo change other datatypes to vectors too

