package actions;

import exceptions.partie.JoueurInexistantException;
import exceptions.partie.NbJoueursIncorrectException;
import exceptions.partie.PartieIntrouvableException;
import modele.Joueur;
import modele.Partie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The type Quitter salon.
 */
public class QuitterSalon extends Environnement {

    private String pseudo;
    private int idPartie;
    private List<String> joueursEnLigne = new ArrayList<>();
    private List<String> partiesPubliquesEnCours = new ArrayList<>();

    @Override
    public String execute() {
        try {
            // Récupération de l'id utilisateur par la variable de session
            int idJoueur = (int) getVariablesSession().get("monId");
            // On teste si l'utilisateur est dans une partie
            if(this.getVariablesSession().containsKey("idPartie")) {
                // On supprime la variable de session de partie
                getFacade().supprimerJoueurPartie((int) this.getVariablesSession().get("idPartie"), idJoueur);
                getFacade().getJoueursDansParties().remove(idJoueur);
                this.getVariablesSession().remove("idPartie");
            }

            if(getVariablesSession().containsKey("hote")){
                getVariablesSession().remove("hote");
            }
            if(getVariablesSession().containsKey("idPartie")){
                getVariablesSession().remove("idPartie");
            }
            // Récupération du joueur par son id
            Joueur j = getFacade().getJoueurById(idJoueur);
            // On stock le pseudo du joueur
            this.pseudo = j.getPseudo();
            // Si le joueur concerné est hôte on supprime la partie
            if ((getFacade().getPartieById(idPartie).getHote().getPseudo().equals(j.getPseudo()))) {
                getFacade().supprimerPartieEtSesJoueurs(idPartie);
            }
            // Sinon, on supprime simplement ce joueur de la partie concernée
            else {
                getFacade().supprimerJoueurPartie(idPartie, idJoueur);
            }

            // On récupère la liste des joueurs en ligne car on va rediriger vers le menu
            for (int i = 0; i < getFacade().getJoueursEnLigne().size(); i++) {
                int idJoueurEnLigne = getFacade().getJoueursEnLigne().get(i);
                joueursEnLigne.add(getFacade().getJoueurById(idJoueurEnLigne).getPseudo());
            }

            // Récupération des parties en cours dans la facade
            Collection<Partie> maListeParties = getFacade().getListeParties().values();
            Object[] mesParties = maListeParties.toArray();
            for (Object mesParty : mesParties) {
                Partie partieEnLigne = (Partie) mesParty;
                if (!partieEnLigne.isPartieTerminee()) {
                    partiesPubliquesEnCours.add("Partie de " + partieEnLigne.getHote().getPseudo());
                }
            }


            return SUCCESS;
        } catch (JoueurInexistantException | PartieIntrouvableException | NbJoueursIncorrectException e) {
            return ERROR;
        }
    }

    /**
     * Gets parties publiques en cours.
     *
     * @return the parties publiques en cours
     */
    public List<String> getPartiesPubliquesEnCours() {
        return partiesPubliquesEnCours;
    }

    /**
     * Sets parties publiques en cours.
     *
     * @param partiesPubliquesEnCours the parties publiques en cours
     */
    public void setPartiesPubliquesEnCours(List<String> partiesPubliquesEnCours) {
        this.partiesPubliquesEnCours = partiesPubliquesEnCours;
    }

    /**
     * Gets id partie.
     *
     * @return the id partie
     */
    public int getIdPartie() {
        return idPartie;
    }

    /**
     * Sets id partie.
     *
     * @param idPartie the id partie
     */
    public void setIdPartie(int idPartie) {
        this.idPartie = idPartie;
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
