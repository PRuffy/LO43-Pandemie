/**
 * Created by stay on 22/12/15.
 */
public class Joueur {
    private Role role;
    private Jeu jeu;
    private Personnage position;
    private CarteSemestre carteEnMain[];

    public Joueur(Role role){
        this.role = role;
        this.position = new Personnage(1);
        this.carteEnMain = new CarteSemestre [6];
    }

    public Joueur(Jeu jeu, Role role, Personnage position){
        this.jeu=jeu;
        this.role=role;
        this.position=position;
        this.carteEnMain= new CarteSemestre[6];
    }

    //constructeur par recopie
    public Joueur(Joueur clone){
        this.jeu=clone.jeu;
        this.role=clone.role;
        this.position=clone.position;
        this.carteEnMain= new CarteSemestre[6];
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
            CarteSemestre temp = null;

            // on parcours le tableau à la recherche de la carte
            while (index < 6 && temp==null) {
                if (carteEnMain[index].equals(carte)) {
                    temp = new CarteSemestre(carte);
                }
                ++index;
            }
            // Si la carte n'a pas été trouvé dans la main (ou si la main est vide)
            if (temp!=null) {
                return temp;
            }else{
                throw new NoSuchCardException("Cette carte ne se trouve pas dans la main de ce joueur");
            }
        }
        catch(NoSuchCardException e){return null;}
        catch(Exception e){return null;}
    }
}