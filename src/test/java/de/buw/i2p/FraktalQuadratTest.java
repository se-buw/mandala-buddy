package de.buw.i2p;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javafx.scene.paint.Color;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
class FraktalQuadratTest {

	 @Test
	    void testPrintTransparentQuadrat() {
	        Vector2D center = new Vector2D(0, 0);
	        double radius = 5;
	        boolean transparent = true;
	        Color canvasBackground = Color.WHITE;
	        java.awt.Color bufferBackground = java.awt.Color.BLACK;

	        FraktalQuadrat quadrat = new FraktalQuadrat(center, radius, transparent, 2, canvasBackground, bufferBackground);

	        Canvas canvas = new Canvas(400, 400);

	        // Get the GraphicsContext associated with the Canvas
	        GraphicsContext gcCanvas = canvas.getGraphicsContext2D();
	        gcCanvas.setFill(Color.AQUA);
	        quadrat.print(gcCanvas);

	        // Assertions
	        assertEquals(Color.BLACK, gcCanvas.getStroke());
	        assertEquals(Color.AQUA, gcCanvas.getFill());
	    }

	    @Test
	    void testPrintFilledQuadrat() {
	        Vector2D center = new Vector2D(0, 0);
	        double radius = 5;
	        boolean transparent = false;
	        Color canvasBackground = Color.WHITE;
	        java.awt.Color bufferBackground = java.awt.Color.BLACK;

	        FraktalQuadrat quadrat = new FraktalQuadrat(center, radius, transparent, 2, canvasBackground, bufferBackground);

	        Canvas canvas = new Canvas(400, 400);

	        // Get the GraphicsContext associated with the Canvas
	        GraphicsContext gcCanvas = canvas.getGraphicsContext2D();
	        quadrat.print(gcCanvas);

	        // Assertions
	        assertEquals(Color.BLACK, gcCanvas.getStroke());
	        assertEquals(Color.WHITE, gcCanvas.getFill());
	    }

	    @Test
	    void testSaveTransparentQuadrat() {
	        Vector2D center = new Vector2D(0, 0);
	        double radius = 5;
	        boolean transparent = true;
	        Color canvasBackground = Color.WHITE;
	        java.awt.Color bufferBackground = java.awt.Color.BLACK;

	        FraktalQuadrat quadrat = new FraktalQuadrat(center, radius, transparent, 2, canvasBackground, bufferBackground);

	        BufferedImage bufferedImage = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
	        Graphics2D gcBuffer = bufferedImage.createGraphics();

	        quadrat.save(gcBuffer);

	        // Verify that the correct methods were called on the Graphics2D
	        assertEquals(java.awt.Color.BLACK, gcBuffer.getColor());
	    }

	    @Test
	    void testSaveFilledQuadrat() {
	        Vector2D center = new Vector2D(0, 0);
	        double radius = 5;
	        boolean transparent = false;
	        Color canvasBackground = Color.WHITE;
	        java.awt.Color bufferBackground = java.awt.Color.BLACK;

	        FraktalQuadrat quadrat = new FraktalQuadrat(center, radius, transparent, 2, canvasBackground, bufferBackground);

	        BufferedImage bufferedImage = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
	        Graphics2D gcBuffer = bufferedImage.createGraphics();

	        quadrat.save(gcBuffer);

	        // Verify that the correct methods were called on the Graphics2D
	        assertEquals(java.awt.Color.BLACK, gcBuffer.getColor());
	    }

}
