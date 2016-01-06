package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

/**
 * Created by Guillaume on 06/01/2016.
 */
public class PlayerSprite extends Rectangle{
    private int numero;

    public PlayerSprite(double x, double y, double width, double height, int numero){
        super(x, y, width, height);
        this.numero=numero;
        setFill(Color.ORANGE);
        setStrokeWidth(1);
        setStroke(Color.BLACK);
        setStrokeType(StrokeType.OUTSIDE);
    }
}
