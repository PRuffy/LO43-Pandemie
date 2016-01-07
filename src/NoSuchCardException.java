package model;

/**
 * Created by Guillaume on 20/12/2015.
 * Cette exception est utilisée dans la classe model.Joueur, méthode retraitCarte
 */
public class NoSuchCardException extends Exception{
    public NoSuchCardException(String exceptionMessage){
        super(exceptionMessage);
    }
}
