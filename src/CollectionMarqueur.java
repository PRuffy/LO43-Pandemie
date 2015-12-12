import java.util.ArrayList;

/**
 * Created by Guillaume on 12/12/2015.
 */
public class CollectionMarqueur
{

    private ArrayList<Marqueur> marqueursI2RV;
    private ArrayList<Marqueur> marqueursILC;
    private ArrayList<Marqueur> marqueursLEIM;
    private ArrayList<Marqueur> marqueursRT;

    public CollectionMarqueur(){
        marqueursI2RV=new ArrayList<Marqueur>();
        for(int i=0;i<18;++i){
            marqueursI2RV.add(new Marqueur(Filiere.I2RV));
        }
        marqueursILC=new ArrayList<Marqueur>();
        for(int i=0;i<18;++i){
            marqueursILC.add(new Marqueur(Filiere.ILC));
        }
        marqueursLEIM=new ArrayList<Marqueur>();
        for(int i=0;i<18;++i){
            marqueursLEIM.add(new Marqueur(Filiere.LEIM));
        }
        marqueursRT=new ArrayList<Marqueur>();
        for(int i=0;i<18;++i){
            marqueursRT.add(new Marqueur(Filiere.RT));
        }
    }

    public ArrayList<Marqueur> getMarqueursI2RV(){
        return marqueursI2RV;
    }
    public ArrayList<Marqueur> getMarqueursILC(){
        return marqueursILC;
    }
    public ArrayList<Marqueur> getMarqueursLEIM(){
        return marqueursLEIM;
    }
    public ArrayList<Marqueur> getMarqueursRT(){
        return marqueursRT;
    }
}
