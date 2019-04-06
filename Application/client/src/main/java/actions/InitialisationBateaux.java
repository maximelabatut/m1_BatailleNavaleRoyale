package actions;

import exceptions.partie.JoueurInexistantException;
import exceptions.partie.NbJoueursIncorrectException;
import exceptions.partie.PartieIntrouvableException;
import exceptions.partie.TaillePlateauIncorrecteException;
import modele.Case;
import modele.Joueur;
import modele.bateau.Bateau;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Initialisation page creation.
 */
public class InitialisationBateaux extends Environnement {

    private String pseudo;
    private Case[][] plateau;
    private List<Bateau> bateaux;
    private int coinPlateau;
    private List<Integer> orientation;
    private List<Integer> coordsX;
    private List<Integer> coordsY;

    @Override
    public String execute() throws JoueurInexistantException {
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
        this.bateaux = j.getListeBateau();
        try {
            this.plateau = getFacade().getPartieById((int) getVariablesSession().get("idPartie")).getPlateau().getLePlateau();
        } catch (PartieIntrouvableException e) {
            return ERROR;
        }
        try {
            this.coinPlateau = getFacade().positionJoueurSurPlateau(idJoueur);
        } catch (PartieIntrouvableException e) {
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

        return SUCCESS;
    }


    public List<Bateau> getBateaux() {
        return bateaux;
    }

    public void setBateaux(List<Bateau> bateaux) {
        this.bateaux = bateaux;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public Case[][] getPlateau() {
        return plateau;
    }

    public void setPlateau(Case[][] plateau) {
        this.plateau = plateau;
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
}