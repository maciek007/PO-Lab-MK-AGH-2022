import agh.ics.oop.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WorldIntegrityTest {
    @Test
    void NormalCaseTest() {
        String[] args = {"r", "l", "f", "f", "f", "b"};
        MoveDirection[] directions = OptionsParser.parse(args);

        // case without collision
        IWorldMap map = new RectangularMap(5, 5);
        Vector2d[] positions = {new Vector2d(2, 3), new Vector2d(2, 1), new Vector2d(2, 2)};
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        String result = new MapVisualizer(map).draw(new Vector2d(0, 0), new Vector2d(4, 4));
        assertEquals(
                parseString("""
                         y\\x  0 1 2 3 4
                          5: -----------
                          4: | | | | | |
                          3: | | | |>| |
                          2: | | | | | |
                          1: | |<|^| | |
                          0: | | | | | |
                         -1: -----------
                        """),parseString(result)
        );

    }

    @Test
    void CollisionCaseTest() {
        // case with 2 collisions and 1 animal which cannot be placed
        String [] args = new String[]{"f","l","r","f","f","f"};
        MoveDirection [] directions = OptionsParser.parse(args);
        IWorldMap map = new RectangularMap(5, 5);
        Vector2d[] positions = new Vector2d[]{new Vector2d(2, 2), new Vector2d(2, 1), new Vector2d(2, 3), new Vector2d(2, 2)};
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        String result = new MapVisualizer(map).draw(new Vector2d(0,0),new Vector2d(4,4));
        assertEquals(
                parseString("""
                          4: | | | | | |
                          3: | | | |>| |
                          2: | | |^| | |
                          1: | |<| | | |
                          0: | | | | | |
                        """),parseString(result)
        );
    }

    @Test
    void ExerciseCaseTest() {
        // case with 2 collisions and 1 animal which cannot be placed
        String [] args = {"f", "b","r","l","f", "f", "r", "r","f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection [] directions = OptionsParser.parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions =  { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        String result = new MapVisualizer(map).draw(new Vector2d(0,0),new Vector2d(9,4));
        assertEquals(
                parseString("""
                         
                           | | | |^| | | | | | |
                           | | | | | | | | | | |
                           | | | | | | | | | | |
                           | | | | | | | | | | |
                           | | |˅| | | | | | | |
                         
                        """),parseString(result)
        );
    }

    @Test
    void MarginalCaseTest()
    {
        String [] args = {"f","f","f","r","f"};
        MoveDirection[] directions = OptionsParser.parse(args);
        IWorldMap map = new RectangularMap(1, 1);
        Vector2d[] positions = { new Vector2d(0,0), new Vector2d(0,0), new Vector2d(1,1)};

        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        String result = new MapVisualizer(map).draw(new Vector2d(0,0),new Vector2d(1,1));
        assertEquals(
                parseString("""
                          
                         | | |
                         |>| |
                         
                        """),parseString(result)
        );
    }

    private String parseString(String s)
    {
        return s.replaceAll("[^|><^˅]+", "");
    }
}


//    String [] args = {"","","",""};
//    MoveDirection[] directions = OptionsParser.parse(args);
//    IWorldMap map = new RectangularMap(1, 1);
//    Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
//    IEngine engine = new SimulationEngine(directions, map, positions);
//        engine.run();
//                System.out.print(map);
