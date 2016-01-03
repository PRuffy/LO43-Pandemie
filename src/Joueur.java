/**
 * Created by stay on 22/12/15.
 */
public class Joueur {
    private int nombreAction;
    private Role role;
    private Personnage position;
    private CarteSemestre carteEnMain[];

    public Joueur(Role role){
        this.role = role;
        this.position = new Personnage(1);
        this.carteEnMain = new CarteSemestre [6];
    }


    //constructeur par recopie
    public Joueur(Joueur clone){
        this.role=clone.role;
        this.position=clone.position;
        this.carteEnMain= new CarteSemestre[6];
        for(int index=0; index<6; index++){
            this.carteEnMain[index]=clone.carteEnMain[index];
        }
    }


    //Accesseurs
    public CarteSemestre[] getHand(){
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
    public boolean getMainComplete(){
        if (carteEnMain[5]!=null){
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

        if(nombreCarteFiliere > 3 ){
            return true;
        }else{
            if(role == Role.surdoue){
                if(nombreCarteFiliere > 2){
                    return true;
                }
            }
            return false;
        }
    }

    public boolean hasCarte(UV uv){
        for(CarteSemestre carte : carteEnMain){
            if(carte.getCible()==uv){
                return  true;
            }
        }
        return false;
    }
    /*
     ajoutCarte sert à rajouter une carte tp dans la main du joueur. deux exceptions peuvent etre renvoyées :
        - NotEnoughSlotsException si le joueur n'a pas assez de place dans sa main
        - WrongTypeException si la carte n'est pas une carte TP
     */
    public void ajoutCarte(CarteSemestre carte) throws NotEnoughSlotsException, WrongTypeException{
        try {
            //On vérifie le type de la carte passée en paramètre
            if (carte.getType() != TypeCarteSemestre.TP)
                throw new WrongTypeException("Un joueur ne peut avoir que des cartes TP en main");


            int index = 0;
            boolean found = false;

            // on parcours le tableau à la recherche d'un emplacement vide pour la nouvelle carte
            while (index < 6 && !found) {
                if (carteEnMain[index] == null) {
                    carteEnMain[index] = carte;
                    found = true;
                }
                ++index;
            }
            // Si aucun emplacement libre n'a été trouvé dans la main du joueur
            if (!found) {
                throw new NotEnoughSlotsException("Il n'y a plus de place dans la main de ce joueur");
            }
        }
        catch(NotEnoughSlotsException e){}
        catch(WrongTypeException e){}
        catch(Exception e){}
    }

    /*
     retraitCarte sert à enlever une carte donnée de la main du joueur. deux exceptions peuvent etre renvoyées :
        - NotSuchCardException si le joueur n'a pas cette carte en main
        - WrongTypeException si la carte n'est pas une carte TP
     */
    public CarteSemestre retraitCarte(CarteSemestre carte) throws NoSuchCardException, WrongTypeException{
        try{
            if (carte.getType() != TypeCarteSemestre.TP)
                throw new WrongTypeException("Un joueur ne peut avoir que des cartes TP en main");


            int index = 0;
            CarteSemestre tempCarte = null;

            // on parcours le tableau à la recherche de la carte
            while (index < 6 && tempCarte==null) {
                if (carteEnMain[index].equals(carte)) {
                    tempCarte = new CarteSemestre(carte);

                    for(int i = index; i < 5; i++){
                        carteEnMain[i]=carteEnMain[i+1];
                    }
                    carteEnMain[5]=null;
                }
                ++index;
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
        int index = 0;
        CarteSemestre tempCarte = null;

        while(index < 6 && tempCarte == null){
            if(carteEnMain[index].getFiliere()==f){
                tempCarte = carteEnMain[index];
                //On avance les cartes dans le tableau pour ne garder que les derniere case null
                for(int i = index; i < 5; i++){
                    carteEnMain[i]=carteEnMain[i+1];
                }
                carteEnMain[5]=null;
            }
            index++;
        }

        return tempCarte;
    }
}