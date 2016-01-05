package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Created by Guillaume on 05/01/2016.
 */
public class UVSprite extends Circle {
    private int position;

    public UVSprite(double x, double y, double radius, Color color, int position){
        super(x, y, radius, color);
        this.position=position;
    }
}
