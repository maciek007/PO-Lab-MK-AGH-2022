package agh.ics.oop;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class GrassField extends AbstractWorldMap{
    protected final ArrayList<Grass> grasses = new ArrayList<>();
    private int n=0;
    private final Random r;




    public GrassField(int n) {
        this.n = n;
        r = new Random();
        for(int i=1;i<n;i++)
        {
            Vector2d v;
            do
            {
                v = new Vector2d(r.nextInt((int)Math.sqrt(10*n)), r.nextInt((int)Math.sqrt(10*n)) );
            }while(isOccupied(v) || isOccupiedByGrass(v));
            place(new Grass(v));
        }

    }

    protected boolean isOccupiedByGrass(Vector2d position){
        for (Grass a : grasses)
            if(a.isAt(position)) return true;
        return false;
    }


    public boolean place(Grass g) {
        if (!isOccupiedByGrass(g.getPosition()) && !isOccupied(g.getPosition())) {
            grasses.add(g);
            return true;
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal a : animals)
            if(a.isAt(position)) return a;
        for (Grass g : grasses)
            if(g.isAt(position)) return g;
        return null;
    }

    @Override
    public String toString() {
        Vector2d[] v = getBorders();
        return new MapVisualizer(this).draw(v[1],v[0]);
    }

    @Override
    public JPanel toGrid() {
        Vector2d[] v = getBorders();
        return new MapVisualizer(this).drawGrid(v[1],v[0]);
    }
    public Vector2d[] getBorders()
    {
        Integer up_pos=null;
        Integer down_pos=null;
        Integer left_pos=null;
        Integer right_pos=null;
        for(Animal a : animals)
        {
            if (up_pos==null || up_pos < a.getPosition().y)
                up_pos = a.getPosition().y;
            if (down_pos==null || down_pos > a.getPosition().y)
                down_pos = a.getPosition().y;
            if (left_pos==null || left_pos > a.getPosition().x)
                left_pos = a.getPosition().x;
            if (right_pos==null || right_pos < a.getPosition().x)
                right_pos = a.getPosition().x;
        }
        for(Grass a : grasses)
        {
            if (up_pos==null || up_pos < a.getPosition().y)
                up_pos = a.getPosition().y;
            if (down_pos==null || down_pos > a.getPosition().y)
                down_pos = a.getPosition().y;
            if (left_pos==null || left_pos > a.getPosition().x)
                left_pos = a.getPosition().x;
            if (right_pos==null || right_pos < a.getPosition().x)
                right_pos = a.getPosition().x;
        }
        return new Vector2d[] {new Vector2d(right_pos,up_pos), new Vector2d(left_pos,down_pos)};
    }
    public boolean canMoveTo(Vector2d position)
    {
        if(isOccupiedByGrass(position))
            eatGrass(position);
        return ( !isOccupied(position) );
    }

    private void eatGrass(Vector2d position) {
        for (int i =0;i<grasses.size();i++)
            if(grasses.get(i).isAt(position))
            {
                Vector2d v;
                do
                {
                    v = new Vector2d(r.nextInt((int)Math.sqrt(10*n)), r.nextInt((int)Math.sqrt(10*n)) );
                }while(isOccupied(v) || isOccupiedByGrass(v));

                grasses.set(i,new Grass(v));
                return;
            }
    }
}
