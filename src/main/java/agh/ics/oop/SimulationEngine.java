package agh.ics.oop;

import agh.ics.oop.gui.App;

import java.util.ArrayList;

public class SimulationEngine implements IEngine, Runnable{


    private MoveDirection[] moveDirection;

    private final IWorldMap map;
    private final ArrayList<Animal> animals = new ArrayList<>();

    private final int moveDelay = 300;
    private final App app;



    public SimulationEngine(IWorldMap map, Vector2d [] positions, App app) {
        this.app = app;
        for (Vector2d p : positions) {
            Animal animToAdd = new Animal(map, p);
            map.place(animToAdd);
            animals.add(animToAdd);
        }
        this.map = map;
        this.moveDirection = new MoveDirection[0];
    }

    public void setMoveDirection(MoveDirection [] md) {
        moveDirection = md;
    }

    @Override
    public void run(){
        if(animals.size()==0)return;
        int i = 0;

        try {
            for (MoveDirection md : moveDirection) {
                animals.get(i).move(md);
                i++;
                i %= animals.size();
                app.redrawMap();
                Thread.sleep(moveDelay);
            }
        }
        catch (InterruptedException e)
        {
            System.out.print("Wątek symulacji nieoczekiwanie został zamknięty");
        }
    }
}
