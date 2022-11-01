package agh.ics.oop;

import javax.swing.*;
import java.util.ArrayList;

public class RectangularMap implements IWorldMap{

    private final int width;
    private final int height;

    private final ArrayList <Animal> animals = new ArrayList<>();

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean canMoveTo(Vector2d position)
    {
        return ( position.follows(new Vector2d(0,0)) &&
                 position.precedes(new Vector2d(width-1,height-1)) &&
                !isOccupied(position) );
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())){
            animals.add(animal);
            return true;
        }else
            return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (Animal a : animals)
            if(a.isAt(position)) return true;
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal a : animals)
            if(a.isAt(position)) return a;
        return null;
    }

    @Override
    public String toString() {
        return new MapVisualizer(this).draw(new Vector2d(0,0), new Vector2d(width-1,height-1));
    }
    public JPanel toGrid() {
        return new MapVisualizer(this).drawGrid(new Vector2d(0,0), new Vector2d(width-1,height-1));
    }

}
