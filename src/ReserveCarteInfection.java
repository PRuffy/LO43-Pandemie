package model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Guillaume on 12/12/2015.
 */
public class ReserveCarteInfection implements ReserveCarte
{
    private ArrayList<CarteInfection> pioche;
    private ArrayList<CarteInfection> defausse;

    public ReserveCarteInfection(Graph graph){
        pioche=new ArrayList<CarteInfection>();
        defausse=new ArrayList<CarteInfection>();

        for(int i = 0; i < 24; i++){
            pioche.add(new CarteInfection(graph.getUV(i+1), graph.getUVFiliere(i+1)));
        }

        Collections.shuffle(pioche);

    }

    public CarteInfection piocherCarte(){
        CarteInfection temp = new CarteInfection(pioche.get(0));
        pioche.remove(0);
        return temp;
    }
    public void defausserCarte(CarteInfection carte){
        defausse.add(carte);

    }

    public int getSizePioche(){ return pioche.size(); }
    public int getSizeDefausse(){
        return defausse.size();
    }


    public CarteInfection getLast(){
        CarteInfection temp = new CarteInfection(pioche.get(pioche.size()-1));
        pioche.remove(pioche.size()-1);
        return temp;
    }
    public void removeCarte(int i){
        defausse.remove(i);
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
