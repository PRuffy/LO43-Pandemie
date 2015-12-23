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
    private boolean projetI2RV;
    private boolean projetILC;
    private boolean projetLEIM;
    private boolean projetRT;
    private int chargeTravail;
    private int compteurEclosion;

    private static final int MAX_ECLOSION = 8;
    private static final int [] CHARGE_TRAVAIL = new int[]{2,2,3,3,4};


    /*constructeur de jeu. Prends les parametre suivant :
     * int nombre de joueur : Permet d'avoir le nombre de joueur désiré. Les différents rôles seront choisis aléatoirement
     * string listUV : Donnée après la lecture du document UV.txt, permet de générer les UV
     * int[][] adjacence : permet d'obtenir la liste des voisins
     * 
     */
    public Jeu(int nombreDeJoueurs){
        chargeTravail = CHARGE_TRAVAIL[0];
        compteurEclosion = 0;

        projetI2RV = false;
        projetILC = false;
        projetLEIM = false;
        projetRT = false;

        carteSemestre = new ReserveCarteSemestre();
        carteInfections = new ReserveCarteInfection();
        graph = new Graph();
        pionProfesseur = new ArrayList<Professeur>();
        reserveMarqueur = new CollectionMarqueur();
    }



    /*Augmente le nombre de marqueur sur une UV donnée*/
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


    /*Lors d'une éclosion la charge de travail augmente*/
    public void augmenterEclosion(){
        if(compteurEclosion != MAX_ECLOSION) {
            compteurEclosion++;
            chargeTravail = CHARGE_TRAVAIL[compteurEclosion];
        }
    }



    /*Enleve les marqueurs sur une uv donnée.
     *Si le joueur est le surdoué alors enlève tous les marqueurs*/
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


    /*Fonction exécutant la pioche des cartes semestre en fin de tour de joueur.
     *Si la carte est une carte TP elle est envoyé en main du joueur
     *Sauf si le joueur a atteint la limite de carte
     *Sinon si carte Bénéfique stockée dans l'Arraylist des cartes bénéfiques jusqu'à utilisation
     *Sinon déclenche une éclosion = Appel de la méthode éclosion
     */
    public void piocheSemestre(){
        CarteSemestre tempCarte = new CarteSemestre();
        for(int i = 0; i<2;i++){
            tempCarte = carteSemestre.piocherCarte();
            if(tempCarte.getType()==TypeCarteSemestre.TP){
                if(joueurActif.getMainComplete()){
                    carteSemestre.defausserCarte(tempCarte);
                }else{
                    try{
                        joueurActif.ajoutCarte(tempCarte);
                    }
                    /*
                    On catch les exceptions correspondant au manque de place dans la main du joueur et
                    aux erreurs de type de cartes possibles venant de la pioche
                    */
                    catch(NotEnoughSlotsException e){System.out.println("Le joueur n'a pas assez de place dans sa main");}
                    catch(WrongTypeException e){System.out.println("Les joueurs ne peuvent avoir que des cartes TP en main");}

                }
            }else if(tempCarte.getType() == TypeCarteSemestre.CC){
                carteSemestre.defausserCarte(tempCarte);
                eclosion();
            }else{
                cartesBénéfiques.add(tempCarte);
            }
        }
    }

    /*Méthode gérant les éclosions lors d'une pioche de carte CC
     */
    public void eclosion(){

    }

    /*Méthode piochant les cartes Infectionn en fin de tour*/
    public void piocheInfection(){
        int tempCompteur = 0;
        CarteInfection tempCarte = new CarteInfection();
        while(tempCompteur<chargeTravail){
            tempCarte = carteInfections.piocherCarte();
            //Si le projet est rendu alors la charge de travail n'augmente pas
            if(!testRenduProjet(tempCarte.getFiliere())){
                augmenterChargeTravail(tempCarte);
            }
            carteInfections.defausserCarte(tempCarte);
            tempCompteur++;
        }

    }
    public void donnerCarte(Joueur secondJoueur, Carte carteTransferer){

    }

    /*
     * Fonction déplacement un joueur. Reçoit un joueur à déplacer et la position où il doit arriver
     * N'effectue pas de test vis a vis de la position atteignable.
     * Peut déplacer le joueur Actif comme un autre joueur
     */
    public void deplacer(Joueur joueurCible,int positionArriver){
        joueurCible.setPosition(positionArriver);
        reduireAction();
    }

    /*Fonction ammenant le prof de la filière sur l'UV où se trouve l'élève*/
    public void appelerProf(){
        int temPositionJoueur = joueurActif.getPosition();
        Filiere f = graph.getUVFiliere(temPositionJoueur);
        reduireAction();
    }

    /*Fonction permettant de rendre un projet. Dépense des cartes.
     *Ne fonctionne que si un professeur se trouve sur la même case que le joueurActif*/
    public void rendreProjet(){

        reduireAction();
    }

    /*Set le nombre d'actions restantes du joueur actif a 0 pour ensuite lancer la fin du tour*/
    public void passer(){
        joueurActif.setNombreAction(0);
        finDeTour();
    }

    /*Renvoit le boolean associé au rendu de projet pour la filiere envoyé*/
    private boolean testRenduProjet(Filiere f){
        if (f == Filiere.RT){
            return projetRT;
        }else if(f == Filiere.I2RV){
            return projetI2RV;
        }else if (f == Filiere.LEIM){
            return projetLEIM;
        }else{
            return projetILC;
        }

    }
    /*Méthode réduisant le nombre d'action des joueurs dès qu'ils en font une. Appel fin de tour si le nombre tombe à 0*/
    public void reduireAction(){
        joueurActif.setNombreAction(joueurActif.getNombreAction()-1);

        if(joueurActif.getNombreAction()==0){
            finDeTour();
        }
    }
    /*Fonction qui remet toutes les valeurs comme a leur état initial et change le joueur Actif*/
    public void finDeTour() {
    }

}
