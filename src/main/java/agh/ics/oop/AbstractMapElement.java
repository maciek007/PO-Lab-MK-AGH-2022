package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMapElement {
    protected Vector2d position;
    protected final List<IPositionChangeObserver> observers = new ArrayList<>();

    public abstract String getResourcePath();
    public abstract String getLabel();

    public Vector2d getPosition(){
        return position;
    }
    public boolean isAt(Vector2d position)
    {
        return this.position.equals(position);
    }


    public void addObserver(IPositionChangeObserver observer)
    {
        observers.add(observer);
    }
    public void removeObserver(IPositionChangeObserver observer)
    {
        observers.remove(observer);
    }

    protected void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        for(IPositionChangeObserver observer : observers)
        {
            observer.positionChanged(oldPosition,newPosition);
        }
    }
}
