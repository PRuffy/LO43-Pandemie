import java.util.ArrayList;

public class Jeu {
    private ReserveCarteSemestre carteSemestre;
    private Graph graph;
    private CollectionMarqueur reserveMarqueur;
    private ReserveCarteInfection carteInfections;
    private ArrayList<Personnage> pionPersonnage;
    private ArrayList<Professeur> pionProfesseur;
    private int chargeTravail;
    private int compteurEclosion;
    private int carteCC;

    private static final int MAX_ECLOSION = 8;
    private static final int [] CHARGE_TRAVAIL = new int[]{2,2,3,3,4};

    public Jeu(){
        chargeTravail = 0;
        compteurEclosion = 0;
        carteSemestre = new ReserveCarteSemestre();
        carteInfections = new ReserveCarteInfection();
        graph = new Graph();
        pionProfesseur = new ArrayList<Professeur>();
        pionPersonnage = new ArrayList<Personnage>();
        reserveMarqueur = new CollectionMarqueur();
    }

    public void augmenterEclosion(){
        if(compteurEclosion != MAX_ECLOSION) {
            compteurEclosion++;
        }
    }
    public void travailler(Personnage p, Role rolePersonnage){
        int positionPersonnage = p.getPosition();
        graph.travail(positionPersonnage, rolePersonnage);
    }

    public void donnerCarte(Joueur secondJoueur, Carte carteTransferer){

    }

    public void deplacer(){}

    public void appelerProf(){}

    public void rendreProjet(){}

    public void passer(){}

}
