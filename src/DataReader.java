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
