import java.util.ArrayList;
import java.util.Collections;


/**
 * Created by Guillaume on 12/12/2015.
 */
public class ReserveCarteSemestre implements ReserveCarte
{
    private ArrayList<CarteSemestre> pioche;
    private ArrayList<CarteSemestre> defausse;

    public ReserveCarteSemestre(Graph graph){
        pioche=new ArrayList<CarteSemestre>();
        defausse=new ArrayList<CarteSemestre>();

        for(int i = 0; i < 24; i++){
            pioche.add(new CarteSemestre(TypeCarteSemestre.TP, graph.getUV(i+1), graph.getUVFiliere(i+1)));
        }

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
