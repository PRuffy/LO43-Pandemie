import java.io.File;

public class Main{
    public static void main (String args[]) {
        System.out.println("Hello world");
        File listeUVText = new File("UV.txt");
        File adjacenceUVText = new File("adjacenceUV.txt");
        String[] nomUV = new String [24];
        Filiere[] filiereUV = new Filiere[24];
        int[][] adjacenceUV = new int[24][24];





        Jeu jeu = new Jeu(4, nomUV, filiereUV, adjacenceUV);
    }
}

