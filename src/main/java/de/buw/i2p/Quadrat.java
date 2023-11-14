
package de.buw.i2p;


import javafx.scene.canvas.GraphicsContext;
import javafx.util.Pair;

public class Quadrat extends Composite {

    public void print(GraphicsContext picture){

        picture.strokeRect(point_x, point_y, width, length );
    };

    public void rotate(){

    }

    private float point_x;
    private float point_y;
    private float length;
    private float width;
}