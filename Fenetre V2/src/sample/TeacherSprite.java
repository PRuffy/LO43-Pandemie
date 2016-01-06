package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import model.Filiere;
import model.WrongTypeException;


public class TeacherSprite extends Rectangle {
    private Filiere filiere;
    private int numero;

    public TeacherSprite(double x, double y, int numero, Filiere filiere){
        super(x, y, 8, 8);
        this.numero=numero;
        this.filiere = filiere;
        setFill(Color.HOTPINK);
        setStrokeWidth(1);
        setStroke(getColor(filiere));
        setStrokeType(StrokeType.OUTSIDE);
    }

    public int getNumero(){return numero;}

    private Color getColor(Filiere filiere){
        try{
            switch(filiere){
                case ILC : return Color.WHEAT;
                case I2RV : return Color.LIGHTBLUE;
                case RT : return Color.LIGHTGREEN;
                case LEIM : return Color.PINK;
                default : throw new WrongTypeException("Erreur de type de filière dans la Classe TeacherSprite");
            }
        }catch(WrongTypeException e){return null;}

    }
}
