import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main{
    public static void main (String args[]) throws FileNotFoundException {
        System.out.println("Hello world");

        File listeUVText = new File("UV.txt");
        File adjacenceUVText = new File("adjacenceUV.txt");

        String[] nomUV = new String [24];
        Filiere[] filiereUV = new Filiere[24];

        int[][] adjacenceUV = new int[24][24];


        try{

            Scanner scannerUV = new Scanner(listeUVText);

            Scanner scannerAdjacence = new Scanner(adjacenceUVText);




            //On fait la liste des UV et celle des filière
            String test;
            for(int i = 0 ; i<24;i++){
                try{
                    test = scannerUV.next();
                    System.out.println(test);
                }catch (NoSuchElementException e){
                   break;
                }

            }
            scannerUV.close();



            //On remplit la liste d'adjacence
            for(int i=0;i<24;i++){
                for(int j =0; j<24;j++){
                    try {
                        if (scannerAdjacence.nextInt() == 1){
                            adjacenceUV[i][j] = 1;
                        } else {
                            adjacenceUV[i][j] = 0;
                        }
                    } catch (NoSuchElementException e) {
                        break;
                    }
                }
            }

            scannerAdjacence.close();

        }catch (FileNotFoundException e){
            System.out.println("No such file");
        }

        Jeu jeu = new Jeu(4, nomUV, filiereUV, adjacenceUV);


    }
}
