import java.util.ArrayList;
import java.util.Collections;

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

    public CarteInfection piocherCarte(){
        CarteInfection temp = new CarteInfection(pioche.get(0));
        pioche.remove(0);
        return temp;
    }
    public void defausserCarte(CarteInfection carte){
        defausse.add(carte);

    }


}
