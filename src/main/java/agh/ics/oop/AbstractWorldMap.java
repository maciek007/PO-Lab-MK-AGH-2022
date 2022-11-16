package agh.ics.oop;

import javafx.scene.layout.GridPane;
import java.util.HashMap;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{

    protected final HashMap<Vector2d, Animal> animals = new HashMap<>();
    protected final MapBoundary bounds = new MapBoundary();

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Animal a = animals.remove(oldPosition);
        if(a!=null)
            animals.put(newPosition,a);
    }

    @Override
    abstract public boolean canMoveTo(Vector2d position) throws IllegalArgumentException;

    @Override
    public void place(Animal animal){
        if (canMoveTo(animal.getPosition())) {
            animals.put(animal.getPosition(), animal);
            animal.addObserver(this);
            bounds.addMapElement(animal);
        }
        else
            throw new IllegalArgumentException("Nie można umieścić zwierzęcia na polu " + animal.getPosition());
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
    public GridPane toGrid()
    {
        Vector2d[] v = bounds.getBounds();
        return new MapVisualizer(this).drawGrid(v[0],v[1]);
    }

}
