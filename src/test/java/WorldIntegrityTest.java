import agh.ics.oop.Animal;
import agh.ics.oop.Vector2d;
import org.junit.jupiter.api.Test;

import static agh.ics.oop.World.AnimalMovement;
import static org.junit.jupiter.api.Assertions.*;

public class WorldIntegrityTest {
    @Test
    void BorderMoveTest() {
        Animal animal = new Animal();

        //Reaching Borders and trying to exceed them
        AnimalMovement(new String[]{"f", "f", "f", "f", "b", "f", "f", "r"}, animal);
        assertEquals("Wschód; (2,4)", animal.toString());

        AnimalMovement(new String[]{"f", "f", "f", "l"}, animal);
        assertEquals("Północ; (4,4)", animal.toString());

        AnimalMovement(new String[]{"b", "b", "b", "b", "b"}, animal);
        assertEquals("Północ; (4,0)", animal.toString());

        AnimalMovement(new String[]{"l", "f", "f", "f", "f", "f"}, animal);
        assertEquals("Zachód; (0,0)", animal.toString());

    }

    @Test
    void StandardMovementTest(){

        //going to point (3,3)
        Animal animal = new Animal();
        AnimalMovement(new String[]{"f","r","f"},animal);
        assertEquals("Wschód; (3,3)",animal.toString());
        //isAt unitTest
        assertTrue(animal.isAt(new Vector2d(3,3)));
        assertFalse(animal.isAt(new Vector2d(3,2)));
        assertFalse(animal.isAt(new Vector2d(2,3)));


        //do circle
        animal = new Animal();
        AnimalMovement(new String[]{"f","r","f","r","f","r","f"},animal);
        assertEquals("Zachód; (2,2)",animal.toString());


        //straight line
        animal = new Animal();
        AnimalMovement(new String[]{"f","f","b","b","b","b","f","f"},animal);
        assertEquals("Północ; (2,2)",animal.toString());

    }

}
