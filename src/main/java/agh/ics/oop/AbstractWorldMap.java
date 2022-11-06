package agh.ics.oop;

import javax.swing.*;
import java.util.ArrayList;

public abstract class AbstractWorldMap implements IWorldMap{

    protected final ArrayList <Animal> animals = new ArrayList<>();
    protected int width;
    protected int height;

    @Override
    abstract public boolean canMoveTo(Vector2d position);

    @Override
    public boolean place(Animal animal){
        if (canMoveTo(animal.getPosition())) {
            animals.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (Animal a : animals)
            if(a.isAt(position)) return true;
        return false;
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
