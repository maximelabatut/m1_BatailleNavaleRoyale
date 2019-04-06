package actions;

import exceptions.partie.JoueurInexistantException;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Initialisation page creation.
 */
public class InitialisationPageCreation extends Environnement {

    // Variables
    private String pseudo;
    private List<Integer> nbJoueurs;

    @Override
    public String execute() {
        // On récupère l'id du joueur avec la variable de session
        int idJoueur = (int) getVariablesSession().get("monId");
        try {
            // On récupère le joueur
            this.pseudo = getFacade().getJoueurById(idJoueur).getPseudo();
        } catch (JoueurInexistantException e) {
            return ERROR;
        }
        // Initialisation d'une liste pour le bouton radio
        this.nbJoueurs = new ArrayList<>();
        this.nbJoueurs.add(2);
        this.nbJoueurs.add(3);
        this.nbJoueurs.add(4);
        return SUCCESS;
    }

    /**
     * Gets nb joueurs.
     *
     * @return the nb joueurs
     */
    public List<Integer> getNbJoueurs() {
        return nbJoueurs;
    }

    /**
     * Sets nb joueurs.
     *
     * @param nbJoueurs the nb joueurs
     */
    public void setNbJoueurs(List<Integer> nbJoueurs) {
        this.nbJoueurs = nbJoueurs;
    }

    /**
     * Gets pseudo.
     *
     * @return the pseudo
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * Sets pseudo.
     *
     * @param pseudo the pseudo
     */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
}
