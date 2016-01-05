package model;

import java.util.ArrayList;

/**
 * Created by stay on 22/12/15.
 */
public class Joueur {
    private int nombreAction;
    private Role role;
    private Personnage position;
    private ArrayList<CarteSemestre> carteEnMain;

    public Joueur(Role role){
        this.role = role;
        this.position = new Personnage(1);
        this.carteEnMain = new ArrayList<>();
    }


    //Accesseurs
    public ArrayList<CarteSemestre> getHand(){
        return carteEnMain;
    }
    public int getNombreAction(){
        return nombreAction;
    }
    public void setNombreAction(int i){
        nombreAction = i;
    }
    public void setPosition(int i){
        position.setPosition(i);
    }
    public int getPosition(){
        return position.getPosition();
    }
    public boolean isMainComplete(){
        if (carteEnMain.size()==6){
            return true;
        }else{
            return false;
        }
    }
    public Role getRole(){
        return this.role;
    }


    public boolean verifierCarte(Filiere f){
        int nombreCarteFiliere = 0;
        for (CarteSemestre c : carteEnMain){
            if(c.getFiliere()==f){
                nombreCarteFiliere++;
            }
        }

        if(nombreCarteFiliere > 3 || (role == Role.surdoue && nombreCarteFiliere > 2)){
            return true;
        }else{
            return false;
        }
    }

    public boolean hasCarte(UV uv){
        for(CarteSemestre carte : carteEnMain){
            System.out.println("plop");
            if(carte.getCible()==uv){
                return  true;
            }
        }
        return false;
    }
    /*
     ajoutCarte sert à rajouter une carte tp dans la main du joueur. deux exceptions peuvent etre renvoyées :
        - model.NotEnoughSlotsException si le joueur n'a pas assez de place dans sa main
        - model.WrongTypeException si la carte n'est pas une carte TP
    */
    public void ajoutCarte(CarteSemestre carte) throws NotEnoughSlotsException, WrongTypeException{
        try {
            //On vérifie le type de la carte passée en paramètre
            if (carte.getType() != TypeCarteSemestre.TP)
                throw new WrongTypeException("Un joueur ne peut avoir que des cartes TP en main");
            if (isMainComplete())
                throw new NotEnoughSlotsException("Un joueur ne peut avoir que 6 cartes en main");

            carteEnMain.add(carte);

        }
        catch(NotEnoughSlotsException e){}
        catch(WrongTypeException e){}
        catch(Exception e){}
    }

    /*
     retraitCarte sert à enlever une carte donnée de la main du joueur. deux exceptions peuvent etre renvoyées :
        - NotSuchCardException si le joueur n'a pas cette carte en main
        - model.WrongTypeException si la carte n'est pas une carte TP
     */
    public CarteSemestre retraitCarte(CarteSemestre carte) throws NoSuchCardException, WrongTypeException{
        try{
            if (carte.getType() != TypeCarteSemestre.TP)
                throw new WrongTypeException("Un joueur ne peut avoir que des cartes TP en main");


            int index = 0;
            CarteSemestre tempCarte = null;

            if(carteEnMain.contains(carte)){
                tempCarte = carteEnMain.get(carteEnMain.indexOf(carte));
                carteEnMain.remove(carte);
            }
            // Si la carte n'a pas été trouvé dans la main (ou si la main est vide)
            if (tempCarte!=null) {
                return tempCarte;
            }else{
                throw new NoSuchCardException("Cette carte ne se trouve pas dans la main de ce joueur");
            }
        }
        catch(NoSuchCardException e){return null;}
        catch(Exception e){return null;}
    }

    //Effectue une opération similaire a la fonction précédente. Cependant enlève une carte queconque de la main du joueur
    //Ayant la filière voulu
    public CarteSemestre retraitCarte(Filiere f){
        CarteSemestre tempCarte=null;

        for(CarteSemestre current : carteEnMain){
            if(current.getFiliere()==f && tempCarte==null){
                tempCarte = current;
                carteEnMain.remove(current);
            }
        }

        return tempCarte;
    }
}