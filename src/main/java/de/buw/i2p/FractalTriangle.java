package de.buw.i2p;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.canvas.*;
import java.awt.*;
public class FractalTriangle extends Composite{
    private Point2D corner_0;
    private Point2D corner_1;
    private Point2D corner_2;
    private Color canvas_color; //Linien Farbe des Kreises für den Canvas
    private java.awt.Color buffer_color;    //Linien Farbe des Kreises für das zu speichernde Bild
    private int recursion_Depth;

    public FractalTriangle(Point2D corner_0, Point2D corner_1, Point2D corner_2, int recursion_Depth){
        this.corner_0 = corner_0;
        this.corner_1 = corner_1;
        this.corner_2 = corner_2;
        this.recursion_Depth = recursion_Depth;
        canvas_color = Color.BLACK;
        buffer_color = java.awt.Color.BLACK;
    }

    //das Fraktal wird auf dem GraphicsContext für den Canvas gezeichnet
    public void print(GraphicsContext gc_canvas){
        double[] x_points = {corner_0.getX(), corner_1.getX(), corner_2.getX()};
        double[] y_points = {corner_0.getY(), corner_1.getY(), corner_2.getY()};
        gc_canvas.setStroke(canvas_color);
        gc_canvas.strokePolygon(x_points, y_points, 3);

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


}
