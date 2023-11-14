
package de.buw.i2p;

import javafx.util.Pair;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;


public class Kreis extends Composite {
    public void print(GraphicsContext picture){
        picture.strokeOval(point_x, point_y, radius, radius);
    }

    public void rotate(){

    }
    private float radius;
    private float point_x;
    private float point_y;


}

