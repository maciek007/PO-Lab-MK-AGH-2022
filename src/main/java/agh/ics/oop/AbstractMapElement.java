package agh.ics.oop;

public abstract class AbstractMapElement {
    protected Vector2d position;

    public Vector2d getPosition(){
        return position;
    }
    public boolean isAt(Vector2d position)
    {
        return this.position.equals(position);
    }
}
