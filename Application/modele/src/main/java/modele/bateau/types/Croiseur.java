package modele.bateau.types;

import modele.bateau.Bateau;

public class Croiseur extends Bateau {

    private static final int LONGUEUR = 4;
    private static final int LARGEUR = 1;
    private static final int VITESSE = 2;
    private static final int RAYON = 10;

    public Croiseur() {
        super(LONGUEUR, LARGEUR, VITESSE, RAYON);
    }
}
