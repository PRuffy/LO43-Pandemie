package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by Guillaume on 07/01/2016.
 */
public class DeckSprite extends Rectangle {
    private int numero;

    public DeckSprite(int numero){
        super();
        setWidth(70);
        setHeight(70);
        setFill(Color.DARKGRAY);
        this.numero = numero;
    }
}
