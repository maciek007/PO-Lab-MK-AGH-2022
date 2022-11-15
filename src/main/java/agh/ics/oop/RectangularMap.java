package agh.ics.oop;

import javax.swing.*;

public class RectangularMap extends AbstractWorldMap{
    protected int width;
    protected int height;

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
    }


    @Override
    public Object objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public String toString() {
        return new MapVisualizer(this).draw(new Vector2d(0,0), new Vector2d(width-1,height-1));
    }
    public JPanel toGrid() {
        return new MapVisualizer(this).drawGrid(new Vector2d(0,0), new Vector2d(width-1,height-1));
    }

    public boolean canMoveTo(Vector2d position)
    {
        return ( position.follows(new Vector2d(0,0)) &&
                position.precedes(new Vector2d(width-1,height-1)) &&
                !isOccupied(position) );
    }
}
