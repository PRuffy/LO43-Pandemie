package model;

/**
 * Created by Guillaume on 12/12/2015.
 */
public class Personnage {
    private int position;


    public Personnage(){
        this.position=0;
    }
    public Personnage(int position){
        this.position=position;
    }

    public int getPosition(){
        return position;
    }
    public void setPosition(int pos){
        this.position=pos;
    }
}
