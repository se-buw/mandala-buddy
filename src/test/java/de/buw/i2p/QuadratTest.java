package de.buw.i2p;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import java.awt.*;
import java.awt.image.BufferedImage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

class QuadratTest {

    @Test
    void testPrintTransparent() {
    	 Vector2D center = new Vector2D(0, 0);
         double radius = 5;
         boolean transparent = true;
         Color canvasBackground = Color.WHITE;
         java.awt.Color bufferBackground = java.awt.Color.BLACK;

         Quadrat quadrat = new Quadrat(center, radius, transparent, canvasBackground, bufferBackground);

         Canvas canvas = new Canvas(400, 400);

         // Get the GraphicsContext associated with the Canvas
         GraphicsContext picture = canvas.getGraphicsContext2D();
         picture.setFill(Color.AQUAMARINE);
         quadrat.print(picture);
         
        // Assertions
        assertEquals(Color.BLACK,picture.getStroke());
        assertEquals(Color.AQUAMARINE,picture.getFill());

        // You can add more assertions based on your requirements
    }
    @Test
    void testPrintFilled() {
    	 Vector2D center = new Vector2D(0, 0);
    	    double radius = 5;
    	    boolean transparent = false;
    	    Color canvasBackground = Color.WHITE;
    	    java.awt.Color bufferBackground = java.awt.Color.BLACK;

    	    Quadrat quadrat = new Quadrat(center, radius, transparent, canvasBackground, bufferBackground);

    	    Canvas canvas = new Canvas(400, 400);

    	    // Get the GraphicsContext associated with the Canvas
    	    GraphicsContext picture = canvas.getGraphicsContext2D();
    	    quadrat.print(picture);

    	    // Assertions
    	    assertEquals(Color.BLACK, picture.getStroke());
    	    assertEquals(Color.WHITE, picture.getFill());
    }
    @Test
    void testSaveTransparentQuadrat() {
        Vector2D center = new Vector2D(0, 0);
        double radius = 5;
        boolean transparent = true;
        Color canvasBackground = Color.WHITE;
        java.awt.Color bufferBackground = java.awt.Color.BLACK;

        Quadrat quadrat = new Quadrat(center, radius, transparent, canvasBackground, bufferBackground);

        BufferedImage bufferedImage = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gcBuffer = bufferedImage.createGraphics();

        quadrat.save(gcBuffer);

        // Verify that the correct methods were called on the Graphics2D
        assertEquals(java.awt.Color.BLACK, gcBuffer.getColor());

        // You can add more assertions based on your requirements
    }
    @Test
    void testSaveFilledQuadrat() {
        Vector2D center = new Vector2D(0, 0);
        double radius = 5;
        boolean transparent = false;
        Color canvasBackground = Color.WHITE;
        java.awt.Color bufferBackground = java.awt.Color.BLACK;

        Quadrat quadrat = new Quadrat(center, radius, transparent, canvasBackground, bufferBackground);

        BufferedImage bufferedImage = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gcBuffer = bufferedImage.createGraphics();

        quadrat.save(gcBuffer);

        // Verify that the correct methods were called on the Graphics2D
        assertEquals(java.awt.Color.BLACK, gcBuffer.getColor());

        // You can add more assertions based on your requirements
    }
}

