import java.util.ArrayList;

/**
 * Created by stay on 14/12/15.
 */
public class Graph {
    private ArrayList<UV> listUV;

    public Graph(){}

    /*Fonction appelé par la classe Jeu quand le joueur veux travailler.
     *Appel alors la fonction removeMarqueur de la classe UV.
     * Si un argument est envoyé alors il s'agit d'une méthode qui enlève tout les marqueurs d'une UV.
     * Sinon enlève seulement un marqueur
     */
    public void travail(int position, Role rolePersonnage){
        for(UV uv : listUV){
            if (position == uv.getPosition()){
                if(rolePersonnage == Role.surdoue) {
                    uv.removeMarqueur(1);
                }else{
                    uv.removeMarqueur();
                }
            }
        }
    }

    /*Reçois l'uv de départ et appel la fonction addMarqueur pour ses voisins
     *Place le boolean eclosion a 1 pour éviter de repasser plusieurs fois par la même UV
     * Enfin, une fois l'eclosion terminé remet tout les booleen a 0
     */
    public void eclosion(UV departEclosion, Filiere f){
        for(UV uv : departEclosion.getVoisins()){
            if(!uv.getEclosion()){
                uv.addMarqueur(f);
                uv.setEclosion(true);
            }
        }


        for(UV uv : listUV){
            uv.setEclosion(false);
        }
    }
}
