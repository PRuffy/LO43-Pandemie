import java.util.ArrayList;

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

    public Carte piocherCarte(){
        CarteSemestre temp = new CarteSemestre(pioche.get(0));
        pioche.remove(0);
        return temp;
    }
    public void defausserCarte(CarteSemestre carte){
        defausse.add(carte);
    }
    public void melangerCarte(ArrayList<CarteSemestre> list){
        /*
         * A FINIR
         * Cette méthode doit prendre la liste en paramètre et changer de manière pseudo-aléatoire l'ordre de ses éléments
         */
    }
}
