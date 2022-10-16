package agh.ics.oop;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Vector2d
{
    final public int x;
    final public int y;

    public  Vector2d(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    public String toString()
    {
        return "(" + x + "," + y + ")";
    }
    public boolean precedes(Vector2d other)
    {
        return this.x <= other.x && this.y<= other.y;
    }
    public boolean follows(Vector2d other)
    {
        return this.x >= other.x && this.y >= other.y;
    }
    public Vector2d upperRight(Vector2d other)
    {
        return new Vector2d(max(this.x, other.x),max(this.y, other.y));
    }
    public Vector2d lowerLeft(Vector2d other)
    {
        return new Vector2d(min(this.x, other.x),min(this.y, other.y));
    }
    public Vector2d add(Vector2d other)
    {
        return new Vector2d(this.x + other.x,this.y + other.y);
    }
    public Vector2d substract(Vector2d other)
    {
        return new Vector2d(this.x - other.x,this.y - other.y);
    }
    public boolean equals(Object other)
    {
        if(this == other)
            return true;
        if(!(other instanceof Vector2d that))
            return false;
        return (that.x==this.x && that.y == this.y);
    }
    public Vector2d opposite()
    {
        return new Vector2d(-this.x,-this.y);
    }

    public static void main(String[] args) {
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));
    }

}
