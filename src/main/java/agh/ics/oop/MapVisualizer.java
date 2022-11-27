package agh.ics.oop;

import agh.ics.oop.gui.GuiElementBox;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;

import java.io.FileNotFoundException;

import static java.lang.Math.min;

/**
 * The map visualizer converts the {@link IWorldMap} map into a string
 * representation.
 *
 * @author apohllo
 */

public class MapVisualizer{

    private static final String EMPTY_CELL = " ";
    private static final String FRAME_SEGMENT = "-";
    private static final String CELL_SEGMENT = "|";
    private IWorldMap map;
    private GridPane grid;

    /**
     * Initializes the MapVisualizer with an instance of map to visualize.
     * @param map
     */
    public MapVisualizer(IWorldMap map) {
        this.map = map;
    }

    /**
     * Convert selected region of the map into a string. It is assumed that the
     * indices of the map will have no more than two characters (including the
     * sign).
     *
     * @param lowerLeft  The lower left corner of the region that is drawn.
     * @param upperRight The upper right corner of the region that is drawn.
     * @return String representation of the selected region of the map.
     */
    public String draw(Vector2d lowerLeft, Vector2d upperRight) {
        StringBuilder builder = new StringBuilder();
        for (int i = upperRight.y + 1; i >= lowerLeft.y - 1; i--) {
            if (i == upperRight.y + 1) {
                builder.append(drawHeader(lowerLeft, upperRight));
            }
            builder.append(String.format("%3d: ", i));
            for (int j = lowerLeft.x; j <= upperRight.x + 1; j++) {
                if (i < lowerLeft.y || i > upperRight.y) {
                    builder.append(drawFrame(j <= upperRight.x));
                } else {
                    builder.append(CELL_SEGMENT);
                    if (j <= upperRight.x) {
                        builder.append(drawObject(new Vector2d(j, i)));
                    }
                }
            }
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }

    private String drawFrame(boolean innerSegment) {
        if (innerSegment) {
            return FRAME_SEGMENT + FRAME_SEGMENT;
        } else {
            return FRAME_SEGMENT;
        }
    }

    private String drawHeader(Vector2d lowerLeft, Vector2d upperRight) {
        StringBuilder builder = new StringBuilder();
        builder.append(" y\\x ");
        for (int j = lowerLeft.x; j < upperRight.x + 1; j++) {
            builder.append(String.format("%2d", j));
        }
        builder.append(System.lineSeparator());
        return builder.toString();
    }

    private String drawObject(Vector2d currentPosition) {
        String result = null;
        if (this.map.objectAt(currentPosition)!=null) {
            AbstractMapElement object = this.map.objectAt(currentPosition);
            if (object != null) {
                result = object.toString();
            } else {
                result = EMPTY_CELL;
            }
        } else {
            result = EMPTY_CELL;
        }
        return result;
    }

    public GridPane drawGrid(Vector2d lowerLeft, Vector2d upperRight) throws FileNotFoundException
    {
        if(grid == null) {
            int width = upperRight.substract(lowerLeft).x + 2;
            int height = upperRight.substract(lowerLeft).y + 2;

            grid = new GridPane();

            float scalar = min(800 / width, 800 / height);

            for (int j = lowerLeft.y; j <= upperRight.y; j++) {
                Label l = new Label(Integer.toString(j));
                l.setFont(new Font(scalar * 2 / 3));
                GridPane.setHalignment(l, HPos.CENTER);
                grid.add(l, 0, upperRight.y - j + 1);
            }
            for (int i = lowerLeft.x; i <= upperRight.x; i++) {
                Label l = new Label(Integer.toString(i));
                l.setFont(new Font(scalar * 2 / 3));
                GridPane.setHalignment(l, HPos.CENTER);
                grid.add(l, i - lowerLeft.x + 1, 0);
            }

            {
                Label l = new Label("y/x");
                l.setFont(new Font(scalar * 2 / 3));
                GridPane.setHalignment(l, HPos.CENTER);
                grid.add(l, 0, 0);
            }

            grid.getColumnConstraints().add(new ColumnConstraints(scalar));
            grid.getRowConstraints().add(new RowConstraints(scalar));

            for (int j = lowerLeft.y; j <= upperRight.y; j++) {
                grid.getRowConstraints().add(new RowConstraints(scalar));
                for (int i = lowerLeft.x; i <= upperRight.x; i++) {
                    //grid.getColumnConstraints().add(new ColumnConstraints(scalar));
                    Vector2d analyse = new Vector2d(i, j);

                    javafx.scene.Node child = new Label();
                    if (map.objectAt(analyse) != null) {
                        GuiElementBox box = new GuiElementBox(map.objectAt(analyse), scalar);
                        child = box.getVbox();
                    }
                    GridPane.setHalignment(child, HPos.CENTER);
                    grid.add(child, i - lowerLeft.x + 1, upperRight.y - j + 1);


                }
            }

            for (int i = lowerLeft.x; i <= upperRight.x; i++)
                grid.getColumnConstraints().add(new ColumnConstraints(scalar));
            grid.setGridLinesVisible(true);
        }
        return grid;
    }


}


