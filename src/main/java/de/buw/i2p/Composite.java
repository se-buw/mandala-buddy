
package de.buw.i2p;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Pair;
import java.util.Vector;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;

public class Composite {
    public void print(GraphicsContext picture){
        for (Composite it: comp_container){
            it.print(picture);
        }
    };

    public void rotate(){
        for (Composite it: comp_container){
            it.rotate();
        }
    };

    public static void add(Composite object){
            comp_container.add(object);

    }
    private static Vector<Composite> comp_container;
}

