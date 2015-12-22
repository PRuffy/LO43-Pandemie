import java.util.ArrayList;

public class CollectionMarqueur {

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

    /*Cette fonction remet des marqueur qui sont sur des UV dans leur collection respective*/
    public void setMarqueur(Marqueur m) throws WrongTypeException{
        switch (m.getFiliere()){
            case I2RV:
                marqueursI2RV.add(m);
                break;
            case ILC:
                marqueursILC.add(m);
                break;
            case LEIM:
                marqueursLEIM.add(m);
                break;
            case RT:
                marqueursRT.add(m);
                break;
            default:
                throw new WrongTypeException("Type Marqueur Inconnu");

        }
    }
    /*Cette fonction enlève 1 marqueur de la collection pour aller le déposé sur l'UV appelante
     *Si la collection est vide alors on appele une fonction qui met fin à la partie (Défaite) */
    public Marqueur getMarqueur(Filiere f) {
        Marqueur m = new Marqueur();

        if (f == Filiere.I2RV){
            if (!marqueursI2RV.isEmpty()){
                m = marqueursI2RV.remove(0);
            }else{
                System.out.println("Perdu");
            }
        }else if(f == Filiere.ILC){
            if (!marqueursILC.isEmpty()){
                m = marqueursILC.remove(0);
            }else{
                System.out.println("Perdu");
            }
        }else if (f == Filiere.LEIM){
            if (!marqueursLEIM.isEmpty()){
                m = marqueursLEIM.remove(0);
            }else{
                System.out.println("Perdu");
            }
        }else if(f == Filiere.RT){
            if (!marqueursRT.isEmpty()){
                m = marqueursRT.remove(0);
            }else{
                System.out.println("Perdu");
            }
        }

        return m;
    }

}

