
package de.buw.i2p;

import javafx.scene.paint.*;
import javafx.scene.canvas.*;


public class Kreis extends Composite {
    private float radius;
    private float center_x;
    private float center_y;
    private Color color;
    private boolean transparent;


    public Kreis(float center_x_, float center_y_, float radius_, boolean trasparent_){
        center_x = center_x_;
        center_y = center_y_;
        radius = radius_;
        transparent = trasparent_;
        color = Color.BLACK;
    }
    public void print(GraphicsContext picture){
        picture.strokeOval(center_x, center_y, radius, radius);
    }

    public void rotate(){

    }



}

