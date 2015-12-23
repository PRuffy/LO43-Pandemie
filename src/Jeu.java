import java.util.ArrayList;

public class Jeu {
    private Joueur[] joueurs;
    private Joueur joueurActif;
    private ArrayList<CarteSemestre> cartesBénéfiques;
    private Graph graph;
    private CollectionMarqueur reserveMarqueur;
    private ReserveCarteSemestre carteSemestre;
    private ReserveCarteInfection carteInfections;
    private ArrayList<Professeur> pionProfesseur;
    private int chargeTravail;
    private int compteurSurchargeTravail;
    private int carteCC;

    private static final int MAX_ECLOSION = 8;
    private static final int [] CHARGE_TRAVAIL = new int[]{2,2,3,3,4};

    public Jeu(int nombreDeJoueurs){
        chargeTravail = CHARGE_TRAVAIL[0];
        compteurSurchargeTravail = 0;
        carteCC = 0;

        carteSemestre = new ReserveCarteSemestre();
        carteInfections = new ReserveCarteInfection();
        graph = new Graph();
        pionProfesseur = new ArrayList<Professeur>();
        reserveMarqueur = new CollectionMarqueur();
    }




    public void augmenterChargeTravail(CarteInfection carte){
        Filiere carteFilière = carte.getFiliere();
        UV cible = carte.getCible();
        Marqueur m = new Marqueur(reserveMarqueur.getMarqueur(carteFilière));
        cible.addMarqueur(m);

        if (cible.getEclosion()){
            augmenterEclosion();
            graph.eclosion(cible, carteFilière, reserveMarqueur);
        }
    }



    public void augmenterEclosion(){
        if(compteurSurchargeTravail != MAX_ECLOSION) {
            compteurSurchargeTravail++;
            chargeTravail = CHARGE_TRAVAIL[compteurSurchargeTravail];
        }
    }



    public void travailler(){
        int positionPersonnage = joueurActif.getPosition();
        UV uvTravail = graph.getUV(positionPersonnage);
        Marqueur m = new Marqueur();
        if (joueurActif.getRole()==Role.surdoue){
            while(uvTravail.getNombreMarqueur()!=0){
                m = graph.travail(positionPersonnage);
                reserveMarqueur.setMarqueur(m);
            }
        }else{
            m = graph.travail(positionPersonnage);
            reserveMarqueur.setMarqueur(m);
        }

    }

    public void piocheSemestre(){
        CarteSemestre tempCarte = new CarteSemestre();
        for(int i = 0; i<2;i++){
            tempCarte = carteSemestre.piocherCarte();
            if(tempCarte.getType()==TypeCarteSemestre.TP){
                if(joueurActif.getMainComplete()){
                    carteSemestre.defausserCarte(tempCarte);
                }else{
                    joueurActif.ajoutCarte(tempCarte);
                }
            }else if(tempCarte.getType() == TypeCarteSemestre.CC){
                carteSemestre.defausserCarte(tempCarte);
                eclosion();
            }else{
                cartesBénéfiques.add(tempCarte);
            }
        }
    }

    public void eclosion(){

    }
    
    public void piocheInfection(){
        int tempCompteur = 0;
        CarteInfection tempCarte = new CarteInfection();
        while(tempCompteur<chargeTravail){
            tempCarte = carteInfections.piocherCarte();
            augmenterChargeTravail(tempCarte);
            carteInfections.defausserCarte(tempCarte);
            tempCompteur++;
        }

    }
    public void donnerCarte(Joueur secondJoueur, Carte carteTransferer){

    }

    public void deplacer(Joueur joueurCible,int positionArriver){
        joueurCible.setPosition(positionArriver);
    }

    public void appelerProf(){}

    public void rendreProjet(){}

    public void passer(){
        joueurActif.setNombreAction(0);
    }

}
