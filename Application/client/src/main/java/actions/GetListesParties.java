package actions;

import exceptions.partie.JoueurInexistantException;
import modele.Partie;

import java.util.ArrayList;
import java.util.Collection;

public class GetListesParties extends Environnement {

    private Collection<Partie> listeParties;
    private String pseudo;

    @Override
    public String execute() {
        Collection<Partie> listeParties = getFacade().getListeParties().values();
        this.listeParties = new ArrayList<>();
        int idJoueur = (int) getVariablesSession().get("monId");
        try {
            this.pseudo = getFacade().getJoueurById(idJoueur).getPseudo();
        } catch (JoueurInexistantException e) {
            return ERROR;
        }

        // Récupération des l'ensemble des parties dans un tableau d'objets mesParties
        Object [] mesParties = listeParties.toArray();

        // Pour chaque partie, on test si elle est publique ou privée pour l'ajouter à la bonne liste
        for (Object mesParty : mesParties) {
            Partie maPartie = (Partie) mesParty;
            if (maPartie.getMesJoueurs().size() != maPartie.getNbJoueurAttendu()) {
                if (!maPartie.isLancee()) {
                    this.listeParties.add(maPartie);
                }
            }
        }
        return SUCCESS;
    }

    public Collection<Partie> getListeParties() {
        return listeParties;
    }

    public void setListeParties(Collection<Partie> listeParties) {
        this.listeParties = listeParties;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
}
