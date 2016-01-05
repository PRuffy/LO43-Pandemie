package model;

import java.util.ArrayList;

public class UV
{

    private int position;
    private String nom;
    private Filiere filiere;
    private ArrayList<Marqueur> marqueur;
    private ArrayList<UV> voisins;
    private Professeur professeur;
    private ArrayList<Personnage> personnages;
    private boolean testEclosion; // Permet de ne pas repasser plusieurs par la même model.UV en cas d'éclosion


    public UV(){

    }

    public UV(int position, String nom, Filiere filiere){
        this.position=position;
        this.nom=nom;
        this.filiere=filiere;
        testEclosion = false;
        marqueur=new ArrayList<>();
        voisins=new ArrayList<>();
        professeur=null;
        personnages=new ArrayList<>();
    }

    /*Accesseurs aux différents variable de model.UV*/
    public int getPosition(){
        return position;
    }

    public ArrayList<UV> getVoisins(){
        return  this.voisins;
    }

    public ArrayList<Personnage> getPersonnages(){return personnages;}

    public void addPersonnage(Personnage nouveauPersonnage){
        this.personnages.add(nouveauPersonnage);
    }

    public void removePersonnage(Personnage personnageToRemove){
        personnages.remove(personnageToRemove);
    }

    public int getNombreMarqueur(){
        return marqueur.size();
    }

    public Filiere getFiliere(){
            return filiere;
    }

    public String getNom(){
        return this.nom;
    }

    public void addVoisins(UV uv){
        voisins.add(uv);
    }

    public void setEclosion(boolean eclosion){
        this.testEclosion = eclosion;
    }
    public boolean getEclosion(){return this.testEclosion;}
    public void setProfesseur(Professeur p){
        this.professeur=p;
    }
    public Professeur getProfesseur() {
        return professeur;
    }

    public int getNombreVoisins(){
        return voisins.size();
    }

    public boolean correctDestinationProf(Filiere f, int uvCible){
        UV uvDest = voisins.get(uvCible);
        if(uvDest.getFiliere()==f){
            return true;
        }else{
            return false;
        }
    }

    public boolean marqueurFiliere(Filiere f){
        boolean test = false;
        for(Marqueur m : marqueur){
            if(m.getFiliere()==f){
                test = true;
            }
        }

        return test;
    }

    public boolean hasMarqueur(){
        return !marqueur.isEmpty();
    }

    public ArrayList<Marqueur> getMarqueurs(){
        return marqueur;
    }
    /*Recupere un marqueur de la collection correspondante*/
    public void addMarqueur (Marqueur m){
        if(marqueur.size() < 3 ){
            marqueur.add(m);
        }else{
            testEclosion = true;
        }
    }

    /*Enlève un marqueur et le replace dans la collection ou il appartient*/
    public Marqueur removeMarqueur(){
        Marqueur m;
        m = marqueur.remove(0);

        return m;


    }
    public Marqueur removeMarqueur(Filiere f){
        Marqueur m =null;
        if(marqueur.size()!=0){
            for(Marqueur m1 : marqueur){
                if(m1.getFiliere()==f){
                    m=m1;
                }
            }
        }
        //On renvoit null si aucun marqueur ayant la filière ne correspond
        return m;
    }

    public String toString(){
        String temp;
        switch (filiere){
            case ILC:
                temp = "ILC";
                break;
            case I2RV:
                temp = "I2RV";
                break;
            case RT:
                temp = "RT";
                break;
            case LEIM:
                temp = "LEIM";
                break;
            default:
                temp = "none";
                break;
        }
        return (position+" "+nom+" "+temp);
    }


}
