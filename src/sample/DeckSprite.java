package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;

/**
 * Created by Guillaume on 07/01/2016.
 */
public class DeckSprite extends Rectangle {
    private int numero;

    public DeckSprite(int numero){
        super();
        setWidth(70);
        setHeight(70);
        setFill(Color.IVORY);
        setStrokeWidth(3);
        setStrokeLineCap(StrokeLineCap.SQUARE);
        setStroke(Color.BLACK);
        setStrokeType(StrokeType.OUTSIDE);
        this.numero = numero;
    }
}
