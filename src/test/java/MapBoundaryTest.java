import agh.ics.oop.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MapBoundaryTest {

    @Test
    void MapBoundaryIntegrityTest(){
        IWorldMap map = new GrassField(0);
        MapBoundary mb = new MapBoundary();
        mb.addMapElement(new Animal(map,new Vector2d(0,5)));
        mb.addMapElement(new Animal(map,new Vector2d(5,-3)));
        mb.addMapElement(new Animal(map,new Vector2d(-6,-4)));
        mb.addMapElement(new Animal(map,new Vector2d(1,-42)));
        mb.addMapElement(new Animal(map,new Vector2d(-3,21)));
        mb.addMapElement(new Animal(map,new Vector2d(2,-37)));
        assertArrayEquals(new Vector2d[]{new Vector2d(-6,-42),new Vector2d(5,21)},mb.getBounds());
        mb.positionChanged(new Vector2d(-6,-4),new Vector2d(3,30));
        mb.removeMapElement(new Animal(map,new Vector2d(5,-3)));
        assertArrayEquals(new Vector2d[]{new Vector2d(-3,-42),new Vector2d(3,30)},mb.getBounds());

    }
}
