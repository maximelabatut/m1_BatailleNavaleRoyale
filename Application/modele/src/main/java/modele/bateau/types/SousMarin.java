package modele.bateau.types;

import modele.bateau.Bateau;

public class SousMarin extends Bateau {

    private static final int LONGUEUR = 3;
    private static final int LARGEUR = 1;
    private static final int VITESSE = 5;
    private static final int RAYON = 3;

    public SousMarin() {
        super(LONGUEUR, LARGEUR, VITESSE, RAYON);
    }
}
