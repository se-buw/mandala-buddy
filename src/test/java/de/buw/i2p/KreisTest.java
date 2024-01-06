package de.buw.i2p;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import java.awt.image.BufferedImage;
import javafx.scene.paint.Color;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;


class KreisTest {

	   @Test
	    void testPrintTransparentKreis() {
	        Vector2D center = new Vector2D(0, 0);
	        double radius = 5;
	        boolean transparent = true;
	        Color canvasBackground = Color.WHITE;
	        java.awt.Color bufferBackground = java.awt.Color.BLACK;

	        Kreis kreis = new Kreis(center, radius, transparent, canvasBackground, bufferBackground);

	        Canvas canvas = new Canvas(400, 400);

	        // Get the GraphicsContext associated with the Canvas
	        GraphicsContext gcCanvas = canvas.getGraphicsContext2D();
	        kreis.print(gcCanvas);

	        // Assertions
	        assertEquals(Color.BLACK, gcCanvas.getStroke());
	        

	        // You can add more assertions based on your requirements
	    }
	   @Test
	    void testPrintFilledKreis() {
	        Vector2D center = new Vector2D(0, 0);
	        double radius = 5;
	        boolean transparent = false;
	        Color canvasBackground = Color.WHITE;
	        java.awt.Color bufferBackground = java.awt.Color.BLACK;

	        Kreis kreis = new Kreis(center, radius, transparent, canvasBackground, bufferBackground);

	        Canvas canvas = new Canvas(400, 400);

	        // Get the GraphicsContext associated with the Canvas
	        GraphicsContext gcCanvas = canvas.getGraphicsContext2D();
	        kreis.print(gcCanvas);

	        // Assertions
	        assertEquals(Color.BLACK, gcCanvas.getStroke());
	        assertEquals(Color.WHITE, gcCanvas.getFill());

	        // You can add more assertions based on your requirements
	    }
	   

}
