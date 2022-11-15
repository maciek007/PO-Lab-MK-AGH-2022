package agh.ics.oop;

import javax.swing.*;

public class World {
    public static void main(String[] args) {

        int op = JOptionPane.showOptionDialog(new JFrame(),"Zaczac symulacje z wizualizacja?","Wizualizacja",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null, null, null);
        //System.out.println(op);

        try {
            MoveDirection[] directions = OptionsParser.parse(args);
            //IWorldMap map = new RectangularMap(10, 5);
            IWorldMap gf = new GrassField(10);

            Vector2d[] positions = {new Vector2d(2, 2),new Vector2d(2, 2), new Vector2d(5, 4)};
            SimulationEngine engine = new SimulationEngine(directions, gf, positions);
            if (op == 1) engine.run();
            else engine.runWithVisualization();
        }
        catch (IllegalArgumentException E)
        {
            System.out.print(E);
        }
    }

}