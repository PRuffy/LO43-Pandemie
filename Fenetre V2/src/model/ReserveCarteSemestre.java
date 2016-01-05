package model;

import java.util.ArrayList;
import java.util.Collections;

public class ReserveCarteSemestre implements ReserveCarte
{
    private ArrayList<CarteSemestre> pioche;
    private ArrayList<CarteSemestre> defausse;

    //Cette méthode construit les objet pioche et defausse et ajoute les carte TP a la pioche
    public ReserveCarteSemestre(Graph graph){
        pioche=new ArrayList<CarteSemestre>();
        defausse=new ArrayList<CarteSemestre>();

        for(int i = 0; i < 24; i++){
            pioche.add(new CarteSemestre(TypeCarteSemestre.TP, graph.getUV(i), graph.getUVFiliere(i)));
        }

        Collections.shuffle(pioche);
    }

    //Cette methode est utiliser en fin de constructeur de jeu pour rajouter les carte spécials afin qu'elle ne soit pas piocher par les joueurs
    public void completerPioche(){
        pioche.add(new CarteSemestre(TypeCarteSemestre.AntiPop, null,null));



        for(int i = 0; i <4; i++){
            pioche.add(new CarteSemestre(TypeCarteSemestre.CC, null,null));
            pioche.add(new CarteSemestre(TypeCarteSemestre.Fermeture, null,null));
        }

        Collections.shuffle(pioche);
    }

    public CarteSemestre piocherCarte(){
        CarteSemestre temp = new CarteSemestre(pioche.get(0));
        pioche.remove(0);
        return temp;
    }

    public void defausserCarte(CarteSemestre carte){
        defausse.add(carte);
    }

    public int getSizePioche(){
        return pioche.size();
    }

}
