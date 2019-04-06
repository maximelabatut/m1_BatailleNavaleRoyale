package modele;


import exceptions.partie.TaillePlateauIncorrecteException;

public class Plateau implements IPlateau{

    private Case[][] lePlateau;

    /**
     * Constructeur du plateau
     *
     * @param largeur du plateau
     * @param hauteur du plateau
     * @throws TaillePlateauIncorrecteException si la taille du plateau n'est pas correcte
     */
    public Plateau(int largeur, int hauteur) throws TaillePlateauIncorrecteException {
        if ((largeur != hauteur) || largeur < 100)
            throw new TaillePlateauIncorrecteException();
        this.lePlateau = new Case[largeur][hauteur];
        for (int i = 0; i < largeur; i++) {
            for (int j = 0; j < hauteur; j++) {
                this.lePlateau[i][j] = new Case(i, j);
            }
        }
    }

    /**
     * Méthode permettant de récupérer la taille du plateau
     *
     * @return l'entier correspondant à la taille du plateau
     */
    public int getTaillePlateau() {
        return this.lePlateau.length;
    }

    /**
     * Méthode permettant de copier un plateau
     *
     * @return la copie du plateau
     */
    public Case[][] copy() {
        Case[][] maCopy = new Case[getTaillePlateau()][getTaillePlateau()];
        for (int i = 0; i < getTaillePlateau(); i++) {
            for (int j = 0; j < getTaillePlateau(); j++) {
                Case caseACopier = lePlateau[i][j];
                Case maNouvelleCase = new Case(i, j);
                maNouvelleCase.setEau(caseACopier.isEau());
                maNouvelleCase.setImgPath(caseACopier.getImgPath());
                maCopy[i][j] = maNouvelleCase;
            }
        }
        return maCopy;
    }

    // GETTER

    public Case[][] getLePlateau() {
        return lePlateau;
    }


}
