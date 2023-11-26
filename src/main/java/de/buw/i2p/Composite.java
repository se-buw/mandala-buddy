
package de.buw.i2p;
import javafx.scene.canvas.GraphicsContext;

import org.checkerframework.checker.units.qual.C;

import javafx.util.Pair;

import java.awt.*;

import java.util.Vector;

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



    public void generate(int num_segments, String shape){


        Kreis base = new Kreis(center_x, center_y, diameter/2, false);

        comp_container.add(base);

        double num = (Math.random());
        if(num < 0.5){
            random_body(num_segments);
        }
        else{
            random_border(num_segments);
            Composite comp_inside = new Composite(diameter * 0.75f, center_x, center_y);
            comp_inside.random_body(num_segments);
            comp_container.add(comp_inside);
        }

    }

    public void random_body(int num_segments){
        int num = (int)(5* Math.random());
        switch(num){
            case(0):
                generate_01(num_segments);
                break;
            case(1):
                generate_02(num_segments);
                break;
            case(2):
                generate_03(num_segments);
                break;
            case(3):
                generate_04(num_segments);
                break;
            case(4):
                generate_05(num_segments);
                break;
        }
    }

    public void random_border(int num_segments){
        int num = (int)(4* Math.random());
        switch(num){
            case(0):
                generate_11(num_segments);
                break;
            case(1):
                generate_12(num_segments);
                break;
            case(2):
                generate_13(num_segments);
                break;
            case(3):
                generate_14(num_segments);
                break;
        }
    }

    public void random_center(int num_segments){
        int num = (int)(2* Math.random());
        switch(num){
            case(0):
                generate_21(num_segments);
                break;
            case(1):
                generate_22(num_segments);
                break;
        }
    }

    public void generate_01(int num_segments){
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
            comp_container.add(new Kreis(element_center_x, element_center_y, element_radius_small, false));
            //comp_container.add(new Quadrat(element_center_x, element_center_y, element_radius_small, false));
            element_center_x = center_x + (float)(Math.cos(i * angle)*radius_big);
            element_center_y = center_y + (float)(Math.sin(i * angle)*radius_big);
            comp_container.add(new Kreis(element_center_x, element_center_y, element_radius_big, false));
            //comp_container.add(new Quadrat(element_center_x, element_center_y, element_radius_big, false));
        }

        double num = Math.random();
        if(num < 0.5 ){
            random_center(num_segments);
        }
    }


    public void generate_02(int num_segments){
        float radius_1 = diameter/2 * 0.25f;
        float radius_2 = diameter/2 * 0.45f;
        float radius_3 = diameter/2 * 0.75f;

        float angle = (float)(2* Math.PI/ num_segments);
        float element_radius_1 = diameter/2 * 0.05f;
        float element_radius_2 = diameter/2 * 0.1f;
        float element_radius_3 = diameter/2 * 0.15f;
        float element_center_x;
        float element_center_y;

        for(int i = 0; i < num_segments; i++){
            element_center_x = center_x + (float)(Math.cos((i * angle))* radius_1);
            element_center_y = center_y + (float)(Math.sin((i * angle))* radius_1);
            comp_container.add(new Kreis(element_center_x, element_center_y, element_radius_1, true));
            //comp_container.add(new Quadrat(element_center_x, element_center_y, element_radius_1, true));

            element_center_x = center_x + (float)(Math.cos((i * angle))* radius_2);
            element_center_y = center_y + (float)(Math.sin((i * angle))* radius_2);
            comp_container.add(new Kreis(element_center_x, element_center_y, element_radius_2, true));
            //comp_container.add(new Quadrat(element_center_x, element_center_y, element_radius_2, true));

            element_center_x = center_x + (float)(Math.cos((i * angle))* radius_3);
            element_center_y = center_y + (float)(Math.sin((i * angle))* radius_3);
            comp_container.add(new Kreis(element_center_x, element_center_y, element_radius_3, true));
            //comp_container.add(new Quadrat(element_center_x, element_center_y, element_radius_3, true));
        }
        double num = Math.random();
        if(num < 0.5 ){
            random_center(num_segments);
        }
    }

    public void generate_03(int num_segments){
        float radius_big = diameter/2 * 0.65f;

        float angle = (float)(2* Math.PI/ num_segments);
        float element_radius_small = diameter/2 * 0.05f;
        float element_radius_big = diameter/2 * 0.15f;
        float element_center_x;
        float element_center_y;

        for(int i = 0; i < num_segments; i++){
            element_center_x = center_x + (float)(Math.cos((i * angle))* radius_big);
            element_center_y = center_y + (float)(Math.sin((i * angle))* radius_big);
            comp_container.add(new Kreis(element_center_x, element_center_y, element_radius_big, true));
            //comp_container.add(new Quadrat(element_center_x, element_center_y, element_radius_big, true));

            element_center_x = center_x + (float)(Math.cos((i * angle))* (diameter/2 *0.9f));
            element_center_y = center_y + (float)(Math.sin((i * angle))* (diameter/2 *0.9f));
            comp_container.add(new Kreis(element_center_x, element_center_y, element_radius_small, true));
            //comp_container.add(new Quadrat(element_center_x, element_center_y, element_radius_small, true));

            element_center_x = center_x + (float)(Math.cos((i * angle))* (diameter/2 * 0.25f));
            element_center_y = center_y + (float)(Math.sin((i * angle))* (diameter/2 * 0.25f));
            comp_container.add(new Kreis(element_center_x, element_center_y, element_radius_small, true));
            //comp_container.add(new Quadrat(element_center_x, element_center_y, element_radius_small, true));

            element_center_x = center_x + (float)(Math.cos((i * angle))* (diameter/2 * 0.4f));
            element_center_y = center_y + (float)(Math.sin((i * angle))* (diameter/2 * 0.4f));
            comp_container.add(new Kreis(element_center_x, element_center_y, element_radius_small, true));
            //comp_container.add(new Quadrat(element_center_x, element_center_y, element_radius_small, true));

        }
        double num = Math.random();
        if(num < 0.5 ){
            random_center(num_segments);
        }

    }
    public void generate_04(int num_segments){
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
        double num = Math.random();
        if(num < 0.5 ){
            random_center(num_segments);
        }
    }

    public void generate_05(int num_segments){
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

    public void generate_11(int num_segments){

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

    public void generate_12(int num_segments){
        float radius_small = diameter/2 * 0.9f;
        float radius_big = diameter/2 * 0.95f;
        comp_container.add(new Kreis(center_x, center_y, radius_big, true));
        comp_container.add(new Kreis(center_x, center_y, radius_small, true));

        generate_11(num_segments);
    }

    public void generate_13(int num_segments){
        float radius_small = diameter/2 * 0.9f;
        float radius_mid = diameter/2 * 0.925f;
        float radius_big = diameter/2 * 0.95f;

        comp_container.add(new Kreis(center_x, center_y, radius_big, true));
        comp_container.add(new Kreis(center_x, center_y, radius_mid, true));
        comp_container.add(new Kreis(center_x, center_y, radius_small, true));
    }

    public void generate_14(int num_segments){
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

        /*
        Composite comp_inside = new Composite(diameter * 0.75f, center_x, center_y);
        comp_inside.generate_05(num_segments);
        //comp_inside.generate_1(num_segments);
        comp_container.add(comp_inside);
         */

    }



    public void generate_21(int num_segments){
        comp_container.add(new Kreis(center_x, center_y, diameter/2 * 0.075f, true));
    }

    public void generate_22(int num_segments){
        comp_container.add(new Kreis(center_x, center_y, diameter/2 * 0.075f, true));
        comp_container.add(new Kreis(center_x, center_y, diameter/2 * 0.1f, true));
        comp_container.add(new Kreis(center_x, center_y, diameter/2 * 0.125f, true));
    }
  
  

   public void print(GraphicsContext gc_canvas, Graphics2D gc_buffer){
          for (Composite it: comp_container){
              it.print(gc_canvas, gc_buffer);
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

