import agh.ics.oop.Vector2d;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {

    Vector2d v1 = new Vector2d(0,0);
    Vector2d v2 = new Vector2d(1,10);
    Vector2d v3 = new Vector2d(1,10);
    Vector2d v4 = new Vector2d(-2,0);
    Vector2d v5 = new Vector2d(0,-5);
    Vector2d v6 = new Vector2d(5,10);

    @Test
    void Vector2dEqualsTest(){
        assertNotEquals(v1, v2);
        assertEquals(v2, v3);
        assertNotEquals(v1, v3);
        assertEquals(v4, v4);
    }

    @Test
    void Vector2dToStringTest(){
        assertEquals("(0,0)", v1.toString());
        assertEquals("(1,10)", v2.toString());
        assertEquals("(-2,0)", v4.toString());
    }

    @Test
    void Vector2dPrecedesTest(){
        assertTrue(v2.precedes(v2));
        assertTrue(v1.precedes(v2));
        assertTrue(v4.precedes(v2));
        assertFalse(v5.precedes(v4));
        assertFalse(v2.precedes(v4));
        assertTrue(v3.precedes(v6));
    }

    @Test
    void Vector2dFollowsTest(){
        assertTrue(v2.follows(v2));
        assertFalse(v1.follows(v2));
        assertFalse(v4.follows(v2));
        assertFalse(v5.follows(v4));
        assertTrue(v2.follows(v4));
        assertFalse(v3.follows(v6));
    }

    @Test
    void Vector2dUpperRightTest(){
        assertEquals(new Vector2d(0,0), v1.upperRight(v5));
        assertEquals(new Vector2d(0,0), v5.upperRight(v1));
        assertEquals(new Vector2d(1,10),v1.upperRight(v3));
        assertEquals(new Vector2d(0,0),v4.upperRight(v5));
    }

    @Test
    void Vector2dLowerLeftTest(){
        assertEquals(new Vector2d(0,-5), v1.lowerLeft(v5));
        assertEquals(new Vector2d(0,-5), v5.lowerLeft(v1));
        assertEquals(new Vector2d(0,0),v1.lowerLeft(v3));
        assertEquals(new Vector2d(-2,-5),v4.lowerLeft(v5));
    }

    @Test
    void Vector2dAddTest(){
        assertEquals(new Vector2d(0,-5), v1.add(v5));
        assertEquals(new Vector2d(0,-5), v5.add(v1));
        assertEquals(new Vector2d(1,10),v1.add(v3));
        assertEquals(new Vector2d(-2,-5),v4.add(v5));
    }

    @Test
    void Vector2dSubtractTest(){
        assertEquals(new Vector2d(0,5), v1.substract(v5));
        assertEquals(new Vector2d(0,-5), v5.substract(v1));
        assertEquals(new Vector2d(-1,-10),v1.substract(v3));
        assertEquals(new Vector2d(-2,5),v4.substract(v5));
    }

    @Test
    void Vector2dOppositeTest(){
        assertEquals(new Vector2d(0,0), v1.opposite());
        assertEquals(new Vector2d(-1,-10), v2.opposite());
        assertEquals(new Vector2d(-1,-10),v3.opposite());
        assertEquals(new Vector2d(2,0),v4.opposite());
        assertEquals(new Vector2d(0,5),v5.opposite());
        assertEquals(new Vector2d(-5,-10),v6.opposite());
    }

}
