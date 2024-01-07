package de.buw.i2p;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import java.awt.*;
import java.awt.image.BufferedImage;

class MandalaTest {

    @Test
    void testGenerateAndPrintMandala() {
        Mandala mandala = new Mandala();
        Canvas canvas = new Canvas(800, 800);
        GraphicsContext gcCanvas = canvas.getGraphicsContext2D();

        int numSegments = 6; // Adjust as needed
        String shape = "circle"; // Adjust as needed
        Color backgroundColor = Color.WHITE;

        mandala.generate(gcCanvas, numSegments, shape, backgroundColor);

        // Verify that the shapes were generated and printed on the GraphicsContext
        assertNotNull(mandala.shapes);
    }

    @Test
    void testSaveMandala() {
        Mandala mandala = new Mandala();

        BufferedImage bufferedImage = new BufferedImage(800, 800, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gcBuffer = bufferedImage.createGraphics();

        mandala.save(gcBuffer);

        // Verify that the save function did not throw any exceptions
        assertNotNull(mandala.shapes);
    }
    @Test
    void testClearMandala() {
        Mandala mandala = new Mandala();
        int numSegments = 6;
        String shape = "Quadrat";
        Color backgroundColor = Color.WHITE;

        mandala.generate(new Canvas(700, 700).getGraphicsContext2D(), numSegments, shape, backgroundColor);

        // Verify that the shapes were added to the Composite
        assertFalse(mandala.shapes.comp_container.isEmpty());

        // Clear the Mandala
        mandala.clear();

        // Verify that the Composite is empty after clearing
        assertTrue(mandala.shapes.comp_container.isEmpty());
    }
} 
