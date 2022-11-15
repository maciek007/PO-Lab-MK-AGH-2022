import agh.ics.oop.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GrassFieldTest {

    @Test
    void placeNormalCaseTest()
    {
        IWorldMap map = new GrassField(0);
        map.place(new Animal(map,new Vector2d(0,0)));
        map.place(new Animal(map,new Vector2d(1,0)));
        map.place(new Animal(map,new Vector2d(0,1)));
        map.place(new Animal(map,new Vector2d(1,1)));
        map.place(new Animal(map,new Vector2d(2,1)));

    }

    @Test
    void placeExceptionCaseTest() {
        IllegalArgumentException t = assertThrows(IllegalArgumentException.class, () ->
        {
            IWorldMap map = new GrassField(0);
            map.place(new Animal(map, new Vector2d(0, 0)));
            map.place(new Animal(map, new Vector2d(1, 0)));
            map.place(new Animal(map, new Vector2d(0, 1)));
            map.place(new Animal(map, new Vector2d(1, 1)));
            map.place(new Animal(map, new Vector2d(1, 1)));
        });
        assertEquals("Nie można umieścić zwierzęcia na polu (1,1)",t.getMessage());

    }
}
