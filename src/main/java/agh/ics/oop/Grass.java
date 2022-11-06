package agh.ics.oop;

public class Grass extends AbstractMapElement{
    public Grass(Vector2d pos)
    {
        position = pos;
    }

    @Override
    public String toString(){
        return "*";
    }
}
