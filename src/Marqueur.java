/**
 * Created by Guillaume on 12/12/2015.
 */
public class Marqueur
{
    private Filiere filiere;

    public Marqueur(Filiere filiere){
        this.filiere=filiere;
    }

    public Marqueur(Marqueur m){
        this.filiere = m.filiere;
    }

    public Filiere getFiliere(){
        return filiere;
    }
}