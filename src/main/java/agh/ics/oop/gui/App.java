package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.List;


public class App extends Application {


    SimulationEngine engine;
    IWorldMap gf;
    GridPane grid;
    Scene scene;
    VBox vb;


    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("Wizualizacja");
        try {
            //IWorldMap map = new RectangularMap(10, 5);

            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(5, 4)};
            gf = new GrassField(10);

            engine = new SimulationEngine( gf, positions, this);
            grid = gf.toGrid();

            TextField tf = new TextField(getParameters().getRaw().stream().reduce("",(sub,el)->sub+el+" "));
            Button startButton = new Button();


            startButton.setOnAction((e)->{
                engine.setMoveDirection(OptionsParser.parse(List.of(tf.getText().split(" "))));
                Thread engineThread = new Thread(engine);
                engineThread.start();});
            startButton.setText("Start Simulation");

            HBox hb = new HBox(tf,startButton);
            hb.setSpacing(5.);
            vb = new VBox(hb,grid);
            vb.setSpacing(10.);

            scene = new Scene(vb, 800, 850);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (IllegalArgumentException | FileNotFoundException E)
        {
            System.out.print(E);

        }

    }


    public void redrawMap() {
        try {
            grid = gf.toGrid();
        }
        catch (FileNotFoundException e)
        {
            System.out.print("Nie udało się załadować tekstur - nie znaleziono pliku");
        }
        Platform.runLater(() -> {vb.getChildren().set(1,grid);});
    }
}
