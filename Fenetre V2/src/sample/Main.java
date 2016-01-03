package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class Main extends Application {
    private int i=0 ;
    public void seti (int j){i = j;}

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Bienvenue sur Pandémie.");  //titre

        Group root = new Group();
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

        Scene scene2 = new Scene(root,800,600);
        Scene scene1 = new Scene(grid, 800, 600);
        Scene scene = new Scene(root0,800,600);

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

        Rectangle fondbouton1 = new Rectangle();
        fondbouton1.setWidth(130);
        fondbouton1.setHeight(50);
        fondbouton1.setFill(Color.DARKGRAY);
        fondbouton1.setTranslateX(35);
        fondbouton1.setTranslateY(100);
        root.getChildren().add(fondbouton1);

        fondbouton1.setOnMouseEntered(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                fondbouton1.setFill(Color.LIGHTGREY);
            }
        });

        fondbouton1.setOnMouseExited(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                fondbouton1.setFill(Color.DARKGRAY);
            }
        });

        fondbouton1.setOnMousePressed(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                //if (action==disponible)
                //mettre l'action correspondant à l'action
            }
        });

      Label lbouton1 = new Label("Action1");
        lbouton1.setFont(Font.font("Arial",18));
        lbouton1.setTranslateX(35);
        lbouton1.setTranslateY(100);
        root.getChildren().add(lbouton1);


        Rectangle fondbouton2 = new Rectangle();
        fondbouton2.setWidth(130);
        fondbouton2.setHeight(50);
        fondbouton2.setFill(Color.DARKGRAY);
        fondbouton2.setTranslateX(35);
        fondbouton2.setTranslateY(175);
        root.getChildren().add(fondbouton2);

        fondbouton2.setOnMouseEntered(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                fondbouton2.setFill(Color.LIGHTGREY);
            }
        });

        fondbouton2.setOnMouseExited(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                fondbouton2.setFill(Color.DARKGRAY);
            }
        });

        fondbouton2.setOnMousePressed(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                //if (action==disponible)
                //mettre l'action correspondant à l'action
            }
        });

        Label lbouton2 = new Label("Action2");
        lbouton2.setFont(Font.font("Arial",18));
        lbouton2.setTranslateX(35);
        lbouton2.setTranslateY(175);
        root.getChildren().add(lbouton2);

        Rectangle fondbouton3 = new Rectangle();
        fondbouton3.setWidth(130);
        fondbouton3.setHeight(50);
        fondbouton3.setFill(Color.DARKGRAY);
        fondbouton3.setTranslateX(35);
        fondbouton3.setTranslateY(250);
        root.getChildren().add(fondbouton3);

        fondbouton3.setOnMouseEntered(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                fondbouton3.setFill(Color.LIGHTGREY);
            }
        });

        fondbouton3.setOnMouseExited(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                fondbouton3.setFill(Color.DARKGRAY);
            }
        });

        fondbouton3.setOnMousePressed(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                //if (action==disponible)
                //mettre l'action correspondant à l'action
            }
        });

        Label lbouton3 = new Label("Action3");
        lbouton3.setFont(Font.font("Arial",18));
        lbouton3.setTranslateX(35);
        lbouton3.setTranslateY(250);
        root.getChildren().add(lbouton3);

        Rectangle fondbouton4 = new Rectangle();
        fondbouton4.setWidth(130);
        fondbouton4.setHeight(50);
        fondbouton4.setFill(Color.DARKGRAY);
        fondbouton4.setTranslateX(35);
        fondbouton4.setTranslateY(325);
        root.getChildren().add(fondbouton4);

        fondbouton4.setOnMouseEntered(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                fondbouton4.setFill(Color.LIGHTGREY);
            }
        });

        fondbouton4.setOnMouseExited(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                fondbouton4.setFill(Color.DARKGRAY);
            }
        });

        fondbouton4.setOnMousePressed(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                //if (action==disponible)
                //mettre l'action correspondant à l'action
            }
        });

        Label lbouton4 = new Label("Action4");
        lbouton4.setFont(Font.font("Arial",18));
        lbouton4.setTranslateX(35);
        lbouton4.setTranslateY(325);
        root.getChildren().add(lbouton4);

        Rectangle fondbouton5 = new Rectangle();
        fondbouton5.setWidth(130);
        fondbouton5.setHeight(50);
        fondbouton5.setFill(Color.DARKGRAY);
        fondbouton5.setTranslateX(35);
        fondbouton5.setTranslateY(400);
        root.getChildren().add(fondbouton5);

        fondbouton5.setOnMouseEntered(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                fondbouton5.setFill(Color.LIGHTGREY);
            }
        });

        fondbouton5.setOnMouseExited(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                fondbouton5.setFill(Color.DARKGRAY);
            }
        });

        fondbouton5.setOnMousePressed(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                //if (action==disponible)
                //mettre l'action correspondant à l'action
            }
        });

        Label lbouton5 = new Label("Action5");
        lbouton5.setFont(Font.font("Arial",18));
        lbouton5.setTranslateX(35);
        lbouton5.setTranslateY(400);
        root.getChildren().add(lbouton5);

        Rectangle fondbouton6 = new Rectangle();
        fondbouton6.setWidth(130);
        fondbouton6.setHeight(50);
        fondbouton6.setFill(Color.DARKGRAY);
        fondbouton6.setTranslateX(35);
        fondbouton6.setTranslateY(475);
        root.getChildren().add(fondbouton6);

        fondbouton6.setOnMouseEntered(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                fondbouton6.setFill(Color.LIGHTGREY);
            }
        });

        fondbouton6.setOnMouseExited(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                fondbouton6.setFill(Color.DARKGRAY);
            }
        });

        fondbouton6.setOnMousePressed(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                //if (action==disponible)
                //mettre l'action correspondant à l'action
            }
        });

        Label lbouton6 = new Label("Action4");
        lbouton6.setFont(Font.font("Arial",18));
        lbouton6.setTranslateX(35);
        lbouton6.setTranslateY(475);
        root.getChildren().add(lbouton6);

        Rectangle carte1 = new Rectangle();
        carte1.setWidth(50);
        carte1.setHeight(70);
        carte1.setFill(Color.DARKGRAY);
        carte1.setTranslateX(210);
        carte1.setTranslateY(10);
        root.getChildren().add(carte1);

        carte1.setOnMouseEntered(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                carte1.setFill(Color.LIGHTGREY);
            }
        });

        carte1.setOnMouseExited(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                carte1.setFill(Color.DARKGRAY);
            }
        });

        carte1.setOnMousePressed(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                //if (carte)
                //mettre l'action correspondant à l'action
            }
        });

        Label lcarte1 = new Label("BD40");
        lcarte1.setFont(Font.font("Arial",18));
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

        carte2.setOnMouseEntered(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                carte2.setFill(Color.LIGHTGREY);
            }
        });

        carte2.setOnMouseExited(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                carte2.setFill(Color.DARKGRAY);
            }
        });

        carte2.setOnMousePressed(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                //if (carte)
                //mettre l'action correspondant à l'action
            }
        });

        Label lcarte2 = new Label("BD40");
        lcarte2.setFont(Font.font("Arial",18));
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

        carte3.setOnMousePressed(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                //if (carte)
                //mettre l'action correspondant à l'action
            }
        });

        carte3.setOnMouseEntered(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                carte3.setFill(Color.LIGHTGREY);
            }
        });

        carte3.setOnMouseExited(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                carte3.setFill(Color.DARKGRAY);
            }
        });

        Label lcarte3 = new Label("BD40");
        lcarte3.setFont(Font.font("Arial",18));
        lcarte3.setMaxWidth(50);
        lcarte3.setWrapText(true);
        lcarte3.setTranslateX(330);
        lcarte3.setTranslateY(10);
        root.getChildren().add(lcarte3);

        Rectangle carte4 = new Rectangle();
        carte4.setWidth(50);
        carte4.setHeight(70);
        carte4.setFill(Color.DARKGRAY);
        carte4.setTranslateX(560);
        carte4.setTranslateY(530);
        root.getChildren().add(carte4);

        carte4.setOnMousePressed(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                //if (carte && action == se déplacer avec carte)
                //mettre l'action correspondant à l'action
            }
        });

        carte4.setOnMouseEntered(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                carte4.setFill(Color.LIGHTGREY);
            }
        });

        carte4.setOnMouseExited(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                carte4.setFill(Color.DARKGRAY);
            }
        });

        Label lcarte4 = new Label("BD40");
        lcarte4.setFont(Font.font("Arial",18));
        lcarte4.setMaxWidth(50);
        lcarte4.setWrapText(true);
        lcarte4.setTranslateX(560);
        lcarte4.setTranslateY(530);
        root.getChildren().add(lcarte4);

        Rectangle carte5 = new Rectangle();
        carte5.setWidth(50);
        carte5.setHeight(70);
        carte5.setFill(Color.DARKGRAY);
        carte5.setTranslateX(320);
        carte5.setTranslateY(530);
        root.getChildren().add(carte5);

        carte5.setOnMousePressed(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                //if (carte && action == se déplacer avec carte)
                //mettre l'action correspondant à l'action
            }
        });

        carte5.setOnMouseEntered(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                carte5.setFill(Color.LIGHTGREY);
            }
        });

        carte5.setOnMouseExited(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                carte5.setFill(Color.DARKGRAY);
            }
        });

        Label lcarte5 = new Label("BD40");
        lcarte5.setFont(Font.font("Arial",18));
        lcarte5.setMaxWidth(50);
        lcarte5.setWrapText(true);
        lcarte5.setTranslateX(320);
        lcarte5.setTranslateY(530);
        root.getChildren().add(lcarte5);

        Rectangle carte6 = new Rectangle();
        carte6.setWidth(50);
        carte6.setHeight(70);
        carte6.setFill(Color.DARKGRAY);
        carte6.setTranslateX(380);
        carte6.setTranslateY(530);
        root.getChildren().add(carte6);

        carte6.setOnMouseEntered(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                carte6.setFill(Color.LIGHTGREY);
            }
        });

        carte6.setOnMouseExited(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                carte6.setFill(Color.DARKGRAY);
            }
        });

        carte6.setOnMousePressed(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                //if (carte && action == se déplacer avec carte)
                //mettre l'action correspondant à l'action
            }
        });

        Label lcarte6 = new Label("BD40");
        lcarte6.setFont(Font.font("Arial",18));
        lcarte6.setMaxWidth(50);
        lcarte6.setWrapText(true);
        lcarte6.setTranslateX(380);
        lcarte6.setTranslateY(530);
        root.getChildren().add(lcarte6);

        Rectangle carte7 = new Rectangle();
        carte7.setWidth(50);
        carte7.setHeight(70);
        carte7.setFill(Color.DARKGRAY);
        carte7.setTranslateX(440);
        carte7.setTranslateY(530);
        root.getChildren().add(carte7);

        carte7.setOnMousePressed(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                //if (carte && action == se déplacer avec carte)
                //mettre l'action correspondant à l'action
            }
        });

        carte7.setOnMouseEntered(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                carte7.setFill(Color.LIGHTGREY);
            }
        });

        carte7.setOnMouseExited(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                carte7.setFill(Color.DARKGRAY);
            }
        });

        Label lcarte7 = new Label("BD40");
        lcarte7.setFont(Font.font("Arial",18));
        lcarte7.setMaxWidth(50);
        lcarte7.setWrapText(true);
        lcarte7.setTranslateX(440);
        lcarte7.setTranslateY(530);
        root.getChildren().add(lcarte7);

        Rectangle carte8 = new Rectangle();
        carte8.setWidth(50);
        carte8.setHeight(70);
        carte8.setFill(Color.DARKGRAY);
        carte8.setTranslateX(500);
        carte8.setTranslateY(530);
        root.getChildren().add(carte8);

        carte8.setOnMousePressed(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                //if (carte && action == se déplacer avec carte)
                //mettre l'action correspondant à l'action
            }
        });

        carte8.setOnMouseEntered(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                carte8.setFill(Color.LIGHTGREY);
            }
        });

        carte8.setOnMouseExited(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                carte8.setFill(Color.DARKGRAY);
            }
        });

        Label lcarte8 = new Label("BD40");
        lcarte8.setFont(Font.font("Arial",18));
        lcarte8.setMaxWidth(50);
        lcarte8.setWrapText(true);
        lcarte8.setTranslateX(500);
        lcarte8.setTranslateY(530);
        root.getChildren().add(lcarte8);

        Rectangle pioche1 = new Rectangle();
        pioche1.setWidth(70);
        pioche1.setHeight(70);
        pioche1.setFill(Color.DARKGRAY);
        pioche1.setTranslateX(730);
        pioche1.setTranslateY(530);
        root.getChildren().add(pioche1);

        Label lpioche1 = new Label("pioche 1 : 20");
        lpioche1.setFont(Font.font("Arial",18));
        lpioche1.setMaxWidth(70);
        lpioche1.setWrapText(true);
        lpioche1.setTranslateX(730);
        lpioche1.setTranslateY(530);
        root.getChildren().add(lpioche1);

        Rectangle pioche2 = new Rectangle();
        pioche2.setWidth(70);
        pioche2.setHeight(70);
        pioche2.setFill(Color.DARKGRAY);
        pioche2.setTranslateX(650);
        pioche2.setTranslateY(530);
        root.getChildren().add(pioche2);

        Label lpioche2 = new Label("pioche 2 : 20");
        lpioche2.setFont(Font.font("Arial",18));
        lpioche2.setMaxWidth(70);
        lpioche2.setWrapText(true);
        lpioche2.setTranslateX(650);
        lpioche2.setTranslateY(530);
        root.getChildren().add(lpioche2);

        Rectangle defausse1 = new Rectangle();
        defausse1.setWidth(70);
        defausse1.setHeight(70);
        defausse1.setFill(Color.DARKGRAY);
        defausse1.setTranslateX(730);
        defausse1.setTranslateY(450);
        root.getChildren().add(defausse1);

        Label ldefausse1 = new Label("pioche 2 : 20");
        ldefausse1.setFont(Font.font("Arial",18));
        ldefausse1.setMaxWidth(70);
        ldefausse1.setWrapText(true);
        ldefausse1.setTranslateX(730);
        ldefausse1.setTranslateY(450);
        root.getChildren().add(ldefausse1);

        Rectangle defausse2 = new Rectangle();
        defausse2.setWidth(70);
        defausse2.setHeight(70);
        defausse2.setFill(Color.DARKGRAY);
        defausse2.setTranslateX(650);
        defausse2.setTranslateY(450);
        root.getChildren().add(defausse2);

        Label ldefausse2 = new Label("pioche 2 : 20");
        ldefausse2.setFont(Font.font("Arial",18));
        ldefausse2.setMaxWidth(70);
        ldefausse2.setWrapText(true);
        ldefausse2.setTranslateX(650);
        ldefausse2.setTranslateY(450);
        root.getChildren().add(ldefausse2);

        //if pas de marqueur :: setvisible false
        Label IN54 = new Label("1");
        IN54.setFont(Font.font("Arial",18));
        IN54.setTranslateX(317);
        IN54.setTranslateY(105);
        root.getChildren().add(IN54);

        //if pas de marqueur :: setvisible false
        Label MT51 = new Label("1");
        MT51.setFont(Font.font("Arial",18));
        MT51.setTranslateX(400);
        MT51.setTranslateY(150);
        root.getChildren().add(MT51);

        //if pas de marqueur :: setvisible false
        Label VI50= new Label("1");
        VI50.setFont(Font.font("Arial",18));
        VI50.setTranslateX(350);
        VI50.setTranslateY(192);
        root.getChildren().add(VI50);

        //if pas de marqueur :: setvisible false
        Label IN52= new Label("1");
        IN52.setFont(Font.font("Arial",18));
        IN52.setTranslateX(300);
        IN52.setTranslateY(160);
        root.getChildren().add(IN52);

        //if pas de marqueur :: setvisible false
        Label VI51= new Label("1");
        VI51.setFont(Font.font("Arial",18));
        VI51.setTranslateX(410);
        VI51.setTranslateY(225);
        root.getChildren().add(VI51);


        Text scenetitle = new Text("Veuillez selectionner le nombre de joueurs"); //Ajouter un titre
        grid.add(scenetitle, 0, 0, 2, 1);
        scenetitle.setId("welcome-text");


        Label userName = new Label("Nombre de joueurs :");
        userName.setId("Label1");
        grid.add(userName, 0, 1);

        Button choix1 = new Button("3") ;
        HBox hbChoix1 = new HBox(10);
        hbChoix1.getChildren().add(choix1);
        grid.add(choix1, 1,1);

        Button choix2 = new Button("4") ;
        HBox hbChoix2 = new HBox(10);
        hbChoix2.getChildren().add(choix2);
        grid.add(choix2, 1,2);

        Button goToGame = new Button ("Valider et Passer au jeu");
        HBox hbGoToGame = new HBox(10);
        hbGoToGame.getChildren().add(goToGame);
        grid.add(goToGame,2,7);
        goToGame.setVisible(false);

        Button goToChoice = new Button ("JOUER");
        goToChoice.setId("Boutonjouer");
        goToChoice.setPrefSize(200,200);
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


        Label labelnom1 = new Label("Entrez le nom du Joueur 1 :");
        labelnom1.setId("Label1");
        grid.add(labelnom1,0,3);
        labelnom1.setVisible(false);

        TextField nom1 = new TextField();
        grid.add(nom1, 1, 3);
        nom1.setVisible(false);

        Label labelnom2 = new Label("Entrez le nom du Joueur 2 :");
        grid.add(labelnom2,0,4);
        labelnom2.setId("Label1");
        labelnom2.setVisible(false);

        TextField nom2 = new TextField();
        grid.add(nom2, 1, 4);
        nom2.setVisible(false);

        Label labelnom3 = new Label("Entrez le nom du Joueur 3 :");
        grid.add(labelnom3,0,5);
        labelnom3.setVisible(false);
        labelnom3.setId("Label1");
        TextField nom3 = new TextField();
        grid.add(nom3, 1, 5);
        nom3.setVisible(false);

        Label labelnom4 = new Label("Entrez le nom du Joueur 4 :");
        grid.add(labelnom4,0,6);
        labelnom4.setVisible(false);
        labelnom4.setId("Label1");
        TextField nom4 = new TextField();
        grid.add(nom4, 1, 6);
        nom4.setVisible(false);

        final Text actiontarget = new Text("");
        grid.add(actiontarget, 1, 7);

        choix1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                actiontarget.setText("Entrez le nom des joueurs");
                actiontarget.setId("actiontarget");
                seti(3);
                nom1.setVisible(true);
                labelnom1.setVisible(true);
                nom2.setVisible(true);
                labelnom2.setVisible(true);
                nom3.setVisible(true);
                labelnom3.setVisible(true);
                choix2.setVisible(false);
                goToGame.setVisible(true);
                monimage.setVisible(false);
                //A ajouter : le setter du nombre de joueur ./
            }
        });

        choix2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                actiontarget.setText("Entrez le nom des joueurs");
                actiontarget.setId("actiontarget");
                seti(3);
                nom1.setVisible(true);
                labelnom1.setVisible(true);
                nom2.setVisible(true);
                labelnom2.setVisible(true);
                nom3.setVisible(true);
                labelnom3.setVisible(true);
                nom4.setVisible(true);
                labelnom4.setVisible(true);
                goToGame.setVisible(true);
                choix1.setVisible(false);
                monimage.setVisible(false);
                //A ajouter : le setter du nombre de joueur ./
            }
        });

        goToGame.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {

                primaryStage.setScene(scene2);
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
    }




    public static void main(String[] args) {
        launch(args);
    }
}
