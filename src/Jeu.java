import java.util.ArrayList;
import java.util.Random;

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
    private int nombreProjetRendu;

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
        nombreProjetRendu = 0;

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
        }else{
            defaitePartie();
        }
    }



    /*Enleve les marqueurs sur une uv donnée.
     *Si le joueur est le surdoué alors enlève tous les marqueurs*/
    public void travailler(){
        int positionPersonnage = joueurActif.getPosition();
        UV uvTravail = graph.getUV(positionPersonnage);
        Marqueur m = new Marqueur();

        //Test du role du joueur si surdoué alors tout les marqueurs sont enlevé
        if (joueurActif.getRole()==Role.surdoue){
            while(uvTravail.getNombreMarqueur()!=0){
                m = graph.travail(positionPersonnage);
                reserveMarqueur.setMarqueur(m);
            }
        }else{
            //Sinon on enlève un marqueur.
            //Partie a potentiellement modifié pour géré le cas ou le projet a été rendu
            m = graph.travail(positionPersonnage);
            reserveMarqueur.setMarqueur(m);
        }

    }

    //Methode de travail par défaut lorsque le surdoué arrive sur une case et que des marqueur d'un type précis sont sur la case
    public void travailler(int position, Filiere f){
        if(testRenduProjet(f)){
            Marqueur m = null;


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
        //On pioche deux cartes par tour
        for(int i = 0; i<2;i++){
            //Si la pioche tombe a 0 carte on déclanche la défaite sinon on pioche une carte
            if (carteSemestre.getSizePioche() != 0) {
                tempCarte = carteSemestre.piocherCarte();
                //Si on a une carte TP alors on test si la main du joueur actif est complète
                //Si ce n'est pas le cas on met la carte dans la main du joueur
                //sinon on la défausse
                if (tempCarte.getType() == TypeCarteSemestre.TP) {
                    if (joueurActif.getMainComplete()) {
                        carteSemestre.defausserCarte(tempCarte);
                    } else {
                        try{
                            joueurActif.ajoutCarte(tempCarte);
                        }catch(WrongTypeException e){}
                        catch(NotEnoughSlotsException e){}
                    }


                //Si la carte est une carte CC alors on déclanche une éclosion
                    // Sinon on rajoute la carte dans la liste des carte bénéfique
                } else if (tempCarte.getType() == TypeCarteSemestre.CC) {
                    carteSemestre.defausserCarte(tempCarte);
                    eclosion();

                } else {
                    cartesBénéfiques.add(tempCarte);
                }
            }else{
                defaitePartie();
            }
        }
    }

    /*Méthode gérant les éclosions lors d'une pioche de carte CC
     */
    public void eclosion(){
        carteInfections.melangeDefaussePioche();
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

    /*Prend la carte que le premier joueur veux donner et l'envoi dans la main du second joueur
     *Si celui-ci n'a pas une main complète. Sinon ne fait rien.
     */
    public void donnerCarte(Joueur secondJoueur, CarteSemestre carteTransferer){
        if(!secondJoueur.getMainComplete()){
            try{
                secondJoueur.ajoutCarte(carteTransferer);
                joueurActif.retraitCarte(carteTransferer);
            }catch(WrongTypeException e){}
            catch(NotEnoughSlotsException e){}
            catch(NoSuchCardException e){}
        }
    }

    /*
     * Fonction déplacement un joueur. Reçoit un joueur à déplacer et la position où il doit arriver
     * N'effectue pas de test vis a vis de la position atteignable.
     * Peut déplacer le joueur Actif comme un autre joueur
     */
    public void deplacer(Joueur joueurCible,int positionArriver){
        joueurCible.setPosition(positionArriver);
        if(joueurCible.getRole()==Role.surdoue){
            verifierMarqueurDest(joueurCible, positionArriver);
        }
        reduireAction();
    }

    //Cette fonction vérifie que le contenu de la case ou le joueur arrive
    //Si la case est vide ne fait rien
    //Si elle contient des marqueur mais que le projet n'est pas rendu ne fait rien
    //Si elle contient des marqueur et que le projet est rendu lance un travail
    //Ne fonctionne que avec le surdoue
    //Appel une fonction travail secondaire
    public void verifierMarqueurDest (Joueur joueurCile, int position){
        UV uv = graph.getUV(position);
        if(uv.hasMarqueur()){
            ArrayList<Marqueur> marqueursUV = new ArrayList<Marqueur>();
            marqueursUV = uv.getMarqueurs();

            Filiere f;
            for(Marqueur m : marqueursUV){
                f = m.getFiliere();
                if(testRenduProjet(f)){
                    travailler(position, f);
                    marqueursUV = uv.getMarqueurs();
                }
            }
        }
    }
    //Fonction vérfiant la liste des déplacement possible pour un joueur donné
    public ArrayList<Integer> deplacementPossible(Joueur joueurCible){
        ArrayList<Integer> ciblePossible = new ArrayList<Integer>();

        //Si le joueur a la carte de sa position actuelle il pourra aller partout.
        int posJoueur = joueurCible.getPosition();
        UV uvJoueur = null;
        uvJoueur = graph.getUV(posJoueur);

        //Le joueur a la carte de la position ou il se trouve et peux se déplacer n'importe ou
        if(joueurCible.hasCarte(uvJoueur)){
            for(int i = 0; i<graph.getListSize();i++){
                if(i!=posJoueur){
                    ciblePossible.add(i);
                }
            }
        }

        //Sinon vérifier sa position, ajouter les voisin, ajouter les carte présente en main et les autre proffesseur si présence de l'un d'eux

        return ciblePossible;
    }

    /*Fonction ammenant le prof de la filière sur l'UV où se trouve l'élève*/
    public void appelerProf(){
        int temPositionJoueur = joueurActif.getPosition();
        Filiere f = graph.getUVFiliere(temPositionJoueur);
        //Parcours des professeur pour trouver celui ayant la bonne filière
        for (Professeur p : pionProfesseur){
            if(p.getFiliere() == f){
                p.setPosition(temPositionJoueur);
            }
        }
        reduireAction();
    }

    /*Fonction permettant de rendre un projet. Dépense des cartes.
     *Ne fonctionne que si un professeur se trouve sur la même case que le joueurActif
     *Vas tenter de rendre le projet correspondant a la filière ou se trouve le proffesseur*/
    public void rendreProjet(){
        /*On test si un prof est sur la même case que le joueur actif*/
        if(presenceProf()){
            Filiere f = null;
            for(Professeur p : pionProfesseur){
                if(p.getPosition() == joueurActif.getPosition()){
                    f = p.getFiliere();
                }
            }
            if(!testRenduProjet(f)){
                if(joueurActif.verifierCarte(f)){

                    if(joueurActif.getRole()==Role.surdoue){
                        for(int i =0; i < 3 ; i++){
                            carteSemestre.defausserCarte(joueurActif.retraitCarte(f));
                        }
                    }else{
                        for(int i =0; i < 4 ; i++){
                            carteSemestre.defausserCarte(joueurActif.retraitCarte(f));
                        }
                    }

                    nombreProjetRendu++;
                    if(nombreProjetRendu == 4){
                        victoirePartie();
                    }
                    reduireAction();
                }

            }
        }

    }


    /*Fonction comparant la coordonée du joueur actif avec celle des différents professeur*/
    public boolean presenceProf(){
        boolean test;
        for(Professeur p : pionProfesseur){
            if(p.getPosition()==joueurActif.getPosition()){
                test = true;
            }
        }
        test = false;

        return test;
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




    //Bloc de fin de tour
    /*Fonction qui remet toutes les valeurs comme a leur état initial et change le joueur Actif*/
    public void finDeTour() {

        //On fait piocher le joueur actif
        piocheSemestre();

        //On pioche les cartes infection
        piocheInfection();

        //appel de la méthode finTour de graph qui s'occupe de reset les valeurs de base des UV (par ex: eclosion)
        graph.finTour();

        //On remet le nombre d'action a 4 en prévision du prochain tour du joueur.
        joueurActif.setNombreAction(4);

        //On change le joueur actif.
        //Si joueur actif est le dernier joueur du tableau alors on reprend le premier
        //Sinon on prend le suivant
        if(joueurActif == joueurs[joueurs.length-1]){
            joueurActif = joueurs[0];
        }else{
            int parcoursJoueur = 0;
            while(joueurActif != joueurs[parcoursJoueur]){
                parcoursJoueur++;
            }
            joueurActif = joueurs[parcoursJoueur+1];
        }

        //On déplace tout les prof aléatoirement si ils n'ont pas bouger.
        //On s'assure aussi de ne pas les faire changer ed filière
        Random r = new Random();

        for(Professeur p : pionProfesseur){
            if(p.isDeplace()){
                p.setDeplace(false);
            }else{
                Filiere filiereProf = p.getFiliere();
                int positionProf = p.getPosition();

                //on récupère l'uv ou se trouve le prof
                UV uv = graph.getUV(positionProf);
                //On récupère le nombre de voisin
                int nombreVoisin = uv.getNombreVoisins();
                int uvCible;

                do{
                    // On choisis la case cible aléatoirement dans le tableau des voisins
                    uvCible = r.nextInt(nombreVoisin);
                }while(!uv.correctDestinationProf(filiereProf, uvCible));
                UV uvDest = graph.getUV(uvCible);
                uv.setProfesseur(null);
                uvDest.setProfesseur(p);
                p.setPosition(uvCible);


            }
        }
    }


    //Bloc de fin de partie
    /*Méthode déclanchant la fin du jeu lors d'une défaite*/
    //Doit bloquer les éléments graphique ainsi qu'annoncer la défaite
    public void defaitePartie(){

    }

    /*Méthode déclanchant la fin de la partie lors d'une victoire*/
    //Doit bloquer les éléments graphique ainsi qu'annoncer la victoire
    public void victoirePartie(){

    }

}
