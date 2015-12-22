import java.util.ArrayList;

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

        testEclosion = false;
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

    public void removePersonnage(Personnage personnageToRemove){
        personnages.remove(personnageToRemove);
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
    public boolean getEclosion(){return this.testEclosion;}
    public void setProfesseur(Professeur p){
        this.professeur=p;
    }
    public Professeur getProfesseur() {
        return professeur;
    }


    /*Recupere un marqueur de la collection correspondante*/
    public void addMarqueur (Marqueur m){

        if (marqueur[2] != null){
            setEclosion(true);
        }else{
            if(marqueur[0] == null){
                marqueur[0] = m;
            }else if (marqueur[1] == null){
                marqueur[1] = m;
            }else{
                marqueur[2] =m;
            }
        }
    }

    /*Enlève un marqueur et le replace dans la collection ou il appartient*/
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

    /*Est appelé si le joueur est médecin.
     *Appel la fonction removeMarqueur de base tant que la case n'est pas vide
     */
    public void removeMarqueur(int i){
        while(marqueur[0]!=null){
            removeMarqueur();
        }
    }
}
