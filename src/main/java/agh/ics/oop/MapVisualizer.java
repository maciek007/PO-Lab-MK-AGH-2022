package agh.ics.oop;

import javax.swing.*;
import java.awt.*;

import static java.lang.Math.min;

/**
 * The map visualizer converts the {@link IWorldMap} map into a string
 * representation.
 *
 * @author apohllo
 */

public class MapVisualizer {

    private static final String EMPTY_CELL = " ";
    private static final String FRAME_SEGMENT = "-";
    private static final String CELL_SEGMENT = "|";
    private IWorldMap map;

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
        if (this.map.isOccupied(currentPosition)) {
            Object object = this.map.objectAt(currentPosition);
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

    public JPanel drawGrid(Vector2d lowerLeft, Vector2d upperRight)
    {
        int width = upperRight.substract(lowerLeft).x+1;
        int height = upperRight.substract(lowerLeft).y+1;

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(height,width,1,1));
        panel.setBackground(Color.BLACK);

        int scalar = min(760/width,460/height);

        panel.setSize(width*scalar,height*scalar);
        //System.out.println(panel.getSize());



        for(int j = 0; j<height;j++) {
            for(int i = 0; i<width;i++){
                JLabel l = new JLabel(  drawObject( new Vector2d(i,height-j-1) ),SwingConstants.CENTER );
                l.setBackground(Color.GRAY);
                l.setOpaque(true);
                l.setVerticalAlignment(SwingConstants.CENTER);
                l.setFont(new Font("Serif", Font. BOLD, scalar/2));
                l.setSize(scalar,scalar);
                panel.add(l);
            }
        }

        return panel;
    }

}
