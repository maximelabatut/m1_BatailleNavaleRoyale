package actions;


import exceptions.partie.PartieIntrouvableException;

/**
 * The type Deconnexion.
 */
public class Deconnexion extends Environnement {

    @Override
    public String execute() {

        // Récupération de l'id de l'utilisateur connecté
        int idJoueur = (int) getVariablesSession().get("monId");

        // On déconnecte l'utilisateur
        try {
            getFacade().deconnexion(idJoueur);
        } catch (PartieIntrouvableException e) {
            return ERROR;
        }
        // On efface la variable de session
        getVariablesSession().remove("monId");
        return SUCCESS;
    }

}
