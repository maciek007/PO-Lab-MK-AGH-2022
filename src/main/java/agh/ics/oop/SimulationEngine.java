package agh.ics.oop;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class SimulationEngine implements IEngine,ActionListener{


    private final MoveDirection[] moveDirection;

    private final IWorldMap map;
    private final ArrayList<Animal> animals = new ArrayList<>();

    private JFrame frame;
    private JPanel mapContainer;


    public SimulationEngine(MoveDirection []md, IWorldMap map, IPositionChangeObserver observer, Vector2d [] positions) {
        for (Vector2d p : positions) {
            Animal animToAdd = new Animal(map, p);
            if (map.place(animToAdd)) {
                animals.add(animToAdd);
                animToAdd.addObserver(observer);
            }
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

    public void runWithVisualization()
    {
        frame = new JFrame();
        frame.setSize(810,580);

        Container visualization = frame.getContentPane();

        visualization.setLayout(null);
        JButton startButton = new JButton("Start Simulation");

        JPanel currentMap = map.toGrid();


        mapContainer = new JPanel();
        mapContainer.setBorder(BorderFactory.createTitledBorder("Mapa"));
        mapContainer.setLayout(new BorderLayout());
        mapContainer.add(currentMap, BorderLayout.CENTER);




//        JPanel historyContainer = new JPanel();
//        historyContainer.setBorder(BorderFactory.createTitledBorder("Historia ruchow"));
//        historyContainer.add(new Label(""));



        visualization.add(startButton);
        visualization.add(mapContainer);
        //visualization.add(historyContainer);

        startButton.setBounds(0,0,790, 50);
        mapContainer.setBounds(0,50,790, 490);
        //historyContainer.setBounds(200,50,200, 350);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        startButton.addActionListener(e -> startPresentation());
    }

    private void startPresentation(){
        if(animals.size()==0)return;
        Timer timer;
        timer = new Timer(600, this);
        timer.start();

    }


    private int moveDirection_i=0;
    private int animal_i=0;
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(moveDirection_i);
        System.out.println(moveDirection.length);
        if(moveDirection_i<moveDirection.length) {
            animals.get(animal_i).move(moveDirection[moveDirection_i]);
            animal_i += 1;
            animal_i %= animals.size();
            moveDirection_i += 1;
            mapContainer.removeAll();
            mapContainer.add(map.toGrid());
            mapContainer.revalidate();
            mapContainer.repaint();
        }
    }
}
