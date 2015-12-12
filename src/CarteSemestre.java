import java.lang.reflect.Type;

/**
 * Created by Guillaume on 12/12/2015.
 */
public class CarteSemestre implements Carte
{
    private TypeCarteSemestre type;
    private UV cible;
    private Filiere filiere;

    public CarteSemestre(TypeCarteSemestre type, UV cible, Filiere filiere){
        this.type=type;
        if(type==TypeCarteSemestre.TP) {
            this.cible = cible;
            this.filiere = filiere;
        }
    }
    public CarteSemestre(CarteSemestre clone){
        this.type=clone.type;
        this.cible=clone.cible;
        this.filiere=clone.filiere;
    }
}
