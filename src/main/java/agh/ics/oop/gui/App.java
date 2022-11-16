package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;



public class App extends Application{



    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("Wizualizacja");

        SimulationEngine engine;
        IWorldMap gf;
        GridPane grid;

        try {
            MoveDirection[] directions = OptionsParser.parse(getParameters().getRaw());
            //IWorldMap map = new RectangularMap(10, 5);


            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(5, 4)};
            gf = new GrassField(10);
            engine = new SimulationEngine(directions, gf, positions);
            engine.run();

            grid = gf.toGrid();

            Scene scene = new Scene(grid, 800, 800);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (IllegalArgumentException E)
        {
            System.out.print(E);
        }


    }
}
