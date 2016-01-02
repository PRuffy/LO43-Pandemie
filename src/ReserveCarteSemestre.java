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
            pioche.add(new CarteSemestre(TypeCarteSemestre.TP, graph.getUV(i+1), graph.getUVFiliere(i+1)));
        }

        Collections.shuffle(pioche);
    }

    //Cette methode est utiliser en fin de constructeur de jeu pour rajouter les carte spécials afin qu'elle ne soit pas piocher par les joueurs
    public void completerPioche(){
        pioche.add(new CarteSemestre(TypeCarteSemestre.AntiPop, null,null));
        pioche.add(new CarteSemestre(TypeCarteSemestre.Fermeture, null,null));
        pioche.add(new CarteSemestre(TypeCarteSemestre.Transfert, null,null));
        pioche.add(new CarteSemestre(TypeCarteSemestre.Prevision, null,null));

        for(int i = 0; i <4; i++){
            pioche.add(new CarteSemestre(TypeCarteSemestre.CC, null,null));
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
