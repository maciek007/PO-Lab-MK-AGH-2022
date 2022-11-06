import agh.ics.oop.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AbstractMapTest {

    @Test
    public void ObjectAtTest() {
        IWorldMap map = new RectangularMap(5, 5);
        Animal a = new Animal(map, new Vector2d(2, 0));
        map.place(a);
        map.place(new Animal(map, new Vector2d(3,2)));
        assertEquals(a,map.objectAt(new Vector2d(2,0)));
        assertNull(map.objectAt(new Vector2d(0, 0)));

        map = new GrassField(0);
        map.place(a);
        map.place(new Animal(map, new Vector2d(3,2)));
        assertEquals(a,map.objectAt(new Vector2d(2,0)));
        assertNull(map.objectAt(new Vector2d(0, 0)));
    }
    @Test
    public void isOccupiedTest() {
        IWorldMap map = new RectangularMap(5, 5);
        Animal a = new Animal(map, new Vector2d(2, 0));
        map.place(a);
        map.place(new Animal(map, new Vector2d(3,2)));
        assertTrue(map.isOccupied(new Vector2d(3,2)));
        assertFalse(map.isOccupied(new Vector2d(2,2)));

        map = new GrassField(0);
        map.place(a);
        map.place(new Animal(map, new Vector2d(3,2)));
        assertTrue(map.isOccupied(new Vector2d(3,2)));
        assertFalse(map.isOccupied(new Vector2d(2,2)));
    }
    @Test
    public void canMoveToTest()
    {
        IWorldMap map = new RectangularMap(5, 5);
        Animal a = new Animal(map, new Vector2d(2, 0));
        map.place(a);
        map.place(new Animal(map, new Vector2d(3,2)));
        assertFalse(map.canMoveTo(new Vector2d(3,2)));
        assertTrue(map.canMoveTo(new Vector2d(2,2)));
        assertFalse(map.canMoveTo(new Vector2d(5,5)));
        assertFalse(map.canMoveTo(new Vector2d(0,-1)));

        map = new GrassField(0);
        map.place(a);
        map.place(new Animal(map, new Vector2d(3,2)));
        assertFalse(map.canMoveTo(new Vector2d(3,2)));
        assertTrue(map.canMoveTo(new Vector2d(2,2)));
    }

}
