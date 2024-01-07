package de.buw.i2p;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javafx.scene.paint.Color;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
class FraktalDreieckTest {

	@Test
    void testPrintTransparentDreieck() {
        Vector2D corner0 = new Vector2D(0, 0);
        Vector2D corner1 = new Vector2D(10, 0);
        Vector2D corner2 = new Vector2D(5, 8.66);
        boolean transparent = true;
        Color canvasBackground = Color.WHITE;
        java.awt.Color bufferBackground = java.awt.Color.BLACK;

        FraktalDreieck dreieck = new FraktalDreieck(corner0, corner1, corner2, transparent, 2, canvasBackground, bufferBackground);

        Canvas canvas = new Canvas(400, 400);

        // Get the GraphicsContext associated with the Canvas
        GraphicsContext gcCanvas = canvas.getGraphicsContext2D();
        gcCanvas.setFill(Color.AQUA);
        dreieck.print(gcCanvas);

        // Assertions
        assertEquals(Color.BLACK, gcCanvas.getStroke());
        assertEquals(Color.AQUA, gcCanvas.getFill());
    }

    @Test
    void testPrintFilledDreieck() {
        Vector2D corner0 = new Vector2D(0, 0);
        Vector2D corner1 = new Vector2D(10, 0);
        Vector2D corner2 = new Vector2D(5, 8.66);
        boolean transparent = false;
        Color canvasBackground = Color.WHITE;
        java.awt.Color bufferBackground = java.awt.Color.BLACK;

        FraktalDreieck dreieck = new FraktalDreieck(corner0, corner1, corner2, transparent, 2, canvasBackground, bufferBackground);

        Canvas canvas = new Canvas(400, 400);

        // Get the GraphicsContext associated with the Canvas
        GraphicsContext gcCanvas = canvas.getGraphicsContext2D();
        dreieck.print(gcCanvas);

        // Assertions
        assertEquals(Color.BLACK, gcCanvas.getStroke());
        assertEquals(Color.WHITE, gcCanvas.getFill());
    }

    @Test
    void testSaveTransparentDreieck() {
        Vector2D corner0 = new Vector2D(0, 0);
        Vector2D corner1 = new Vector2D(10, 0);
        Vector2D corner2 = new Vector2D(5, 8.66);
        boolean transparent = true;
        Color canvasBackground = Color.WHITE;
        java.awt.Color bufferBackground = java.awt.Color.BLACK;

        FraktalDreieck dreieck = new FraktalDreieck(corner0, corner1, corner2, transparent, 2, canvasBackground, bufferBackground);

        BufferedImage bufferedImage = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gcBuffer = bufferedImage.createGraphics();
        gcBuffer.setColor(java.awt.Color.BLUE);
        dreieck.save(gcBuffer);

        // Verify that the correct methods were called on the Graphics2D
        assertEquals(java.awt.Color.BLUE, gcBuffer.getColor());
    }

    @Test
    void testSaveFilledDreieck() {
        Vector2D corner0 = new Vector2D(0, 0);
        Vector2D corner1 = new Vector2D(10, 0);
        Vector2D corner2 = new Vector2D(5, 8.66);
        boolean transparent = false;
        Color canvasBackground = Color.WHITE;
        java.awt.Color bufferBackground = java.awt.Color.BLACK;

        FraktalDreieck dreieck = new FraktalDreieck(corner0, corner1, corner2, transparent, 2, canvasBackground, bufferBackground);

        BufferedImage bufferedImage = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gcBuffer = bufferedImage.createGraphics();

        dreieck.save(gcBuffer);

        // Verify that the correct methods were called on the Graphics2D
        assertEquals(java.awt.Color.BLACK, gcBuffer.getColor());
    }

}
