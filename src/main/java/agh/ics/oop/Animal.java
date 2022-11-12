package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class Animal extends AbstractMapElement{

    private final IWorldMap map;
    private final List<IPositionChangeObserver> observers = new ArrayList<>();
    private MapDirection orientation = MapDirection.NORTH;

    public Animal(IWorldMap map) {
        this.map = map;
        this.position = new Vector2d(2,2);
    }
    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.position = new Vector2d(initialPosition);

    }

    public String toString()
    {
        return orientation.toString();
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
            positionChanged(position,position_tmp);
            position = position_tmp;
            return true;
        }

        return false;
    }

    public void addObserver(IPositionChangeObserver observer)
    {
        observers.add(observer);
    }
    public void removeObserver(IPositionChangeObserver observer)
    {
        observers.remove(observer);
    }

    private void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        for(IPositionChangeObserver observer : observers)
        {
            observer.positionChanged(oldPosition,newPosition);
        }
    }
}
