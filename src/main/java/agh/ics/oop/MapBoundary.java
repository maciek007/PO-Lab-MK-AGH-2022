package agh.ics.oop;

import java.util.SortedSet;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver{

    SortedSet <Vector2d> sortedX = new TreeSet<>((Vector2d a, Vector2d b) -> a.x-b.x==0? a.y-b.y : a.x-b.x);
    SortedSet <Vector2d> sortedY = new TreeSet<>((Vector2d a, Vector2d b) -> a.y-b.y==0? a.x-b.x : a.y-b.y);

    public void addMapElement(AbstractMapElement el)
    {
        el.addObserver(this);
        sortedX.add(el.position);
        sortedY.add(el.position);
    }
    public void removeMapElement(AbstractMapElement el)
    {
        sortedX.remove(el.position);
        sortedY.remove(el.position);
    }
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        sortedX.remove(oldPosition);
        sortedX.add(newPosition);
        sortedY.remove(oldPosition);
        sortedY.add(newPosition);
    }

    public Vector2d[] getBounds(){
        //System.out.print(Arrays.toString(new Vector2d[]{new Vector2d(sortedX.first().x,sortedY.first().y),new Vector2d(sortedX.last().x,sortedY.last().y)}));
        return new Vector2d[]{new Vector2d(sortedX.first().x,sortedY.first().y),new Vector2d(sortedX.last().x,sortedY.last().y)};
    }

}