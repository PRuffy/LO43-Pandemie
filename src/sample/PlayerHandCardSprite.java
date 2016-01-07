package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;

/**
 * Created by Guillaume on 07/01/2016.
 */
public class PlayerHandCardSprite extends Rectangle{
    private int numero;

    public PlayerHandCardSprite(int numero){
        setWidth(50);
        setHeight(70);
        setFill(Color.DARKGRAY);
        setStrokeWidth(1);
        setStrokeLineCap(StrokeLineCap.SQUARE);
        setStroke(Color.BLACK);
        setStrokeType(StrokeType.OUTSIDE);
        this.numero = numero;
    }

    public int getNumero(){ return numero; }

}
