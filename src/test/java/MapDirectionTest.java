import agh.ics.oop.MapDirection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapDirectionTest {

    //assertEquals(a, b) - weryfikuje czy obiekty a i b są sobie równe (korzystając z metody equals),
    //assertTrue(a) - weryfikuje czy wartość logiczna a jest prawdą,
    //assertFalse(a)
    @Test
    void nextDirectionTest()
    {
        MapDirection vt = MapDirection.NORTH;
        vt = vt.next();
        assertEquals(vt,MapDirection.EAST);
        vt = vt.next();
        assertEquals(vt,MapDirection.SOUTH);
        vt = vt.next();
        assertEquals(vt,MapDirection.WEST);
        vt = vt.next();
        assertEquals(vt,MapDirection.NORTH);

    }
    @Test
    void prevDirectionTest()
    {
        MapDirection vt = MapDirection.NORTH;
        vt = vt.previous();
        assertEquals(vt,MapDirection.WEST);
        vt = vt.previous();
        assertEquals(vt,MapDirection.SOUTH);
        vt = vt.previous();
        assertEquals(vt,MapDirection.EAST);
        vt = vt.previous();
        assertEquals(vt,MapDirection.NORTH);

    }
}
