import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main{
    public static void main (String args[]) throws FileNotFoundException {
        System.out.println("Hello world");

        DataReader dat = new DataReader("UV.jjq");

        if(dat.hasSuccessfullyLoaded()){
            Jeu jeu = new Jeu(4, dat);
        }

    }
}
