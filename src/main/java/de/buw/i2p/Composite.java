
package de.buw.i2p;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Pair;
import java.util.Vector;
import java.util.function.Supplier;

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

    public Composite(float diameter, float center_x, float center_y){
        this.diameter = diameter;
        this.center_x = center_x;
        this.center_y = center_y;
        comp_container = new Vector<>();
    }


    public <T> void generate(int num_segments, float diameter,float center_x, float center_y, String shape){
        this.diameter = diameter;
        this.center_x = center_x;
        this.center_y = center_y;

        Kreis base = new Kreis(center_x, center_y, diameter/2, false);
        comp_container.add(base);
        int num = (int)(4* Math.random());
        generate_1(num_segments);
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
            element_center_x = center_x + (float)(Math.cos(i * angle)*radius_small);
            element_center_y = center_y + (float)(Math.sin(i * angle)*radius_small);
            //comp_container.add(new Kreis(element_center_x, element_center_y, element_radius_small, false));
            comp_container.add(new Quadrat(element_center_x, element_center_y, element_radius_small, false));
            element_center_x = center_x + (float)(Math.cos(i * angle)*radius_big);
            element_center_y = center_y + (float)(Math.sin(i * angle)*radius_big);
            //comp_container.add(new Kreis(element_center_x, element_center_y, element_radius_big, false));
            comp_container.add(new Quadrat(element_center_x, element_center_y, element_radius_big, false));
        }
    }

    public void generate_1(int num_segments){
        //float radius = diameter/2 * 0.333f;

        float radius = diameter/2 * 0.275f;

        float element_center_x;
        float element_center_y;
        float element_radius = diameter/2.0f * 0.6f;
        float angle = (float)(2* Math.PI/ num_segments);
        for (int i = 0 ; i < num_segments; i++){
            element_center_x = center_x + (float)(Math.cos(i * angle)* radius);
            element_center_y = center_y + (float)(Math.sin(i * angle)* radius);
            comp_container.add(new Kreis(element_center_x, element_center_y, element_radius, true));
            //comp_container.add(new Quadrat(element_center_x, element_center_y, element_radius, true));
        }
    }

    public void generate_2(int num_segments){
        float element_center_x;
        float element_center_y;
        float angle = (float)(2* Math.PI/ num_segments);
        float element_radius = diameter/2 * 0.45f;
        for (int i = 0 ; i < num_segments; i++){
            element_center_x = center_x + (float)(Math.cos(i * angle)* element_radius);
            element_center_y = center_y + (float)(Math.sin(i * angle)*element_radius);
            comp_container.add(new Kreis(element_center_x, element_center_y, element_radius, true));
            //comp_container.add(new Quadrat(element_center_x, element_center_y, element_radius, true));
        }
    }

    public void generate_3(int num_segments){

        float radius = diameter/2 * 0.925f;
        float element_radius = diameter/2 * 0.025f;
        int num = (int)(Math.PI * radius / element_radius * 0.95f);
        float angle = (float)(2* Math.PI/ num);
        float element_center_x;
        float element_center_y;
        for(int i = 0; i < num; i++){
            element_center_x = center_x + (float)(Math.cos(i * angle)* radius);
            element_center_y = center_y + (float)(Math.sin(i * angle)* radius);
            comp_container.add(new Kreis(element_center_x, element_center_y, element_radius, true));
            //comp_container.add(new Quadrat(element_center_x, element_center_y, element_radius, true));
        }
    }

    public void generate_4(int num_segments){
        float radius_small = diameter/2 * 0.9f;
        float radius_big = diameter/2 * 0.95f;
        comp_container.add(new Kreis(center_x, center_y, radius_big, true));
        comp_container.add(new Kreis(center_x, center_y, radius_small, true));

        generate_3(num_segments);
    }

    public void generate_5(int num_segments){
        float radius_small = diameter/2 * 0.9f;
        float radius_mid = diameter/2 * 0.925f;
        float radius_big = diameter/2 * 0.95f;

        comp_container.add(new Kreis(center_x, center_y, radius_big, true));
        comp_container.add(new Kreis(center_x, center_y, radius_mid, true));
        comp_container.add(new Kreis(center_x, center_y, radius_small, true));
    }

    public void generate_6(int num_segments){
        float radius = diameter/2 * 0.85f;

        float angle = (float)(2* Math.PI/ num_segments);
        float element_radius = diameter/2 * 0.1f;
        float element_center_x;
        float element_center_y;

        for (int i = 0 ; i < num_segments; i++){
            element_center_x = center_x + (float)(Math.cos((i * angle) + (0.5f * angle))* radius);
            element_center_y = center_y + (float)(Math.sin((i * angle) + (0.5f * angle))* radius);
            comp_container.add(new Kreis(element_center_x, element_center_y, element_radius, true));
            //comp_container.add(new Quadrat(element_center_x, element_center_y, element_radius, true));
        }

        Composite comp_inside = new Composite(diameter * 0.75f, center_x, center_y);
        //comp_inside.generate_2(num_segments);
        comp_inside.generate_1(num_segments);
        comp_container.add(comp_inside);
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

