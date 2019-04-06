package modele.bateau.types;

import modele.bateau.Bateau;

public class Torpilleur extends Bateau {

    private static final int LONGUEUR = 2;
    private static final int LARGEUR = 1;
    private static final int VITESSE = 4;
    private static final int RAYON = 5;

    public Torpilleur() {
        super(LONGUEUR, LARGEUR, VITESSE, RAYON);
    }
}
