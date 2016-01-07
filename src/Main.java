package model;

import java.io.FileNotFoundException;

public class Main{
    public static void main (String args[]) throws FileNotFoundException {


        DataReader dat = new DataReader("UV.jjq");

        if(dat.hasSuccessfullyLoaded()){
            //Jeu jeu = new Jeu(4, dat);
            System.out.println("Hello world");
        }

    }
}
