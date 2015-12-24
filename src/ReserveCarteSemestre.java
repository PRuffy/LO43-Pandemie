import java.util.ArrayList;
import java.util.Collections;


/**
 * Created by Guillaume on 12/12/2015.
 */
public class ReserveCarteSemestre implements ReserveCarte
{
    private ArrayList<CarteSemestre> pioche;
    private ArrayList<CarteSemestre> defausse;

    public ReserveCarteSemestre(){
        pioche=new ArrayList<CarteSemestre>();
        defausse=new ArrayList<CarteSemestre>();
    }

    public CarteSemestre piocherCarte() throws EmptyReserveCarteSemestreException {

        /*Si la réserve de Carte semestre est vide, on lance l'exception EmptyReserveCarteSemestreException, sinon piocher*/

        try {
            if (pioche.size() == 0) {
                throw new EmptyReserveCarteSemestreException("La pioche de Carte Semestre est vide ! Vous avez Perdu");
            } else {
                CarteSemestre temp = new CarteSemestre(pioche.get(0));
                pioche.remove(0);
                return temp;
            }

        }
        catch(EmptyReserveCarteSemestreException e ) { }
        /* Mon compilateur n'est pas trop d'accord là :/ Au niveau du catch il me dit qu'il n'est pas sûr que ma methode
        piocherCarte va renvoyer une CarteSemestre ou faire un Throw. Help ? */
    }



    public void defausserCarte(CarteSemestre carte){
        defausse.add(carte);
    }

    public void melangerCarte()
    {
        Collections.shuffle(pioche);

    }
}
