package actions;

import exceptions.partie.JoueurInexistantException;
import exceptions.partie.NbJoueurMaxException;
import exceptions.partie.PartieIntrouvableException;
import modele.Joueur;
import modele.Partie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The type Rejoindre partie.
 */
public class RejoindrePartie extends Environnement {

    private int idPartie;
    private String pseudo;
    private List<String> listeJoueursConnectes;
    private int hote;
    private ArrayList<Partie> listeParties;

    @Override
    public String execute() {
        try {
            // Comme le joueur rejoint une partie, il n'est donc pas l'hôte
            this.hote = 0;
            // On recupère l'id du joueur dans les variables de session et on retrouve son pseudo
            int idJoueur = (int) getVariablesSession().get("monId");
            // On récupère le pseudo du joueur concerné
            this.pseudo = getFacade().getJoueurById(idJoueur).getPseudo();
            // On ajoute le joueur concerné à la partie qu'il a rejoint dans la liste de la facade
            getFacade().rejoindrePartie(idPartie, idJoueur);
            //
            getVariablesSession().put("idPartie", this.idPartie);
            // Récupération des joueurs
            List<Joueur> listeJoueurs = getFacade().getJoueurPartie(this.idPartie);
            // Instanciation d'une liste de joueurs vide
            this.listeJoueursConnectes = new ArrayList<>();
            for (Joueur listeJoueur : listeJoueurs) {
                listeJoueursConnectes.add(listeJoueur.getPseudo());
            }
            System.out.println("Joueur : " + getFacade().getJoueurById(idJoueur).getPseudo() + " ID PARTIE : " + this.idPartie);
            return SUCCESS;
        } catch (JoueurInexistantException | PartieIntrouvableException | NbJoueurMaxException e) {


            Collection<Partie> listeParties = getFacade().getListeParties().values();
            this.listeParties = new ArrayList<>();
            int idJoueur = (int) getVariablesSession().get("monId");
            try {
                this.pseudo = getFacade().getJoueurById(idJoueur).getPseudo();
            } catch (JoueurInexistantException e1) {
                return ERROR;
            }

            // Récupération des l'ensemble des parties dans un tableau d'objets mesParties
            Object [] mesParties = listeParties.toArray();

            // Pour chaque partie, on test si elle est publique ou privée pour l'ajouter à la bonne liste
            for (int i = 0; i<mesParties.length; i++){
                // Si la partie n'est pas complète
                Partie maPartie = (Partie)mesParties[i];
                if(maPartie.getMesJoueurs().size() != maPartie.getNbJoueurAttendu()){
                    // Si la partie est publique ET qu'elle n'est pas lancée
                    if(!maPartie.isLancee()){
                        this.listeParties.add(maPartie);
                    }
                }
            }


            return ERROR;
        }
    }

    /**
     * Gets liste parties.
     *
     * @return the liste parties
     */
    public ArrayList<Partie> getListeParties() {
        return listeParties;
    }

    /**
     * Sets liste parties.
     *
     * @param listeParties the liste parties
     */
    public void setListeParties(ArrayList<Partie> listeParties) {
        this.listeParties = listeParties;
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
     * Gets liste joueurs connectes.
     *
     * @return the liste joueurs connectes
     */
    public List<String> getListeJoueursConnectes() {
        return listeJoueursConnectes;
    }

    /**
     * Sets liste joueurs connectes.
     *
     * @param listeJoueursConnectes the liste joueurs connectes
     */
    public void setListeJoueursConnectes(List<String> listeJoueursConnectes) {
        this.listeJoueursConnectes = listeJoueursConnectes;
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
     * Gets hote.
     *
     * @return the hote
     */
    public int getHote() {
        return hote;
    }

    /**
     * Sets hote.
     *
     * @param hote the hote
     */
    public void setHote(int hote) {
        this.hote = hote;
    }
}
