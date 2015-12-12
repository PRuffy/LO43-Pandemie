import java.util.ArrayList;

/**
 * Created by Guillaume on 12/12/2015.
 */
public class UV
{

    private int position;
    private String nom;
    private Filiere filiere[];
    private Marqueur marqueur[];
    private ArrayList<UV> voisins;
    private Professeur professeur;
    private ArrayList<Personnage> personnages;

    public UV(int position, String nom, Filiere filiere[]){
        this.position=position;
        this.nom=nom;
        this.filiere[0]=filiere[0];
        this.filiere[1]=filiere[1];

        marqueur=new Marqueur[3];
        voisins=new ArrayList<UV>();
        professeur=null;
        personnages=new ArrayList<Personnage>();
    }

}
