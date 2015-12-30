import java.util.ArrayList;

public class Graph {
    private ArrayList<UV> listUV;
    private boolean eclosionLancer;

    public Graph(){
        listUV = new ArrayList<UV>();
        eclosionLancer = false;
    }


    public int getListSize(){
        return listUV.size();
    }
    public UV getUV(int position){
        UV uvPos = new UV();
        for (UV uv: listUV){
            if (uv.getPosition()==position){
                uvPos = uv;
            }
        }
        return uvPos;
    }

    public Filiere getUVFiliere(int position){
        Filiere f = null;
        for(UV uv : listUV){
            if (uv.getPosition()==position){
                f = uv.getFiliere();
            }
        }
        return f;
    }
    /*Fonction appelé par la classe Jeu quand le joueur veux travailler.
     *Appel alors la fonction removeMarqueur de la classe UV.
     * Si un argument est envoyé alors il s'agit d'une méthode qui enlève tout les marqueurs d'une UV.
     * Sinon enlève seulement un marqueur
     */
    public Marqueur travail(int position){
        Marqueur m = new Marqueur();
        for(UV uv:listUV){
            if(uv.getPosition()==position){
                m = uv.removeMarqueur();
            }
        }
        return m;
    }

    //Méthode enlevant les marqueur d'une filière donné sur une case donnée.
    //Enlève tout les maruque de la filière.
    public void travail(int position, Filiere f, CollectionMarqueur reserve){
        for(UV uv: listUV){
            if(uv.getPosition()==position){

                while(uv.marqueurFiliere(f)){
                    reserve.setMarqueur(uv.removeMarqueur(f));
                }

            }
        }
    }

    public boolean uvHasMarqueur(int p){
        boolean test = false;
        for(UV uv : listUV){
            if(uv.getPosition()==p){
                test =  uv.hasMarqueur();
            }
        }
        return test;
    }

    //Méthode parcourant le graph et remettant les valeurs d'éclosion a false avant le prochain tour.
    public void finTour(){
        eclosionLancer = false;
        for (UV uv : listUV){
            uv.setEclosion(false);
        }
    }
}
