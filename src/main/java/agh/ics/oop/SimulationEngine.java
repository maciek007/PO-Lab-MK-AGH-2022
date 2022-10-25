package agh.ics.oop;

import java.util.ArrayList;

public class SimulationEngine implements IEngine{


    private final MoveDirection[] moveDirection;
    private final IWorldMap map;
    private final ArrayList<Animal> animals = new ArrayList<>();

    public SimulationEngine(MoveDirection []md, IWorldMap map, Vector2d [] positions) {
        for (Vector2d p : positions) {
            Animal animToAdd = new Animal(map, p);
            if (map.place(animToAdd))
                animals.add(animToAdd);
        }
        this.moveDirection = md;
        this.map = map;
    }

    @Override
    public void run(){
        if(animals.size()==0)return;
        int i = 0;
        for(MoveDirection md : moveDirection)
        {
            animals.get(i).move(md);
            i++;
            i%=animals.size();
        }
    }


}
