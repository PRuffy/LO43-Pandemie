package model;

import java.util.ArrayList;
import java.util.Random;

public class Jeu {
    private Joueur[] joueurs;
    private Joueur joueurActif;
    private ArrayList<CarteSemestre> cartesBenefiques;
    private Graph graph;
    private CollectionMarqueur reserveMarqueur;
    private ReserveCarteSemestre carteSemestre;
    private ReserveCarteInfection carteInfections;
    private ArrayList<Professeur> pionProfesseur;
    private boolean projetI2RV;
    private boolean projetILC;
    private boolean projetLEIM;
    private boolean projetRT;
    private boolean antipioche;
    private int chargeTravail;
    private int compteurEclosion;
    private int nombreProjetRendu;


    private static final int MAX_ECLOSION = 8;
    private static final int [] CHARGE_TRAVAIL = new int[]{2,2,3,3,4};

    private boolean DEBUG = true;


    /*constructeur de jeu. Prends les parametre suivant :
     * int nombre de joueur : Permet d'avoir le nombre de joueur désiré. Les différents rôles seront choisis aléatoirement
     * string listUV : Donnée après la lecture du document model.UV.txt, permet de générer les model.UV
     * int[][] adjacence : permet d'obtenir la liste des voisins
     * 
     */
    public Jeu(int nombreDeJoueurs, DataReader dat){
        chargeTravail = CHARGE_TRAVAIL[0];
        compteurEclosion = 0;
        nombreProjetRendu = 0;

        projetI2RV = false;
        projetILC = false;
        projetLEIM = false;
        projetRT = false;
        antipioche = false;

        //Creation du graph puis des cartes
        graph = new Graph(dat);
        carteSemestre = new ReserveCarteSemestre(graph);
        carteInfections = new ReserveCarteInfection(graph);
        cartesBenefiques = new ArrayList<>();

        //Creation des professeur
        pionProfesseur = new ArrayList<Professeur>();
        pionProfesseur.add(new Professeur(Filiere.ILC, 1));
        pionProfesseur.add(new Professeur(Filiere.I2RV, 9));
        pionProfesseur.add(new Professeur(Filiere.LEIM, 17));
        pionProfesseur.add(new Professeur(Filiere.RT, 24));

        joueurActif = null;
        joueurs = new Joueur[nombreDeJoueurs];

        //Utilisation d'un random pour sélectionner les roles
        Random rand = new Random();

        //Creation d'une arrayListe qui contient tout les roles possible
        ArrayList<Role> roleDisp = new ArrayList<Role>();
        roleDisp.add(Role.chefProjet);
        roleDisp.add(Role.decale);
        roleDisp.add(Role.etudiantEtranger);
        roleDisp.add(Role.lecheBotte);
        roleDisp.add(Role.surdoue);

        int random=0;
        Role roleJoueur;
        CarteSemestre carte = null;
        for(int i = 0; i < nombreDeJoueurs; i++){
            //On prend un role aléatoirement dans l'arrayListe
            //Le role est enlever de l'arrayliste
            random = rand.nextInt(roleDisp.size());
            roleJoueur = roleDisp.remove(random);
            joueurs[i] = new Joueur(roleJoueur, i);
            joueurActif = joueurs[i];

            //On pioche des cartes qu'on met dans la main des joueurs
            switch(nombreDeJoueurs){
                //2 Cartes par joueurs
                case 4:
                    for(int j = 0; j < 2 ;j++){
                        carte = carteSemestre.piocherCarte();
                        try {
                            joueurActif.ajoutCarte(carte);
                        } catch (NotEnoughSlotsException e) {
                            e.printStackTrace();
                        } catch (WrongTypeException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                //3 cartes par joueurs
                case 3:
                    for(int j = 0; j < 3 ;j++){
                        carte = carteSemestre.piocherCarte();
                        try {
                            joueurActif.ajoutCarte(carte);
                        } catch (NotEnoughSlotsException e) {
                            e.printStackTrace();
                        } catch (WrongTypeException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                //4 cartes par joueurs
                case 2:
                    for(int j = 0; j < 4 ;j++){
                        carte = carteSemestre.piocherCarte();
                        try {
                            joueurActif.ajoutCarte(carte);
                        } catch (NotEnoughSlotsException e) {
                            e.printStackTrace();
                        } catch (WrongTypeException e) {
                            e.printStackTrace();
                        }
                    }
                    break;

                default:
                    break;
            }

            joueurActif.setNombreAction(4);
        }

        //On créer la réserve de marqueur
        reserveMarqueur = new CollectionMarqueur();

        //On complete la pioche avec les carte benefique et les carteCC
        carteSemestre.completerPioche();
        joueurActif = joueurs[0];

    }



    //Augmente le nombre de marqueur sur une model.UV donnée
    public void augmenterChargeTravail(CarteInfection carte){
        Filiere filiere = carte.getFiliere();
        UV cible = carte.getCible();

        if(cible.getNombreMarqueur()<3){
            Marqueur m = null;
            try{
                m=reserveMarqueur.getMarqueur(filiere);
            }catch(GameOverException e){defaitePartie();}
            cible.addMarqueur(m);
        }else{
            cible.setEclosion(true);
        }


        if (cible.getEclosion()){
            makeEclosion(cible, filiere, new ArrayList<>());
        }
    }



    /*Lors d'une éclosion la charge de travail augmente*/
    public void augmenterEclosion()throws GameOverException{
        if(compteurEclosion < MAX_ECLOSION) {
            compteurEclosion++;
            chargeTravail = CHARGE_TRAVAIL[compteurEclosion];
        }else{
            throw new GameOverException("You loose!");
        }
    }



    /*Enleve les marqueurs sur une uv donnée.
     *Si le joueur est le surdoué alors enlève tous les marqueurs*/
    public void travailler(){
        int positionPersonnage = joueurActif.getPosition();
        UV uvTravail = graph.getUV(positionPersonnage);
        Marqueur m = new Marqueur();

        //Test du role du joueur si surdoué alors tout les marqueurs sont enlevé
        if (joueurActif.getRole()==Role.surdoue || testRenduProjet(uvTravail.getFiliere())){
            while(uvTravail.getNombreMarqueur()!=0){
                m = graph.travail(positionPersonnage);
                reserveMarqueur.setMarqueur(m);
            }
        }else{
            //Sinon on enlève un marqueur.
            //Partie a potentiellement modifié pour gérer le cas ou le projet a été rendu
            m = graph.travail(positionPersonnage);
            reserveMarqueur.setMarqueur(m);
        }
        reduireAction();

    }

    //Methode de travail par défaut lorsque le surdoué arrive sur une case et que des marqueur d'un type précis sont sur la case
    public void travailler(int position, Filiere f){
        if(testRenduProjet(f)){
            if(graph.uvHasMarqueur(position)){
                graph.travail(position, f, reserveMarqueur);
            }

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
                    if (joueurActif.isMainComplete()) {
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
                    eclosion(tempCarte);

                } else {
                    cartesBenefiques.add(tempCarte);
                }
            }else{
                defaitePartie();
            }
        }
    }


    //Methode qui se déclenche quand un joueur utilise une carte spécial
    public void utiliserCarteBenefique(CarteSemestre c){
        for(CarteSemestre carte : cartesBenefiques){
            if(carte.getType() == c.getType()){
                cartesBenefiques.remove(carte);
                switch (carte.getType()){
                    case AntiPop:
                        //Passe un booléen a vrai qui bloque la pioche infection en fin de tour
                        antipioche = true;
                        break;
                    case Fermeture:
                        //Déclenche une methode pour retirer une carte de la défausse dans les carte infection
                        fermetureUV();
                        break;
                    default: break;
                }

                carteSemestre.defausserCarte(carte);
            }
        }
    }

    //Enleve une carte infection de la defausse
    public void fermetureUV(){
        //On vérifie que la defause n'est pas vide.
        if(carteInfections.getDefausseSize()>0){
            //Si la defausse contient effectivement une carte on en enlève une aléatoirement
            Random rand = new Random();
            int numeroCarte = rand.nextInt(carteInfections.getDefausseSize());
            carteInfections.removeCarte(numeroCarte);
        }

    }

    /*
        Méthode gérant les éclosions lors d'une pioche de carte CC
     */
    public void eclosion(CarteSemestre carteCC){
        //On récupère la dernière carte de la pioche Infection
        CarteInfection carte = carteInfections.getLast();


            int i = 0;
            boolean eclos = false;
            Marqueur m = null;
            while(i<3 || carte.getCible().getNombreMarqueur() <3){
                i++;
                try{
                    m = reserveMarqueur.getMarqueur(carte.getFiliere());
                }catch (GameOverException e){defaitePartie();}
                carte.getCible().addMarqueur(m);
                if (carte.getCible().getNombreMarqueur()==3){
                    eclos =true;
                }

            }

            if(eclos){
                // on créé une liste des model.UV touchées par l'éclosion, qui sera remplie par les appels multiples de makeEclosion
                ArrayList<UV> dejaVisite = new ArrayList<>();

                try {
                    augmenterEclosion();
                } catch (GameOverException e) {defaitePartie();}

                //on fait un appel de makeEclosion, càd on lance les ajouts de marqueurs
                makeEclosion(carte.getCible(), carte.getFiliere(), dejaVisite);

                // On reprends la liste des model.UV touchées et on remet leurs booléen d'éclosion à false
                for(UV cible : dejaVisite){
                    cible.setEclosion(false);
                }

                //On mélange les cartes de la défausse Infection dans la pioche correspondante.
                carteInfections.defausserCarte(carte);
                carteInfections.melangeDefaussePioche();
            }




    }

    /*
        Méthode récursive gérant l'ajout de marqueurs du aux éclosions sur les model.UV et leurs voisins (en cas de chaine d'éclosion);7
        makeEclosion prend, en paramètre, une model.UV cible, la filière du marqueur à ajouter et la liste des model.UV déjà touchées par cette chaine d'éclosion
     */
    private void makeEclosion(UV cible, Filiere filiere, ArrayList<UV> dejaVisite){
        //Si l'model.UV cible n'a pas encore été touchée dans cette chaine
        if(!dejaVisite.contains(cible)){
            try{
                //On ajoute l'uv a la liste déjà visiter
                dejaVisite.add(cible);

                //Si l'uv n'a pas encore eu d'éclosion
                if(!cible.getEclosion()){
                    //Si l'uv a déjà trois marqueur alors on appel la fonction pour ses voisins
                    if(cible.getNombreMarqueur() == 3){
                        cible.setEclosion(true);
                        for(UV uv : cible.getVoisins()){
                            makeEclosion(uv, filiere, dejaVisite);
                        }

                    }else{
                        //Sinon on ajoute un marqueur
                        Marqueur m = reserveMarqueur.getMarqueur(filiere);
                        cible.addMarqueur(m);
                        cible.setEclosion(true);
                    }

                }
            }catch(GameOverException e){System.out.println("You loose!");}
        }
    }

    /*Méthode piochant les cartes Infection en fin de tour*/
    public void piocheInfection(){
        int tempCompteur = 0;
        CarteInfection tempCarte;
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
     * Fonctionne tout le temps si l'étudiant étranger donne la carte. Sinon doivent être sur la même case
     */
    public void donnerCarte(Joueur secondJoueur, CarteSemestre carteTransferer){
        if(joueurActif.getRole()==Role.etudiantEtranger || joueurActif.getPosition() == secondJoueur.getPosition()){
            if(!secondJoueur.isMainComplete()){
                try{
                    secondJoueur.ajoutCarte(carteTransferer);
                    joueurActif.retraitCarte(carteTransferer);
                    reduireAction();
                }catch(WrongTypeException e){}
                catch(NotEnoughSlotsException e){}
                catch(NoSuchCardException e){}
            }
        }

    }

    /*
     * Fonction déplacement un joueur. Reçoit un joueur à déplacer et la position où il doit arriver
     * N'effectue pas de test vis a vis de la position atteignable.
     * Peut déplacer le joueur Actif comme un autre joueur
     */
    public void deplacer(Joueur joueurCible, int positionArrivee){
        joueurCible.setPosition(positionArrivee);
        if(joueurCible.getRole()==Role.surdoue){
            verifierMarqueurDest(joueurCible, positionArrivee);
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
        if(joueurCile.getRole()==Role.surdoue){
            UV uv = graph.getUV(position);
            if(uv.hasMarqueur()){
                ArrayList<Marqueur> marqueursUV;
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

    }

    //Fonction vérfiant la liste des déplacement possible pour un joueur donné
    public ArrayList<Integer> deplacementPossible(Joueur joueurCible){
        ArrayList<Integer> ciblePossible = new ArrayList<>();

        //Si le joueur a la carte de sa position actuelle il pourra aller partout.
        int posJoueur = joueurCible.getPosition();
        UV uvJoueur = graph.getUV(posJoueur);

        if(DEBUG) {
            System.out.print(posJoueur);
            System.out.print(uvJoueur);
        }


            //Sinon vérifier sa position, ajouter les voisin, ajouter les carte présente en main et les autre proffesseur si présence de l'un d'eux
            //On check la position d'un prof
        for(Professeur p : pionProfesseur){
            if(p.getPosition()==posJoueur){
                for(Professeur p1 : pionProfesseur){
                    if(p1.getPosition()!=p.getPosition()){
                        if(DEBUG) System.out.println(" because prof : "+p1.getPosition());
                        ciblePossible.add(p1.getPosition());
                    }
                }
            }
        }

        for(UV uv : uvJoueur.getVoisins()){
            if(!ciblePossible.contains(uv.getPosition())){
                if(DEBUG) System.out.println(" because neighbour : "+uv.getPosition());
                ciblePossible.add(uv.getPosition());
            }
        }

        //Le joueur a la carte de la position ou il se trouve et peux se déplacer n'importe ou
        if(joueurCible.hasCarte(uvJoueur)) {
            for (int i = 0; i < graph.getListSize(); i++) {
                if (i != posJoueur) {
                    if (DEBUG) System.out.println(" has carte of his position: " + i);
                    ciblePossible.add(i + 1);
                }
            }
        }

        ArrayList<CarteSemestre> carteJoueur = joueurCible.getHand();
        for(CarteSemestre carte : carteJoueur){
            if(!ciblePossible.contains(carte.getCible().getPosition())){
                if(DEBUG) System.out.println(" has carte : "+carte.getCible().getPosition());
                ciblePossible.add(carte.getCible().getPosition());
            }
        }




        return ciblePossible;
    }

    //Retourne une liste des joueurs déplaçable
    public ArrayList<Joueur> joueurDeplacable(){
        ArrayList<Joueur> joueurPossible = new ArrayList<>();

        if(joueurActif.getRole()==Role.chefProjet){
            for(Joueur joueur : joueurs){
                joueurPossible.add(joueur);
            }
        }else{
            joueurPossible.add(joueurActif);
        }

        return joueurPossible;
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

    //Méthode permettant au Joueur 'lèche-botte' de déplacer les professeurs
    public void deplacerProf(Professeur p, int position){
        if(joueurActif.getRole()==Role.lecheBotte){
            ArrayList<UV> listUV = graph.getListUV();
            for(UV uv : listUV){
                if(uv.getPosition()==position && uv.getFiliere() == p.getFiliere()){
                    p.setPosition(position);
                    p.setDeplace(true);
                    reduireAction();
                }
            }
        }

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

                    if(joueurActif.getRole()==Role.decale){
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
        boolean test = false;
        for(Professeur p : pionProfesseur) {
            if (p.getPosition() == joueurActif.getPosition()) {
                test = true;
            }
        }
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
            System.out.println("Fin de tout due au manque d'action pour le joueur");
            finDeTour();
        }
    }

    //Bloc de fin de tour
    /*Fonction qui remet toutes les valeurs comme a leur état initial et change le joueur Actif*/
    public void finDeTour() {
        if (DEBUG) {System.out.println("------------------------ FIN DE TOUR ------------------------------");}
        //On fait piocher le joueur actif
        piocheSemestre();

        //On pioche les cartes infection si la carte bénéfique n'est pas utilisé
        if(antipioche){
            antipioche = false;
        }else{
            piocheInfection();
        }


        //appel de la méthode finTour de graph qui s'occupe de reset les valeurs de base des model.UV (par ex: eclosion)
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
                UV uvDest = graph.getUV(uv.getVoisins().get(uvCible).getPosition());
                uv.setProfesseur(null);
                uvDest.setProfesseur(p);
                p.setPosition(uvDest.getPosition());


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

    public Graph getGraph(){
        return graph;
    }
    public Joueur getJoueurActif(){return joueurActif;}

    public ArrayList<Integer> deplacementPossibleByCarteSemestre(Joueur joueurCible){
        ArrayList<Integer> ciblePossible = new ArrayList<>();
        ArrayList<CarteSemestre> carteJoueur = joueurCible.getHand();
        // Pour chaque carte de leur mains, les joueurs peuvent accéder aux UVs correspondantes
        for(CarteSemestre carte : carteJoueur){

            //Attention, ici encore il y avait un test (voir ci après)
            //if(!ciblePossible.contains(carte.getCible().getPosition())) {
                if (DEBUG) System.out.println(" has carte : " + carte.getCible().getPosition());
                ciblePossible.add(carte.getCible().getPosition());
            //}
        }
        return ciblePossible;
    }

    public ArrayList<Integer> deplacementPossibleEverywhere(Joueur joueurCible){
        ArrayList<Integer> ciblePossible = new ArrayList<>();

        //Si le joueur a la carte de sa position actuelle il pourra aller partout.
        int posJoueur = joueurCible.getPosition();
        UV uvJoueur =  graph.getUV(posJoueur);

        //Le joueur a la carte de la position ou il se trouve et peux se déplacer n'importe ou
        if(joueurCible.hasCarte(uvJoueur)){
            for(UV current : graph.getListUV()){
                if(current.getPosition()!=posJoueur){
                    if(DEBUG) System.out.println(" has carte of his position : "+current.getPosition());
                    ciblePossible.add(current.getPosition());
                }
            }
        }
        return ciblePossible;
    }

    public ArrayList<Integer> deplacementPossibleByProf(Joueur joueurCible){
        ArrayList<Integer> ciblePossible = new ArrayList<>();
        int posJoueur = joueurCible.getPosition();
        for(Professeur p : pionProfesseur){
            if(p.getPosition()==posJoueur){
                for(Professeur currentProf : pionProfesseur){
                    if(currentProf.getPosition()!=p.getPosition()){
                        if(DEBUG) System.out.println(" because prof : "+currentProf.getPosition());
                        ciblePossible.add(currentProf.getPosition());
                    }
                }
            }

        }

        return ciblePossible;
    }

    public ArrayList<Integer> deplacementPossibleNeighbour(Joueur joueurCible){
        ArrayList<Integer> ciblePossible = new ArrayList<>();
        int posJoueur = joueurCible.getPosition();
        UV uvJoueur = graph.getUV(posJoueur);
        //On ajoute chaque voisin dans l'ArrayList
        for(UV uv : uvJoueur.getVoisins()){
            // if(!ciblePossible.contains(uv.getPosition()))
            if(DEBUG) System.out.println(" because neighbour : "+ uv.getPosition());
            ciblePossible.add(uv.getPosition());
        }
        return ciblePossible;
    }

    public Joueur[] getJoueurs(){
        return joueurs;
    }

    public ArrayList<Professeur> getPionProfesseur(){
        return pionProfesseur;
    }
}
