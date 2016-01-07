package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by Guillaume on 07/01/2016.
 */
public class PlayerHandCardSprite extends Rectangle{
    private int numero;

    public PlayerHandCardSprite(int numero){
        setWidth(50);
        setHeight(70);
        setFill(Color.DARKGRAY);
        this.numero = numero;
    }

}
