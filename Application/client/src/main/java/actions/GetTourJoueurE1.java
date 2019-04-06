package actions;

import exceptions.partie.JoueurInexistantException;
import exceptions.partie.NbJoueursIncorrectException;
import exceptions.partie.PartieIntrouvableException;
import exceptions.partie.TaillePlateauIncorrecteException;
import modele.Case;
import modele.Joueur;
import modele.Plateau;

import java.util.Map;


/**
 * The type Get tour joueur e 1.
 */
public class GetTourJoueurE1 extends Environnement {

    private Plateau monPlateau;
    private Joueur joueur;

    public String execute() {
        try{
            // On récupère l'id de joueur par la variable de session
            int idJoueur = (int) getVariablesSession().get("monId");
            // Récupération du joueur par son id
            this.joueur = getFacade().getJoueurById(idJoueur);

            Map<Integer, Integer> listeJoueursDansParties = getFacade().getJoueursDansParties();
            int idPartie = listeJoueursDansParties.get(idJoueur);
            this.monPlateau = getFacade().getPartieById((int) getVariablesSession().get("idPartie")).getPlateau();
        }
        catch(PartieIntrouvableException pie){
            pie.printStackTrace();
        } catch (JoueurInexistantException e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * Gets joueur.
     *
     * @return the joueur
     */
    public Joueur getJoueur() {
        return joueur;
    }

    /**
     * Sets joueur.
     *
     * @param joueur the joueur
     */
    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    /**
     * Gets mon plateau.
     *
     * @return the mon plateau
     */
    public Plateau getMonPlateau() {
        return monPlateau;
    }

    /**
     * Sets mon plateau.
     *
     * @param monPlateau the mon plateau
     */
    public void setMonPlateau(Plateau monPlateau) {
        this.monPlateau = monPlateau;
    }

}
