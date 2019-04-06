package actions;


import exceptions.identification.DejaConnecteException;
import exceptions.identification.MotDePasseIncorrectException;
import exceptions.partie.JoueurInexistantException;
import modele.Partie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The type Connexion.
 */
public class Connexion extends Environnement {

    private static final String PSEUDO_INPUT = "pseudo";
    private String pseudo;
    private String mdp;
    private List<String> joueursEnLigne = new ArrayList<>();
    private List<String> listeParties = new ArrayList<>();

    @Override
    public String execute(){

        int idJoueur = 0;
        try {
            idJoueur = getFacade().connexion(pseudo, mdp);
        } catch (DejaConnecteException e) {
            addFieldError(PSEUDO_INPUT, getText("errors.pseudo.alreadyConnected"));
            return INPUT;
        } catch (MotDePasseIncorrectException e) {
            addFieldError(PSEUDO_INPUT, getText("errors.pseudo.wrongPassword"));
            return INPUT;
        }

        getVariablesSession().put("monId", idJoueur);

            // Récupération des joueurs en ligne dans la facade
            for(int i = 0; i<getFacade().getJoueursEnLigne().size(); i++){
                int idJoueurEnLigne = getFacade().getJoueursEnLigne().get(i);
                try {
                    joueursEnLigne.add(getFacade().getJoueurById(idJoueurEnLigne).getPseudo());
                } catch (JoueurInexistantException e) {
                    return ERROR;
                }
            }

            // Récupération des parties en cours dans la facade
            Collection<Partie> maListeParties = getFacade().getListeParties().values();
            Object[] mesParties = maListeParties.toArray();
            for (Object mesParty : mesParties) {
                Partie partieEnLigne = (Partie) mesParty;
                if (!partieEnLigne.isPartieTerminee()) {
                    listeParties.add("Partie de " + partieEnLigne.getHote().getPseudo());
                }
            }
            return SUCCESS;

    }

    @Override
    public void validate() {
        if (pseudo == null || pseudo.length() < 3) {
            addFieldError(PSEUDO_INPUT, getText("errors.pseudo.requiredstring"));
        }
        if (pseudo.length() > 15) {
            addFieldError(PSEUDO_INPUT, getText("errors.pseudo.maxlength"));
        }
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
     * Gets mdp.
     *
     * @return the mdp
     */
    public String getMdp() {
        return mdp;
    }

    /**
     * Sets mdp.
     *
     * @param mdp the mdp
     */
    public void setMdp(String mdp) {
        this.mdp = mdp;
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

    /**
     * Gets liste parties.
     *
     * @return the liste parties
     */
    public List<String> getListeParties() {
        return listeParties;
    }

    /**
     * Sets liste parties.
     *
     * @param listeParties the liste parties
     */
    public void setListeParties(List<String> listeParties) {
        this.listeParties = listeParties;
    }
}
