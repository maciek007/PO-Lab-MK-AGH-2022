package agh.ics.oop;

import java.util.Arrays;
import java.util.List;

public class OptionsParser {
    public static MoveDirection[] parse(List<String> s_tab) throws IllegalArgumentException
    {
        MoveDirection[] md = new MoveDirection[s_tab.size()];
        int index = 0;
        for(String c: s_tab)
        {
            switch (c) {
                case "f", "forward" -> md[index++] = MoveDirection.FORWARD;
                case "b", "backward" -> md[index++] = MoveDirection.BACKWARD;
                case "l", "left" -> md[index++] = MoveDirection.LEFT;
                case "r", "right" -> md[index++] = MoveDirection.RIGHT;
                default -> throw new IllegalArgumentException(c + " is not legal move specification");
            }
        }

        return Arrays.copyOfRange(md,0, index);
    }

}
