
package de.buw.i2p;

import javafx.util.Pair;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;


public class Kreis extends Composite {

    public Kreis(float x, float y, float r, Color c){
        this.point_x = x;
        this.point_y = y;
        this.radius_ = r;
        this.color_ = c;
    }

    public void print(GraphicsContext picture){
        picture.setStroke(color_);
        picture.strokeOval(point_x - radius_, point_y - radius_, 2*radius_, 2*radius_);
    }

    public void rotate(){

    }
    private float radius_;
    private Color color_;
    private float point_x;
    private float point_y;


}

