
package de.buw.i2p;

import javafx.scene.canvas.GraphicsContext;


import javafx.scene.paint.*;
import javafx.scene.paint.Color;

import java.awt.*;


public class Quadrat extends Composite {
    private float center_x;
    private float center_y;
    private float height;
    private float width;

    private float radius;
    private Color color;
    private java.awt.Color buffer_color;
    private boolean transparent;


    public Quadrat(float corner_x, float corner_y, float radius, boolean trasparent){
        this.center_x = corner_x;
        this.center_y = corner_y;
        this.radius = radius;
        this.transparent = trasparent;
        this.color = Color.BLACK;
        buffer_color = java.awt.Color.BLACK;
    }

    public void print(GraphicsContext picture){
        float length = 2 * radius * (float)Math.sin(Math.PI/4.0);

        picture.setStroke(color);
        picture.setFill(Color.WHITE);
        if(!transparent){
            picture.fillRect(center_x - (length/2), center_y - (length/2), length, length);
        }
        picture.strokeRect(center_x - (length/2), center_y - (length/2), length, length);


    }

    public void save(Graphics2D gc_buffer){
        float length = 2 * radius * (float)Math.sin(Math.PI/4.0);

        //es werden die Farben gesetzt
        gc_buffer.setColor(buffer_color);
        if(!transparent){
            gc_buffer.setColor(java.awt.Color.WHITE);
            gc_buffer.fillRect((int)center_x - (int)radius, (int)center_y - (int)radius, 2*(int)radius, 2*(int)radius);
        }
        //die Kreise werden gezeichnet
        gc_buffer.drawRect((int)center_x - (int)radius, (int)center_y - (int)radius, 2*(int)radius, 2*(int)radius);

    }

    public void rotate(){

    }

}