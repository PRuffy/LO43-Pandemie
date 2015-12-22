import java.util.ArrayList;

public class Graph {
    private ArrayList<UV> listUV;
    private boolean eclosionLancer;

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
    public void eclosion(UV departEclosion, Filiere f, CollectionMarqueur reserveMarqueur){
        if(!eclosionLancer){
            eclosionLancer=true;
        }

        Marqueur m = new Marqueur();
        for(UV uv : departEclosion.getVoisins()){
            if(!uv.getEclosion()){
                m = reserveMarqueur.getMarqueur(f);
                uv.addMarqueur(m);
                uv.setEclosion(true);
            }
        }


        for(UV uv : listUV){
            uv.setEclosion(false);
        }

        eclosionLancer = false;
    }
}
