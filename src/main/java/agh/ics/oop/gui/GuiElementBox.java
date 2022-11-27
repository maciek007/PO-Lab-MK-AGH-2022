package agh.ics.oop.gui;

import agh.ics.oop.AbstractMapElement;
import agh.ics.oop.IPositionChangeObserver;
import agh.ics.oop.Vector2d;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox implements IPositionChangeObserver {

    private ImageView imgV;
    private Label l;
    private VBox verticalBox;

    public GuiElementBox(AbstractMapElement mapElement, float size) throws FileNotFoundException
    {
        Image img = new Image(new FileInputStream(mapElement.getResourcePath()));

        imgV = new ImageView(img);
        imgV.setFitHeight(size-20);
        imgV.setFitWidth(size-20);
        l = new Label(mapElement.getLabel());
        verticalBox = new VBox(imgV, l);
        verticalBox.setAlignment(Pos.CENTER);
    }
    public VBox getVbox() {
        return verticalBox;
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        l.setText(newPosition.toString());
    }
}
