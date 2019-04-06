package modele.bateau;

import exceptions.bateau.BateauNonReparableException;
import modele.Case;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Bateau {

    /**
     * A LIRE AVANT DE TENTER DE COMPRENDRE LA CLASSE :
     * Par convention ->
     * TETEHAUT : 1
     * TETEDROITE : 2
     * TETEBAS : 3
     * TETEGAUCHE : 4
     */

    private int rayon;
    private int largeur;
    private int longueur;
    private int vitesse;
    private int posX; //position x du moteur
    private int posY; //position y du moteur
    private boolean isMoteurTouche;
    private List<Boolean> dommages;
    private int angleOrientation;

    /**
     * Constructeur de la classe
     *
     * @param longueur totale d'un bateau (donne par cahier charges)
     * @param largeur  totale d'un bateau (donne par cahier charges)
     * @param vitesse  d'un bateau (donne par cahier charges)
     * @param rayon    d'un bateau -> correspond au nb de case devoilé par le presence du dit bateau (donne par cahier charges)
     */
    public Bateau(int longueur, int largeur, int vitesse, int rayon) {
        this.longueur = longueur;
        this.largeur = largeur;
        this.vitesse = vitesse;
        this.rayon = rayon;
        this.angleOrientation = 1;
        this.isMoteurTouche = false;
        this.posX = -1;
        this.posY = -1;
        this.dommages = new ArrayList<>();
        for (int i = 0; i < this.longueur * this.largeur; i++) {
            this.dommages.add(false);
        }
        // si largeur est paire : moteur dominant -> droit
        // si largeur est impaire : moteur dominant -> centre (si 1 alors c'est lui)
    }

    /**
     * Methode qui donne la coordonnees X du cote oppose au moteur
     *
     * @return
     */
    public int getXGoalTete() {
        switch (this.getAngleOrientation()) {
            case 1:
                return this.getPosX() - (longueur - 1);
            case 3:
                return this.getPosX() + (longueur - 1);
            default:
                return this.getPosX();
        }
    }

    /**
     * Methode qui donne la coordonnes Y du cote oppose au moteur
     *
     * @return
     */
    public int getYGoalTete() {
        switch (this.getAngleOrientation()) {
            case 2:
                return this.getPosY() + (longueur - 1);
            case 4:
                return this.getPosY() - (longueur - 1);
            default:
                return this.getPosY();
        }
    }

    /**
     * Récupérer la case pivot du bateau
     *
     * @return la position pivot sur la longeur du bateau
     */
    public int getPivotBateau() {
        int pivot = 0;
        // Si la taille du bateau est paire, alors on prend pour pivot la / par 2
        if (this.getLongueur() % 2 == 0) {
            pivot = this.getLongueur() / 2;
        } else {
            // Si la taille du bateau est impaire, alors on prend pour pivot la / par 2 + 1
            pivot = (this.getLongueur() / 2) + 1;
        }
        return pivot;
    }

    /**
     * Pivoter bateau moins 90 degres(vers la gauche)
     */
    public void pivoterBateauMoins90Degres() {
        switch (this.getAngleOrientation()) {
            case 1:
                this.setPosX(this.posX - (this.getPivotBateau() - 1));
                this.setPosY(this.posY + (this.getPivotBateau() - 1));
                this.setAngleOrientation(4);
                break;
            case 2:
                this.setPosX(this.posX + (this.getPivotBateau() - 1));
                this.setPosY(this.posY + (this.getPivotBateau() - 1));
                this.setAngleOrientation(1);
                break;
            case 3:
                this.setPosX(this.posX + (this.getPivotBateau() - 1));
                this.setPosY(this.posY - (this.getPivotBateau() - 1));
                this.setAngleOrientation(2);
                break;
            case 4:
                this.setPosX(this.posX - (this.getPivotBateau() - 1));
                this.setPosY(this.posY - (this.getPivotBateau() - 1));
                this.setAngleOrientation(3);
                break;
            default:
                break;
        }
    }

    /**
     * Pivoter bateau plus 90 degres (vers la droite)
     */
    public void pivoterBateauPlus90Degres() {
        switch (this.getAngleOrientation()) {
            case 1:
                this.setPosX(this.posX - (this.getPivotBateau() - 1));
                this.setPosY(this.posY - (this.getPivotBateau() - 1));
                this.setAngleOrientation(2);
                break;
            case 2:
                this.setPosX(this.posX - (this.getPivotBateau() - 1));
                this.setPosY(this.posY + (this.getPivotBateau() - 1));
                this.setAngleOrientation(3);
                break;
            case 3:
                this.setPosX(this.posX + (this.getPivotBateau() - 1));
                this.setPosY(this.posY + (this.getPivotBateau() - 1));
                this.setAngleOrientation(4);
                break;
            case 4:
                this.setPosX(this.posX + (this.getPivotBateau() - 1));
                this.setPosY(this.posY - (this.getPivotBateau() - 1));
                this.setAngleOrientation(1);
                break;
            default:
                break;
        }
    }

    /**
     * Retourne la liste des cases que mon bateau couvre
     * @return liste des cases
     */
    public List<Case> getAllCasesCouvertes() {
        ArrayList<Case> liste = new ArrayList<>();
        switch (this.getAngleOrientation()) {
            case 1:
                for (int i = 0; i < this.longueur; i++) {
                    liste.add(new Case(this.posX - i, this.posY));
                }
                break;
            case 3:
                for (int i = 0; i < this.longueur; i++) {
                    liste.add(new Case(this.posX + i, this.posY));
                }
                break;
            case 4:
                for (int i = 0; i < this.longueur; i++) {
                    liste.add(new Case(this.posX, this.posY - i));
                }
                break;
            case 2:
                for (int i = 0; i < this.longueur; i++) {
                    liste.add(new Case(this.posX, this.posY + 1));
                }
                break;
            default:
                break;
        }
        return liste;
    }

    /**
     * Retourne la liste des cases que mon bateau peut "voir" via sa portee
     *
     * @return liste de cases
     */
    public List<Case> getMaVue() {
        // liste des cases couvertes par mon bateau
        ArrayList<Case> moi = (ArrayList<Case>) getAllCasesCouvertes();
        // init d'un set pour avoir des elements uniques
        Set<Case> liste = new HashSet<>();

        for (Case c : moi) {
            for (int i = 1; i < this.rayon; i++) {
                int x = c.getX();
                int y = c.getY();
                liste.add(new Case(x, y));
                liste.add(new Case(x - i, y));
                liste.add(new Case(x + i, y));
                liste.add(new Case(x, y - i));
                liste.add(new Case(x, y + i));
                liste.add(new Case(x + i, y + i));
                liste.add(new Case(x - i, y - i));
                liste.add(new Case(x - i, y + i));
                liste.add(new Case(x + i, y - i));
            }
        }
        // return une arraylist (je caste donc mon Set en List)
        return new ArrayList<>(liste);
    }

    /**
     * Méthode permettant de savoir si le bateau est placé verticalement sur le plateau ou non
     * @return un boolean true ou false
     */
    public boolean isVertical() {
        return (this.angleOrientation == 1 || this.angleOrientation == 3);
    }

    /**
     * Méthode permmettant de savoir si le bateau est placé horizontalement sur la plateau ou non
     * @return un boolean true ou false
     */
    public boolean isHorizontal() {
        return (this.angleOrientation == 2 || this.angleOrientation == 4);
    }

    /**
     * Méthode permettant de réparer un bateau sur ses coordonnées x, y si il est endommagé (sinon exception)
     * @param x la coordonnée x du bateau
     * @param y la coordonnée y du bateau
     * @throws BateauNonReparableException si le bateau n'est pas endommagé à cet endroit // pas réparable
     */
    public void reparer(int x, int y) throws BateauNonReparableException {
        //Si x fait la longueur du bateau alors je suis sur le moteur
        if (this.getDommages().get(x).booleanValue()) {
            this.impact(x, y, false);
        } else {
            throw new BateauNonReparableException();
        }
    }

    /**
     * Méthode permettant de savoir si un bateau est coulé ou non
     *
     * @return true ou false en fonction de la situation du bateau
     */
    public boolean isCoule() {
        for (int i = 0; i < this.getDommages().size(); i++) {
            if (!this.getDommages().get(i)) {
                //Le bateau n'est pas coulé
                return false;
            }
        }
        //Le bateau est coulé
        return true;
    }

    /**
     * Methode qui utilisée :
     *      - reparer()
     *      - effectuerTir() dans la facade
     * @param impactX
     * @param impactY
     * @param estTouche
     */
    public void impact(int impactX, int impactY, boolean estTouche) {
        if (impactX == this.getLongueur() - 1) {
            this.setDommage(impactX, estTouche);
            this.isMoteurTouche = estTouche;
        } else {
            //Si y==0 alors le bateau à une largeur de 1 donc ce n'est pas un porte avion et nous ne sommes pas sur le moteur
            if (impactY == 0) {
                this.setDommage(impactX, estTouche);
            } else {
                //Je suis sur le porte avion de largeur 2 avec 2 moteurs
                //Je vérifie que si je suis sur le premier moteur
                if (this.getLongueur() / 2 == impactX) {
                    this.setDommage(impactX, estTouche);
                    this.isMoteurTouche = estTouche;
                } else {
                    //Je suis sur la deuxieme colonne du bateau
                    int maFormule = this.getLongueur() / 2 + impactX;
                    this.setDommage(maFormule, estTouche);
                }
            }
        }
    }

    // GETTERS AND SETTERS

    public boolean getDommage(int index) {
        return dommages.get(index);
    }

    public void setDommage(int index, boolean b) {
        this.dommages.set(index, b);
    }

    public List<Boolean> getDommages() {
        return dommages;
    }

    public int getRayon() {
        return rayon;
    }

    public int getLargeur() {
        return largeur;
    }

    public int getLongueur() {
        return longueur;
    }

    public int getVitesse() {
        return vitesse;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public boolean isMoteurTouche() {
        return isMoteurTouche;
    }

    public void setMoteurTouche(boolean moteurTouche) {
        isMoteurTouche = moteurTouche;
    }

    public int getAngleOrientation() {
        return angleOrientation;
    }

    public void setAngleOrientation(int angleOrientation) {
        this.angleOrientation = angleOrientation;
    }
}
