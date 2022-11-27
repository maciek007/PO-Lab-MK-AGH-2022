package agh.ics.oop;

public class Grass extends AbstractMapElement{

    final static private String guiGrass = "src/main/resources/grass.png";

    public Grass(Vector2d pos)
    {
        position = pos;
    }

    @Override
    public String getResourcePath() {
        return guiGrass;
    }

    @Override
    public String toString(){
        return "*";
    }

    @Override
    public String getLabel() {
        return "Trawa";
    }
}
