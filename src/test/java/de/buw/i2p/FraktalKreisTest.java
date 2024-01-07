package de.buw.i2p;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javafx.scene.paint.Color;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;


class FraktalKreisTest {

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
	        gcCanvas.setFill(Color.AQUA);
	        kreis.print(gcCanvas);

	        // Assertions
	        assertEquals(Color.BLACK, gcCanvas.getStroke());
	        assertEquals(Color.AQUA, gcCanvas.getFill());
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
	    }

	    @Test
	    void testSaveTransparentKreis() {
	        Vector2D center = new Vector2D(0, 0);
	        double radius = 5;
	        boolean transparent = true;
	        Color canvasBackground = Color.WHITE;
	        java.awt.Color bufferBackground = java.awt.Color.BLACK;

	        Kreis kreis = new Kreis(center, radius, transparent, canvasBackground, bufferBackground);

	        BufferedImage bufferedImage = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
	        Graphics2D gcBuffer = bufferedImage.createGraphics();

	        kreis.save(gcBuffer);

	        // Verify that the correct methods were called on the Graphics2D
	        assertEquals(java.awt.Color.BLACK, gcBuffer.getColor());
	    }

	    @Test
	    void testSaveFilledKreis() {
	        Vector2D center = new Vector2D(0, 0);
	        double radius = 5;
	        boolean transparent = false;
	        Color canvasBackground = Color.WHITE;
	        java.awt.Color bufferBackground = java.awt.Color.BLACK;

	        Kreis kreis = new Kreis(center, radius, transparent, canvasBackground, bufferBackground);

	        BufferedImage bufferedImage = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
	        Graphics2D gcBuffer = bufferedImage.createGraphics();

	        kreis.save(gcBuffer);

	        // Verify that the correct methods were called on the Graphics2D
	        assertEquals(java.awt.Color.BLACK, gcBuffer.getColor());
	    }

}
