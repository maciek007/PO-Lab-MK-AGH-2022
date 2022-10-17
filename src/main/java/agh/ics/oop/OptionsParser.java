package agh.ics.oop;

import java.util.Arrays;

public class OptionsParser {
    public static MoveDirection[] parse(String[] s_tab)
    {
        MoveDirection[] md = new MoveDirection[s_tab.length];
        int index = 0;
        for(String c: s_tab)
        {
            switch (c) {
                case "f" -> md[index++] = MoveDirection.FORWARD;
                case "b" -> md[index++] = MoveDirection.BACKWARD;
                case "l" -> md[index++] = MoveDirection.LEFT;
                case "r" -> md[index++] = MoveDirection.RIGHT;
            }
        }

        return Arrays.copyOfRange(md,0, index);
    }

}
