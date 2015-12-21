import java.util.ArrayList;

/**
 * Created by stay on 14/12/15.
 */
public class Graph {
    private ArrayList<UV> listUV;

    public Graph(){}

    public void travail(int position){
        for(UV uv : listUV){
            if (position == uv.getPosition()){
                uv.removeMarqueur();
            }
        }
    }
    public void eclosion(UV departEclosion){

    }
}
