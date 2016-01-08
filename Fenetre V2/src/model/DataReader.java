package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;


public class DataReader {

    /*
        fileName : nom du fichier qi va être chargé
        uv : collection d'objet model.UV, remplie par le chargement d'après les données du fichier lu
        adjacence : matrice d'adjacence des uv stockées dans la collection
        successfulLoad : booleen, vaut 'true' si le chargement du fichier est arrivé à son terme, et 'false' sinon

        DEBUG : booleen, par défaut à 'false', à mettre sur 'true' pour afficher des valeurs utiles au debugging dans la console.
     */
    private String fileName;
    private ArrayList<UV> uv;
    private int adjacence[][];
    private int[][] coordUVSprite;
    private int[][] coordHandCardsSprites;
    private int[][] coordLibraryAndGraveyard;
    private int[][] coordPlayerIndicator;
    private int[][] coordProjectIndicator;

    private boolean successfulLoad;

    private boolean DEBUG = true;
    private boolean GENERATE_NEW_FILE = false;

    /*
        Constructeur : nom du fichier cible en paramètre
     */
    public DataReader(String fileName){
        this.fileName=fileName;
        if(!GENERATE_NEW_FILE){
            successfulLoad = loadData();
        }else{
            generateNewFile(fileName);
            successfulLoad = false;
        }

    }

    /*
        Méthode loadData() : parcours le fichier pour lire les deux séparateur, la liste des uv ainsi que la matrice d'adjacence.
     */
    public boolean loadData(){
        Properties properties = new Properties();
        InputStream input;


        try {
            // on lie le fichier et on récupère les properties
            input = new FileInputStream(fileName);
            properties.load(input);

            // on récupère les séparateurs de lignes et de colonnes.
            String rowSeparator = properties.getProperty("rowSeparator");
            String columnSeparator = properties.getProperty("columnSeparator");

            if (DEBUG) {
                System.out.println(rowSeparator + "\t\t" + columnSeparator + "\n");
                System.out.println(properties.getProperty("UV") + "\n");
                System.out.println(properties.getProperty("Adjacence") + "\n");
            }

            // on instancie la liste d'uv, on récupère la liste 'brute' depuis le fichier, que l'on va split suivant les deux séparateurs pour extraire le contenu
            // l'index permet de donner la position des model.UV, qui n'a pas besoin d'être stockée dans le fichier
            uv = new ArrayList<>();
            String rawUV = properties.getProperty("UV");
            String uvs[] = rawUV.split(rowSeparator);
            String currentUV[];
            int index = 1;

            for (String uv : uvs) {
                currentUV = uv.split(columnSeparator);
                this.uv.add(new UV(index, currentUV[0], getFiliere(currentUV[1])));
                index++;
            }

            adjacence = new int[this.uv.size()][this.uv.size()];
            String rawAdj = properties.getProperty("Adjacence");
            int indexRow = 0, indexColumn;
            for (String row : rawAdj.split(rowSeparator)) {
                indexColumn = 0;
                for (String point : row.split(columnSeparator)) {
                    adjacence[indexRow][indexColumn] = Integer.parseInt(point);
                    if(DEBUG){System.out.print(Integer.parseInt(point)+" ");}
                    indexColumn++;
                }
                indexRow++;
                if(DEBUG){System.out.print("\n");}
            }

            coordUVSprite = new int[24][2];
            String rawCUS = properties.getProperty("coordUVSprites");
            indexRow = 0;
            for (String row : rawCUS.split(rowSeparator)) {
                indexColumn = 0;
                for (String point : row.split(columnSeparator)) {
                    coordUVSprite[indexRow][indexColumn] = Integer.parseInt(point);
                    if(DEBUG){System.out.print(Integer.parseInt(point)+" ");}
                    indexColumn++;
                }
                indexRow++;
                if(DEBUG){System.out.print("\n");}
            }

            coordHandCardsSprites= new int[5][2];
            String rawCHCS = properties.getProperty("coordHandCardsSprites");
            indexRow = 0;
            for (String row : rawCHCS.split(rowSeparator)) {
                indexColumn = 0;
                for (String point : row.split(columnSeparator)) {
                    coordHandCardsSprites[indexRow][indexColumn] = Integer.parseInt(point);
                    if(DEBUG){System.out.print(Integer.parseInt(point)+" ");}
                    indexColumn++;
                }
                indexRow++;
                if(DEBUG){System.out.print("\n");}
            }

            coordLibraryAndGraveyard = new int[4][2];
            String rawCLAG = properties.getProperty("coordLibraryAndGraveyard");
            indexRow = 0;
            for (String row : rawCLAG.split(rowSeparator)) {
                indexColumn = 0;
                for (String point : row.split(columnSeparator)) {
                    coordLibraryAndGraveyard[indexRow][indexColumn] = Integer.parseInt(point);
                    if(DEBUG){System.out.print(Integer.parseInt(point)+" ");}
                    indexColumn++;
                }
                indexRow++;
                if(DEBUG){System.out.print("\n");}
            }

            coordPlayerIndicator = new int[4][2];
            String rawCPlI = properties.getProperty("coordPlayerIndicator");
            indexRow = 0;
            for (String row : rawCPlI.split(rowSeparator)) {
                indexColumn = 0;
                for (String point : row.split(columnSeparator)) {
                    coordPlayerIndicator[indexRow][indexColumn] = Integer.parseInt(point);
                    if(DEBUG){System.out.print(Integer.parseInt(point)+" ");}
                    indexColumn++;
                }
                indexRow++;
                if(DEBUG){System.out.print("\n");}
            }

            coordProjectIndicator = new int[24][2];
            String rawCPrI = properties.getProperty("coordProjectIndicator");
            indexRow = 0;
            for (String row : rawCPrI.split(rowSeparator)) {
                indexColumn = 0;
                for (String point : row.split(columnSeparator)) {
                    coordProjectIndicator[indexRow][indexColumn] = Integer.parseInt(point);
                    if(DEBUG){System.out.print(Integer.parseInt(point)+" ");}
                    indexColumn++;
                }
                indexRow++;
                if(DEBUG){System.out.print("\n");}
            }
        }catch(WrongTypeException e){
                System.out.println("Erreur de lecture de fichier : une filière n'a pas été reconnue");
                return false;
        }
        catch(IOException e){
            e.printStackTrace();
            return false;
        }


        return true;
    }


    /*
        La méthode generateNewFile permet de ... attention gros spoil ... générer une nouvelle fois le fichier!! #NoShitSherlock
        Les valeurs des properties sont rentrées en dur dans le code, et doivent être changées si le graph est modifié (Date et heure actuelle : 04 Jan 2016, 22:15 GMT)
     */
    public void generateNewFile(String fileName)
    {
        Properties properties = new Properties();
        properties.setProperty("UV", "BD51;ILC;;GL51;ILC;;BD50;ILC;;GL52;ILC;;LO51;ILC;;IA54;ILC;;IN52;I2RV;;VI50;I2RV;;IN54;I2RV;;MT51;I2RV;;IN55;I2RV;;VI51;I2RV;;TR53;LEIM;;MI52;LEIM;;SM57;LEIM;;LO52;LEIM;;LO53;LEIM;;TR54;LEIM;;RE52;RT;;RE56;RT;;RE51;RT;;TL53;RT;;RE53;RT;;RE55;RT");
        properties.setProperty("Adjacence", "0;1;0;0;1;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;;0;0;0;1;0;1;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;;0;0;0;1;1;1;0;0;0;0;0;0;1;0;0;0;0;0;0;0;0;0;0;0;;0;0;0;0;0;1;0;0;0;0;0;1;0;0;0;0;0;0;0;0;0;0;0;0;;0;0;0;0;0;1;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;;0;0;0;0;0;0;0;1;1;0;1;0;0;0;0;0;0;0;0;0;0;0;0;0;;0;0;0;0;0;0;0;0;1;1;1;0;0;0;0;0;0;0;0;0;0;0;0;0;;0;0;0;0;0;0;0;0;0;1;0;0;0;0;0;0;0;0;0;0;1;0;0;0;;0;0;0;0;0;0;0;0;0;0;0;1;0;0;0;0;0;0;0;0;0;0;0;0;;0;0;0;0;0;0;0;0;0;0;0;1;0;0;0;0;0;0;0;0;0;0;0;0;;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;;0;0;0;0;0;0;0;0;0;0;0;0;0;0;1;0;0;1;0;0;0;0;0;0;;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;1;1;1;0;0;0;0;0;0;;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;1;1;0;0;0;0;0;0;;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;1;0;0;0;0;1;0;0;;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;1;0;0;0;0;0;0;;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;1;0;0;1;1;;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;1;1;0;0;;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;1;0;;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;1;;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;1;;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0");
        properties.setProperty("rowSeparator", ";;");
        properties.setProperty("columnSeparator", ";");
        properties.setProperty("coordUVSprites", "345;372;;390;325;;459;380;;443;343;;434;442;;409;380;;300;160;;350;192;;317;105;;400;150;;325;252;;410;225;;565;319;;682;375;;612;278;;727;270;;669;300;;619;360;;675;165;;625;192;;600;135;;739;175;;666;110;;711;126");
        properties.setProperty("coordHandCardsSprites", "320;530;;380;530;;440;530;;500;530;;560;530");
        properties.setProperty("coordLibraryAndGraveyard", "646;450;;726;450;;646;530;;726;530");
        properties.setProperty("coordPlayerIndicator", "35;550;;70;550;;105;550;;140;550");
        properties.setProperty("coordProjectIndicator", "210;10;;245;10;;280;10;;315;10");

        OutputStream output = null;
        File file = new File(fileName);

        try {
            output = new FileOutputStream(file);
            properties.store(output, "Pandemie model.Graph model.UV + matrice d'adjacence - Ruffy Montella Tsagalos Prost - LO43, A2015");
            output.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (output != null) {
                output.close();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }


    /*
        La méthode getFiliere permet de récupérer un objet Filière à partir d'une chaine de caractère.
        Elle est appelée lors de la lecture du fichier, lors du remplissage le la collection 'uv'
     */
    private Filiere getFiliere(String filiereName) throws WrongTypeException {
        switch(filiereName){
            case "ILC" : return Filiere.ILC;
            case "LEIM" : return Filiere.LEIM;
            case "RT" : return Filiere.RT;
            case "I2RV" : return Filiere.I2RV;
            default : throw new WrongTypeException("model.Filiere non-reconnue");
        }
    }


    /*
        Accesseur basiques
     */

    public int[][] getCoordHandCardsSprites() {
        return coordHandCardsSprites;
    }
    public int[][] getCoordLibraryAndGraveyard() {
        return coordLibraryAndGraveyard;
    }
    public int[][] getCoordPlayerIndicator() {
        return coordPlayerIndicator;
    }
    public int[][] getCoordProjectIndicator() {
        return coordProjectIndicator;
    }
    public int[][] getCoordUVSprite(){ return coordUVSprite;}
    public ArrayList<UV> getUv() {
        return uv;
    }
    public int[][] getAdjacence() {
        return adjacence;
    }
    public boolean hasSuccessfullyLoaded() {
        return successfulLoad;
    }
}
