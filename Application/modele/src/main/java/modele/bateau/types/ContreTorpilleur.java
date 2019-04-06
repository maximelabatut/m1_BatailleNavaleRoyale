package modele.bateau.types;

import modele.bateau.Bateau;

public class ContreTorpilleur extends Bateau {

    private static final int LONGUEUR = 3;
    private static final int LARGEUR = 1;
    private static final int VITESSE = 3;
    private static final int RAYON = 7;

    public ContreTorpilleur() {
        super(LONGUEUR, LARGEUR, VITESSE, RAYON);
    }
}
