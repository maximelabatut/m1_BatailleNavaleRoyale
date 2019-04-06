package actions;

import exceptions.partie.*;
import modele.Case;
import modele.Joueur;
import modele.Plateau;
import modele.bateau.Bateau;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The type Initialisation page creation.
 */
public class PoserBateau extends Environnement {

    private String pseudo;
    private int nbBateau;
    private int orientationBateau;
    private int posx;
    private int posy;
    private Case[][] plateau;
    private List<Bateau> bateaux;
    private int coinPlateau;
    private List<Integer> coordsX;
    private List<Integer> coordsY;
    private List<Integer> orientation;
    private Joueur joueur;
    private Plateau monPlateau;

    @Override
    public String execute() throws JoueurInexistantException, PartieIntrouvableException, PositionBateauHorsPlateauException {
        try {
            if (!getFacade().getPartieById((int) getVariablesSession().get("idPartie")).isLancee() && getVariablesSession().containsKey("hote")) {
                try {
                    getFacade().getPartieById((int) getVariablesSession().get("idPartie")).lancerPartie();
                } catch (NbJoueursIncorrectException e) {
                    return ERROR;
                } catch (TaillePlateauIncorrecteException e) {
                    return ERROR;
                }
            }
        } catch (PartieIntrouvableException e) {
            return ERROR;
        }

        // On recupère l'id du joueur dans les variables de session et on retrouve son pseudo
        int idJoueur = (int) getVariablesSession().get("monId");
        Joueur j = getFacade().getJoueurById(idJoueur);
        // On pose le bateau
        getFacade().placerBateau(idJoueur, posx,posy,j.getListeBateau().get(nbBateau), orientationBateau);
        this.bateaux = j.getListeBateau();


        //On garde seulement les bateaux non placés
        for(int i = 0; i < this.bateaux.size(); i++){
            if(this.bateaux.get(i).getPosX() != -1 && this.bateaux.get(i).getPosY() != -1){
                this.bateaux.remove(i);
            }
        }
        if(this.bateaux.size() == 0){
            // Récupération du joueur par son id
            this.joueur = j;
            Map<Integer, Integer> listeJoueursDansParties = getFacade().getJoueursDansParties();
            int idPartie = listeJoueursDansParties.get(idJoueur);
            this.monPlateau = getFacade().getPartieById((int) getVariablesSession().get("idPartie")).getPlateau();
            return "configOK";
        }

        try {
            this.plateau = getFacade().getPartieById((int) getVariablesSession().get("idPartie")).getPlateau().getLePlateau();
        } catch (PartieIntrouvableException e) {
            return ERROR;
        }
        try {
            this.coinPlateau = getFacade().positionJoueurSurPlateau(idJoueur);
        } catch (PartieIntrouvableException e) {
            return ERROR;
        } catch (JoueurInexistantException e) {
            e.printStackTrace();
            return ERROR;
        }

        this.orientation = new ArrayList<>();
        this.orientation.add(1);
        this.orientation.add(2);
        this.orientation.add(3);
        this.orientation.add(4);

        this.coordsX = new ArrayList<>();
        this.coordsY = new ArrayList<>();
        int decalX=0;
        int decalY=0;
        switch (coinPlateau){
            case 1:
                decalX = 0;
                decalY = 0;
                break;
            case 2:
                decalX = 0;
                decalY = plateau.length/4+plateau.length/2;
                break;
            case 3:
                decalX = plateau.length/4+plateau.length/2;
                decalY = 0;
                break;
            case 4:
                decalX = plateau.length/4+plateau.length/2;
                decalY = plateau.length/4+plateau.length/2;
                break;
        }

        for(int i=0; i<plateau.length/4;i++){
            coordsX.add(i+decalX);
        }
        for(int i=0; i<plateau.length/4;i++){
            coordsY.add(i+decalY);
        }
        this.posx = decalX;
        this.posy = decalY;
        return SUCCESS;
    }

    public int getPosx() {
        return posx;
    }

    public void setPosx(int posx) {
        this.posx = posx;
    }

    public int getPosy() {
        return posy;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public int getNbBateau() {
        return nbBateau;
    }

    public void setNbBateau(int nbBateau) {
        this.nbBateau = nbBateau;
    }

    public int getOrientationBateau() {
        return orientationBateau;
    }

    public void setOrientationBateau(int orientationBateau) {
        this.orientationBateau = orientationBateau;
    }

    public Case[][] getPlateau() {
        return plateau;
    }

    public void setPlateau(Case[][] plateau) {
        this.plateau = plateau;
    }

    public List<Bateau> getBateaux() {
        return bateaux;
    }

    public void setBateaux(List<Bateau> bateaux) {
        this.bateaux = bateaux;
    }

    public int getCoinPlateau() {
        return coinPlateau;
    }

    public void setCoinPlateau(int coinPlateau) {
        this.coinPlateau = coinPlateau;
    }

    public List<Integer> getOrientation() {
        return orientation;
    }

    public void setOrientation(List<Integer> orientation) {
        this.orientation = orientation;
    }

    public List<Integer> getCoordsX() {
        return coordsX;
    }

    public void setCoordsX(List<Integer> coordsX) {
        this.coordsX = coordsX;
    }

    public List<Integer> getCoordsY() {
        return coordsY;
    }

    public void setCoordsY(List<Integer> coordsY) {
        this.coordsY = coordsY;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public Plateau getMonPlateau() {
        return monPlateau;
    }

    public void setMonPlateau(Plateau monPlateau) {
        this.monPlateau = monPlateau;
    }
}
