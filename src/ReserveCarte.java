import java.util.ArrayList;

/**
 * Created by Guillaume on 12/12/2015.
 */
public interface ReserveCarte
{
    Carte piocherCarte() throws EmptyReserveCarteSemestreException;

    public void melangerCarte();
    /* On avait dit que melangerCarte prenait une ArrayList en parametre mais vu qu'on mélange la pioche de la réserve
    * en question, je pense que c'est inutile non ?

    /*
     * Ne pas oublier d'implémenter defausserCarte et melangerCarte dans les classes implémentant ReserveCarte
     * 
     */

}



