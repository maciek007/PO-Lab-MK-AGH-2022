package agh.ics.oop;

import java.util.HashMap;
import java.util.Random;

public class GrassField extends AbstractWorldMap{
    protected final HashMap<Vector2d,Grass> grasses = new HashMap<>();
    private int n=0;
    private final Random r;




    public GrassField(int n) {
        this.n = n;
        r = new Random();
        for(int i=1;i<n;i++)
        {
            place(new Grass(findFreeLand()));
        }

    }

    private Vector2d findFreeLand(){
        Vector2d v;
        do
        {
            v = new Vector2d(r.nextInt((int)Math.sqrt(10*n)), r.nextInt((int)Math.sqrt(10*n)) );
        }while(isOccupied(v) || isOccupiedByGrass(v));
        return v;
    }

    protected boolean isOccupiedByGrass(Vector2d position){
        return grasses.containsKey(position);
    }


    public boolean place(Grass g) {
        if (!isOccupiedByGrass(g.getPosition()) && !isOccupied(g.getPosition())) {
            grasses.put(g.getPosition(),g);
            g.addObserver(bounds);
            bounds.addMapElement(g);
            return true;
        }
        return false;
    }

    @Override
    public AbstractMapElement objectAt(Vector2d position) {
        if(animals.containsKey(position)) return animals.get(position);
        if(grasses.containsKey(position)) return grasses.get(position);
        return null;
    }

    @Override
    public String toString() {
        Vector2d[] v = bounds.getBounds();
        return new MapVisualizer(this).draw(v[0],v[1]);
    }

    public boolean canMoveTo(Vector2d position)
    {
        if(isOccupiedByGrass(position))
            eatGrass(position);
        return ( !isOccupied(position) );
    }

    private void eatGrass(Vector2d position) {
            if(grasses.containsKey(position))
            {
                Grass g = grasses.remove(position);
                Vector2d v = findFreeLand();
                grasses.put(v,g);
                g.position = v;
                g.positionChanged(position,v);
            }
    }
}
