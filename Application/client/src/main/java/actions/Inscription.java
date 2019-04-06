package actions;

import exceptions.identification.PseudoDejaPrisException;

/**
 * The type Inscription.
 */
public class Inscription extends Environnement {

    private static final String PSEUDOINSCRIPTION_INPUT = "pseudoInscription";

    // Variables
    private String pseudoInscription;
    private String mdpInscription;
    private static final String CREE="cree";
    private String pseudo;

    @Override
    public String execute() {
        if(pseudo == null) {
            try {
                // On inscrit le joueur
                // On récupère son id
                int idJoueur = getFacade().inscription(pseudoInscription, mdpInscription);
                getVariablesSession().put("monId", idJoueur);
                return SUCCESS;
            } catch (PseudoDejaPrisException e) {
                // Formulaire mal rempli
                addFieldError(PSEUDOINSCRIPTION_INPUT, getText("errors.pseudo.alreadyExist"));
                return INPUT;
            }
        } else {
            return CREE;
        }
    }

    @Override
    public void validate() {
        if(pseudo == null) {
            if (pseudoInscription == null || pseudoInscription.length() < 3) {
                addFieldError(PSEUDOINSCRIPTION_INPUT, getText("errors.pseudo.requiredstring"));
            }
            if (pseudoInscription.length() > 15) {
                addFieldError(PSEUDOINSCRIPTION_INPUT, getText("errors.pseudo.maxlength"));
            }
            if (mdpInscription.equals("")) {
                addFieldError("mdpInscription", getText("errors.mdp.requiredstring"));
            }
        }
    }

    /**
     * Gets pseudo inscription.
     *
     * @return the pseudo inscription
     */
    public String getPseudoInscription() {
        return pseudoInscription;
    }

    /**
     * Sets pseudo inscription.
     *
     * @param pseudoInscription the pseudo inscription
     */
    public void setPseudoInscription(String pseudoInscription) {
        this.pseudoInscription = pseudoInscription;
    }

    /**
     * Gets mdp inscription.
     *
     * @return the mdp inscription
     */
    public String getMdpInscription() {
        return mdpInscription;
    }

    /**
     * Sets mdp inscription.
     *
     * @param mdpInscription the mdp inscription
     */
    public void setMdpInscription(String mdpInscription) {
        this.mdpInscription = mdpInscription;
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
