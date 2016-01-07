package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import model.*;

import java.util.ArrayList;

public class Main extends Application {
    private int i=0 ;
    public void setI(int i){this.i = i;}

    private DataReader dat;
    private Jeu model;
    private Group root;

    private boolean playerWantToMove;
    private boolean playerWantToEndHisTurn;

    private ArrayList<UVSprite> uvSprites;
    private ArrayList<Label> uvLabel;
    private ArrayList<PlayerSprite> playerSprites;
    private ArrayList<TeacherSprite> teacherSprites;
    private ArrayList<PlayerHandCardSprite> playerHandCardSprites;
    private ArrayList<Label> playerHandCardLabel;
    private ArrayList<DeckSprite> deckSprites;
    private ArrayList<Label> deckLabel;
    private ArrayList<String> playerNames;

    private Joueur activePlayer;
    private int nombreJoueur;

    private Rectangle activePlayerInfoRectangle;
    private Label activePlayerInfoLabel;

    private Rectangle fondbouton1, fondbouton2, fondbouton3, fondbouton4, fondbouton5, fondbouton6;
    private Label lbouton1, lbouton2, lbouton3, lbouton4, lbouton5, lbouton6;
    private DeckSprite pioche1, pioche2, defausse1, defausse2;
    private Label lpioche1, lpioche2, ldefausse1, ldefausse2;

    private Color ilcColor = Color.WHEAT;
    private Color i2rvColor = Color.LIGHTBLUE;
    private Color rtColor = Color.LIGHTGREEN;
    private Color leimColor = Color.PINK;


    @Override
    public void start(Stage primaryStage) {

        dat = new DataReader("UV.jjq");
        if(dat.hasSuccessfullyLoaded()) {

            primaryStage.setTitle("Bienvenue sur Pandémie.");  //titre

            root = new Group();
            GridPane root0 = new GridPane();
            GridPane grid = new GridPane();
            root.setId("jeu");
            grid.setAlignment(Pos.CENTER);
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(25, 25, 25, 25));

            ImageView monimage2 = new ImageView(new Image(Main.class.getResourceAsStream("images/Background2.jpg")));
            monimage2.setTranslateY(-30);
            root.getChildren().add(monimage2);

            Scene scene2 = new Scene(root, 800, 600);
            Scene scene1 = new Scene(grid, 800, 600);
            Scene scene = new Scene(root0, 800, 600);

            scene2.getStylesheets().add
                    (Main.class.getResource("Main.css").toExternalForm());

            scene.getStylesheets().add
                    (Main.class.getResource("Main.css").toExternalForm());

            scene1.getStylesheets().add
                    (Main.class.getResource("Main.css").toExternalForm());

            Rectangle fond1 = new Rectangle();
            fond1.setWidth(800);

            Rectangle fond_droit = new Rectangle();
            fond_droit.setWidth(200);
            fond_droit.setHeight(600);
            fond_droit.setFill(Color.GREY);
            root.getChildren().add(fond_droit);

            Rectangle carte1 = new Rectangle();
            carte1.setWidth(50);
            carte1.setHeight(70);
            carte1.setFill(Color.DARKGRAY);
            carte1.setTranslateX(210);
            carte1.setTranslateY(10);
            root.getChildren().add(carte1);

            carte1.setOnMouseEntered(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent me) {
                    carte1.setFill(Color.LIGHTGREY);
                }
            });

            carte1.setOnMouseExited(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent me) {
                    carte1.setFill(Color.DARKGRAY);
                }
            });

            carte1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent me) {
                }
            });

            Label lcarte1 = new Label("BD40");
            lcarte1.setFont(Font.font("Arial", 18));
            lcarte1.setMaxWidth(50);
            lcarte1.setWrapText(true);
            lcarte1.setTranslateX(210);
            lcarte1.setTranslateY(10);
            root.getChildren().add(lcarte1);

            Rectangle carte2 = new Rectangle();
            carte2.setWidth(50);
            carte2.setHeight(70);
            carte2.setFill(Color.DARKGRAY);
            carte2.setTranslateX(270);
            carte2.setTranslateY(10);
            root.getChildren().add(carte2);

            carte2.setOnMouseEntered(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent me) {
                    carte2.setFill(Color.LIGHTGREY);
                }
            });

            carte2.setOnMouseExited(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent me) {
                    carte2.setFill(Color.DARKGRAY);
                }
            });

            carte2.setOnMousePressed(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent me) {
                    //if (carte)
                    //mettre l'action correspondant à l'action
                }
            });

            Label lcarte2 = new Label("BD40");
            lcarte2.setFont(Font.font("Arial", 18));
            lcarte2.setMaxWidth(50);
            lcarte2.setWrapText(true);
            lcarte2.setTranslateX(270);
            lcarte2.setTranslateY(10);
            root.getChildren().add(lcarte2);

            Rectangle carte3 = new Rectangle();
            carte3.setWidth(50);
            carte3.setHeight(70);
            carte3.setFill(Color.DARKGRAY);
            carte3.setTranslateX(330);
            carte3.setTranslateY(10);
            root.getChildren().add(carte3);

            carte3.setOnMousePressed(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent me) {
                    //if (carte)
                    //mettre l'action correspondant à l'action
                }
            });

            carte3.setOnMouseEntered(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent me) {
                    carte3.setFill(Color.LIGHTGREY);
                }
            });

            carte3.setOnMouseExited(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent me) {
                    carte3.setFill(Color.DARKGRAY);
                }
            });

            Label lcarte3 = new Label("BD40");
            lcarte3.setFont(Font.font("Arial", 18));
            lcarte3.setMaxWidth(50);
            lcarte3.setWrapText(true);
            lcarte3.setTranslateX(330);
            lcarte3.setTranslateY(10);
            root.getChildren().add(lcarte3);

            Rectangle Marqueur1 = new Rectangle();
            Marqueur1.setWidth(10);
            Marqueur1.setHeight(10);
            Marqueur1.setFill(Color.GOLD);
            //if 1
        /*Marqueur1.setTranslateX(543);
        Marqueur1.setTranslateY(60);*/
            //if 2
        /*Marqueur1.setTranslateX(603);
        Marqueur1.setTranslateY(28);*/
            //if 3
        /*Marqueur1.setTranslateX(663);
        Marqueur1.setTranslateY(60);*/
            //if 4
            Marqueur1.setTranslateX(723);
            Marqueur1.setTranslateY(28);
            root.getChildren().add(Marqueur1);

            Rectangle Marqueur2 = new Rectangle();
            Marqueur2.setWidth(10);
            Marqueur2.setHeight(10);
            Marqueur2.setFill(Color.GOLD);
            //if 1
        /*Marqueur2.setTranslateX(237);
        Marqueur2.setTranslateY(393);*/
            //if 2
        /*Marqueur2.setTranslateX(288);
        Marqueur2.setTranslateY(436);*/
            //if 3
        /*Marqueur2.setTranslateX(237);
        Marqueur2.setTranslateY(479);*/
            //if 4
        /*Marqueur2.setTranslateX(288);
        Marqueur2.setTranslateY(522);*/
            //if 5
            Marqueur2.setTranslateX(237);
            Marqueur2.setTranslateY(565);
            root.getChildren().add(Marqueur2);

            Text scenetitle = new Text("Veuillez selectionner le nombre de joueurs"); //Ajouter un titre
            grid.add(scenetitle, 0, 0, 2, 1);
            scenetitle.setId("welcome-text");


            Label userName = new Label("Nombre de joueurs :");
            userName.setId("Label1");
            grid.add(userName, 0, 1);

            Button choix0 = new Button("2");
            HBox hbChoix0 = new HBox(10);
            hbChoix0.getChildren().add(choix0);
            grid.add(choix0, 1, 1);

            Button choix1 = new Button("3");
            HBox hbChoix1 = new HBox(10);
            hbChoix1.getChildren().add(choix1);
            grid.add(choix1, 1, 2);

            Button choix2 = new Button("4");
            HBox hbChoix2 = new HBox(10);
            hbChoix2.getChildren().add(choix2);
            grid.add(choix2, 1, 3);

            Button goToGame = new Button("Valider et Passer au jeu");
            HBox hbGoToGame = new HBox(10);
            hbGoToGame.getChildren().add(goToGame);
            grid.add(goToGame, 2, 7);
            goToGame.setVisible(false);


            Button goToChoice = new Button("JOUER");
            goToChoice.setId("Boutonjouer");
            goToChoice.setPrefSize(200, 200);
            HBox hbGoToChoice = new HBox(10);
            hbGoToChoice.getChildren().add(goToChoice);
            root0.getChildren().add(hbGoToChoice);
            goToChoice.setTranslateX(300);
            goToChoice.setTranslateY(250);
            goToChoice.setVisible(true);

            Text title1 = new Text("Bienvenue Sur PANDEMIE UTBM"); //Ajouter un titre
            root0.add(title1, 0, 0, 2, 1);
            title1.setTranslateX(110);
            title1.setId("title1");


            ImageView monimage = new ImageView(new Image(Main.class.getResourceAsStream("images/image1.png")));
            monimage.setTranslateX(200);
            monimage.setTranslateY(300);
            grid.getChildren().add(monimage);


            Label labelNom1 = new Label("Entrez le nom du Joueur 1 :");
            labelNom1.setId("Label1");
            grid.add(labelNom1, 0, 3);
            labelNom1.setVisible(false);

            TextField nom1 = new TextField();
            grid.add(nom1, 1, 3);
            nom1.setVisible(false);

            Label labelNom2 = new Label("Entrez le nom du Joueur 2 :");
            grid.add(labelNom2, 0, 4);
            labelNom2.setId("Label1");
            labelNom2.setVisible(false);

            TextField nom2 = new TextField();
            grid.add(nom2, 1, 4);
            nom2.setVisible(false);

            Label labelNom3 = new Label("Entrez le nom du Joueur 3 :");
            grid.add(labelNom3, 0, 5);
            labelNom3.setVisible(false);
            labelNom3.setId("Label1");
            TextField nom3 = new TextField();
            grid.add(nom3, 1, 5);
            nom3.setVisible(false);

            Label labelNom4 = new Label("Entrez le nom du Joueur 4 :");
            grid.add(labelNom4, 0, 6);
            labelNom4.setVisible(false);
            labelNom4.setId("Label1");
            TextField nom4 = new TextField();
            grid.add(nom4, 1, 6);
            nom4.setVisible(false);

            final Text actiontarget = new Text("");
            grid.add(actiontarget, 1, 7);

            choix0.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent e) {
                    actiontarget.setText("Entrez le nom des joueurs");
                    actiontarget.setId("actiontarget");
                    setI(2);
                    nom1.setVisible(true);
                    nom1.setText("John");
                    labelNom1.setVisible(true);
                    nom2.setVisible(true);
                    nom2.setText("John");
                    labelNom2.setVisible(true);
                    choix2.setVisible(false);
                    choix1.setVisible(false);
                    goToGame.setVisible(true);
                    monimage.setVisible(false);
                }
            });

            choix1.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent e) {
                    actiontarget.setText("Entrez le nom des joueurs");
                    actiontarget.setId("actiontarget");
                    setI(3);
                    nom1.setVisible(true);
                    labelNom1.setVisible(true);
                    nom2.setVisible(true);
                    labelNom2.setVisible(true);
                    nom3.setVisible(true);
                    labelNom3.setVisible(true);
                    choix2.setVisible(false);
                    goToGame.setVisible(true);
                    choix0.setVisible(false);
                    monimage.setVisible(false);
                }
            });

            choix2.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent e) {
                    actiontarget.setText("Entrez le nom des joueurs");
                    actiontarget.setId("actiontarget");
                    setI(4);
                    nom1.setVisible(true);
                    labelNom1.setVisible(true);
                    nom2.setVisible(true);
                    labelNom2.setVisible(true);
                    nom3.setVisible(true);
                    labelNom3.setVisible(true);
                    nom4.setVisible(true);
                    labelNom4.setVisible(true);
                    goToGame.setVisible(true);
                    choix1.setVisible(false);
                    choix0.setVisible(false);
                    monimage.setVisible(false);
                    choix2.setTranslateY(-50);
                }
            });
            goToGame.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent e) {
                    primaryStage.setScene(scene2);
                    initBoard(i);
                }
            });

            goToChoice.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent e) {
                    primaryStage.setScene(scene1);
                }
            });
            primaryStage.setScene(scene);
            primaryStage.show();


        }else{
            System.out.println("Error happened when loading graph data from file");
        }
    }

    // initCoordUVSprites renvoie un tableau d'int[i][j] contenant les coordonnées des UV classées sur i, j=0 correspond à x et 1 à y
    public int[][] initCoordUVSprites(){
        int tab[][] = new int[24][2];
        tab[0][0] = 345;
        tab[0][1] = 372;
        tab[1][0] = 390;
        tab[1][1] = 325;
        tab[2][0] = 459;
        tab[2][1] = 380;
        tab[3][0] = 443;
        tab[3][1] = 343;
        tab[4][0] = 434;
        tab[4][1] = 442;
        tab[5][0] = 409;
        tab[5][1] = 380;
        tab[6][0] = 300;
        tab[6][1] = 160;
        tab[7][0] = 350;
        tab[7][1] = 192;
        tab[8][0] = 317;
        tab[8][1] = 105;
        tab[9][0] = 400;
        tab[9][1] = 150;
        tab[10][0] = 325;
        tab[10][1] = 252;
        tab[11][0] = 410;
        tab[11][1] = 225;
        tab[12][0] = 565;
        tab[12][1] = 319;
        tab[13][0] = 682;
        tab[13][1] = 375;
        tab[14][0] = 612;
        tab[14][1] = 278;
        tab[15][0] = 727;
        tab[15][1] = 270;
        tab[16][0] = 669;
        tab[16][1] = 300;
        tab[17][0] = 619;
        tab[17][1] = 360;
        tab[18][0] = 675;
        tab[18][1] = 165;
        tab[19][0] = 625;
        tab[19][1] = 192;
        tab[20][0] = 600;
        tab[20][1] = 135;
        tab[21][0] = 739;
        tab[21][1] = 175;
        tab[22][0] = 666;
        tab[22][1] = 110;
        tab[23][0] = 711;
        tab[23][1] = 126;

        return tab;
    }
    public int[][] initCoordPionJoueurs(){
        int tab[][] = new int[nombreJoueur][2];

        tab[0][0] = -10;
        tab[0][1] = -6;
        tab[1][0] = -10;
        tab[1][1] = 2;
        if(nombreJoueur>=3){
            tab[2][0] = -10;
            tab[2][1] = +10;
        }
        if(nombreJoueur==4){
            tab[2][0] = -10;
            tab[2][1] = +10;
            tab[3][0] = -10;
            tab[3][1] = +18;
        }



        return tab;
    }
    public int[][] initCoordHandCardsSprites(){
        int tab[][] = new int[5][2];

        tab[0][0] = 320;
        tab[0][1] = 530;
        tab[1][0] = 380;
        tab[1][1] = 530;
        tab[2][0] = 440;
        tab[2][1] = 530;
        tab[3][0] = 500;
        tab[3][1] = 530;
        tab[4][0] = 560;
        tab[4][1] = 530;
        return tab;
    }
    public int[][] initCoordLibraryAndGraveYard(){
        int tab[][] = new int[4][2];

        tab[0][0] = 650;
        tab[0][1] = 450;
        tab[1][0] = 730;
        tab[1][1] = 450;
        tab[2][0] = 650;
        tab[2][1] = 530;
        tab[3][0] = 730;
        tab[3][1] = 530;

        return tab;
    }

    public void movePlayer(int positionUV){
        Joueur currentPlayer = model.getJoueurActif();
        System.out.println(model.getGraph().getUV(positionUV));
        if(model.deplacementPossible(currentPlayer).contains(model.getGraph().getUV(positionUV).getPosition())){
            PlayerSprite currentPlayerSprite = playerSprites.get(currentPlayer.getNumero());
            int tabUV[][] = initCoordUVSprites();
            int tabPion[][] = initCoordPionJoueurs();

            model.deplacer(currentPlayer, model.getGraph().getUV(positionUV).getPosition());
            currentPlayerSprite.setX(tabPion[currentPlayer.getNumero()][0] + tabUV[currentPlayer.getPosition()-1][0]);
            currentPlayerSprite.setY(tabPion[currentPlayer.getNumero()][1] + tabUV[currentPlayer.getPosition()-1][1]);

            System.out.println("action déplacement désactivée automatiquement!!----------------------------------------------------------------------");
            fondbouton1.setFill(Color.DARKGRAY);
            if(model.getJoueurActif() != activePlayer) {
                endTurn();
            }
            else {
                resetAllActionButtons(false);
            }

        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void circleReachableUV(ArrayList<Integer> reachableUVPositions){
        for(Integer currentUVPosition : reachableUVPositions){
            UVSprite correspondingSprite = uvSprites.get(currentUVPosition-1);
            correspondingSprite.setStrokeWidth(2);
        }
    }

    public void uncircleUV(){
        for(UVSprite currentSprite : uvSprites){
            currentSprite.setStrokeWidth(0);
            currentSprite.setStroke(Color.BLACK);
        }
    }

    public void resetAllActionButtons(boolean comeFromEndTurnButtonClicked){
        // On remet la couleur par défaut à tous les boutons d'action au cas où l'uin d'eux était sélectionné
        fondbouton1.setFill(Color.DARKGRAY);
        fondbouton2.setFill(Color.DARKGRAY);
        fondbouton3.setFill(Color.DARKGRAY);
        fondbouton4.setFill(Color.DARKGRAY);
        fondbouton5.setFill(Color.DARKGRAY);
        fondbouton6.setFill(Color.DARKGRAY);

        // On remet le texte du bouton "passer" à sa valeur par défaut, au cas où le joueur avait déjà cliqué dessus une fois
        // De même pour le booléen correspondant;
        if(comeFromEndTurnButtonClicked){
            lbouton6.setText("Passer");
            playerWantToEndHisTurn=false;
        }

        // On remet la valeur de playerWantToMove à 'false' au cas où le joueur avait déjà cliqué sur le bouton de déplacement une fois
        // Pour la même raison, on désactive l'affichage différent pour les UV accessibles
        playerWantToMove=false;
        uncircleUV();

        // On actualise l'affichage des marqueurs, qui va également actualiser la couleur du bouton "travailler"
        updateLabelMarqueur();
        updateTeacherSprite();
    }

    private void endTurnButtonClicked(){
        System.out.println("Bouton de fin de tour activé");
        if(lbouton6.getText()=="Passer"){
            lbouton6.setText("Etes vous \nsur?");
        }else{
            playerWantToEndHisTurn=true;
            endTurn();
            resetAllActionButtons(true);
        }
    }

    private void workButtonClicked(){


        if(lbouton2.getTextFill()!=Color.RED){
            model.travailler();
        }

        if(model.getJoueurActif() != activePlayer) {
            endTurn();
        }
        else {
            resetAllActionButtons(false);
        }
    }

    private void moveButtonClicked(){
        if (!playerWantToMove) {
            fondbouton1.setFill(Color.LIGHTBLUE);
            playerWantToMove = true;
            System.out.println("Action déplacement activée!!----------------------------------------------------------");
            circleReachableUV(model.deplacementPossible(model.getJoueurActif()));

        } else {
            fondbouton1.setFill(Color.LIGHTGREY);
            playerWantToMove = false;
            System.out.println("Action déplacement désactivée manuellement!!----------------------------------------------------------");
            uncircleUV();
        }
    }

    private void callTeacherButtonClicked(){

        if(lbouton3.getTextFill() != Color.RED){
            model.appelerProf();
        }

        if(model.getJoueurActif() != activePlayer) {
            endTurn();
        }
        else {
            resetAllActionButtons(false);
        }
    }

    private void giveProjectButtonClicked(){
        if(lbouton4.getTextFill() != Color.RED){
            model.rendreProjet();
        }

        if(model.getJoueurActif() != activePlayer) {
            endTurn();
        }
        else {
            resetAllActionButtons(false);
        }
    }



    public void initBoard(int nombreJoueur){
        playerNames = new ArrayList<>();
        playerNames.add(" Player 1");
        playerNames.add(" Player 2");
        playerNames.add(" Player 3");
        playerNames.add(" Player 4");
        this.nombreJoueur = nombreJoueur;

        try {model = new Jeu(nombreJoueur, dat, playerNames);}catch(GameOverException e){}
        activePlayer = model.getJoueurActif();
        model.getGraph().printAllUV();

        displayActionButtons();
        displayUVSprites();
        displayPlayerSprites();
        displayTeacherSprites();
        displayHandSemesterCards();
        displayActivePlayerInfo();
        displayLibraryAndGraveyard();
        updateActivePlayerInfo();
        updateLabelMarqueur();
        updateHandSemesterCardLabel();
    }

    public void endTurn(){
        if(playerWantToEndHisTurn){
            System.out.println("Fin de tour due la la méthode endTurn");
            model.passer();
        }
        updatePlayerSpriteColor();
        updateHandSemesterCardLabel();
        updateActivePlayerInfo();
        updateLibraryAndGraveyard();
        resetAllActionButtons(false);
    }



    public void updatePlayerSpriteColor(){
        for(PlayerSprite currentPlayerSprite : playerSprites){
            currentPlayerSprite.setFill(Color.ORANGE);
        }
        activePlayer = model.getJoueurActif();
        playerSprites.get(activePlayer.getNumero()).setFill(Color.TEAL);
    }

    public void updateTeacherSprite(){
        ArrayList<Professeur> teachers = model.getPionProfesseur();
        int tabUV[][] = initCoordUVSprites();
        ArrayList<UV> listUV = model.getGraph().getListUV();

        for(TeacherSprite currentTeacherSprite : teacherSprites){
            Professeur currentTeacher = teachers.get(currentTeacherSprite.getNumero());

            currentTeacherSprite.setX(17 + tabUV[currentTeacher.getPosition()-1][0]);
            currentTeacherSprite.setY(6 + tabUV[currentTeacher.getPosition()-1][1]);
        }


        if(model.presenceProf()) {lbouton3.setTextFill(Color.RED);}
        else {lbouton3.setTextFill(Color.BLACK);}
    }

    public void updateLabelMarqueur(){
        System.out.println("Mise à jour affichage marqueurs");
        int index = 0;
        ArrayList<UV> listUV = model.getGraph().getListUV();
        for(Label currentUVLabel : uvLabel){
            currentUVLabel.setText(Integer.toString(listUV.get(index).getMarqueurs().size()));
            index++;
        }

        model.getGraph().printAllUV();
        if(listUV.get(model.getJoueurActif().getPosition()-1).getMarqueurs().size()==0) {lbouton2.setTextFill(Color.RED);}
        else {lbouton2.setTextFill(Color.BLACK);}
    }

    public void updateHandSemesterCardLabel(){
        for(Label currentLabel : playerHandCardLabel){
            currentLabel.setText("");
            playerHandCardSprites.get(playerHandCardLabel.indexOf(currentLabel)).setFill(Color.DARKGRAY);
        }
        for(CarteSemestre currentCard : activePlayer.getHand()){
            playerHandCardLabel.get(activePlayer.getHand().indexOf(currentCard)).setText(currentCard.getCible().toString());
            playerHandCardSprites.get(activePlayer.getHand().indexOf(currentCard)).setFill(switchCardColor(currentCard));

            if(model.peutRendreProjet()){
                lbouton4.setTextFill(Color.BLACK);
            }else{
                lbouton4.setTextFill(Color.RED);
            }
        }
    }

    public void updateActivePlayerInfo(){
        activePlayerInfoLabel.setText(activePlayer.toString());
    }

    public void updateLibraryAndGraveyard(){
        String tab[] = {"Pioche semestre : ", "Défausse semestre : ", "Pioche travail : ", "Défausse travail : "};
        int tabValues[] = {model.getCarteSemestre().getSizePioche(), model.getCarteSemestre().getSizeDefausse(), model.getCarteInfection().getSizePioche(), model.getCarteInfection().getSizeDefausse()};
        int index = 0;

        // OMONDIEUMONDIEUMONDIEUMONDIEUMONDIEUMONDIEUMONDIEUMONDIEU C'EST MOCHEUH!
        for(Label currentDeckLabel : deckLabel){
            currentDeckLabel.setText(tab[index]+tabValues[index]);
            index++;
        }
    }

    public void displayUVSprites(){
        int coordonnees[][] = initCoordUVSprites();
        int index = 0;
        uvSprites = new ArrayList<>();
        uvLabel = new ArrayList<>();

        Label labelBD51 = new Label("0");
        labelBD51.setFont(Font.font("Arial", 18));
        labelBD51.setTranslateX(coordonnees[index][0]);
        labelBD51.setTranslateY(coordonnees[index][1]);
        UVSprite circleBD51 = new UVSprite(coordonnees[index][0] + 5, coordonnees[index][1] + 9, 10, ilcColor, index + 1);
        uvLabel.add(labelBD51);
        uvSprites.add(circleBD51);
        root.getChildren().addAll(circleBD51, labelBD51);

        circleBD51.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    movePlayer(1);
                }
            }
        });
        labelBD51.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    movePlayer(1);
                }
            }
        });
        index++;

        Label labelGL51 = new Label("0");
        labelGL51.setFont(Font.font("Arial", 18));
        labelGL51.setTranslateX(coordonnees[index][0]);
        labelGL51.setTranslateY(coordonnees[index][1]);
        UVSprite circleGL51 = new UVSprite(coordonnees[index][0] + 6, coordonnees[index][1] + 11, 10, ilcColor, index + 1);
        uvLabel.add(labelGL51);
        uvSprites.add(circleGL51);
        root.getChildren().addAll(circleGL51, labelGL51);

        circleGL51.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    System.out.println("plop");
                    movePlayer(2);
                }
            }
        });
        labelGL51.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    System.out.println("plop");
                    movePlayer(2);
                }
            }
        });
        index++;

        Label labelBD50 = new Label("0");
        labelBD50.setFont(Font.font("Arial", 18));
        labelBD50.setTranslateX(coordonnees[index][0]);
        labelBD50.setTranslateY(coordonnees[index][1]);
        UVSprite circleBD50 = new UVSprite(coordonnees[index][0] + 5, coordonnees[index][1] + 10, 10, ilcColor, index + 1);
        uvLabel.add(labelBD50);
        uvSprites.add(circleBD50);
        root.getChildren().addAll(circleBD50, labelBD50);

        circleBD50.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    movePlayer(3);
                }
            }
        });
        labelBD50.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    movePlayer(3);
                }
            }
        });
        index++;

        Label labelGL52 = new Label("0");
        labelGL52.setFont(Font.font("Arial", 18));
        labelGL52.setTranslateX(coordonnees[index][0]);
        labelGL52.setTranslateY(coordonnees[index][1]);
        UVSprite circleGL52 = new UVSprite(coordonnees[index][0] + 4, coordonnees[index][1] + 9, 10, ilcColor, index + 1);
        uvLabel.add(labelGL52);
        uvSprites.add(circleGL52);
        root.getChildren().addAll(circleGL52, labelGL52);

        circleGL52.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    movePlayer(4);
                }
            }
        });
        labelGL52.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    movePlayer(4);
                }
            }
        });
        index++;

        Label labelLO51 = new Label("0");
        labelLO51.setFont(Font.font("Arial", 18));
        labelLO51.setTranslateX(coordonnees[index][0]);
        labelLO51.setTranslateY(coordonnees[index][1]);
        UVSprite circleLO51 = new UVSprite(coordonnees[index][0] + 5, coordonnees[index][1] + 10, 10, ilcColor, index + 1);
        uvLabel.add(labelLO51);
        uvSprites.add(circleLO51);
        root.getChildren().addAll(circleLO51, labelLO51);

        circleLO51.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    movePlayer(5);
                }
            }
        });
        labelLO51.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    movePlayer(5);
                }
            }
        });
        index++;

        Label labelIA54 = new Label("0");
        labelIA54.setFont(Font.font("Arial", 18));
        labelIA54.setTranslateX(coordonnees[index][0]);
        labelIA54.setTranslateY(coordonnees[index][1]);
        UVSprite circleIA54 = new UVSprite(coordonnees[index][0] + 6, coordonnees[index][1] + 10, 10, ilcColor, index + 1);
        uvLabel.add(labelIA54);
        uvSprites.add(circleIA54);
        root.getChildren().addAll(circleIA54, labelIA54);

        circleIA54.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    movePlayer(6);
                }
            }
        });
        labelIA54.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    movePlayer(6);
                }
            }
        });
        index++;

        Label labelIN52 = new Label("0");
        labelIN52.setFont(Font.font("Arial", 18));
        labelIN52.setTranslateX(coordonnees[index][0]);
        labelIN52.setTranslateY(coordonnees[index][1]);
        UVSprite circleIN52 = new UVSprite(coordonnees[index][0] + 4, coordonnees[index][1] + 9, 10, i2rvColor, index + 1);
        uvLabel.add(labelIN52);
        uvSprites.add(circleIN52);
        root.getChildren().addAll(circleIN52, labelIN52);

        circleIN52.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    movePlayer(7);
                }
            }
        });
        labelIN52.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    movePlayer(7);
                }
            }
        });
        index++;

        Label labelVI50 = new Label("0");
        labelVI50.setFont(Font.font("Arial", 18));
        labelVI50.setTranslateX(coordonnees[index][0]);
        labelVI50.setTranslateY(coordonnees[index][1]);
        UVSprite circleVI50 = new UVSprite(coordonnees[index][0] + 4, coordonnees[index][1] + 10, 10, i2rvColor, index + 1);
        uvLabel.add(labelVI50);
        uvSprites.add(circleVI50);
        root.getChildren().addAll(circleVI50, labelVI50);

        circleVI50.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    movePlayer(8);
                }
            }
        });
        labelVI50.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    movePlayer(8);
                }
            }
        });
        index++;

        Label labelIN54 = new Label("0");
        labelIN54.setFont(Font.font("Arial", 18));
        labelIN54.setTranslateX(coordonnees[index][0]);
        labelIN54.setTranslateY(coordonnees[index][1]);
        UVSprite circleIN54 = new UVSprite(coordonnees[index][0] + 4, coordonnees[index][1] + 9, 10, i2rvColor, index + 1);
        uvLabel.add(labelIN54);
        uvSprites.add(circleIN54);
        root.getChildren().addAll(circleIN54, labelIN54);

        circleIN54.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    movePlayer(9);
                }
            }
        });
        labelIN54.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    movePlayer(9);
                }
            }
        });
        index++;

        Label labelMT51 = new Label("0");
        labelMT51.setFont(Font.font("Arial", 18));
        labelMT51.setTranslateX(coordonnees[index][0]);
        labelMT51.setTranslateY(coordonnees[index][1]);
        UVSprite circleMT51 = new UVSprite(coordonnees[index][0] + 6, coordonnees[index][1] + 10, 10, i2rvColor, index + 1);
        uvLabel.add(labelMT51);
        uvSprites.add(circleMT51);
        root.getChildren().addAll(circleMT51, labelMT51);

        circleMT51.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    movePlayer(10);
                }
            }
        });
        labelMT51.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    movePlayer(10);
                }
            }
        });
        index++;

        Label labelIN55 = new Label("0");
        labelIN55.setFont(Font.font("Arial", 18));
        labelIN55.setTranslateX(coordonnees[index][0]);
        labelIN55.setTranslateY(coordonnees[index][1]);
        UVSprite circleIN55 = new UVSprite(coordonnees[index][0] + 4, coordonnees[index][1] + 10, 10, i2rvColor, index + 1);
        uvLabel.add(labelIN55);
        uvSprites.add(circleIN55);
        root.getChildren().addAll(circleIN55, labelIN55);

        circleIN55.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    movePlayer(11);
                }
            }
        });
        labelIN55.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    movePlayer(11);
                }
            }
        });
        index++;

        Label labelVI51 = new Label("0");
        labelVI51.setFont(Font.font("Arial", 18));
        labelVI51.setTranslateX(coordonnees[index][0]);
        labelVI51.setTranslateY(coordonnees[index][1]);
        UVSprite circleVI51 = new UVSprite(coordonnees[index][0] + 4, coordonnees[index][1] + 10, 10, i2rvColor, index + 1);
        uvLabel.add(labelVI51);
        uvSprites.add(circleVI51);
        root.getChildren().addAll(circleVI51, labelVI51);

        circleVI51.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    movePlayer(12);
                }
            }
        });
        labelVI51.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    movePlayer(12);
                }
            }
        });
        index++;

        Label labelTR53 = new Label("0");
        labelTR53.setFont(Font.font("Arial", 18));
        labelTR53.setTranslateX(coordonnees[index][0]);
        labelTR53.setTranslateY(coordonnees[index][1]);
        UVSprite circleTR53 = new UVSprite(coordonnees[index][0] + 6, coordonnees[index][1] + 9, 10, leimColor, index + 1);
        uvLabel.add(labelTR53);
        uvSprites.add(circleTR53);
        root.getChildren().addAll(circleTR53, labelTR53);

        circleTR53.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    movePlayer(13);
                }
            }
        });
        labelTR53.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    movePlayer(13);
                }
            }
        });
        index++;

        Label labelMI52 = new Label("0");
        labelMI52.setFont(Font.font("Arial", 18));
        labelMI52.setTranslateX(coordonnees[index][0]);
        labelMI52.setTranslateY(coordonnees[index][1]);
        UVSprite circleMI52 = new UVSprite(coordonnees[index][0] + 6, coordonnees[index][1] + 11, 10, leimColor, index + 1);
        uvLabel.add(labelMI52);
        uvSprites.add(circleMI52);
        root.getChildren().addAll(circleMI52, labelMI52);

        circleMI52.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    movePlayer(14);
                }
            }
        });
        labelMI52.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    movePlayer(14);
                }
            }
        });
        index++;

        Label labelSM57 = new Label("0");
        labelSM57.setFont(Font.font("Arial", 18));
        labelSM57.setTranslateX(coordonnees[index][0]);
        labelSM57.setTranslateY(coordonnees[index][1]);
        UVSprite circleSM57 = new UVSprite(coordonnees[index][0] + 5, coordonnees[index][1] + 9, 10, leimColor, index + 1);
        uvLabel.add(labelSM57);
        uvSprites.add(circleSM57);
        root.getChildren().addAll(circleSM57, labelSM57);

        circleSM57.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    movePlayer(15);
                }
            }
        });
        labelSM57.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    movePlayer(15);
                }
            }
        });
        index++;

        Label labelLO52 = new Label("0");
        labelLO52.setFont(Font.font("Arial", 18));
        labelLO52.setTranslateX(coordonnees[index][0]);
        labelLO52.setTranslateY(coordonnees[index][1]);
        UVSprite circleLO52 = new UVSprite(coordonnees[index][0] + 5, coordonnees[index][1] + 9, 10, leimColor, index + 1);
        uvLabel.add(labelLO52);
        uvSprites.add(circleLO52);
        root.getChildren().addAll(circleLO52, labelLO52);

        circleLO52.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    movePlayer(16);
                }
            }
        });
        labelLO52.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    movePlayer(16);
                }
            }
        });
        index++;

        Label labelLO53 = new Label("0");
        labelLO53.setFont(Font.font("Arial", 18));
        labelLO53.setTranslateX(coordonnees[index][0]);
        labelLO53.setTranslateY(coordonnees[index][1]);
        UVSprite circleLO53 = new UVSprite(coordonnees[index][0] + 5, coordonnees[index][1] + 9, 10, leimColor, index + 1);
        uvLabel.add(labelLO53);
        uvSprites.add(circleLO53);
        root.getChildren().addAll(circleLO53, labelLO53);

        circleLO53.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    movePlayer(17);
                }
            }
        });
        labelLO53.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    movePlayer(17);
                }
            }
        });
        index++;

        Label labelTR54 = new Label("0");
        labelTR54.setFont(Font.font("Arial", 18));
        labelTR54.setTranslateX(coordonnees[index][0]);
        labelTR54.setTranslateY(coordonnees[index][1]);
        UVSprite circleTR54 = new UVSprite(coordonnees[index][0] + 6, coordonnees[index][1] + 9, 10, leimColor, index + 1);
        uvLabel.add(labelTR54);
        uvSprites.add(circleTR54);
        root.getChildren().addAll(circleTR54, labelTR54);

        circleTR54.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    movePlayer(18);
                }
            }
        });
        labelTR54.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    movePlayer(18);
                }
            }
        });
        index++;

        Label labelRE52 = new Label("0");
        labelRE52.setFont(Font.font("Arial", 18));
        labelRE52.setTranslateX(coordonnees[index][0]);
        labelRE52.setTranslateY(coordonnees[index][1]);
        UVSprite circleRE52 = new UVSprite(coordonnees[index][0] + 5, coordonnees[index][1] + 11, 10, rtColor, index + 1);
        uvLabel.add(labelRE52);
        uvSprites.add(circleRE52);
        root.getChildren().addAll(circleRE52, labelRE52);

        circleRE52.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    movePlayer(19);
                }
            }
        });
        labelRE52.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    movePlayer(19);
                }
            }
        });
        index++;

        Label labelRE56 = new Label("0");
        labelRE56.setFont(Font.font("Arial", 18));
        labelRE56.setTranslateX(coordonnees[index][0]);
        labelRE56.setTranslateY(coordonnees[index][1]);
        UVSprite circleRE56 = new UVSprite(coordonnees[index][0] + 6, coordonnees[index][1] + 10, 10, rtColor, index + 1);
        uvLabel.add(labelRE56);
        uvSprites.add(circleRE56);
        root.getChildren().addAll(circleRE56, labelRE56);

        circleRE56.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    movePlayer(20);
                }
            }
        });
        labelRE56.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    movePlayer(20);
                }
            }
        });
        index++;

        Label labelRE51 = new Label("0");
        labelRE51.setFont(Font.font("Arial", 18));
        labelRE51.setTranslateX(coordonnees[index][0]);
        labelRE51.setTranslateY(coordonnees[index][1]);
        UVSprite circleRE51 = new UVSprite(coordonnees[index][0] + 6, coordonnees[index][1] + 10, 10, rtColor, index + 1);
        uvLabel.add(labelRE51);
        uvSprites.add(circleRE51);
        root.getChildren().addAll(circleRE51, labelRE51);

        circleRE51.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    movePlayer(21);
                }
            }
        });
        labelRE51.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    movePlayer(21);
                }
            }
        });
        index++;

        Label labelTL53 = new Label("0");
        labelTL53.setFont(Font.font("Arial", 18));
        labelTL53.setTranslateX(coordonnees[index][0]);
        labelTL53.setTranslateY(coordonnees[index][1]);
        UVSprite circleTL53 = new UVSprite(coordonnees[index][0] + 5, coordonnees[index][1] + 10, 10, rtColor, index + 1);
        uvLabel.add(labelTL53);
        uvSprites.add(circleTL53);
        root.getChildren().addAll(circleTL53, labelTL53);

        circleTL53.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    movePlayer(22);
                }
            }
        });
        labelTL53.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    movePlayer(22);
                }
            }
        });
        index++;

        Label labelRE53 = new Label("0");
        labelRE53.setFont(Font.font("Arial", 18));
        labelRE53.setTranslateX(coordonnees[index][0]);
        labelRE53.setTranslateY(coordonnees[index][1]);
        UVSprite circleRE53 = new UVSprite(coordonnees[index][0] + 5, coordonnees[index][1] + 10, 10, rtColor, index + 1);
        uvLabel.add(labelRE53);
        uvSprites.add(circleRE53);
        root.getChildren().addAll(circleRE53, labelRE53);

        circleRE53.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    movePlayer(23);
                }
            }
        });
        labelRE53.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    movePlayer(23);
                }
            }
        });
        index++;

        Label labelRE55 = new Label("0");
        labelRE55.setFont(Font.font("Arial", 18));
        labelRE55.setTranslateX(coordonnees[index][0]);
        labelRE55.setTranslateY(coordonnees[index][1]);
        UVSprite circleRE55 = new UVSprite(coordonnees[index][0] + 5, coordonnees[index][1] + 12, 10, rtColor, index + 1);
        uvLabel.add(labelRE55);
        uvSprites.add(circleRE55);
        root.getChildren().addAll(circleRE55, labelRE55);

        circleRE55.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    movePlayer(24);
                }
            }
        });
        labelRE55.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (playerWantToMove) {
                    movePlayer(24);
                }
            }
        });
    }

    public void displayPlayerSprites(){
        /*
        Création des sprites des joueurs
         */
        playerSprites = new ArrayList<>();
        int tabPion[][] = initCoordPionJoueurs();
        int tabUV[][] = initCoordUVSprites();
        int index = 0;
        Joueur[] joueurs = model.getJoueurs();
        int x = tabPion[index][0] + tabUV[joueurs[index].getPosition() - 1][0];
        int y = tabPion[index][1] + tabUV[joueurs[index].getPosition() - 1][1];
        PlayerSprite pionJoueur1 = new PlayerSprite(x, y, index + 1);
        root.getChildren().add(pionJoueur1);
        playerSprites.add(pionJoueur1);
        index++;

        x = tabPion[index][0] + tabUV[joueurs[index].getPosition() - 1][0];
        y = tabPion[index][1] + tabUV[joueurs[index].getPosition() - 1][1];
        PlayerSprite pionJoueur2 = new PlayerSprite(x, y, index + 1);
        root.getChildren().add(pionJoueur2);
        playerSprites.add(pionJoueur2);
        index++;

        if (nombreJoueur >= 3) {
            x = tabPion[index][0] + tabUV[joueurs[index].getPosition() - 1][0];
            y = tabPion[index][1] + tabUV[joueurs[index].getPosition() - 1][1];
            PlayerSprite pionJoueur3 = new PlayerSprite(x, y, index + 1);
            root.getChildren().add(pionJoueur3);
            playerSprites.add(pionJoueur3);
            index++;
        }
        if (nombreJoueur == 4) {
            x = tabPion[index][0] + tabUV[joueurs[index].getPosition() - 1][0];
            y = tabPion[index][1] + tabUV[joueurs[index].getPosition() - 1][1];
            PlayerSprite pionJoueur4 = new PlayerSprite(x, y, index + 1);
            root.getChildren().add(pionJoueur4);
            playerSprites.add(pionJoueur4);
            index++;
        }

        playerSprites.get(activePlayer.getNumero()).setFill(Color.TEAL);

    }

    public void displayTeacherSprites(){
        /*
        Création des sprites des professeurs
         */
        int index = 0;
        ArrayList<Professeur> teachers = model.getPionProfesseur();
        teacherSprites = new ArrayList<>();
        int teacherOffsetX = 16, teacherOffsetY = 6;
        int tabUV[][] = initCoordUVSprites();

        int x = teacherOffsetX + tabUV[teachers.get(index).getPosition() - 1][0];
        int y = teacherOffsetY + tabUV[teachers.get(index).getPosition() - 1][1];
        TeacherSprite spriteProfIlc = new TeacherSprite(x, y, index, Filiere.ILC);
        teacherSprites.add(spriteProfIlc);
        index++;

        x = teacherOffsetX + tabUV[teachers.get(index).getPosition() - 1][0];
        y = teacherOffsetY + tabUV[teachers.get(index).getPosition() - 1][1];
        TeacherSprite spriteProfI2rv = new TeacherSprite(x, y, index, Filiere.I2RV);
        teacherSprites.add(spriteProfI2rv);
        index++;

        x = teacherOffsetX + tabUV[teachers.get(index).getPosition() - 1][0];
        y = teacherOffsetY + tabUV[teachers.get(index).getPosition() - 1][1];
        TeacherSprite spriteProfLeim = new TeacherSprite(x, y, index, Filiere.LEIM);
        teacherSprites.add(spriteProfLeim);
        index++;

        x = teacherOffsetX + tabUV[teachers.get(index).getPosition() - 1][0];
        y = teacherOffsetY + tabUV[teachers.get(index).getPosition() - 1][1];
        TeacherSprite spriteProfRt = new TeacherSprite(x, y, index, Filiere.RT);
        teacherSprites.add(spriteProfRt);

        root.getChildren().addAll(spriteProfI2rv, spriteProfIlc, spriteProfLeim, spriteProfRt);

        updateTeacherSprite();

    }

    public void displayActionButtons(){
        displayActionButtonDeplacement();
        displayActionButtonPasser();
        displayActionButtonRendreProjet();
        displayActionButtonTravail();
        displayActionButtonTravailGroupe();
        displayActionButtonAppelProf();
    }

    public void displayActionButtonDeplacement(){
        fondbouton1 = new Rectangle();
        fondbouton1.setWidth(130);
        fondbouton1.setHeight(50);
        fondbouton1.setFill(Color.DARKGRAY);
        fondbouton1.setTranslateX(35);
        fondbouton1.setTranslateY(100);
        root.getChildren().add(fondbouton1);

        fondbouton1.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (!playerWantToMove) {
                    fondbouton1.setFill(Color.LIGHTGREY);
                }
            }
        });

        fondbouton1.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (!playerWantToMove) {
                    fondbouton1.setFill(Color.DARKGRAY);
                }
            }
        });

        fondbouton1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) { moveButtonClicked(); }
        });

        lbouton1 = new Label("Déplacement");
        lbouton1.setFont(Font.font("Arial", 16));
        lbouton1.setTextFill(Color.BLACK);
        lbouton1.setTranslateX(35);
        lbouton1.setTranslateY(100);
        root.getChildren().add(lbouton1);

        lbouton1.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (!playerWantToMove) {
                    fondbouton1.setFill(Color.LIGHTGREY);
                }
            }
        });

        lbouton1.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (!playerWantToMove) {
                    fondbouton1.setFill(Color.DARKGRAY);
                }
            }
        });

        lbouton1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) { moveButtonClicked(); }
        });
    }

    public void displayActionButtonTravail(){
        fondbouton2 = new Rectangle();
        fondbouton2.setWidth(130);
        fondbouton2.setHeight(50);
        fondbouton2.setFill(Color.DARKGRAY);
        fondbouton2.setTranslateX(35);
        fondbouton2.setTranslateY(175);
        root.getChildren().add(fondbouton2);

        fondbouton2.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                fondbouton2.setFill(Color.LIGHTGREY);
            }
        });

        fondbouton2.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                fondbouton2.setFill(Color.DARKGRAY);
            }
        });

        fondbouton2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                workButtonClicked();
            }
        });

        lbouton2 = new Label("Travail");
        lbouton2.setFont(Font.font("Arial", 16));
        lbouton2.setTextFill(Color.BLACK);
        lbouton2.setTranslateX(35);
        lbouton2.setTranslateY(175);
        root.getChildren().add(lbouton2);

        lbouton2.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                fondbouton2.setFill(Color.LIGHTGREY);
            }
        });

        lbouton2.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                fondbouton2.setFill(Color.DARKGRAY);
            }
        });

        lbouton2.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) { workButtonClicked(); }
        });
    }

    public void displayActionButtonAppelProf(){
        fondbouton3 = new Rectangle();
        fondbouton3.setWidth(130);
        fondbouton3.setHeight(50);
        fondbouton3.setFill(Color.DARKGRAY);
        fondbouton3.setTranslateX(35);
        fondbouton3.setTranslateY(250);
        root.getChildren().add(fondbouton3);

        fondbouton3.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                fondbouton3.setFill(Color.LIGHTGREY);
            }
        });

        fondbouton3.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                fondbouton3.setFill(Color.DARKGRAY);
            }
        });

        fondbouton3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) { callTeacherButtonClicked(); }
        });

        lbouton3 = new Label("Appeler un \nprofesseur");
        lbouton3.setFont(Font.font("Arial", 16));
        lbouton3.setTextFill(Color.BLACK);
        lbouton3.setTranslateX(35);
        lbouton3.setTranslateY(250);
        root.getChildren().add(lbouton3);

        lbouton3.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                fondbouton3.setFill(Color.LIGHTGREY);
            }
        });

        lbouton3.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                fondbouton3.setFill(Color.DARKGRAY);
            }
        });

        lbouton3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) { callTeacherButtonClicked(); }
        });

    }

    public void displayActionButtonRendreProjet(){
        fondbouton4 = new Rectangle();
        fondbouton4.setWidth(130);
        fondbouton4.setHeight(50);
        fondbouton4.setFill(Color.DARKGRAY);
        fondbouton4.setTranslateX(35);
        fondbouton4.setTranslateY(325);
        root.getChildren().add(fondbouton4);

        fondbouton4.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                fondbouton4.setFill(Color.LIGHTGREY);
            }
        });

        fondbouton4.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                fondbouton4.setFill(Color.DARKGRAY);
            }
        });

        fondbouton4.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) { giveProjectButtonClicked();}
        });

        lbouton4 = new Label("Rendre un \nprojet");
        lbouton4.setFont(Font.font("Arial", 16));
        lbouton4.setTextFill(Color.BLACK);
        lbouton4.setTranslateX(35);
        lbouton4.setTranslateY(325);
        root.getChildren().add(lbouton4);

        lbouton4.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) { giveProjectButtonClicked();}
        });

        lbouton4.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                fondbouton4.setFill(Color.LIGHTGREY);
            }
        });

        lbouton4.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                fondbouton4.setFill(Color.DARKGRAY);
            }
        });


    }

    public void displayActionButtonTravailGroupe(){
        fondbouton5 = new Rectangle();
        fondbouton5.setWidth(130);
        fondbouton5.setHeight(50);
        fondbouton5.setFill(Color.DARKGRAY);
        fondbouton5.setTranslateX(35);
        fondbouton5.setTranslateY(400);
        root.getChildren().add(fondbouton5);

        fondbouton5.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                fondbouton5.setFill(Color.LIGHTGREY);
            }
        });

        fondbouton5.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                fondbouton5.setFill(Color.DARKGRAY);
            }
        });

        fondbouton5.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                //if (action==disponible)
                //mettre l'action correspondant à l'action
            }
        });

        lbouton5 = new Label("Travail de \ngroupe");
        lbouton5.setFont(Font.font("Arial", 16));
        lbouton5.setTextFill(Color.BLACK);
        lbouton5.setTranslateX(35);
        lbouton5.setTranslateY(400);
        root.getChildren().add(lbouton5);

    }

    public void displayActionButtonPasser(){
        fondbouton6 = new Rectangle();
        fondbouton6.setWidth(130);
        fondbouton6.setHeight(50);
        fondbouton6.setFill(Color.DARKGRAY);
        fondbouton6.setTranslateX(35);
        fondbouton6.setTranslateY(475);
        root.getChildren().add(fondbouton6);

        lbouton6 = new Label("Passer");
        lbouton6.setFont(Font.font("Arial", 16));
        lbouton6.setTextFill(Color.BLACK);
        lbouton6.setTranslateX(35);
        lbouton6.setTranslateY(475);
        root.getChildren().add(lbouton6);

        fondbouton6.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                fondbouton6.setFill(Color.LIGHTGREY);
            }
        });

        fondbouton6.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                fondbouton6.setFill(Color.DARKGRAY);
            }
        });

        fondbouton6.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                endTurnButtonClicked();
            }
        });


        lbouton6.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                fondbouton6.setFill(Color.LIGHTGREY);
            }
        });

        lbouton6.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                fondbouton6.setFill(Color.DARKGRAY);
            }
        });

        lbouton6.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                endTurnButtonClicked();
            }
        });
    }

    public void displayHandSemesterCards(){
        int tabCoord[][] = initCoordHandCardsSprites();
        int index = 0;
        playerHandCardSprites = new ArrayList<>();
        playerHandCardLabel = new ArrayList<>();

        PlayerHandCardSprite carte1 = new PlayerHandCardSprite(index);
        carte1.setTranslateX(tabCoord[index][0]);
        carte1.setTranslateY(tabCoord[index][1]);
        playerHandCardSprites.add(carte1);
        root.getChildren().add(carte1);

        carte1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {}
        });

        Label lcarte1 = new Label("");
        lcarte1.setFont(Font.font("Arial", 18));
        lcarte1.setMaxWidth(50);
        lcarte1.setWrapText(true);
        lcarte1.setTranslateX(tabCoord[index][0]);
        lcarte1.setTranslateY(tabCoord[index][1]);
        playerHandCardLabel.add(lcarte1);
        root.getChildren().add(lcarte1);

        lcarte1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {}
        });

        index++;

        PlayerHandCardSprite carte2 = new PlayerHandCardSprite(index);
        carte2.setTranslateX(tabCoord[index][0]);
        carte2.setTranslateY(tabCoord[index][1]);
        playerHandCardSprites.add(carte2);
        root.getChildren().add(carte2);

        carte2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {}
        });

        Label lcarte2 = new Label("");
        lcarte2.setFont(Font.font("Arial", 18));
        lcarte2.setMaxWidth(50);
        lcarte2.setWrapText(true);
        lcarte2.setTranslateX(tabCoord[index][0]);
        lcarte2.setTranslateY(tabCoord[index][1]);
        playerHandCardLabel.add(lcarte2);
        root.getChildren().add(lcarte2);

        lcarte2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {}
        });

        index++;

        PlayerHandCardSprite carte3 = new PlayerHandCardSprite(index);
        carte3.setTranslateX(tabCoord[index][0]);
        carte3.setTranslateY(tabCoord[index][1]);
        playerHandCardSprites.add(carte3);
        root.getChildren().add(carte3);

        carte3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
            }
        });

        Label lcarte3 = new Label("");
        lcarte3.setFont(Font.font("Arial", 18));
        lcarte3.setMaxWidth(50);
        lcarte3.setWrapText(true);
        lcarte3.setTranslateX(tabCoord[index][0]);
        lcarte3.setTranslateY(tabCoord[index][1]);
        playerHandCardLabel.add(lcarte3);
        root.getChildren().add(lcarte3);

        lcarte3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {}
        });

        index++;

        PlayerHandCardSprite carte4 = new PlayerHandCardSprite(index);
        carte4.setTranslateX(tabCoord[index][0]);
        carte4.setTranslateY(tabCoord[index][1]);
        playerHandCardSprites.add(carte4);
        root.getChildren().add(carte4);

        carte4.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {}
        });

        Label lcarte4 = new Label("");
        lcarte4.setFont(Font.font("Arial", 18));
        lcarte4.setMaxWidth(50);
        lcarte4.setWrapText(true);
        lcarte4.setTranslateX(tabCoord[index][0]);
        lcarte4.setTranslateY(tabCoord[index][1]);
        playerHandCardLabel.add(lcarte4);
        root.getChildren().add(lcarte4);

        lcarte4.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {}
        });

        index++;

        PlayerHandCardSprite carte5 = new PlayerHandCardSprite(index);
        carte5.setTranslateX(tabCoord[index][0]);
        carte5.setTranslateY(tabCoord[index][1]);
        playerHandCardSprites.add(carte5);
        root.getChildren().add(carte5);

        carte5.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {}
        });

        Label lcarte5 = new Label("");
        lcarte5.setFont(Font.font("Arial", 18));
        lcarte5.setMaxWidth(50);
        lcarte5.setWrapText(true);
        lcarte5.setTranslateX(tabCoord[index][0]);
        lcarte5.setTranslateY(tabCoord[index][1]);
        playerHandCardLabel.add(lcarte5);
        root.getChildren().add(lcarte5);

        lcarte5.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {}
        });
    }

    public void displayActivePlayerInfo(){
        activePlayerInfoRectangle = new Rectangle();
        activePlayerInfoRectangle.setWidth(130);
        activePlayerInfoRectangle.setHeight(50);
        activePlayerInfoRectangle.setFill(Color.IVORY);
        activePlayerInfoRectangle.setTranslateX(35);
        activePlayerInfoRectangle.setTranslateY(20);
        activePlayerInfoRectangle.setStrokeWidth(3);
        activePlayerInfoRectangle.setStrokeLineCap(StrokeLineCap.SQUARE);
        activePlayerInfoRectangle.setStroke(Color.BLACK);
        activePlayerInfoRectangle.setStrokeType(StrokeType.OUTSIDE);
        root.getChildren().add(activePlayerInfoRectangle);

        activePlayerInfoLabel = new Label();
        activePlayerInfoLabel.setText("");
        activePlayerInfoLabel.setFont(Font.font("Arial", 16));
        activePlayerInfoLabel.setTextFill(Color.BLACK);
        activePlayerInfoLabel.setTranslateX(35);
        activePlayerInfoLabel.setTranslateY(20);
        root.getChildren().add(activePlayerInfoLabel);
    }

    public void displayLibraryAndGraveyard(){
        deckLabel = new ArrayList<>();
        deckSprites = new ArrayList<>();
        int index = 0;
        int coordDeck[][] = initCoordLibraryAndGraveYard();

        pioche1 = new DeckSprite(index);
        pioche1.setTranslateX(coordDeck[index][0]);
        pioche1.setTranslateY(coordDeck[index][1]);
        deckSprites.add(pioche1);
        root.getChildren().add(pioche1);

        lpioche1 = new Label("Pioche semestre : " + model.getCarteSemestre().getSizePioche());
        lpioche1.setFont(Font.font("Arial", 12));
        lpioche1.setMaxWidth(70);
        lpioche1.setWrapText(true);
        lpioche1.setTranslateX(coordDeck[index][0]);
        lpioche1.setTranslateY(coordDeck[index][1]);
        deckLabel.add(lpioche1);
        root.getChildren().add(lpioche1);

        index++;

        defausse1 = new DeckSprite(index);
        defausse1.setTranslateX(coordDeck[index][0]);
        defausse1.setTranslateY(coordDeck[index][1]);
        root.getChildren().add(defausse1);

        ldefausse1 = new Label("Défausse semestre : " + model.getCarteSemestre().getSizeDefausse());
        ldefausse1.setFont(Font.font("Arial", 12));
        ldefausse1.setMaxWidth(70);
        ldefausse1.setWrapText(true);
        ldefausse1.setTranslateX(coordDeck[index][0]);
        ldefausse1.setTranslateY(coordDeck[index][1]);
        deckLabel.add(ldefausse1);
        root.getChildren().add(ldefausse1);

        index++;

        pioche2 = new DeckSprite(index);
        pioche2.setTranslateX(coordDeck[index][0]);
        pioche2.setTranslateY(coordDeck[index][1]);
        root.getChildren().add(pioche2);

        lpioche2 = new Label("Pioche travail : " + model.getCarteInfection().getSizePioche());
        lpioche2.setFont(Font.font("Arial", 12));
        lpioche2.setMaxWidth(70);
        lpioche2.setWrapText(true);
        lpioche2.setTranslateX(coordDeck[index][0]);
        lpioche2.setTranslateY(coordDeck[index][1]);
        deckLabel.add(lpioche2);
        root.getChildren().add(lpioche2);


        index++;

        defausse2 = new DeckSprite(index);
        defausse2.setTranslateX(coordDeck[index][0]);
        defausse2.setTranslateY(coordDeck[index][1]);
        root.getChildren().add(defausse2);

        ldefausse2 = new Label("Défausse travail : " + model.getCarteInfection().getSizeDefausse());
        ldefausse2.setFont(Font.font("Arial", 12));
        ldefausse2.setMaxWidth(70);
        ldefausse2.setWrapText(true);
        ldefausse2.setTranslateX(coordDeck[index][0]);
        ldefausse2.setTranslateY(coordDeck[index][1]);
        deckLabel.add(ldefausse2);
        root.getChildren().add(ldefausse2);
    }

    private Color switchCardColor(CarteSemestre card){
        try {
            switch (card.getFiliere()) {
                case ILC:
                    return Color.WHEAT;
                case I2RV:
                    return Color.LIGHTBLUE;
                case RT:
                    return Color.LIGHTGREEN;
                case LEIM:
                    return Color.PINK;
                default:
                    throw new WrongTypeException("Erreur de type de filière dans la méthode switchCardColor de la classe Main du package sample");
            }
    }catch(WrongTypeException e){return Color.TRANSPARENT;}
    }
}