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

    //Méthode appelé lors d'une éclosion par CC
    //Vas mélanger la défausse puis replacer toute les carte ainsi mélanger au dessus de la pioche
    public void melangeDefaussePioche(){
        if(!defausse.isEmpty()){
            CarteInfection tempCarte = new CarteInfection();
            Collections.shuffle(defausse);
            while(!defausse.isEmpty()){
                tempCarte = defausse.get(0);
                defausse.remove(0);
                pioche.add(0,tempCarte);
            }
        }
    }

}
