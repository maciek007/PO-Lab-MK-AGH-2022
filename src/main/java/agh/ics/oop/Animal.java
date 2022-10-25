package agh.ics.oop;

public class Animal {

    private final IWorldMap map;
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position;

    public Animal(IWorldMap map) {
        this.map = map;
        this.position = new Vector2d(2,2);
    }
    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.position = new Vector2d(initialPosition);

    }

    public Vector2d getPosition(){
        return position;
    }
    public String toString()
    {
        return orientation.toString();
    }

    public boolean isAt(Vector2d position)
    {
        return this.position.equals(position);
    }
    public boolean move(MoveDirection direction)
    {
        Vector2d position_tmp = new Vector2d(0,0);
        switch (direction)
        {
            case RIGHT:
                orientation = orientation.next();
                return true;
            case LEFT:
                orientation = orientation.previous();
                return true;
            case FORWARD:
                position_tmp = position.add(orientation.toUnitVector());
                break;
            case BACKWARD:
                position_tmp = position.add(orientation.toUnitVector().opposite());
                break;
        }
        if(map.canMoveTo(position_tmp))
        {
            position = position_tmp;
            return true;
        }
        return false;
    }
}
