package agh.ics.oop;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public class World {
    public static void main(String[] args) {

        System.out.println("system wystartował");

        Stream<Direction> sd = interpret(args);
        run(sd);

        System.out.println("system zakończył działanie");
    }

    private static void run(Stream<Direction> comm) {

        comm.forEach
                (
                        d -> {
                            String msg = switch (d) {
                                case FORWARD -> "Zwierzak idzie do przodu";
                                case RIGHT -> "Zwierzak skręca w prawo";
                                case LEFT -> "Zwierzak skręca w lewo";
                                case BACKWARD -> "Zwierzak idzie do tyłu";
                            };
                            System.out.println(msg);
                        }
                );

    }


    private static Stream<Direction> interpret(String[] args) {
        return Arrays.stream(args)
                .map(element -> switch (element) {
                    case "f" -> Direction.FORWARD;
                    case "b" -> Direction.BACKWARD;
                    case "l" -> Direction.LEFT;
                    case "r" -> Direction.RIGHT;
                    default -> null;
                }).filter(Objects::nonNull);
    }

}