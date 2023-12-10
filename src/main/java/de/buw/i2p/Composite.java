
package de.buw.i2p;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.paint.Color;
import org.checkerframework.checker.units.qual.C;

import javafx.util.Pair;

import java.awt.*;

import java.util.Vector;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;


public class Composite {

    double diameter;     //Größe des Composites
    private Vector2D center;
    private Color canvas_background;
    private java.awt.Color buffer_background;
    private Vector<Composite> comp_container;   //Vektor, in dem alle Kinder des Composites gespeichert werden
    private Vector<Color> offset_canvas_colors;
    private Vector<java.awt.Color> offset_buffer_colors;

    public Composite(){
        comp_container = new Vector<>();
    }


    //ein Composite einer bestimmten Größe, mit einem bestimmten Mittelpunkt wird erzeugt
    public Composite(Vector2D center, double diameter, Color canvas_background, java.awt.Color buffer_background){
        this.center = center;
        this.diameter = diameter;
        comp_container = new Vector<>();
        this.canvas_background = canvas_background;
        this.buffer_background = buffer_background;

        offset_canvas_colors = new Vector<>();
        offset_buffer_colors = new Vector<>();
        //create similar colors to the background
        for( int i = 0; i < 5; i++ ){
            Color offset_color_canvas = offset_canvas_color(canvas_background);
            java.awt.Color offset_color_buffer = new java.awt.Color((int)(offset_color_canvas.getRed() * 255), (int)(offset_color_canvas.getGreen() * 255), (int)(offset_color_canvas.getBlue() * 255));

            offset_canvas_colors.add(offset_color_canvas);
            offset_buffer_colors.add(offset_color_buffer);
        }
    }



    public void generate(int num_segments, String shape){
        Kreis base = new Kreis(center, diameter/2, false, canvas_background, buffer_background);
        comp_container.add(base);       //die Grundform des Mandalas wird erstllt und

        //es wird zufällig entschieden, ob das Mandala eine Bordüre haben soll oder nicht
        double num = (Math.random());
        if(num < 0.5){
            random_body(num_segments, shape);       //keine Bordüre, nur der Körper
        }
        else{
            random_border(num_segments, shape);     //Bordüre
            Composite comp_inside = new Composite(center, diameter * 0.75f, canvas_background, buffer_background);    //für den jetzt etwas kleineren Körper wird ein neues Composite erstellt
            comp_inside.random_body(num_segments, shape);
            comp_container.add(comp_inside);
        }


    }

    //es wird zufällig eine Technik für die Erstellung des Körpers gewählt
    public void random_body(int num_segments, String shape){
        int num = (int)(5* Math.random());
        switch(num){
            case(0):
                generate_01(num_segments, shape);
                break;
            case(1):
                generate_02(num_segments, shape);
                break;
            case(2):
                generate_03(num_segments, shape);
                break;
            case(3):
                generate_04(num_segments, shape);
                break;
            case(4):
                generate_05(num_segments, shape);
                break;
        }

    }

    //es wird zufällig eine Bordüre gewählt
    public void random_border(int num_segments, String shape){
        int num = (int)(4* Math.random());
        switch(num){
            case(0):
                generate_11(num_segments, shape);
                break;
            case(1):
                generate_12(num_segments, shape);
                break;
            case(2):
                generate_13(num_segments, shape);
                break;
            case(3):
                generate_14(num_segments, shape);
                break;
        }
    }

    //es wird zufällig ein Zentrum gewählt
    public void random_center(int num_segments, String shape){
        int num = (int)(2* Math.random());
        switch(num){
            case(0):
                generate_21(num_segments, shape);
                break;
            case(1):
                generate_22(num_segments, shape);
                break;
        }
    }

    //generate_01 bis 05 generieren verschiedene Körper

    public void generate_01(int num_segments, String shape){
        double radius_small = diameter/2 * 0.4f;
        double radius_big = diameter/2 * 0.75f;
        comp_container.add(new Kreis(center, radius_small, true, canvas_background, buffer_background));
        comp_container.add(new Kreis(center, radius_big, true, canvas_background, buffer_background));

        double element_center_x_1, element_center_x_2;
        double element_center_y_1, element_center_y_2;
        double element_radius_small = diameter/2 * 0.1f;
        double element_radius_big = diameter/2 * 0.167f;

        double random_rotation = Math.random();

        float angle = (float)(2* Math.PI/ num_segments);
        for (int i = 0 ; i < num_segments; i++){
            //todo remove (double) if not necessary
            element_center_x_1 = center.getX() + (double)(Math.cos(i * angle)*radius_small);
            element_center_y_1 = center.getY() + (double)(Math.sin(i * angle)*radius_small);
            element_center_x_2 = center.getX() + (double)(Math.cos(i * angle)*radius_big);
            element_center_y_2 = center.getY() + (double)(Math.sin(i * angle)*radius_big);

            if(shape.equals("Kreis")){
                comp_container.add(new Kreis(new Vector2D(element_center_x_1, element_center_y_1), element_radius_small, false, offset_canvas_colors.get(0), offset_buffer_colors.get(0)));
                comp_container.add(new Kreis(new Vector2D(element_center_x_2, element_center_y_2), element_radius_big, false, offset_canvas_colors.get(1), offset_buffer_colors.get(1)));
            }
            else if(shape.equals("Quadrat")){
                comp_container.add(new Quadrat(new Vector2D(element_center_x_1, element_center_y_1), element_radius_small, false, offset_canvas_colors.get(0), offset_buffer_colors.get(0)));
                comp_container.add(new Quadrat(new Vector2D(element_center_x_2, element_center_y_2), element_radius_big, false, offset_canvas_colors.get(1), offset_buffer_colors.get(1)));
            }
            else if(shape.equals("FraktalDreieck")){
                comp_container.add(new FraktalDreieck(new Vector2D(element_center_x_1, element_center_y_1), element_radius_small, false, 3, random_rotation * i * angle, offset_canvas_colors.get(0), offset_buffer_colors.get(0)));
                comp_container.add(new FraktalDreieck(new Vector2D(element_center_x_2, element_center_y_2), element_radius_big, false, 3, random_rotation * i * angle, offset_canvas_colors.get(1), offset_buffer_colors.get(1)));
            }
            else if(shape.equals("FraktalKreis")){
                comp_container.add(new FraktalKreis(new Vector2D(element_center_x_1, element_center_y_1), element_radius_small, false, 3, offset_canvas_colors.get(0), offset_buffer_colors.get(0)));
                comp_container.add(new FraktalKreis(new Vector2D(element_center_x_2, element_center_y_2), element_radius_big, false, 3 , offset_canvas_colors.get(1), offset_buffer_colors.get(1)));
            }
            else if(shape.equals("FraktalQuadrat")){
                comp_container.add(new FraktalQuadrat(new Vector2D(element_center_x_1, element_center_y_1), element_radius_small, false, 3, offset_canvas_colors.get(0), offset_buffer_colors.get(0)));
                comp_container.add(new FraktalQuadrat(new Vector2D(element_center_x_2, element_center_y_2), element_radius_big, false, 3, offset_canvas_colors.get(1), offset_buffer_colors.get(1)));
            }
        }

        //es wird zufällig entschieden, ob das Mandala ein Zentrum haben soll
        double num = Math.random();
        if(num < 0.5 ){
            random_center(num_segments, shape);
        }
    }


    public void generate_02(int num_segments, String shape){
        double radius_1 = diameter/2 * 0.25f;
        double radius_2 = diameter/2 * 0.45f;
        double radius_3 = diameter/2 * 0.75f;

        double angle = (double)(2* Math.PI/ num_segments);
        double element_radius_1 = diameter/2 * 0.05f;
        double element_radius_2 = diameter/2 * 0.1f;
        double element_radius_3 = diameter/2 * 0.15f;
        double element_center_x_1, element_center_x_2, element_center_x_3;
        double element_center_y_1, element_center_y_2, element_center_y_3;

        double random_rotation = Math.random();

        for(int i = 0; i < num_segments; i++){
            element_center_x_1 = center.getX() + (double)(Math.cos((i * angle))* radius_1);
            element_center_y_1 = center.getY() + (double)(Math.sin((i * angle))* radius_1);

            element_center_x_2 = center.getX() + (double)(Math.cos((i * angle))* radius_2);
            element_center_y_2 = center.getY() + (double)(Math.sin((i * angle))* radius_2);

            element_center_x_3 = center.getX() + (double)(Math.cos((i * angle))* radius_3);
            element_center_y_3 = center.getY() + (double)(Math.sin((i * angle))* radius_3);

            if(shape.equals("Kreis")){
                comp_container.add(new Kreis(new Vector2D(element_center_x_1, element_center_y_1), element_radius_1, false, offset_canvas_colors.get(0), offset_buffer_colors.get(0)));
                comp_container.add(new Kreis(new Vector2D(element_center_x_2, element_center_y_2), element_radius_2, false, offset_canvas_colors.get(1), offset_buffer_colors.get(1)));
                comp_container.add(new Kreis(new Vector2D(element_center_x_3, element_center_y_3), element_radius_3, false, offset_canvas_colors.get(2), offset_buffer_colors.get(2)));
            }
            else if(shape.equals("Quadrat")){
                comp_container.add(new Quadrat(new Vector2D(element_center_x_1, element_center_y_1), element_radius_1, false, offset_canvas_colors.get(0), offset_buffer_colors.get(0)));
                comp_container.add(new Quadrat(new Vector2D(element_center_x_2, element_center_y_2), element_radius_2, false, offset_canvas_colors.get(1), offset_buffer_colors.get(1)));
                comp_container.add(new Quadrat(new Vector2D(element_center_x_3, element_center_y_3), element_radius_3, false, offset_canvas_colors.get(2), offset_buffer_colors.get(2)));
            }
            else if(shape.equals("FraktalDreieck")){
                comp_container.add(new FraktalDreieck(new Vector2D(element_center_x_1, element_center_y_1), element_radius_1, false, 3, random_rotation * i * angle, offset_canvas_colors.get(0), offset_buffer_colors.get(0)));
                comp_container.add(new FraktalDreieck(new Vector2D(element_center_x_2, element_center_y_2), element_radius_2, true,  3,  random_rotation * i * angle, offset_canvas_colors.get(1), offset_buffer_colors.get(1)));
                comp_container.add(new FraktalDreieck(new Vector2D(element_center_x_3, element_center_y_3), element_radius_3, true,3, random_rotation * i * angle, offset_canvas_colors.get(2), offset_buffer_colors.get(2)));
            }
            else if(shape.equals("FraktalKreis")){
                comp_container.add(new FraktalKreis(new Vector2D(element_center_x_1, element_center_y_1), element_radius_1, false, 3, offset_canvas_colors.get(0), offset_buffer_colors.get(0)));
                comp_container.add(new FraktalKreis(new Vector2D(element_center_x_2, element_center_y_2), element_radius_2, false, 3, offset_canvas_colors.get(1), offset_buffer_colors.get(1)));
                comp_container.add(new FraktalKreis(new Vector2D(element_center_x_3, element_center_y_3), element_radius_3, false, 3, offset_canvas_colors.get(2), offset_buffer_colors.get(2)));
            }
            else if(shape.equals("FraktalQuadrat")){
                comp_container.add(new FraktalQuadrat(new Vector2D(element_center_x_1, element_center_y_1), element_radius_1, false, 3, offset_canvas_colors.get(0), offset_buffer_colors.get(0)));
                comp_container.add(new FraktalQuadrat(new Vector2D(element_center_x_2, element_center_y_2), element_radius_2, false, 3, offset_canvas_colors.get(1), offset_buffer_colors.get(1)));
                comp_container.add(new FraktalQuadrat(new Vector2D(element_center_x_3, element_center_y_3), element_radius_3, false, 3, offset_canvas_colors.get(2), offset_buffer_colors.get(2)));
            }
        }

        //es wird zufällig entschieden, ob das Mandala ein Zentrum haben soll
        double num = Math.random();
        if(num < 0.5 ){
            random_center(num_segments, shape);
        }
    }

    public void generate_03(int num_segments, String shape){
        double radius_big = diameter/2 * 0.65f;

        double angle = (double)(2* Math.PI/ num_segments);
        double element_radius_small = diameter/2 * 0.05f;
        double element_radius_big = diameter/2 * 0.15f;
        double element_center_x_1, element_center_x_2, element_center_x_3, element_center_x_4;
        double element_center_y_1, element_center_y_2, element_center_y_3, element_center_y_4;

        double random_rotation = Math.random();

        for(int i = 0; i < num_segments; i++){
            element_center_x_1 = center.getX() + (double)(Math.cos((i * angle))* radius_big);
            element_center_y_1 = center.getY() + (double)(Math.sin((i * angle))* radius_big);

            element_center_x_2 = center.getX() + (double)(Math.cos((i * angle))* (diameter/2 *0.9f));
            element_center_y_2 = center.getY() + (double)(Math.sin((i * angle))* (diameter/2 *0.9f));

            element_center_x_3 = center.getX() + (double)(Math.cos((i * angle))* (diameter/2 * 0.25f));
            element_center_y_3 = center.getY() + (double)(Math.sin((i * angle))* (diameter/2 * 0.25f));

            element_center_x_4 = center.getX() + (double)(Math.cos((i * angle))* (diameter/2 * 0.4f));
            element_center_y_4 = center.getY() + (double)(Math.sin((i * angle))* (diameter/2 * 0.4f));

            if(shape.equals("Kreis")){
                comp_container.add(new Kreis(new Vector2D(element_center_x_1, element_center_y_1), element_radius_big, false, offset_canvas_colors.get(0), offset_buffer_colors.get(0)));
                comp_container.add(new Kreis(new Vector2D(element_center_x_2, element_center_y_2), element_radius_small, false, offset_canvas_colors.get(1), offset_buffer_colors.get(1)));
                comp_container.add(new Kreis(new Vector2D(element_center_x_3, element_center_y_3), element_radius_small, false, offset_canvas_colors.get(2), offset_buffer_colors.get(2)));
                comp_container.add(new Kreis(new Vector2D(element_center_x_4, element_center_y_4), element_radius_small, false, offset_canvas_colors.get(3), offset_buffer_colors.get(3)));
            }
            else if(shape.equals("Quadrat")){
                comp_container.add(new Quadrat(new Vector2D(element_center_x_1, element_center_y_1), element_radius_big, false, offset_canvas_colors.get(0), offset_buffer_colors.get(0)));
                comp_container.add(new Quadrat(new Vector2D(element_center_x_2, element_center_y_2), element_radius_small, false, offset_canvas_colors.get(1), offset_buffer_colors.get(1)));
                comp_container.add(new Quadrat(new Vector2D(element_center_x_3, element_center_y_3), element_radius_small, false, offset_canvas_colors.get(2), offset_buffer_colors.get(2)));
                comp_container.add(new Quadrat(new Vector2D(element_center_x_4, element_center_y_4), element_radius_small, false, offset_canvas_colors.get(3), offset_buffer_colors.get(3)));
            }
            else if(shape.equals("FraktalDreieck")){
                comp_container.add(new FraktalDreieck(new Vector2D(element_center_x_1, element_center_y_1), element_radius_big, false, 3, i * angle, offset_canvas_colors.get(0), offset_buffer_colors.get(0)));
                comp_container.add(new FraktalDreieck(new Vector2D(element_center_x_2, element_center_y_2), element_radius_small, false,  3, random_rotation * i * angle, offset_canvas_colors.get(1), offset_buffer_colors.get(1)));
                comp_container.add(new FraktalDreieck(new Vector2D(element_center_x_3, element_center_y_3), element_radius_small, false,3, random_rotation * i * angle, offset_canvas_colors.get(2), offset_buffer_colors.get(2)));
                comp_container.add(new FraktalDreieck(new Vector2D(element_center_x_3, element_center_y_3), element_radius_small, false,3, random_rotation * i * angle, offset_canvas_colors.get(3), offset_buffer_colors.get(3)));
            }
            else if(shape.equals("FraktalKreis")){
                comp_container.add(new FraktalKreis(new Vector2D(element_center_x_1, element_center_y_1), element_radius_big, false, 3, offset_canvas_colors.get(0), offset_buffer_colors.get(0)));
                comp_container.add(new FraktalKreis(new Vector2D(element_center_x_2, element_center_y_2), element_radius_small, false, 3, offset_canvas_colors.get(1), offset_buffer_colors.get(1)));
                comp_container.add(new FraktalKreis(new Vector2D(element_center_x_3, element_center_y_3), element_radius_small, false, 3, offset_canvas_colors.get(2), offset_buffer_colors.get(2)));
                comp_container.add(new FraktalKreis(new Vector2D(element_center_x_4, element_center_y_4), element_radius_small, false, 3, offset_canvas_colors.get(3), offset_buffer_colors.get(3)));
            }
            else if(shape.equals("FraktalQuadrat")){
                comp_container.add(new FraktalQuadrat(new Vector2D(element_center_x_1, element_center_y_1), element_radius_big, false, 3, offset_canvas_colors.get(0), offset_buffer_colors.get(0)));
                comp_container.add(new FraktalQuadrat(new Vector2D(element_center_x_2, element_center_y_2), element_radius_small, false, 3, offset_canvas_colors.get(1), offset_buffer_colors.get(1)));
                comp_container.add(new FraktalQuadrat(new Vector2D(element_center_x_3, element_center_y_3), element_radius_small, false, 3, offset_canvas_colors.get(2), offset_buffer_colors.get(2)));
                comp_container.add(new FraktalQuadrat(new Vector2D(element_center_x_4, element_center_y_4), element_radius_small, false, 3, offset_canvas_colors.get(3), offset_buffer_colors.get(3)));
            }

        }

        //es wird zufällig entschieden, ob das Mandala ein Zentrum haben soll
        double num = Math.random();
        if(num < 0.5 ){
            random_center(num_segments, shape);
        }

    }
    public void generate_04(int num_segments, String shape){
        //float radius = diameter/2 * 0.333f;

        double radius = diameter/2 * 0.275f;

        double element_center_x;
        double element_center_y;
        double element_radius = diameter/2.0f * 0.6f;
        double angle = (float)(2* Math.PI/ num_segments);

        for (int i = 0 ; i < num_segments; i++){
            element_center_x = center.getX() + (float)(Math.cos(i * angle)* radius);
            element_center_y = center.getY() + (float)(Math.sin(i * angle)* radius);

            if(shape.equals("Kreis")){
                comp_container.add(new Kreis(new Vector2D(element_center_x, element_center_y), element_radius, true, offset_canvas_colors.get(0), offset_buffer_colors.get(0)));
            }
            else if(shape.equals("Quadrat")){
                comp_container.add(new Quadrat(new Vector2D(element_center_x, element_center_y), element_radius, true, offset_canvas_colors.get(0), offset_buffer_colors.get(0)));
            }
            else if(shape.equals("FraktalDreieck")){
                comp_container.add(new FraktalDreieck(new Vector2D(element_center_x, element_center_y), element_radius, true,3, i * angle, offset_canvas_colors.get(0), offset_buffer_colors.get(0)));
            }
            else if(shape.equals("FraktalKreis")){
                comp_container.add(new FraktalKreis(new Vector2D(element_center_x, element_center_y), element_radius, true, 3, offset_canvas_colors.get(0), offset_buffer_colors.get(0)));
            }
            else if(shape.equals("FraktalQuadrat")) {
                comp_container.add(new FraktalQuadrat(new Vector2D(element_center_x, element_center_y), element_radius, true, 3, offset_canvas_colors.get(0), offset_buffer_colors.get(0)));
            }
        }

        //es wird zufällig entschieden, ob das Mandala ein Zentrum haben soll
        double num = Math.random();
        if(num < 0.5 ){
            random_center(num_segments, shape);
        }
    }

    public void generate_05(int num_segments, String shape){
        double element_center_x;
        double element_center_y;
        double angle = (double)(2* Math.PI/ num_segments);
        double element_radius = diameter/2 * 0.45f;

        for (int i = 0 ; i < num_segments; i++){
            element_center_x = center.getX() + (double)(Math.cos(i * angle)* element_radius);
            element_center_y = center.getY() + (double)(Math.sin(i * angle)*element_radius);

            if(shape.equals("Kreis")){
                comp_container.add(new Kreis(new Vector2D(element_center_x, element_center_y), element_radius, true, offset_canvas_colors.get(0), offset_buffer_colors.get(0)));
            }
            else if(shape.equals("Quadrat")){
                comp_container.add(new Quadrat(new Vector2D(element_center_x, element_center_y), element_radius, true, offset_canvas_colors.get(0), offset_buffer_colors.get(0)));
            }
            else if(shape.equals("FraktalDreieck")){
                comp_container.add(new FraktalDreieck(new Vector2D(element_center_x, element_center_y), element_radius, true,3, i * angle, offset_canvas_colors.get(0), offset_buffer_colors.get(0)));
            }
            else if(shape.equals("FraktalKreis")){
                comp_container.add(new FraktalKreis(new Vector2D(element_center_x, element_center_y), element_radius, true, 3, offset_canvas_colors.get(0), offset_buffer_colors.get(0)));
            }
            else if(shape.equals("FraktalQuadrat")) {
                comp_container.add(new FraktalQuadrat(new Vector2D(element_center_x, element_center_y), element_radius, true, 3, offset_canvas_colors.get(0), offset_buffer_colors.get(0)));
            }
        }
    }

    //generate_11 bis 14 generieren verschiedene Bordüren

    public void generate_11(int num_segments, String shape){

        double radius = diameter/2 * 0.925f;
        double element_radius = diameter/2 * 0.025f;
        int num = (int)(Math.PI * radius / element_radius * 0.95f);
        double angle = (double)(2* Math.PI/ num);
        double element_center_x;
        double element_center_y;
        for(int i = 0; i < num; i++){
            element_center_x = center.getX() + (double)(Math.cos(i * angle)* radius);
            element_center_y = center.getY() + (double)(Math.sin(i * angle)* radius);

            if(shape.equals("Kreis")){
                comp_container.add(new Kreis(new Vector2D(element_center_x, element_center_y), element_radius, false, offset_canvas_colors.get(0), offset_buffer_colors.get(0)));
            }
            else if(shape.equals("Quadrat")){
                comp_container.add(new Quadrat(new Vector2D(element_center_x, element_center_y), element_radius, false, offset_canvas_colors.get(0), offset_buffer_colors.get(0)));
            }
            else if(shape.equals("FraktalDreieck")){
                comp_container.add(new FraktalDreieck(new Vector2D(element_center_x, element_center_y), element_radius, false,3, i * angle, offset_canvas_colors.get(0), offset_buffer_colors.get(0)));
            }
            else if(shape.equals("FraktalKreis")){
                comp_container.add(new FraktalKreis(new Vector2D(element_center_x, element_center_y), element_radius, false, 3, offset_canvas_colors.get(0), offset_buffer_colors.get(0)));
            }
            else if(shape.equals("FraktalQuadrat")) {
                comp_container.add(new FraktalQuadrat(new Vector2D(element_center_x, element_center_y), element_radius, false, 3, offset_canvas_colors.get(0), offset_buffer_colors.get(0)));
            }
        }
    }

    public void generate_12(int num_segments, String shape){
        double radius_small = diameter/2 * 0.9f;
        double radius_big = diameter/2 * 0.95f;

        comp_container.add(new Kreis(center, radius_big, true, canvas_background, buffer_background));
        comp_container.add(new Kreis(center, radius_small, true, canvas_background, buffer_background));

        generate_11(num_segments, shape);
    }

    public void generate_13(int num_segments, String shape){
        double radius_small = diameter/2 * 0.9f;
        double radius_mid = diameter/2 * 0.925f;
        double radius_big = diameter/2 * 0.95f;

        comp_container.add(new Kreis(center, radius_big, true, canvas_background, buffer_background));
        comp_container.add(new Kreis(center, radius_mid, true, canvas_background, buffer_background));
        comp_container.add(new Kreis(center, radius_small, true, canvas_background, buffer_background));

    }

    public void generate_14(int num_segments, String shape){
        double radius = diameter/2 * 0.85f;

        double angle = (double)(2* Math.PI/ num_segments);
        double element_radius = diameter/2 * 0.1f;
        double element_center_x;
        double element_center_y;

        for (int i = 0 ; i < num_segments; i++){
            element_center_x = center.getX() + (double)(Math.cos((i * angle) + (0.5f * angle))* radius);
            element_center_y = center.getY() + (double)(Math.sin((i * angle) + (0.5f * angle))* radius);

            if(shape.equals("Kreis")){
                comp_container.add(new Kreis(new Vector2D(element_center_x, element_center_y), element_radius, true, canvas_background, buffer_background));
            }
            else if(shape.equals("Quadrat")){
                comp_container.add(new Quadrat(new Vector2D(element_center_x, element_center_y), element_radius, true, canvas_background, buffer_background));
            }
            else if(shape.equals("FraktalDreieck")){
                comp_container.add(new FraktalDreieck(new Vector2D(element_center_x, element_center_y), element_radius, true,3, i * angle, canvas_background, buffer_background));
            }
            else if(shape.equals("FraktalKreis")){
                comp_container.add(new FraktalKreis(new Vector2D(element_center_x, element_center_y), element_radius, true, 3, canvas_background, buffer_background));
            }
            else if(shape.equals("FraktalQuadrat")) {
                comp_container.add(new FraktalQuadrat(new Vector2D(element_center_x, element_center_y), element_radius, true, 3, canvas_background, buffer_background));
            }
        }
    }

    //generate_21 bis 22 erzeugen verschiedene Zentren

    public void generate_21(int num_segments, String shape){
        if(shape.equals("Kreis")){
            comp_container.add(new Kreis(center, diameter/2 * 0.075f, true, canvas_background, buffer_background));
        }
        else if(shape.equals("Quadrat")){
            comp_container.add(new Quadrat(center, diameter/2 * 0.075f, true, canvas_background, buffer_background));
        }
    }

    public void generate_22(int num_segments, String shape){
        if(shape.equals("Kreis")){
            comp_container.add(new Kreis(center, diameter/2 * 0.075f, true, canvas_background, buffer_background));
            comp_container.add(new Kreis(center, diameter/2 * 0.1f, true, canvas_background, buffer_background));
            comp_container.add(new Kreis(center, diameter/2 * 0.125f, true, canvas_background, buffer_background));
        }
        else if(shape.equals("Quadrat")){
            comp_container.add(new Quadrat(center, diameter/2 * 0.075f, true, canvas_background, buffer_background));
            comp_container.add(new Quadrat(center, diameter/2 * 0.1f, true, canvas_background, buffer_background));
            comp_container.add(new Quadrat(center, diameter/2 * 0.125f, true, canvas_background, buffer_background));
        }
    }

    public Color offset_canvas_color(Color color){
        double h = color.getHue();
        double s = color.getSaturation();
        double b = color.getBrightness();

        double degrees = Math.random() * 30;
        //adjust the hue by random amount
        h = (h + degrees) % 1.0;

        return Color.hsb(h * 360, s, b);
    }



    //alle Elemente des Composites werden auf dem GraphicsContext für den Canvas gezeichnet
    public void print(GraphicsContext picture){
        for (Composite it: comp_container){
            it.print(picture);
        }
    }

    //alle Elemente des Composites werden auf dem GraphicsContext, der später gespeichert wird, gezeichnet
    public void save(Graphics2D gc_buffer){
        for (Composite it: comp_container){
            it.save(gc_buffer);
        }

    }

    /*
    public void add(Composite object){
            comp_container.add(object);
    }
     */


    //alle Elemente werden aus dem Composite entfernt
    public void clear(){
        comp_container.clear();
    }

}

