/**
 * Created by stay on 22/12/15.
 */
public class Joueur {
    private Role role;
    private Personnage position;
    private CarteSemestre[] carteEnMain[];

    public Joueur(Role role){
        this.role = role;
        this.position = new Personnage(1);
        this.carteEnMain [6] = new CarteSemestre [6];
    }


}
