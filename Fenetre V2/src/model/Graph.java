package model;

import java.util.ArrayList;

public class Graph {
    private ArrayList<UV> listUV;
    private boolean eclosionLancer;

    //public model.Graph(String[] nomUV, model.Filiere[] filiereUV, int[][] adjacenceUV){
    public Graph(DataReader dat){
        listUV = dat.getUv();

        //Mise a jour des liste des voisins
        UV tempUV1 = null;
        UV tempUV2 = null;
        for(int i = 0; i<24;i++){
            for(int j = i; j<24;j++){
                if(dat.getAdjacence()[i][j]==1){
                    for(UV uv:listUV){
                        if(uv.getPosition()==i+1){
                            tempUV1 = uv;
                        }

                        if(uv.getPosition()==j+1){
                            tempUV2 = uv;
                        }
                    }

                    tempUV1.addVoisins(tempUV2);
                    tempUV2.addVoisins(tempUV1);
                }
            }
        }
        eclosionLancer = false;
    }


    public int getListSize(){
        return listUV.size();
    }
    public UV getUV(int position){
        UV uvWanted = new UV();
        for (UV uv: listUV){
            if (uv.getPosition()==position){
                uvWanted = uv;
            }
        }
        return uvWanted;
    }

    public UV getUV(String name){
        UV uvWanted = new UV();
        for (UV uv: listUV){
            if (uv.getNom()==name){
                uvWanted = uv;
            }
        }
        return uvWanted;
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
     *Appel alors la fonction removeMarqueur de la classe V.
     * Si un argument est envoyé alors il s'agit d'une méthode qui enlève tous les marqueurs d'une UV.
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
    //Enlève tout les marqueurs de la filière.
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

    public ArrayList<UV> getListUV(){
        return listUV;
    }

    //Méthode parcourant le graph et remettant les valeurs d'éclosion a false avant le prochain tour.
    public void finTour(){
        eclosionLancer = false;
        for (UV uv : listUV){
            uv.setEclosion(false);
        }
    }

    public void printAllUV(){
        for(UV current : listUV){
            System.out.println(current.toString());
        }
    }
}
