package agh.ics.oop;

public enum MapDirection {
    NORTH,
    SOUTH,
    WEST,
    EAST;

    final static String guiNORTH = "src/main/resources/up.png";
    final static String guiEAST = "src/main/resources/right.png";
    final static String guiSOUTH = "src/main/resources/down.png";
    final static String guiWEST = "src/main/resources/left.png";

    public String toString()
    {
        return switch (this) {
            case NORTH -> "^";
            case EAST -> ">";
            case SOUTH -> "˅";
            case WEST -> "<";
        };
    }

    public String toResPath()
    {
        return switch (this) {
            case NORTH -> guiNORTH;
            case EAST -> guiEAST;
            case SOUTH -> guiSOUTH;
            case WEST -> guiWEST;
        };
    }

    public MapDirection next()
    {
        return switch (this) {
            case NORTH -> EAST;
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            case WEST -> NORTH;
        };
    }
    public MapDirection previous()
    {
        return switch (this) {
            case NORTH -> WEST;
            case EAST -> NORTH;
            case SOUTH -> EAST;
            case WEST -> SOUTH;
        };
    }
    public Vector2d toUnitVector()
    {
        return switch (this) {
            case NORTH -> new Vector2d(0, 1);
            case EAST -> new Vector2d(1, 0);
            case SOUTH -> new Vector2d(0, -1);
            case WEST -> new Vector2d(-1, 0);
        };
    }
}
