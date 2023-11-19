
package de.buw.i2p;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Pair;
import java.util.Vector;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;

public class Composite {

    float size;
    private Vector<Composite> comp_container;

    public Composite(){
        comp_container = new Vector<>();
    }
    public void print(GraphicsContext picture){
        System.out.println("Composites print");
        for (Composite it: comp_container){
            System.out.println("hi");
            it.print(picture);
        }
    };

    public void rotate(){
        for (Composite it: comp_container){
            it.rotate();
        }
    };

    public /*static*/ void add(Composite object){
            comp_container.add(object);

    }

    public void generate(int num_segments, String shape){

        Kreis base = new Kreis(350.0f, 350.0f, size/2, true);
        comp_container.add(base);
        int num = (int)(4* Math.random());
        generate_2(num_segments);
        /*
        switch(num){
            case(0):
                generate_0(num_segments);
                break;
            case(1):
                generate_1(num_segments);
                break;
            case(2):
                generate_2(num_segments);
                break;

        }*/

    }

    public void generate_0(int num_segments){
        float center_x;
        float center_y;
        float radius = (float) ((Math.PI * size/4)/num_segments);
        float angle = (350.0f/ num_segments);
        for (int i = 0 ; i < num_segments; i++){
            center_x = 350.0f + (float)(Math.cos(i * angle)*radius);
            center_y = 350.0f + (float)(Math.sin(i * angle)*radius);
            comp_container.add(new Kreis(center_x, center_y, radius, false));
        }
    }

    public void generate_1(int num_segments){
        float center_x;
        float center_y;
        float radius =  1.5f*((float)((Math.PI * size/4)/num_segments));
        float angle = (350.0f/ num_segments);
    }

    public void generate_2(int num_segments){
        float center_x;
        float center_y;
        float angle = (350.0f/ num_segments);
        float radius = 125.0f;
        for (int i = 0 ; i < num_segments; i++){
            center_x = 350.0f + (float)(Math.cos(i * angle)* radius);
            center_y = 350.0f + (float)(Math.sin(i * angle)*radius);
            comp_container.add(new Kreis(center_x, center_y, radius, false));
        }
    }

}

