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
    private boolean testEclosion; // Permet de ne pas repasser plusieurs par la même UV en cas d'éclosion

    public UV(int position, String nom, Filiere filiere[]){
        this.position=position;
        this.nom=nom;
        this.filiere[0]=filiere[0];
        this.filiere[1]=filiere[1];

        testEclosion = true;
        marqueur=new Marqueur[3];
        voisins=new ArrayList<UV>();
        professeur=null;
        personnages=new ArrayList<Personnage>();
    }

    /*Accesseurs aux différents variable de UV*/

    public int getPosition(){
        return position;
    }

    public ArrayList<UV> getVoisins(){
        return  this.voisins;
    }

    public ArrayList<Personnage> getPersonnages(){return personnages;}

    public void addPersonnage(Personnage nouveauPersonnage){
        this.personnages.add(nouveauPersonnage);
    }

    public Personnage removePersonnage(){
        //enelver le personnage qui appel la fonction
    }

    public Filiere getFiliere(int i){
        if (filiere[i] == null){
            return null;
        }else{
            return filiere[i];
        }
    }

    public String getNom(){
        return this.nom;
    }

    public void setEclosion(boolean eclosion){
        this.testEclosion = eclosion;
    }
    public void setProfesseur(Professeur p){
        this.professeur=p;
    }
    public Professeur getProfesseur() {
        return professeur;
    }

    public void addMarqueur (Marqueur m){

        if (marqueur[2] != null){
            Graph.eclosion(this);
        }else{
            if(marqueur[0] == null){
                marqueur[0] = m;
            }else if (marqueur[1] == null){
                marqueur[1] = m;
            }else{
                marqueur[2] = m;
            }
        }
    }

    public Marqueur removeMarqueur(){
        Marqueur m = new Marqueur();

        if(marqueur[2] != null){
            m = marqueur[2];
            marqueur[2]=null;
        }else if(marqueur[1] != null){
            m = marqueur[1];
            marqueur[1] = null;
        }else if (marqueur[0] != null) {
            m = marqueur[0];
            marqueur[0] = null;
        }


        return m;
    }
}
