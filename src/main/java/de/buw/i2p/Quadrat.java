
package de.buw.i2p;

import javafx.scene.canvas.GraphicsContext;


import javafx.scene.paint.*;


public class Quadrat extends Composite {
    private float center_x;
    private float center_y;
    private float height;
    private float width;

    private float radius;
    private Color color;
    private boolean transparent;


    public Quadrat(float corner_x, float corner_y, float radius, boolean trasparent){
        this.center_x = corner_x;
        this.center_y = corner_y;
        this.radius = radius;
        this.transparent = trasparent;
        this.color = Color.BLACK;
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

    public void rotate(){

    }

}