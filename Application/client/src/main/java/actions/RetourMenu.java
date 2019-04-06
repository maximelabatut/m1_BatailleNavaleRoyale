package actions;

import exceptions.partie.JoueurInexistantException;
import modele.Joueur;
import modele.Partie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The type Retour menu.
 */
public class RetourMenu extends Environnement {

    // Variables
    private String pseudo;
    private List<String> joueursEnLigne = new ArrayList<>();
    private List<String> partiesEnCours = new ArrayList<>();

    @Override
    public String execute(){
        // On récupère l'id de l'utilisateur en variable de session
        int idJoueur = (int) getVariablesSession().get("monId");
        // Initialisation d'un joueur null
        Joueur j = null;
        try {
            // Récupération du joueur par son id
            j = getFacade().getJoueurById(idJoueur);
        } catch (JoueurInexistantException e) {
            return ERROR;
        }
        // Récupération du pseudo du joueur courant
        this.pseudo = j.getPseudo();
        // On récupère la liste des joueurs en ligne
        for (int i = 0; i < getFacade().getJoueursEnLigne().size(); i++) {
            try {
                int idJoueurEnLigne = getFacade().getJoueursEnLigne().get(i);
                joueursEnLigne.add(getFacade().getJoueurById(idJoueurEnLigne).getPseudo());
            } catch (JoueurInexistantException e) {
                return ERROR;
            }

        }
        // Récupération des parties en cours dans la facade
        Collection<Partie> maListeParties = getFacade().getListeParties().values();
        // Récupération des parties dans un tableau de parties
        Object[] mesParties = maListeParties.toArray();
        // Insertion de toutes les parties dans le select
        for (Object mesParty : mesParties) {
            Partie partieEnLigne = (Partie) mesParty;
            if (!partieEnLigne.isPartieTerminee()) {
                partiesEnCours.add("Partie de " + partieEnLigne.getHote().getPseudo());
            }
        }
        return SUCCESS;
    }

    /**
     * Gets parties en cours.
     *
     * @return the parties en cours
     */
    public List<String> getPartiesEnCours() {
        return partiesEnCours;
    }

    /**
     * Sets parties en cours.
     *
     * @param partiesEnCours the parties en cours
     */
    public void setPartiesEnCours(List<String> partiesEnCours) {
        this.partiesEnCours = partiesEnCours;
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

    /**
     * Gets joueurs en ligne.
     *
     * @return the joueurs en ligne
     */
    public List<String> getJoueursEnLigne() {
        return joueursEnLigne;
    }

    /**
     * Sets joueurs en ligne.
     *
     * @param joueursEnLigne the joueurs en ligne
     */
    public void setJoueursEnLigne(List<String> joueursEnLigne) {
        this.joueursEnLigne = joueursEnLigne;
    }
}
