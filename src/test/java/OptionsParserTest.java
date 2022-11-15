import agh.ics.oop.MoveDirection;
import agh.ics.oop.OptionsParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OptionsParserTest {
    @Test
    void NormalCaseTest()
    {
        MoveDirection[] directions = OptionsParser.parse(new String[]{"f", "forward", "b", "r","backward","right","l","left"});
        assertArrayEquals(new MoveDirection[]{MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.LEFT },directions);
    }

    @Test
    void ExceptionCaseTest()
    {
        IllegalArgumentException t = assertThrows(IllegalArgumentException.class, ()->OptionsParser.parse(new String[]{"f", "forward", "b", "r","backward","h","right","l","left"}));
        assertEquals("h is not legal move specification",t.getMessage());
    }
}
