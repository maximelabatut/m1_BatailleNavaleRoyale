package modele.bateau.types;

import modele.bateau.Bateau;

public class PorteAvion extends Bateau {

    private static final int LONGUEUR = 5;
    private static final int LARGEUR = 2;
    private static final int VITESSE = 1;
    private static final int RAYON = 15;

    public PorteAvion() {
        super(LONGUEUR, LARGEUR, VITESSE, RAYON);
    }
}
