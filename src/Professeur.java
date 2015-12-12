import javafx.geometry.Pos;

/**
 * Created by Guillaume on 12/12/2015.
 */
public class Professeur {

    private Filiere filiere;
    private int position;
    private boolean deplacementEffectue;

    public Professeur(Filiere f)
    {
        filiere=f;
        position=0;
        deplacementEffectue=false;
    }
    public Professeur(Filiere f, int position){
        filiere=f;
        this.position=position;
        deplacementEffectue=false
    }

    public Filiere getFiliere(){
        return filiere;
    }
    public int getPosition(){
        return position;
    }
    public void setPosition(int p){
        position=p;
    }
    public boolean isDeplace(){
        return deplacementEffectue;
    }
    public void setDeplace(boolean state){
        deplacementEffectue=state;
    }


}
