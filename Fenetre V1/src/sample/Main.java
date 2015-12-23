package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
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


        GridPane grid = new GridPane(); //panneau 1
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        GridPane grid2 = new GridPane(); // Panneau 2
        grid2.setAlignment(Pos.CENTER);
        grid2.setHgap(10);
        grid2.setVgap(10);
        grid2.setPadding(new Insets(25, 25, 25, 25));

        Scene scene2 = new Scene(grid2,800,600);
        scene2.getStylesheets().add
                (Main.class.getResource("Main.css").toExternalForm());

        Scene scene = new Scene(grid, 800, 600);
        scene.getStylesheets().add
                (Main.class.getResource("Main.css").toExternalForm());
        primaryStage.show();

        Text scenetitle = new Text("Veuillez selectionner le nombre de joueurs"); //Ajouter un titre
        grid.add(scenetitle, 0, 0, 2, 1);
        scenetitle.setId("welcome-text");

        Text scene2title = new Text("Scène de jeu ; à continuer :D");
        grid2.add(scene2title,0,0,2,1);
        scene2title.setId("welcome-text2");

        Label userName = new Label("Nombre de joueurs :");
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

        Label labelnom1 = new Label("Entrez le nom du Joueur 1 :");
        grid.add(labelnom1,0,3);
        labelnom1.setVisible(false);

        TextField nom1 = new TextField();
        grid.add(nom1, 1, 3);
        nom1.setVisible(false);

        Label labelnom2 = new Label("Entrez le nom du Joueur 2 :");
        grid.add(labelnom2,0,4);
        labelnom2.setVisible(false);

        TextField nom2 = new TextField();
        grid.add(nom2, 1, 4);
        nom2.setVisible(false);

        Label labelnom3 = new Label("Entrez le nom du Joueur 3 :");
        grid.add(labelnom3,0,5);
        labelnom3.setVisible(false);

        TextField nom3 = new TextField();
        grid.add(nom3, 1, 5);
        nom3.setVisible(false);

        Label labelnom4 = new Label("Entrez le nom du Joueur 4 :");
        grid.add(labelnom4,0,6);
        labelnom4.setVisible(false);

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
                //A ajouter : le setter du nombre de joueur ./
            }
        });

        goToGame.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {

                primaryStage.setScene(scene2);
            }
        });
        primaryStage.setScene(scene);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
