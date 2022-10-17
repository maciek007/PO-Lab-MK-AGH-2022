package agh.ics.oop;

public class Animal {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2,2);

    public String toString()
    {
        return orientation.toString() + "; " + position.toString();
    }

    public boolean isAt(Vector2d position)
    {
        return this.position.equals(position);
    }
    public void move(MoveDirection direction)
    {
        Vector2d position_tmp = new Vector2d(0,0);
        switch (direction)
        {
            case RIGHT:
                orientation = orientation.next();
                return;
            case LEFT:
                orientation = orientation.previous();
                return;
            case FORWARD:
                position_tmp = position.add(orientation.toUnitVector());
                break;
            case BACKWARD:
                position_tmp = position.add(orientation.toUnitVector().opposite());
                break;
        }
        if(position_tmp.follows(new Vector2d(0,0)) && position_tmp.precedes(new Vector2d(4,4)))
            position = position_tmp;
    }
}
