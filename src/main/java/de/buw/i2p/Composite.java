
package de.buw.i2p;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Pair;
import java.util.Vector;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;

public class Composite {

    float diameter;
    float center_x;
    float center_y;
    private Vector<Composite> comp_container;

    public Composite(){
        comp_container = new Vector<>();
    }


    public void generate(int num_segments, float diameter,float center_x, float center_y, String shape ){
        this.diameter = diameter;
        this.center_x = center_x;
        this.center_y = center_y;

        Kreis base = new Kreis(center_x, center_y, diameter/2, false);
        comp_container.add(base);
        int num = (int)(4* Math.random());
        generate_0(num_segments);
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
        float radius_small = diameter/2 * 0.4f;
        float radius_big = diameter/2 * 0.75f;
        comp_container.add(new Kreis(center_x, center_y, radius_small, true));
        comp_container.add(new Kreis(center_x, center_y, radius_big, true));
        
        float element_center_x;
        float element_center_y;
        float element_radius_small = diameter/2 * 0.1f;
        float element_radius_big = diameter/2 * 0.167f;

        float angle = (float)(2* Math.PI/ num_segments);
        for (int i = 0 ; i < num_segments; i++){
            element_center_x = 350.0f + (float)(Math.cos(i * angle)*radius_small);
            element_center_y = 350.0f + (float)(Math.sin(i * angle)*radius_small);
            comp_container.add(new Kreis(element_center_x, element_center_y, element_radius_small, false));
            //comp_container.add(new Quadrat(element_center_x, element_center_y, 30.0f, 30.0f , false));
            element_center_x = 350.0f + (float)(Math.cos(i * angle)*radius_big);
            element_center_y = 350.0f + (float)(Math.sin(i * angle)*radius_big);
            comp_container.add(new Kreis(element_center_x, element_center_y, element_radius_big, false));
            //comp_container.add(new Quadrat(element_center_x, element_center_y, 50.0f, 50.0f , false));
        }
    }

    public void generate_1(int num_segments){
        float center_x;
        float center_y;
        //float radius = 800.0f/(float)Math.sqrt((double) num_segments) - 300.0f/num_segments;
        float radius = 150.0f;
        float angle = (float)(2* Math.PI/ num_segments);
        for (int i = 0 ; i < num_segments; i++){
            center_x = 350.0f + (float)(Math.cos(i * angle)* 100.0f);
            center_y = 350.0f + (float)(Math.sin(i * angle)* 100.0f);
            comp_container.add(new Kreis(center_x, center_y, radius, true));
            //comp_container.add(new Quadrat(center_x, center_y, 2 * radius * (float)Math.sin(Math.PI/4.0),2* radius * (float)Math.sin(Math.PI/4.0), true));
        }
    }

    public void generate_2(int num_segments){
        float center_x;
        float center_y;
        float angle = (float)(2* Math.PI/ num_segments);
        float radius = 125.0f;
        for (int i = 0 ; i < num_segments; i++){
            center_x = 350.0f + (float)(Math.cos(i * angle)* radius);
            center_y = 350.0f + (float)(Math.sin(i * angle)*radius);
            comp_container.add(new Kreis(center_x, center_y, radius, true));
            //comp_container.add(new Quadrat(center_x, center_y, 2 * radius * (float)Math.sin(Math.PI/4.0),2* radius * (float)Math.sin(Math.PI/4.0),  true));
        }
    }

    public void print(GraphicsContext picture){
        for (Composite it: comp_container){
            it.print(picture);
        }
    }

    public void rotate(){
        for (Composite it: comp_container){
            it.rotate();
        }
    }

    public void add(Composite object){
            comp_container.add(object);
    }





}

