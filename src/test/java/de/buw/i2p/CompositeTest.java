package de.buw.i2p;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import javafx.scene.paint.Color;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.awt.*;
class CompositeTest {

	@Test
    void testDefaultConstructor() {
        Composite composite = new Composite();
        assertNotNull(composite);
        assertNotNull(composite.comp_container);
        assertEquals(0, composite.comp_container.size());
    }

	@Test
	void testParameterizedConstructor() {
	    Vector2D center = new Vector2D(0, 0);
	    double diameter = 10.0;
	    Color canvasBackground = Color.WHITE;
	    java.awt.Color bufferBackground = new java.awt.Color(255, 0, 0);

	    Composite composite = new Composite(center, diameter, canvasBackground, bufferBackground);
	    assertNotNull(composite);
	    assertEquals(center, composite.center);
	    assertEquals(diameter, composite.diameter);
	    assertEquals(canvasBackground, composite.canvas_background);
	    assertEquals(bufferBackground, composite.buffer_background);
	    assertNotNull(composite);
	    assertEquals(0, composite.comp_container.size());
	}
}
