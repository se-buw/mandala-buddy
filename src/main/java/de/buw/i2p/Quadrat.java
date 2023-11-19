
package de.buw.i2p;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Pair;

public class Quadrat extends Composite {
    private float corner_x;
    private float corner_y;
    private float length;
    private float width;
    private Color color;
    private boolean transparent;

    public Quadrat(float corner_x_, float corner_y_, float length_, float width_, boolean trasparent_){
        corner_x = corner_x_;
        corner_y = corner_y_;
        length = length_;
        width = width_;
        transparent = trasparent_;
        color = Color.BLACK;
    }

    public void print(GraphicsContext picture){

        picture.strokeRect(corner_x, corner_y, width, length );
    };

    public void rotate(){

    }


}