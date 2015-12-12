import java.util.ArrayList;

/**
 * Created by Guillaume on 12/12/2015.
 */
public class ReserveCarteInfection implements ReserveCarte
{
    private ArrayList<CarteInfection> pioche;
    private ArrayList<CarteInfection> defausse;

    public ReserveCarteInfection(){
        pioche=new ArrayList<CarteInfection>();
        defausse=new ArrayList<CarteInfection>();
    }

    public Carte piocherCarte(){
        CarteInfection temp = new CarteInfection(pioche.get(0));
        pioche.remove(0);
        return temp;
    }
    public void defausserCarte(CarteInfection carte){
        defausse.add(carte);

    }
    public void melangerCarte(ArrayList<CarteInfection> list){
        /*
         * A FINIR
         * Cette méthode doit prendre la liste en paramètre et changer de manière pseudo-aléatoire l'ordre de ses éléments
         */
    }

}
