package agh.ics.oop;

import javax.swing.*;
import java.util.HashMap;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{

    protected final HashMap<Vector2d, Animal> animals = new HashMap<>();
    protected int width;
    protected int height;

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Animal a = animals.remove(oldPosition);
        if(a!=null)
            animals.put(newPosition,a);
    }

    @Override
    abstract public boolean canMoveTo(Vector2d position);

    @Override
    public boolean place(Animal animal){
        if (canMoveTo(animal.getPosition())) {
            animals.put(animal.getPosition(), animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    abstract public Object objectAt(Vector2d position);


    @Override
    abstract public String toString();
//    {
//        return new MapVisualizer(this).draw(new Vector2d(0,0), new Vector2d(width-1,height-1));
//    }
    abstract public JPanel toGrid();
//    {
//        return new MapVisualizer(this).drawGrid(new Vector2d(0,0), new Vector2d(width-1,height-1));
//    }

}
