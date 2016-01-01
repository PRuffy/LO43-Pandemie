import javax.swing.*;

/**
 * Created by Guillaume on 12/12/2015.
 */
public class CarteInfection implements Carte
{
    private UV cible;
    private Filiere filiere;

    public CarteInfection(){}
    public CarteInfection(UV cible, Filiere filiere){
        this.cible=cible;
        this.filiere=filiere;
    }

    public CarteInfection(CarteInfection clone){
        this.cible=clone.cible;
        this.filiere=clone.filiere;
    }



    public UV getCible(){
        return cible;
    }
    public Filiere getFiliere(){
        return filiere;
    }
}
