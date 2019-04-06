package modele;

import exceptions.partie.*;
import modele.bateau.Bateau;

import java.util.ArrayList;
import java.util.List;

public class Partie implements IPartie {

    private static int idprive = 0;

    private int idPartie;
    private ArrayList<Joueur> mesJoueurs;
    private int pointAction;
    private int nbJoueurAttendu;
    private boolean isLancee;
    private boolean isPartieTerminee;
    private Joueur hote;
    private Plateau plateau;

    /**
     * Constructeur de la classe partie
     *
     * @param largeur du plateau
     * @param hauteur du plateau
     * @throws TaillePlateauIncorrecteException
     */
    public Partie(int largeur, int hauteur, Joueur hote, int nbJoueurAttendu) throws TaillePlateauIncorrecteException {
        this.idPartie = idprive;
        this.mesJoueurs = new ArrayList<>();
        this.pointAction = 5;
        this.isLancee = false;
        this.isPartieTerminee = false;
        this.hote = hote;
        this.plateau = new Plateau(largeur, hauteur);
        this.nbJoueurAttendu = nbJoueurAttendu;
        idprive++;
        mesJoueurs.add(hote);
    }

    /**
     * Méthode permettant d'ajouter un joueur dans la partie
     *
     * @param j le joueur à ajouter
     * @throws NbJoueurMaxException
     */
    public void ajouterJoueur(Joueur j) throws NbJoueurMaxException {
        if (mesJoueurs.size() < 4) {
            mesJoueurs.add(j);
        } else {
            throw new NbJoueurMaxException();
        }
    }

    /**
     * Méthode permettant de supprimer un joueur de la partie
     *
     * @param idJoueur l'idPartie du joueur à supprimer
     * @throws NbJoueursIncorrectException si le joueur n'existe pas
     */
    public void supprimerJoueur(int idJoueur) throws NbJoueursIncorrectException {
        if (!this.mesJoueurs.isEmpty()) {
            for (int i = 0; i < this.mesJoueurs.size(); i++) {
                if (this.mesJoueurs.get(i).getIdJoueur() == idJoueur) {
                    this.mesJoueurs.remove(i);
                }
            }
        } else {
            throw new NbJoueursIncorrectException();
        }
    }

    /**
     * Permet de lancer une partie en initialisant le pointeur sur le joueur qui commence la partie
     *
     * @throws NbJoueursIncorrectException si le nombre de joueur est inférieur à 2
     */
    public void lancerPartie() throws NbJoueursIncorrectException, TaillePlateauIncorrecteException {
        if (this.mesJoueurs.size() >= 2) {
            this.isLancee = true;
            this.plateau = new Plateau(100, 100);

        } else {
            throw new NbJoueursIncorrectException();
        }
    }

    public void placerBateau(int idJoueur, int posXMoteur, int posYMoteur, Bateau b,int angleOrientation) throws JoueurInexistantException, PositionBateauHorsPlateauException {
        int posJoueur = getPositionJoueur(idJoueur);

        int longueurBateauX = b.getLongueur() - 1;
        longueurBateauX = longueurBateauX + posXMoteur;

        int longueurBateauY = b.getLongueur() - 1;
        longueurBateauY = longueurBateauY + posYMoteur;

        switch (posJoueur){
            // Si c'est le joueur 1 alors il est en haut à gauche du plateau
            case 1:
                // si l'orientation est vers le haut
                switch (angleOrientation){
                    case 1 :
                        if(!(posXMoteur - (b.getLongueur() - 1) >= 0)) {
                            throw new PositionBateauHorsPlateauException();
                        }
                        break;
                    case 2 :
                        if(!(posYMoteur + (b.getLongueur() - 1) <= 24)){
                            throw new PositionBateauHorsPlateauException();
                        }
                        break;
                    case 3 :
                        if(!(posXMoteur + (b.getLongueur() - 1) <= 24)){
                            throw new PositionBateauHorsPlateauException();
                        }
                        break;
                    case 4 :
                        if(!(posYMoteur - (b.getLongueur() - 1) >= 0)){
                            throw new PositionBateauHorsPlateauException();
                        }
                        break;
                }
                break;
            // Si c'est le joueur 2 alors il est en haut à droite du plateau
            case 2:
                // si l'orientation est vers le haut
                switch (angleOrientation){
                    case 1 :
                        if(!(posXMoteur - (b.getLongueur() - 1) >= 0)) {
                            throw new PositionBateauHorsPlateauException();
                        }
                        break;
                    case 2 :
                        if(!(posYMoteur + (b.getLongueur() - 1) <= 99)){
                            throw new PositionBateauHorsPlateauException();
                        }
                        break;
                    case 3 :
                        if(!(posXMoteur + (b.getLongueur() - 1) <= 24)){
                            throw new PositionBateauHorsPlateauException();
                        }
                        break;
                    case 4 :
                        if(!(posYMoteur - (b.getLongueur() - 1) >= 74)){
                            throw new PositionBateauHorsPlateauException();
                        }
                        break;
                }
                break;
            // Si c'est le joueur 3 alors il est en bas à gauche du plateau
            case 3 :
                // si l'orientation est vers le haut
                switch (angleOrientation){
                    case 1 :
                        if(!(posXMoteur - (b.getLongueur() - 1) >= 74)) {
                            throw new PositionBateauHorsPlateauException();
                        }
                        break;
                    case 2 :
                        if(!(posYMoteur + (b.getLongueur() - 1) <= 24)){
                            throw new PositionBateauHorsPlateauException();
                        }
                        break;
                    case 3 :
                        if(!(posXMoteur + (b.getLongueur() - 1) <= 99)){
                            throw new PositionBateauHorsPlateauException();
                        }
                        break;
                    case 4 :
                        if(!(posYMoteur - (b.getLongueur() - 1) >= 0)){
                            throw new PositionBateauHorsPlateauException();
                        }
                        break;
                }
                break;
            // Si c'est le joueur 4 alors il est en bas à droite du plateau
            case 4:
                // si l'orientation est vers le haut
                switch (angleOrientation){
                    case 1 :
                        if(!(posXMoteur - (b.getLongueur() - 1) >= 74)){
                            throw new PositionBateauHorsPlateauException();
                        }
                        break;
                    case 2 :
                        if(!(posYMoteur + (b.getLongueur() - 1) <= 99)){
                            throw new PositionBateauHorsPlateauException();
                        }
                        break;
                    case 3 :
                        if(!(posXMoteur + (b.getLongueur() - 1) <= 99)){
                            throw new PositionBateauHorsPlateauException();
                        }
                        break;
                    case 4 :
                        if(!(posYMoteur - (b.getLongueur() - 1) >= 74)){
                            throw new PositionBateauHorsPlateauException();
                        }
                        break;
                    default :
                        break;
                }
                break;
            default :
                break;
        }

        updatePosition(angleOrientation, posYMoteur, posXMoteur, b);
        updateImage(angleOrientation, posXMoteur, posYMoteur, b);
    }

    public void updatePosition(int angleOrientation, int posYMoteur, int posXMoteur, Bateau b){
        b.setAngleOrientation(angleOrientation);
        b.setPosY(posYMoteur);
        b.setPosX(posXMoteur);
    }

    public void updateImage(int angleOrientation, int posXMoteur, int posYMoteur, Bateau b){
        Case[][] monPlateau = this.plateau.getLePlateau();

        switch(angleOrientation){
            case 1 :
                for(int i=0; i<b.getLongueur(); i++){
                    if(i==0){
                        monPlateau[posXMoteur-i][posYMoteur].setImgPath("./img/moteur.png");
                    }else{
                        monPlateau[posXMoteur-i][posYMoteur].setImgPath("./img/bateau.png");
                    }
                }
                break;
            case 2 :
                for(int i=0; i<b.getLongueur(); i++){
                    if(i==0){
                        monPlateau[posXMoteur][posYMoteur+i].setImgPath("./img/moteur.png");
                    }else{
                        monPlateau[posXMoteur][posYMoteur+i].setImgPath("./img/bateau.png");
                    }
                }
                break;
            case 3 :
                for(int i=0; i<b.getLongueur(); i++){
                    if(i==0){
                        monPlateau[posXMoteur+i][posYMoteur].setImgPath("./img/moteur.png");
                    }else{
                        monPlateau[posXMoteur+i][posYMoteur].setImgPath("./img/bateau.png");
                    }
                }
                break;
            case 4 :
                for(int i=0; i<b.getLongueur(); i++){
                    if(i==0){
                        monPlateau[posXMoteur][posYMoteur-i].setImgPath("./img/moteur.png");
                    }else{
                        monPlateau[posXMoteur][posYMoteur-i].setImgPath("./img/bateau.png");
                    }
                }
                break;
            default :
                break;
        }
    }

    /**
     * Méthode permettant de savoir si un nombre passé en paramètre est pair ou non
     *
     * @param number le nombre a tester
     * @return true ou false selon si le nombre est pair ou non
     */
    public boolean isPair(int number) {
        return number % 2 == 0;
    }

    /**
     * Méthode permettant de retourner la position du joueur sur le plateau. Par convention :
     * le joueur 0 dans la liste des joueurs est en haut à gauche (1)
     * Le joueur 1 dans la liste des joueurs est en haut à droite (2)
     * Le joueur 2 dans la liste des joueurs est en bas à gauche (3)
     * Le joueur 3 dans la liste des joueurs est en bas à droite (4)
     *
     * @param idJoueur l'id du joueur dont on veut récupérer la position
     * @return la position du joueur concerné
     * @throws JoueurInexistantException si le jour n'existe pas dans la partie
     */
    public int getPositionJoueur(int idJoueur) throws JoueurInexistantException {
        for (int i = 0; i < this.mesJoueurs.size(); i++) {
            if (this.mesJoueurs.get(i).getIdJoueur() == idJoueur) {
                return i + 1;
            }
        }
        throw new JoueurInexistantException();
    }

    // GETTERS AND SETTERS

    public boolean isPartieTerminee() {
        return isPartieTerminee;
    }

    public void setPartieTerminee(boolean partieTerminee) {
        isPartieTerminee = partieTerminee;
    }

    public Joueur getHote() {
        return hote;
    }

    public List<Joueur> getMesJoueurs() {
        return mesJoueurs;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public int getPointAction() {
        return pointAction;
    }

    public boolean isLancee() {
        return isLancee;
    }

    public int getNbJoueurAttendu() {
        return nbJoueurAttendu;
    }

    public int getIdPartie() {
        return idPartie;
    }
}
