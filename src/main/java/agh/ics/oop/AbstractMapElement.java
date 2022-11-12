package agh.ics.oop;

public abstract class AbstractMapElement {
    protected Vector2d position;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o.getClass() != this.getClass()) return false;
//        AbstractMapElement that = (AbstractMapElement) o;
//        return Objects.equals(position, that.position);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(position, getClass());
//    }

    public Vector2d getPosition(){
        return position;
    }
    public boolean isAt(Vector2d position)
    {
        return this.position.equals(position);
    }
}
